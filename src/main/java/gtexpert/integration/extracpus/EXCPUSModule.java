package gtexpert.integration.extracpus;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.extracpus.recipes.EXCPUSBlocksRecipe;
import gtexpert.integration.extracpus.recipes.EXCPUSItemsRecipe;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_EXCPUS,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.APPLIED_ENERGISTICS2, Mods.Names.AE_ADDITIONS, Mods.Names.EXTRA_CPUS },
           name = "GTExpert Extra CPUs Integration",
           description = "Extra CPUs Integration Module")
public class EXCPUSModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        EXCPUSItemsRecipe.init();
        EXCPUSBlocksRecipe.init();
    }
}
