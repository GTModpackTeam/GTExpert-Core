package com.github.gtexpert.core.loaders.recipe.handlers;

import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.item.ItemStack;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

import com.github.gtexpert.core.api.util.Mods;

public class GTEImplosionRecipeHandler {

    public static void add(Material inputMaterial, Material outputMaterial) {
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosives(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosives(4)
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosives(MetaItems.DYNAMITE.getStackForm(2))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosives(new ItemStack(MetaBlocks.ITNT))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();

        if (!Mods.ImplosionNoBomb.isModLoaded()) return;
        GTEImplosionNoBombRecipeHandler.add(inputMaterial, outputMaterial);
    }

    public static void add(Material inputMaterial, ItemStack outputStack) {
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosives(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosives(4)
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(outputStack)
                .explosives(MetaItems.DYNAMITE.getStackForm(2))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosives(new ItemStack(MetaBlocks.ITNT))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();

        if (!Mods.ImplosionNoBomb.isModLoaded()) return;
        GTEImplosionNoBombRecipeHandler.add(inputMaterial, outputStack);
    }

    public static void add(String inputOreDict, ItemStack outputStack) {
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosives(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosives(4)
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosives(MetaItems.DYNAMITE.getStackForm(2))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosives(new ItemStack(MetaBlocks.ITNT))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();

        if (!Mods.ImplosionNoBomb.isModLoaded()) return;
        GTEImplosionNoBombRecipeHandler.add(inputOreDict, outputStack);
    }
}
