package gtexpert.integration.deda.recipemaps;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMapBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
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
            "draconic_fusion_tier_up", new TierUpRecipeBuilder())
                    .itemInputs(6)
                    .itemOutputs(3)
                    .fluidInputs(3)
                    .fluidOutputs(1)
                    .progressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .build();

    /**
     * Fake recipe map to show tier-up recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<TierUpRecipeBuilder> AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES = new RecipeMapBuilder<>(
            "awakened_draconic_fusion_tier_up", new TierUpRecipeBuilder())
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
            "draconic_fusion_upgrade", new UpgradeRecipeBuilder())
                    .itemInputs(6)
                    .itemOutputs(3)
                    .fluidInputs(3)
                    .fluidOutputs(1)
                    .progressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
                    .build();

    /**
     * Fake recipe map to show upgrade recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<UpgradeRecipeBuilder> AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES = new RecipeMapBuilder<>(
            "awakened_draconic_fusion_upgrade", new UpgradeRecipeBuilder())
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
