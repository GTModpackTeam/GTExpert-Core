package gtexpert.api;

import net.minecraftforge.fml.common.Loader;

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
            MODID_EXCPU = "extracpus",
            MODID_DE = "draconicevolution",
            MODID_DA = "draconicadditions",
            MODID_CHISEL = "chisel",
            MODID_AVARITIA = "avaritia",
            MODID_AVAADDON = "avaritiaddons";

    public static boolean isModLoadedDEDA() {
        return Loader.isModLoaded(MODID_DE) && Loader.isModLoaded(MODID_DA);
    }
}
