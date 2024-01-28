package gtexpert.integration.eio;

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
public class EnderIORecipeMaps {

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VIAL_EXTRACTOR_RECIPES = new RecipeMap<>(
            "vial_extractor", 1, 4, 0, 1, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GTEGuiTextures.SOULVIAL_FULL_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.COMPRESSOR);
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SLICE_N_SPLICE_RECIPES = new RecipeMap<>(
            "slice'n'splice", 6, 1, 0, 0, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL);
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SOUL_BINDER_RECIPES = new RecipeMap<>(
            "soul_binder", 2, 2, 1, 0, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GTEGuiTextures.SOULVIAL_FULL_OVERLAY)
                    .setSlotOverlay(true, false, false, GTEGuiTextures.SOULVIAL_EMPTY_OVRELAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_CANNER, ProgressWidget.MoveType.HORIZONTAL);
}
