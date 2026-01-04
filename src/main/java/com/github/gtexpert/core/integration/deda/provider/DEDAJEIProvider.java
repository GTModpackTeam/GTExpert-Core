package com.github.gtexpert.core.integration.deda.provider;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.integration.deda.metatileentities.DEDAMetaTileEntities;
import com.github.gtexpert.core.integration.deda.recipemaps.GTEDraconicRecipeMaps;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@SuppressWarnings("unused")
@JEIPlugin
public class DEDAJEIProvider implements IModPlugin {

    @Override
    public void register(@NotNull IModRegistry registry) {
        if (GTEValues.isModLoadedDEDA()) {
            String fusionCategory = GTValues.MODID + ":" +
                    GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.unlocalizedName;
            String tierUpCategory = GTValues.MODID + ":" +
                    GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_RECIPES.unlocalizedName;
            String upgradeCategory = GTValues.MODID + ":" +
                    GTEDraconicRecipeMaps.DRACONIC_FUSION_UPGRADE_RECIPES.unlocalizedName;

            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), fusionCategory);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.DRACONIUM_FUSION.getStackForm(), tierUpCategory);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), tierUpCategory);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.DRACONIUM_FUSION.getStackForm(), upgradeCategory);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), upgradeCategory);
        }
    }
}
