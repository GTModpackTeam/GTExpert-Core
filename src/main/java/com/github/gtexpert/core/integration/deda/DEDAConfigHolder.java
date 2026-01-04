package com.github.gtexpert.core.integration.deda;

import net.minecraftforge.common.config.Config;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.deda")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_DEDA,
        category = "Draconic Evolution & Draconic Additions")
public class DEDAConfigHolder {

    @Config.Comment({ "The voltage at which DE/DA can be started.",
            "The material is also adjusted to each voltage.", "Default: 6 (LuV)" })
    @Config.RangeInt(min = 3, max = 6)
    public static int voltageTier = 6;

    @Config.Comment({ "Enable verbose logging for upgrade recipe lookup debugging.",
            "Useful for troubleshooting recipe matching issues.", "Default: false" })
    public static boolean debugRecipeLookup = false;

    @Config.Comment({ "Enable caching for upgrade recipe lookups.",
            "Improves performance by indexing recipes by catalyst item type.",
            "Disable if you experience recipe matching issues.", "Default: true" })
    public static boolean enableUpgradeRecipeCache = true;
}
