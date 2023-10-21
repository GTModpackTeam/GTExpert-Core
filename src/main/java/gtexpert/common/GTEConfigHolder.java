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

        @Config.Comment({ "Making Planks even more difficult.",
                "CEu's nerfWoodCrafting to true to reflect.", "Default: false" })
        public boolean moreNerfPlankCrafting = false;

        @Config.Comment({ "Making Sticks even more difficult.",
                "CEu's harderRods to true to reflect.", "Default: false" })
        public boolean moreNerfStickCrafting = false;

        @Config.Comment({ "Change to a recipe using Assembly Line.",
                "CEu's enableHighTierSolars to true to reflect.", "Default: false" })
        public boolean hardSolarPanel = false;

        @Config.Comment({ "Raising Terracotta Grinding from ULV to MV.", "Default: false" })
        public boolean nerfTerracottaCrafting = false;

        @Config.Comment({ "Add a cover available in the ULV age.", "Default: false" })
        public boolean enablePrimitiveCovers = false;

        @Config.Comment({ "Recipe type Options: false (2x2 crafting), true (3x3 crafting).", "Default: false" })
        public boolean hardPrimitiveCovers = false;
    }

    public static class AE2Integration {

        @Config.Comment({ "The voltage at which AE can be started.",
                "The material is also adjusted to each voltage.", "Default: 1 (LV)" })
        @Config.RangeInt(min = 1, max = 14)
        public int voltageTier = 1;

        @Config.Comment({ "Change AE swords, axes, etc. to GT recipe standards.",
                "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
        public boolean hardToolRecipes = false;

        @Config.Comment({ "Integrate Printed Silicon and various Circuit creation molds.", "Default: false" })
        public boolean moveSteelShape = false;
    }

    public static class EIOIntegration {

        @Config.Comment({ "Change EIO swords, axes, armor, etc. to GT recipe standards.",
                "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
        public boolean hardToolArmorRecipes = false;

        @Config.Comment({ "Add Shapeless Recipe in CoreMod Machines and EIO Machines.",
                "This change adds a recipe for equivalent exchange of HV machines and EIO machines", "Default: false" })
        public boolean addShapelessRecipeMachines = false;
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
