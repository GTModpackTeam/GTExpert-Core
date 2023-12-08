package gtexpert.loaders.recipe;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.GTEConfigHolder.ceuOverride;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.AssemblyLineRecipeBuilder;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.metatileentities.GTEMultiMetaTileEntities;

public class GTERecipeLoader {

    public static void init() {
        materials();
        items();
        blocks();
        tools();
        end_contents();
    }

    private static void materials() {
        // Osmium
        Materials.Osmium.getProperty(PropertyKey.ORE).setOreByProducts(Materials.Iridium);

        // Iridium
        Materials.Iridium.getProperty(PropertyKey.ORE).setOreByProducts(Materials.Platinum, Materials.Osmium);

        // Nether Star Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Materials.Diamond, 1)
                .input(dust, Materials.Iridium, 1)
                .fluidInputs(Materials.NetherAir.getFluid(8000))
                .fluidInputs(Materials.RocketFuel.getFluid(1000))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, Materials.NetherStar, 2)
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Ender Eye
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Materials.EnderPearl.getFluid(144))
                .fluidInputs(Materials.Blaze.getFluid(144))
                .fluidOutputs(Materials.EnderEye.getFluid(144))
                .duration(50).EUt(VA[HV])
                .buildAndRegister();

        // NM_HEA_NPs Dust
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.NM_HEA_NPs, 8)
                .output(dust, Materials.Gold, 1)
                .output(dust, Materials.Silver, 1)
                .output(dust, Materials.Ruthenium, 1)
                .output(dust, Materials.Rhodium, 1)
                .output(dust, Materials.Palladium, 1)
                .output(dust, Materials.Osmium, 1)
                .fluidOutputs(Materials.Iridium.getFluid(144))
                .fluidOutputs(Materials.Platinum.getFluid(144))
                .duration(10).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.NM_HEA_NPs, 8)
                .output(dust, Materials.Gold, 1)
                .output(dust, Materials.Silver, 1)
                .output(dust, Materials.Ruthenium, 1)
                .output(dust, Materials.Rhodium, 1)
                .output(dust, Materials.Palladium, 1)
                .output(dust, Materials.Osmium, 1)
                .fluidOutputs(Materials.Iridium.getFluid(144))
                .fluidOutputs(Materials.Platinum.getFluid(144))
                .duration(10).EUt(VA[LV])
                .buildAndRegister();

        // Naquadah Rocket Fuel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.NaquadahEnriched.getFluid(1296))
                .fluidInputs(Materials.RocketFuel.getFluid(5000))
                .fluidOutputs(GTEMaterials.NaquadahRocketFuel.getFluid(6000))
                .duration(20).EUt(VA[IV])
                .buildAndRegister();
        RecipeMaps.COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(GTEMaterials.NaquadahRocketFuel.getFluid(1))
                .duration(750).EUt(32)
                .buildAndRegister();

        // Liquid Air, Liquid Nether Air, Liquid Ender Air
        GTERecipeMaps.ADVANCED_GAS_COLLECTOR_RECIPES.recipeBuilder()
                .input(dust, Materials.Stone, 32)
                .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 5000))
                .fluidOutputs(Materials.Helium.getFluid(2500))
                .fluidOutputs(Materials.LiquidAir.getFluid(10000))
                .duration(200).EUt(VA[ZPM])
                .buildAndRegister();
        GTERecipeMaps.ADVANCED_GAS_COLLECTOR_RECIPES.recipeBuilder()
                .input(dust, Materials.Netherrack, 32)
                .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 5000))
                .fluidOutputs(Materials.Helium.getFluid(2500))
                .fluidOutputs(Materials.LiquidNetherAir.getFluid(10000))
                .duration(200).EUt(VA[ZPM])
                .buildAndRegister();
        GTERecipeMaps.ADVANCED_GAS_COLLECTOR_RECIPES.recipeBuilder()
                .input(dust, Materials.Endstone, 32)
                .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 5000))
                .fluidOutputs(Materials.Helium.getFluid(2500))
                .fluidOutputs(Materials.LiquidEnderAir.getFluid(10000))
                .duration(200).EUt(VA[ZPM])
                .buildAndRegister();

        if (GTEValues.isModLoadedDEDA()) {
            GTERecipeMaps.ADVANCED_GAS_COLLECTOR_RECIPES.recipeBuilder()
                    .input(dust, Materials.Stone, 32)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(1000))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 250))
                    .fluidOutputs(Materials.LiquidAir.getFluid(10000))
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
            GTERecipeMaps.ADVANCED_GAS_COLLECTOR_RECIPES.recipeBuilder()
                    .input(dust, Materials.Netherrack, 32)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(1000))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 250))
                    .fluidOutputs(Materials.LiquidNetherAir.getFluid(10000))
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
            GTERecipeMaps.ADVANCED_GAS_COLLECTOR_RECIPES.recipeBuilder()
                    .input(dust, Materials.Endstone, 32)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(1000))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 250))
                    .fluidOutputs(Materials.LiquidEnderAir.getFluid(10000))
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
        }

        // Netherrack Dust, Endstone Dust
        RecipeMaps.ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(new ItemStack(Blocks.NETHERRACK, 1))
                .outputs(new ItemStack(Blocks.NETHERRACK, 1))
                .duration(16).EUt(VA[LuV])
                .buildAndRegister();
        RecipeMaps.ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(new ItemStack(Blocks.END_STONE, 1))
                .outputs(new ItemStack(Blocks.END_STONE, 1))
                .duration(16).EUt(VA[ZPM])
                .buildAndRegister();
    }

    private static void items() {
        if (ceuOverride.hardPrimitiveParts) {
            ModHandler.addShapedRecipe(true, "primitive_motor",
                    GTEMetaItems.PRIMITIVE_MOTOR.getStackForm(), "CWR", "WMW", "RWC",
                    'R', new UnificationEntry(stick, Materials.Bronze),
                    'M', new UnificationEntry(stick, Materials.IronMagnetic),
                    'W', new UnificationEntry(wireGtSingle, Materials.Tin),
                    'C', new UnificationEntry(cableGtSingle, Materials.Lead));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, Materials.Lead, 2)
                    .input(stick, Materials.Bronze, 2)
                    .input(stick, Materials.IronMagnetic)
                    .input(wireGtSingle, Materials.Tin, 4)
                    .output(GTEMetaItems.PRIMITIVE_MOTOR)
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_piston",
                    GTEMetaItems.PRIMITIVE_PISTON.getStackForm(), "PPP", "CRR", "CMG",
                    'R', new UnificationEntry(stick, Materials.Bronze),
                    'G', new UnificationEntry(gearSmall, Materials.Bronze),
                    'P', new UnificationEntry(plate, Materials.Bronze),
                    'C', new UnificationEntry(cableGtSingle, Materials.Lead),
                    'M', GTEMetaItems.PRIMITIVE_MOTOR.getStackForm());
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(stick, Materials.Bronze, 2)
                    .input(cableGtSingle, Materials.Lead, 2)
                    .input(plate, Materials.Bronze, 3)
                    .input(gearSmall, Materials.Bronze)
                    .input(GTEMetaItems.PRIMITIVE_MOTOR)
                    .output(GTEMetaItems.PRIMITIVE_PISTON)
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_pump",
                    GTEMetaItems.PRIMITIVE_PUMP.getStackForm(), "SRO", "dPw", "OMC",
                    'R', new UnificationEntry(rotor, Materials.Bronze),
                    'S', new UnificationEntry(screw, Materials.Bronze),
                    'O', new UnificationEntry(ring, Materials.Rubber),
                    'P', new UnificationEntry(pipeNormalFluid, Materials.Copper),
                    'C', new UnificationEntry(cableGtSingle, Materials.Lead),
                    'M', GTEMetaItems.PRIMITIVE_MOTOR.getStackForm());
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, Materials.Lead)
                    .input(pipeNormalFluid, Materials.Copper)
                    .input(screw, Materials.Bronze)
                    .input(rotor, Materials.Bronze)
                    .input(ring, Materials.Rubber, 2)
                    .input(GTEMetaItems.PRIMITIVE_MOTOR)
                    .output(GTEMetaItems.PRIMITIVE_PUMP)
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_conveyor",
                    GTEMetaItems.PRIMITIVE_CONVEYOR.getStackForm(), "PPP", "MCM", "PPP",
                    'P', new UnificationEntry(plate, Materials.Rubber),
                    'C', new UnificationEntry(cableGtSingle, Materials.Lead),
                    'M', GTEMetaItems.PRIMITIVE_MOTOR.getStackForm());
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, Materials.Lead)
                    .inputs(GTEMetaItems.PRIMITIVE_MOTOR.getStackForm(2))
                    .fluidInputs(Materials.Rubber.getFluid(L * 6))
                    .circuitMeta(1)
                    .output(GTEMetaItems.PRIMITIVE_CONVEYOR)
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_robot_arm",
                    GTEMetaItems.PRIMITIVE_ROBOT_ARM.getStackForm(), "CCC", "MRM", "PUR",
                    'R', new UnificationEntry(stick, Materials.Bronze),
                    'C', new UnificationEntry(cableGtSingle, Materials.Lead),
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'P', GTEMetaItems.PRIMITIVE_PISTON.getStackForm(),
                    'M', GTEMetaItems.PRIMITIVE_MOTOR.getStackForm());
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, Materials.Lead, 3)
                    .input(stick, Materials.Bronze, 2)
                    .input(GTEMetaItems.PRIMITIVE_MOTOR, 2)
                    .input(GTEMetaItems.PRIMITIVE_PISTON)
                    .input(circuit, MarkerMaterials.Tier.ULV)
                    .output(GTEMetaItems.PRIMITIVE_ROBOT_ARM)
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_fluid_regulator",
                    GTEMetaItems.PRIMITIVE_FLUID_REGULATOR.getStackForm(), " U ", "dPw", " U ",
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'P', GTEMetaItems.PRIMITIVE_PUMP.getStackForm());
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(GTEMetaItems.PRIMITIVE_PUMP)
                    .input(circuit, MarkerMaterials.Tier.ULV, 2)
                    .circuitMeta(1)
                    .output(GTEMetaItems.PRIMITIVE_FLUID_REGULATOR)
                    .EUt(VA[ULV])
                    .duration(400)
                    .withRecycling()
                    .buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_field_generator",
                    GTEMetaItems.PRIMITIVE_FIELD_GENERATOR.getStackForm(), "CPC", "UGU", "CPC",
                    'C', new UnificationEntry(wireGtDouble, Materials.RedAlloy),
                    'P', new UnificationEntry(plate, Materials.Bronze),
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'G', new UnificationEntry(gem, Materials.Lapis));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(gem, Materials.Lapis)
                    .input(plate, Materials.Bronze, 2)
                    .input(circuit, MarkerMaterials.Tier.ULV, 2)
                    .input(wireGtDouble, Materials.RedAlloy, 4)
                    .output(GTEMetaItems.PRIMITIVE_FIELD_GENERATOR)
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_emitter",
                    GTEMetaItems.PRIMITIVE_EMITTER.getStackForm(), "CSU", "SGS", "USC",
                    'C', new UnificationEntry(cableGtSingle, Materials.RedAlloy),
                    'S', new UnificationEntry(stick, Materials.Bronze),
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'G', new UnificationEntry(gem, Materials.Lapis));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(stick, Materials.Bronze, 4)
                    .input(cableGtSingle, Materials.RedAlloy, 2)
                    .input(circuit, MarkerMaterials.Tier.ULV, 2)
                    .input(gem, Materials.Lapis)
                    .output(GTEMetaItems.PRIMITIVE_EMITTER)
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_sensor",
                    GTEMetaItems.PRIMITIVE_SENSOR.getStackForm(), "P G", "PS ", "UPP",
                    'P', new UnificationEntry(plate, Materials.Bronze),
                    'S', new UnificationEntry(stick, Materials.Bronze),
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'G', new UnificationEntry(gem, Materials.Lapis));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(stick, Materials.Bronze)
                    .input(plate, Materials.Bronze, 4)
                    .input(circuit, MarkerMaterials.Tier.ULV)
                    .input(gem, Materials.Lapis)
                    .output(GTEMetaItems.PRIMITIVE_SENSOR)
                    .duration(100).EUt(VA[ULV]).buildAndRegister();
        } else {
            ModHandler.addShapedRecipe(true, "primitive_motor",
                    GTEMetaItems.PRIMITIVE_MOTOR.getStackForm(), "WR", "MW",
                    'R', new UnificationEntry(stick, Materials.Bronze),
                    'M', new UnificationEntry(stick, Materials.IronMagnetic),
                    'W', new UnificationEntry(wireGtSingle, Materials.Tin));

            ModHandler.addShapedRecipe(true, "primitive_piston",
                    GTEMetaItems.PRIMITIVE_PISTON.getStackForm(), "PR", "MG",
                    'R', new UnificationEntry(stick, Materials.Bronze),
                    'G', new UnificationEntry(gearSmall, Materials.Bronze),
                    'P', new UnificationEntry(plate, Materials.Bronze),
                    'M', GTEMetaItems.PRIMITIVE_MOTOR.getStackForm());

            ModHandler.addShapedRecipe(true, "primitive_pump",
                    GTEMetaItems.PRIMITIVE_PUMP.getStackForm(), "PR", "MO",
                    'R', new UnificationEntry(rotor, Materials.Bronze),
                    'O', new UnificationEntry(ring, Materials.Rubber),
                    'P', new UnificationEntry(pipeNormalFluid, Materials.Copper),
                    'M', GTEMetaItems.PRIMITIVE_MOTOR.getStackForm());

            ModHandler.addShapedRecipe(true, "primitive_conveyor",
                    GTEMetaItems.PRIMITIVE_CONVEYOR.getStackForm(), "PC", "MP",
                    'P', new UnificationEntry(plate, Materials.Rubber),
                    'C', new UnificationEntry(cableGtSingle, Materials.Lead),
                    'M', GTEMetaItems.PRIMITIVE_MOTOR.getStackForm());

            ModHandler.addShapedRecipe(true, "primitive_robot_arm",
                    GTEMetaItems.PRIMITIVE_ROBOT_ARM.getStackForm(), "PR", "MC",
                    'R', new UnificationEntry(stick, Materials.Bronze),
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'P', GTEMetaItems.PRIMITIVE_PISTON.getStackForm(),
                    'M', GTEMetaItems.PRIMITIVE_MOTOR.getStackForm());

            ModHandler.addShapedRecipe(true, "primitive_fluid_regulator",
                    GTEMetaItems.PRIMITIVE_FLUID_REGULATOR.getStackForm(), "PC", "Cd",
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'P', GTEMetaItems.PRIMITIVE_PUMP.getStackForm());

            ModHandler.addShapedRecipe(true, "primitive_field_generator",
                    GTEMetaItems.PRIMITIVE_FIELD_GENERATOR.getStackForm(), "CP", "UG",
                    'C', new UnificationEntry(wireGtDouble, Materials.RedAlloy),
                    'P', new UnificationEntry(plate, Materials.Bronze),
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'G', new UnificationEntry(gem, Materials.Lapis));

            ModHandler.addShapedRecipe(true, "primitive_emitter",
                    GTEMetaItems.PRIMITIVE_EMITTER.getStackForm(), "CS", "UG",
                    'C', new UnificationEntry(cableGtSingle, Materials.RedAlloy),
                    'S', new UnificationEntry(stick, Materials.Bronze),
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'G', new UnificationEntry(gem, Materials.Lapis));

            ModHandler.addShapedRecipe(true, "primitive_sensor",
                    GTEMetaItems.PRIMITIVE_SENSOR.getStackForm(), "PG", "US",
                    'P', new UnificationEntry(plate, Materials.Bronze),
                    'S', new UnificationEntry(stick, Materials.Bronze),
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'G', new UnificationEntry(gem, Materials.Lapis));
        }

        if (!ConfigHolder.machines.enableHighTierSolars) return;
        if (ceuOverride.hardSolarPanel) {
            // Remove solar panels
            ModHandler.removeRecipeByOutput(MetaItems.COVER_SOLAR_PANEL.getStackForm());
            ModHandler.removeRecipeByOutput(MetaItems.COVER_SOLAR_PANEL_ULV.getStackForm());
            ModHandler.removeRecipeByOutput(MetaItems.COVER_SOLAR_PANEL_LV.getStackForm());

            // Solar Panel
            ModHandler.addShapedRecipe("solar_panel_basic",
                    MetaItems.COVER_SOLAR_PANEL.getStackForm(), "SGS", "CFC",
                    'S', MetaItems.SILICON_WAFER,
                    'G', paneGlass,
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'F', MetaItems.CARBON_FIBER_PLATE);
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(MetaItems.SILICON_WAFER, 2)
                    .input("paneGlass", 1)
                    .input(circuit, MarkerMaterials.Tier.ULV, 2)
                    .input(MetaItems.CARBON_FIBER_PLATE, 1)
                    .output(MetaItems.COVER_SOLAR_PANEL, 1)
                    .duration(20).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(MetaItems.PHOSPHORUS_WAFER, 2)
                    .input("paneGlass", 4)
                    .input(circuit, MarkerMaterials.Tier.ULV, 8)
                    .input(MetaItems.CARBON_FIBER_PLATE, 4)
                    .output(MetaItems.COVER_SOLAR_PANEL, 4)
                    .duration(20).EUt(VA[MV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(MetaItems.NAQUADAH_WAFER, 2)
                    .input("paneGlass", 8)
                    .input(circuit, MarkerMaterials.Tier.ULV, 16)
                    .input(MetaItems.CARBON_FIBER_PLATE, 8)
                    .output(MetaItems.COVER_SOLAR_PANEL, 8)
                    .duration(20).EUt(VA[EV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(MetaItems.NEUTRONIUM_WAFER, 2)
                    .input("paneGlass", 16)
                    .input(circuit, MarkerMaterials.Tier.ULV, 32)
                    .input(MetaItems.CARBON_FIBER_PLATE, 16)
                    .output(MetaItems.COVER_SOLAR_PANEL, 16)
                    .duration(20).EUt(VA[LuV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input(MetaItems.SILICON_WAFER, 16)
                    .input("paneGlass", 8)
                    .input(circuit, MarkerMaterials.Tier.ULV, 16)
                    .input(MetaItems.CARBON_FIBER_PLATE, 8)
                    .output(MetaItems.COVER_SOLAR_PANEL, 8)
                    .duration(20).EUt(VA[LV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(MetaItems.SILICON_WAFER, 32)
                    .input("paneGlass", 16)
                    .input(circuit, MarkerMaterials.Tier.ULV, 32)
                    .input(MetaItems.CARBON_FIBER_PLATE, 16)
                    .output(MetaItems.COVER_SOLAR_PANEL, 16)
                    .duration(20).EUt(VA[MV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(MetaItems.SILICON_WAFER, 64)
                    .input("paneGlass", 32)
                    .input(circuit, MarkerMaterials.Tier.ULV, 64)
                    .input(MetaItems.CARBON_FIBER_PLATE, 32)
                    .output(MetaItems.COVER_SOLAR_PANEL, 32)
                    .duration(20).EUt(VA[HV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(5)
                    .input(MetaItems.SILICON_WAFER, 64)
                    .input(MetaItems.SILICON_WAFER, 64)
                    .input("paneGlass", 64)
                    .input(circuit, MarkerMaterials.Tier.ULV, 64)
                    .input(circuit, MarkerMaterials.Tier.ULV, 64)
                    .input(MetaItems.CARBON_FIBER_PLATE, 64)
                    .output(MetaItems.COVER_SOLAR_PANEL, 64)
                    .duration(20).EUt(VA[EV])
                    .buildAndRegister();

            // Solar Panel (8V)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaItems.COVER_SOLAR_PANEL, 8)
                    .input(Blocks.DAYLIGHT_DETECTOR, 8)
                    .input(MetaItems.NAND_CHIP_ULV, 4)
                    .input(MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT, 4)
                    .input(Blocks.GLASS)
                    .input(MetaTileEntities.TRANSFORMER[0])
                    .fluidInputs(Materials.Silicon.getFluid(L))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(L))
                    .output(MetaItems.COVER_SOLAR_PANEL_ULV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(MetaItems.COVER_SOLAR_PANEL.getStackForm())
                            .CWUt(32).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (LV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaItems.COVER_SOLAR_PANEL_ULV, 4)
                    .input(MetaItems.SENSOR_LV, 8)
                    .input(MetaItems.INTEGRATED_CIRCUIT_LV, 4)
                    .input(MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT, 8)
                    .inputs(Loader.isModLoaded(GTEValues.MODID_AE) ?
                            getModItem(GTEValues.MODID_AE, "quartz_glass", 1, 0) :
                            new ItemStack(Blocks.GLASS))
                    .input(MetaTileEntities.TRANSFORMER[1])
                    .fluidInputs(Materials.Silicon.getFluid(L << 2))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(L << 2))
                    .output(MetaItems.COVER_SOLAR_PANEL_LV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(MetaItems.COVER_SOLAR_PANEL_ULV.getStackForm())
                            .CWUt(32).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (MV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaItems.COVER_SOLAR_PANEL_LV, 4)
                    .input(MetaItems.SENSOR_MV, 8)
                    .input(MetaItems.INTEGRATED_CIRCUIT_MV, 4)
                    .input(MetaItems.LOW_POWER_INTEGRATED_CIRCUIT, 4)
                    .inputs(Loader.isModLoaded(GTEValues.MODID_EIO) ?
                            getModItem(GTEValues.MODID_EIO, "block_fused_quartz", 1, 0) :
                            new ItemStack(Blocks.GLASS))
                    .input(MetaTileEntities.TRANSFORMER[2])
                    .fluidInputs(Materials.Silicon.getFluid(L << 3))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(L << 3))
                    .output(MetaItems.COVER_SOLAR_PANEL_MV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(MetaItems.COVER_SOLAR_PANEL_LV.getStackForm())
                            .CWUt(32).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (HV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaItems.COVER_SOLAR_PANEL_MV, 4)
                    .input(MetaItems.SENSOR_HV, 8)
                    .input(MetaItems.INTEGRATED_CIRCUIT_HV, 4)
                    .input(MetaItems.LOW_POWER_INTEGRATED_CIRCUIT, 8)
                    .inputs(Loader.isModLoaded(GTEValues.MODID_EIO) ?
                            getModItem(GTEValues.MODID_EIO, "block_fused_quartz", 1, 0) :
                            new ItemStack(Blocks.GLASS))
                    .input(MetaTileEntities.TRANSFORMER[3])
                    .fluidInputs(Materials.Silicon.getFluid(L << 4))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(L << 4))
                    .output(MetaItems.COVER_SOLAR_PANEL_HV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(MetaItems.COVER_SOLAR_PANEL_MV.getStackForm())
                            .CWUt(48).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (EV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaItems.COVER_SOLAR_PANEL_HV, 4)
                    .input(MetaItems.SENSOR_EV, 8)
                    .input(MetaItems.WORKSTATION_EV, 4)
                    .input(MetaItems.POWER_INTEGRATED_CIRCUIT, 4)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 0)
                    .input(MetaTileEntities.TRANSFORMER[4])
                    .fluidInputs(Materials.Silicon.getFluid(L << 5))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(L << 5))
                    .output(MetaItems.COVER_SOLAR_PANEL_EV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(MetaItems.COVER_SOLAR_PANEL_HV.getStackForm())
                            .CWUt(48).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (IV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaItems.COVER_SOLAR_PANEL_EV, 4)
                    .input(MetaItems.SENSOR_IV, 8)
                    .input(MetaItems.MAINFRAME_IV, 4)
                    .input(MetaItems.POWER_INTEGRATED_CIRCUIT, 8)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 0)
                    .input(MetaTileEntities.TRANSFORMER[5])
                    .fluidInputs(Materials.Silicon.getFluid(L << 6))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(L << 6))
                    .output(MetaItems.COVER_SOLAR_PANEL_IV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(MetaItems.COVER_SOLAR_PANEL_EV.getStackForm())
                            .CWUt(48).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (LuV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaItems.COVER_SOLAR_PANEL_IV, 4)
                    .input(MetaItems.SENSOR_LuV, 8)
                    .input(MetaItems.NANO_MAINFRAME_LUV, 4)
                    .input(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT, 8)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 1)
                    .input(MetaTileEntities.TRANSFORMER[6])
                    .fluidInputs(Materials.Silicon.getFluid(L << 7))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(L << 7))
                    .output(MetaItems.COVER_SOLAR_PANEL_LUV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(MetaItems.COVER_SOLAR_PANEL_IV.getStackForm())
                            .CWUt(64).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (ZPM)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaItems.COVER_SOLAR_PANEL_LUV, 4)
                    .input(MetaItems.SENSOR_ZPM, 8)
                    .input(MetaItems.QUANTUM_MAINFRAME_ZPM, 4)
                    .input(MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT, 16)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 1)
                    .input(MetaTileEntities.TRANSFORMER[7])
                    .fluidInputs(Materials.Silicon.getFluid(L << 8))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(L << 8))
                    .output(MetaItems.COVER_SOLAR_PANEL_ZPM)
                    .duration(100).EUt(VA[ZPM])
                    .stationResearch(b -> b.researchStack(MetaItems.COVER_SOLAR_PANEL_LUV.getStackForm())
                            .CWUt(64).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (UV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaItems.COVER_SOLAR_PANEL_ZPM, 4)
                    .input(MetaItems.SENSOR_UV, 8)
                    .input(MetaItems.CRYSTAL_MAINFRAME_UV, 4)
                    .input(MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 1)
                    .input(MetaTileEntities.TRANSFORMER[8])
                    .fluidInputs(Materials.Silicon.getFluid(L << 9))
                    .fluidInputs(Materials.SolderingAlloy.getFluid(L << 9))
                    .output(MetaItems.COVER_SOLAR_PANEL_UV)
                    .duration(100).EUt(VA[UV])
                    .stationResearch(b -> b.researchStack(MetaItems.COVER_SOLAR_PANEL_ZPM.getStackForm())
                            .CWUt(64).EUt(VA[LuV]))
                    .buildAndRegister();
        } else {
            // Solar Panel (8V)
            ModHandler.addShapedRecipe("solar_panel_basic_gt5u",
                    MetaItems.COVER_SOLAR_PANEL_ULV.getStackForm(), "SSS", "SCS", "SSS",
                    'S', MetaItems.COVER_SOLAR_PANEL,
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.HV));

            // Solar Panel (LV)
            ModHandler.addShapedRecipe("solar_panel_lv_gt5u",
                    MetaItems.COVER_SOLAR_PANEL_LV.getStackForm(), " S ", "SCS", " S ",
                    'S', MetaItems.COVER_SOLAR_PANEL_ULV,
                    'C', MetaTileEntities.TRANSFORMER[0].getStackForm());

            // Solar Panel (MV)
            ModHandler.addShapedRecipe("solar_panel_mv_gt5u",
                    MetaItems.COVER_SOLAR_PANEL_MV.getStackForm(), " S ", "SCS", " S ",
                    'S', MetaItems.COVER_SOLAR_PANEL_LV,
                    'C', MetaTileEntities.TRANSFORMER[1].getStackForm());

            // Solar Panel (HV)
            ModHandler.addShapedRecipe("solar_panel_hv_gt5u",
                    MetaItems.COVER_SOLAR_PANEL_HV.getStackForm(), " S ", "SCS", " S ",
                    'S', MetaItems.COVER_SOLAR_PANEL_MV,
                    'C', MetaTileEntities.TRANSFORMER[2].getStackForm());

            // Solar Panel (EV)
            ModHandler.addShapedRecipe("solar_panel_ev_gt5u",
                    MetaItems.COVER_SOLAR_PANEL_EV.getStackForm(), " S ", "SCS", " S ",
                    'S', MetaItems.COVER_SOLAR_PANEL_HV,
                    'C', MetaTileEntities.TRANSFORMER[3].getStackForm());

            // Solar Panel (IV)
            ModHandler.addShapedRecipe("solar_panel_iv_gt5u",
                    MetaItems.COVER_SOLAR_PANEL_IV.getStackForm(), " S ", "SCS", " S ",
                    'S', MetaItems.COVER_SOLAR_PANEL_EV,
                    'C', MetaTileEntities.TRANSFORMER[4].getStackForm());

            // Solar Panel (LuV)
            ModHandler.addShapedRecipe("solar_panel_luv_gt5u",
                    MetaItems.COVER_SOLAR_PANEL_LUV.getStackForm(), " S ", "SCS", " S ",
                    'S', MetaItems.COVER_SOLAR_PANEL_IV,
                    'C', MetaTileEntities.TRANSFORMER[5].getStackForm());

            // Solar Panel (ZPM)
            ModHandler.addShapedRecipe("solar_panel_zpm_gt5u",
                    MetaItems.COVER_SOLAR_PANEL_ZPM.getStackForm(), " S ", "SCS", " S ",
                    'S', MetaItems.COVER_SOLAR_PANEL_LUV,
                    'C', MetaTileEntities.TRANSFORMER[6].getStackForm());

            // Solar Panel (UV)
            ModHandler.addShapedRecipe("solar_panel_uv_gt5u",
                    MetaItems.COVER_SOLAR_PANEL_UV.getStackForm(), " S ", "SCS", " S ",
                    'S', MetaItems.COVER_SOLAR_PANEL_ZPM,
                    'C', MetaTileEntities.TRANSFORMER[7].getStackForm());
        }
    }

    private static void blocks() {
        // Sawmill
        ModHandler.addShapedRecipe(true, "gtexpert.machine.sawmill",
                GTEMultiMetaTileEntities.SAWMILL.getStackForm(), "SBs", "MHM", "COC",
                'S', new UnificationEntry(screw, Materials.Steel),
                'B', new UnificationEntry(toolHeadBuzzSaw, Materials.Steel),
                'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm(),
                'H', MetaTileEntities.HULL[MV].getStackForm(),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.MV),
                'O', MetaItems.CONVEYOR_MODULE_MV.getStackForm());

        // Large Oil Cracking Unit
        ModHandler.addShapedRecipe(true, "gtexpert.machine.large_oil_cracking_unit",
                GTEMultiMetaTileEntities.LARGE_CRACKER.getStackForm(), "PCP", "FSF", "PCP",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.ZPM),
                'S', MetaTileEntities.CRACKER.getStackForm(),
                'P', MetaItems.ELECTRIC_PUMP_IV.getStackForm(),
                'F', MetaItems.FIELD_GENERATOR_IV.getStackForm());

        // Advanced Chemical Plant
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.LARGE_CHEMICAL_REACTOR, 1)
                .input(foil, Materials.Polybenzimidazole, 32)
                .input(plate, Materials.IndiumTinBariumTitaniumCuprate, 32)
                .input(stickLong, Materials.Cupronickel, 32)
                .input(pipeLargeFluid, Materials.Polytetrafluoroethylene, 8)
                .input(circuit, MarkerMaterials.Tier.LuV, 4)
                .input(MetaItems.ELECTRIC_MOTOR_LuV, 4)
                .fluidInputs(Materials.SolderingAlloy.getFluid(2304))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(2304))
                .output(GTEMultiMetaTileEntities.ADVANCED_CHEMICAL_PLANT)
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Advanced Gas Collector
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.GAS_COLLECTOR[ZPM], 1)
                .input(plate, GTEMaterials.NM_HEA_NPs, 8)
                .input(circuit, MarkerMaterials.Tier.ZPM, 16)
                .input(MetaItems.SENSOR_ZPM, 4)
                .input(MetaItems.FLUID_REGULATOR_ZPM, 4)
                .input(MetaItems.FIELD_GENERATOR_ZPM, 4)
                .fluidInputs(Materials.SolderingAlloy.getFluid(2304))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(2304))
                .output(GTEMultiMetaTileEntities.ADVANCED_GAS_COLLECTOR)
                .duration(200).EUt(VA[ZPM])
                .buildAndRegister();

        // Void Ore Miner
        AssemblyLineRecipeBuilder builderVOM = RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.ADVANCED_LARGE_MINER)
                .fluidInputs(Materials.SolderingAlloy.getFluid(18432))
                .output(GTEMultiMetaTileEntities.VOIDOREMINER);
        if (GTEValues.isModLoadedDEDA()) {
            builderVOM.inputs(getModItem(GTEValues.MODID_DE, "awakened_core", 4, 0));
            builderVOM.input(MetaItems.ELECTRIC_MOTOR_UV, 4);
            builderVOM.input(MetaItems.ELECTRIC_PUMP_UV, 4);
            builderVOM.input(MetaItems.CONVEYOR_MODULE_UV, 4);
            builderVOM.input(MetaItems.ELECTRIC_PISTON_UV, 4);
            builderVOM.input(MetaItems.ROBOT_ARM_UV, 4);
            builderVOM.input(MetaItems.EMITTER_UV, 4);
            builderVOM.input(MetaItems.SENSOR_UV, 4);
            builderVOM.duration(600).EUt(VA[UV]);
            builderVOM.stationResearch(
                    b -> b.researchStack(MetaTileEntities.ADVANCED_LARGE_MINER.getStackForm()).CWUt(96).EUt(VA[UV]));
        } else {
            builderVOM.input(circuit, MarkerMaterials.Tier.ZPM, 4);
            builderVOM.input(MetaItems.ELECTRIC_MOTOR_ZPM, 4);
            builderVOM.input(MetaItems.ELECTRIC_PUMP_ZPM, 4);
            builderVOM.input(MetaItems.CONVEYOR_MODULE_ZPM, 4);
            builderVOM.input(MetaItems.ELECTRIC_PISTON_ZPM, 4);
            builderVOM.input(MetaItems.ROBOT_ARM_ZPM, 4);
            builderVOM.input(MetaItems.EMITTER_ZPM, 4);
            builderVOM.input(MetaItems.SENSOR_ZPM, 4);
            builderVOM.duration(600).EUt(VA[ZPM]);
            builderVOM.stationResearch(
                    b -> b.researchStack(MetaTileEntities.ADVANCED_LARGE_MINER.getStackForm()).CWUt(64).EUt(VA[ZPM]));
        }
        builderVOM.input(MetaItems.ORE_DICTIONARY_FILTER);
        builderVOM.input(gear, Materials.NaquadahAlloy, 4);
        builderVOM.buildAndRegister();

        // Void Ore Miner Recipes
        List<Material> materials = new LinkedList<>(GregTechAPI.materialManager.getRegisteredMaterials());
        materials.forEach(GTERecipeLoader::voidOreMiner);

        // Treated Wood Machine Casing
        ModHandler.addShapedRecipe(true, "casing_treated_wood",
                GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill,
                        ConfigHolder.recipes.casingsPerCraft),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, Materials.TreatedWood),
                'F', new UnificationEntry(frameGt, Materials.TreatedWood));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, Materials.TreatedWood, 6)
                .input(frameGt, Materials.TreatedWood, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill, 2))
                .duration(50).EUt(VH[LV])
                .buildAndRegister();

        // Void Ore Miner Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM))
                .input(MetaItems.COVER_FLUID_VOIDING_ADVANCED)
                .input(MetaItems.VOLTAGE_COIL_ZPM, 2)
                .input(MetaItems.FIELD_GENERATOR_ZPM)
                .input(plate, GTEMaterials.NM_HEA_NPs, 6)
                .fluidInputs(Materials.EnderPearl.getFluid(GTValues.L << 2))
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER,
                                ConfigHolder.recipes.casingsPerCraft))
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();

        // Sawmill Conveyor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(MetaItems.CONVEYOR_MODULE_MV, 1)
                .input(frameGt, Materials.TreatedWood, 1)
                .input(Items.LEATHER, 3)
                .fluidInputs(Materials.Glue.getFluid(100))
                .output(GTEMetaBlocks.BLOCK_SAWMILL_CONVEYOR, 1)
                .duration(100).EUt(VA[MV])
                .buildAndRegister();
    }

    private static void tools() {
        // Piston Boots
        ModHandler.addShapedRecipe(true, "piston_boots",
                GTEMetaItems.PISTON_BOOTS.getStackForm(), "EhE", "RLR", "PBP",
                'E', Items.LEATHER,
                'R', new UnificationEntry(plate, Materials.Rubber),
                'L', Items.LEATHER_BOOTS,
                'P', MetaItems.ELECTRIC_PISTON_LV,
                'B', MetaItems.BATTERY_LV_SODIUM);
    }

    private static void end_contents() {
        // Infinite GT Energy Unit Emitter
        AssemblyLineRecipeBuilder builderIGTEUE = RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.HULL[UHV])
                .fluidInputs(Materials.SolderingAlloy.getFluid(18432))
                .fluidInputs(Materials.UraniumRhodiumDinaquadide.getFluid(9216))
                .output(MetaTileEntities.CREATIVE_ENERGY)
                .duration(2000).EUt(VA[UHV]);
        if (!(GTEValues.isModLoadedDEDA() && Loader.isModLoaded(GTEValues.MODID_AE) &&
                Loader.isModLoaded(GTEValues.MODID_EIO))) {
            builderIGTEUE.input(MetaItems.ENERGY_CLUSTER, 4);
            builderIGTEUE.input(MetaItems.FIELD_GENERATOR_UV, 4);
            builderIGTEUE.input(circuit, MarkerMaterials.Tier.UV, 16);
        } else {
            if (GTEValues.isModLoadedDEDA()) {
                builderIGTEUE.inputs(getModItem(GTEValues.MODID_DA, "chaotic_energy_core", 8, 0));
                builderIGTEUE.inputs(getModItem(GTEValues.MODID_DA, "chaos_stabilizer_core", 8, 0));
            }
            if (Loader.isModLoaded(GTEValues.MODID_AE)) {
                builderIGTEUE.inputs(getModItem(GTEValues.MODID_AE, "creative_energy_cell", 4, 0));
                builderIGTEUE.stationResearch(
                        b -> b.researchStack(GTEMetaItems.GTE_ME_FAKE_COMPONENT.getStackForm()).CWUt(128).EUt(VA[UHV]));
            }
            if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
                builderIGTEUE.inputNBT(getModItem(GTEValues.MODID_EIO, "block_cap_bank", 4, 3).getItem(),
                        NBTMatcher.ANY, NBTCondition.ANY);
            }
        }
        if (GTEValues.isModLoadedDEDA() && Loader.isModLoaded(GTEValues.MODID_AE) &&
                Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderIGTEUE.input(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 4);
        } else if (Loader.isModLoaded(GTEValues.MODID_AE) &&
                !(GTEValues.isModLoadedDEDA() && Loader.isModLoaded(GTEValues.MODID_EIO))) {
                    builderIGTEUE.input(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 4);
                }
        builderIGTEUE.buildAndRegister();

        // Creative Quantum Tank
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.CREATIVE_ENERGY, 4)
                .input(MetaItems.QUANTUM_STAR, 64)
                .input(MetaItems.ELECTRIC_PUMP_UV, 64)
                .input(MetaItems.FLUID_REGULATOR_UV, 64)
                .input(MetaItems.EMITTER_UV, 64)
                .input(MetaItems.WETWARE_MAINFRAME_UHV, 64)
                .fluidInputs(Materials.SolderingAlloy.getFluid(36864))
                .fluidInputs(Materials.RutheniumTriniumAmericiumNeutronate.getFluid(9216))
                .outputs(MetaTileEntities.CREATIVE_TANK.getStackForm())
                .duration(2000).EUt(VA[UHV])
                .stationResearch(b -> b.researchStack(MetaTileEntities.CREATIVE_ENERGY.getStackForm())
                        .CWUt(160).EUt(VA[UHV]))
                .buildAndRegister();

        // Creative Quantum Chest
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.CREATIVE_TANK, 8)
                .input(MetaItems.QUANTUM_STAR, 64)
                .input(MetaItems.CONVEYOR_MODULE_UV, 64)
                .input(MetaItems.ROBOT_ARM_UV, 64)
                .input(MetaItems.EMITTER_UV, 64)
                .input(MetaItems.WETWARE_MAINFRAME_UHV, 64)
                .input(MetaItems.NAN_CERTIFICATE)
                .fluidInputs(Materials.SolderingAlloy.getFluid(36864))
                .fluidInputs(Materials.RutheniumTriniumAmericiumNeutronate.getFluid(9216))
                .outputs(MetaTileEntities.CREATIVE_CHEST.getStackForm())
                .stationResearch(b -> b.researchStack(MetaTileEntities.CREATIVE_TANK.getStackForm())
                        .CWUt(160).EUt(VA[UHV]))
                .duration(2000).EUt(VA[UHV])
                .buildAndRegister();

        // Creative Data Access Hatch
        AssemblyLineRecipeBuilder builderCDAH = RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.ADVANCED_DATA_ACCESS_HATCH)
                .input(MetaItems.TOOL_DATA_MODULE, 4)
                .input(MetaItems.WETWARE_MAINFRAME_UHV, 4)
                .fluidInputs(Materials.SolderingAlloy.getFluid(18432))
                .fluidInputs(Materials.UraniumRhodiumDinaquadide.getFluid(9216))
                .outputs(MetaTileEntities.CREATIVE_DATA_HATCH.getStackForm())
                .duration(2000).EUt(VA[UHV])
                .stationResearch(b -> b.researchStack(MetaTileEntities.ADVANCED_DATA_ACCESS_HATCH.getStackForm())
                        .CWUt(160).EUt(VA[UHV]));
        if (Loader.isModLoaded(GTEValues.MODID_AE)) {
            builderCDAH.input(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 4);
        }
        builderCDAH.buildAndRegister();
    }

    /**
     * Add recipes for the Void Ore Miner
     *
     * @param material The material to add recipes for
     */
    private static void voidOreMiner(@NotNull Material material) {
        // Skip if the material doesn't have an ore
        if (!material.hasProperty(PropertyKey.ORE)) return;
        if (material.hasFlag(MaterialFlags.DISABLE_ORE_BLOCK)) return;

        // Get the ore
        List<ItemStack> ores = OreDictUnifier.getAll(new UnificationEntry(ore, material));
        ItemStack oreStack = ores.get(ores.size() - 1);
        oreStack.setCount(32);
        GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                .input(ore, material)
                .fluidInputs(Materials.EnderPearl.getFluid(576))
                .fluidInputs(Materials.DrillingFluid.getFluid(10000))
                .outputs(oreStack)
                .duration(20).EUt(VA[ZPM])
                .buildAndRegister();
        GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                .input(oreNetherrack, material)
                .fluidInputs(Materials.EnderPearl.getFluid(576))
                .fluidInputs(Materials.DrillingFluid.getFluid(10000))
                .output(oreNetherrack, material, 64)
                .duration(20).EUt(VA[ZPM])
                .buildAndRegister();
        GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                .input(oreEndstone, material)
                .fluidInputs(Materials.EnderPearl.getFluid(576))
                .fluidInputs(Materials.DrillingFluid.getFluid(10000))
                .output(oreEndstone, material, 64)
                .duration(20).EUt(VA[ZPM])
                .buildAndRegister();
    }
}
