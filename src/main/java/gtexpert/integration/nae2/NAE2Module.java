package gtexpert.integration.nae2;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.nae2.recipes.NAE2BlocksRecipe;
import gtexpert.integration.nae2.recipes.NAE2ItemsRecipe;
import gtexpert.modules.GTEModules;

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
