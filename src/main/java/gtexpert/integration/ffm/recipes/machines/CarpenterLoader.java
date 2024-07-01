package gtexpert.integration.ffm.recipes.machines;

import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import gtexpert.api.util.GTELog;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.integration.ffm.FFMConfigHolder;
import gtexpert.integration.ffm.FFMUtility;

import forestry.api.circuits.ICircuit;
import forestry.api.recipes.RecipeManagers;
import forestry.arboriculture.ModuleArboriculture;
import forestry.core.circuits.EnumCircuitBoardType;
import forestry.core.circuits.ItemCircuitBoard;
import forestry.core.fluids.Fluids;
import forestry.factory.recipes.CarpenterRecipeManager;
import forestry.lepidopterology.ModuleLepidopterology;

public class CarpenterLoader {

    public static void initBase() {
        Core();
        Apiculture();
        Climatology();
        Factory();
        Mail();
        Storage();
    }

    public static void initMode() {
        Enum<FFMUtility.recipeMode> recipeMode = FFMUtility.recipeMode.safeValueOf(FFMConfigHolder.gameMode);

        if (recipeMode == FFMUtility.recipeMode.NORMAL) {
            CoreNormal();
            ApicultureNormal();

            // Circuit
            CarpenterLoader.registerCarpenterRecipe(recipeMode, Mods.Forestry.getItem("chipsets", 1),
                    20, Materials.Iron, MetaItems.COATED_BOARD);
            CarpenterLoader.registerCarpenterRecipe(recipeMode, Mods.Forestry.getItem("chipsets", 1, 1),
                    40, Materials.Bronze, MetaItems.COATED_BOARD);
            CarpenterLoader.registerCarpenterRecipe(recipeMode, Mods.Forestry.getItem("chipsets", 1, 2),
                    80, Materials.Steel, MetaItems.PHENOLIC_BOARD);
            CarpenterLoader.registerCarpenterRecipe(recipeMode, Mods.Forestry.getItem("chipsets", 1, 3),
                    160, Materials.Electrum, MetaItems.PHENOLIC_BOARD);

        } else if (recipeMode == FFMUtility.recipeMode.HARD) {
            CoreHard();
            ApicultureHard();
            ArboricultureHard();
            LepidopterologyHard();

            // Circuit
            CarpenterLoader.registerCarpenterRecipe(recipeMode, Mods.Forestry.getItem("chipsets", 1),
                    20, Materials.Iron, MetaItems.COATED_BOARD, GTEUtility.oreDictionaryCircuit(GTValues.ULV));
            CarpenterLoader.registerCarpenterRecipe(recipeMode, Mods.Forestry.getItem("chipsets", 1, 1),
                    40, Materials.Bronze, MetaItems.COATED_BOARD, GTEUtility.oreDictionaryCircuit(GTValues.LV));
            CarpenterLoader.registerCarpenterRecipe(recipeMode, Mods.Forestry.getItem("chipsets", 1, 2),
                    80, Materials.Steel, MetaItems.PHENOLIC_BOARD, GTEUtility.oreDictionaryCircuit(GTValues.MV));
            CarpenterLoader.registerCarpenterRecipe(recipeMode, Mods.Forestry.getItem("chipsets", 1, 3),
                    160, Materials.Electrum, MetaItems.PHENOLIC_BOARD, GTEUtility.oreDictionaryCircuit(GTValues.HV));
        }
    }

