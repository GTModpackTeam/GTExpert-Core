package com.github.gtexpert.core.integration.chisel;

import net.minecraft.block.Block;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.chisel.loaders.ChiselOreDictionaryLoader;
import com.github.gtexpert.core.integration.chisel.metatileentities.ChiselMetaTileEntities;
import com.github.gtexpert.core.integration.chisel.recipes.ChiselBlocksRecipe;
import com.github.gtexpert.core.integration.chisel.recipes.ChiselToolsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_CHISEL,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.CHISEL,
           name = "GTExpert Chisel Integration",
           description = "Chisel Integration Module")
public class ChiselModule extends GTEIntegrationSubmodule {

    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        ChiselMetaTileEntities.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        ChiselOreDictionaryLoader.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        ChiselBlocksRecipe.init();
        ChiselToolsRecipe.init();
    }
}
