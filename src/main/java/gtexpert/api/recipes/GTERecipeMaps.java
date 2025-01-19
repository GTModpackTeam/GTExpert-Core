package gtexpert.api.recipes;

import net.minecraft.init.SoundEvents;

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
    public static final RecipeMap<SimpleRecipeBuilder> SIEVE_RECIPES = new RecipeMap<>(
            "sieve", 2, 30, 0, 0, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.VERTICAL_INVERTED)
                    .setSound(SoundEvents.BLOCK_SAND_PLACE);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VOID_ORE_MINER_RECIPES = new RecipeMap<>(
            "void_ore_miner", 1, 1, 2, 0, new SimpleRecipeBuilder(), false)
                    .setProgressBar(GTEGuiTextures.PROGRESS_BAR_VOM, ProgressWidget.MoveType.VERTICAL)
                    .setSound(GTSoundEvents.DRILL_TOOL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> LARGE_GAS_COLLECTOR_RECIPES = new RecipeMap<>(
            "large_gas_collector", 2, 0, 1, 2, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
                    .setSlotOverlay(true, true, GuiTextures.CENTRIFUGE_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, ProgressWidget.MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.COOLING);
}
