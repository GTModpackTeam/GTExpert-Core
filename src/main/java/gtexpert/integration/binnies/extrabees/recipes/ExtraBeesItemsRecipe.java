package gtexpert.integration.binnies.extrabees.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.ModHandler;

import gtexpert.api.util.Mods;
import gtexpert.integration.ffm.FFMConfigHolder;
import gtexpert.integration.ffm.FFMUtility;

public class ExtraBeesItemsRecipe {

    public static void init() {
        Enum<FFMUtility.recipeMode> recipeMode = FFMUtility.recipeMode.safeValueOf(FFMConfigHolder.gameMode);

        // Apiarist Database
        ModHandler.addShapelessNBTClearingRecipe("apiarist_database_nbt",
                Mods.ExtraBees.getItem("dictionary"),
                Mods.ExtraBees.getItem("dictionary"));

        if (recipeMode == FFMUtility.recipeMode.HARD) {

        }
    }
}
