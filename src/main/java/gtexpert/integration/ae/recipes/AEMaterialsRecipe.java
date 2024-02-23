package gtexpert.integration.ae.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.Mods;

public class AEMaterialsRecipe {

    public static void init() {
        // Iron Ingot
        ModHandler.removeFurnaceSmelting(Mods.AppliedEnergistics2.getItem("material", 1, 49));

        // Gold Ingot
        ModHandler.removeFurnaceSmelting(Mods.AppliedEnergistics2.getItem("material", 1, 51));

        // Pure Certus Quartz Crystal
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("misc/seeds_certus"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.CertusQuartz, 1)
                .input("sand", 1)
                .outputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 2, 0))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crystal_seed"))
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 10))
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crystal_seed"))
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 10), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Pure Nether Quartz Crystal
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("misc/seeds_nether"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.NetherQuartz, 1)
                .input("sand", 1)
                .outputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 2, 600))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 1, 600))
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 11))
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 1, 600))
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 11), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Pure Fluix Crystal
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("misc/seeds_fluix"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .input("sand", 1)
                .outputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 2, 1200))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 1, 1200))
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 12))
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 1, 1200))
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 12), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Fluix Pearl
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("misc/fluixpearl"));
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(gem, Materials.EnderPearl, 1)
                .fluidInputs(GTEMaterials.Fluix.getFluid(144))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 9))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // ########################################
        // Sky Stone
        // ########################################
        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 45))
                .duration(500).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("smooth_sky_stone_block"))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 45))
                .duration(500).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Block
        ModHandler.removeFurnaceSmelting(Mods.AppliedEnergistics2.getItem("sky_stone_block"));
        RecipeMaps.BLAST_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                .outputs(Mods.AppliedEnergistics2.getItem("smooth_sky_stone_block"))
                .duration(100).EUt(VA[GTEValues.ae2VoltageTier])
                .blastFurnaceTemp(2700)
                .buildAndRegister();
        RecipeMaps.ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                .outputs(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                .duration(100).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // ########################################
        // Nether Quartz
        // ########################################
        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 11))
                .fluidOutputs(Materials.NetherQuartz.getFluid(72))
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 11))
                .output(dustSmall, Materials.NetherQuartz, 2)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/quartz_block_pure"));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 8, 11))
                .output(block, Materials.NetherQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 11))
                .output(stick, Materials.NetherQuartz, 1)
                .duration(40).EUt(VH[LV])
                .buildAndRegister();

        // ########################################
        // Certus Quartz
        // ########################################
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES,
                OreDictUnifier.get(block, Materials.CertusQuartz, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES,
                OreDictUnifier.get(gem, Materials.CertusQuartz, 9));

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 10))
                .fluidOutputs(Materials.CertusQuartz.getFluid(72))
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 10))
                .output(dustSmall, Materials.CertusQuartz, 2)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/certus_quartz_block"));
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/certus_quartz_block_pure"));
        ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("quartz_block", 4));
        ModHandler.addMirroredShapedRecipe("ae2_certus_quartz_block",
                Mods.AppliedEnergistics2.getItem("quartz_block"), "B", 'B',
                new UnificationEntry(block, Materials.CertusQuartz));
        ModHandler.addMirroredShapedRecipe("ceu_certus_quartz_block", OreDictUnifier.get(block, Materials.CertusQuartz),
                "B", 'B',
                Mods.AppliedEnergistics2.getItem("quartz_block"));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 8, 10))
                .output(block, Materials.CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 10))
                .output(stick, Materials.CertusQuartz, 1)
                .duration(40).EUt(VH[LV])
                .buildAndRegister();

        // ########################################
        // Charged Certus Quartz
        // ########################################
        // Gem
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(gem, Materials.CertusQuartz, 1)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 1))
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, Materials.CertusQuartz, 1)
                .output(dust, GTEMaterials.ChargedCertusQuartz, 1)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.ChargedCertusQuartz, 1)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 1))
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.ChargedCertusQuartz, 1)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 1), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.ChargedCertusQuartz, 4)
                .explosivesAmount(2)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 3, 1))
                .output(dustSmall, Materials.DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.ChargedCertusQuartz, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(Mods.AppliedEnergistics2.getItem("material", 3, 1))
                .output(dustSmall, Materials.DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Lens
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.ChargedCertusQuartz, 1)
                .output(lens, GTEMaterials.ChargedCertusQuartz, 1)
                .output(dustSmall, GTEMaterials.ChargedCertusQuartz, 1)
                .duration(1200).EUt(VA[MV])
                .buildAndRegister();

        // ########################################
        // Fluix
        // ########################################
        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 12))
                .fluidOutputs(GTEMaterials.Fluix.getFluid(72))
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, GTEMaterials.ChargedCertusQuartz, 1)
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.NetherQuartz, 1)
                .output(dust, GTEMaterials.Fluix, 3)
                .duration(200).EUt(VA[3])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 12))
                .output(dustSmall, GTEMaterials.Fluix, 2)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 7))
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 7), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 4)
                .explosivesAmount(2)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 3, 7))
                .output(dustSmall, Materials.DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(Mods.AppliedEnergistics2.getItem("material", 3, 7))
                .output(dustSmall, Materials.DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/fluix_block"));
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/fluix_block_pure"));
        ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("material", 4, 7));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 4, 7))
                .outputs(Mods.AppliedEnergistics2.getItem("fluix_block"))
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 8, 12))
                .outputs(Mods.AppliedEnergistics2.getItem("fluix_block"))
                .duration(300).EUt(2)
                .buildAndRegister();

        // Lens
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Fluix, 1)
                .output(lens, GTEMaterials.Fluix, 1)
                .output(dustSmall, GTEMaterials.Fluix, 1)
                .duration(1200).EUt(VA[MV])
                .buildAndRegister();

        // ########################################
        // Fluix Alloy
        // ########################################
        // Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(Mods.AppliedEnergistics2.getItem("material", 2, 45))
                .input(dust, GTEMaterials.Fluix, 2)
                .input(dust, Materials.Carbon, 2)
                .input(dust, Materials.Silicon, 1)
                .input(dust, Materials.Iron, 1)
                .output(dust, GTEMaterials.FluixAlloy, 8)
                .duration(200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
    }
}
