package com.github.gtexpert.core.integration.binnies.extrabees;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.binnies.extrabees.recipes.ExtraBeesItemsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_EXBEES,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.FORESTRY, Mods.Names.EXTRA_BEES },
           name = "GTExpert Extra Bees(Binnie's Mods) Integration",
           description = "Extra Bees(Binnie's Mods) Integration Module")
public class ExtraBeesModule extends GTEIntegrationSubmodule {

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ExtraBeesItemsRecipe.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {}
}
