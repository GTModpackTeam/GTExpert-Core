package com.github.gtexpert.core.integration.deda.recipemaps;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

import com.github.gtexpert.core.integration.deda.recipemaps.tierup.TierUpRecipeBuilder;
import com.github.gtexpert.core.integration.deda.recipemaps.upgrade.UpgradeRecipeBuilder;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenExpansion("mods.gregtech.recipe.RecipeMaps")
@ZenRegister
public class GTEDraconicRecipeMaps {

    @ZenProperty
    public static final RecipeMapDraconicTierUp DRACONIC_FUSION_TIER_UP_RECIPES = new RecipeMapDraconicTierUp(
            "draconic_fusion_tier_up", 6, 3, 3, 1, new TierUpRecipeBuilder(), false);

    @ZenProperty
    public static final RecipeMapDraconicUpgrade DRACONIC_FUSION_UPGRADE_RECIPES = new RecipeMapDraconicUpgrade(
            "draconic_fusion_upgrade", 6, 3, 3, 1, new UpgradeRecipeBuilder(), false);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DRACONIUM_FUSION_RECIPES = new RecipeMapDraconicFusion(
            "draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), false,
            DRACONIC_FUSION_TIER_UP_RECIPES, DRACONIC_FUSION_UPGRADE_RECIPES);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> AWAKENED_DRACONIUM_FUSION_RECIPES = new RecipeMapDraconicFusion(
            "awakened_draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), false,
            DRACONIC_FUSION_TIER_UP_RECIPES, DRACONIC_FUSION_UPGRADE_RECIPES);

    static {
        DRACONIC_FUSION_TIER_UP_RECIPES
                .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL);
        DRACONIC_FUSION_UPGRADE_RECIPES
                .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL);
        DRACONIUM_FUSION_RECIPES
                .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.ELECTROLYZER);
        AWAKENED_DRACONIUM_FUSION_RECIPES
                .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.ELECTROLYZER);
    }
}
