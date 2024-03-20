package gtexpert.integration.additions;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.additions.recipes.AEAItemsRecipe;
import gtexpert.modules.GTEModules;

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
