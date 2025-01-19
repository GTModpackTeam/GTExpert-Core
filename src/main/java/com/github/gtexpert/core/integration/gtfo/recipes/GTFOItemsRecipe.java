package com.github.gtexpert.core.integration.gtfo.recipes;

import net.minecraft.init.Items;

import com.github.gtexpert.core.api.util.Mods;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;

public class GTFOItemsRecipe {

    public static void init() {
        // Kitchen Recipe
        ModHandler.removeRecipeByOutput(Mods.GregTechFoodOption.getItem("gtfo_meta_item", 1, 343));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.PAPER)
                .input("dyeBlack")
                .circuitMeta(3)
                .outputs(Mods.GregTechFoodOption.getItem("gtfo_meta_item", 1, 343))
                .duration(100).EUt(4).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.PAPER)
                .fluidInputs(Materials.DyeBlack.getFluid(72))
                .circuitMeta(3)
                .outputs(Mods.GregTechFoodOption.getItem("gtfo_meta_item", 1, 343))
                .duration(100).EUt(4).buildAndRegister();
    }
}
