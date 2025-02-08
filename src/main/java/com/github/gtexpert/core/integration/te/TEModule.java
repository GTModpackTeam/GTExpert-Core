package com.github.gtexpert.core.integration.te;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_TE,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.THAUMIC_ENERGISTICS,
           name = "GTExpert ThaumicEnergistics Integration",
           description = "ThaumicEnergistics Integration Module")
public class TEModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {}
}
