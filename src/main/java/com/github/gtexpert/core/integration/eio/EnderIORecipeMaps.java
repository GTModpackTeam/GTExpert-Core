package com.github.gtexpert.core.integration.eio;

import net.minecraft.util.SoundEvent;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMapBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

import com.github.gtexpert.core.api.gui.GTEGuiTextures;
import com.github.gtexpert.core.api.util.Mods;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenExpansion("mods.gregtech.recipe.RecipeMaps")
@ZenRegister
public class EnderIORecipeMaps {

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VIAL_EXTRACTOR_RECIPES = new RecipeMapBuilder<>(
            "vial_extractor", new SimpleRecipeBuilder())
                    .itemInputs(1)
                    .itemOutputs(4)
                    .fluidInputs(0)
                    .fluidOutputs(1)
                    .itemSlotOverlay(GTEGuiTextures.SOULVIAL_FULL_OVERLAY, false)
                    .progressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL)
                    .sound(GTSoundEvents.COMPRESSOR)
                    .build();

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SLICE_N_SPLICE_RECIPES = new RecipeMapBuilder<>(
            "slice_n_splice", new SimpleRecipeBuilder())
                    .itemInputs(6)
                    .itemOutputs(1)
                    .fluidInputs(0)
                    .fluidOutputs(0)
                    .progressBar(GuiTextures.PROGRESS_BAR_SLICE, ProgressWidget.MoveType.HORIZONTAL)
                    .sound(new SoundEvent(Mods.EnderIO.getResource("machine.slicensplice")))
                    .build();

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SOUL_BINDER_RECIPES = new RecipeMapBuilder<>(
            "soul_binder", new SimpleRecipeBuilder())
                    .itemInputs(2)
                    .itemOutputs(2)
                    .fluidInputs(1)
                    .fluidOutputs(0)
                    .itemSlotOverlay(GTEGuiTextures.SOULVIAL_FULL_OVERLAY, false)
                    .itemSlotOverlay(GTEGuiTextures.SOULVIAL_EMPTY_OVRELAY, true)
                    .progressBar(GuiTextures.PROGRESS_BAR_CANNER, ProgressWidget.MoveType.HORIZONTAL)
                    .sound(new SoundEvent(Mods.EnderIO.getResource("machine.soulbinder")))
                    .build();
}
