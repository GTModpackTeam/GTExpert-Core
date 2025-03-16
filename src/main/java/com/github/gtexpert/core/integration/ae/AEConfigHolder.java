package com.github.gtexpert.core.integration.ae;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.modules.GTEModules;

@LangKey(GTEValues.MODID + ".config.integration.ae")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_AE,
        category = "Applied Energistics 2")
public class AEConfigHolder {

    @Config.Comment({ "The voltage at which AE can be started.",
            "The material is also adjusted to each voltage.", "Default: 3 (HV)" })
    @Config.RangeInt(min = 2, max = 10)
    public static int voltageTier = 3;

    @Config.Comment({ "Change AE swords, axes, etc. to GT recipe standards.",
            "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
    public static boolean hardToolRecipes = false;

    @Config.Comment({ "Integrate Printed Silicon and various Circuit creation molds.", "Default: false" })
    public static boolean moveSteelShape = false;
}
