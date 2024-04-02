package gtexpert.integration.binnies.botany.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.ModHandler;

import gtexpert.api.util.Mods;
import gtexpert.integration.ffm.FFMConfigHolder;
import gtexpert.integration.ffm.FFMUtility;

public class BotanyItemsRecipe {

    public static void init() {
        Enum<FFMUtility.recipeMode> recipeMode = FFMUtility.recipeMode.safeValueOf(FFMConfigHolder.gameMode);

        // Botanist Database
        ModHandler.addShapelessNBTClearingRecipe("botanist_database_nbt",
                Mods.Botany.getItem("database"),
                Mods.Botany.getItem("database"));

        if (recipeMode == FFMUtility.recipeMode.HARD) {

        }
    }
}
