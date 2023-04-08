package gtexpert.api.recipes;

import crafttweaker.annotations.ZenRegister;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.*;
import gregtech.core.sound.GTSoundEvents;
import gtexpert.api.gui.GTEGuiTextures;
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
    public static final RecipeMap<SimpleRecipeBuilder> SAWMill_RECIPES = new RecipeMap<>("sawmill", 2, 2, 1, 0, new SimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, GuiTextures.SAWBLADE_OVERLAY)
            .setSlotOverlay(true, false, false, GuiTextures.CUTTER_OVERLAY)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.CHAINSAW_TOOL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VOID_ORE_MINER_RECIPES = new RecipeMap<>("void_ore_miner", 1, 1, 2, 0, new SimpleRecipeBuilder(), false)
            .setProgressBar(GTEGuiTextures.PROGRESS_BAR_VOID, ProgressWidget.MoveType.VERTICAL)
            .setSound(GTSoundEvents.DRILL_TOOL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> DRACONIUM_FUSION_RECIPES = new RecipeMap<>("draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> AWAKENED_DRACONIUM_FUSION_RECIPES = new RecipeMap<>("awakened_draconium_fusion", 6, 3, 3, 1, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.ASSEMBLER);
}
