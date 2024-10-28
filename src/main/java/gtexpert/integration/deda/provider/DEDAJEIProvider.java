package gtexpert.integration.deda.provider;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;

import gtexpert.api.GTEValues;
import gtexpert.integration.deda.metatileentities.DEDAMetaTileEntities;
import gtexpert.integration.deda.recipemaps.GTEDraconicRecipeMaps;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@SuppressWarnings("unused")
@JEIPlugin
public class DEDAJEIProvider implements IModPlugin {

    @Override
    public void register(@NotNull IModRegistry registry) {
        if (GTEValues.isModLoadedDEDA()) {
            registry.addRecipeCatalyst(DEDAMetaTileEntities.DRACONIUM_FUSION.getStackForm(),
                    GTValues.MODID + ":" + GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), GTValues.MODID +
                    ":" + GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.DRACONIUM_FUSION.getStackForm(),
                    GTValues.MODID + ":" + GTEDraconicRecipeMaps.DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), GTValues.MODID +
                    ":" + GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
        }
    }
}
