package com.github.gtexpert.core.integration.tc;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.tc.loaders.TCOreDictionaryLoader;
import com.github.gtexpert.core.integration.tc.recipes.TCBlocksRecipe;
import com.github.gtexpert.core.integration.tc.recipes.TCItemsRecipe;
import com.github.gtexpert.core.integration.tc.recipes.TCMaterialsRecipe;
import com.github.gtexpert.core.integration.tc.recipes.TCToolsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_TC,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.THAUMCRAFT,
           name = "GTExpert Thaumcraft Integration",
           description = "Thaumcraft Integration Module")
public class TCModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        TCOreDictionaryLoader.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        TCMaterialsRecipe.remove();

        TCMaterialsRecipe.init();
        TCItemsRecipe.init();
        TCBlocksRecipe.init();
        TCToolsRecipe.init();
    }
}
