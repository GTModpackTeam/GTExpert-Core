package gtexpert.integration.chisel.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTEUtility;
import gtexpert.integration.chisel.ChiselConfig;

public class ChiselToolsRecipe {

    public static void init() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && ChiselConfig.hardToolRecipes) {
            // Iron Chisel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_iron"));
            ModHandler.addShapedRecipe(true, "chisel_iron",
                    GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_iron"),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Materials.Iron),
                    'C', new UnificationEntry(screw, Materials.Iron),
                    'S', new UnificationEntry(stick, Materials.Bronze));

            // Diamond Chisel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_diamond"));
            ModHandler.addShapedRecipe(true, "chisel_diamond",
                    GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_diamond"),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Materials.Diamond),
                    'C', GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_iron"),
                    'S', new UnificationEntry(stick, Materials.RoseGold));

            // iChisel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_hitech"));
            ModHandler.addShapedRecipe(true, "chisel_hitech",
                    GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_hitech"),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Materials.Diamond),
                    'C', GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_diamond"),
                    'S', new UnificationEntry(stick, Materials.StainlessSteel));
        }
    }
}
