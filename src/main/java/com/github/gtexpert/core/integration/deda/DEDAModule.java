package com.github.gtexpert.core.integration.deda;

import net.minecraft.block.Block;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.deda.loaders.DEDAMaterialInfoLoader;
import com.github.gtexpert.core.integration.deda.loaders.DEDAOreDictionaryLoader;
import com.github.gtexpert.core.integration.deda.metatileentities.DEDAMetaTileEntities;
import com.github.gtexpert.core.integration.deda.recipes.*;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_DEDA,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.DRACONIC_EVOLUTION, Mods.Names.DRACONIC_ADDITIONS },
           name = "GTExpert Draconic Evolution & Draconic Additions Integration",
           description = "Draconic Evolution & Draconic Additions Integration Module")
public class DEDAModule extends GTEIntegrationSubmodule {

    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        DEDAMetaTileEntities.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        DraconicMaterialsRecipe.remove();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        DEDAMaterialInfoLoader.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        DEDAOreDictionaryLoader.init();

        // Draconic recipes
        DraconicFluidRecipe.init();
        DraconicMaterialsRecipe.init();
        DraconicItemsRecipe.init();
        DraconicBlocksRecipe.init();
        DraconicToolsRecipe.init();

        // Draconic upgrade recipes
        DraconicTierupRecipe.init();
        DraconicUpgradeRecipe.init();

        if (!Mods.ImplosionNoBomb.isModLoaded()) return;
        DraconicImplosionMaterialsRecipe.init();
    }
}
