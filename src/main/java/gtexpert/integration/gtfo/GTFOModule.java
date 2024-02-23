package gtexpert.integration.gtfo;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.gtfo.recipes.GTFOChemicalRecipe;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_GTFO,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.GREGTECH_FOOD_OPTION,
           name = "GTExpert GregTech Food Option Integration",
           description = "GregTech Food Option Integration Module")
public class GTFOModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        GTFOChemicalRecipe.init();
    }
}
