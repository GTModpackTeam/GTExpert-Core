package com.github.gtexpert.core.api;

import com.github.gtexpert.core.Tags;
import com.github.gtexpert.core.api.util.GTELog;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.ae.AEConfigHolder;
import com.github.gtexpert.core.integration.deda.DEDAConfigHolder;
import com.github.gtexpert.core.integration.eio.EnderIOConfigHolder;
import com.github.gtexpert.core.modules.GTEModules;

public class GTEValues {

    public static final String MODNAME = Tags.MODNAME;
    public static final String MODID = Tags.MODID;

    public static int ae2VoltageTier = voltageTier(AEConfigHolder.voltageTier, GTEModules.MODULE_AE) ?
            AEConfigHolder.voltageTier : 3;
    public static int eioVoltageTier = voltageTier(EnderIOConfigHolder.voltageTier, GTEModules.MODULE_EIO) ?
            EnderIOConfigHolder.voltageTier : 3;
    public static int dedaVoltageTier = voltageTier(DEDAConfigHolder.voltageTier, GTEModules.MODULE_DEDA) ?
            DEDAConfigHolder.voltageTier : 6;

    public static boolean isModLoadedDEDA() {
        return Mods.DraconicEvolution.isModLoaded() && Mods.DraconicAdditions.isModLoaded();
    }

    private static boolean voltageTier(int voltage, String mod) {
        String greaterMsg = "Base Voltage must be greater than %d! Set to default value.";
        String lessMsg = "Base Voltage must be less than %d! Set to default value.";

        if (voltage == 0) {
            GTELog.logger.error("{}", String.format(greaterMsg, 0));
            return false;
        }

        switch (mod) {
            case GTEModules.MODULE_AE -> {
                if (voltage < 2) {
                    GTELog.logger.error("[" + GTEModules.MODULE_AE + "] {}", String.format(greaterMsg, 2));
                } else if (voltage > 10) {
                    GTELog.logger.error("[" + GTEModules.MODULE_AE + "] {}", String.format(lessMsg, 10));
                } else {
                    return true;
                }
            }
            case GTEModules.MODULE_EIO -> {
                if (voltage < 1) {
                    GTELog.logger.error("[" + GTEModules.MODULE_EIO + "] {}", String.format(greaterMsg, 1));
                } else if (voltage > 8) {
                    GTELog.logger.error("[" + GTEModules.MODULE_EIO + "] {}", String.format(lessMsg, 8));
                } else {
                    return true;
                }
            }
            case GTEModules.MODULE_DEDA -> {
                if (voltage < 3) {
                    GTELog.logger.error("[" + GTEModules.MODULE_DEDA + "] {}", String.format(greaterMsg, 3));
                } else if (voltage > 6) {
                    GTELog.logger.error("[" + GTEModules.MODULE_DEDA + "] {}", String.format(lessMsg, 6));
                } else {
                    return true;
                }
            }
            default -> GTELog.logger.error("Unknown mod: {}", mod);
        }
        return false;
    }
}
