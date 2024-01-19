package gtexpert.integration.jei;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.deda.metatileentities.DEDAMetaTileEntities;
import gtexpert.integration.deda.recipemaps.GTEDraconicRecipeMaps;
import gtexpert.modules.GTEModules;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
@GTEModule(
           moduleID = GTEModules.MODULE_JEI,
           containerID = GTEValues.MODID,
           modDependencies = GTEValues.MODID_JEI,
           name = "GTExpert Just Enough Items Module")
public class JustEnoughItemsModule extends GTEIntegrationSubmodule implements IModPlugin {

    @Override
    public void register(@NotNull IModRegistry registry) {
        if (GTEValues.isModLoadedDEDA()) {
            registry.addRecipeCatalyst(DEDAMetaTileEntities.DRACONIUM_FUSION,
                    GTValues.MODID + ":" + GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION, GTValues.MODID +
                    ":" + GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.DRACONIUM_FUSION,
                    GTValues.MODID + ":" + GTEDraconicRecipeMaps.DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION, GTValues.MODID +
                    ":" + GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
        }
    }
}
