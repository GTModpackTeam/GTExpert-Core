package gtexpert.loaders.recipe;

import gtexpert.api.GTEValues;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
import gtexpert.loaders.recipe.ingredients.ChiselRecipeLoader;
import gtexpert.loaders.recipe.ingredients.DraconicRecipeLoader;
import gtexpert.loaders.recipe.ingredients.DraconicUpgradeRecipeLoader;
import gtexpert.loaders.recipe.ingredients.GTFORecipeLoader;

import net.minecraftforge.fml.common.Loader;

public class GTERecipeManager {

    private GTERecipeManager() {}

    public static void preLoad() {}

    public static void load() {
        GTEMetaItems.init();
        GTEMetaTileEntities.init();
    }

    public static void loadLow() {
        VanillaOverrideRecipes.init();
        CEUOverrideRecipeLoader.init();
        GTERecipeLoader.init();
        GTEWoodRecipeLoader.init();
        AERecipeLoader.init();
        EIORecipeLoader.init();
        EIOSoulRecipeLoader.init();
    }

    public static void loadLowest() {
        if (Loader.isModLoaded(GTEValues.MODID_DE) && Loader.isModLoaded(GTEValues.MODID_DA)) {
            DraconicRecipeLoader.init();
            DraconicUpgradeRecipeLoader.init();
        }
        if (Loader.isModLoaded(GTEValues.MODID_GTFO)) {
            GTFORecipeLoader.init();
        }
        if (Loader.isModLoaded(GTEValues.MODID_CHISEL)) {
            ChiselRecipeLoader.init();
        }
    }
}
