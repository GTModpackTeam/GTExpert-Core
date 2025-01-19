package com.github.gtexpert.core.integration.gendustry;

import net.minecraftforge.common.config.Config;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.gendustry")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_GENDUSTRY,
        category = "Gendustry")
public class GendustryConfigHolder {}
