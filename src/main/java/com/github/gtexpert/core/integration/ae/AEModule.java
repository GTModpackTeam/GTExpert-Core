package com.github.gtexpert.core.integration.ae;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.ae.loaders.AEMaterialInfoLoader;
import com.github.gtexpert.core.integration.ae.loaders.AEOreDictionaryLoader;
import com.github.gtexpert.core.integration.ae.recipes.AEBlocksRecipe;
import com.github.gtexpert.core.integration.ae.recipes.AEItemsRecipe;
import com.github.gtexpert.core.integration.ae.recipes.AEMaterialsRecipe;
import com.github.gtexpert.core.integration.ae.recipes.AEToolsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_AE,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.APPLIED_ENERGISTICS2,
           name = "GTExpert Applied Energistics 2 Integration",
           description = "Applied Energistics 2 Integration Module")
public class AEModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        AEMaterialInfoLoader.init();
        AEOreDictionaryLoader.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        AEMaterialsRecipe.remove();

        AEMaterialsRecipe.init();
        AEItemsRecipe.init();
        AEBlocksRecipe.init();
        AEToolsRecipe.init();
    }
}
