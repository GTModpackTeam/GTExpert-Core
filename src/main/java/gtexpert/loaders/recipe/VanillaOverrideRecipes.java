package gtexpert.loaders.recipe;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;

import gtexpert.api.GTEValues;

public class VanillaOverrideRecipes {

    public static void init() {
        // Flint
        ModHandler.addShapelessRecipe("flint", new ItemStack(Items.FLINT, 1),
                new ItemStack(Blocks.GRAVEL, 1),
                new ItemStack(Blocks.GRAVEL, 1),
                new ItemStack(Blocks.GRAVEL, 1));

        // Diorite
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, "diorite"));

        // Granite
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, "granite"));

        // Andesite
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, "andesite"));

        // Daylight Sensor
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
