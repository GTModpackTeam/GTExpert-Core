package com.github.gtexpert.core.loaders.recipe.handlers;

import static gregtech.api.unification.ore.OrePrefix.block;

import net.minecraft.item.ItemStack;

import gregtech.api.unification.material.Material;
import gregtech.api.util.GTUtility;

import com.github.gtexpert.inb.api.recipes.INBRecipeMaps;

public class GTEImplosionNoBombRecipeHandler {

    public static void add(Material inputMaterial, Material outputMaterial) {
        INBRecipeMaps.ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES.recipeBuilder()
                .input(block, inputMaterial, 4)
                .output(block, outputMaterial, 3)
                .buildAndRegister();
    }

    public static void add(Material inputMaterial, ItemStack outputStack) {
        INBRecipeMaps.ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES.recipeBuilder()
                .input(block, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .buildAndRegister();
    }

    public static void add(String inputOreDict, ItemStack outputStack) {
        INBRecipeMaps.ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .buildAndRegister();
    }
}
