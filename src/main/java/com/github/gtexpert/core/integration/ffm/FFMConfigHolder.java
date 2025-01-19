package com.github.gtexpert.core.integration.ffm;

import net.minecraftforge.common.config.Config;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.ffm")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_FFM,
        category = "Forestry")
public class FFMConfigHolder {

    @Config.Comment({ "Recipes for various items in Forestry will be more difficult",
            "default: NORMAL",
            "valid: [NORMAL, HARD]" })
    public static String gameMode = "NORMAL";
}
