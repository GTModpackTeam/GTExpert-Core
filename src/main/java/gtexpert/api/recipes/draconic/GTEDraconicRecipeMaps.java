package gtexpert.api.recipes.draconic;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.core.sound.GTSoundEvents;

import gtexpert.api.recipes.draconic.tierup.TierUpRecipeBuilder;
import gtexpert.api.recipes.draconic.upgrade.UpgradeRecipeBuilder;

import stanhebben.zenscript.annotations.ZenProperty;

public class GTEDraconicRecipeMaps {

    /**
     * Fake recipe map to show tier-up recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<TierUpRecipeBuilder> DRACONIC_FUSION_TIER_UP_FAKE_RECIPES;

    /**
     * Fake recipe map to show tier-up recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<TierUpRecipeBuilder> AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES;

    /**
     * Fake recipe map to show upgrade recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<UpgradeRecipeBuilder> DRACONIC_FUSION_UPGRADE_FAKE_RECIPES;

    /**
     * Fake recipe map to show upgrade recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<UpgradeRecipeBuilder> AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES;

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DRACONIUM_FUSION_RECIPES;

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> AWAKENED_DRACONIUM_FUSION_RECIPES;

    static {
        DRACONIC_FUSION_TIER_UP_FAKE_RECIPES = new RecipeMapDraconicUpgrade<>(
                "draconic_fusion_tier_up", 6, 3, 3, 1, new TierUpRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                        .onRecipeBuild(
                                recipeBuilder -> GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES
                                        .recipeBuilder()
                                        .inputs(recipeBuilder.getInputs().toArray(new GTRecipeInput[0]))
                                        .fluidInputs(recipeBuilder.getFluidInputs())
                                        .outputs(recipeBuilder.getOutputs())
                                        .fluidOutputs(recipeBuilder.getFluidOutputs())
                                        .duration(recipeBuilder.getDuration())
                                        .EUt(recipeBuilder.getEUt())
                                        .catalyst(recipeBuilder.getCatalyst())
                                        .result(recipeBuilder.getResult())
                                        .buildAndRegister());

        AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES = new RecipeMapDraconicUpgrade<>(
                "awakened_draconic_fusion_tier_up", 6, 3, 3, 1, new TierUpRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                        .setSmallRecipeMap(DRACONIC_FUSION_TIER_UP_FAKE_RECIPES);

        DRACONIC_FUSION_UPGRADE_FAKE_RECIPES = new RecipeMapDraconicUpgrade<>(
                "draconic_fusion_upgrade", 6, 3, 3, 1, new UpgradeRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                        .onRecipeBuild(
                                recipeBuilder -> GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES
                                        .recipeBuilder()
                                        .inputs(recipeBuilder.getInputs().toArray(new GTRecipeInput[0]))
                                        .fluidInputs(recipeBuilder.getFluidInputs())
                                        .outputs(recipeBuilder.getOutputs())
                                        .fluidOutputs(recipeBuilder.getFluidOutputs())
                                        .duration(recipeBuilder.getDuration())
                                        .EUt(recipeBuilder.getEUt())
                                        .catalyst(recipeBuilder.getCatalyst())
                                        .upgradeName(recipeBuilder.getUpgradeName())
                                        .level(recipeBuilder.getCurrentLevel())
                                        .buildAndRegister());

        AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES = new RecipeMapDraconicUpgrade<>(
                "awakened_draconic_fusion_upgrade", 6, 3, 3, 1, new UpgradeRecipeBuilder(), false)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                        .setSmallRecipeMap(DRACONIC_FUSION_UPGRADE_FAKE_RECIPES);

        DRACONIUM_FUSION_RECIPES = new RecipeMapDraconicFusion(
                "draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), false, DRACONIC_FUSION_TIER_UP_FAKE_RECIPES,
                DRACONIC_FUSION_UPGRADE_FAKE_RECIPES)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.ARC)
                        .onRecipeBuild(
                                recipeBuilder -> GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                                        .inputs(recipeBuilder.getInputs().toArray(new GTRecipeInput[0]))
                                        .fluidInputs(recipeBuilder.getFluidInputs())
                                        .outputs(recipeBuilder.getOutputs())
                                        .fluidOutputs(recipeBuilder.getFluidOutputs())
                                        .duration(recipeBuilder.getDuration())
                                        .EUt(recipeBuilder.getEUt())
                                        .buildAndRegister());

        AWAKENED_DRACONIUM_FUSION_RECIPES = new RecipeMapDraconicFusion(
                "awakened_draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), false,
                AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES, AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.ARC)
                        .setSmallRecipeMap(DRACONIUM_FUSION_RECIPES);
    }
}
