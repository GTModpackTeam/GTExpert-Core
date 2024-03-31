package gtexpert.integration.ffm.recipes.machines;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gtexpert.integration.ffm.FFMModule.removeCarpenterRecipe;

import forestry.api.core.ForestryAPI;
import forestry.api.recipes.RecipeManagers;
import forestry.arboriculture.ModuleArboriculture;
import forestry.core.fluids.Fluids;
import forestry.lepidopterology.ModuleLepidopterology;
import gregtech.api.GTValues;
import gregtech.api.capability.FeCompat;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gtexpert.api.util.Mods;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CarpenterLoader {
    public static float energyModifier = ForestryAPI.activeMode.getFloatSetting("energy.demand.modifier");
    public static int feToEu = FeCompat.ratio(true);

    public static void initBase() {
        Core();
        Apiculture();
        Climatology();
        Factory();
        Mail();
        Storage();
    }

    public static void initNormal() {
        CoreNormal();
        ApicultureNormal();
        FactoryNormal();
    }

    public static void initHard() {
        CoreHard();
        ApicultureHard();
        ArboricultureHard();
        FactoryHard();
        LepidopterologyHard();
    }

    /**
     * XX : Module Name
     * - : Always loaded
     * Normal : Only loaded when hardForestryRecipe is false
     * Hard : Only loaded when hardForestryRecipe is true
    */
    public static void Core() {
        if (Mods.ForestryFactory.isModLoaded()) {
            removeCarpenterRecipe(Mods.Forestry.getItem("portable_alyzer"));
            removeCarpenterRecipe(Mods.Forestry.getItem("hardened_machine"));
            removeCarpenterRecipe(Mods.Forestry.getItem("kit_pickaxe"));
            removeCarpenterRecipe(Mods.Forestry.getItem("kit_shovel"));
            removeCarpenterRecipe(Mods.Forestry.getItem("wood_pulp"));
            removeCarpenterRecipe(Mods.Forestry.getItem("carton"));
            removeCarpenterRecipe(new ItemStack(Items.PAPER));

            RecipeManagers.carpenterManager.addRecipe(
                    5, Water.getFluid(1000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("carton"),
                    " D ", "D D", " D ", 'D', new UnificationEntry(OrePrefix.dust, Wood).toString());

            RecipeManagers.carpenterManager.addRecipe(
                    20,
                    Mods.Forestry.getItem("carton"), Mods.Forestry.getItem("kit_pickaxe"),
                    "BBB", " S ", " S ",
                    'B', new UnificationEntry(OrePrefix.plate, Bronze).toString(),
                    'S', new UnificationEntry(OrePrefix.stick, Wood).toString());

            RecipeManagers.carpenterManager.addRecipe(20,
                    Mods.Forestry.getItem("carton"), Mods.Forestry.getItem("kit_shovel"),
                    " B ", " S ", " S ",
                    'B', new UnificationEntry(OrePrefix.plate, Bronze).toString(),
                    'S', new UnificationEntry(OrePrefix.stick, Wood).toString());

            // Hardened Casing
            RecipeManagers.carpenterManager.addRecipe(
                    75, Water.getFluid(5000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("hardened_machine"),
                    "# #", " Y ", "# #",
                    '#', "plateDiamond",
                    'Y', Mods.Forestry.getItem("sturdy_machine"));
        } else {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("portable_alyzer"));
        }
        // Impregnated Casing
        ASSEMBLER_RECIPES.recipeBuilder()
                .input("logWood", 8)
                .fluidInputs(SeedOil.getFluid(250))
                .circuitMeta(1)
                .outputs(Mods.Forestry.getItem("impregnated_casing"))
                .EUt(50).duration(timeCarpenter(50)).buildAndRegister();

        // Escritoire
        ASSEMBLER_RECIPES.recipeBuilder()
                .input("plankWood", 6)
                .fluidInputs(SeedOil.getFluid(500))
                .circuitMeta(10)
                .outputs(Mods.Forestry.getItem("escritoire"))
                .EUt(50).duration(timeCarpenter(50)).buildAndRegister();

        // Impregnated Stick
        ASSEMBLER_RECIPES.recipeBuilder()
                .input("logWood", 2)
                .fluidInputs(SeedOil.getFluid(100))
                .circuitMeta(11)
                .outputs(Mods.Forestry.getItem("oak_stick"))
                .EUt(10).duration(timeCarpenter(10)).buildAndRegister();

        // Bog Earth
        MIXER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.DIRT, 4, GTValues.W))
                .input("sand", 4)
                .inputs(Mods.Forestry.getItem("mulch"))
                .fluidInputs(Water.getFluid(1000))
                .circuitMeta(2)
                .outputs(Mods.Forestry.getItem("bog_earth", 8))
                .EUt(5).duration(timeCarpenter(5)).buildAndRegister();

        // Hardened Casing
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, Diamond, 4)
                .inputs(Mods.Forestry.getItem("sturdy_machine"))
                .fluidInputs(Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("hardened_machine"))
                .EUt(75).duration(timeCarpenter(75)).buildAndRegister();

        // Ender Pearl
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("crafting_material", 5, 1))
                .outputs(new ItemStack(Items.ENDER_PEARL))
                .EUt(100).duration(timeCarpenter(100)).buildAndRegister();

        // Woven Silk
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("crafting_material", 9, 2))
                .fluidInputs(Water.getFluid(500))
                .outputs(Mods.Forestry.getItem("crafting_material", 1, 3))
                .EUt(10).duration(timeCarpenter(10)).buildAndRegister();

        // Tool
        PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("bronze_pickaxe"))
                .inputs(Mods.Forestry.getItem("carton"))
                .outputs(Mods.Forestry.getItem("kit_pickaxe"))
                .EUt(20).duration(timeCarpenter(20)).buildAndRegister();

        PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("bronze_shovel"))
                .inputs(Mods.Forestry.getItem("carton"))
                .outputs(Mods.Forestry.getItem("kit_shovel"))
                .EUt(20).duration(timeCarpenter(20)).buildAndRegister();

        ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("broken_bronze_pickaxe"))
                .fluidInputs(Oxygen.getFluid(100))
                .output(OrePrefix.ingot, Bronze, 2)
                .EUt(5).duration(timeCarpenter(5)).buildAndRegister();

        ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("broken_bronze_shovel"))
                .fluidInputs(Oxygen.getFluid(100))
                .output(OrePrefix.ingot, Bronze)
                .EUt(5).duration(timeCarpenter(5)).buildAndRegister();

        // Carton
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Wood, 4)
                .fluidInputs(Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("carton"))
                .EUt(5).duration(timeCarpenter(5)).buildAndRegister();
    }

    public static void CoreNormal() {
        // Portable Analyzer
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, Tin, 2)
                .input("circuitLv")
                .input(OrePrefix.plate, Diamond)
                .input("paneGlass", 2)
                .input(OrePrefix.plate, RedAlloy, 2)
                .fluidInputs(Water.getFluid(2000))
                .outputs(Mods.Forestry.getItem("portable_alyzer"))
                .EUt(50).duration(timeCarpenter(50 * 4)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {
            RecipeManagers.carpenterManager.addRecipe(
                    50, Water.getFluid(2000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("portable_alyzer"),
                    "TGT", "CGC", "RDR",
                    'T', new UnificationEntry(OrePrefix.plate, Tin).toString(),
                    'G', "paneGlass",
                    'C', "circuitLv",
                    'R', new UnificationEntry(OrePrefix.plate, RedAlloy).toString(),
                    'D', new UnificationEntry(OrePrefix.plate, Diamond).toString());
        }
    }

    public static void CoreHard() {
        // Portable Analyzer
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, Tin, 2)
                .input("circuitMv")
                .input(OrePrefix.plate, Diamond)
                .input("paneGlass", 2)
                .input(OrePrefix.plate, RedAlloy, 2)
                .fluidInputs(Water.getFluid(2000))
                .outputs(Mods.Forestry.getItem("portable_alyzer"))
                .EUt(50).duration(timeCarpenter(50 * 4)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {
            RecipeManagers.carpenterManager.addRecipe(
                    50, Water.getFluid(2000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("portable_alyzer"),
                    "TGT", "CGC", "RDR",
                    'T', new UnificationEntry(OrePrefix.plate, Tin).toString(),
                    'G', "paneGlass",
                    'C', "circuitMv",
                    'R', new UnificationEntry(OrePrefix.plate, RedAlloy).toString(),
                    'D', new UnificationEntry(OrePrefix.plate, Diamond).toString());
        }
    }

    public static void Apiculture() {
        if (!Mods.ForestryApiculture.isModLoaded()) return;
        // Scented Paneling
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("royal_jelly"))
                .input("plankWood", 3)
                .inputs(Mods.Forestry.getItem("beeswax", 2))
                .inputs(Mods.Forestry.getItem("pollen"))
                .fluidInputs(Fluids.FOR_HONEY.getFluid(500))
                .outputs(Mods.Forestry.getItem("crafting_material", 1, 6))
                .EUt(50).duration(timeCarpenter(50)).buildAndRegister();

        // Candle
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.STRING)
                .inputs(Mods.Forestry.getItem("beeswax", 6))
                .fluidInputs(Water.getFluid(600))
                .outputs(Mods.Forestry.getItem("candle", 24))
                .EUt(30).duration(timeCarpenter(30)).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("crafting_material", 1, 2))
                .inputs(Mods.Forestry.getItem("beeswax", 6))
                .fluidInputs(Water.getFluid(200))
                .outputs(Mods.Forestry.getItem("candle", 6))
                .EUt(10).duration(timeCarpenter(10)).buildAndRegister();

        // Iodine Capsule
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("honey_drop", 2))
                .inputs(GTUtility.copy(4, ModuleArboriculture.getItems().pollenFertile.getWildcard()))
                .inputs(Mods.Forestry.getItem("can"))
                .input(OrePrefix.dust, Gunpowder, 2)
                .fluidInputs(Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("iodine_capsule"))
                .EUt(5).duration(timeCarpenter(5)).buildAndRegister();

        // Dissipation Charge
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("honeydew", 2))
                .inputs(Mods.Forestry.getItem("royal_jelly", 4))
                .inputs(Mods.Forestry.getItem("can"))
                .input(OrePrefix.dust, Gunpowder, 2)
                .fluidInputs(Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("crafting_material", 1, 4))
                .EUt(5).duration(timeCarpenter(5)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {}
    }

    public static void ApicultureNormal() {
        if (!Mods.ForestryApiculture.isModLoaded()) return;
        if (Mods.ForestryFactory.isModLoaded()) {}
    }

    public static void ApicultureHard() {
        if (!Mods.ForestryApiculture.isModLoaded()) return;
        // Apiarist's Chest
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bee_chest"));
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.screw, Steel, 2)
                .input("blockGlass")
                .input("beeComb", 5)
                .input("chestWood")
                .fluidInputs(Fluids.FOR_HONEY.getFluid(1000))
                .outputs(Mods.Forestry.getItem("bee_chest"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();

        // Apiariy
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("apiary"));
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("bee_house", 2))
                .inputs(Mods.Forestry.getItem("frame_impregnated"))
                .input(OrePrefix.screw, Steel, 2)
                .input("slabWood", 2)
                .input("beeComb")
                .input("fenceWood")
                .fluidInputs(SeedOil.getFluid(1000))
                .outputs(Mods.Forestry.getItem("apiary"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();

        // Alvearies
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_swarmer"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_fan"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_heater"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_hygro"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_stabiliser"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_sieve"));

        // Swarmer
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 5))
                .inputs(Mods.Forestry.getItem("frame_impregnated"))
                .inputs(Mods.Forestry.getItem("royal_jelly"))
                .input(OrePrefix.foil, RoseGold, 2)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.swarmer"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();

        // Fan
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 11))
                .input(Blocks.IRON_BARS, 2)
                .input(OrePrefix.rotor, Bronze)
                .input(MetaItems.ELECTRIC_MOTOR_MV, 2)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.fan"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();

        // Heater
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 7))
                .input(Blocks.IRON_BARS)
                .input(MetaItems.ELECTRIC_MOTOR_MV)
                .input(OrePrefix.wireGtQuadruple, Cupronickel, 3)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.heater"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();

        // Hygroregulator
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 6))
                .input("circuitMv")
                .input(MetaTileEntities.BRONZE_DRUM, 2)
                .input(OrePrefix.pipeNormalFluid, StainlessSteel)
                .input(OrePrefix.plate, RedAlloy)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.hygro"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();

        // Stabiliser
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 4))
                .inputs(Mods.Forestry.getItem("royal_jelly"))
                .input(MetaItems.ITEM_FILTER, 2)
                .input(OrePrefix.plate, CertusQuartz, 2)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.stabiliser"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();

        // Sieve
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 9))
                .inputs(Mods.Forestry.getItem("crafting_material", 3, 4))
                .inputs(GTUtility.copy(2, ModuleArboriculture.getItems().pollenFertile.getWildcard()))
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.sieve"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();
        
        if (Mods.ForestryFactory.isModLoaded()) {
            // Apiarist's Chest
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(1000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("bee_chest"),
                    "SGS", "BCB", "BBB",
                    'S', new UnificationEntry(OrePrefix.screw, Steel).toString(),
                    'G', "blockGlass",
                    'C', "chestWood",
                    'B', "beeComb");

            // Apiariy
            RecipeManagers.carpenterManager.addRecipe(
                    60, SeedOil.getFluid(1000), Mods.Forestry.getItem("frame_impregnated"), Mods.Forestry.getItem("apiary"),
                    "sws", "aca", "fwf",
                    's', "screwSteel",
                    'w', "slabWood",
                    'a', Mods.Forestry.getItem("bee_house"),
                    'c', "beeComb",
                    'f', "fenceWood");

            // Alvearies
            // Swarmer
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(5000),
                    Mods.Forestry.getItem("alveary.plain"), Mods.Forestry.getItem("alveary.swarmer"),
                    "TGT", "RFR", "TGT",
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1,5 ),
                    'G', new UnificationEntry(OrePrefix.foil, RoseGold).toString(),
                    'R', Mods.Forestry.getItem("royal_jelly"),
                    'F', Mods.Forestry.getItem("frame_impregnated"));

            // Fan
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(5000),
                    Mods.Forestry.getItem("alveary.plain"), Mods.Forestry.getItem("alveary.fan"),
                    "TBT", "MRM", "TBT",
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1, 11),
                    'B', Blocks.IRON_BARS,
                    'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm(),
                    'R', new UnificationEntry(OrePrefix.rotor, Bronze).toString());

            // Heater
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(5000),
                    Mods.Forestry.getItem("alveary.plain"), Mods.Forestry.getItem("alveary.heater"),
                    "TBT", "CCC", "TMT",
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1, 7),
                    'B', Blocks.IRON_BARS,
                    'C', new UnificationEntry(OrePrefix.wireGtQuadruple, Cupronickel).toString(),
                    'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm());

            // Hygroregulator
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(5000),
                    Mods.Forestry.getItem("alveary.plain"), Mods.Forestry.getItem("alveary.hygro"),
                    "TCT", "DPD", "TRT",
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1, 6),
                    'C', "circuitMv",
                    'D', MetaTileEntities.BRONZE_DRUM.getStackForm(),
                    'P', new UnificationEntry(OrePrefix.pipeNormalFluid, StainlessSteel).toString(),
                    'R', new UnificationEntry(OrePrefix.plate, RedAlloy).toString());

            // Stabiliser
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(5000),
                    Mods.Forestry.getItem("alveary.plain"), Mods.Forestry.getItem("alveary.stabiliser"),
                    "TQT", "FJF", "TQT",
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1, 4),
                    'Q', new UnificationEntry(OrePrefix.plate, CertusQuartz).toString(),
                    'F', MetaItems.ITEM_FILTER.getStackForm(),
                    'J', Mods.Forestry.getItem("royal_jelly"));

            // Sieve
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(5000),
                    Mods.Forestry.getItem("alveary.plain"), Mods.Forestry.getItem("alveary.sieve"),
                    "TPT", "CCC", "TPT",
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1, 9),
                    'C', Mods.Forestry.getItem("crafting_material", 1, 4),
                    'P', ModuleArboriculture.getItems().pollenFertile.getWildcard());
        }
    }

    public static void ArboricultureHard() {
        if (!Mods.ForestryArboriculture.isModLoaded()) return;
        // Arborist's Chest
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("tree_chest"));
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.screw, Steel, 2)
                .input("blockGlass")
                .input("treeSapling", 5)
                .input("chestWood")
                .fluidInputs(SeedOil.getFluid(1000))
                .outputs(Mods.Forestry.getItem("tree_chest"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {
            RecipeManagers.carpenterManager.addRecipe(
                    60, SeedOil.getFluid(1000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("tree_chest"),
                    "SGS", "TCT", "TTT",
                    'S', new UnificationEntry(OrePrefix.screw, Steel).toString(),
                    'G', "blockGlass",
                    'C', "chestWood",
                    'T', "treeSapling");
        }
    }

    public static void Climatology() {
        if (!Mods.ForestryClimatology.isModLoaded()) return;
        if (Mods.ForestryFactory.isModLoaded()) {
            removeCarpenterRecipe(Mods.Forestry.getItem("habitat_screen"));

            RecipeManagers.carpenterManager.addRecipe(
                    100, Water.getFluid(2000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("habitat_screen"),
                    "BPB", "BPB", "GDG",
                    'B', new UnificationEntry(OrePrefix.plate, Bronze).toString(),
                    'P', "paneGlass",
                    'G', new UnificationEntry(OrePrefix.gear, Bronze).toString(),
                    'D', new UnificationEntry(OrePrefix.plate, Diamond).toString());
        } else {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("habitat_screen"));
        }

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, Bronze, 4)
                .input("paneGlass", 2)
                .input(OrePrefix.gear, Bronze, 2)
                .input(OrePrefix.plate, Diamond)
                .fluidInputs(Water.getFluid(2000))
                .outputs(Mods.Forestry.getItem("habitat_screen"))
                .EUt(100).duration(timeCarpenter(100)).buildAndRegister();

    }

    public static void Factory() {
        if (!Mods.ForestryFactory.isModLoaded()) return;
        removeCarpenterRecipe(Mods.Forestry.getItem("chipsets", 1, 0));
        removeCarpenterRecipe(Mods.Forestry.getItem("chipsets", 1, 1));
        removeCarpenterRecipe(Mods.Forestry.getItem("chipsets", 1, 2));
        removeCarpenterRecipe(Mods.Forestry.getItem("chipsets", 1, 2));

        removeCarpenterRecipe(Mods.Forestry.getItem("soldering_iron"));

        RecipeManagers.carpenterManager.addRecipe(
                40, Water.getFluid(1000),
                ItemStack.EMPTY, Mods.Forestry.getItem("soldering_iron"),
                " I ", "I I", "  B",
                'I', new UnificationEntry(OrePrefix.plate, Iron).toString(),
                'B', new UnificationEntry(OrePrefix.plate, Bronze).toString());

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, Iron, 3)
                .input(OrePrefix.plate, Bronze)
                .fluidInputs(Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("soldering_iron"))
                .EUt(40).duration(timeCarpenter(40)).buildAndRegister();
    }

    public static void FactoryNormal() {
        if (!Mods.ForestryFactory.isModLoaded()) return;
        registerCircuitRecipeNormal(Mods.Forestry.getItem("chipsets", 1, 0), 20, Iron, MetaItems.COATED_BOARD);
        registerCircuitRecipeNormal(Mods.Forestry.getItem("chipsets", 1, 1), 40, Bronze, MetaItems.COATED_BOARD);
        registerCircuitRecipeNormal(Mods.Forestry.getItem("chipsets", 1, 2), 80, Steel, MetaItems.PHENOLIC_BOARD);
        registerCircuitRecipeNormal(Mods.Forestry.getItem("chipsets", 1, 3), 160, Electrum, MetaItems.PHENOLIC_BOARD);
    }

    public static void FactoryHard() {
        if (!Mods.ForestryFactory.isModLoaded()) return;
        registerCircuitRecipeHard(Mods.Forestry.getItem("chipsets", 1, 0), 20, Iron, MetaItems.COATED_BOARD, "circuitUlv");
        registerCircuitRecipeHard(Mods.Forestry.getItem("chipsets", 1, 1), 40, Bronze, MetaItems.COATED_BOARD, "circuitLv");
        registerCircuitRecipeHard(Mods.Forestry.getItem("chipsets", 1, 2), 80, Steel, MetaItems.PHENOLIC_BOARD, "circuitMv");
        registerCircuitRecipeHard(Mods.Forestry.getItem("chipsets", 1, 3), 160, Electrum, MetaItems.PHENOLIC_BOARD, "circuitHv");
    }

    public static void Mail() {
        if (!Mods.ForestryMail.isModLoaded()) return;

        Material[] matStamp = {Apatite, Copper, Tin, Gold};

        for (int i = 0; i < matStamp.length; i++) {
            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(matStamp[i] == Apatite ? OrePrefix.gem : OrePrefix.plate, matStamp[i], 3)
                    .inputs(new ItemStack(Items.PAPER, 3))
                    .fluidInputs(SeedOil.getFluid(300))
                    .outputs(Mods.Forestry.getItem("stamps", 1, i))
                    .EUt(10).duration(timeCarpenter(10)).buildAndRegister();
        }
    }

    public static void LepidopterologyHard() {
        if (!Mods.ForestryLepidopterology.isModLoaded()) return;
        // Lepidoptetist's Chest
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("butterfly_chest"));
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.screw, Steel, 2)
                .input("blockGlass")
                .inputs(GTUtility.copy(5, ModuleLepidopterology.getItems().butterflyGE.getWildcard()))
                .input("chestWood")
                .fluidInputs(SeedOil.getFluid(1000))
                .outputs(Mods.Forestry.getItem("butterfly_chest"))
                .EUt(60).duration(timeCarpenter(60)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {
            RecipeManagers.carpenterManager.addRecipe(
                    60, SeedOil.getFluid(1000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("butterfly_chest"),
                    "SGS", "BCB", "BBB",
                    'S', new UnificationEntry(OrePrefix.screw, Steel).toString(),
                    'G', "blockGlass",
                    'C', "chestWood",
                    'B', ModuleLepidopterology.getItems().butterflyGE.getWildcard());
        }
    }

    public static void Storage() {
        if (Mods.ForestryBackpacks.isModLoaded()) {
            ItemStack[] backStack = { Mods.Forestry.getItem("miner_bag"),
                    Mods.Forestry.getItem("digger_bag"),
                    Mods.Forestry.getItem("forester_bag"),
                    Mods.Forestry.getItem("hunter_bag"),
                    Mods.Forestry.getItem("adventurer_bag"),
                    Mods.Forestry.getItem("builder_bag")};
            ItemStack[] backStackT2 = { Mods.Forestry.getItem("miner_bag_t2"),
                    Mods.Forestry.getItem("digger_bag_t2"),
                    Mods.Forestry.getItem("forester_bag_t2"),
                    Mods.Forestry.getItem("hunter_bag_t2"),
                    Mods.Forestry.getItem("adventurer_bag_t2"),
                    Mods.Forestry.getItem("builder_bag_t2")};

            for (int i =0; i < backStack.length; i++) {
                NBTTagCompound tag = backStack[1].getTagCompound();
                backStackT2[i].setTagCompound(tag);

                ASSEMBLER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, Diamond)
                        .inputs(backStack[i])
                        .inputs(Mods.Forestry.getItem("crafting_material", 7, 3))
                        .fluidInputs(Water.getFluid(1000))
                        .outputs(backStackT2[i])
                        .EUt(200).duration(timeCarpenter(200)).buildAndRegister();
            }


        }
        if (Mods.ForestryCrate.isModLoaded()) {
            ASSEMBLER_RECIPES.recipeBuilder()
                    .input("logWood", 4)
                    .fluidInputs(Water.getFluid(1000))
                    .circuitMeta(10)
                    .outputs(Mods.Forestry.getItem("crate"))
                    .EUt(20).duration(timeCarpenter(20)).buildAndRegister();
        }
    }

    private static void registerCircuitRecipeNormal (ItemStack output, int EUt,
                                              Material material,
                                              MetaItem.MetaValueItem center) {
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, material, 6)
                .input(OrePrefix.plate, RedAlloy, 2)
                .input(center)
                .outputs(output)
                .EUt(EUt).duration(timeCarpenter(EUt)).buildAndRegister();

        RecipeManagers.carpenterManager.addRecipe(
                EUt, SolderingAlloy.getFluid(72),
                ItemStack.EMPTY, output,
                "STS", "SCS", "STS",
                'S', new UnificationEntry(OrePrefix.plate, material).toString(),
                'T', new UnificationEntry(OrePrefix.plate, RedAlloy).toString(),
                'C', center.getStackForm()
        );
        RecipeManagers.carpenterManager.addRecipe(
                EUt, Tin.getFluid(144),
                ItemStack.EMPTY, output,
                "STS", "SCS", "STS",
                'S', new UnificationEntry(OrePrefix.plate, material).toString(),
                'T', new UnificationEntry(OrePrefix.plate, RedAlloy).toString(),
                'C', center.getStackForm()
        );
    }

    private static void registerCircuitRecipeHard(ItemStack output, int EUt, Material material,
                                           MetaItem.MetaValueItem broad, String circuit) {
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.screw, material, 4)
                .input(OrePrefix.foil, material, 2)
                .input(OrePrefix.wireFine, material)
                .input(circuit, 2)
                .input(broad)
                .outputs(output)
                .EUt(EUt).duration(timeCarpenter(EUt)).buildAndRegister();

        RecipeManagers.carpenterManager.addRecipe(
                EUt, SolderingAlloy.getFluid(72),
                broad.getStackForm(), output,
                "sfs", "cwc", "sfs",
                's', new UnificationEntry(OrePrefix.screw, material).toString(),
                'f', new UnificationEntry(OrePrefix.foil, material).toString(),
                'c', circuit,
                'w', new UnificationEntry(OrePrefix.wireFine, material).toString());
        RecipeManagers.carpenterManager.addRecipe(
                EUt, Tin.getFluid(72),
                broad.getStackForm(), output,
                "sfs", "cwc", "sfs",
                's', new UnificationEntry(OrePrefix.screw, material).toString(),
                'f', new UnificationEntry(OrePrefix.foil, material).toString(),
                'c', circuit,
                'w', new UnificationEntry(OrePrefix.wireFine, material).toString());
    }

    public static int timeCarpenter(int EUt) {
        return Math.round(EUt * 204 * energyModifier / (100 * feToEu));
    }



}
