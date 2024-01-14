package gtexpert.recipe;

public class GTERecipeModules implements IGTERecipeContainer {

    public static final String MODULE_CORE = "core";
    public static final String AE_RECIPE = "ae_recipe",
            AVARITIA_RECIPE = "avaritia_recipe",
            CHISEL_RECIPE = "chisel_recipe",
            DRACONIC_RECIPE = "draconic_recipe",
            DRACONIC_UPGRADE_RECIPE = "draconic_upgrade_recipe",
            EIO_RECIPE = "eio_recipe",
            EIO_SOUL_RECIPE = "eio_soul_recipe",
            GTFO_RECIPE = "gtfo_recipe",
            VANILLA_RECIPE = "vanilla_recipe",
            CEU_OVERRIDE_RECIPE = "ceu_override_recipe",
            GTE_WOOD_RECIPE = "gte_wood_recipe",
            GTE_ORE_DICTIONARY = "gte_ore_dictionary";

    @Override
    public String getID() {
        return "gtexpert";
    }
}
