package gtexpert.integration.binnies.genetics.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;

import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.integration.ffm.FFMConfigHolder;
import gtexpert.integration.ffm.FFMUtility;
import gtexpert.integration.ffm.recipes.machines.CarpenterLoader;

import forestry.api.recipes.RecipeManagers;

public class GeneticsItemsRecipe {

    public static void init() {
        Enum<FFMUtility.recipeMode> recipeMode = FFMUtility.recipeMode.safeValueOf(FFMConfigHolder.gameMode);

        // Gene Database
        ModHandler.addShapelessNBTClearingRecipe("gene_database_nbt",
                Mods.Genetics.getItem("geneticdatabase"),
                Mods.Genetics.getItem("geneticdatabase"));

        if (recipeMode == FFMUtility.recipeMode.HARD) {
            // Reinforced Casing
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("laboratory_casing"));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(Mods.Forestry.getItem("sturdy_machine"))
                    .input(plate, Materials.Aluminium, 8)
                    .outputs(Mods.Genetics.getItem("misc"))
                    .EUt(VA[MV]).duration(1200).buildAndRegister();

            // DNA Dye
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("dna_dye_from_glowstone"));
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("dna_dye"));
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MIXER_RECIPES,
                    OreDictUnifier.get(dust, Materials.Glowstone),
                    OreDictUnifier.get("dyeBlue"),
                    OreDictUnifier.get("dyePurple"));
            RecipeMaps.MIXER_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(dust, Materials.Redstone))
                    .inputs(OreDictUnifier.get(dust, Materials.Glowstone))
                    .input("dyeBlue")
                    .input("dyePurple")
                    .fluidInputs(Materials.Blaze.getFluid(144))
                    .outputs(Mods.Genetics.getItem("misc", 1, 1))
                    .EUt(VA[LV]).duration(100).buildAndRegister();

            // Fluorescent Dye
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("fluorescent_dye"));
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MIXER_RECIPES,
                    OreDictUnifier.get(dust, Materials.Glowstone),
                    OreDictUnifier.get("dyeOrange"),
                    OreDictUnifier.get("dyeYellow"));
            RecipeMaps.MIXER_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(dust, Materials.Redstone))
                    .inputs(OreDictUnifier.get(dust, Materials.Glowstone))
                    .input("dyeOrange")
                    .input("dyeYellow")
                    .fluidInputs(Materials.Blaze.getFluid(144))
                    .outputs(Mods.Genetics.getItem("misc", 1, 2))
                    .EUt(VA[LV]).duration(100).buildAndRegister();

            // Growth Medium
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("growth_medium"));
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MIXER_RECIPES,
                    OreDictUnifier.get(dust, Materials.Sugar),
                    OreDictUnifier.get(dust, Materials.Bone));
            RecipeMaps.MIXER_RECIPES.recipeBuilder()
                    .inputs(OreDictUnifier.get(dust, Materials.Sugar))
                    .inputs(OreDictUnifier.get(dust, Materials.Calcium))
                    .inputs(Mods.Forestry.getItem("mulch"))
                    .inputs(OreDictUnifier.get(dust, Materials.Wood))
                    .inputs(OreDictUnifier.get(dust, Materials.Ash))
                    .fluidInputs(Materials.Water.getFluid(1000))
                    .outputs(Mods.Genetics.getItem("misc", 1, 4))
                    .EUt(VA[LV]).duration(400).buildAndRegister();

            // Blank Sequence
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("empty_sequencer"));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(6)
                    .inputs(new ItemStack(Blocks.GLASS_PANE, 2))
                    .fluidInputs(Materials.Gold.getFluid(288))
                    .outputs(Mods.Genetics.getItem("misc", 1, 5))
                    .EUt(VA[LV]).duration(200).buildAndRegister();

            // Empty Serum Vial
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("empty_serum"));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(7)
                    .inputs(new ItemStack(Blocks.GLASS_PANE, 4))
                    .fluidInputs(Materials.Gold.getFluid(144))
                    .outputs(Mods.Genetics.getItem("misc", 1, 6))
                    .EUt(VH[MV]).duration(200).buildAndRegister();

            // Empty Serum Array
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("empty_genome"));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(8)
                    .inputs(new ItemStack(Blocks.GLASS_PANE, 10))
                    .fluidInputs(Materials.Gold.getFluid(576))
                    .outputs(Mods.Genetics.getItem("misc", 1, 7))
                    .EUt(VA[MV]).duration(200).buildAndRegister();

            // Integrated Circuit Board
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("integrated_circuit"));
            ModHandler.addShapedRecipe(true, "integrated_circuit",
                    Mods.Genetics.getItem("misc", 1, 8),
                    "SdS", "CBC", "SwS",
                    'S', new UnificationEntry(screw, Materials.StainlessSteel),
                    'B', Mods.Forestry.getItem("chipsets", 1, 1),
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.HV));
            RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(Mods.Forestry.getItem("chipsets", 1, 1))
                    .input(circuit, MarkerMaterials.Tier.HV, 2)
                    .fluidInputs(Materials.StainlessSteel.getFluid(64))
                    .outputs(Mods.Genetics.getItem("misc", 1, 8))
                    .EUt(VA[LV]).duration(600).buildAndRegister();

            // Integrated CPU
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("integrated_cpu"));
            RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                    .input(GTEUtility.circuitBoard(HV))
                    .inputs(Mods.Forestry.getItem("thermionic_tubes", 1, 5))
                    .fluidInputs(Materials.Glowstone.getFluid(144))
                    .outputs(Mods.Genetics.getItem("misc", 1, 9))
                    .EUt(VA[LV]).duration(600).buildAndRegister();

            // Integrated Casing
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("integrated_casing"));
            RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(Mods.Forestry.getItem("hardened_machine"))
                    .inputs(Mods.Genetics.getItem("misc", 2, 9))
                    .fluidInputs(Materials.Glowstone.getFluid(288))
                    .outputs(Mods.Genetics.getItem("misc", 1, 10))
                    .EUt(VA[HV]).duration(900).buildAndRegister();

            // Gene Database
            CarpenterLoader.removeCarpenterRecipe(Mods.Genetics.getItem("geneticdatabase"));
            RecipeManagers.carpenterManager.addRecipe(
                    20, Materials.Redstone.getFluid(2880),
                    Mods.Forestry.getItem("portable_alyzer"), Mods.Genetics.getItem("geneticdatabase"),
                    "SPS", "PCP", "SPS",
                    'S', new UnificationEntry(screw, Materials.Emerald).toString(),
                    'P', new UnificationEntry(plate, Materials.Diamond).toString(),
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.EV).toString());

            // Analyst
            ModHandler.removeRecipeByName(Mods.Genetics.getResource("analyst"));
            RecipeManagers.carpenterManager.addRecipe(
                    40, Materials.Redstone.getFluid(4320),
                    Mods.Forestry.getItem("portable_alyzer"), Mods.Genetics.getItem("analyst"),
                    "IAI", "PCP", "IPI",
                    'I', Mods.Genetics.getItem("misc", 1, 8),
                    'A', Mods.Forestry.getItem("portable_alyzer"),
                    'P', new UnificationEntry(plate, Materials.Diamond).toString(),
                    'C', Mods.Genetics.getItem("misc", 1, 9));
        }
    }
}
