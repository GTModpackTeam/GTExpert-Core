package com.github.gtexpert.core.integration.binnies.extratrees;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.binnies.extratrees.recipes.*;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_EXTREES,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.FORESTRY, Mods.Names.EXTRA_TREES },
           name = "GTExpert Extra Trees(Binnie's Mods) Integration",
           description = "Extra Trees(Binnie's Mods) Integration Module")
public class ExtraTreesModule extends GTEIntegrationSubmodule {

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ExtraTreesItemsRecipe.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {}
}
