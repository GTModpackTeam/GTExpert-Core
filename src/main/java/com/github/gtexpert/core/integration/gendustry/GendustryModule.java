package com.github.gtexpert.core.integration.gendustry;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.gendustry.metatileentities.GendustryMetaTileEntities;
import com.github.gtexpert.core.integration.gendustry.recipes.GendustryBlocksRecipe;
import com.github.gtexpert.core.integration.gendustry.recipes.GendustryItemsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_GENDUSTRY,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.FORESTRY, Mods.Names.GENDUSTRY },
           name = "GTExpert Gendustry For Minecraft Integration",
           description = "Gendustry Integration Module")
public class GendustryModule extends GTEIntegrationSubmodule {

    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        GendustryMetaTileEntities.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        GendustryItemsRecipe.init();
        GendustryBlocksRecipe.init();
    }
}
