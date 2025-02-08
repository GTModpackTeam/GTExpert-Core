package com.github.gtexpert.core.integration.avaritia;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.avaritia.recipes.AvaritiaMaterialsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_AVARITIA,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.AVARITIA,
           name = "GTExpert Avaritia Integration",
           description = "Avaritia Integration Module")
public class AvaritiaModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        AvaritiaMaterialsRecipe.remove();

        AvaritiaMaterialsRecipe.init();
    }
}
