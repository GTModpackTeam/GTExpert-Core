package gtexpert.integration.binnies.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;

import gtexpert.api.util.Mods;
import gtexpert.integration.ffm.FFMConfigHolder;
import gtexpert.integration.ffm.FFMUtility;

import forestry.api.recipes.RecipeManagers;

public class BinniesItemsRecipe {

    public static void init() {
        Enum<FFMUtility.recipeMode> recipeMode = FFMUtility.recipeMode.safeValueOf(FFMConfigHolder.gameMode);

        if (recipeMode == FFMUtility.recipeMode.HARD) {
            // Registry
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("registry"));
            RecipeManagers.carpenterManager.addRecipe(
                    40, Materials.Redstone.getFluid(4320),
                    Mods.Forestry.getItem("portable_alyzer"), Mods.Genetics.getItem("registry"),
                    "IAI", "BCP", "ILI",
                    'I', Mods.Genetics.getItem("misc", 1, 8),
                    'A', Mods.ExtraTrees.getItem("databasetree"),
                    'B', Mods.Botany.getItem("database"),
                    'C', Mods.Genetics.getItem("misc", 1, 9),
                    'P', Mods.ExtraBees.getItem("dictionary"),
                    'L', Mods.ExtraTrees.getItem("databaselepi"));
        }
    }
}
