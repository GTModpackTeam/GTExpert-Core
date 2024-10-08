package gtexpert.common;

import net.minecraftforge.common.config.Config;

import gtexpert.api.GTEValues;

@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/" + GTEValues.MODID,
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
                "2. When enabled, the recipe of the following items will be changed: ",
                "Logic Processor, Calc Processor, Engineer Processor",
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

        @Config.Comment({
                "1. When enabled, the following recipes will be changed to Peaceful difficulty: ",
                "Nether Star Dust, Skeleton Skull, Wither Skeleton Skull, Zombie Head, Creeper Head, Enderman Head",
                "Default: false" })
        public boolean peacefulFlag = false;
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
