package gtexpert.api.recipes;

import static gregtech.api.GTValues.*;

import net.minecraft.init.SoundEvents;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMapBuilder;
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
    public static final RecipeMap<SimpleRecipeBuilder> SIEVE_RECIPES = new RecipeMapBuilder<>(
            "sieve", new SimpleRecipeBuilder())
                    .itemInputs(2)
                    .itemOutputs(30)
                    .fluidInputs(0)
                    .fluidOutputs(0)
                    .progressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.VERTICAL_INVERTED)
                    .sound(SoundEvents.BLOCK_SAND_PLACE)
                    .build();

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> VOID_ORE_MINER_RECIPES = new RecipeMapBuilder<>(
            "void_ore_miner", new SimpleRecipeBuilder())
                    .itemInputs(1)
                    .itemOutputs(1)
                    .fluidInputs(2)
                    .fluidOutputs(0)
                    .progressBar(GTEGuiTextures.PROGRESS_BAR_VOM, ProgressWidget.MoveType.VERTICAL)
                    .sound(GTSoundEvents.DRILL_TOOL)
                    .build();

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> LARGE_GAS_COLLECTOR_RECIPES = new RecipeMapBuilder<>(
            "large_gas_collector", new SimpleRecipeBuilder())
                    .itemInputs(2)
                    .itemOutputs(0)
                    .fluidInputs(1)
                    .fluidOutputs(2)
                    .itemSlotOverlay(GuiTextures.DUST_OVERLAY, false)
                    .fluidSlotOverlay(GuiTextures.CENTRIFUGE_OVERLAY, true)
                    .progressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, ProgressWidget.MoveType.HORIZONTAL)
                    .sound(GTSoundEvents.COOLING)
                    .build();

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES = new RecipeMapBuilder<>(
            "electric_implosion_compressor",
            new SimpleRecipeBuilder().duration(1).EUt(VA[UV]))
                    .itemInputs(6)
                    .itemOutputs(2)
                    .fluidInputs(0)
                    .fluidOutputs(0)
                    .itemSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_2, false)
                    .itemSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_2, false)
                    .itemSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_2, false)
                    .itemSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_2, false)
                    .itemSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_2, false)
                    .itemSlotOverlay(GuiTextures.IMPLOSION_OVERLAY_2, false, true)
                    .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true, true)
                    .sound(SoundEvents.ENTITY_GENERIC_EXPLODE)
                    .build().setSmallRecipeMap(RecipeMaps.IMPLOSION_RECIPES);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> STEAM_MIXER_RECIPES = new RecipeMapBuilder<>(
            "steam_mixer", new SimpleRecipeBuilder())
                    .itemInputs(6)
                    .itemOutputs(2)
                    .fluidInputs(0)
                    .fluidOutputs(0)
                    .itemSlotOverlay(GuiTextures.DUST_OVERLAY, false)
                    .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true)
                    .progressBar(GuiTextures.PROGRESS_BAR_MIXER, ProgressWidget.MoveType.CIRCULAR)
                    .sound(GTSoundEvents.MIXER)
                    .build();

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> STEAM_ASSEMBLER_RECIPES = new RecipeMapBuilder<>(
            "steam_assembler", new SimpleRecipeBuilder())
                    .itemInputs(9)
                    .itemOutputs(2)
                    .fluidInputs(0)
                    .fluidOutputs(0)
                    .itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false)
                    .progressBar(GuiTextures.PROGRESS_BAR_CIRCUIT, ProgressWidget.MoveType.HORIZONTAL)
                    .sound(GTSoundEvents.ASSEMBLER)
                    .build();

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> STEAM_CIRCUIT_ASSEMBLER_RECIPES = new RecipeMapBuilder<>(
            "steam_circuit_assembler", new SimpleRecipeBuilder())
                    .itemInputs(6)
                    .itemOutputs(2)
                    .fluidInputs(0)
                    .fluidOutputs(0)
                    .itemSlotOverlay(GuiTextures.CIRCUIT_OVERLAY, false)
                    .progressBar(GuiTextures.PROGRESS_BAR_CIRCUIT_ASSEMBLER, ProgressWidget.MoveType.HORIZONTAL)
                    .sound(GTSoundEvents.ASSEMBLER)
                    .build();

    public static void init() {
        RecipeMaps.VACUUM_RECIPES.setMaxFluidOutputs(2);
    }
}
