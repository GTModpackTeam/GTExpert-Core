package gtexpert.integration.te;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_TE,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.THAUMIC_ENERGISTICS,
           name = "GTExpert ThaumicEnergistics Integration",
           description = "ThaumicEnergistics Integration Module")
public class TEModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {}
}
