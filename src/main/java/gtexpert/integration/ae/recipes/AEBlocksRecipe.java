package gtexpert.integration.ae.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.integration.ae.AEUtil.*;

import java.util.Map;
import java.util.stream.IntStream;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

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
import gtexpert.api.util.GTEUtility;
import gtexpert.integration.ae.AEConfigHolder;

import appeng.api.util.AEColor;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public class AEBlocksRecipe {

    public static void init() {
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
                GTEUtility.getModItem(GTEValues.MODID_AE, "interface"),
                "I", " ",
                'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interface"));
        ModHandler.addShapedNBTClearingRecipe("interface_to_part_interface",
                GTEUtility.getModItem(GTEValues.MODID_AE, "interface"),
                " ", "I",
                'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 440));
        ModHandler.addShapedNBTClearingRecipe("part_interface_to_part_interface",
                GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 440),
                "I", " ",
                'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 440));
        ModHandler.addShapedNBTClearingRecipe("part_interface_to_interface",
                GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 440),
                " ", "I",
                'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interface"));

        // ME Fluid Interface
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/blocks/fluid_interfaces_interface_alt"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AE, "network/blocks/fluid_interfaces_interface_part"));
        ModHandler.addShapedNBTClearingRecipe("fluid_interface_to_fluid_interface",
                GTEUtility.getModItem(GTEValues.MODID_AE, "fluid_interface"),
                "I", " ",
                'I', GTEUtility.getModItem(GTEValues.MODID_AE, "fluid_interface"));
        ModHandler.addShapedNBTClearingRecipe("fluid_interface_to_part_fluid_interface",
                GTEUtility.getModItem(GTEValues.MODID_AE, "fluid_interface"),
                " ", "I",
                'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 441));
        ModHandler.addShapedNBTClearingRecipe("part_fluid_interface_to_part_fluid_interface",
                GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 441),
                "I", " ",
                'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 441));
        ModHandler.addShapedNBTClearingRecipe("part_fluid_interface_to_fluid_interface",
                GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 441),
                " ", "I",
                'I', GTEUtility.getModItem(GTEValues.MODID_AE, "fluid_interface"));

        if (AEConfigHolder.enableAE2UELExtended) {
            // ME Delivery Interface
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceimp_alt"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceimp_part"));
            ModHandler.addShapedNBTClearingRecipe("interfaceimp_to_interfaceimp",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceimp"),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceimp"));
            ModHandler.addShapedNBTClearingRecipe("interfaceimp_to_part_interfaceimp",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceimp"),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 620));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceimp_to_part_interfaceimp",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 620),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 620));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceimp_to_interfaceimp",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 620),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceimp"));

            // Advanced ME Delivery Interface
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceadv_alt"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceadv_part"));
            ModHandler.addShapedNBTClearingRecipe("interfaceadv_to_interfaceadv",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceadv"),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceadv"));
            ModHandler.addShapedNBTClearingRecipe("interfaceadv_to_part_interfaceadv",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceadv"),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 621));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceadv_to_part_interfaceadv",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 621),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 621));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceadv_to_interfaceadv",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 621),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceadv"));

            // Perfect ME Delivery Interface
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceper_alt"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfaceper_part"));
            ModHandler.addShapedNBTClearingRecipe("interfaceper_to_interfaceper",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceper"),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceper"));
            ModHandler.addShapedNBTClearingRecipe("interfaceper_to_part_interfaceper",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceper"),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 622));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceper_to_part_interfaceper",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 622),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 622));
            ModHandler.addShapedNBTClearingRecipe("part_interfaceper_to_interfaceper",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 622),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interfaceper"));

            // ME Patterns Interface
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfacepatt_alt"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/blocks/interfaces_interfacepatt_part"));
            ModHandler.addShapedNBTClearingRecipe("interfacepatt_to_interfacepatt",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "interface_patterns"),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interface_patterns"));
            ModHandler.addShapedNBTClearingRecipe("interfacepatt_to_part_interfacepatt",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "interface_patterns"),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 623));
            ModHandler.addShapedNBTClearingRecipe("part_interfacepatt_to_part_interfacepatt",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 623),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 623));
            ModHandler.addShapedNBTClearingRecipe("part_interfacepatt_to_interfacepatt",
                    GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 623),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AE, "interface_patterns"));
        }

        // ME Dual Interface
        if (Loader.isModLoaded(GTEValues.MODID_AEFC)) {
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEFC, "dual_interface"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEFC, "dual_interface_alter"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEFC, "part_dual_interface"));
            ModHandler.addShapedNBTClearingRecipe("dual_interface_to_dual_interface",
                    GTEUtility.getModItem(GTEValues.MODID_AEFC, "dual_interface"),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AEFC, "dual_interface"));
            ModHandler.addShapedNBTClearingRecipe("dual_interface_to_part_dual_interface",
                    GTEUtility.getModItem(GTEValues.MODID_AEFC, "dual_interface"),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AEFC, "part_dual_interface"));
            ModHandler.addShapedNBTClearingRecipe("part_dual_interface_to_part_dual_interface",
                    GTEUtility.getModItem(GTEValues.MODID_AEFC, "part_dual_interface"),
                    "I", " ",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AEFC, "part_dual_interface"));
            ModHandler.addShapedNBTClearingRecipe("part_dual_interface_to_dual_interface",
                    GTEUtility.getModItem(GTEValues.MODID_AEFC, "part_dual_interface"),
                    " ", "I",
                    'I', GTEUtility.getModItem(GTEValues.MODID_AEFC, "dual_interface"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .input("craftInterfaceItem")
                    .input("craftInterfaceFluid")
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AEFC, "dual_interface"))
                    .duration(20).EUt(VA[GTEValues.ae2VoltageTier + 1])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .input("craftInterfaceDual")
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "interface"))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "fluid_interface"))
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
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 16))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftGlassCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, i))
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
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 36))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftCoveredCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 20 + i))
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
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 56))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftSmartCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 40 + i))
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
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 516))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseCoveredCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 500 + i))
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
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 76))
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();
        IntStream.range(0, Materials.CHEMICAL_DYES.length).forEach(i -> RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input("craftDenseSmartCable")
                .fluidInputs(Materials.CHEMICAL_DYES[i].getFluid(18))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "part", 1, 60 + i))
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

        if (AEConfigHolder.enableAE2UELExtended) {
            // 1mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_1mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 61))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_1mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_1mb"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 61))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_4mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 62))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_4mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_4mb"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 62))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_16mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 63))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_16mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_16mb"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 63))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 64mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_64mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 64))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_64mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_64mb"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 64))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 256mb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_256mb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 65))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_256mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_256mb"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 65))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1gb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_1gb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 66))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_1gb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_1gb"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 66))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 2gb Crafting Storage
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_AE, "network/crafting/cpu_crafting_storage_15gb"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 67))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_15gb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_storage_15gb"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 67))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        }

        if (GTEValues.isModLoadedAEACPU()) {
            // 256k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_256k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component"))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_EXCPU, "crafting_storage_256k"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_EXCPU, "crafting_storage_256k"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1024k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_1024k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_EXCPU, "crafting_storage_1024k"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_EXCPU, "crafting_storage_1024k"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4096k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_4096k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_EXCPU, "crafting_storage_4096k"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_EXCPU, "crafting_storage_4096k"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16384k Crafting Storage
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPU, "crafting_storage_16384k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_EXCPU, "crafting_storage_16384k"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(GTEUtility.getModItem(GTEValues.MODID_EXCPU, "crafting_storage_16384k"))
                    .outputs(aeBlocks.craftingUnit().maybeStack(1).get())
                    .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        }
    }
}
