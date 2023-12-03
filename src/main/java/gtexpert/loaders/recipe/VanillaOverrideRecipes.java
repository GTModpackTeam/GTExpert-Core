package gtexpert.loaders.recipe;

import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;

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
                .input(gem, Materials.NetherQuartz, 3)
                .input(slab, Materials.Wood, 2)
                .fluidInputs(Materials.Glass.getFluid(144))
                .output(Blocks.DAYLIGHT_DETECTOR)
                .duration(200).EUt(10)
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, Materials.CertusQuartz, 3)
                .input(slab, Materials.Wood, 2)
                .fluidInputs(Materials.Glass.getFluid(144))
                .output(Blocks.DAYLIGHT_DETECTOR)
                .duration(200).EUt(10)
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, Materials.Quartzite, 3)
                .input(slab, Materials.Wood, 2)
                .fluidInputs(Materials.Glass.getFluid(144))
                .output(Blocks.DAYLIGHT_DETECTOR)
                .duration(200).EUt(10)
                .buildAndRegister();
    }
}
