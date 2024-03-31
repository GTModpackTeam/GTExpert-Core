package gtexpert.integration.ffm.recipes.machines;

public class FFMMachineRecipeManager {

    public static void init() {
        CarpenterLoader.initBase();
        CarpenterLoader.initNormal();
        CarpenterLoader.initHard();
    }
}
