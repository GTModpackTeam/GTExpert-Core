package gtexpert.integration.chisel;

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
public class ChiselRecipeMaps {

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> AUTO_CHISEL_RECIPES = new RecipeMap<>(
            "auto_chisel", 2, 9, 0, 0, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, false, GuiTextures.BOXED_BACKGROUND)
                    .setProgressBar(GTEGuiTextures.PROGRESS_BAR_CHISEL, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.FILE_TOOL);
}
