package gtexpert.loaders.recipe;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gtexpert.api.GTEValues;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class VanillaOverrideRecipes {
    public static void init() {
        blocks();
    }

    private static void blocks() {
        // Diorite
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, "diorite"));

        // Granite
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, "granite"));

        // Andesite
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, "andesite"));

        // Comparator
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/vanilla_comparator"));

        // Daylight Sensor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/vanilla_daylight_detector"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, NetherQuartz, 3)
                .input(slab, Wood, 2)
                .fluidInputs(Glass.getFluid(144))
                .output(Blocks.DAYLIGHT_DETECTOR)
                .duration(200).EUt(10)
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, CertusQuartz, 3)
                .input(slab, Wood, 2)
                .fluidInputs(Glass.getFluid(144))
                .output(Blocks.DAYLIGHT_DETECTOR)
                .duration(200).EUt(10)
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, Quartzite, 3)
                .input(slab, Wood, 2)
                .fluidInputs(Glass.getFluid(144))
                .output(Blocks.DAYLIGHT_DETECTOR)
                .duration(200).EUt(10)
                .buildAndRegister();
    }
}
