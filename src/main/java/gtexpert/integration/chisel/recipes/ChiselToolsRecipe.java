package gtexpert.integration.chisel.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;

import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.integration.chisel.ChiselConfigHolder;

public class ChiselToolsRecipe {

    public static void init() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && ChiselConfigHolder.hardToolRecipes) {
            // Iron Chisel
            ModHandler.removeRecipeByName(Mods.Chisel.getResource("chisel_iron"));
            ModHandler.addShapedRecipe(true, "chisel_iron",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "chisel_iron"),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Materials.Iron),
                    'C', new UnificationEntry(screw, Materials.Iron),
                    'S', new UnificationEntry(stick, Materials.Bronze));

            // Diamond Chisel
            ModHandler.removeRecipeByName(Mods.Chisel.getResource("chisel_diamond"));
            ModHandler.addShapedRecipe(true, "chisel_diamond",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "chisel_diamond"),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Materials.Diamond),
                    'C', GTEUtility.getModItem(Mods.Names.CHISEL, "chisel_iron"),
                    'S', new UnificationEntry(stick, Materials.RoseGold));

            // iChisel
            ModHandler.removeRecipeByName(Mods.Chisel.getResource("chisel_hitech"));
            ModHandler.addShapedRecipe(true, "chisel_hitech",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "chisel_hitech"),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Materials.Diamond),
                    'C', GTEUtility.getModItem(Mods.Names.CHISEL, "chisel_diamond"),
                    'S', new UnificationEntry(stick, Materials.StainlessSteel));
        }
    }
}
