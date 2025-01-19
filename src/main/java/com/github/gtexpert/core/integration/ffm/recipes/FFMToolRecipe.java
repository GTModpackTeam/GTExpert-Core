package com.github.gtexpert.core.integration.ffm.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;

import com.github.gtexpert.core.api.util.Mods;

public class FFMToolRecipe {

    public static void init() {
        if (Mods.ForestryCore.isModLoaded()) {
            toolCore();
        }
        if (Mods.ForestryArboriculture.isModLoaded()) {
            toolArboriculture();
        }
        if (Mods.ForestryApiculture.isModLoaded()) {
            toolApiculture();
        }
    }

    public static void toolCore() {
        if (!ConfigHolder.recipes.hardToolArmorRecipes) return;

        // Survivalist Tools
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bronze_pickaxe"));
        ModHandler.addShapedRecipe(true, "survivalist_pickaxe", Mods.Forestry.getItem("bronze_pickaxe"),
                " S ", "hSf", "PII",
                'S', new ItemStack((Items.STICK)),
                'P', new UnificationEntry(plate, Materials.Bronze),
                'I', new UnificationEntry(ingot, Materials.Bronze));

        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bronze_shovel"));
        ModHandler.addShapedRecipe(true, "survivalist_shovel", Mods.Forestry.getItem("bronze_shovel"),
                " S ", " S ", "hPf",
                'S', new ItemStack(Items.STICK),
                'P', new UnificationEntry(plate, Materials.Bronze));

        // Naturalist's Armor
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("spectacles"));
        ModHandler.addShapedRecipe(true, "spectacles", Mods.Forestry.getItem("naturalist_helmet"),
                " P ", "G G",
                'P', new UnificationEntry(plate, Materials.Bronze),
                'G', new ItemStack(Blocks.GLASS_PANE));
    }

    private static void toolArboriculture() {
        if (!ConfigHolder.recipes.hardToolArmorRecipes) return;

        // Grafter
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("grafter"));
        ModHandler.addShapedRecipe(true, "grafter", Mods.Forestry.getItem("grafter"),
                "Ph ", "fS ", "  S",
                'P', new UnificationEntry(plate, Materials.Bronze),
                'S', new UnificationEntry(stick, Materials.Wood));
    }

    private static void toolApiculture() {
        // Habitat Locator
        if (!ConfigHolder.recipes.hardToolArmorRecipes) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(dust, Materials.Redstone)
                    .input(plate, Materials.Bronze, 4)
                    .circuitMeta(1)
                    .outputs(Mods.Forestry.getItem("habitat_locator"))
                    .duration(100).EUt(4).buildAndRegister();
        } else {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("habitat_locator"));
            ModHandler.addShapedRecipe(true, "habitat_locator", Mods.Forestry.getItem("habitat_locator"),
                    "SGB", "RPR", "AdS",
                    'S', new UnificationEntry(screw, Materials.Bronze),
                    'G', new ItemStack(Blocks.GLASS_PANE, 1, GTValues.W),
                    'B', new UnificationEntry(bolt, Materials.IronMagnetic),
                    'R', new UnificationEntry(ring, Materials.Zinc),
                    'P', new UnificationEntry(plate, Materials.Bronze),
                    'A', new UnificationEntry(bolt, Materials.RedAlloy));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(plate, Materials.Bronze)
                    .input(ring, Materials.Zinc)
                    .input(bolt, Materials.RedAlloy)
                    .input(bolt, Materials.IronMagnetic)
                    .input(screw, Materials.Bronze, 2)
                    .outputs(Mods.Forestry.getItem("habitat_locator"))
                    .duration(100).EUt(16).buildAndRegister();
        }
    }
}
