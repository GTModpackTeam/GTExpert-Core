package gtexpert.integration.ffm.recipes.machines;

import gtexpert.integration.ffm.FFMConfigHolder;

public class FFMMachineRecipeManager {

    public static void init() {
        CarpenterLoader.initBase();
        if (FFMConfigHolder.hardForestryRecipe) {
            CarpenterLoader.initHard();
        } else {
            CarpenterLoader.initNormal();
        }
    }
}
