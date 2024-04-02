package gtexpert.integration.binnies.extratrees.recipes;

import gregtech.api.recipes.ModHandler;

import gtexpert.api.util.Mods;
import gtexpert.integration.ffm.FFMConfigHolder;
import gtexpert.integration.ffm.FFMUtility;

public class ExtraTreesItemsRecipe {

    public static void init() {
        Enum<FFMUtility.recipeMode> recipeMode = FFMUtility.recipeMode.safeValueOf(FFMConfigHolder.gameMode);

        // Arborist Database
        ModHandler.addShapelessNBTClearingRecipe("arborist_database_nbt",
                Mods.ExtraTrees.getItem("databaselepi"),
                Mods.ExtraTrees.getItem("databaselepi"));
    }
}
