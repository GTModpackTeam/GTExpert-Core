package gtexpert.integration.ffm.recipes;

import static gregtech.api.unification.material.Materials.Bronze;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;

import gtexpert.api.util.Mods;

public class FFMToolRecipe {

    public static void init() {
        if (ConfigHolder.recipes.hardToolArmorRecipes) {
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
    }

    public static void toolCore() {
        // Survivalist Tools
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bronze_pickaxe"));
        ModHandler.addShapedRecipe(true, "survivalist_pickaxe", Mods.Forestry.getItem("bronze_pickaxe"),
                " S ", "hSf", "PII",
                'S', new ItemStack((Items.STICK)),
                'P', new UnificationEntry(OrePrefix.plate, Bronze),
                'I', new UnificationEntry(OrePrefix.ingot, Bronze));

        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bronze_shovel"));
        ModHandler.addShapedRecipe(true, "survivalist_shovel", Mods.Forestry.getItem("bronze_shovel"),
                " S ", " S ", "hPf",
                'S', new ItemStack(Items.STICK),
                'P', new UnificationEntry(OrePrefix.plate, Bronze));

        // Naturalist's Armor
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("spectacles"));
        ModHandler.addShapedRecipe(true, "spectacles", Mods.Forestry.getItem("naturalist_helmet"),
                " P ", "G G",
                'P', new UnificationEntry(OrePrefix.plate, Bronze),
                'G', new ItemStack(Blocks.GLASS_PANE));
    }

    private static void toolArboriculture() {
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("grafter"));
        ModHandler.addShapedRecipe(true, "grafter", Mods.Forestry.getItem("grafter"),
                " hP", " Sf", "S  ",
                'P', new UnificationEntry(OrePrefix.plate, Bronze),
                'S', new ItemStack(Items.STICK));
    }

    private static void toolApiculture() {
        // Habitat Locator
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("habitat_locator"));
        ModHandler.addShapedRecipe(true, "habitat_locator", Mods.Forestry.getItem("habitat_locator"),
                "SGB", "RPR", "AdS",
                'S', new UnificationEntry(OrePrefix.screw, Bronze),
                'G', new ItemStack(Blocks.GLASS_PANE, 1, GTValues.W),
                'B', new UnificationEntry(OrePrefix.bolt, Materials.IronMagnetic),
                'R', new UnificationEntry(OrePrefix.ring, Materials.Zinc),
                'P', new UnificationEntry(OrePrefix.plate,Bronze),
                'A', new UnificationEntry(OrePrefix.bolt, Materials.RedAlloy));
    }
}
