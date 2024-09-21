package gtexpert.integration.deda.recipemaps;

import static gtexpert.api.util.GTEUtility.gteId;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMapBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.core.sound.GTSoundEvents;

import gtexpert.integration.deda.recipemaps.tierup.TierUpRecipeBuilder;
import gtexpert.integration.deda.recipemaps.upgrade.UpgradeRecipeBuilder;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenExpansion("mods.gregtech.recipe.RecipeMaps")
@ZenRegister
public class GTEDraconicRecipeMaps {

    /**
     * Fake recipe map to show tier-up recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<TierUpRecipeBuilder> DRACONIC_FUSION_TIER_UP_FAKE_RECIPES = new RecipeMapBuilder<>(
            "draconic_fusion_tier_up", new TierUpRecipeBuilder().hidden())
                    .itemInputs(6)
                    .itemOutputs(3)
                    .fluidInputs(3)
                    .fluidOutputs(1)
                    .progressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .onBuild(gteId("draconic_fusion_tier_up_1"),
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
                                    .buildAndRegister())
                    .build();

    /**
     * Fake recipe map to show tier-up recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<TierUpRecipeBuilder> AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES = new RecipeMapBuilder<>(
            "awakened_draconic_fusion_tier_up", new TierUpRecipeBuilder().hidden())
                    .itemInputs(6)
                    .itemOutputs(3)
                    .fluidInputs(3)
                    .fluidOutputs(1)
                    .progressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .build().setSmallRecipeMap(DRACONIC_FUSION_TIER_UP_FAKE_RECIPES);

    /**
     * Fake recipe map to show upgrade recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<UpgradeRecipeBuilder> DRACONIC_FUSION_UPGRADE_FAKE_RECIPES = new RecipeMapBuilder<>(
            "draconic_fusion_upgrade", new UpgradeRecipeBuilder().hidden())
                    .itemInputs(6)
                    .itemOutputs(3)
                    .fluidInputs(3)
                    .fluidOutputs(1)
                    .progressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .onBuild(gteId("draconic_fusion_upgrade_1"),
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
                                    .buildAndRegister())
                    .build();

    /**
     * Fake recipe map to show upgrade recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<UpgradeRecipeBuilder> AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES = new RecipeMapBuilder<>(
            "awakened_draconic_fusion_upgrade", new UpgradeRecipeBuilder().hidden())
                    .itemInputs(6)
                    .itemOutputs(3)
                    .fluidInputs(3)
                    .fluidOutputs(1)
                    .progressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .build().setSmallRecipeMap(DRACONIC_FUSION_UPGRADE_FAKE_RECIPES);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DRACONIUM_FUSION_RECIPES = new RecipeMapBuilder<>(
            "draconium_fusion", new SimpleRecipeBuilder())
                    .itemInputs(6)
                    .itemOutputs(3)
                    .fluidInputs(3)
                    .fluidOutputs(1)
                    .progressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .sound(GTSoundEvents.ELECTROLYZER)
                    .onBuild(gteId("draconic_fusion_1"),
                            recipeBuilder -> GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES
                                    .recipeBuilder()
                                    .inputs(recipeBuilder.getInputs().toArray(new GTRecipeInput[0]))
                                    .fluidInputs(recipeBuilder.getFluidInputs())
                                    .outputs(recipeBuilder.getOutputs())
                                    .fluidOutputs(recipeBuilder.getFluidOutputs())
                                    .duration(recipeBuilder.getDuration())
                                    .EUt(recipeBuilder.getEUt())
                                    .buildAndRegister())
                    .build();

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> AWAKENED_DRACONIUM_FUSION_RECIPES = new RecipeMapBuilder<>(
            "awakened_draconium_fusion", new SimpleRecipeBuilder())
                    .itemInputs(6)
                    .itemOutputs(3)
                    .fluidInputs(3)
                    .fluidOutputs(1)
                    .progressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .sound(GTSoundEvents.ELECTROLYZER)
                    .build().setSmallRecipeMap(DRACONIUM_FUSION_RECIPES);
}
