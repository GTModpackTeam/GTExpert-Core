package gtexpert.integration.ae;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.ae.recipes.AEBlocksRecipe;
import gtexpert.integration.ae.recipes.AEItemsRecipe;
import gtexpert.integration.ae.recipes.AEMaterialsRecipe;
import gtexpert.integration.ae.recipes.AEToolsRecipe;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_AE,
           containerID = GTEValues.MODID,
           modDependencies = GTEValues.MODID_AE,
           name = "GTExpert Applied Energistics 2 Module")
public class AEModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        AEMaterialsRecipe.init();
        AEItemsRecipe.init();
        AEBlocksRecipe.init();
        AEToolsRecipe.init();
    }
}
