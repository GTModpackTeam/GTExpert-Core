package gtexpert.loaders.recipe.handlers;

import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.item.ItemStack;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Material;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

import gtexpert.api.util.Mods;

public class GTEImplosionRecipeHandler {

    public static void add(Material inputMaterial, Material outputMaterial) {
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosivesAmount(4)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .output(gem, outputMaterial, 3)
                .explosivesType(new ItemStack(MetaBlocks.ITNT))
                .buildAndRegister();

        if (!Mods.ImplosionNoBomb.isModLoaded()) return;
        GTEImplosionNoBombRecipeHandler.add(inputMaterial, outputMaterial);
    }

    public static void add(Material inputMaterial, ItemStack outputStack) {
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesAmount(4)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(outputStack)
                .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, inputMaterial, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(new ItemStack(MetaBlocks.ITNT))
                .buildAndRegister();

        if (!Mods.ImplosionNoBomb.isModLoaded()) return;
        GTEImplosionNoBombRecipeHandler.add(inputMaterial, outputStack);
    }

    public static void add(String inputOreDict, ItemStack outputStack) {
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesAmount(4)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(inputOreDict, 4)
                .outputs(GTUtility.copy(3, outputStack))
                .explosivesType(new ItemStack(MetaBlocks.ITNT))
                .buildAndRegister();

        if (!Mods.ImplosionNoBomb.isModLoaded()) return;
        GTEImplosionNoBombRecipeHandler.add(inputOreDict, outputStack);
    }
}
