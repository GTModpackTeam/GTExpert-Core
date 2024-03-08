package gtexpert.integration.gtfo.recipes;

import java.util.Arrays;
import java.util.List;

import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.Mods;
import gtexpert.core.loaders.GTEWoodRecipeLoader;

public class GTFOWoodRecipe {

    private static List<WoodTypeEntry> DEFAULT_ENTRIES;

    private static List<WoodTypeEntry> getDefaultEntries() {
        if (DEFAULT_ENTRIES == null) {
            final String mcModId = Mods.GregTechFoodOption.name();
            return DEFAULT_ENTRIES = Arrays.asList(
                    new WoodTypeEntry.Builder(mcModId, "banana")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 0), "banana_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_0", 1, 0)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "orange")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 1), "orange_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_0", 1, 4)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mango")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 2), "mango_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_0", 1, 8)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "apricot")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 3), "apricot_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_0", 1, 12)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "lemon")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 4), "lemon_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_1", 1, 0)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "lime")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 5), "lime_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_1", 1, 4)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "olive")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 6), "olive_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_1", 1, 8)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "rainbowwood")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 7), "rainbowwood_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_1", 1, 12)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "nutmeg")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 8), "nutmeg_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_2", 1, 0)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "coconut")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 9), "coconut_planks")
                            .log(Mods.GregTechFoodOption.getItem("gtfo_logs_2", 1, 4)).removeCharcoalRecipe()
                            .registerAllUnificationInfo()
                            .build());
        }
        return DEFAULT_ENTRIES;
    }

    public static void init() {
        for (WoodTypeEntry entry : getDefaultEntries()) {
            GTEWoodRecipeLoader.removePlankRecipes(true, entry);

            GTEWoodRecipeLoader.addPlankRecipes(entry);
            GTEWoodRecipeLoader.addSawmillRecipes(entry);
        }
    }
}
