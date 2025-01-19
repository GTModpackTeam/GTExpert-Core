package com.github.gtexpert.core.integration.binnies;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.binnies.recipes.BinniesItemsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_BINNIES,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.FORESTRY, Mods.Names.GENETICS, Mods.Names.BOTANY,
                   Mods.Names.EXTRA_BEES, Mods.Names.EXTRA_TREES },
           name = "GTExpert Binnie's Mods Integration",
           description = "Binnie's Mods Integration Module")
public class BinniesModule extends GTEIntegrationSubmodule {

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        BinniesItemsRecipe.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {}
}