    /**
     * XX : Module Name
     * - : Always loaded
     * Normal : Only loaded when hardForestryRecipe is false
     * Hard : Only loaded when hardForestryRecipe is true
     */
    private static void Core() {
        if (Mods.ForestryFactory.isModLoaded()) {
            CarpenterLoader.removeCarpenterRecipe(Mods.Forestry.getItem("portable_alyzer"));
            CarpenterLoader.removeCarpenterRecipe(Mods.Forestry.getItem("hardened_machine"));
            CarpenterLoader.removeCarpenterRecipe(Mods.Forestry.getItem("kit_pickaxe"));
            CarpenterLoader.removeCarpenterRecipe(Mods.Forestry.getItem("kit_shovel"));
            CarpenterLoader.removeCarpenterRecipe(Mods.Forestry.getItem("wood_pulp"));
            CarpenterLoader.removeCarpenterRecipe(Mods.Forestry.getItem("carton"));
            CarpenterLoader.removeCarpenterRecipe(new ItemStack(Items.PAPER));

            RecipeManagers.carpenterManager.addRecipe(
                    5, Materials.Water.getFluid(1000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("carton"),
                    " D ", "D D", " D ", 'D', new UnificationEntry(dust, Materials.Wood).toString());

            RecipeManagers.carpenterManager.addRecipe(
                    20,
                    Mods.Forestry.getItem("carton"), Mods.Forestry.getItem("kit_pickaxe"),
                    " S ", " S ", "PII",
                    'S', new UnificationEntry(stick, Materials.Wood).toString(),
                    'P', new UnificationEntry(plate, Materials.Bronze).toString(),
                    'I', new UnificationEntry(ingot, Materials.Bronze).toString());

            RecipeManagers.carpenterManager.addRecipe(20,
                    Mods.Forestry.getItem("carton"), Mods.Forestry.getItem("kit_shovel"),
                    " S ", " S ", " P ",
                    'S', new UnificationEntry(stick, Materials.Wood).toString(),
                    'P', new UnificationEntry(plate, Materials.Bronze).toString());

            // Hardened Casing
            RecipeManagers.carpenterManager.addRecipe(
                    75, Materials.Water.getFluid(5000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("hardened_machine"),
                    "# #", " Y ", "# #",
                    '#', "plateDiamond",
                    'Y', Mods.Forestry.getItem("sturdy_machine"));
        } else {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("portable_alyzer"));
        }

        // Impregnated Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input("logWood", 8)
                .fluidInputs(Materials.SeedOil.getFluid(250))
                .circuitMeta(1)
                .outputs(Mods.Forestry.getItem("impregnated_casing"))
                .EUt(50).duration(FFMUtility.timeCarpenter(50)).buildAndRegister();

        // Escritoire
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input("plankWood", 6)
                .fluidInputs(Materials.SeedOil.getFluid(500))
                .circuitMeta(10)
                .outputs(Mods.Forestry.getItem("escritoire"))
                .EUt(50).duration(FFMUtility.timeCarpenter(50)).buildAndRegister();

        // Impregnated Stick
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input("logWood", 2)
                .fluidInputs(Materials.SeedOil.getFluid(100))
                .circuitMeta(11)
                .outputs(Mods.Forestry.getItem("oak_stick"))
                .EUt(10).duration(FFMUtility.timeCarpenter(10)).buildAndRegister();

        // Bog Earth
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.DIRT, 4, GTValues.W))
                .input("sand", 4)
                .inputs(Mods.Forestry.getItem("mulch"))
                .fluidInputs(Materials.Water.getFluid(1000))
                .circuitMeta(2)
                .outputs(Mods.Forestry.getItem("bog_earth", 8))
                .EUt(5).duration(FFMUtility.timeCarpenter(5)).buildAndRegister();

        // Hardened Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Materials.Diamond, 4)
                .inputs(Mods.Forestry.getItem("sturdy_machine"))
                .fluidInputs(Materials.Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("hardened_machine"))
                .EUt(75).duration(FFMUtility.timeCarpenter(75)).buildAndRegister();

