package com.github.gtexpert.core.integration.te;

import net.minecraftforge.common.config.Config;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.te")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_TE,
        category = "ThaumicEnergistics")
public class TEConfigHolder {}
