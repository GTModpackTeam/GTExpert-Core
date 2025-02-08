package com.github.gtexpert.core.integration.gtfo;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.gtfo.recipes.*;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_GTFO,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.GREGTECH_FOOD_OPTION,
           name = "GTExpert GregTech Food Option Integration",
           description = "GregTech Food Option Integration Module")
public class GTFOModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        GTFOItemsRecipe.init();
        GTFOChemicalRecipe.init();
    }
}