        // Ender Pearl
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("crafting_material", 5, 1))
                .outputs(new ItemStack(Items.ENDER_PEARL))
                .EUt(100).duration(FFMUtility.timeCarpenter(100)).buildAndRegister();

        // Woven Silk
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("crafting_material", 9, 2))
                .fluidInputs(Materials.Water.getFluid(500))
                .outputs(Mods.Forestry.getItem("crafting_material", 1, 3))
                .EUt(10).duration(FFMUtility.timeCarpenter(10)).buildAndRegister();

        // Tool
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("bronze_pickaxe"))
                .inputs(Mods.Forestry.getItem("carton"))
                .outputs(Mods.Forestry.getItem("kit_pickaxe"))
                .EUt(20).duration(FFMUtility.timeCarpenter(20)).buildAndRegister();

        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("bronze_shovel"))
                .inputs(Mods.Forestry.getItem("carton"))
                .outputs(Mods.Forestry.getItem("kit_shovel"))
                .EUt(20).duration(FFMUtility.timeCarpenter(20)).buildAndRegister();

        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("broken_bronze_pickaxe"))
                .fluidInputs(Materials.Oxygen.getFluid(100))
                .output(ingot, Materials.Bronze, 2)
                .EUt(5).duration(FFMUtility.timeCarpenter(5)).buildAndRegister();

        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("broken_bronze_shovel"))
                .fluidInputs(Materials.Oxygen.getFluid(100))
                .output(ingot, Materials.Bronze)
                .EUt(5).duration(FFMUtility.timeCarpenter(5)).buildAndRegister();

        // Carton
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(dust, Materials.Wood, 4)
                .fluidInputs(Materials.Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("carton"))
                .EUt(5).duration(FFMUtility.timeCarpenter(5)).buildAndRegister();
    }

    private static void CoreNormal() {
        // Portable Analyzer
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Materials.Tin, 2)
                .input("circuitLv", 2)
                .input(plate, Materials.Diamond)
                .input("paneGlass", 2)
                .input(plate, Materials.RedAlloy, 2)
                .fluidInputs(Materials.Water.getFluid(2000))
                .outputs(Mods.Forestry.getItem("portable_alyzer"))
                .EUt(50).duration(FFMUtility.timeCarpenter(50 * 4)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {
            RecipeManagers.carpenterManager.addRecipe(
                    50, Materials.Water.getFluid(2000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("portable_alyzer"),
                    "TGT", "CGC", "RDR",
                    'T', new UnificationEntry(plate, Materials.Tin).toString(),
                    'G', "paneGlass",
                    'C', "circuitLv",
                    'R', new UnificationEntry(plate, Materials.RedAlloy).toString(),
                    'D', new UnificationEntry(plate, Materials.Diamond).toString());
        }
    }

    private static void CoreHard() {
        // Portable Analyzer
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Materials.Tin, 2)
                .input("circuitMv", 2)
                .input(plate, Materials.Diamond)
                .input("paneGlass", 2)
                .input(plate, Materials.RedAlloy, 2)
                .fluidInputs(Materials.Water.getFluid(2000))
                .outputs(Mods.Forestry.getItem("portable_alyzer"))
                .EUt(50).duration(FFMUtility.timeCarpenter(50 * 4)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {
            RecipeManagers.carpenterManager.addRecipe(
                    50, Materials.Water.getFluid(2000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("portable_alyzer"),
                    "TGT", "CGC", "RDR",
                    'T', new UnificationEntry(plate, Materials.Tin).toString(),
                    'G', "paneGlass",
                    'C', "circuitMv",
                    'R', new UnificationEntry(plate, Materials.RedAlloy).toString(),
                    'D', new UnificationEntry(plate, Materials.Diamond).toString());
        }
    }

    private static void Apiculture() {
        if (!Mods.ForestryApiculture.isModLoaded()) return;
        // Scented Paneling
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("royal_jelly"))
                .input("plankWood", 3)
                .inputs(Mods.Forestry.getItem("beeswax", 2))
                .inputs(Mods.Forestry.getItem("pollen"))
                .fluidInputs(Fluids.FOR_HONEY.getFluid(500))
                .outputs(Mods.Forestry.getItem("crafting_material", 1, 6))
                .EUt(50).duration(FFMUtility.timeCarpenter(50)).buildAndRegister();

        // Candle
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.STRING)
                .inputs(Mods.Forestry.getItem("beeswax", 6))
                .fluidInputs(Materials.Water.getFluid(600))
                .outputs(Mods.Forestry.getItem("candle", 24))
                .EUt(30).duration(FFMUtility.timeCarpenter(30)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("crafting_material", 1, 2))
                .inputs(Mods.Forestry.getItem("beeswax", 6))
                .fluidInputs(Materials.Water.getFluid(200))
                .outputs(Mods.Forestry.getItem("candle", 6))
                .EUt(10).duration(FFMUtility.timeCarpenter(10)).buildAndRegister();

        // Iodine Capsule
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("honey_drop", 2))
                .inputs(GTUtility.copy(4, ModuleArboriculture.getItems().pollenFertile.getWildcard()))
                .inputs(Mods.Forestry.getItem("can"))
                .input(dust, Materials.Gunpowder, 2)
                .fluidInputs(Materials.Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("iodine_capsule"))
                .EUt(5).duration(FFMUtility.timeCarpenter(5)).buildAndRegister();

        // Dissipation Charge
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("honeydew", 2))
                .inputs(Mods.Forestry.getItem("royal_jelly", 4))
                .inputs(Mods.Forestry.getItem("can"))
                .input(dust, Materials.Gunpowder, 2)
                .fluidInputs(Materials.Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("crafting_material", 1, 4))
                .EUt(5).duration(FFMUtility.timeCarpenter(5)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {}
    }

    private static void ApicultureNormal() {
        if (!Mods.ForestryApiculture.isModLoaded()) return;
        if (Mods.ForestryFactory.isModLoaded()) {}
    }

    private static void ApicultureHard() {
        if (!Mods.ForestryApiculture.isModLoaded()) return;
        // Apiarist's Chest
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("bee_chest"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(screw, Materials.Steel, 2)
                .input("blockGlass")
                .input("beeComb", 5)
                .input("chestWood")
                .fluidInputs(Fluids.FOR_HONEY.getFluid(1000))
                .outputs(Mods.Forestry.getItem("bee_chest"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        // Apiariy
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("apiary"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("bee_house", 2))
                .inputs(Mods.Forestry.getItem("frame_impregnated"))
                .input(screw, Materials.Steel, 2)
                .input("slabWood", 2)
                .input("beeComb")
                .input("fenceWood")
                .fluidInputs(Materials.SeedOil.getFluid(1000))
                .outputs(Mods.Forestry.getItem("apiary"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        // Alvearies
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_swarmer"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_fan"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_heater"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_hygro"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_stabiliser"));
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("alveary_sieve"));

        // Swarmer
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 5))
                .inputs(Mods.Forestry.getItem("frame_impregnated"))
                .inputs(Mods.Forestry.getItem("royal_jelly"))
                .input(foil, Materials.RoseGold, 2)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.swarmer"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        // Fan
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 11))
                .input(Blocks.IRON_BARS, 2)
                .input(rotor, Materials.Bronze)
                .input(MetaItems.ELECTRIC_MOTOR_MV, 2)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.fan"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        // Heater
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 7))
                .input(Blocks.IRON_BARS)
                .input(MetaItems.ELECTRIC_MOTOR_MV)
                .input(wireGtQuadruple, Materials.Cupronickel, 3)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.heater"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        // Hygroregulator
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 6))
                .input("circuitMv")
                .input(MetaTileEntities.BRONZE_DRUM, 2)
                .input(pipeNormalFluid, Materials.StainlessSteel)
                .input(plate, Materials.RedAlloy)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.hygro"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        // Stabiliser
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 4))
                .inputs(Mods.Forestry.getItem("royal_jelly"))
                .input(MetaItems.ITEM_FILTER, 2)
                .input(plate, Materials.CertusQuartz, 2)
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.stabiliser"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        // Sieve
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Forestry.getItem("alveary.plain"))
                .inputs(Mods.Forestry.getItem("thermionic_tubes", 4, 9))
                .inputs(Mods.Forestry.getItem("crafting_material", 3, 4))
                .inputs(GTUtility.copy(2, ModuleArboriculture.getItems().pollenFertile.getWildcard()))
                .fluidInputs(Fluids.FOR_HONEY.getFluid(5000))
                .outputs(Mods.Forestry.getItem("alveary.sieve"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {
            // Apiarist's Chest
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(1000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("bee_chest"),
                    "SGS", "BCB", "BBB",
                    'S', new UnificationEntry(screw, Materials.Steel).toString(),
                    'G', "blockGlass",
                    'C', "chestWood",
                    'B', "beeComb");

            // Apiariy
            RecipeManagers.carpenterManager.addRecipe(
                    60, Materials.SeedOil.getFluid(1000), Mods.Forestry.getItem("frame_impregnated"),
                    Mods.Forestry.getItem("apiary"),
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
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1, 5),
                    'G', new UnificationEntry(foil, Materials.RoseGold).toString(),
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
                    'R', new UnificationEntry(rotor, Materials.Bronze).toString());

            // Heater
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(5000),
                    Mods.Forestry.getItem("alveary.plain"), Mods.Forestry.getItem("alveary.heater"),
                    "TBT", "CCC", "TMT",
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1, 7),
                    'B', Blocks.IRON_BARS,
                    'C', new UnificationEntry(wireGtQuadruple, Materials.Cupronickel).toString(),
                    'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm());

            // Hygroregulator
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(5000),
                    Mods.Forestry.getItem("alveary.plain"), Mods.Forestry.getItem("alveary.hygro"),
                    "TCT", "DPD", "TRT",
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1, 6),
                    'C', "circuitMv",
                    'D', MetaTileEntities.BRONZE_DRUM.getStackForm(),
                    'P', new UnificationEntry(pipeNormalFluid, Materials.StainlessSteel).toString(),
                    'R', new UnificationEntry(plate, Materials.RedAlloy).toString());

            // Stabiliser
            RecipeManagers.carpenterManager.addRecipe(
                    60, Fluids.FOR_HONEY.getFluid(5000),
                    Mods.Forestry.getItem("alveary.plain"), Mods.Forestry.getItem("alveary.stabiliser"),
                    "TQT", "FJF", "TQT",
                    'T', Mods.Forestry.getItem("thermionic_tubes", 1, 4),
                    'Q', new UnificationEntry(plate, Materials.CertusQuartz).toString(),
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

    private static void ArboricultureHard() {
        if (!Mods.ForestryArboriculture.isModLoaded()) return;
        // Arborist's Chest
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("tree_chest"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(screw, Materials.Steel, 2)
                .input("blockGlass")
                .input("treeSapling", 5)
                .input("chestWood")
                .fluidInputs(Materials.SeedOil.getFluid(1000))
                .outputs(Mods.Forestry.getItem("tree_chest"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {
            RecipeManagers.carpenterManager.addRecipe(
                    60, Materials.SeedOil.getFluid(1000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("tree_chest"),
                    "SGS", "TCT", "TTT",
                    'S', new UnificationEntry(screw, Materials.Steel).toString(),
                    'G', "blockGlass",
                    'C', "chestWood",
                    'T', "treeSapling");
        }
    }

    private static void Climatology() {
        if (!Mods.ForestryClimatology.isModLoaded()) return;
        if (Mods.ForestryFactory.isModLoaded()) {
            CarpenterLoader.removeCarpenterRecipe(Mods.Forestry.getItem("habitat_screen"));

            RecipeManagers.carpenterManager.addRecipe(
                    100, Materials.Water.getFluid(2000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("habitat_screen"),
                    "BPB", "BPB", "GDG",
                    'B', new UnificationEntry(plate, Materials.Bronze).toString(),
                    'P', "paneGlass",
                    'G', new UnificationEntry(gear, Materials.Bronze).toString(),
                    'D', new UnificationEntry(plate, Materials.Diamond).toString());
        } else {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("habitat_screen"));
        }

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Materials.Bronze, 4)
                .input("paneGlass", 2)
                .input(gear, Materials.Bronze, 2)
                .input(plate, Materials.Diamond)
                .fluidInputs(Materials.Water.getFluid(2000))
                .outputs(Mods.Forestry.getItem("habitat_screen"))
                .EUt(100).duration(FFMUtility.timeCarpenter(100)).buildAndRegister();
    }

    private static void Factory() {
        if (!Mods.ForestryFactory.isModLoaded()) return;
        CarpenterLoader.removeCarpenterRecipe(
                ItemCircuitBoard.createCircuitboard(EnumCircuitBoardType.BASIC, null, new ICircuit[] {}));
        CarpenterLoader.removeCarpenterRecipe(
                ItemCircuitBoard.createCircuitboard(EnumCircuitBoardType.ENHANCED, null, new ICircuit[] {}));
        CarpenterLoader.removeCarpenterRecipe(
                ItemCircuitBoard.createCircuitboard(EnumCircuitBoardType.REFINED, null, new ICircuit[] {}));
        CarpenterLoader.removeCarpenterRecipe(
                ItemCircuitBoard.createCircuitboard(EnumCircuitBoardType.INTRICATE, null, new ICircuit[] {}));
        CarpenterLoader.removeCarpenterRecipe(Mods.Forestry.getItem("soldering_iron"));

        RecipeManagers.carpenterManager.addRecipe(
                40, Materials.Water.getFluid(1000),
                ItemStack.EMPTY, Mods.Forestry.getItem("soldering_iron"),
                " I ", "I I", "  B",
                'I', new UnificationEntry(plate, Materials.Iron).toString(),
                'B', new UnificationEntry(plate, Materials.Bronze).toString());

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Materials.Iron, 3)
                .input(plate, Materials.Bronze)
                .fluidInputs(Materials.Water.getFluid(1000))
                .outputs(Mods.Forestry.getItem("soldering_iron"))
                .EUt(40).duration(FFMUtility.timeCarpenter(40)).buildAndRegister();
    }

    private static void Mail() {
        if (!Mods.ForestryMail.isModLoaded()) return;

        Material[] matStamp = { Materials.Apatite, Materials.Copper, Materials.Tin, Materials.Gold };

        for (int i = 0; i < matStamp.length; i++) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(matStamp[i] == Materials.Apatite ? gem : plate, matStamp[i], 3)
                    .inputs(new ItemStack(Items.PAPER, 3))
                    .fluidInputs(Materials.SeedOil.getFluid(300))
                    .outputs(Mods.Forestry.getItem("stamps", 1, i))
                    .EUt(10).duration(FFMUtility.timeCarpenter(10)).buildAndRegister();
        }
    }

    private static void LepidopterologyHard() {
        if (!Mods.ForestryLepidopterology.isModLoaded()) return;
        // Lepidoptetist's Chest
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("butterfly_chest"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(screw, Materials.Steel, 2)
                .input("blockGlass")
                .inputs(GTUtility.copy(5, ModuleLepidopterology.getItems().butterflyGE.getWildcard()))
                .input("chestWood")
                .fluidInputs(Materials.SeedOil.getFluid(1000))
                .outputs(Mods.Forestry.getItem("butterfly_chest"))
                .EUt(60).duration(FFMUtility.timeCarpenter(60)).buildAndRegister();

        if (Mods.ForestryFactory.isModLoaded()) {
            RecipeManagers.carpenterManager.addRecipe(
                    60, Materials.SeedOil.getFluid(1000),
                    ItemStack.EMPTY, Mods.Forestry.getItem("butterfly_chest"),
                    "SGS", "BCB", "BBB",
                    'S', new UnificationEntry(screw, Materials.Steel).toString(),
                    'G', "blockGlass",
                    'C', "chestWood",
                    'B', ModuleLepidopterology.getItems().butterflyGE.getWildcard());
        }
    }

    private static void Storage() {
        if (Mods.ForestryBackpacks.isModLoaded()) {
            ItemStack[] backStack = { Mods.Forestry.getItem("miner_bag"),
                    Mods.Forestry.getItem("digger_bag"),
                    Mods.Forestry.getItem("forester_bag"),
                    Mods.Forestry.getItem("hunter_bag"),
                    Mods.Forestry.getItem("adventurer_bag"),
                    Mods.Forestry.getItem("builder_bag") };
            ItemStack[] backStackT2 = { Mods.Forestry.getItem("miner_bag_t2"),
                    Mods.Forestry.getItem("digger_bag_t2"),
                    Mods.Forestry.getItem("forester_bag_t2"),
                    Mods.Forestry.getItem("hunter_bag_t2"),
                    Mods.Forestry.getItem("adventurer_bag_t2"),
                    Mods.Forestry.getItem("builder_bag_t2") };

            for (int i = 0; i < backStack.length; i++) {

                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                        .input(gem, Materials.Diamond)
                        .inputNBT(backStack[i].getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                        .inputs(Mods.Forestry.getItem("crafting_material", 7, 3))
                        .fluidInputs(Materials.Water.getFluid(1000))
                        .outputs(backStackT2[i])
                        .EUt(200).duration(FFMUtility.timeCarpenter(200)).buildAndRegister();
            }
        }
        if (Mods.ForestryCrate.isModLoaded()) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input("logWood", 4)
                    .fluidInputs(Materials.Water.getFluid(1000))
                    .circuitMeta(10)
                    .outputs(Mods.Forestry.getItem("crate"))
                    .EUt(20).duration(FFMUtility.timeCarpenter(20)).buildAndRegister();
        }
    }

    public static void registerCarpenterRecipe(Enum recipeMode, ItemStack output, int EUt, Material material,
                                               MetaItem.MetaValueItem broad, String... circuit) {
        if (recipeMode == FFMUtility.recipeMode.NORMAL) {
            RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                    .input(plate, material, 6)
                    .input(plate, Materials.RedAlloy, 2)
                    .input(broad)
                    .outputs(output)
                    .EUt(EUt).duration(FFMUtility.timeCarpenter(EUt)).buildAndRegister();
            RecipeManagers.carpenterManager.addRecipe(
                    EUt, Materials.SolderingAlloy.getFluid(72),
                    ItemStack.EMPTY, output,
                    "STS", "SBS", "STS",
                    'S', new UnificationEntry(plate, material).toString(),
                    'T', new UnificationEntry(plate, Materials.RedAlloy).toString(),
                    'B', broad.getStackForm());
            RecipeManagers.carpenterManager.addRecipe(
                    EUt, Materials.Tin.getFluid(144),
                    ItemStack.EMPTY, output,
                    "STS", "SBS", "STS",
                    'S', new UnificationEntry(plate, material).toString(),
                    'T', new UnificationEntry(plate, Materials.RedAlloy).toString(),
                    'B', broad.getStackForm());

        } else if (recipeMode == FFMUtility.recipeMode.HARD) {
            if (circuit.length >= 2) {
                GTELog.logger.error("Circuit recipe for " + output.getDisplayName() + " has more than one circuit.");
                return;
            }

            RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                    .input(screw, material, 4)
                    .input(foil, material, 2)
                    .input(wireFine, material)
                    .input(circuit[0], 2)
                    .input(broad)
                    .outputs(output)
                    .EUt(EUt).duration(FFMUtility.timeCarpenter(EUt)).buildAndRegister();
            RecipeManagers.carpenterManager.addRecipe(
                    EUt, Materials.SolderingAlloy.getFluid(72),
                    broad.getStackForm(), output,
                    "SFS", "CWC", "SFS",
                    'S', new UnificationEntry(screw, material).toString(),
                    'F', new UnificationEntry(foil, material).toString(),
                    'C', circuit[0],
                    'W', new UnificationEntry(wireFine, material).toString());
            RecipeManagers.carpenterManager.addRecipe(
                    EUt, Materials.Tin.getFluid(72),
                    broad.getStackForm(), output,
                    "SFS", "CWC", "SFS",
                    'S', new UnificationEntry(screw, material).toString(),
                    'F', new UnificationEntry(foil, material).toString(),
                    'C', circuit[0],
                    'W', new UnificationEntry(wireFine, material).toString());
        }
    }

    public static void removeCarpenterRecipe(ItemStack output) {
        CarpenterRecipeManager.getRecipes(output).forEach(r -> RecipeManagers.carpenterManager.removeRecipe(r));
    }
}
