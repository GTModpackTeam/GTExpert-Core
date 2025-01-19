package com.github.gtexpert.core.integration.ffm.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import com.github.gtexpert.core.api.util.Mods;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;

public class FFMItemRecipe {

    public static void init() {
        if (Mods.ForestryCore.isModLoaded()) {
            itemCore();
        }
    }

    public static void itemCore() {
        // Study Casing
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("sturdy_casing"));
        ModHandler.addShapedRecipe(true, "sturdy_casing", Mods.Forestry.getItem("sturdy_machine"),
                "PPP", "PwP", "PPP",
                'P', new UnificationEntry(plate, Materials.Bronze));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Materials.Bronze, 8)
                .circuitMeta(8)
                .outputs(Mods.Forestry.getItem("sturdy_machine"))
                .duration(50).EUt(16).buildAndRegister();

        // Portable Analyzer
        ModHandler.addShapelessNBTClearingRecipe("portable_alyzer_nbt",
                Mods.Forestry.getItem("portable_alyzer"),
                Mods.Forestry.getItem("portable_alyzer"));
    }
}
