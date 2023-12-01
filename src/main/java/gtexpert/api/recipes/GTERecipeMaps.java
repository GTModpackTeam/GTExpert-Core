package gtexpert.api.recipes;

import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
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
    public static final RecipeMap<SimpleRecipeBuilder> AUTO_CHISEL_RECIPES;

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VIAL_EXTRACTOR_RECIPES;

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SLICE_N_SPLICE_RECIPES;

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SOUL_BINDER_RECIPES;

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SAWMILL_RECIPES;

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VOID_ORE_MINER_RECIPES;

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> ADVANCED_CHEMICAL_REACTOR_RECIPES;

    static {
        AUTO_CHISEL_RECIPES = new RecipeMap<>("auto_chisel", 2, 9, 0, 0, new SimpleRecipeBuilder(), false)
                .setSlotOverlay(false, false, false, GuiTextures.BOXED_BACKGROUND)
                .setProgressBar(GTEGuiTextures.PROGRESS_BAR_CHISEL, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.FILE_TOOL);

        VIAL_EXTRACTOR_RECIPES = new RecipeMap<>("vial_extractor", 1, 4, 0, 1, new SimpleRecipeBuilder(), false)
                .setSlotOverlay(false, false, GTEGuiTextures.SOULVIAL_FULL_OVERLAY)
                .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.COMPRESSOR);

        SLICE_N_SPLICE_RECIPES = new RecipeMap<>("slice'n'splice", 6, 1, 0, 0, new SimpleRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CUT);

        SOUL_BINDER_RECIPES = new RecipeMap<>("soul_binder", 2, 2, 1, 0, new SimpleRecipeBuilder(), false)
                .setSlotOverlay(false, false, false, GTEGuiTextures.SOULVIAL_FULL_OVERLAY)
                .setSlotOverlay(true, false, false, GTEGuiTextures.SOULVIAL_EMPTY_OVRELAY)
                .setProgressBar(GuiTextures.PROGRESS_BAR_CANNER, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.ASSEMBLER);

        SAWMILL_RECIPES = new RecipeMap<>("sawmill", 2, 2, 1, 0, new SimpleRecipeBuilder(), false)
                .setSlotOverlay(false, false, GuiTextures.SAWBLADE_OVERLAY)
                .setSlotOverlay(true, false, false, GuiTextures.CUTTER_OVERLAY)
                .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
                .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.CHAINSAW_TOOL);

        VOID_ORE_MINER_RECIPES = new RecipeMap<>("void_ore_miner", 1, 1, 2, 0, new SimpleRecipeBuilder(), false)
                .setProgressBar(GTEGuiTextures.PROGRESS_BAR_VOID, ProgressWidget.MoveType.VERTICAL)
                .setSound(GTSoundEvents.DRILL_TOOL);

        ADVANCED_CHEMICAL_REACTOR_RECIPES = new RecipeMap<>("advanced_chemical_plant", 3, 3, 5, 4,
                new SimpleRecipeBuilder().EUt(GTValues.VA[GTValues.LV]), false)
                        .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
                        .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
                        .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
                        .setSlotOverlay(false, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
                        .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
                        .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
                        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
                        .setSound(GTSoundEvents.CHEMICAL_REACTOR)
                        .setSmallRecipeMap(RecipeMaps.CHEMICAL_RECIPES);
    }
}
