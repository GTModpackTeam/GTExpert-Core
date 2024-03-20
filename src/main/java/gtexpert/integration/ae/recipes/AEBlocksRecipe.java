package gtexpert.integration.ae.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.integration.ae.AEUtil.tierMaterials;

import java.util.Map;
import java.util.stream.IntStream;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.Mods;
import gtexpert.integration.ae.AEConfigHolder;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public class AEBlocksRecipe {

    public static void init() {
        // Creative Energy Cell
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaItems.ENERGY_CLUSTER, 4)
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3, 8))
                .input(MetaTileEntities.HULL[UV])
                .input(MetaItems.CRYSTAL_MAINFRAME_UV, 4)
                .inputs(Mods.AppliedEnergistics2.getItem("dense_energy_cell", 8))
                .input(MetaItems.COVER_SOLAR_PANEL_UV, 1)
                .fluidInputs(GTEMaterials.Fluix.getFluid(18432))
                .fluidInputs(Materials.SolderingAlloy.getFluid(18432))
                .fluidInputs(Materials.Neutronium.getFluid(9216))
                .outputs(Mods.AppliedEnergistics2.getItem("creative_energy_cell"))
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        // ME Interface
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interface_alt"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interface_part"));
        ModHandler.addShapedNBTClearingRecipe("interface_to_interface",
                Mods.AppliedEnergistics2.getItem("interface"),
                "I", " ",
                'I', Mods.AppliedEnergistics2.getItem("interface"));
        ModHandler.addShapedNBTClearingRecipe("interface_to_part_interface",
                Mods.AppliedEnergistics2.getItem("interface"),
                " ", "I",
                'I', Mods.AppliedEnergistics2.getItem("part", 1, 440));
        ModHandler.addShapedNBTClearingRecipe("part_interface_to_part_interface",
                Mods.AppliedEnergistics2.getItem("part", 1, 440),
                "I", " ",
                'I', Mods.AppliedEnergistics2.getItem("part", 1, 440));
        ModHandler.addShapedNBTClearingRecipe("part_interface_to_interface",
                Mods.AppliedEnergistics2.getItem("part", 1, 440),
                " ", "I",
                'I', Mods.AppliedEnergistics2.getItem("interface"));

        // ME Fluid Interface
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/blocks/fluid_interfaces_interface_alt"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/blocks/fluid_interfaces_interface_part"));
        ModHandler.addShapedNBTClearingRecipe("fluid_interface_to_fluid_interface",
                Mods.AppliedEnergistics2.getItem("fluid_interface"),
                "I", " ",
                'I', Mods.AppliedEnergistics2.getItem("fluid_interface"));
        ModHandler.addShapedNBTClearingRecipe("fluid_interface_to_part_fluid_interface",
                Mods.AppliedEnergistics2.getItem("fluid_interface"),
                " ", "I",
                'I', Mods.AppliedEnergistics2.getItem("part", 1, 441));
        ModHandler.addShapedNBTClearingRecipe("part_fluid_interface_to_part_fluid_interface",
                Mods.AppliedEnergistics2.getItem("part", 1, 441),
                "I", " ",
                'I', Mods.AppliedEnergistics2.getItem("part", 1, 441));
        ModHandler.addShapedNBTClearingRecipe("part_fluid_interface_to_fluid_interface",
                Mods.AppliedEnergistics2.getItem("part", 1, 441),
                " ", "I",
                'I', Mods.AppliedEnergistics2.getItem("fluid_interface"));

        if (AEConfigHolder.enableAE2UELExtended) {
            // ME Delivery Interface
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interfaceimp_alt"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interfaceimp_part"));
            ModHandler.addShapedNBTClearingRecipe("interfaceimp_to_interfaceimp",
                    Mods.AppliedEnergistics2.getItem("interfaceimp"),
                    "I", " ",
                    'I', Mods.AppliedEnergistics2.getItem("interfaceimp"));
            ModHandler.addShapedNBTClearingRecipe("interfaceimp_to_part_interfaceimp",
                    Mods.AppliedEnergistics2.getItem("interfaceimp"),
                    " ", "I",
                    'I', Mods.AppliedEnergistics2.getItem("part", 1, 620));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceimp_to_part_interfaceimp",
                    Mods.AppliedEnergistics2.getItem("part", 1, 620),
                    "I", " ",
                    'I', Mods.AppliedEnergistics2.getItem("part", 1, 620));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceimp_to_interfaceimp",
                    Mods.AppliedEnergistics2.getItem("part", 1, 620),
                    " ", "I",
                    'I', Mods.AppliedEnergistics2.getItem("interfaceimp"));

            // Advanced ME Delivery Interface
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interfaceadv_alt"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interfaceadv_part"));
            ModHandler.addShapedNBTClearingRecipe("interfaceadv_to_interfaceadv",
                    Mods.AppliedEnergistics2.getItem("interfaceadv"),
                    "I", " ",
                    'I', Mods.AppliedEnergistics2.getItem("interfaceadv"));
            ModHandler.addShapedNBTClearingRecipe("interfaceadv_to_part_interfaceadv",
                    Mods.AppliedEnergistics2.getItem("interfaceadv"),
                    " ", "I",
                    'I', Mods.AppliedEnergistics2.getItem("part", 1, 621));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceadv_to_part_interfaceadv",
                    Mods.AppliedEnergistics2.getItem("part", 1, 621),
                    "I", " ",
                    'I', Mods.AppliedEnergistics2.getItem("part", 1, 621));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceadv_to_interfaceadv",
                    Mods.AppliedEnergistics2.getItem("part", 1, 621),
                    " ", "I",
                    'I', Mods.AppliedEnergistics2.getItem("interfaceadv"));

            // Perfect ME Delivery Interface
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interfaceper_alt"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interfaceper_part"));
            ModHandler.addShapedNBTClearingRecipe("interfaceper_to_interfaceper",
                    Mods.AppliedEnergistics2.getItem("interfaceper"),
                    "I", " ",
                    'I', Mods.AppliedEnergistics2.getItem("interfaceper"));
            ModHandler.addShapedNBTClearingRecipe("interfaceper_to_part_interfaceper",
                    Mods.AppliedEnergistics2.getItem("interfaceper"),
                    " ", "I",
                    'I', Mods.AppliedEnergistics2.getItem("part", 1, 622));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceper_to_part_interfaceper",
                    Mods.AppliedEnergistics2.getItem("part", 1, 622),
                    "I", " ",
                    'I', Mods.AppliedEnergistics2.getItem("part", 1, 622));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceper_to_interfaceper",
                    Mods.AppliedEnergistics2.getItem("part", 1, 622),
                    " ", "I",
                    'I', Mods.AppliedEnergistics2.getItem("interfaceper"));

            // ME Patterns Interface
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interfacepatt_alt"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/blocks/interfaces_interfacepatt_part"));
            ModHandler.addShapedNBTClearingRecipe("interfacepatt_to_interfacepatt",
                    Mods.AppliedEnergistics2.getItem("interface_patterns"),
                    "I", " ",
                    'I', Mods.AppliedEnergistics2.getItem("interface_patterns"));
            ModHandler.addShapedNBTClearingRecipe("interfacepatt_to_part_interfacepatt",
                    Mods.AppliedEnergistics2.getItem("interface_patterns"),
                    " ", "I",
                    'I', Mods.AppliedEnergistics2.getItem("part", 1, 623));
            ModHandler.addShapedNBTClearingRecipe("part_interfacepatt_to_part_interfacepatt",
                    Mods.AppliedEnergistics2.getItem("part", 1, 623),
                    "I", " ",
                    'I', Mods.AppliedEnergistics2.getItem("part", 1, 623));
            ModHandler.addShapedNBTClearingRecipe("part_interfacepatt_to_interfacepatt",
                    Mods.AppliedEnergistics2.getItem("part", 1, 623),
                    " ", "I",
                    'I', Mods.AppliedEnergistics2.getItem("interface_patterns"));
        }

        // ME Dual Interface
        if (Mods.AE2FluidCrafting.isModLoaded()) {
            ModHandler.removeRecipeByName(Mods.AE2FluidCrafting.getResource("dual_interface"));
            ModHandler.removeRecipeByName(Mods.AE2FluidCrafting.getResource("dual_interface_alter"));
            ModHandler.removeRecipeByName(Mods.AE2FluidCrafting.getResource("part_dual_interface"));
            ModHandler.addShapedNBTClearingRecipe("dual_interface_to_dual_interface",
                    Mods.AE2FluidCrafting.getItem("dual_interface"),
                    "I", " ",
                    'I', Mods.AE2FluidCrafting.getItem("dual_interface"));
            ModHandler.addShapedNBTClearingRecipe("dual_interface_to_part_dual_interface",
                    Mods.AE2FluidCrafting.getItem("dual_interface"),
                    " ", "I",
                    'I', Mods.AE2FluidCrafting.getItem("part_dual_interface"));
            ModHandler.addShapedNBTClearingRecipe("part_dual_interface_to_part_dual_interface",
                    Mods.AE2FluidCrafting.getItem("part_dual_interface"),
                    "I", " ",
                    'I', Mods.AE2FluidCrafting.getItem("part_dual_interface"));
            ModHandler.addShapedNBTClearingRecipe("part_dual_interface_to_dual_interface",
                    Mods.AE2FluidCrafting.getItem("part_dual_interface"),
                    " ", "I",
                    'I', Mods.AE2FluidCrafting.getItem("dual_interface"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .input("craftInterfaceItem")
                    .input("craftInterfaceFluid")
                    .outputs(Mods.AE2FluidCrafting.getItem("dual_interface"))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier + 1])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .input("craftInterfaceDual")
                    .outputs(Mods.AppliedEnergistics2.getItem("interface"))
                    .outputs(Mods.AppliedEnergistics2.getItem("fluid_interface"))
                    .duration(20).EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Rubber List
        final Map<Material, Integer> rubberMaterials = new Object2ObjectOpenHashMap<>();
        rubberMaterials.put(Materials.Rubber, 432);
        rubberMaterials.put(Materials.SiliconeRubber, 216);
        rubberMaterials.put(Materials.StyreneButadieneRubber, 108);

        // Quartz Fiber
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("network/parts/quartz_fiber_part"));
        ModHandler.addMirroredShapedRecipe("nether_quartz_cutter_wire",
                Mods.AppliedEnergistics2.getItem("part", 1, 140), "Px",
                'P', OreDictUnifier.get(plate, Materials.NetherQuartz));
        ModHandler.addMirroredShapedRecipe("certus_quartz_cutter_wire",
                Mods.AppliedEnergistics2.getItem("part", 1, 140), "Px",
                'P', OreDictUnifier.get(plate, Materials.CertusQuartz));
        ModHandler.addMirroredShapedRecipe("quartzite_cutter_wire",
                Mods.AppliedEnergistics2.getItem("part", 1, 140), "Px",
                'P', OreDictUnifier.get(plate, Materials.Quartzite));
        RecipeMaps.WIREMILL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input("craftStickQuartz", 1)
                .outputs(Mods.AppliedEnergistics2.getItem("part", 2, 140))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Glass Cable
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("network/cables/glass_fluix"));
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("network/cables/glass_fluix_clean"));
        ModHandler.addShapedRecipe("fluix_glass_cable",
                Mods.AppliedEnergistics2.getItem("part", 6, 16),
                "SFS", "CCC", "SFS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'F', OreDictUnifier.get(dust, GTEMaterials.Fluix),
                'C', Mods.AppliedEnergistics2.getItem("part", 1, 140));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(Mods.AppliedEnergistics2.getItem("part", 3, 140))
                .fluidInputs(GTEMaterials.Fluix.getFluid(144))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 6, 16))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(Mods.AppliedEnergistics2.getItem("part", 3, 140))
                .input(dust, GTEMaterials.Fluix, 1)
                .outputs(Mods.AppliedEnergistics2.getItem("part", 6, 16))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftGlassCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 16))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftGlassCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());

        // Covered Cable
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("network/cables/covered_fluix"));
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("network/cables/covered_fluix_clean"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("part", 1, 516))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 4, 36))
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftCoveredCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 36))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftCoveredCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 20 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());
        for (Map.Entry<Material, Integer> materialEntry : rubberMaterials.entrySet()) {
            Material material = materialEntry.getKey();
            Integer materialAmount = materialEntry.getValue();
            ModHandler.addShapedRecipe(material.equals(Materials.Rubber), "fluix_covered_cable_" + material.getName(),
                    Mods.AppliedEnergistics2.getItem("part", 1, 36),
                    "RRR", "CCC", "RRR",
                    'R', new UnificationEntry(plate, material),
                    'C', "craftGlassCable");
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input("craftGlassCable", 3)
                    .fluidInputs(material.getFluid(materialAmount))
                    .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 36))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // Smart Cable
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("network/cables/smart_fluix"));
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("network/cables/smart_fluix_clean"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("part", 1, 76))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 4, 56))
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();
        ModHandler.addShapedRecipe("fluix_smart_cable_1",
                Mods.AppliedEnergistics2.getItem("part", 1, 56),
                " G ", "RCR", " G ",
                'G', OreDictUnifier.get(dust, Materials.Glowstone),
                'R', OreDictUnifier.get(dust, Materials.Redstone),
                'C', Mods.AppliedEnergistics2.getItem("part", 1, 36));
        ModHandler.addShapedRecipe("fluix_smart_cable_2",
                Mods.AppliedEnergistics2.getItem("part", 1, 56),
                " R ", "GCG", " R ",
                'G', OreDictUnifier.get(dust, Materials.Glowstone),
                'R', OreDictUnifier.get(dust, Materials.Redstone),
                'C', Mods.AppliedEnergistics2.getItem("part", 1, 36));
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftSmartCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 56))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftSmartCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 40 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input("craftCoveredCable", 1)
                .input(dust, Materials.Glowstone, 1)
                .input(dust, Materials.Redstone, 1)
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 56))
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
                    .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 56))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // Dense Covered Cable
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cables/dense_covered_fluix"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cables/dense_covered_fluix_clean"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("part", 4, 36))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 516))
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseCoveredCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 516))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseCoveredCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 500 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());
        for (Map.Entry<Material, Integer> materialEntry : rubberMaterials.entrySet()) {
            Material material = materialEntry.getKey();
            Integer materialAmount = materialEntry.getValue();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input("craftGlassCable", 12)
                    .fluidInputs(material.getFluid(materialAmount * 4))
                    .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 516))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // Dense Smart Cable
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cables/dense_smart_fluix"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cables/dense_smart_fluix_clean"));
        ModHandler.addShapedRecipe("fluix_dense_smart_cable_1",
                Mods.AppliedEnergistics2.getItem("part", 1, 76),
                " G ", "RCR", " G ",
                'G', OreDictUnifier.get(dust, Materials.Glowstone),
                'R', OreDictUnifier.get(dust, Materials.Redstone),
                'C', Mods.AppliedEnergistics2.getItem("part", 1, 516));
        ModHandler.addShapedRecipe("fluix_dense_smart_cable_2",
                Mods.AppliedEnergistics2.getItem("part", 1, 76),
                " R ", "GCG", " R ",
                'G', OreDictUnifier.get(dust, Materials.Glowstone),
                'R', OreDictUnifier.get(dust, Materials.Redstone),
                'C', Mods.AppliedEnergistics2.getItem("part", 1, 516));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("part", 4, 56))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 76))
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseSmartCableColors", 1)
                .fluidInputs(Materials.Chlorine.getFluid(25))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 76))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseSmartCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 60 + i))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input("craftDenseCoveredCable", 1)
                .input(dust, Materials.Glowstone, 1)
                .input(dust, Materials.Redstone, 1)
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 76))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input("craftCoveredCable", 4)
                .input(dust, Materials.Glowstone, 4)
                .input(dust, Materials.Redstone, 4)
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 76))
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
                    .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 76))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // Crafting Monitor
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_monitor"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .inputs(Mods.AppliedEnergistics2.getItem("part", 1, 400))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_monitor"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_monitor"))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .outputs(Mods.AppliedEnergistics2.getItem("part", 1, 400))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // Crafting Co-Processing Unit
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_accelerator"));
        ModHandler.addShapelessRecipe("crafting_accelerator",
                Mods.AppliedEnergistics2.getItem("crafting_accelerator"),
                Mods.AppliedEnergistics2.getItem("crafting_unit"),
                Mods.AppliedEnergistics2.getItem("material", 1, 23),
                Mods.AppliedEnergistics2.getItem("material", 1, 22),
                Mods.AppliedEnergistics2.getItem("material", 1, 24));

        if (AEConfigHolder.enableAE2UELExtended) {
            // 4x Crafting Co-Processing Unit
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_accelerator_4x"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 35))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator_4x"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator_4x"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 35))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16x Crafting Co-Processing Unit
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_accelerator_16x"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 36))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator_16x"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator_16x"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 36))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 64x Crafting Co-Processing Unit
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_accelerator_64x"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 37))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator_64x"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator_64x"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 37))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        }

        // 1k Crafting Storage
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_1k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 35))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_1k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_1k"))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 35))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4k Crafting Storage
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_4k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 36))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_4k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_4k"))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 36))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k Crafting Storage
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_16k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 37))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_16k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_16k"))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 37))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k Crafting Storage
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_64k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 38))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_64k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_64k"))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 38))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        if (AEConfigHolder.enableAE2UELExtended) {
            // 1mb Crafting Storage
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_1mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 61))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_1mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_1mb"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 61))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4mb Crafting Storage
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_4mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 62))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_4mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_4mb"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 62))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16mb Crafting Storage
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_16mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 63))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_16mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_16mb"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 63))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 64mb Crafting Storage
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_64mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 64))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_64mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_64mb"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 64))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 256mb Crafting Storage
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_256mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 65))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_256mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_256mb"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 65))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1gb Crafting Storage
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_1gb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 66))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_1gb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_1gb"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 66))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 2gb Crafting Storage
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/crafting/cpu_crafting_storage_15gb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 67))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_storage_15gb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_storage_15gb"))
                    .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 67))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        }
    }
}
