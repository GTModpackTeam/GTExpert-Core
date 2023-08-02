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

    @Config.Name("EnderIO Integration")
    @Config.RequiresMcRestart
    public static EIOIntegration eioIntegration = new EIOIntegration();

    @Config.Name("Chisel Integration")
    @Config.RequiresMcRestart
    public static ChiselIntegration chiselIntegration = new ChiselIntegration();

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

        @Config.Comment({ "Change AE swords, axes, etc. to GT recipe standards.",
                "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
        public boolean hardToolRecipes = false;
    }

    public static class EIOIntegration {

        @Config.Comment({ "Change EIO swords, axes, armor, etc. to GT recipe standards.",
                "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
        public boolean hardToolArmorRecipes = false;
    }

    public static class ChiselIntegration {

        @Config.Comment({ "Change Chisel recipes to GT recipe standards.",
                "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
        public boolean hardToolRecipes = false;

        @Config.Comment({ "Change LED for Project:RED recipes to GT recipe standards.", "Default: false" })
        public boolean hardLedRecipes = false;
    }

    // Register the config holder
    static {
        ConfigAnytime.register(GTEConfigHolder.class);
    }
}
