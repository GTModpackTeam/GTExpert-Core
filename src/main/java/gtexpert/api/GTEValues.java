package gtexpert.api;

import gtexpert.Tags;
import gtexpert.api.util.GTELog;
import gtexpert.api.util.Mods;
import gtexpert.integration.ae.AEConfigHolder;
import gtexpert.integration.deda.DEDAConfigHolder;
import gtexpert.integration.eio.EnderIOConfigHolder;

public class GTEValues {

    public static final String MODNAME = Tags.MODNAME;
    public static final String MODID = Tags.MODID;

    public static int ae2VoltageTier = voltageTier(AEConfigHolder.voltageTier) ? AEConfigHolder.voltageTier : 3,
            eioVoltageTier = voltageTier(EnderIOConfigHolder.voltageTier) ? EnderIOConfigHolder.voltageTier : 3,
            dedaVoltageTier = voltageTier(DEDAConfigHolder.voltageTier) ? DEDAConfigHolder.voltageTier : 6;

    public static boolean isModLoadedDEDA() {
        return Mods.DraconicEvolution.isModLoaded() && Mods.DraconicAdditions.isModLoaded();
    }

    private static boolean voltageTier(int voltage) {
        if (voltage == 0) {
            GTELog.logger.error("Base Voltage must be greater than 0! Set to default value.");
            return false;
        }
        if (ae2VoltageTier < 2) {
            GTELog.logger.error("Base Voltage must be greater than 2! Set to default value.");
            return false;
        }
        if (ae2VoltageTier > 10) {
            GTELog.logger.error("Base Voltage must be less than 10! Set to default value.");
            return false;
        }
        if (eioVoltageTier > 8) {
            GTELog.logger.error("Base Voltage must be less than 8! Set to default value.");
            return false;
        }
        if (dedaVoltageTier < 3) {
            GTELog.logger.error("Base Voltage must be greater than 3! Set to default value.");
            return false;
        } else if (dedaVoltageTier > 6) {
            GTELog.logger.error("Base Voltage must be less than 8! Set to default value.");
            return false;
        }
        return true;
    }
}
