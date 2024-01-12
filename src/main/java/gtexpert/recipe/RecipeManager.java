package gtexpert.recipe;

import java.util.*;
import java.util.stream.Collectors;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.eventhandler.EventPriority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Preconditions;

import gtexpert.api.modules.IModuleContainer;
import gtexpert.api.modules.ModuleContainer;

public class RecipeManager implements IGTERecipeManager {

    private static final RecipeManager INSTANCE = new RecipeManager();

    private Map<String, IModuleContainer> containers = new LinkedHashMap<>();
    private final Map<ResourceLocation, IGTERecipe> sortedModules = new LinkedHashMap<>();
    private final Set<IGTERecipe> loadedRecipes = new LinkedHashSet<>();
    private final Map<EventPriority, List<IGTERecipe>> recipePriorities = new LinkedHashMap<>();

    private boolean canRegisterContainer = true;

    private final Logger logger = LogManager.getLogger("GTExpert Recipe Loader");

    private RecipeManager() {}

    public static RecipeManager getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isModuleEnabled(ResourceLocation id) {
        return sortedModules.containsKey(id);
    }

    @Override
    public void registerContainer(IModuleContainer container) {
        if (!canRegisterContainer) {
            logger.error("Failed to register module container {}, as recipe loading has a already begun", container);
            return;
        }
        Preconditions.checkNotNull(container);
        containers.put(container.getID(), container);
    }

    @Override
    public IModuleContainer getLoadedContainer() {
        return null;
    }

    public void setup(ASMDataTable asmDataTable) {
        discoverContainers(asmDataTable);
        containers = containers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        canRegisterContainer = false;
        Map<String, List<IGTERecipe>> modules = getModules(asmDataTable);
        logger.info("Size of modules: " + modules.size());
        configureModules(modules);
    }

    private void init(EventPriority priority) {
        List<IGTERecipe> recipes = recipePriorities.get(priority);
        if(recipes == null) {
            logger.info("priority " + priority.name() + " has no gtexpert recipes");
            return;
        }
        for (IGTERecipe recipe : recipes) {
            recipe.getLogger().debug("initialize start");
            recipe.init();
            recipe.getLogger().debug("initialize complete");
        }
    }

    public void loadHighest() {
        init(EventPriority.HIGHEST);
    }

    public void loadHigh() {
        init(EventPriority.HIGH);
    }

    public void loadNormal() {
        init(EventPriority.NORMAL);
    }

    public void loadLow() {
        init(EventPriority.LOW);
    }

    public void loadLowest() {
        init(EventPriority.LOWEST);
    }

    private void configureModules(Map<String, List<IGTERecipe>> modules) {
        Locale locale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);
        Set<ResourceLocation> toLoad = new LinkedHashSet<>();
        Set<IGTERecipe> modulesToLoad = new LinkedHashSet<>();
        for (IModuleContainer container : containers.values()) {
            String containerID = container.getID();
            List<IGTERecipe> containerModules = modules.get(containerID);
            if(containerModules == null) {
                logger.error("containerModules was null!");
                continue;
            }
            Iterator<IGTERecipe> iterator = containerModules.iterator();
            while (iterator.hasNext()) {
                IGTERecipe module = iterator.next();
                GTERecipe annotation = module.getClass().getAnnotation(GTERecipe.class);
                toLoad.add(new ResourceLocation(containerID, annotation.moduleID()));
                modulesToLoad.add(module);
            }
        }

        // Check any module dependencies
        Iterator<IGTERecipe> iterator;
        boolean changed;
        do {
            changed = false;
            iterator = modulesToLoad.iterator();
            while (iterator.hasNext()) {
                IGTERecipe module = iterator.next();

                // Check module dependencies
                Set<ResourceLocation> dependencies = module.getDependencyUids();
                if (!toLoad.containsAll(dependencies)) {
                    iterator.remove();
                    changed = true;
                    GTERecipe annotation = module.getClass().getAnnotation(GTERecipe.class);
                    String moduleID = annotation.moduleID();
                    toLoad.remove(new ResourceLocation(moduleID));
                    logger.info("Module {} is missing at least one of module dependencies: {}, skipping loading...",
                            moduleID, dependencies);
                }
            }
        } while (changed);

        // Sort modules by their module dependencies
        do {
            changed = false;
            iterator = modulesToLoad.iterator();
            while (iterator.hasNext()) {
                IGTERecipe module = iterator.next();
                if (sortedModules.keySet().containsAll(module.getDependencyUids())) {
                    iterator.remove();
                    GTERecipe annotation = module.getClass().getAnnotation(GTERecipe.class);
                    sortedModules.put(new ResourceLocation(annotation.containerID(), annotation.moduleID()), module);
                    changed = true;
                    break;
                }
            }
        } while (changed);

        loadedRecipes.addAll(sortedModules.values());

        for (IGTERecipe recipe : loadedRecipes) {
            GTERecipe annotation = recipe.getClass().getAnnotation(GTERecipe.class);
            List<IGTERecipe> recipes = recipePriorities.getOrDefault(annotation.priority(), new ArrayList<>());
            recipes.add(recipe);
            recipePriorities.put(annotation.priority(), recipes);
        }

        Locale.setDefault(locale);
    }

    private void discoverContainers(ASMDataTable table) {
        Set<ASMDataTable.ASMData> dataSet = table.getAll(ModuleContainer.class.getCanonicalName());
        for (ASMDataTable.ASMData data : dataSet) {
            try {
                Class<?> clazz = Class.forName(data.getClassName());
                registerContainer((IModuleContainer) clazz.newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                logger.error("Could not initialize module container " + data.getClassName(), e);
            }
        }
    }

    private Map<String, List<IGTERecipe>> getModules(ASMDataTable table) {
        List<IGTERecipe> instances = getInstances(table);
        Map<String, List<IGTERecipe>> modules = new LinkedHashMap<>();
        for (IGTERecipe recipe : instances) {
            GTERecipe info = recipe.getClass().getAnnotation(GTERecipe.class);
            modules.computeIfAbsent(info.containerID(), k -> new ArrayList<>()).add(recipe);
        }
        return modules;
    }

    @SuppressWarnings("unchecked")
    private List<IGTERecipe> getInstances(ASMDataTable table) {
        Set<ASMDataTable.ASMData> dataSet = table.getAll(GTERecipe.class.getCanonicalName());
        List<IGTERecipe> instances = new ArrayList<>();
        for (ASMDataTable.ASMData data : dataSet) {
            String moduleID = (String) data.getAnnotationInfo().get("moduleID");
            List<String> modDependencies = (ArrayList<String>) data.getAnnotationInfo().get("modDependencies");
            if (modDependencies == null || modDependencies.stream().allMatch(Loader::isModLoaded)) {
                try {
                    Class<?> clazz = Class.forName(data.getClassName());
                    instances.add((IGTERecipe) clazz.newInstance());
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    logger.error("Could not initialize module " + moduleID, e);
                }
            } else {
                logger.info("Module {} is missing at least one of mod dependencies; {}, skipping loading...", moduleID,
                        modDependencies);
            }
        }
        return instances.stream().sorted((m1, m2) -> {
            GTERecipe m1a = m1.getClass().getAnnotation(GTERecipe.class);
            GTERecipe m2a = m2.getClass().getAnnotation(GTERecipe.class);
            return (m1a.containerID() + ":" + m1a.moduleID()).compareTo(m2a.containerID() + ":" + m2a.moduleID());
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
