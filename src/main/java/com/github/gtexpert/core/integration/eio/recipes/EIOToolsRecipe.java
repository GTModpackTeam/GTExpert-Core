package com.github.gtexpert.core.integration.eio.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.ConfigHolder;

import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.integration.eio.EnderIOConfigHolder;

import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;

public class EIOToolsRecipe {

    public static void init() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && EnderIOConfigHolder.hardToolArmorRecipes) {
            // Dark Helm
            ModHandler.addShapedRecipe(true, "dark_steel_helmet",
                    new ItemStack(ModObject.itemDarkSteelHelmet.getItemNN(), 1),
                    "PPP", "PhP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Dark Chest
            ModHandler.addShapedRecipe(true, "dark_steel_chestplate",
                    new ItemStack(ModObject.itemDarkSteelChestplate.getItemNN(), 1),
                    "PhP", "PPP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Dark Leggings
            ModHandler.addShapedRecipe(true, "dark_steel_leggings",
                    new ItemStack(ModObject.itemDarkSteelLeggings.getItemNN(), 1),
                    "PPP", "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Dark Boots
            ModHandler.addShapedRecipe(true, "dark_steel_boots",
                    new ItemStack(ModObject.itemDarkSteelBoots.getItemNN(), 1),
                    "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Ender Helm
            ModHandler.addShapedRecipe(true, "end_steel_helmet",
                    new ItemStack(ModObject.itemEndSteelHelmet.getItemNN(), 1),
                    "PPP", "PhP", " G ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Ender Chest
            ModHandler.addShapedRecipe(true, "end_steel_chestplate",
                    new ItemStack(ModObject.itemEndSteelChestplate.getItemNN(), 1),
                    "PhP", "PGP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Ender Leggings
            ModHandler.addShapedRecipe(true, "end_steel_leggings",
                    new ItemStack(ModObject.itemEndSteelLeggings.getItemNN(), 1),
                    "PPP", "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Ender Boots
            ModHandler.addShapedRecipe(true, "end_steel_boots",
                    new ItemStack(ModObject.itemEndSteelBoots.getItemNN(), 1),
                    "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Stellar Helm
            ModHandler.addShapedRecipe(true, "stellar_alloy_helmet",
                    new ItemStack(EndergyObject.itemStellarAlloyHelmet.getItemNN(), 1),
                    "PPP", "PhP", " G ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Stellar Chest
            ModHandler.addShapedRecipe(true, "stellar_alloy_chestplate",
                    new ItemStack(EndergyObject.itemStellarAlloyChestplate.getItemNN(), 1),
                    "PhP", "PGP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Stellar Leggings
            ModHandler.addShapedRecipe(true, "stellar_alloy_leggings",
                    new ItemStack(EndergyObject.itemStellarAlloyLeggings.getItemNN(), 1),
                    "PPP", "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Stellar Boots
            ModHandler.addShapedRecipe(true, "stellar_alloy_boots",
                    new ItemStack(EndergyObject.itemStellarAlloyBoots.getItemNN(), 1),
                    "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Dark Axe
            ModHandler.addShapedRecipe(true, "dark_steel_axe",
                    new ItemStack(ModObject.itemDarkSteelAxe.getItemNN(), 1),
                    "PIf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Pickaxe
            ModHandler.addShapedRecipe(true, "dark_steel_pickaxe",
                    new ItemStack(ModObject.itemDarkSteelPickaxe.getItemNN(), 1),
                    "PII", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Sword
            ModHandler.addShapedRecipe(true, "dark_steel_sword",
                    new ItemStack(ModObject.itemDarkSteelSword.getItemNN(), 1),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Crook
            ModHandler.addShapedRecipe(true, "dark_steel_crook",
                    new ItemStack(ModObject.itemDarkSteelCrook.getItemNN(), 1),
                    "PPS", "shS", " fS",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Backhoe
            ModHandler.addShapedRecipe(true, "dark_steel_backhoe",
                    new ItemStack(ModObject.itemDarkSteelHand.getItemNN(), 1),
                    "PPP", "PHP", "hsf",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'H', new ItemStack(Items.DIAMOND_HOE, 1));

            // Dark Shears
            ModHandler.addShapedRecipe(true, "dark_steel_shears",
                    new ItemStack(ModObject.itemDarkSteelShears.getItemNN(), 1),
                    "PTP", "hRf", "SsS",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'T', OreDictUnifier.get(screw, GTEMaterials.DarkSteel),
                    'R', OreDictUnifier.get(ring, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Bow
            ModHandler.addShapedRecipe(true, "dark_steel_bow",
                    new ItemStack(ModObject.itemDarkSteelBow.getItemNN(), 1),
                    "hSW", "PRW", "fSW",
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel),
                    'W', new ItemStack(Items.STRING, 1),
                    'P', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 45),
                    'R', OreDictUnifier.get(ring, GTEMaterials.DarkSteel));

            // Dark Shield
            ModHandler.addShapedRecipe(true, "dark_steel_shield",
                    new ItemStack(ModObject.itemDarkSteelShield.getItemNN(), 1),
                    "BSB", "LPL", "BSB",
                    'B', OreDictUnifier.get(bolt, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel),
                    'L', OreDictUnifier.get(stickLong, GTEMaterials.DarkSteel),
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Ender Axe
            ModHandler.addShapedRecipe(true, "ender_steel_axe",
                    new ItemStack(ModObject.itemEndSteelAxe.getItemNN(), 1),
                    "PIf", "PG ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.EndSteel));

            // Ender Pickaxe
            ModHandler.addShapedRecipe(true, "ender_steel_pickaxe",
                    new ItemStack(ModObject.itemEndSteelPickaxe.getItemNN(), 1),
                    "PII", "hGf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.EndSteel));

            // Ender Sword
            ModHandler.addShapedRecipe(true, "ender_steel_sword",
                    new ItemStack(ModObject.itemEndSteelSword.getItemNN(), 1),
                    " P ", "hGf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.EndSteel));

            // Ender Bow
            ModHandler.addShapedRecipe(true, "ender_steel_bow",
                    new ItemStack(ModObject.itemEndSteelBow.getItemNN(), 1),
                    "hSW", "PRW", "fSW",
                    'S', OreDictUnifier.get(stick, GTEMaterials.EndSteel),
                    'W', new ItemStack(Items.STRING, 1),
                    'P', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 45),
                    'R', OreDictUnifier.get(ring, GTEMaterials.EndSteel));

            // Ender Shield
            ModHandler.addShapedRecipe(true, "ender_steel_shield",
                    new ItemStack(ModObject.itemEndSteelShield.getItemNN(), 1),
                    "BSB", "LPL", "BSB",
                    'B', OreDictUnifier.get(bolt, GTEMaterials.EndSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.EndSteel),
                    'L', OreDictUnifier.get(stickLong, GTEMaterials.EndSteel),
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel));

            // Stellar Axe
            ModHandler.addShapedRecipe(true, "stellar_alloy_axe",
                    new ItemStack(EndergyObject.itemStellarAlloyAxe.getItemNN(), 1),
                    "PIf", "PO ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.StellarAlloy),
                    'O', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.StellarAlloy));

            // Stellar Pickaxe
            ModHandler.addShapedRecipe(true, "stellar_alloy_pickaxe",
                    new ItemStack(EndergyObject.itemStellarAlloyPickaxe.getItemNN(), 1),
                    "PII", "hOf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.StellarAlloy),
                    'O', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.StellarAlloy));

            // Stellar Sword
            ModHandler.addShapedRecipe(true, "stellar_alloy_sword",
                    new ItemStack(EndergyObject.itemStellarAlloySword.getItemNN(), 1),
                    " P ", "hOf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'O', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.StellarAlloy));

            // Yeta Wrench
            ModHandler.addShapedRecipe(true, "yeta_wrench", new ItemStack(ModObject.itemYetaWrench.getItemNN(), 1),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.ElectricalSteel));
        }
    }
}
