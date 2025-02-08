package com.github.gtexpert.core.common;

import net.minecraftforge.common.config.Config;

import com.github.gtexpert.core.api.GTEValues;

@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/" + GTEValues.MODID,
        category = "GTExpert-Core")
public class GTEConfigHolder {

    @Config.Name("GTExpert-Core Options")
    @Config.RequiresMcRestart
    public static final GTEFlag gteFlag = new GTEFlag();

    @Config.Name("GTExpert-Core Feature Options")
    @Config.RequiresMcRestart
    public static final GTEFeatureFlag gteFeatureFlag = new GTEFeatureFlag();

    @Config.Name("Gregtech Override")
    @Config.RequiresMcRestart
    public static final GregtechOverride ceuOverride = new GregtechOverride();

    public static class GTEFlag {

        @Config.Comment({ "Item name to be CEu standard instead of Primitive",
                "If you want to use Filostorm's ULV Covers, set it to none.",
                "Options: ulv, primitive, none",
                "Default: primitive" })
        public String componentsName = "primitive";

        @Config.Comment({
                "Recipe Type. Options: none (no generated recipes), easy (2x2 crafting), normal (3x3 crafting).",
                "Default: easy" })
        public String componentsRecipeType = "easy";

        @Config.Comment({
                "1. When enabled, the following recipes will be changed to Peaceful difficulty: ",
                "Nether Star Dust, Skeleton Skull, Wither Skeleton Skull, Zombie Head, Creeper Head, Enderman Head",
                "Default: false" })
        public boolean peacefulFlag = false;

        @Config.Comment({ "Add to a creative machine recipe.",
                "When enabled, Recipes will be added to the following machines: ",
                "1. GTCEu: Data Hatch, Energy Unit, Quantum Tank, Quantum Chest",
                "2. AE2UEL: Energy Cell",
                "3. EIO: Capacitor Bank",
                "Default: true" })
        public boolean addCreativeRecipe = true;
    }

    public static class GTEFeatureFlag {

        @Config.Comment({ "Activate changes in the replacement schedule.",
                "1. When enabled, the following structure and mte name changes: ",
                "Large Cracking Unit",
                "1. When enabled, the following mte name changes: ",
                "Advanced Gas Collector -> Large Gas Collector",
                "Default: false" })
        public boolean migrationMachine = false;

        @Config.Comment({ "Activate machines under development.",
                "Default: false" })
        public boolean previewMachines = false;

        @Config.Comment({ "Activate items under development.",
                "Default: false" })
        public boolean previewItems = false;
    }

    public static class GregtechOverride {

        @Config.Comment({ "Change to a recipe using Assembly Line.",
                "CEu's enableHighTierSolars to true to reflect.", "Default: false" })
        public boolean hardSolarPanel = false;

        @Config.Comment({ "Raising Terracotta Grinding from ULV to MV.", "Default: false" })
        public boolean nerfTerracottaCrafting = false;

        @Config.Comment({ "Disable auto eat function of QuarkTech helmet", "Default: true" })
        public boolean disableHelmetAutoEat = true;
    }
}
