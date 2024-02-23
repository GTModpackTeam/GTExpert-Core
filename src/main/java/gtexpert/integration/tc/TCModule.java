package gtexpert.integration.tc;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.tc.recipes.TCBlocksRecipe;
import gtexpert.integration.tc.recipes.TCItemsRecipe;
import gtexpert.integration.tc.recipes.TCMaterialsRecipe;
import gtexpert.integration.tc.recipes.TCToolsRecipe;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_TC,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.THAUMCRAFT,
           name = "GTExpert Thaumcraft Integration",
           description = "Thaumcraft Integration Module")
public class TCModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        TCMaterialsRecipe.init();
        TCItemsRecipe.init();
        TCBlocksRecipe.init();
        TCToolsRecipe.init();
    }
}
