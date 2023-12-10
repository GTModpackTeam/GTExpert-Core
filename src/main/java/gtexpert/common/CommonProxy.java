package gtexpert.common;

import static gregtech.api.GregTechAPI.HEATING_COILS;
import static gtexpert.common.blocks.GTEMetaBlocks.*;

import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import gregtech.api.GregTechAPI;
import gregtech.api.block.VariantItemBlock;
import gregtech.api.cover.CoverDefinition;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTELog;
import gtexpert.common.blocks.GTEBlockWireCoil.GTECoilType;
import gtexpert.common.blocks.GTEMetaBlocks;
import gtexpert.common.items.*;
import gtexpert.integration.theoneprobe.TOPProviders;
import gtexpert.loaders.*;
import gtexpert.loaders.recipe.*;

@Mod.EventBusSubscriber(modid = GTEValues.MODID)
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        GTEMetaBlocks.init();
        GTEMetaItems.init();

        /* Start API Block Registration */
        for (GTECoilType type : GTECoilType.values()) {
            HEATING_COILS.put(GTE_WIRE_COIL.getState(type), type);
        }
        /* End API Block Registration */
    }

    public void init(FMLInitializationEvent event) {
        if (Loader.isModLoaded(GTEValues.MODID_TOP)) {
            TOPProviders.init();
        }
    }

    public void postInit(FMLPostInitializationEvent event) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        GTELog.logger.info("Registering Blocks...");
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(GTE_WIRE_COIL);
        registry.register(GTE_METAL_CASING);
        registry.register(BLOCK_SAWMILL_CONVEYOR);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        GTELog.logger.info("Registering Items...");
        IForgeRegistry<Item> registry = event.getRegistry();

        GTERecipeManager.preLoad();
        registry.register(createItemBlock(GTE_WIRE_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(GTE_METAL_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(BLOCK_SAWMILL_CONVEYOR, ItemBlock::new));
    }

    @SubscribeEvent
    public static void registerCovers(GregTechAPI.RegisterEvent<CoverDefinition> event) {
        GTELog.logger.info("Registering Covers...");
        GTECoverBehaviors.init();
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GTELog.logger.info("Registering ore dictionary...");
        GTEOreDictionaryLoader.init();
        GTEMaterialInfoLoader.init();

        GTELog.logger.info("Registering Recipes...");
        GTERecipeManager.load();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipesLow(RegistryEvent.Register<IRecipe> event) {
        GTELog.logger.info("Registering Recipes...");
        GTERecipeManager.loadLow();
    }

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(GTEValues.MODID)) {
            ConfigManager.sync(GTEValues.MODID, Config.Type.INSTANCE);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        GTELog.logger.info("Registering Recipes...");
        GTERecipeManager.loadLowest();
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        ResourceLocation registryName = block.getRegistryName();
        if (registryName == null) {
            throw new IllegalArgumentException("Block " + block.getTranslationKey() + " has no registry name.");
        }
        itemBlock.setRegistryName(registryName);
        return itemBlock;
    }

    public void onPreLoad() {}

    public void onLoad() {}

    public void onPostLoad() {}

    public void onLoadComplete(FMLLoadCompleteEvent event) {}
}
