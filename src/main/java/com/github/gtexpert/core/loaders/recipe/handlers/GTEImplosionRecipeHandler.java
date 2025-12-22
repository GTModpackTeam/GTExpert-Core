package com.github.gtexpert.core.loaders.recipe.handlers;

import static gregtech.api.unification.ore.OrePrefix.*;

import java.util.function.Consumer;

import net.minecraft.item.ItemStack;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.ImplosionRecipeBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

import com.github.gtexpert.core.api.util.Mods;

public class GTEImplosionRecipeHandler {

    private static final Consumer<ImplosionRecipeBuilder>[] EXPLOSIVES = new Consumer[] {
            b -> ((ImplosionRecipeBuilder) b).explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8)),
            b -> ((ImplosionRecipeBuilder) b).explosivesAmount(4),
            b -> ((ImplosionRecipeBuilder) b).explosivesType(MetaItems.DYNAMITE.getStackForm(2)),
            b -> ((ImplosionRecipeBuilder) b).explosivesType(new ItemStack(MetaBlocks.ITNT))
    };

    public static void add(Material inputMaterial, Material outputMaterial) {
        register(
                builder -> builder.input(dust, inputMaterial, 4).output(gem, outputMaterial, 3),
                () -> GTEImplosionNoBombRecipeHandler.add(inputMaterial, outputMaterial));
    }

    public static void add(Material inputMaterial, ItemStack outputStack) {
        register(
                builder -> builder.input(dust, inputMaterial, 4).outputs(GTUtility.copy(3, outputStack)),
                () -> GTEImplosionNoBombRecipeHandler.add(inputMaterial, outputStack));
    }

    public static void add(String inputOreDict, ItemStack outputStack) {
        register(
                builder -> builder.input(inputOreDict, 4).outputs(GTUtility.copy(3, outputStack)),
                () -> GTEImplosionNoBombRecipeHandler.add(inputOreDict, outputStack));
    }

    private static void register(Consumer<ImplosionRecipeBuilder> recipeConfig, Runnable noBombHandler) {
        if (Mods.ImplosionNoBomb.isModLoaded()) {
            noBombHandler.run();
        } else {
            for (Consumer<ImplosionRecipeBuilder> explosive : EXPLOSIVES) {
                ImplosionRecipeBuilder builder = RecipeMaps.IMPLOSION_RECIPES.recipeBuilder();
                recipeConfig.accept(builder);
                builder.chancedOutput(dust, Materials.DarkAsh, 2500, 0);
                explosive.accept(builder);
                builder.buildAndRegister();
            }
        }
    }
}
