package com.github.gtexpert.core.integration.aeaditions;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.aeaditions.recipes.AEAItemsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_AEA,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.APPLIED_ENERGISTICS2, Mods.Names.AE_ADDITIONS },
           name = "GTExpert AE Additions Integration",
           description = "AE Additions Integration Module")
public class AEAModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        AEAItemsRecipe.init();
    }
}
