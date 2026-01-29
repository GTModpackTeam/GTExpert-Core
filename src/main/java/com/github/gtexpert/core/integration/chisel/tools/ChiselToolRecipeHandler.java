package com.github.gtexpert.core.integration.chisel.tools;

import static gregtech.api.unification.material.info.MaterialFlags.*;

import com.github.gtexpert.core.integration.chisel.ChiselConfigHolder;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;

public class ChiselToolRecipeHandler {

    public static void registerRecipes() {
        OrePrefix.plate.addProcessingHandler(PropertyKey.TOOL, ChiselToolRecipeHandler::processChiselRecipe);
    }

    private static void processChiselRecipe(OrePrefix prefix, Material material, ToolProperty property) {
        if (!material.hasFlag(GENERATE_PLATE)) return;

        if (ConfigHolder.recipes.hardToolArmorRecipes && ChiselConfigHolder.hardToolRecipes) {
            ModHandler.addShapedRecipe(String.format("chisel_%s", material.getName()),
                    ChiselToolItems.CHISEL.get(material),
                    "fPh", " S ",
                    'P', new UnificationEntry(OrePrefix.plate, material),
                    'S', new UnificationEntry(OrePrefix.stick, Materials.Wood));
        } else {
            ModHandler.addShapedRecipe(String.format("chisel_%s", material.getName()),
                    ChiselToolItems.CHISEL.get(material),
                    " I", "S ",
                    'I', new UnificationEntry(OrePrefix.ingot, material),
                    'S', new UnificationEntry(OrePrefix.stick, Materials.Wood));
        }
    }
}
