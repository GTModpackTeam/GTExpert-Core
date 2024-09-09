package gtexpert.common;

import net.minecraftforge.common.config.Config;

import gtexpert.api.GTEValues;

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

        @Config.Comment({
                "1. When enabled, the following recipes will be changed to Peaceful difficulty: ",
                "Nether Star Dust, Skeleton Skull, Wither Skeleton Skull, Zombie Head, Creeper Head, Enderman Head",
                "Default: false" })
        public boolean peacefulFlag = false;
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

        @Config.Comment({ "Items and machines are re-numbered.",
                "Default: false" })
        public boolean newId = false;
    }

    public static class GregtechOverride {

        @Config.Comment({ "Making Planks even more difficult.",
                "CEu's hardWoodRecipes & nerfWoodCrafting to true to reflect.", "Default: false" })
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
}
