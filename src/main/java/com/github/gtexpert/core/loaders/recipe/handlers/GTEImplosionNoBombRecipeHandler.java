package com.github.gtexpert.core.loaders.recipe.handlers;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.unification.material.Materials;

import net.minecraft.item.ItemStack;

import gregtech.api.unification.material.Material;
import gregtech.api.util.GTUtility;

import com.github.gtexpert.inb.api.recipes.INBRecipeMaps;

public class GTEImplosionNoBombRecipeHandler {

    public static void add(Material inputMaterial, Material outputMaterial) {
        INBRecipeMaps.FAKE_IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
    }

    public static void add(Material inputMaterial, ItemStack outputStack) {
        INBRecipeMaps.FAKE_IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
    }

    public static void add(String inputOreDict, ItemStack outputStack) {
        INBRecipeMaps.FAKE_IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
    }
}
