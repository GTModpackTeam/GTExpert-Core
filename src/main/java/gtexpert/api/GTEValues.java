package gtexpert.api;

import net.minecraftforge.fml.common.Loader;

import gtexpert.Tags;
import gtexpert.api.util.GTELog;
import gtexpert.integration.ae.AEConfigHolder;
import gtexpert.integration.deda.DEDAConfigHolder;
import gtexpert.integration.eio.EnderIOConfigHolder;

public class GTEValues {

    public static final String MODNAME = Tags.MODNAME;
    public static final String MODID = Tags.MODID,
            MODID_VANILLA = "minecraft",
            MODID_GCYM = "gcym",
            MODID_GTFO = "gregtechfoodoption",
            MODID_JEI = "jei",
            MODID_TOP = "theoneprobe",
            MODID_MUI = "modularui",
            MODID_MIXIN = "mixinbooter",
            MODID_FFM = "forestry",
            MODID_ECO = "endercore",
            MODID_EIO = "enderio",
            MODID_EIOE = "enderioendergy",
            MODID_EIOM = "enderiomachines",
            MODID_EIOC = "enderioconduits",
            MODID_EIOCA = "enderioconduitsappliedenergistics",
            MODID_AE = "appliedenergistics2",
            MODID_AEA = "aeadditions",
            MODID_AEFC = "ae2fc",
            MODID_NAE2 = "nae2",
            MODID_EXCPUS = "extracpus",
            MODID_DE = "draconicevolution",
            MODID_DA = "draconicadditions",
            MODID_CHISEL = "chisel",
            MODID_AVARITIA = "avaritia",
            MODID_AVAADDON = "avaritiaddons",
            MODID_TC = "thaumcraft",
            MODID_TE = "thaumicenergistics",
            MODID_CT = "crafttweaker";

    public static int ae2VoltageTier = voltageTier(AEConfigHolder.voltageTier) ? AEConfigHolder.voltageTier : 3,
            eioVoltageTier = voltageTier(EnderIOConfigHolder.voltageTier) ? EnderIOConfigHolder.voltageTier : 3,
            dedaVoltageTier = voltageTier(DEDAConfigHolder.voltageTier) ? DEDAConfigHolder.voltageTier : 6;

    public static boolean isModLoadedDEDA() {
        return Loader.isModLoaded(MODID_DE) && Loader.isModLoaded(MODID_DA);
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
