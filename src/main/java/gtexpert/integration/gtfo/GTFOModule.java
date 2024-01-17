package gtexpert.integration.gtfo;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.gtfo.recipes.GTFOChemicalRecipe;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_GTFO,
           containerID = GTEValues.MODID,
           modDependencies = GTEValues.MODID_GTFO,
           name = "GTExpert GregTech Food Option Module")
public class GTFOModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        GTFOChemicalRecipe.init();
    }
}
