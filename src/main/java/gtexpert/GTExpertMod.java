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
import gtexpert.common.items.GTECoverBehaviors;
import gtexpert.modules.GTEModuleManager;
import gtexpert.modules.GTEModules;

@Mod(modid = GTEValues.MODID,
     name = GTEValues.MODNAME,
     version = Tags.VERSION,
     dependencies = "required-after:mixinbooter;" +
             GTInternalTags.DEP_VERSION_STRING + "required-after:" + GTEValues.MODID_GCYM + ";" +
             "after:" + GTEValues.MODID_GTFO + ";" + "after:" + GTEValues.MODID_AE + ";" +
             "after:" + GTEValues.MODID_AEA + ";" + "after:" + GTEValues.MODID_AEFC + ";" +
             "after:" + GTEValues.MODID_EXCPU + ";" + "after:" + GTEValues.MODID_ECO + ";" +
             "after:" + GTEValues.MODID_EIO + ";" + "after:" + GTEValues.MODID_EIOE + ";" +
             "after:" + GTEValues.MODID_EIOM + ";" + "after:" + GTEValues.MODID_EIOC + ";" +
             "after:" + GTEValues.MODID_EIOCA + ";" + "after:" + GTEValues.MODID_DE + ";" +
             "after:" + GTEValues.MODID_DA + ";" + "after:" + GTEValues.MODID_CHISEL + ";" +
             "after:" + GTEValues.MODID_AVARITIA + ";" + "after:" + GTEValues.MODID_FFM + ";")
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
