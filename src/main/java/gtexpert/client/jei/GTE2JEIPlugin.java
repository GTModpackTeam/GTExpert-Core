package gtexpert.client.jei;

import gregtech.api.GTValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import org.jetbrains.annotations.NotNull;

@JEIPlugin
public class GTE2JEIPlugin implements IModPlugin {

    @Override
    public void register(@NotNull IModRegistry registry) {
        registry.addRecipeCatalyst(GTEMetaTileEntities.DRACONIUM_FUSION.getStackForm(), GTValues.MODID + ":" + GTERecipeMaps.DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
        registry.addRecipeCatalyst(GTEMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), GTValues.MODID + ":" + GTERecipeMaps.AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
        registry.addRecipeCatalyst(GTEMetaTileEntities.DRACONIUM_FUSION.getStackForm(), GTValues.MODID + ":" + GTERecipeMaps.DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
        registry.addRecipeCatalyst(GTEMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), GTValues.MODID + ":" + GTERecipeMaps.AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
    }
}
