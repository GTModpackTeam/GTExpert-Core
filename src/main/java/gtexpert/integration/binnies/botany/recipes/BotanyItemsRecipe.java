package gtexpert.integration.binnies.botany.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;

import gtexpert.api.util.Mods;
import gtexpert.integration.ffm.FFMConfigHolder;
import gtexpert.integration.ffm.FFMUtility;
import gtexpert.integration.ffm.recipes.machines.CarpenterLoader;

import forestry.api.recipes.RecipeManagers;

public class BotanyItemsRecipe {

    public static void init() {
        Enum<FFMUtility.recipeMode> recipeMode = FFMUtility.recipeMode.safeValueOf(FFMConfigHolder.gameMode);

        // Botanist Database
        ModHandler.addShapelessNBTClearingRecipe("botanist_database_nbt",
                Mods.Botany.getItem("database"),
                Mods.Botany.getItem("database"));

        if (recipeMode == FFMUtility.recipeMode.HARD) {
            // Botanist Database
            CarpenterLoader.removeCarpenterRecipe(Mods.Botany.getItem("database"));
            RecipeManagers.carpenterManager.addRecipe(
                    20, Materials.Redstone.getFluid(1440),
                    Mods.Forestry.getItem("portable_alyzer"), Mods.Botany.getItem("database"),
                    "SPS", "DCE", "SPS",
                    'S', new UnificationEntry(screw, Materials.Silver).toString(),
                    'D', new UnificationEntry(plate, Materials.Diamond).toString(),
                    'E', new UnificationEntry(plate, Materials.Emerald).toString(),
                    'P', new UnificationEntry(plate, Materials.Gold).toString(),
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.HV).toString());
        }
    }
}
