package gtexpert.integration.ae.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.common.ConfigHolder;

import gtexpert.api.util.Mods;
import gtexpert.integration.ae.AEConfigHolder;

public class AEToolsRecipe {

    public static void init() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && AEConfigHolder.hardToolRecipes) {
            // Nether Quartz Axe
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/nether_quartz_axe"));
            ModHandler.addShapedRecipe(true, "nether_quartz_axe",
                    Mods.AppliedEnergistics2.getItem("nether_quartz_axe"),
                    "PQf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Hoe
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/nether_quartz_hoe"));
            ModHandler.addShapedRecipe(true, "nether_quartz_hoe",
                    Mods.AppliedEnergistics2.getItem("nether_quartz_hoe"),
                    "PQf", "hS ", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Pickaxe
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/nether_quartz_pickaxe"));
            ModHandler.addShapedRecipe(true, "nether_quartz_pickaxe",
                    Mods.AppliedEnergistics2.getItem("nether_quartz_pickaxe"),
                    "PQQ", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Shovel
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/nether_quartz_spade"));
            ModHandler.addShapedRecipe(true, "nether_quartz_spade",
                    Mods.AppliedEnergistics2.getItem("nether_quartz_spade"),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Sword
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/nether_quartz_sword"));
            ModHandler.addShapedRecipe(true, "nether_quartz_sword",
                    Mods.AppliedEnergistics2.getItem("nether_quartz_sword"),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Cutting Knife
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/nether_quartz_cutting_knife"));
            ModHandler.addShapedRecipe(true, "nether_quartz_cutting_knife",
                    Mods.AppliedEnergistics2.getItem("nether_quartz_cutting_knife"),
                    "fPh", "QSQ", " S ",
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Wrench
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/nether_quartz_wrench"));
            ModHandler.addShapedRecipe(true, "ether_quartz_wrench",
                    Mods.AppliedEnergistics2.getItem("nether_quartz_wrench"),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz));

            // Certus Quartz Axe
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/certus_quartz_axe"));
            ModHandler.addShapedRecipe(true, "certus_quartz_axe",
                    Mods.AppliedEnergistics2.getItem("certus_quartz_axe"),
                    "PQf", "PS ", "hS ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Hoe
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/certus_quartz_hoe"));
            ModHandler.addShapedRecipe(true, "certus_quartz_hoe",
                    Mods.AppliedEnergistics2.getItem("certus_quartz_hoe"),
                    "PQf", "hS ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Pickaxe
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/certus_quartz_pickaxe"));
            ModHandler.addShapedRecipe(true, "certus_quartz_pickaxe",
                    Mods.AppliedEnergistics2.getItem("certus_quartz_pickaxe"),
                    "PQQ", "hSf", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Shovel
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/certus_quartz_spade"));
            ModHandler.addShapedRecipe(true, "certus_quartz_spade",
                    Mods.AppliedEnergistics2.getItem("certus_quartz_spade"),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Sword
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/certus_quartz_sword"));
            ModHandler.addShapedRecipe(true, "certus_quartz_sword",
                    Mods.AppliedEnergistics2.getItem("certus_quartz_sword"),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Cutting Knife
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/certus_quartz_cutting_knife"));
            ModHandler.addShapedRecipe(true, "certus_quartz_cutting_knife",
                    Mods.AppliedEnergistics2.getItem("certus_quartz_cutting_knife"),
                    "fPh", "QSQ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Wrench
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("tools/certus_quartz_wrench"));
            ModHandler.addShapedRecipe(true, "certus_quartz_wrench",
                    Mods.AppliedEnergistics2.getItem("certus_quartz_wrench"),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz));
        }
    }
}
