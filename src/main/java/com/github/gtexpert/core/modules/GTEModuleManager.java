package com.github.gtexpert.core.modules;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.*;

public class GTEModuleManager implements IModuleManager {

    private static final GTEModuleManager INSTANCE = new GTEModuleManager();
    private static final String MODULE_CFG_FILE_NAME = "modules.cfg";
    private static final String MODULE_CFG_CATEGORY_NAME = "modules";
    private static File configFolder;

    private Map<String, IModuleContainer> containers = new LinkedHashMap<>();
    private final Map<ResourceLocation, IGTEModule> sortedModules = new LinkedHashMap<>();
    private final Set<IGTEModule> loadedModules = new LinkedHashSet<>();

    private IModuleContainer currentContainer;

    private ModuleStage currentStage = ModuleStage.C_SETUP;
    private final Logger logger = LogManager.getLogger("GTExpert Module Loader");
    private Configuration config;

    private GTEModuleManager() {}

    public static GTEModuleManager getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isModuleEnabled(ResourceLocation id) {
        return sortedModules.containsKey(id);
    }

    public boolean isModuleEnabled(IGTEModule module) {
        GTEModule annotation = module.getClass().getAnnotation(GTEModule.class);
        String comment = getComment(module);
        Property prop = getConfiguration().get(MODULE_CFG_CATEGORY_NAME,
                annotation.containerID() + ":" + annotation.moduleID(), true, comment);
        return prop.getBoolean();
    }

    @Override
    public IModuleContainer getLoadedContainer() {
        return currentContainer;
    }

    @Override
    public ModuleStage getStage() {
        return currentStage;
    }

    @Override
    public boolean hasPassedStage(ModuleStage stage) {
        return currentStage.ordinal() > stage.ordinal();
    }

    @Override
    public void registerContainer(IModuleContainer container) {
        if (currentStage != ModuleStage.C_SETUP) {
            logger.error("Failed to register module container {}, as module loading has already begun", container);
            return;
        }
        Preconditions.checkNotNull(container);
        containers.put(container.getID(), container);
    }

