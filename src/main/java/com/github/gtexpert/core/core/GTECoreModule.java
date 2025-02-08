package com.github.gtexpert.core.core;

import static com.github.gtexpert.core.GTExpertMod.createItemBlock;
import static com.github.gtexpert.core.common.blocks.GTEMetaBlocks.*;
import static gregtech.api.GregTechAPI.HEATING_COILS;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import gregtech.api.block.VariantItemBlock;
import gregtech.loaders.recipe.RecyclingRecipes;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.modules.IGTEModule;
import com.github.gtexpert.core.common.CommonProxy;
import com.github.gtexpert.core.common.blocks.GTEBlockWireCoil;
import com.github.gtexpert.core.common.blocks.GTEMetaBlocks;
import com.github.gtexpert.core.common.items.GTEMetaItems;
import com.github.gtexpert.core.common.metatileentities.GTEMetaTileEntities;
import com.github.gtexpert.core.loaders.GTEOreDictionaryLoader;
import com.github.gtexpert.core.loaders.recipe.CEUOverrideRecipe;
import com.github.gtexpert.core.loaders.recipe.GTERecipe;
import com.github.gtexpert.core.loaders.recipe.GTEVanillaOverrideRecipes;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_CORE,
           containerID = GTEValues.MODID,
           name = "GTExpert Core",
           description = "Core GTExpert content.",
           coreModule = true)
public class GTECoreModule implements IGTEModule {

    public static final Logger logger = LogManager.getLogger("GTExpert Core");

    @SidedProxy(modId = GTEValues.MODID,
                clientSide = "com.github.gtexpert.core.client.ClientProxy",
                serverSide = "com.github.gtexpert.core.common.CommonProxy")
    public static CommonProxy proxy;

    @Override
    public @NotNull Logger getLogger() {
        return logger;
    }

    @Override
    public void construction(FMLConstructionEvent event) {}

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

        GTEMetaBlocks.init();
        GTEMetaItems.init();

        /* Start API Block Registration */
        for (GTEBlockWireCoil.GTECoilType type : GTEBlockWireCoil.GTECoilType.values()) {
            HEATING_COILS.put(GTE_WIRE_COIL.getState(type), type);
        }
        /* End API Block Registration */
    }

    @Override
    public void init(FMLInitializationEvent event) {}

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        RecyclingRecipes.init();
    }

    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(GTE_WIRE_COIL);
        registry.register(GTE_METAL_CASING);
    }

    @Override
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(GTE_WIRE_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(GTE_METAL_CASING, VariantItemBlock::new));
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        GTEOreDictionaryLoader.init();
        GTEMetaTileEntities.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        GTERecipe.init();
        CEUOverrideRecipe.init();
        GTEVanillaOverrideRecipes.init();
    }
}
