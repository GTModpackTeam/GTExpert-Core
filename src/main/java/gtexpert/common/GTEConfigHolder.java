package gtexpert.common;

import gtexpert.api.GTEValues;

import net.minecraftforge.common.config.Config;

import com.cleanroommc.configanytime.ConfigAnytime;

@Config(modid = GTEValues.MODID)
public class GTEConfigHolder {

    @Config.Name("Gregtech Override")
    @Config.RequiresMcRestart
    public static GregtechOverride ceuOverride = new GregtechOverride();

    @Config.Name("AE2 Integration")
    @Config.RequiresMcRestart
    public static AE2Integration ae2Integration = new AE2Integration();

    public static class GregtechOverride {

        @Config.Comment({ "Making Planks and Sticks even more difficult.",
                "CEu's nerfWoodCrafting to true to reflect.", "Default: false" })
        public boolean moreNerfWoodCrafting = false;

        @Config.Comment({ "Change to a recipe using Assembly Line.",
                "CEu's enableHighTierSolars to true to reflect.", "Default: false" })
        public boolean hardSolarPanel = false;
    }

    public static class AE2Integration {

        @Config.Comment({ "The voltage at which AE can be started.",
                "The material is also adjusted to each voltage.", "Default: 1 (LV)" })
        @Config.RangeInt(min = 1, max = 14)
        public int voltageTier = 1;
    }

    // Register the config holder
    static {
        ConfigAnytime.register(GTEConfigHolder.class);
    }
}
