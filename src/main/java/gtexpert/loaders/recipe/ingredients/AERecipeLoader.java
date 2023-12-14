package gtexpert.loaders.recipe.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.GTEConfigHolder.ae2Integration;
import static gtexpert.integration.ae.AEHelper.*;

import java.util.*;
import java.util.stream.IntStream;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.AssemblyLineRecipeBuilder;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.common.items.GTEMetaItems;

import appeng.api.util.AEColor;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class AERecipeLoader {

    public static void init() {
        materials();
        items();
        blocks();
        tools();
    }

    private static void materials() {
        // Iron Ingot
        ModHandler.removeFurnaceSmelting(aeMaterials.ironDust().maybeStack(1).get());

        // Gold Ingot
        ModHandler.removeFurnaceSmelting(aeMaterials.goldDust().maybeStack(1).get());

        // Pure Certus Quartz Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/seeds_certus"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.CertusQuartz, 1)
                .input("sand", 1)
                .outputs(getModItem(GTEValues.MODID_AE, "crystal_seed", 2, 0))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 0))
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 0))
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Pure Nether Quartz Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/seeds_nether"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.NetherQuartz, 1)
                .input("sand", 1)
                .outputs(getModItem(GTEValues.MODID_AE, "crystal_seed", 2, 600))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 600))
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 600))
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Pure Fluix Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/seeds_fluix"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .input("sand", 1)
                .outputs(getModItem(GTEValues.MODID_AE, "crystal_seed", 2, 1200))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 1200))
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(getModItem(GTEValues.MODID_AE, "crystal_seed", 1, 1200))
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(aeMaterials.purifiedFluixCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
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

    private static void blocks() {
        // Creative Energy Cell
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaItems.ENERGY_CLUSTER, 4)
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3, 8))
                .input(MetaTileEntities.HULL[UV])
                .input(MetaItems.CRYSTAL_MAINFRAME_UV, 4)
                .inputs(aeBlocks.energyCellDense().maybeStack(8).get())
                .input(MetaItems.COVER_SOLAR_PANEL_UV, 1)
                .fluidInputs(GTEMaterials.Fluix.getFluid(18432))
                .fluidInputs(Materials.SolderingAlloy.getFluid(18432))
                .fluidInputs(Materials.Neutronium.getFluid(9216))
                .outputs(aeBlocks.energyCellCreative().maybeStack(1).get())
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        // ME Interface
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interface_alt"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interface_part"));
        ModHandler.addShapedNBTClearingRecipe("interface_to_interface",
                getModItem(GTEValues.MODID_AE, "interface", 1, 0),
                "I", " ",
                'I', getModItem(GTEValues.MODID_AE, "interface", 1, 0));
        ModHandler.addShapedNBTClearingRecipe("interface_to_part_interface",
                getModItem(GTEValues.MODID_AE, "interface", 1, 0),
                " ", "I",
                'I', getModItem(GTEValues.MODID_AE, "part", 1, 440));
        ModHandler.addShapedNBTClearingRecipe("part_interface_to_part_interface",
                getModItem(GTEValues.MODID_AE, "part", 1, 440),
                "I", " ",
                'I', getModItem(GTEValues.MODID_AE, "part", 1, 440));
        ModHandler.addShapedNBTClearingRecipe("part_interface_to_interface",
                getModItem(GTEValues.MODID_AE, "part", 1, 440),
                " ", "I",
                'I', getModItem(GTEValues.MODID_AE, "interface", 1, 0));

        // ME Fluid Interface
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/blocks/fluid_interfaces_interface_alt"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/blocks/fluid_interfaces_interface_part"));
        ModHandler.addShapedNBTClearingRecipe("fluid_interface_to_fluid_interface",
                getModItem(GTEValues.MODID_AE, "fluid_interface", 1, 0),
                "I", " ",
                'I', getModItem(GTEValues.MODID_AE, "fluid_interface", 1, 0));
        ModHandler.addShapedNBTClearingRecipe("fluid_interface_to_part_fluid_interface",
                getModItem(GTEValues.MODID_AE, "fluid_interface", 1, 0),
                " ", "I",
                'I', getModItem(GTEValues.MODID_AE, "part", 1, 441));
        ModHandler.addShapedNBTClearingRecipe("part_fluid_interface_to_part_fluid_interface",
                getModItem(GTEValues.MODID_AE, "part", 1, 441),
                "I", " ",
                'I', getModItem(GTEValues.MODID_AE, "part", 1, 441));
        ModHandler.addShapedNBTClearingRecipe("part_fluid_interface_to_fluid_interface",
                getModItem(GTEValues.MODID_AE, "part", 1, 441),
                " ", "I",
                'I', getModItem(GTEValues.MODID_AE, "fluid_interface", 1, 0));

        if (ae2Integration.enableAE2UELExtended) {
            // ME Delivery Interface
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceimp_alt"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceimp_part"));
            ModHandler.addShapedNBTClearingRecipe("interfaceimp_to_interfaceimp",
                    getModItem(GTEValues.MODID_AE, "interfaceimp", 1, 0),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AE, "interfaceimp", 1, 0));
            ModHandler.addShapedNBTClearingRecipe("interfaceimp_to_part_interfaceimp",
                    getModItem(GTEValues.MODID_AE, "interfaceimp", 1, 0),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AE, "part", 1, 620));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceimp_to_part_interfaceimp",
                    getModItem(GTEValues.MODID_AE, "part", 1, 620),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AE, "part", 1, 620));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceimp_to_interfaceimp",
                    getModItem(GTEValues.MODID_AE, "part", 1, 620),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AE, "interfaceimp", 1, 0));

            // Advanced ME Delivery Interface
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceadv_alt"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceadv_part"));
            ModHandler.addShapedNBTClearingRecipe("interfaceadv_to_interfaceadv",
                    getModItem(GTEValues.MODID_AE, "interfaceadv", 1, 0),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AE, "interfaceadv", 1, 0));
            ModHandler.addShapedNBTClearingRecipe("interfaceadv_to_part_interfaceadv",
                    getModItem(GTEValues.MODID_AE, "interfaceadv", 1, 0),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AE, "part", 1, 621));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceadv_to_part_interfaceadv",
                    getModItem(GTEValues.MODID_AE, "part", 1, 621),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AE, "part", 1, 621));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceadv_to_interfaceadv",
                    getModItem(GTEValues.MODID_AE, "part", 1, 621),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AE, "interfaceadv", 1, 0));

            // Perfect ME Delivery Interface
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceper_alt"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceper_part"));
            ModHandler.addShapedNBTClearingRecipe("interfaceper_to_interfaceper",
                    getModItem(GTEValues.MODID_AE, "interfaceper", 1, 0),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AE, "interfaceper", 1, 0));
            ModHandler.addShapedNBTClearingRecipe("interfaceper_to_part_interfaceper",
                    getModItem(GTEValues.MODID_AE, "interfaceper", 1, 0),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AE, "part", 1, 622));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceper_to_part_interfaceper",
                    getModItem(GTEValues.MODID_AE, "part", 1, 622),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AE, "part", 1, 622));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceper_to_interfaceper",
                    getModItem(GTEValues.MODID_AE, "part", 1, 622),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AE, "interfaceper", 1, 0));

            // ME Patterns Interface
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfacepatt_alt"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfacepatt_part"));
            ModHandler.addShapedNBTClearingRecipe("interfacepatt_to_interfacepatt",
                    getModItem(GTEValues.MODID_AE, "interface_patterns", 1, 0),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AE, "interface_patterns", 1, 0));
            ModHandler.addShapedNBTClearingRecipe("interfacepatt_to_part_interfacepatt",
                    getModItem(GTEValues.MODID_AE, "interface_patterns", 1, 0),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AE, "part", 1, 623));
            ModHandler.addShapedNBTClearingRecipe("part_interfacepatt_to_part_interfacepatt",
                    getModItem(GTEValues.MODID_AE, "part", 1, 623),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AE, "part", 1, 623));
            ModHandler.addShapedNBTClearingRecipe("part_interfacepatt_to_interfacepatt",
                    getModItem(GTEValues.MODID_AE, "part", 1, 623),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AE, "interface_patterns", 1, 0));
        }

        // ME Dual Interface
        if (Loader.isModLoaded(GTEValues.MODID_AEFC)) {
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEFC, "dual_interface"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEFC, "dual_interface_alter"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEFC, "part_dual_interface"));
            ModHandler.addShapedNBTClearingRecipe("dual_interface_to_dual_interface",
                    getModItem(GTEValues.MODID_AEFC, "dual_interface", 1, 0),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AEFC, "dual_interface", 1, 0));
            ModHandler.addShapedNBTClearingRecipe("dual_interface_to_part_dual_interface",
                    getModItem(GTEValues.MODID_AEFC, "dual_interface", 1, 0),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AEFC, "part_dual_interface", 1, 0));
            ModHandler.addShapedNBTClearingRecipe("part_dual_interface_to_part_dual_interface",
                    getModItem(GTEValues.MODID_AEFC, "part_dual_interface", 1, 0),
                    "I", " ",
                    'I', getModItem(GTEValues.MODID_AEFC, "part_dual_interface", 1, 0));
            ModHandler.addShapedNBTClearingRecipe("part_dual_interface_to_dual_interface",
                    getModItem(GTEValues.MODID_AEFC, "part_dual_interface", 1, 0),
                    " ", "I",
                    'I', getModItem(GTEValues.MODID_AEFC, "dual_interface", 1, 0));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .input("craftInterfaceItem")
                    .input("craftInterfaceFluid")
                    .outputs(getModItem(GTEValues.MODID_AEFC, "dual_interface", 1, 0))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier + 1])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .input("craftInterfaceDual")
                    .outputs(getModItem(GTEValues.MODID_AE, "interface", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_AE, "fluid_interface", 1, 0))
                    .duration(20).EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Rubber List
        final Map<Material, Integer> rubberMaterials = new Object2ObjectOpenHashMap<>();
        rubberMaterials.put(Materials.Rubber, 432);
        rubberMaterials.put(Materials.SiliconeRubber, 216);
        rubberMaterials.put(Materials.StyreneButadieneRubber, 108);

        // Quartz Fiber
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/parts/quartz_fiber_part"));
        ModHandler.addMirroredShapedRecipe("nether_quartz_cutter_wire",
                aeParts.quartzFiber().maybeStack(1).get(), "Px", 'P',
                OreDictUnifier.get(plate, Materials.NetherQuartz));
        ModHandler.addMirroredShapedRecipe("certus_quartz_cutter_wire",
                aeParts.quartzFiber().maybeStack(1).get(), "Px", 'P',
                OreDictUnifier.get(plate, Materials.CertusQuartz));
        ModHandler.addMirroredShapedRecipe("quartzite_cutter_wire", aeParts.quartzFiber().maybeStack(1).get(),
                "Px", 'P', OreDictUnifier.get(plate, Materials.Quartzite));
        RecipeMaps.WIREMILL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input("craftStickQuartz", 1)
                .outputs(aeParts.quartzFiber().maybeStack(2).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Glass Cable
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/glass_fluix"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/glass_fluix_clean"));
        ModHandler.addShapedRecipe("fluix_glass_cable", aeParts.cableGlass().stack(AEColor.TRANSPARENT, 6),
                "SFS", "CCC", "SFS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'F', OreDictUnifier.get(dust, GTEMaterials.Fluix),
                'C', aeParts.quartzFiber().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(aeParts.quartzFiber().maybeStack(3).get())
                .fluidInputs(GTEMaterials.Fluix.getFluid(144))
                .outputs(aeParts.cableGlass().stack(AEColor.TRANSPARENT, 6))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(aeParts.quartzFiber().maybeStack(3).get())
                .input(dust, GTEMaterials.Fluix, 1)
                .outputs(aeParts.cableGlass().stack(AEColor.TRANSPARENT, 6))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftGlassCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 16))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftGlassCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());

        // Covered Cable
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/covered_fluix"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/covered_fluix_clean"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeParts.cableDenseCovered().stack(AEColor.TRANSPARENT, 1))
                .outputs(aeParts.cableCovered().stack(AEColor.TRANSPARENT, 4))
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftCoveredCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 36))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftCoveredCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 20 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());
        for (Map.Entry<Material, Integer> materialEntry : rubberMaterials.entrySet()) {
            Material material = materialEntry.getKey();
            Integer materialAmount = materialEntry.getValue();
            ModHandler.addShapedRecipe(material.equals(Materials.Rubber), "fluix_covered_cable_" + material.getName(),
                    aeParts.cableCovered().stack(AEColor.TRANSPARENT, 1),
                    "RRR", "CCC", "RRR",
                    'R', new UnificationEntry(plate, material),
                    'C', "craftGlassCable");
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input("craftGlassCable", 3)
                    .fluidInputs(material.getFluid(materialAmount))
                    .outputs(aeParts.cableCovered().stack(AEColor.TRANSPARENT, 1))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // Smart Cable
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/smart_fluix"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/smart_fluix_clean"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeParts.cableDenseSmart().stack(AEColor.TRANSPARENT, 1))
                .outputs(aeParts.cableSmart().stack(AEColor.TRANSPARENT, 4))
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();
        ModHandler.addShapedRecipe("fluix_smart_cable_1", aeParts.cableSmart().stack(AEColor.TRANSPARENT, 1),
                " G ", "RCR", " G ",
                'G', OreDictUnifier.get(dust, Materials.Glowstone),
                'R', OreDictUnifier.get(dust, Materials.Redstone),
                'C', aeParts.cableCovered().stack(AEColor.TRANSPARENT, 1));
        ModHandler.addShapedRecipe("fluix_smart_cable_2", aeParts.cableSmart().stack(AEColor.TRANSPARENT, 1),
                " R ", "GCG", " R ",
                'G', OreDictUnifier.get(dust, Materials.Glowstone),
                'R', OreDictUnifier.get(dust, Materials.Redstone),
                'C', aeParts.cableCovered().stack(AEColor.TRANSPARENT, 1));
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftSmartCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 56))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftSmartCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 40 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input("craftCoveredCable", 1)
                .input(dust, Materials.Glowstone, 1)
                .input(dust, Materials.Redstone, 1)
                .outputs(aeParts.cableSmart().stack(AEColor.TRANSPARENT, 1))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        for (Map.Entry<Material, Integer> materialEntry : rubberMaterials.entrySet()) {
            Material material = materialEntry.getKey();
            Integer materialAmount = materialEntry.getValue();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input("craftGlassCable", 3)
                    .input(dust, Materials.Glowstone, 3)
                    .input(dust, Materials.Redstone, 3)
                    .fluidInputs(material.getFluid(materialAmount))
                    .outputs(aeParts.cableSmart().stack(AEColor.TRANSPARENT, 1))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // Dense Covered Cable
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cables/dense_covered_fluix"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cables/dense_covered_fluix_clean"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeParts.cableCovered().stack(AEColor.TRANSPARENT, 4))
                .outputs(aeParts.cableDenseCovered().stack(AEColor.TRANSPARENT, 1))
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseCoveredCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 516))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseCoveredCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 500 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());
        for (Map.Entry<Material, Integer> materialEntry : rubberMaterials.entrySet()) {
            Material material = materialEntry.getKey();
            Integer materialAmount = materialEntry.getValue();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input("craftGlassCable", 12)
                    .fluidInputs(material.getFluid(materialAmount * 4))
                    .outputs(aeParts.cableDenseCovered().stack(AEColor.TRANSPARENT, 1))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // Dense Smart Cable
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/dense_smart_fluix"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cables/dense_smart_fluix_clean"));
        ModHandler.addShapedRecipe("fluix_dense_smart_cable_1", aeParts.cableDenseSmart().stack(AEColor.TRANSPARENT, 1),
                " G ", "RCR", " G ",
                'G', OreDictUnifier.get(dust, Materials.Glowstone),
                'R', OreDictUnifier.get(dust, Materials.Redstone),
                'C', aeParts.cableDenseCovered().stack(AEColor.TRANSPARENT, 1));
        ModHandler.addShapedRecipe("fluix_dense_smart_cable_2", aeParts.cableDenseSmart().stack(AEColor.TRANSPARENT, 1),
                " R ", "GCG", " R ",
                'G', OreDictUnifier.get(dust, Materials.Glowstone),
                'R', OreDictUnifier.get(dust, Materials.Redstone),
                'C', aeParts.cableDenseCovered().stack(AEColor.TRANSPARENT, 1));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeParts.cableSmart().stack(AEColor.TRANSPARENT, 4))
                .outputs(aeParts.cableDenseSmart().stack(AEColor.TRANSPARENT, 1))
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseSmartCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 76))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseSmartCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(getModItem(GTEValues.MODID_AE, "part", 1, 60 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input("craftDenseCoveredCable", 1)
                .input(dust, Materials.Glowstone, 1)
                .input(dust, Materials.Redstone, 1)
                .outputs(aeParts.cableDenseSmart().stack(AEColor.TRANSPARENT, 1))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input("craftCoveredCable", 4)
                .input(dust, Materials.Glowstone, 4)
                .input(dust, Materials.Redstone, 4)
                .outputs(aeParts.cableDenseSmart().stack(AEColor.TRANSPARENT, 1))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        for (Map.Entry<Material, Integer> materialEntry : rubberMaterials.entrySet()) {
            Material material = materialEntry.getKey();
            Integer materialAmount = materialEntry.getValue();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input("craftGlassCable", 12)
                    .input(dust, Materials.Glowstone, 12)
                    .input(dust, Materials.Redstone, 12)
                    .fluidInputs(material.getFluid(materialAmount * 4))
                    .outputs(aeParts.cableDenseSmart().stack(AEColor.TRANSPARENT, 1))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // Crafting Monitor
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_monitor"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(aeParts.storageMonitor().maybeStack(1).get())
                .outputs(aeBlocks.craftingMonitor().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingMonitor().maybeStack(1).get())
                .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(aeParts.storageMonitor().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1k Crafting Storage
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_1k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(aeMaterials.cell1kPart().maybeStack(1).get())
                .outputs(aeBlocks.craftingStorage1k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingStorage1k().maybeStack(1).get())
                .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(aeMaterials.cell1kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4k Crafting Storage
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_4k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(aeMaterials.cell4kPart().maybeStack(1).get())
                .outputs(aeBlocks.craftingStorage4k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingStorage4k().maybeStack(1).get())
                .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(aeMaterials.cell4kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k Crafting Storage
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_16k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(aeMaterials.cell16kPart().maybeStack(1).get())
                .outputs(aeBlocks.craftingStorage16k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingStorage16k().maybeStack(1).get())
                .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(aeMaterials.cell16kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k Crafting Storage
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_64k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .inputs(aeMaterials.cell64kPart().maybeStack(1).get())
                .outputs(aeBlocks.craftingStorage64k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.craftingStorage64k().maybeStack(1).get())
                .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                .outputs(aeMaterials.cell64kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        if (ae2Integration.enableAE2UELExtended) {
            // 1mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_1mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 61))
                    .outputs(getModItem(GTEValues.MODID_AE, "crafting_storage_1mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AE, "crafting_storage_1mb", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 61))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_4mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 62))
                    .outputs(getModItem(GTEValues.MODID_AE, "crafting_storage_4mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AE, "crafting_storage_4mb", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 62))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_16mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 63))
                    .outputs(getModItem(GTEValues.MODID_AE, "crafting_storage_16mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AE, "crafting_storage_16mb", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 63))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 64mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_64mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 64))
                    .outputs(getModItem(GTEValues.MODID_AE, "crafting_storage_64mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AE, "crafting_storage_64mb", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 64))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 256mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_256mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 65))
                    .outputs(getModItem(GTEValues.MODID_AE, "crafting_storage_256mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AE, "crafting_storage_256mb", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 65))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1gb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_1gb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 66))
                    .outputs(getModItem(GTEValues.MODID_AE, "crafting_storage_1gb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AE, "crafting_storage_1gb", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 66))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 2gb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_15gb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 67))
                    .outputs(getModItem(GTEValues.MODID_AE, "crafting_storage_15gb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AE, "crafting_storage_15gb", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 67))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        }

        if (GTEValues.isModLoadedAEACPU()) {
            // 256k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_256k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_256k", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_256k", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1024k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_1024k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                    .outputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_1024k", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_1024k", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4096k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_4096k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                    .outputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_4096k", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_4096k", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16384k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_16384k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                    .outputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_16384k", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EXCPU, "crafting_storage_16384k", 1, 0))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        }
    }

    private static void items() {
        // GTE ME Storage Fake Component
        AssemblyLineRecipeBuilder builderGTECore = RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(screw, Materials.Neutronium, 8)
                .input(MetaItems.CRYSTAL_MAINFRAME_UV, 4)
                .fluidInputs(Materials.SolderingAlloy.getFluid(18432))
                .fluidInputs(Materials.Neutronium.getFluid(9216))
                .output(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 1)
                .duration(1200).EUt(VA[UV]);
        if (ae2Integration.enableAE2UELExtended) {
            if (Loader.isModLoaded(GTEValues.MODID_AEA)) {
                builderGTECore.inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 16, 3));
                builderGTECore.inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 16, 6));
            } else {
                builderGTECore.inputs(getModItem(GTEValues.MODID_AE, "material", 16, 65));
                builderGTECore.inputs(getModItem(GTEValues.MODID_AE, "material", 16, 70));
            }
        } else {
            builderGTECore.inputs(getModItem(GTEValues.MODID_AE, "material", 16, 38));
            builderGTECore.inputs(getModItem(GTEValues.MODID_AE, "material", 16, 57));
        }
        builderGTECore.buildAndRegister();

        // 1k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_1k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_1k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.cell1kPart().maybeStack(1).get())
                .outputs(aeItems.cell1k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.cell1k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.cell1kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_4k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_4k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.cell4kPart().maybeStack(1).get())
                .outputs(aeItems.cell4k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.cell4k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.cell4kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_16k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_16k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.cell16kPart().maybeStack(1).get())
                .outputs(aeItems.cell16k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.cell16k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.cell16kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_64k"));
        ModHandler
                .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_64k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.cell64kPart().maybeStack(1).get())
                .outputs(aeItems.cell64k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.cell64k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.cell64kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_1k"));
        ModHandler
                .removeRecipeByName(
                        new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_1k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.fluidCell1kPart().maybeStack(1).get())
                .outputs(aeItems.fluidCell1k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.fluidCell1k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.fluidCell1kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_4k"));
        ModHandler
                .removeRecipeByName(
                        new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_4k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.fluidCell4kPart().maybeStack(1).get())
                .outputs(aeItems.fluidCell4k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.fluidCell4k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.fluidCell4kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_16k"));
        ModHandler
                .removeRecipeByName(
                        new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_16k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.fluidCell16kPart().maybeStack(1).get())
                .outputs(aeItems.fluidCell16k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.fluidCell16k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.fluidCell16kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k Fluid Storage Cell
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_64k"));
        ModHandler
                .removeRecipeByName(
                        new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_64k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.fluidCell64kPart().maybeStack(1).get())
                .outputs(aeItems.fluidCell64k().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.fluidCell64k().maybeStack(1).get().getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.fluidCell64kPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 2³ Spatial Storage Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_2_cubed"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_2_cubed_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.cell2SpatialPart().maybeStack(1).get())
                .outputs(aeItems.spatialCell2().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.spatialCell2().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.cell2SpatialPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16³ Spatial Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_16_cubed"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_16_cubed_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.cell16SpatialPart().maybeStack(1).get())
                .outputs(aeItems.spatialCell16().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.spatialCell16().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.cell16SpatialPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 128³ Spatial Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_128_cubed"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/cells/spatial_storage_cell_128_cubed_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .inputs(aeMaterials.cell128SpatialPart().maybeStack(1).get())
                .outputs(aeItems.spatialCell128().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(aeItems.spatialCell128().maybeStack(1).get().getItem(), NBTMatcher.ANY,
                        NBTCondition.ANY)
                .outputs(Loader.isModLoaded(GTEValues.MODID_AEA) ?
                        getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0) :
                        aeMaterials.emptyStorageCell().maybeStack(1).get())
                .outputs(aeMaterials.cell128SpatialPart().maybeStack(1).get())
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // Recycle - Storage Housing
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/empty_storage_cell"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/e2acasing"));
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .output(dust, Materials.Steel, 2)
                .output(dustTiny, Materials.Steel, 2)
                .duration(100).EUt(VH[LV])
                .buildAndRegister();
        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                .fluidInputs(Materials.Oxygen.getFluid(56))
                .output(ingot, Materials.Steel, 2)
                .output(nugget, Materials.Steel, 2)
                .duration(56).EUt(VA[LV])
                .buildAndRegister();

        if (ae2Integration.enableAE2UELExtended) {
            // 1mb Storage Cell
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_1mb"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_1mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 61))
                    .outputs(getModItem(GTEValues.MODID_AE, "storage_cell_1mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(getModItem(GTEValues.MODID_AE, "storage_cell_1mb", 1, 0).getItem(), NBTMatcher.ANY,
                            NBTCondition.ANY)
                    .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 61))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4mb Storage Cell
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_4mb"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_4mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 62))
                    .outputs(getModItem(GTEValues.MODID_AE, "storage_cell_4mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(getModItem(GTEValues.MODID_AE, "storage_cell_4mb", 1, 0).getItem(), NBTMatcher.ANY,
                            NBTCondition.ANY)
                    .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 62))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16mb Storage Cell
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_16mb"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_16mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 63))
                    .outputs(getModItem(GTEValues.MODID_AE, "storage_cell_16mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(getModItem(GTEValues.MODID_AE, "storage_cell_16mb", 1, 0).getItem(), NBTMatcher.ANY,
                            NBTCondition.ANY)
                    .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 63))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 64mb Storage Cell
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_64mb"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_64mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 64))
                    .outputs(getModItem(GTEValues.MODID_AE, "storage_cell_64mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(getModItem(GTEValues.MODID_AE, "storage_cell_64mb", 1, 0).getItem(), NBTMatcher.ANY,
                            NBTCondition.ANY)
                    .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 64))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 256mb Storage Cell
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_256mb"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/storage_cell_256mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 65))
                    .outputs(getModItem(GTEValues.MODID_AE, "storage_cell_256mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(getModItem(GTEValues.MODID_AE, "storage_cell_256mb", 1, 0).getItem(), NBTMatcher.ANY,
                            NBTCondition.ANY)
                    .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 65))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1mb Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_1mb"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_1mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 68))
                    .outputs(getModItem(GTEValues.MODID_AE, "fluid_storage_cell_1mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(getModItem(GTEValues.MODID_AE, "fluid_storage_cell_1mb", 1, 0).getItem(), NBTMatcher.ANY,
                            NBTCondition.ANY)
                    .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 68))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4mb Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_4mb"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_4mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 69))
                    .outputs(getModItem(GTEValues.MODID_AE, "fluid_storage_cell_4mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(getModItem(GTEValues.MODID_AE, "fluid_storage_cell_4mb", 1, 0).getItem(), NBTMatcher.ANY,
                            NBTCondition.ANY)
                    .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 69))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16mb Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_16mb"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/cells/fluid_storage_cell_16mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .inputs(getModItem(GTEValues.MODID_AE, "material", 1, 70))
                    .outputs(getModItem(GTEValues.MODID_AE, "fluid_storage_cell_16mb", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(getModItem(GTEValues.MODID_AE, "fluid_storage_cell_16mb", 1, 0).getItem(), NBTMatcher.ANY,
                            NBTCondition.ANY)
                    .outputs(aeMaterials.emptyStorageCell().maybeStack(1).get())
                    .outputs(getModItem(GTEValues.MODID_AE, "material", 1, 70))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        } else if (Loader.isModLoaded(GTEValues.MODID_AEA)) {
            // 256k Storage Cell
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/256k"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/256k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1024k Storage Cell
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/1024k"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/1024k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 1))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 1))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4096k Storage Cell
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/4096k"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/4096k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 2))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 2))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16384k Storage Cell
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/16384k"));
            ModHandler
                    .removeRecipeByName(
                            new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/16384k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 3))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 3))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 256k Fluid Storage Cell
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/owncasing/256k"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/extracasing/256k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 4))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 0))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 0))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 4))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1024k Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/owncasing/1024k"));
            ModHandler
                    .removeRecipeByName(
                            new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/extracasing/1024k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 5))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 1))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 1))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 5))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4096k Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/owncasing/4096k"));
            ModHandler
                    .removeRecipeByName(
                            new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/extracasing/4096k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 6))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 2))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 2))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                    .outputs(getModItem(GTEValues.MODID_AEA, "storage.component", 1, 6))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // Recycle - Fluid Storage Housing
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/case/fluid"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/a2ecasing"));
            RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                    .output(dust, Materials.StainlessSteel, 2)
                    .output(dustTiny, Materials.StainlessSteel, 2)
                    .duration(100).EUt(VH[LV])
                    .buildAndRegister();
            RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                    .fluidInputs(Materials.Oxygen.getFluid(56))
                    .output(ingot, Materials.StainlessSteel, 2)
                    .output(nugget, Materials.StainlessSteel, 2)
                    .duration(56).EUt(VA[LV])
                    .buildAndRegister();

            // Recycle - Advanced Storage Housing
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/case/item"));
            RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .output(dust, Materials.TungstenSteel, 2)
                    .output(dustTiny, Materials.TungstenSteel, 2)
                    .duration(100).EUt(VH[LV])
                    .buildAndRegister();
            RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 0))
                    .fluidInputs(Materials.Oxygen.getFluid(56))
                    .output(ingot, Materials.TungstenSteel, 2)
                    .output(nugget, Materials.TungstenSteel, 2)
                    .duration(56).EUt(VA[LV])
                    .buildAndRegister();
        }

        // Formation Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "materials/formationcore"));
        ModHandler.addShapedRecipe("formation_core", aeMaterials.formationCore().maybeStack(1).get(),
                "SES", "LQL", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', "gemMaterials.NetherQuartz",
                'E', aeMaterials.engProcessor().maybeStack(1).get(),
                'L', aeMaterials.logicProcessor().maybeStack(1).get());
        ModHandler.addShapedRecipe("formation_core_pure", aeMaterials.formationCore().maybeStack(2).get(),
                "SES", "LQL", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get(),
                'E', aeMaterials.engProcessor().maybeStack(1).get(),
                'L', aeMaterials.logicProcessor().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(aeMaterials.engProcessor().maybeStack(1).get())
                .inputs(aeMaterials.logicProcessor().maybeStack(1).get())
                .input("craftNetherQuartz", 1)
                .outputs(aeMaterials.formationCore().maybeStack(4).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier + 1])
                .buildAndRegister();

        // Annihilation Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "materials/annihilationcore"));
        ModHandler.addShapedRecipe("annihilation_core", aeMaterials.annihilationCore().maybeStack(1).get(),
                "SES", "CQC", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', "gemCertusQuartz",
                'E', aeMaterials.engProcessor().maybeStack(1).get(),
                'C', aeMaterials.calcProcessor().maybeStack(1).get());
        ModHandler.addShapedRecipe("annihilation_core_pure",
                aeMaterials.annihilationCore().maybeStack(2).get(),
                "SES", "CQC", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get(),
                'E', aeMaterials.engProcessor().maybeStack(1).get(),
                'C', aeMaterials.calcProcessor().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(aeMaterials.engProcessor().maybeStack(1).get())
                .inputs(aeMaterials.calcProcessor().maybeStack(1).get())
                .input("craftCertusQuartz", 1)
                .outputs(aeMaterials.annihilationCore().maybeStack(4).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier + 1])
                .buildAndRegister();

        // Matrix Core
        ModHandler.addShapedRecipe("matrix_core", GTEMetaItems.MATRIX_CORE.getStackForm(),
                "SAS", "FQF", "SAS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', aeMaterials.fluixCrystal().maybeStack(1).get(),
                'A', aeMaterials.annihilationCore().maybeStack(1).get(),
                'F', aeMaterials.formationCore().maybeStack(1).get());
        ModHandler.addShapedRecipe("matrix_core_pure", GTEMetaItems.MATRIX_CORE.getStackForm(2),
                "SAS", "FQF", "SAS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', aeMaterials.purifiedFluixCrystal().maybeStack(1).get(),
                'A', aeMaterials.annihilationCore().maybeStack(1).get(),
                'F', aeMaterials.formationCore().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(aeMaterials.annihilationCore().maybeStack(1).get())
                .inputs(aeMaterials.formationCore().maybeStack(1).get())
                .input("craftFluix", 1)
                .output(GTEMetaItems.MATRIX_CORE, 4)
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier + 1])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 4)
                .inputs(aeMaterials.engProcessor().maybeStack(2).get())
                .inputs(aeMaterials.logicProcessor().maybeStack(1).get())
                .inputs(aeMaterials.calcProcessor().maybeStack(1).get())
                .input("craftNetherQuartz", 1)
                .input("craftCertusQuartz", 1)
                .input("craftFluix", 1)
                .output(GTEMetaItems.MATRIX_CORE, 4)
                .duration(100).EUt(VA[GTEValues.ae2VoltageTier + 1])
                .buildAndRegister();

        // Printed Silicon
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(ae2Integration.moveSteelShape ? GTEMetaItems.SHAPE_MOLD_PRINTED_SILICON.getStackForm() :
                        aeMaterials.siliconPress().maybeStack(1).get())
                .input(plate, Materials.Silicon, 1)
                .outputs(aeMaterials.siliconPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Logic Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(ae2Integration.moveSteelShape ? GTEMetaItems.SHAPE_MOLD_LOGIC_PROCESSOR.getStackForm() :
                        aeMaterials.logicProcessorPress().maybeStack(1).get())
                .input(plate, Materials.Gold, 1)
                .outputs(aeMaterials.logicProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Calc Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(
                        ae2Integration.moveSteelShape ? GTEMetaItems.SHAPE_MOLD_CALCULATION_PROCESSOR.getStackForm() :
                                aeMaterials.calcProcessorPress().maybeStack(1).get())
                .input(plate, Materials.CertusQuartz, 1)
                .outputs(aeMaterials.calcProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Engineer Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(
                        ae2Integration.moveSteelShape ? GTEMetaItems.SHAPE_MOLD_ENGINEERING_PROCESSOR.getStackForm() :
                                aeMaterials.engProcessorPress().maybeStack(1).get())
                .input(plate, Materials.Diamond, 1)
                .outputs(aeMaterials.engProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Logic Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(aeMaterials.logicProcessorPrint().maybeStack(1).get())
                .fluidInputs(Materials.Redstone.getFluid(144))
                .outputs(aeMaterials.logicProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Calc Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(aeMaterials.calcProcessorPrint().maybeStack(1).get())
                .fluidInputs(Materials.Redstone.getFluid(144))
                .outputs(aeMaterials.calcProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Engineer Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(aeMaterials.engProcessorPrint().maybeStack(1).get())
                .fluidInputs(Materials.Redstone.getFluid(144))
                .outputs(aeMaterials.engProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        if (ae2Integration.moveSteelShape) {
            // All shapes
            Arrays.stream(GTEMetaItems.GTE_SHAPE_MOLDS)
                    .forEach(shapeMold -> RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                            .notConsumable(shapeMold.getStackForm())
                            .input(MetaItems.SHAPE_EMPTY)
                            .output(shapeMold)
                            .duration(120).EUt(22)
                            .buildAndRegister());
            Arrays.stream(GTEMetaItems.GTE_SHAPE_EXTRUDERS).filter(Objects::nonNull)
                    .forEach(shapeExtruder -> RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                            .notConsumable(shapeExtruder.getStackForm())
                            .input(MetaItems.SHAPE_EMPTY)
                            .output(shapeExtruder)
                            .duration(120).EUt(22)
                            .buildAndRegister());

            // Mold (Printed Silicon)
            ModHandler.addShapedRecipe("shape_mold_printed_silicon",
                    GTEMetaItems.SHAPE_MOLD_PRINTED_SILICON.getStackForm(),
                    "h  ", "   ", "S  ",
                    'S', MetaItems.SHAPE_EMPTY);
            ModHandler.addShapelessRecipe("silicon_processor_mold_to_gt",
                    GTEMetaItems.SHAPE_MOLD_PRINTED_SILICON.getStackForm(),
                    aeMaterials.siliconPress().maybeStack(1).get());

            // Mold (Logic Processor)
            ModHandler.addShapedRecipe("shape_mold_logic_processor",
                    GTEMetaItems.SHAPE_MOLD_LOGIC_PROCESSOR.getStackForm(),
                    " h ", "   ", "S  ",
                    'S', MetaItems.SHAPE_EMPTY);
            ModHandler.addShapelessRecipe("logic_processor_mold_to_gt",
                    GTEMetaItems.SHAPE_MOLD_LOGIC_PROCESSOR.getStackForm(),
                    aeMaterials.logicProcessorPress().maybeStack(1).get());

            // Mold (Calculation Processor)
            ModHandler.addShapedRecipe("shape_mold_calculation_processor",
                    GTEMetaItems.SHAPE_MOLD_CALCULATION_PROCESSOR.getStackForm(),
                    "   ", "  h", "S  ",
                    'S', MetaItems.SHAPE_EMPTY);
            ModHandler.addShapelessRecipe("calc_processor_mold_to_gt",
                    GTEMetaItems.SHAPE_MOLD_CALCULATION_PROCESSOR.getStackForm(),
                    aeMaterials.calcProcessorPress().maybeStack(1).get());

            // Mold (Engineering Processor)
            ModHandler.addShapedRecipe("shape_mold_engineering_processor",
                    GTEMetaItems.SHAPE_MOLD_ENGINEERING_PROCESSOR.getStackForm(),
                    "   ", "   ", "S h",
                    'S', MetaItems.SHAPE_EMPTY);
            ModHandler.addShapelessRecipe("engineer_processor_mold_to_gt",
                    GTEMetaItems.SHAPE_MOLD_ENGINEERING_PROCESSOR.getStackForm(),
                    aeMaterials.engProcessorPress().maybeStack(1).get());

            // Extruder Shape (Printed Silicon)
            ModHandler.addShapedRecipe("shape_extruder_printed_silicon",
                    GTEMetaItems.SHAPE_EXTRUDER_PRINTED_SILICON.getStackForm(),
                    " x ", " S ", "   ",
                    'S', MetaItems.SHAPE_EMPTY);

            // Extruder Shape (Logic Processor)
            ModHandler.addShapedRecipe("shape_extruder_logic_processor",
                    GTEMetaItems.SHAPE_EXTRUDER_LOGIC_PROCESSOR.getStackForm(),
                    " x ", "S  ", "   ",
                    'S', GTEMetaItems.SHAPE_EXTRUDER_LOGIC_PROCESSOR);

            // Extruder Shape (Calculation Processor)
            ModHandler.addShapedRecipe("shape_extruder_calculation_processor",
                    GTEMetaItems.SHAPE_EXTRUDER_CALCULATION_PROCESSOR.getStackForm(),
                    " x ", " S ", "   ",
                    'S', GTEMetaItems.SHAPE_EXTRUDER_LOGIC_PROCESSOR.getStackForm());

            // Extruder Shape (Engineering Processor)
            ModHandler.addShapedRecipe("shape_extruder_engineering_processor",
                    GTEMetaItems.SHAPE_EXTRUDER_ENGINEERING_PROCESSOR.getStackForm(),
                    " x ", "  S", "   ",
                    'S', GTEMetaItems.SHAPE_EXTRUDER_LOGIC_PROCESSOR.getStackForm());
        } else {
            // Silicon Processor Press
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(lens, Materials.NetherQuartz)
                    .input(block, Materials.Iron, 1)
                    .outputs(aeMaterials.siliconPress().maybeStack(1).get())
                    .duration(2000).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();

            // Logic Processor Press
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(lens, GTEMaterials.ChargedCertusQuartz)
                    .input(block, Materials.Iron, 1)
                    .outputs(aeMaterials.logicProcessorPress().maybeStack(1).get())
                    .duration(2000).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();

            // Calc Processor Press
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(lens, Materials.CertusQuartz)
                    .input(block, Materials.Iron, 1)
                    .outputs(aeMaterials.calcProcessorPress().maybeStack(1).get())
                    .duration(2000).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();

            // Engineer Processor Press
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(lens, GTEMaterials.Fluix)
                    .input(block, Materials.Iron, 1)
                    .outputs(aeMaterials.engProcessorPress().maybeStack(1).get())
                    .duration(2000).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }
    }

    private static void tools() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && ae2Integration.hardToolRecipes) {
            // Nether Quartz Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_axe"));
            ModHandler.addShapedRecipe(true, "nether_quartz_axe",
                    aeItems.netherQuartzAxe().maybeStack(1).get(),
                    "PQf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_hoe"));
            ModHandler.addShapedRecipe(true, "nether_quartz_hoe",
                    aeItems.netherQuartzHoe().maybeStack(1).get(),
                    "PQf", "hS ", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_pickaxe"));
            ModHandler.addShapedRecipe(true, "nether_quartz_pickaxe",
                    aeItems.netherQuartzPick().maybeStack(1).get(),
                    "PQQ", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_spade"));
            ModHandler.addShapedRecipe(true, "nether_quartz_spade",
                    aeItems.netherQuartzShovel().maybeStack(1).get(),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_sword"));
            ModHandler.addShapedRecipe(true, "nether_quartz_sword",
                    aeItems.netherQuartzSword().maybeStack(1).get(),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Cutting Knife
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_cutting_knife"));
            ModHandler.addShapedRecipe(true, "nether_quartz_cutting_knife",
                    aeItems.netherQuartzKnife().maybeStack(1).get(),
                    "fPh", "QSQ", " S ",
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Wrench
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_wrench"));
            ModHandler.addShapedRecipe(true, "ether_quartz_wrench",
                    aeItems.netherQuartzWrench().maybeStack(1).get(),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz));

            // Certus Quartz Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_axe"));
            ModHandler.addShapedRecipe(true, "certus_quartz_axe",
                    aeItems.certusQuartzAxe().maybeStack(1).get(),
                    "PQf", "PS ", "hS ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_hoe"));
            ModHandler.addShapedRecipe(true, "certus_quartz_hoe",
                    aeItems.certusQuartzHoe().maybeStack(1).get(),
                    "PQf", "hS ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_pickaxe"));
            ModHandler.addShapedRecipe(true, "certus_quartz_pickaxe",
                    aeItems.certusQuartzPick().maybeStack(1).get(),
                    "PQQ", "hSf", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_spade"));
            ModHandler.addShapedRecipe(true, "certus_quartz_spade",
                    aeItems.certusQuartzShovel().maybeStack(1).get(),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_sword"));
            ModHandler.addShapedRecipe(true, "certus_quartz_sword",
                    aeItems.certusQuartzSword().maybeStack(1).get(),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Cutting Knife
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_cutting_knife"));
            ModHandler.addShapedRecipe(true, "certus_quartz_cutting_knife",
                    aeItems.certusQuartzKnife().maybeStack(1).get(),
                    "fPh", "QSQ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Wrench
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_wrench"));
            ModHandler.addShapedRecipe(true, "certus_quartz_wrench",
                    aeItems.certusQuartzWrench().maybeStack(1).get(),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz));
        }
    }
}
