package gtexpert.integration.deda;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.deda.loaders.DEDAMaterialInfoLoader;
import gtexpert.integration.deda.loaders.DEDAOreDictionaryLoader;
import gtexpert.integration.deda.metatileentities.DEDAMetaTileEntities;
import gtexpert.integration.deda.recipes.*;
import gtexpert.integration.deda.recipes.DraconicTierupRecipe;
import gtexpert.integration.deda.recipes.DraconicUpgradeRecipe;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_DEDA,
           containerID = GTEValues.MODID,
           modDependencies = { GTEValues.MODID_DE, GTEValues.MODID_DA },
           name = "GTExpert Draconic Evolution & Draconic Additions Module")
public class DEDAModule extends GTEIntegrationSubmodule {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        DEDAMetaTileEntities.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        DEDAMaterialInfoLoader.init();
        DEDAOreDictionaryLoader.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        // Draconic recipes
        DraconicFluidRecipe.init();
        DraconicMaterialsRecipe.init();
        DraconicItemsRecipe.init();
        DraconicBlocksRecipe.init();
        DraconicToolsRecipe.init();

        // Draconic upgrade recipes
        DraconicTierupRecipe.init();
        DraconicUpgradeRecipe.init();
    }
}
