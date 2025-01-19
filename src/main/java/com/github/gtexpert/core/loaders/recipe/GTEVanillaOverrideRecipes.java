package com.github.gtexpert.core.loaders.recipe;

import static gregtech.api.GTValues.MV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.ore.OrePrefix.*;
import static com.github.gtexpert.core.common.GTEConfigHolder.ceuOverride;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.category.RecipeCategories;
import gregtech.api.unification.material.Materials;

import com.github.gtexpert.core.api.util.Mods;

public class GTEVanillaOverrideRecipes {

    public static void init() {
        // Flint
        ModHandler.addShapelessRecipe("flint", new ItemStack(Items.FLINT, 1),
                new ItemStack(Blocks.GRAVEL, 1),
                new ItemStack(Blocks.GRAVEL, 1),
                new ItemStack(Blocks.GRAVEL, 1));

        // Diorite
        ModHandler.removeRecipeByName(Mods.Vanilla.getResource("diorite"));

        // Granite
        ModHandler.removeRecipeByName(Mods.Vanilla.getResource("granite"));

        // Andesite
        ModHandler.removeRecipeByName(Mods.Vanilla.getResource("andesite"));

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

        // Terracotta
        if (ceuOverride.nerfTerracottaCrafting) {
            for (int i = 0; i < Materials.CHEMICAL_DYES.length; i++) {
                // Remove terracotta macerator recipes
                GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES,
                        new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, i));

                // Add terracotta macerator recipes
                RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, i))
                        .output(dust, Materials.Clay, 4)
                        .category(RecipeCategories.MACERATOR_RECYCLING)
                        .duration(60).EUt(VA[MV])
                        .buildAndRegister();
            }
        }
    }
}
