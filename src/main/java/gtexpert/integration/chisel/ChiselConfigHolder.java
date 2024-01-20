package gtexpert.integration.chisel;

import net.minecraftforge.common.config.Config;

public class ChiselConfigHolder {

    @Config.Comment({ "Change Chisel recipes to GT recipe standards.",
            "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
    public static boolean hardToolRecipes = false;

    @Config.Comment({ "Change LED for Project:RED recipes to GT recipe standards.", "Default: false" })
    public static boolean hardLedRecipes = false;
}
