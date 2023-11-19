package gtexpert.loaders.recipe;

import gtexpert.api.GTEValues;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.metatileentities.MetaTileEntitiesManager;
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
        MetaTileEntitiesManager.init();
    }

    public static void loadLow() {
        VanillaOverrideRecipes.init();
        CEUOverrideRecipeLoader.init();
        GTERecipeLoader.init();
        GTEWoodRecipeLoader.init();
        if (Loader.isModLoaded(GTEValues.MODID_AE)) {
            AERecipeLoader.init();
        }
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            EIORecipeLoader.init();
            EIOSoulRecipeLoader.init();
        }
    }

    public static void loadLowest() {
        LowestOverrideRecipeLoader.init();

        if (GTEValues.isModLoadedDEDA()) {
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
