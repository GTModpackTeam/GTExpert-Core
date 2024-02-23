package gtexpert.integration.ae.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.integration.ae.AEUtil.tierMaterials;

import java.util.Arrays;
import java.util.Objects;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.AssemblyLineRecipeBuilder;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.Mods;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.integration.ae.AEConfigHolder;

public class AEItemsRecipe {

    public static void init() {
        // GTE ME Storage Fake Component
        AssemblyLineRecipeBuilder builderGTECore = RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(screw, Materials.Neutronium, 8)
                .input(MetaItems.CRYSTAL_MAINFRAME_UV, 4)
                .fluidInputs(Materials.SolderingAlloy.getFluid(18432))
                .fluidInputs(Materials.Neutronium.getFluid(9216))
                .output(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 1)
                .duration(1200).EUt(VA[UV]);
        if (AEConfigHolder.enableAE2UELExtended) {
            builderGTECore.inputs(Mods.AppliedEnergistics2.getItem("material", 16, 65));
            builderGTECore.inputs(Mods.AppliedEnergistics2.getItem("material", 16, 70));
        } else if (Mods.AEAdditions.isModLoaded()) {
            builderGTECore.inputs(Mods.AEAdditions.getItem("storage.component", 16, 3));
            builderGTECore.inputs(Mods.AEAdditions.getItem("storage.component", 16, 6));
        } else {
            builderGTECore.inputs(Mods.AppliedEnergistics2.getItem("material", 16, 38));
            builderGTECore.inputs(Mods.AppliedEnergistics2.getItem("material", 16, 57));
        }
        builderGTECore.buildAndRegister();

        // 1k Storage Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_1k"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_1k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 35))
                .outputs(Mods.AppliedEnergistics2.getItem("storage_cell_1k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("storage_cell_1k").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 35))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4k Storage Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_4k"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_4k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 36))
                .outputs(Mods.AppliedEnergistics2.getItem("storage_cell_4k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("storage_cell_4k").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 36))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k Storage Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_16k"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_16k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 37))
                .outputs(Mods.AppliedEnergistics2.getItem("storage_cell_16k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("storage_cell_16k").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 37))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k Storage Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_64k"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_64k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 38))
                .outputs(Mods.AppliedEnergistics2.getItem("storage_cell_64k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("storage_cell_64k").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 38))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1k Fluid Storage Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_1k"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_1k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 54))
                .outputs(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_1k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_1k").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 54))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4k Fluid Storage Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_4k"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_4k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 55))
                .outputs(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_4k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_4k").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 55))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k Fluid Storage Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_16k"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_16k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 56))
                .outputs(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_16k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_16k").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 56))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k Fluid Storage Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_64k"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_64k_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 57))
                .outputs(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_64k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_64k").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 57))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 2³ Spatial Storage Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/spatial_storage_cell_2_cubed"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/spatial_storage_cell_2_cubed_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing") :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 32))
                .outputs(Mods.AppliedEnergistics2.getItem("spatial_storage_cell_2_cubed"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("spatial_storage_cell_2_cubed").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing") :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 32))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16³ Spatial Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/spatial_storage_cell_16_cubed"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/spatial_storage_cell_16_cubed_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing") :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 33))
                .outputs(Mods.AppliedEnergistics2.getItem("spatial_storage_cell_16_cubed"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("spatial_storage_cell_16_cubed").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing") :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 33))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 128³ Spatial Cell
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/spatial_storage_cell_128_cubed"));
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/spatial_storage_cell_128_cubed_storage"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing") :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 34))
                .outputs(Mods.AppliedEnergistics2.getItem("spatial_storage_cell_128_cubed"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("spatial_storage_cell_128_cubed").getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY)
                .outputs(Mods.AEAdditions.isModLoaded() ?
                        Mods.AEAdditions.getItem("storage.casing") :
                        Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 34))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // Storage Housing
        ModHandler.removeRecipeByName(
                Mods.AppliedEnergistics2.getResource("network/cells/empty_storage_cell"));
        ModHandler.addShapedRecipe(true, "empty_storage_cell_1",
                Mods.AppliedEnergistics2.getItem("material", 1, 39),
                "DPS", "P P", "SPH",
                'D', "craftingToolScrewdriver",
                'P', OreDictUnifier.get(plate, tierMaterials[GTEValues.ae2VoltageTier - 2]),
                'S', OreDictUnifier.get(screw, tierMaterials[GTEValues.ae2VoltageTier - 2]),
                'H', "craftingToolHardHammer");
        ModHandler.addShapedRecipe("empty_storage_cell_2",
                Mods.AppliedEnergistics2.getItem("material", 1, 39),
                "HPS", "P P", "SPD",
                'D', "craftingToolScrewdriver",
                'P', OreDictUnifier.get(plate, tierMaterials[GTEValues.ae2VoltageTier - 2]),
                'S', OreDictUnifier.get(screw, tierMaterials[GTEValues.ae2VoltageTier - 2]),
                'H', "craftingToolHardHammer");
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(plate, tierMaterials[GTEValues.ae2VoltageTier - 2], 2)
                .input(screw, tierMaterials[GTEValues.ae2VoltageTier - 2], 2)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 39))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .withRecycling()
                .buildAndRegister();

        if (AEConfigHolder.enableAE2UELExtended) {
            // 1mb Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_1mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_1mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 61))
                    .outputs(Mods.AppliedEnergistics2.getItem("storage_cell_1mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("storage_cell_1mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 61))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4mb Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_4mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_4mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 62))
                    .outputs(Mods.AppliedEnergistics2.getItem("storage_cell_4mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("storage_cell_4mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 62))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16mb Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_16mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_16mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 63))
                    .outputs(Mods.AppliedEnergistics2.getItem("storage_cell_16mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("storage_cell_16mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 63))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 64mb Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_64mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_64mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 64))
                    .outputs(Mods.AppliedEnergistics2.getItem("storage_cell_64mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("storage_cell_64mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 64))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 256mb Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_256mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/storage_cell_256mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 65))
                    .outputs(Mods.AppliedEnergistics2.getItem("storage_cell_256mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("storage_cell_256mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing") :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 65))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1mb Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_1mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_1mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 68))
                    .outputs(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_1mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_1mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 68))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4mb Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_4mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_4mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 69))
                    .outputs(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_4mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_4mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 69))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16mb Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_16mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_16mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 70))
                    .outputs(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_16mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_16mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 70))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 64mb Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_64mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_64mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 71))
                    .outputs(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_64mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_64mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 71))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 256mb Fluid Storage Cell
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_256mb"));
            ModHandler.removeRecipeByName(
                    Mods.AppliedEnergistics2.getResource("network/cells/fluid_storage_cell_256mb_storage"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 72))
                    .outputs(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_256mb"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputNBT(Mods.AppliedEnergistics2.getItem("fluid_storage_cell_256mb").getItem(),
                            NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(Mods.AEAdditions.isModLoaded() ?
                            Mods.AEAdditions.getItem("storage.casing", 1, 1) :
                            Mods.AppliedEnergistics2.getItem("material", 1, 39))
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 72))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Formation Core
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("materials/formationcore"));
        ModHandler.addShapedRecipe(true, "formation_core",
                Mods.AppliedEnergistics2.getItem("material", 2, 43),
                "SES", "LQL", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', "gemMaterials.NetherQuartz",
                'E', Mods.AppliedEnergistics2.getItem("material", 1, 24),
                'L', Mods.AppliedEnergistics2.getItem("material", 1, 22));
        ModHandler.addShapedRecipe("formation_core_pure",
                Mods.AppliedEnergistics2.getItem("material", 2, 43),
                "SES", "LQL", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', Mods.AppliedEnergistics2.getItem("material", 1, 11),
                'E', Mods.AppliedEnergistics2.getItem("material", 1, 24),
                'L', Mods.AppliedEnergistics2.getItem("material", 1, 22));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 24))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 22))
                .input("craftNetherQuartz", 1)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 4, 43))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier + 1])
                .buildAndRegister();

        // Annihilation Core
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("materials/annihilationcore"));
        ModHandler.addShapedRecipe(true, "annihilation_core",
                Mods.AppliedEnergistics2.getItem("material", 2, 44),
                "SES", "CQC", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', "gemCertusQuartz",
                'E', Mods.AppliedEnergistics2.getItem("material", 1, 24),
                'C', Mods.AppliedEnergistics2.getItem("material", 1, 23));
        ModHandler.addShapedRecipe("annihilation_core_pure",
                Mods.AppliedEnergistics2.getItem("material", 2, 44),
                "SES", "CQC", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', Mods.AppliedEnergistics2.getItem("material", 1, 10),
                'E', Mods.AppliedEnergistics2.getItem("material", 1, 24),
                'C', Mods.AppliedEnergistics2.getItem("material", 1, 23));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 24))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 23))
                .input("craftCertusQuartz", 1)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 4, 44))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier + 1])
                .buildAndRegister();

        // Matrix Core
        ModHandler.addShapedRecipe(true, "matrix_core", GTEMetaItems.MATRIX_CORE.getStackForm(),
                "SAS", "FQF", "SAS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', Mods.AppliedEnergistics2.getItem("material", 1, 7),
                'A', Mods.AppliedEnergistics2.getItem("material", 2, 44),
                'F', Mods.AppliedEnergistics2.getItem("material", 2, 43));
        ModHandler.addShapedRecipe("matrix_core_pure", GTEMetaItems.MATRIX_CORE.getStackForm(2),
                "SAS", "FQF", "SAS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEValues.ae2VoltageTier - 1]),
                'Q', Mods.AppliedEnergistics2.getItem("material", 1, 12),
                'A', Mods.AppliedEnergistics2.getItem("material", 2, 44),
                'F', Mods.AppliedEnergistics2.getItem("material", 2, 43));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 2)
                .inputs(Mods.AppliedEnergistics2.getItem("material", 2, 44))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 2, 43))
                .input("craftFluix", 1)
                .output(GTEMetaItems.MATRIX_CORE, 4)
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier + 1])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(stick, tierMaterials[GTEValues.ae2VoltageTier - 1], 4)
                .inputs(Mods.AppliedEnergistics2.getItem("material", 2, 24))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 22))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 23))
                .input("craftNetherQuartz", 1)
                .input("craftCertusQuartz", 1)
                .input("craftFluix", 1)
                .output(GTEMetaItems.MATRIX_CORE, 4)
                .duration(100).EUt(VA[GTEValues.ae2VoltageTier + 1])
                .buildAndRegister();

        // Printed Silicon
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEConfigHolder.moveSteelShape ?
                        GTEMetaItems.SHAPE_MOLD_PRINTED_SILICON.getStackForm() :
                        Mods.AppliedEnergistics2.getItem("material", 1, 19))
                .input(plate, Materials.Silicon, 1)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 20))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Logic Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEConfigHolder.moveSteelShape ?
                        GTEMetaItems.SHAPE_MOLD_LOGIC_PROCESSOR.getStackForm() :
                        Mods.AppliedEnergistics2.getItem("material", 1, 15))
                .input(plate, Materials.Gold, 1)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 18))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Calc Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEConfigHolder.moveSteelShape ?
                        GTEMetaItems.SHAPE_MOLD_CALCULATION_PROCESSOR.getStackForm() :
                        Mods.AppliedEnergistics2.getItem("material", 1, 13))
                .input(plate, Materials.CertusQuartz, 1)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 16))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Engineer Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEConfigHolder.moveSteelShape ?
                        GTEMetaItems.SHAPE_MOLD_ENGINEERING_PROCESSOR.getStackForm() :
                        Mods.AppliedEnergistics2.getItem("material", 1, 14))
                .input(plate, Materials.Diamond, 1)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 17))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Logic Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 20))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 18))
                .fluidInputs(Materials.Redstone.getFluid(144))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 22))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Calc Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 20))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 16))
                .fluidInputs(Materials.Redstone.getFluid(144))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 23))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Engineer Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 20))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 17))
                .fluidInputs(Materials.Redstone.getFluid(144))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 24))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .withRecycling()
                .buildAndRegister();

        if (AEConfigHolder.moveSteelShape) {
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
            ModHandler.addShapedRecipe(true, "shape_mold_printed_silicon",
                    GTEMetaItems.SHAPE_MOLD_PRINTED_SILICON.getStackForm(),
                    "h  ", "   ", "S  ",
                    'S', MetaItems.SHAPE_EMPTY);
            ModHandler.addShapelessRecipe("silicon_processor_mold_to_gt",
                    GTEMetaItems.SHAPE_MOLD_PRINTED_SILICON.getStackForm(),
                    Mods.AppliedEnergistics2.getItem("material", 1, 19));

            // Mold (Logic Processor)
            ModHandler.addShapedRecipe(true, "shape_mold_logic_processor",
                    GTEMetaItems.SHAPE_MOLD_LOGIC_PROCESSOR.getStackForm(),
                    " h ", "   ", "S  ",
                    'S', MetaItems.SHAPE_EMPTY);
            ModHandler.addShapelessRecipe("logic_processor_mold_to_gt",
                    GTEMetaItems.SHAPE_MOLD_LOGIC_PROCESSOR.getStackForm(),
                    Mods.AppliedEnergistics2.getItem("material", 1, 15));

            // Mold (Calculation Processor)
            ModHandler.addShapedRecipe(true, "shape_mold_calculation_processor",
                    GTEMetaItems.SHAPE_MOLD_CALCULATION_PROCESSOR.getStackForm(),
                    "   ", "  h", "S  ",
                    'S', MetaItems.SHAPE_EMPTY);
            ModHandler.addShapelessRecipe("calc_processor_mold_to_gt",
                    GTEMetaItems.SHAPE_MOLD_CALCULATION_PROCESSOR.getStackForm(),
                    Mods.AppliedEnergistics2.getItem("material", 1, 13));

            // Mold (Engineering Processor)
            ModHandler.addShapedRecipe(true, "shape_mold_engineering_processor",
                    GTEMetaItems.SHAPE_MOLD_ENGINEERING_PROCESSOR.getStackForm(),
                    "   ", "   ", "S h",
                    'S', MetaItems.SHAPE_EMPTY);
            ModHandler.addShapelessRecipe("engineer_processor_mold_to_gt",
                    GTEMetaItems.SHAPE_MOLD_ENGINEERING_PROCESSOR.getStackForm(),
                    Mods.AppliedEnergistics2.getItem("material", 1, 14));

            // Extruder Shape (Printed Silicon)
            ModHandler.addShapedRecipe(true, "shape_extruder_printed_silicon",
                    GTEMetaItems.SHAPE_EXTRUDER_PRINTED_SILICON.getStackForm(),
                    " x ", " S ", "   ",
                    'S', MetaItems.SHAPE_EMPTY);

            // Extruder Shape (Logic Processor)
            ModHandler.addShapedRecipe(true, "shape_extruder_logic_processor",
                    GTEMetaItems.SHAPE_EXTRUDER_LOGIC_PROCESSOR.getStackForm(),
                    " x ", "S  ", "   ",
                    'S', GTEMetaItems.SHAPE_EXTRUDER_PRINTED_SILICON);

            // Extruder Shape (Calculation Processor)
            ModHandler.addShapedRecipe(true, "shape_extruder_calculation_processor",
                    GTEMetaItems.SHAPE_EXTRUDER_CALCULATION_PROCESSOR.getStackForm(),
                    " x ", " S ", "   ",
                    'S', GTEMetaItems.SHAPE_EXTRUDER_PRINTED_SILICON);

            // Extruder Shape (Engineering Processor)
            ModHandler.addShapedRecipe(true, "shape_extruder_engineering_processor",
                    GTEMetaItems.SHAPE_EXTRUDER_ENGINEERING_PROCESSOR.getStackForm(),
                    " x ", "  S", "   ",
                    'S', GTEMetaItems.SHAPE_EXTRUDER_PRINTED_SILICON);
        } else {
            // Silicon Processor Press
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(lens, Materials.NetherQuartz)
                    .input(block, Materials.Iron, 1)
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 19))
                    .duration(2000).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();

            // Logic Processor Press
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(lens, GTEMaterials.ChargedCertusQuartz)
                    .input(block, Materials.Iron, 1)
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 15))
                    .duration(2000).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();

            // Calc Processor Press
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(lens, Materials.CertusQuartz)
                    .input(block, Materials.Iron, 1)
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 13))
                    .duration(2000).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();

            // Engineer Processor Press
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(lens, GTEMaterials.Fluix)
                    .input(block, Materials.Iron, 1)
                    .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 14))
                    .duration(2000).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }
    }
}
