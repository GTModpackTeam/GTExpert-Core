package com.github.gtexpert.core.integration.nae2;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.nae2.recipes.NAE2BlocksRecipe;
import com.github.gtexpert.core.integration.nae2.recipes.NAE2ItemsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_NAE2,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.APPLIED_ENERGISTICS2, Mods.Names.NEEVES_AE2 },
           name = "GTExpert Neeve's AE2 Integration",
           description = "Neeve's AE2 Integration Module")
public class NAE2Module extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        NAE2ItemsRecipe.init();
        NAE2BlocksRecipe.init();
    }
}
