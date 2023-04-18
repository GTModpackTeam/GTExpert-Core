package gtexpert.api.recipes;

import crafttweaker.annotations.ZenRegister;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.core.sound.GTSoundEvents;
import gtexpert.api.gui.GTEGuiTextures;
import gtexpert.api.recipes.draconicupgrade.RecipeMapDraconicFusion;
import gtexpert.api.recipes.draconicupgrade.RecipeMapOnRecipeCompileHook;
import gtexpert.api.recipes.draconicupgrade.tierup.TierUpRecipeBuilder;
import gtexpert.api.recipes.draconicupgrade.upgrade.UpgradeRecipeBuilder;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenExpansion("mods.gregtech.recipe.RecipeMaps")
@ZenRegister
public class GTERecipeMaps {
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> EXTREME_MIXER_RECIPES = new RecipeMap<>("extreme_mixer", 9, 1, 0, 1, new SimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
            .setSound(GTSoundEvents.MIXER);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VIAL_EXTRACTOR_RECIPES = new RecipeMap<>("vial_extractor", 1, 4, 0, 1, new SimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.EXTRACTOR_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.COMPRESSOR);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SAWMILL_RECIPES = new RecipeMap<>("sawmill", 2, 2, 1, 0, new SimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.SAWBLADE_OVERLAY)
            .setSlotOverlay(true, false, false, GuiTextures.CUTTER_OVERLAY)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.CHAINSAW_TOOL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VOID_ORE_MINER_RECIPES = new RecipeMap<>("void_ore_miner", 1, 1, 2, 0, new SimpleRecipeBuilder(), false)
            .setProgressBar(GTEGuiTextures.PROGRESS_BAR_VOID, ProgressWidget.MoveType.VERTICAL)
            .setSound(GTSoundEvents.DRILL_TOOL);

    /**
     * Fake recipe map to show tier-up recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link gtexpert.api.recipes.draconicupgrade.RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<TierUpRecipeBuilder> DRACONIC_FUSION_TIER_UP_FAKE_RECIPES = new RecipeMap<>("draconic_fusion_tier_up", 6, 3, 3, 1, new TierUpRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
            .onRecipeBuild(recipeBuilder -> GTERecipeMaps.AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.recipeBuilder()
                    .inputs(recipeBuilder.getInputs().toArray(new GTRecipeInput[0]))
                    .fluidInputs(recipeBuilder.getFluidInputs())
                    .outputs(recipeBuilder.getOutputs())
                    .fluidOutputs(recipeBuilder.getFluidOutputs())
                    .duration(recipeBuilder.getDuration())
                    .EUt(recipeBuilder.getEUt())
                    .catalyst(((TierUpRecipeBuilder) recipeBuilder).getCatalyst())
                    .result(((TierUpRecipeBuilder) recipeBuilder).getResult())
                    .buildAndRegister());

    /**
     * Fake recipe map to show tier-up recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link gtexpert.api.recipes.draconicupgrade.RecipeMapDraconicFusion}.
     */
    public static final RecipeMap<TierUpRecipeBuilder> AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES = new RecipeMap<>("awakened_draconic_fusion_tier_up", 6, 3, 3, 1, new TierUpRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL);

    /**
     * Fake recipe map to show upgrade recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link gtexpert.api.recipes.draconicupgrade.RecipeMapDraconicFusion}.
     */
    public static final RecipeMapOnRecipeCompileHook<UpgradeRecipeBuilder> DRACONIC_FUSION_UPGRADE_FAKE_RECIPES = (RecipeMapOnRecipeCompileHook<UpgradeRecipeBuilder>) new RecipeMapOnRecipeCompileHook<>("draconic_fusion_upgrade", 6, 3, 3, 1, new UpgradeRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
            .onRecipeBuild(recipeBuilder -> GTERecipeMaps.AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.recipeBuilder()
                    .inputs(recipeBuilder.getInputs().toArray(new GTRecipeInput[0]))
                    .fluidInputs(recipeBuilder.getFluidInputs())
                    .outputs(recipeBuilder.getOutputs())
                    .fluidOutputs(recipeBuilder.getFluidOutputs())
                    .duration(recipeBuilder.getDuration())
                    .EUt(recipeBuilder.getEUt())
                    .catalyst(((UpgradeRecipeBuilder) recipeBuilder).getCatalyst())
                    .upgradeName(((UpgradeRecipeBuilder) recipeBuilder).getUpgradeName())
                    .level(((UpgradeRecipeBuilder) recipeBuilder).getCurrentLevel())
                    .buildAndRegister());

    /**
     * Fake recipe map to show upgrade recipes for draconic fusion on JEI.
     * In order to preserve upgrade info NBT, actual recipe is handled by {@link gtexpert.api.recipes.draconicupgrade.RecipeMapDraconicFusion}.
     */
    public static final RecipeMapOnRecipeCompileHook<UpgradeRecipeBuilder> AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES = (RecipeMapOnRecipeCompileHook<UpgradeRecipeBuilder>) new RecipeMapOnRecipeCompileHook<UpgradeRecipeBuilder>("awakened_draconic_fusion_upgrade", 6, 3, 3, 1, new UpgradeRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DRACONIUM_FUSION_RECIPES = new RecipeMapDraconicFusion("draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), false, DRACONIC_FUSION_TIER_UP_FAKE_RECIPES, DRACONIC_FUSION_UPGRADE_FAKE_RECIPES)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.ARC)
            .onRecipeBuild(recipeBuilder -> GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                    .inputs(recipeBuilder.getInputs().toArray(new GTRecipeInput[0]))
                    .fluidInputs(recipeBuilder.getFluidInputs())
                    .outputs(recipeBuilder.getOutputs())
                    .fluidOutputs(recipeBuilder.getFluidOutputs())
                    .duration(recipeBuilder.getDuration())
                    .EUt(recipeBuilder.getEUt())
                    .buildAndRegister());

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> AWAKENED_DRACONIUM_FUSION_RECIPES = new RecipeMapDraconicFusion("awakened_draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), false, AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES, AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.ARC);
}
