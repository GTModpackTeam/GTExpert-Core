package gtexpert.integration.jei;

import static gtexpert.common.metatileentities.GTEMultiMetaTileEntities.AWAKENED_DRACONIUM_FUSION;
import static gtexpert.common.metatileentities.GTEMultiMetaTileEntities.DRACONIUM_FUSION;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.recipes.draconic.GTEDraconicRecipeMaps;
import gtexpert.integration.IntegrationModule;
import gtexpert.modules.GTEModules;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@GTEModule(
           moduleID = GTEModules.JEI_DEDA_INTEGRATION,
           containerID = GTEValues.MODID,
           modDependencies = { GTEValues.MODID_JEI, GTEValues.MODID_DE, GTEValues.MODID_DA },
           name = "GTExpert JEI Integration",
           coreModule = true)
@JEIPlugin
public class GTEJeiDEDAModule extends IntegrationModule implements IModPlugin {

    @Override
    public void register(@NotNull IModRegistry registry) {
        registry.addRecipeCatalyst(DRACONIUM_FUSION.getStackForm(),
                GTValues.MODID + ":" + GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
        registry.addRecipeCatalyst(AWAKENED_DRACONIUM_FUSION.getStackForm(), GTValues.MODID +
                ":" + GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
        registry.addRecipeCatalyst(DRACONIUM_FUSION.getStackForm(),
                GTValues.MODID + ":" + GTEDraconicRecipeMaps.DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
        registry.addRecipeCatalyst(AWAKENED_DRACONIUM_FUSION.getStackForm(), GTValues.MODID +
                ":" + GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
    }
}
