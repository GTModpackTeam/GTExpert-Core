package gtexpert.integration.jei;

import gregtech.api.GTValues;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.draconic.GTEDraconicRecipeMaps;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

import javax.annotation.Nonnull;

import static gtexpert.common.metatileentities.GTEMultiMetaTileEntities.*;

@SuppressWarnings("unused")
@JEIPlugin
public class GTEJEIPlugin implements IModPlugin {

    @Override
    public void register(@Nonnull IModRegistry registry) {
        if (GTEValues.isModLoadedDEDA()) {
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
}
