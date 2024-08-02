package gtexpert.loaders.recipe.handlers;

import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.item.ItemStack;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

import gtexpert.api.recipes.GTERecipeMaps;

public class GTEMaterialRecipeHandler {
    // TODO: リファクタリング

    public static void addImplosionRecipes(Material inputMaterial, Material outputMaterial) {
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosivesAmount(4)
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosivesType(new ItemStack(MetaBlocks.ITNT))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        GTERecipeMaps.ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
    }

    public static void addImplosionRecipes(Material inputMaterial, ItemStack outputStack) {
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesAmount(4)
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(outputStack)
                .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(new ItemStack(MetaBlocks.ITNT))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        GTERecipeMaps.ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
    }

    public static void addImplosionRecipes(String inputOreDict, ItemStack outputStack) {
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesAmount(4)
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(new ItemStack(MetaBlocks.ITNT))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
        GTERecipeMaps.ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .chancedOutput(dust, Materials.DarkAsh, 2500, 0)
                .buildAndRegister();
    }
}
