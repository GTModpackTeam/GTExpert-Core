package gtexpert.core;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

import gtexpert.api.gui.GTEGuiTextures;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenExpansion("mods.gregtech.recipe.RecipeMaps")
@ZenRegister
public class GTERecipeMaps {

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> AUTO_CHISEL_RECIPES = new RecipeMap<>(
            "auto_chisel", 2, 9, 0, 0, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GuiTextures.BOXED_BACKGROUND)
                    .setProgressBar(GTEGuiTextures.PROGRESS_BAR_CHISEL, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.FILE_TOOL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VIAL_EXTRACTOR_RECIPES = new RecipeMap<>(
            "vial_extractor", 1, 4, 0, 1, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GTEGuiTextures.SOULVIAL_FULL_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.COMPRESSOR);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SLICE_N_SPLICE_RECIPES = new RecipeMap<>(
            "slice'n'splice", 6, 1, 0, 0, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.CUT);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SOUL_BINDER_RECIPES = new RecipeMap<>(
            "soul_binder", 2, 2, 1, 0, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GTEGuiTextures.SOULVIAL_FULL_OVERLAY)
                    .setSlotOverlay(true, false, false, GTEGuiTextures.SOULVIAL_EMPTY_OVRELAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_CANNER, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ASSEMBLER);

    // @ZenProperty
    // public static final RecipeMap<SimpleRecipeBuilder> SIEVE_RECIPES = new RecipeMap<>(
    // "sieve", 2, 30, 0, 0, new SimpleRecipeBuilder(), false)
    // .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.VERTICAL_INVERTED)
    // .setSound(SoundEvents.BLOCK_SAND_PLACE);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SAWMILL_RECIPES = new RecipeMap<>(
            "sawmill", 2, 2, 1, 0, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.SAWBLADE_OVERLAY)
                    .setSlotOverlay(true, false, false, GuiTextures.CUTTER_OVERLAY)
                    .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.CHAINSAW_TOOL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VOID_ORE_MINER_RECIPES = new RecipeMap<>(
            "void_ore_miner", 1, 1, 2, 0, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GTEGuiTextures.PROGRESS_BAR_VOM, ProgressWidget.MoveType.VERTICAL)
                    .setSound(GTSoundEvents.DRILL_TOOL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> ADVANCED_GAS_COLLECTOR_RECIPES = new RecipeMap<>(
            "advanced_gas_collector", 2, 0, 1, 2, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
                    .setSlotOverlay(true, true, GuiTextures.CENTRIFUGE_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.COOLING);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> STEAM_MIXER_RECIPES = new RecipeMap<>(
            "steam_mixer", 6, 2, 0, 0, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
                    .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
                    .setSound(GTSoundEvents.MIXER);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> STEAM_ASSEMBLER_RECIPES = new RecipeMap<>(
            "steam_assembler", 9, 2, 0, 0, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> STEAM_CIRCUIT_ASSEMBLER_RECIPES = new RecipeMap<>(
            "steam_circuit_assembler", 6, 2, 0, 0, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.ASSEMBLER);
}
