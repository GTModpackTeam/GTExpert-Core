package gtexpert.integration.binnies.extratrees;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.binnies.extratrees.recipes.*;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_EXTREES,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.FORESTRY, Mods.Names.EXTRA_TREES },
           name = "GTExpert Extra Trees(Binnie's Mods) Integration",
           description = "Extra Trees(Binnie's Mods) Integration Module")
public class ExtraTreesModule extends GTEIntegrationSubmodule {

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ExtraTreesItemsRecipe.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {}
}
