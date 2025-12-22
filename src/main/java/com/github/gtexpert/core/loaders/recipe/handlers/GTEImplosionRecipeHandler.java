package com.github.gtexpert.core.loaders.recipe.handlers;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.unification.material.Materials;

import net.minecraft.item.ItemStack;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Material;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

import com.github.gtexpert.core.api.util.Mods;

public class GTEImplosionRecipeHandler {

    public static void add(Material inputMaterial, Material outputMaterial) {
        if (Mods.ImplosionNoBomb.isModLoaded()) {
            GTEImplosionNoBombRecipeHandler.add(inputMaterial, outputMaterial);
        } else {
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(dust, inputMaterial, 4)
                    .output(gem, outputMaterial, 3)
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                    .buildAndRegister();
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(dust, inputMaterial, 4)
                    .output(gem, outputMaterial, 3)
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesAmount(4)
                    .buildAndRegister();
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(dust, inputMaterial, 4)
                    .output(gem, outputMaterial, 3)
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                    .buildAndRegister();
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(dust, inputMaterial, 4)
                    .output(gem, outputMaterial, 3)
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesType(new ItemStack(MetaBlocks.ITNT))
                    .buildAndRegister();
        }
    }

    public static void add(Material inputMaterial, ItemStack outputStack) {
        if (Mods.ImplosionNoBomb.isModLoaded()) {
            GTEImplosionNoBombRecipeHandler.add(inputMaterial, outputStack);
        } else {
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(dust, inputMaterial, 4)
                    .outputs(GTUtility.copy(3, outputStack))
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                    .buildAndRegister();
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(dust, inputMaterial, 4)
                    .outputs(GTUtility.copy(3, outputStack))
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesAmount(4)
                    .buildAndRegister();
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(dust, inputMaterial, 4)
                    .outputs(GTUtility.copy(3, outputStack))
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                    .buildAndRegister();
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(dust, inputMaterial, 4)
                    .outputs(GTUtility.copy(3, outputStack))
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesType(new ItemStack(MetaBlocks.ITNT))
                    .buildAndRegister();
        }
    }

    public static void add(String inputOreDict, ItemStack outputStack) {
        if (Mods.ImplosionNoBomb.isModLoaded()) {
            GTEImplosionNoBombRecipeHandler.add(inputOreDict, outputStack);
        } else {
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(inputOreDict, 4)
                    .outputs(GTUtility.copy(3, outputStack))
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                    .buildAndRegister();
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(inputOreDict, 4)
                    .outputs(GTUtility.copy(3, outputStack))
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesAmount(4)
                    .buildAndRegister();
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(inputOreDict, 4)
                    .outputs(GTUtility.copy(3, outputStack))
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                    .buildAndRegister();
            RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                    .input(inputOreDict, 4)
                    .outputs(GTUtility.copy(3, outputStack))
                    .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                    .explosivesType(new ItemStack(MetaBlocks.ITNT))
                    .buildAndRegister();
        }
    }
}
