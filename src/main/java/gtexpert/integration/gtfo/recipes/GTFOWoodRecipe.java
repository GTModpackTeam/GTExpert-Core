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
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_0", 1))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "orange")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 1), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_0", 1, 4))
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mango")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 2), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_0", 1, 8))
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "apricot")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 3), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_0", 1, 12))
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "lemon")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 4), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_1", 1))
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "lime")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 5), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_1", 1, 4))
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "olive")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 6), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_1", 1, 8))
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "rainbowwood")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 7), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_1", 1, 12))
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "nutmeg")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 8), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_2", 1))
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "coconut")
                            .planks(Mods.GregTechFoodOption.getItem("gtfo_planks_0", 1, 9), null)
                            .log(Mods.GregTechFoodOption.getItem("gtfo_log_2", 1, 4))
                            .build());
        }
        return DEFAULT_ENTRIES;
    }

    public static void init() {
        for (WoodTypeEntry entry : getDefaultEntries()) {
            GTEWoodRecipeLoader.removePlankRecipe(true, entry, Mods.GregTechFoodOption.name());

            GTEWoodRecipeLoader.registerWoodTypeRecipe(false, entry);
            GTEWoodRecipeLoader.addSawmillRecipe(entry);
        }
    }
}
