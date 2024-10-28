package gtexpert.integration.tc;

import net.minecraftforge.common.config.Config;

import gtexpert.api.GTEValues;
import gtexpert.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.tc")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_TC,
        category = "Thaumcraft")
public class TCConfigHolder {

    @Config.Comment({ "Change Thaumcraft recipes to GT recipe standards.",
            "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
    public static boolean hardToolRecipes = false;
}
