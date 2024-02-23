package gtexpert;

import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gregtech.GTInternalTags;
import gregtech.api.GregTechAPI;
import gregtech.api.cover.CoverDefinition;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTELog;
import gtexpert.api.util.Mods;
import gtexpert.common.items.GTECoverBehaviors;
import gtexpert.modules.GTEModuleManager;
import gtexpert.modules.GTEModules;

@Mod(modid = GTEValues.MODID,
     name = GTEValues.MODNAME,
     acceptedMinecraftVersions = "[1.12.2,1.13)",
     version = Tags.VERSION,
     dependencies = "required-after:mixinbooter@[8.0,);" +
             GTInternalTags.DEP_VERSION_STRING + "required-after:" + Mods.Names.GREGICALITY_MULTIBLOCKS + ";" +
             "after:" + Mods.Names.GREGTECH_FOOD_OPTION + ";" + "after:" + Mods.Names.APPLIED_ENERGISTICS2 + ";" +
             "after:" + Mods.Names.AE_ADDITIONS + ";" + "after:" + Mods.Names.AE2_FLUID_CRAFTING + ";" +
             "after:" + Mods.Names.NEEVES_AE2 + ";" + "after:" + Mods.Names.EXTRA_CPUS + ";" +
             "after:" + Mods.Names.ENDER_CORE + ";" + "after:" + Mods.Names.ENDER_IO + ";" +
             "after:" + Mods.Names.ENDER_ENDERGY + ";" + "after:" + Mods.Names.ENDER_MACHINES + ";" +
             "after:" + Mods.Names.ENDER_CONDUITS + ";" + "after:" + Mods.Names.ENDER_AE2_CONDUITS + ";" +
             "after:" + Mods.Names.DRACONIC_EVOLUTION + ";" + "after:" + Mods.Names.DRACONIC_ADDITIONS + ";" +
             "after:" + Mods.Names.CHISEL + ";" + "after:" + Mods.Names.AVARITIA + ";" +
             "after:" + Mods.Names.FORESTRY + ";")
@Mod.EventBusSubscriber(modid = GTEValues.MODID)
public class GTExpertMod {

    private GTEModuleManager moduleManager;

    @Mod.EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        GTELog.logger.info("starting construction event...");
        moduleManager = GTEModuleManager.getInstance();
        moduleManager.registerContainer(new GTEModules());
        moduleManager.setup(event.getASMHarvestedData(), Loader.instance().getConfigDir());
        moduleManager.onConstruction(event);
        GTELog.logger.info("finished construction!");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        moduleManager.onPreInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        moduleManager.onInit(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        moduleManager.onPostInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        moduleManager.onLoadComplete(event);
    }

    @Mod.EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        moduleManager.onServerAboutToStart(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        moduleManager.onServerStarting(event);
    }

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        moduleManager.onServerStarted(event);
    }

    @Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        moduleManager.onServerStopping(event);
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        moduleManager.onServerStopped(event);
    }

    @Mod.EventHandler
    public void respondIMC(FMLInterModComms.IMCEvent event) {
        moduleManager.processIMC(event.getMessages());
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        GTELog.logger.info("Registering Blocks...");
        moduleManager.registerBlocks(event);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        GTELog.logger.info("Registering Items...");

        moduleManager.registerItems(event);
    }

    @SubscribeEvent
    public static void registerCovers(GregTechAPI.RegisterEvent<CoverDefinition> event) {
        GTELog.logger.info("Registering Covers...");
        GTECoverBehaviors.init();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void registerRecipesHighest(RegistryEvent.Register<IRecipe> event) {
        moduleManager.registerRecipesHighest(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void registerRecipesHigh(RegistryEvent.Register<IRecipe> event) {
        moduleManager.registerRecipesHigh(event);
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        moduleManager.registerRecipesNormal(event);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void registerRecipesLow(RegistryEvent.Register<IRecipe> event) {
        moduleManager.registerRecipesLow(event);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        moduleManager.registerRecipesLowest(event);
    }

    public static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        ResourceLocation registryName = block.getRegistryName();
        if (registryName == null) {
            throw new IllegalArgumentException("Block " + block.getTranslationKey() + " has no registry name.");
        }
        itemBlock.setRegistryName(registryName);
        return itemBlock;
    }

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(GTEValues.MODID)) {
            ConfigManager.sync(GTEValues.MODID, Config.Type.INSTANCE);
        }
    }
}
