package com.github.gtexpert.core.integration.deda.recipemaps;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.core.sound.GTSoundEvents;

import com.github.gtexpert.core.integration.deda.recipemaps.tierup.TierUpRecipeBuilder;
import com.github.gtexpert.core.integration.deda.recipemaps.upgrade.UpgradeRecipeBuilder;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenExpansion("mods.gregtech.recipe.RecipeMaps")
@ZenRegister
public class GTEDraconicRecipeMaps {

    /**
     * Unified recipe map to show all tier-up recipes for draconic fusion on JEI.
     * Consolidates recipes from both Draconic and Awakened fusion machines.
     * Actual recipe execution is handled by {@link RecipeMapDraconicFusion}.
     */
    @ZenProperty
    public static final RecipeMap<TierUpRecipeBuilder> DRACONIC_FUSION_TIER_UP_RECIPES = new RecipeMapDraconicUpgrade<>(
            "draconic_fusion_tier_up", 6, 3, 3, 1, new TierUpRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL);

    /**
     * Unified recipe map to show all upgrade recipes for draconic fusion on JEI.
     * Consolidates recipes from both Draconic and Awakened fusion machines.
     * Actual recipe execution is handled by {@link RecipeMapDraconicFusion}.
     */
    @ZenProperty
    public static final RecipeMap<UpgradeRecipeBuilder> DRACONIC_FUSION_UPGRADE_RECIPES = new RecipeMapDraconicUpgrade<>(
            "draconic_fusion_upgrade", 6, 3, 3, 1, new UpgradeRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DRACONIUM_FUSION_RECIPES = new RecipeMapDraconicFusion(
            "draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), false,
            DRACONIC_FUSION_TIER_UP_RECIPES, DRACONIC_FUSION_UPGRADE_RECIPES)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ELECTROLYZER)
                    .onRecipeBuild(
                            recipeBuilder -> GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES
                                    .recipeBuilder()
                                    .inputs(recipeBuilder.getInputs().toArray(new GTRecipeInput[0]))
                                    .fluidInputs(recipeBuilder.getFluidInputs())
                                    .outputs(recipeBuilder.getOutputs())
                                    .fluidOutputs(recipeBuilder.getFluidOutputs())
                                    .duration(recipeBuilder.getDuration())
                                    .EUt(recipeBuilder.getEUt())
                                    .buildAndRegister());

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> AWAKENED_DRACONIUM_FUSION_RECIPES = new RecipeMapDraconicFusion(
            "awakened_draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), true,
            DRACONIC_FUSION_TIER_UP_RECIPES, DRACONIC_FUSION_UPGRADE_RECIPES)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ELECTROLYZER);
}