    public void setup(ASMDataTable asmDataTable, File configDirectory) {
        // find and register all containers registered with the @ModuleContainer annotation, then sort them by container
        // name
        discoverContainers(asmDataTable);
        containers = containers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        currentStage = ModuleStage.M_SETUP;
        configFolder = new File(configDirectory, GTEValues.MODID);
        Map<String, List<IGTEModule>> modules = getModules(asmDataTable);
        configureModules(modules);

        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Registering event handlers");
            for (Class<?> clazz : module.getEventBusSubscribers()) {
                MinecraftForge.EVENT_BUS.register(clazz);
            }
        }
    }

    public void onConstruction(FMLConstructionEvent event) {
        currentStage = ModuleStage.CONSTRUCTION;
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Construction start");
            module.construction(event);
            module.getLogger().debug("Construction complete");
        }
    }

    public void onPreInit(FMLPreInitializationEvent event) {
        currentStage = ModuleStage.PRE_INIT;
        // Separate loops for strict ordering
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Registering packets");
            module.registerPackets();
        }
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Pre-init start");
            module.preInit(event);
            module.getLogger().debug("Pre-init complete");
        }
    }

    public void onInit(FMLInitializationEvent event) {
        currentStage = ModuleStage.INIT;
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Init start");
            module.init(event);
            module.getLogger().debug("Init complete");
        }
    }

    public void onPostInit(FMLPostInitializationEvent event) {
        currentStage = ModuleStage.POST_INIT;
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Post-init start");
            module.postInit(event);
            module.getLogger().debug("Post-init complete");
        }
    }

    public void onLoadComplete(FMLLoadCompleteEvent event) {
        currentStage = ModuleStage.FINISHED;
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Load-complete start");
            module.loadComplete(event);
            module.getLogger().debug("Load-complete complete");
        }
    }

    public void onServerAboutToStart(FMLServerAboutToStartEvent event) {
        currentStage = ModuleStage.SERVER_ABOUT_TO_START;
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Server-about-to-start start");
            module.serverAboutToStart(event);
            module.getLogger().debug("Server-about-to-start complete");
        }
    }

    public void onServerStarting(FMLServerStartingEvent event) {
        currentStage = ModuleStage.SERVER_STARTING;
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Server-starting start");
            module.serverStarting(event);
            module.getLogger().debug("Server-starting complete");
        }
    }

    public void onServerStarted(FMLServerStartedEvent event) {
        currentStage = ModuleStage.SERVER_STARTED;
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.getLogger().debug("Server-started start");
            module.serverStarted(event);
            module.getLogger().debug("Server-started complete");
        }
    }

    public void onServerStopping(FMLServerStoppingEvent event) {
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.serverStopping(event);
        }
    }

    public void onServerStopped(FMLServerStoppedEvent event) {
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.serverStopped(event);
        }
    }

    public void processIMC(ImmutableList<FMLInterModComms.IMCMessage> messages) {
        for (FMLInterModComms.IMCMessage message : messages) {
            for (IGTEModule module : loadedModules) {
                if (module.processIMC(message)) {
                    break;
                }
            }
        }
    }

    public void registerBlocks(RegistryEvent.Register<Block> event) {
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.registerBlocks(event);
        }
    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.registerItems(event);
        }
    }

    public void registerRecipesHighest(RegistryEvent.Register<IRecipe> event) {
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.registerRecipesHighest(event);
        }
    }

    public void registerRecipesHigh(RegistryEvent.Register<IRecipe> event) {
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.registerRecipesHigh(event);
        }
    }

    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.registerRecipesNormal(event);
        }
    }

    public void registerRecipesLow(RegistryEvent.Register<IRecipe> event) {
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.registerRecipesLow(event);
        }
    }

    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        for (IGTEModule module : loadedModules) {
            currentContainer = containers.get(getContainerID(module));
            module.registerRecipesLowest(event);
        }
    }

    private void configureModules(Map<String, List<IGTEModule>> modules) {
        Locale locale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);
        Set<ResourceLocation> toLoad = new LinkedHashSet<>();
        Set<IGTEModule> modulesToLoad = new LinkedHashSet<>();
        Configuration config = getConfiguration();
        config.load();
        config.addCustomCategoryComment(MODULE_CFG_CATEGORY_NAME,
                "Module configuration file. Can individually enable/disable modules from GTExpert and its addons");

        for (IModuleContainer container : containers.values()) {
            String containerID = container.getID();
            List<IGTEModule> containerModules = modules.get(containerID);
            IGTEModule coreModule = getCoreModule(containerModules);
            if (coreModule == null) {
                throw new IllegalStateException("Could not find core module for module container " + containerID);
            } else {
                containerModules.remove(coreModule);
                containerModules.add(0, coreModule);
            }

            logger.debug("containterModule size: " + containerModules.size());

            // Remove disabled modules and gather potential modules to load
            Iterator<IGTEModule> iterator = containerModules.iterator();
            while (iterator.hasNext()) {
                IGTEModule module = iterator.next();
                if (!isModuleEnabled(module)) {
                    iterator.remove();
                    logger.debug("Module disabled: {}", module);
                    continue;
                }
                GTEModule annotation = module.getClass().getAnnotation(GTEModule.class);
                toLoad.add(new ResourceLocation(containerID, annotation.moduleID()));
                modulesToLoad.add(module);
            }
        }

        // Check any module dependencies
        Iterator<IGTEModule> iterator;
        boolean changed;
        do {
            changed = false;
            iterator = modulesToLoad.iterator();
            while (iterator.hasNext()) {
                IGTEModule module = iterator.next();

                // Check module dependencies
                Set<ResourceLocation> dependencies = module.getDependencyUids();
                if (!toLoad.containsAll(dependencies)) {
                    iterator.remove();
                    changed = true;
                    GTEModule annotation = module.getClass().getAnnotation(GTEModule.class);
                    String moduleID = annotation.moduleID();
                    toLoad.remove(new ResourceLocation(moduleID));
                    logger.debug("Module {} is missing at least one of module dependencies: {}, skipping loading...",
                            moduleID, dependencies);
                }
            }
        } while (changed);

        // Sort modules by their module dependencies
        do {
            changed = false;
            iterator = modulesToLoad.iterator();
            while (iterator.hasNext()) {
                IGTEModule module = iterator.next();
                if (sortedModules.keySet().containsAll(module.getDependencyUids())) {
                    iterator.remove();
                    GTEModule annotation = module.getClass().getAnnotation(GTEModule.class);
                    sortedModules.put(new ResourceLocation(annotation.containerID(), annotation.moduleID()), module);
                    changed = true;
                    break;
                }
            }
        } while (changed);

        loadedModules.addAll(sortedModules.values());

        if (config.hasChanged()) {
            config.save();
        }
        Locale.setDefault(locale);
    }

    private static IGTEModule getCoreModule(List<IGTEModule> modules) {
        for (IGTEModule module : modules) {
            GTEModule annotation = module.getClass().getAnnotation(GTEModule.class);
            if (annotation.coreModule()) {
                return module;
            }
        }
        return null;
    }

    private static String getContainerID(IGTEModule module) {
        GTEModule annotation = module.getClass().getAnnotation(GTEModule.class);
        return annotation.containerID();
    }

    private Map<String, List<IGTEModule>> getModules(ASMDataTable table) {
        List<IGTEModule> instances = getInstances(table);
        Map<String, List<IGTEModule>> modules = new LinkedHashMap<>();
        for (IGTEModule module : instances) {
            GTEModule info = module.getClass().getAnnotation(GTEModule.class);
            modules.computeIfAbsent(info.containerID(), k -> new ArrayList<>()).add(module);
        }
        return modules;
    }

    @SuppressWarnings("unchecked")
    private List<IGTEModule> getInstances(ASMDataTable table) {
        Set<ASMDataTable.ASMData> dataSet = table.getAll(GTEModule.class.getCanonicalName());
        List<IGTEModule> instances = new ArrayList<>();
        for (ASMDataTable.ASMData data : dataSet) {
            String moduleID = (String) data.getAnnotationInfo().get("moduleID");
            List<String> modDependencies = (ArrayList<String>) data.getAnnotationInfo().get("modDependencies");
            if (modDependencies == null || modDependencies.stream().allMatch(Loader::isModLoaded)) {
                try {
                    Class<?> clazz = Class.forName(data.getClassName());
                    instances.add((IGTEModule) clazz.newInstance());
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    logger.error("Could not initialize module " + moduleID, e);
                }
            } else {
                logger.debug("Module {} is missing at least one of mod dependencies: {}, skipping loading...", moduleID,
                        modDependencies);
            }
        }
        return instances.stream().sorted((m1, m2) -> {
            GTEModule m1a = m1.getClass().getAnnotation(GTEModule.class);
            GTEModule m2a = m2.getClass().getAnnotation(GTEModule.class);
            return (m1a.containerID() + ":" + m1a.moduleID()).compareTo(m2a.containerID() + ":" + m2a.moduleID());
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    private void discoverContainers(ASMDataTable table) {
        Set<ASMDataTable.ASMData> dataSet = table.getAll(ModuleContainer.class.getCanonicalName());
        for (ASMDataTable.ASMData data : dataSet) {
            try {
                Class<?> clazz = Class.forName(data.getClassName());
                registerContainer((IModuleContainer) clazz.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                logger.error("Could not initialize module container " + data.getClassName(), e);
            }
        }
    }

    private String getComment(IGTEModule module) {
        GTEModule annotation = module.getClass().getAnnotation(GTEModule.class);

        String comment = annotation.description();
        Set<ResourceLocation> dependencies = module.getDependencyUids();
        if (!dependencies.isEmpty()) {
            Iterator<ResourceLocation> iterator = dependencies.iterator();
            StringBuilder builder = new StringBuilder(comment);
            builder.append("\n");
            builder.append("Module Dependencies: [ ");
            builder.append(iterator.next());
            while (iterator.hasNext()) {
                builder.append(", ").append(iterator.next());
            }
            builder.append(" ]");
            comment = builder.toString();
        }
        String[] modDependencies = annotation.modDependencies();
        if (modDependencies != null && modDependencies.length > 0) {
            Iterator<String> iterator = Arrays.stream(modDependencies).iterator();
            StringBuilder builder = new StringBuilder(comment);
            builder.append("\n");
            builder.append("Mod Dependencies: [ ");
            builder.append(iterator.next());
            while (iterator.hasNext()) {
                builder.append(", ").append(iterator.next());
            }
            builder.append(" ]");
            comment = builder.toString();
        }
        return comment;
    }

    private Configuration getConfiguration() {
        if (config == null) {
            config = new Configuration(new File(configFolder, MODULE_CFG_FILE_NAME));
        }
        return config;
    }
}
