package com.github.gtexpert.core.loaders.recipe.handlers;

import static gregtech.api.unification.ore.OrePrefix.*;

import java.util.function.Consumer;

import net.minecraft.item.ItemStack;

import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;

import com.github.gtexpert.inb.api.recipes.INBRecipeMaps;

public class GTEImplosionNoBombRecipeHandler {

    public static void add(Material inputMaterial, Material outputMaterial) {
        register(builder -> builder.input(dust, inputMaterial, 4).output(gem, outputMaterial, 3));
    }

    public static void add(Material inputMaterial, ItemStack outputStack) {
        register(builder -> builder.input(dust, inputMaterial, 4).outputs(GTUtility.copy(3, outputStack)));
    }

    public static void add(String inputOreDict, ItemStack outputStack) {
        register(builder -> builder.input(inputOreDict, 4).outputs(GTUtility.copy(3, outputStack)));
    }

    private static void register(Consumer<SimpleRecipeBuilder> recipeConfig) {
        SimpleRecipeBuilder builder = INBRecipeMaps.FAKE_IMPLOSION_RECIPES.recipeBuilder();
        recipeConfig.accept(builder);
        builder.chancedOutput(dust, Materials.DarkAsh, 2500, 0).buildAndRegister();
    }
}
