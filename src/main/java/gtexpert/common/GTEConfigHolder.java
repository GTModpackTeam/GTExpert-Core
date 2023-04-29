package gtexpert.common;

import gtexpert.api.GTEValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GTEValues.MODID)
public class GTEConfigHolder {
    @Config.Comment({"Making Planks and Sticks even more difficult.",
            "CEu's nerfWoodCrafting to true to reflect.", "Default: false"})
    public static boolean moreNerfWoodCrafting = false;

    @Config.Comment({"Change to a recipe using Assembly Line.",
            "CEu's enableHighTierSolars to true to reflect.", "Default: false"})
    public static boolean hardSolarPanel = false;
}
