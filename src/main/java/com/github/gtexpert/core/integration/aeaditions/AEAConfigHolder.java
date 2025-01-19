package com.github.gtexpert.core.integration.aeaditions;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.modules.GTEModules;

@LangKey(GTEValues.MODID + ".config.integration.aeadditions")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_AEA,
        category = "AE Additions")
public class AEAConfigHolder {

}
