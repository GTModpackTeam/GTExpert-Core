package gtexpert.api;

import static gtexpert.common.GTEConfigHolder.*;

import net.minecraftforge.fml.common.Loader;

import gtexpert.api.util.GTELog;

public class GTEValues {

    public static final String MODNAME = "GTExpert-Core";
    public static final String MODID = "gtexpert",
            MODID_VANILLA = "minecraft",
            MODID_GCYM = "gcym",
            MODID_GCYS = "gcys",
            MODID_GTFO = "gregtechfoodoption",
            MODID_TOP = "theoneprobe",
            MODID_ECO = "endercore",
            MODID_EIO = "enderio",
            MODID_EIOE = "enderioendergy",
            MODID_EIOM = "enderiomachines",
            MODID_EIOC = "enderioconduits",
            MODID_EIOCA = "enderioconduitsappliedenergistics",
            MODID_AE = "appliedenergistics2",
            MODID_AEA = "aeadditions",
            MODID_AEFC = "ae2fc",
            MODID_EXCPU = "extracpus",
            MODID_DE = "draconicevolution",
            MODID_DA = "draconicadditions",
            MODID_CHISEL = "chisel",
            MODID_AVARITIA = "avaritia",
            MODID_AVAADDON = "avaritiaddons",
            MODID_FFM = "forestry";

    public static int ae2VoltageTier = voltageTier(ae2Integration.voltageTier) ? ae2Integration.voltageTier : 3,
            eioVoltageTier = voltageTier(eioIntegration.voltageTier) ? eioIntegration.voltageTier : 3,
            dedaVoltageTier = voltageTier(dedaIntegration.voltageTier) ? dedaIntegration.voltageTier : 6;

    public static boolean isModLoadedDEDA() {
        return Loader.isModLoaded(MODID_DE) && Loader.isModLoaded(MODID_DA);
    }

    public static boolean isModLoadedAEACPU() {
        return Loader.isModLoaded(GTEValues.MODID_AEA) && Loader.isModLoaded(GTEValues.MODID_EXCPU);
    }

    private static boolean voltageTier(int voltage) {
        if (voltage == 0) {
            GTELog.logger.error("Base Voltage must be greater than 0! Set to default value.");
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
