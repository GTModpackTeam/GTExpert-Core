package gtexpert.loaders.recipe;

import gregtech.loaders.recipe.RecyclingRecipes;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.metatileentities.MetaTileEntitiesManager;

public class GTERecipeManager {
    // TODO remove this

    private GTERecipeManager() {}

    public static void load() {
        GTEMetaItems.init();
        MetaTileEntitiesManager.init();
    }

    public static void loadLow() {
        VanillaOverrideRecipes.init();
        CEUOverrideRecipeLoader.init();
        GTEWoodRecipeLoader.init();
    }

    public static void loadLowest() {
        LowestOverrideRecipeLoader.init();

        RecyclingRecipes.init();
    }
}
