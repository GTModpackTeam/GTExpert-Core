package com.github.gtexpert.core.integration.chisel.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.common.ConfigHolder;

import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.chisel.ChiselConfigHolder;

public class ChiselToolsRecipe {

    public static void init() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && ChiselConfigHolder.hardToolRecipes) {
            // Remove original Chisel mod recipes - replaced by GregTech-style chisel
            ModHandler.removeRecipeByName(Mods.Chisel.getResource("chisel_iron"));
            ModHandler.removeRecipeByName(Mods.Chisel.getResource("chisel_diamond"));
            ModHandler.removeRecipeByName(Mods.Chisel.getResource("chisel_hitech"));
        }
    }
}
