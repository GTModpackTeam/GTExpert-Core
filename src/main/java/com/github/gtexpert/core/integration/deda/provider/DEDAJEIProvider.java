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
            // Fusion categories
            String draconiumFusionCategory = GTValues.MODID + ":" +
                    GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.unlocalizedName;
            String awakenedFusionCategory = GTValues.MODID + ":" +
                    GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.unlocalizedName;

            // Shared categories
            String tierUpCategory = GTValues.MODID + ":" +
                    GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_RECIPES.unlocalizedName;
            String upgradeCategory = GTValues.MODID + ":" +
                    GTEDraconicRecipeMaps.DRACONIC_FUSION_UPGRADE_RECIPES.unlocalizedName;

            // Register Fusion categories
            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(),
                    draconiumFusionCategory);

            // Register TierUp category
            registry.addRecipeCatalyst(DEDAMetaTileEntities.DRACONIUM_FUSION.getStackForm(), tierUpCategory);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), tierUpCategory);

            // Register Upgrade category
            registry.addRecipeCatalyst(DEDAMetaTileEntities.DRACONIUM_FUSION.getStackForm(), upgradeCategory);
            registry.addRecipeCatalyst(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), upgradeCategory);
        }
    }
}
