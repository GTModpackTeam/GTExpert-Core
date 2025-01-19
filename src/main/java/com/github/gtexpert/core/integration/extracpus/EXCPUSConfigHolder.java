package com.github.gtexpert.core.integration.extracpus;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.modules.GTEModules;

@LangKey(GTEValues.MODID + ".config.integration.extracpus")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_EXCPUS,
        category = "Extra CPUs")
public class EXCPUSConfigHolder {

    @Config.Comment({ "Do you want to migrate the recipes to NAE2.",
            "If not changed, the recipes will remain in ExtraCPUs.", "Default: false" })
    public static boolean migrateToNAE2 = false;
}
