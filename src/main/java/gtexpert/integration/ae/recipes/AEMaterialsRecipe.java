package gtexpert.integration.ae.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.integration.ae.AEUtil.*;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;

public class AEMaterialsRecipe {

    public static void init() {
        // Iron Ingot
        ModHandler.removeFurnaceSmelting(aeMaterials.ironDust().maybeStack(1).get());

        // Gold Ingot
        ModHandler.removeFurnaceSmelting(aeMaterials.goldDust().maybeStack(1).get());

        // Pure Certus Quartz Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/seeds_certus"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.CertusQuartz, 1)
                .input("sand", 1)
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crystal_seed", 2, 0))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crystal_seed"))
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crystal_seed"))
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Pure Nether Quartz Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/seeds_nether"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.NetherQuartz, 1)
                .input("sand", 1)
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crystal_seed", 2, 600))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 600))
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 600))
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Pure Fluix Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/seeds_fluix"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .input("sand", 1)
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crystal_seed", 2, 1200))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 1200))
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 1200))
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(aeMaterials.purifiedFluixCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Fluix Pearl
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/fluixpearl"));
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(gem, Materials.EnderPearl, 1)
                .input(dust, GTEMaterials.Fluix, 1)
                .outputs(aeMaterials.fluixPearl().maybeStack(1).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // ########################################
        // Sky Stone
        // ########################################
        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(aeMaterials.skyDust().maybeStack(1).get())
                .duration(500).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeBlocks.smoothSkyStoneBlock().maybeStack(1).get())
                .outputs(aeMaterials.skyDust().maybeStack(1).get())
                .duration(500).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Block
        ModHandler.removeFurnaceSmelting(aeBlocks.skyStoneBlock().maybeStack(1).get());
        RecipeMaps.BLAST_RECIPES.recipeBuilder()
                .inputs(aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(aeBlocks.smoothSkyStoneBlock().maybeStack(1).get())
                .duration(100).EUt(VA[GTEValues.ae2VoltageTier])
                .blastFurnaceTemp(2700)
                .buildAndRegister();
        RecipeMaps.ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(aeBlocks.skyStoneBlock().maybeStack(1).get())
                .duration(100).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // ########################################
        // Nether Quartz
        // ########################################
        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(Materials.NetherQuartz.getFluid(72))
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .output(dustSmall, Materials.NetherQuartz, 2)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/quartz_block_pure"));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(8).get())
                .output(block, Materials.NetherQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
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
                .inputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(Materials.CertusQuartz.getFluid(72))
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .output(dustSmall, Materials.CertusQuartz, 2)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/certus_quartz_block"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/certus_quartz_block_pure"));
        ModHandler.removeRecipeByOutput(aeMaterials.certusQuartzCrystal().maybeStack(4).get());
        ModHandler.addMirroredShapedRecipe("ae2_certus_quartz_block",
                aeBlocks.quartzBlock().maybeStack(1).get(), "B", 'B',
                new UnificationEntry(block, Materials.CertusQuartz));
        ModHandler.addMirroredShapedRecipe("ceu_certus_quartz_block", OreDictUnifier.get(block, Materials.CertusQuartz),
                "B", 'B',
                aeBlocks.quartzBlock().maybeStack(1).get());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(8).get())
                .output(block, Materials.CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .output(stick, Materials.CertusQuartz, 1)
                .duration(40).EUt(VH[LV])
                .buildAndRegister();

        // ########################################
        // Charged Certus Quartz
        // ########################################
        // Gem
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(gem, Materials.CertusQuartz, 1)
                .outputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
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
                .outputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.ChargedCertusQuartz, 1)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.ChargedCertusQuartz, 4)
                .explosivesAmount(2)
                .outputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(3).get())
                .output(dustSmall, Materials.DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.ChargedCertusQuartz, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(3).get())
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
                .inputs(aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
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
                .inputs(aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .output(dustSmall, GTEMaterials.Fluix, 2)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(aeMaterials.fluixCrystal().maybeStack(1).get())
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(aeMaterials.fluixCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 4)
                .explosivesAmount(2)
                .outputs(aeMaterials.fluixCrystal().maybeStack(3).get())
                .output(dustSmall, Materials.DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(aeMaterials.fluixCrystal().maybeStack(3).get())
                .output(dustSmall, Materials.DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/fluix_block"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/fluix_block_pure"));
        ModHandler.removeRecipeByOutput(aeMaterials.fluixCrystal().maybeStack(4).get());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.fluixCrystal().maybeStack(4).get())
                .outputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedFluixCrystal().maybeStack(8).get())
                .outputs(aeBlocks.fluixBlock().maybeStack(1).get())
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
                .inputs(aeMaterials.skyDust().maybeStack(2).get())
                .input(dust, GTEMaterials.Fluix, 2)
                .input(dust, Materials.Carbon, 2)
                .input(dust, Materials.Silicon, 1)
                .input(dust, Materials.Iron, 1)
                .output(dust, GTEMaterials.FluixAlloy, 8)
                .duration(200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
    }
}
