package gtexpert.integration.ffm.recipes;

import gregtech.api.recipes.ModHandler;

import gtexpert.api.util.Mods;

import forestry.modules.ForestryModuleUids;

public class FFMMaterialsRecipe {

    public static void register() {
        if (forestry.modules.ModuleHelper.isEnabled(ForestryModuleUids.CORE)) {
            materialsCore();
        }
    }

    public static void materialsCore() {
        // Core
        // Copper
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("copper_block"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("block_to_copper"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("gear_copper"));
        // Tin
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("tin_block"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("block_to_tin"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("gear_tin"));
        // Bronze
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bronze_ingot"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bronze_block"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("block_to_bronze"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("gear_bronze"));
        // Apatite
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("apatite_block"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("block_to_apatite"));
    }
}
