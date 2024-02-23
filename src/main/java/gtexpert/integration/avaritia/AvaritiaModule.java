package gtexpert.integration.avaritia;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.avaritia.recipes.AvaritiaMaterialsRecipe;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_AVARITIA,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.AVARITIA,
           name = "GTExpert Avaritia Integration",
           description = "Avaritia Integration Module")
public class AvaritiaModule extends GTEIntegrationSubmodule {

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        AvaritiaMaterialsRecipe.init();
    }
}
