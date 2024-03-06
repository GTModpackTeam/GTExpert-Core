package gtexpert.integration.ffm.recipes;

import gregtech.api.recipes.ModHandler;

import gtexpert.api.util.Mods;

public class FFMMaterialsRecipe {

    public static void register() {
        if (Mods.ForestryCore.isModLoaded()) {
            materialsCore();
        }
        if (Mods.ForestryCharcoal.isModLoaded()) {
            materialCharcoal();
        }
    }

    public static void materialsCore() {
        // Copper Ingot
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("block_to_copper"));

        // Copper Block
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("copper_block"));

        // Copper Gear
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("gear_copper"));

        // Tin Ingot
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("block_to_tin"));

        // Tin Block
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("tin_block"));

        // Tin Gear
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("gear_tin"));

        // Bronze Ingot
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bronze_ingot"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("block_to_bronze"));

        // Bronze Block
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bronze_block"));

        // Bronze Gear
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("gear_bronze"));

        // Apatite
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("block_to_apatite"));

        // Apatite Block
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("apatite_block"));
    }

    public static void materialCharcoal() {
        // Charcoal
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("charcoal"));

        // Charcoal Block
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("charcoal_block"));
    }
}
