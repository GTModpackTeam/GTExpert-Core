package com.github.gtexpert.core.integration.tc;

import net.minecraftforge.common.config.Config;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.tc")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_TC,
        category = "Thaumcraft")
public class TCConfigHolder {

    @Config.Comment({ "Start ID for Thaumcraft materials", "Default: 24191" })
    @Config.RangeInt(min = 0)
    @Config.RequiresMcRestart
    public static int materialStartId = 24191;
}
