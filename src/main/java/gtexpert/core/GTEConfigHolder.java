package gtexpert.core;

import net.minecraftforge.common.config.Config;

import gtexpert.api.GTEValues;

@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/" + "core",
        category = "GTExpert-Core")
public class GTEConfigHolder {

    @Config.Name("GTExpert-Core Options")
    @Config.RequiresMcRestart
    public static final ModpackFlag gteFlag = new ModpackFlag();

    @Config.Name("Gregtech Override")
    @Config.RequiresMcRestart
    public static final GregtechOverride ceuOverride = new GregtechOverride();

    public static class ModpackFlag {

        @Config.Comment({ "Activate changes in the replacement schedule.",
                "1. When enabled, the following structure and mte name changes: ",
                "Large Cracking Unit",
                "Default: false" })
        public boolean featureFlag = false;

        @Config.Comment({ "Item name to be CEu standard instead of Primitive",
                "Options: true (ULV), false (Primitive)",
                "Default: false" })
        public boolean componentsName = false;

        @Config.Comment({
                "Recipe Type. Options: none (no generated recipes), easy (2x2 crafting), normal (3x3 crafting).",
                "Default: easy" })
        public String componentsRecipeType = "easy";

        @Config.Comment({
                "Enable the new steam machines.",
                "Steam Mixer, Steam Assembler, Steam Circuit Assembler",
                "Default: false" })
        public boolean steamNewMachine = false;

        @Config.Comment({
                "Recipe Type. Options: none (no generated recipes), easy (2x2 crafting(WIP)), normal (3x3 crafting).",
                "The steamNewMachine must also be enabled.", "Default: normal" })
        public String steamRecipeType = "normal";

        @Config.Name("NanoBow Options")
        public GTEConfigHolder.NanoBow nanoBow = new GTEConfigHolder.NanoBow();

        @Config.Name("QuarkBow Options")
        public GTEConfigHolder.QuarkBow quarkBow = new GTEConfigHolder.QuarkBow();
    }

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
    }

    public static class NanoBow {

        @Config.RangeDouble(
                            min = 0.0,
                            max = 100.0)
        @Config.Comment({ "The additional damage added when the NanoBow is powered.", "Default: 20.0" })
        public double nanoBowDamageBoost = 20.0;
        @Config.RangeDouble(
                            min = 0.0,
                            max = 100.0)
        @Config.Comment({ "The base damage of the NanoBow.", "Default: 5.0" })
        public double nanoBowBaseDamage = 5.0;
        @Config.Comment({ "Should Zombies spawn with charged, active NanoBows on hard difficulty?", "Default: true" })
        public boolean zombieSpawnWithSabers = true;
        @Config.RangeInt(
                         min = 1,
                         max = 512)
        @Config.Comment({ "The EU/t consumption of the NanoBow.", "Default: 64" })
        public int energyConsumption = 64;

        public NanoBow() {}
    }

    public static class QuarkBow {

        @Config.RangeDouble(
                            min = 0.0,
                            max = 100.0)
        @Config.Comment({ "The additional damage added when the QuarkBow is powered.", "Default: 40.0" })
        public double quarkBowDamageBoost = 40.0;
        @Config.RangeDouble(
                            min = 0.0,
                            max = 100.0)
        @Config.Comment({ "The base damage of the QuarkBow.", "Default: 10.0" })
        public double quarkBowBaseDamage = 10.0;
        @Config.Comment({ "Should Zombies spawn with charged, active QuarkBows on hard difficulty?", "Default: true" })
        public boolean zombieSpawnWithSabers = true;
        @Config.RangeInt(
                         min = 1,
                         max = 512)
        @Config.Comment({ "The EU/t consumption of the QuarkBow.", "Default: 256" })
        public int energyConsumption = 256;

        public QuarkBow() {}
    }
}
