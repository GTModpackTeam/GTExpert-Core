package gtexpert.loaders.recipe;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.items.GTEMetaItems.*;
import static gtexpert.common.metatileentities.GTEMultiMetaTileEntities.*;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.AssemblyLineRecipeBuilder;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEConfigHolder;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;

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
        Osmium.getProperty(PropertyKey.ORE).setOreByProducts(Iridium);

        // Iridium
        Iridium.getProperty(PropertyKey.ORE).setOreByProducts(Platinum, Osmium);

        // Nether Star Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Diamond, 1)
                .input(dust, Iridium, 1)
                .fluidInputs(NetherAir.getFluid(8000))
                .fluidInputs(RocketFuel.getFluid(1000))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, NetherStar, 2)
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Ender Eye
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(EnderPearl.getFluid(144))
                .fluidInputs(Blaze.getFluid(144))
                .fluidOutputs(EnderEye.getFluid(144))
                .duration(50).EUt(VA[HV])
                .buildAndRegister();

        // NM_HEA_NPs Dust
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, NM_HEA_NPs, 8)
                .output(dust, Gold, 1)
                .output(dust, Silver, 1)
                .output(dust, Ruthenium, 1)
                .output(dust, Rhodium, 1)
                .output(dust, Palladium, 1)
                .output(dust, Osmium, 1)
                .fluidOutputs(Iridium.getFluid(144))
                .fluidOutputs(Platinum.getFluid(144))
                .duration(10).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, NM_HEA_NPs, 8)
                .output(dust, Gold, 1)
                .output(dust, Silver, 1)
                .output(dust, Ruthenium, 1)
                .output(dust, Rhodium, 1)
                .output(dust, Palladium, 1)
                .output(dust, Osmium, 1)
                .fluidOutputs(Iridium.getFluid(144))
                .fluidOutputs(Platinum.getFluid(144))
                .duration(10).EUt(VA[LV])
                .buildAndRegister();

        // Naquadah Rocket Fuel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(NaquadahEnriched.getFluid(1296))
                .fluidInputs(RocketFuel.getFluid(5000))
                .fluidOutputs(NaquadahRocketFuel.getFluid(6000))
                .duration(20).EUt(VA[IV])
                .buildAndRegister();
        RecipeMaps.COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(NaquadahRocketFuel.getFluid(1))
                .duration(750).EUt(32)
                .buildAndRegister();
    }

    private static void items() {
        if (GTEConfigHolder.ceuOverride.hardPrimitiveCovers) {
            ModHandler.addShapedRecipe(true, "primitive_motor", PRIMITIVE_MOTOR.getStackForm(1),
                    "CWR", "WMW", "RWC",
                    'R', new UnificationEntry(stick, Bronze),
                    'M', new UnificationEntry(stick, IronMagnetic),
                    'W', new UnificationEntry(wireGtSingle, Tin),
                    'C', new UnificationEntry(cableGtSingle, Lead));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, Lead, 2)
                    .input(stick, Bronze, 2)
                    .input(stick, IronMagnetic)
                    .input(wireGtSingle, Tin, 4)
                    .outputs(PRIMITIVE_MOTOR.getStackForm())
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_piston", PRIMITIVE_PISTON.getStackForm(1),
                    "PPP", "CRR", "CMG",
                    'R', new UnificationEntry(stick, Bronze),
                    'G', new UnificationEntry(gearSmall, Bronze),
                    'P', new UnificationEntry(plate, Bronze),
                    'C', new UnificationEntry(cableGtSingle, Lead),
                    'M', PRIMITIVE_MOTOR.getStackForm(1));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(stick, Bronze, 2)
                    .input(cableGtSingle, Lead, 2)
                    .input(plate, Bronze, 3)
                    .input(gearSmall, Bronze)
                    .inputs(PRIMITIVE_MOTOR.getStackForm())
                    .outputs(PRIMITIVE_PISTON.getStackForm())
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_pump", PRIMITIVE_PUMP.getStackForm(1),
                    "SRO", "dPw", "OMC",
                    'R', new UnificationEntry(rotor, Bronze),
                    'S', new UnificationEntry(screw, Bronze),
                    'O', new UnificationEntry(ring, Rubber),
                    'P', new UnificationEntry(pipeNormalFluid, Copper),
                    'C', new UnificationEntry(cableGtSingle, Lead),
                    'M', PRIMITIVE_MOTOR.getStackForm(1));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, Lead)
                    .input(pipeNormalFluid, Copper)
                    .input(screw, Bronze)
                    .input(rotor, Bronze)
                    .input(ring, Rubber, 2)
                    .inputs(PRIMITIVE_MOTOR.getStackForm())
                    .outputs(PRIMITIVE_PUMP.getStackForm())
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_conveyor", PRIMITIVE_CONVEYOR.getStackForm(1),
                    "PPP", "MCM", "PPP",
                    'P', new UnificationEntry(plate, Rubber),
                    'C', new UnificationEntry(cableGtSingle, Lead),
                    'M', PRIMITIVE_MOTOR.getStackForm(1));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, Lead)
                    .inputs(PRIMITIVE_MOTOR.getStackForm(2))
                    .fluidInputs(Rubber.getFluid(L * 6))
                    .circuitMeta(1)
                    .outputs(PRIMITIVE_CONVEYOR.getStackForm())
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_robot_arm", PRIMITIVE_ROBOT_ARM.getStackForm(1),
                    "CCC", "MRM", "PUR",
                    'R', new UnificationEntry(stick, Bronze),
                    'C', new UnificationEntry(cableGtSingle, Lead),
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'P', PRIMITIVE_PISTON.getStackForm(1),
                    'M', PRIMITIVE_MOTOR.getStackForm(1));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(cableGtSingle, Lead, 3)
                    .input(stick, Bronze, 2)
                    .inputs(PRIMITIVE_MOTOR.getStackForm(2))
                    .inputs(PRIMITIVE_PISTON.getStackForm())
                    .input(circuit, MarkerMaterials.Tier.ULV)
                    .outputs(PRIMITIVE_ROBOT_ARM.getStackForm())
                    .duration(100).EUt(VA[ULV]).buildAndRegister();

            ModHandler.addShapedRecipe(true, "primitive_fluid_regulator", PRIMITIVE_FLUID_REGULATOR.getStackForm(1),
                    " U ", "dPw", " U ",
                    'U', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'P', PRIMITIVE_PUMP.getStackForm(1));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(PRIMITIVE_PUMP.getStackForm())
                    .input(circuit, MarkerMaterials.Tier.ULV, 2)
                    .circuitMeta(1)
                    .outputs(PRIMITIVE_FLUID_REGULATOR.getStackForm())
                    .EUt(VA[ULV])
                    .duration(400)
                    .withRecycling()
                    .buildAndRegister();
        } else {
            ModHandler.addShapedRecipe(true, "primitive_motor", PRIMITIVE_MOTOR.getStackForm(1),
                    "WR", "MW",
                    'R', new UnificationEntry(stick, Bronze),
                    'M', new UnificationEntry(stick, IronMagnetic),
                    'W', new UnificationEntry(wireGtSingle, Tin));

            ModHandler.addShapedRecipe(true, "primitive_piston", PRIMITIVE_PISTON.getStackForm(1),
                    "PR", "MG",
                    'R', new UnificationEntry(stick, Bronze),
                    'G', new UnificationEntry(gearSmall, Bronze),
                    'P', new UnificationEntry(plate, Bronze),
                    'M', PRIMITIVE_MOTOR.getStackForm(1));

            ModHandler.addShapedRecipe(true, "primitive_pump", PRIMITIVE_PUMP.getStackForm(1),
                    "PR", "MO",
                    'R', new UnificationEntry(rotor, Bronze),
                    'O', new UnificationEntry(ring, Rubber),
                    'P', new UnificationEntry(pipeNormalFluid, Copper),
                    'M', PRIMITIVE_MOTOR.getStackForm(1));

            ModHandler.addShapedRecipe(true, "primitive_conveyor", PRIMITIVE_CONVEYOR.getStackForm(1),
                    "PC", "MP",
                    'P', new UnificationEntry(plate, Rubber),
                    'C', new UnificationEntry(cableGtSingle, Lead),
                    'M', PRIMITIVE_MOTOR.getStackForm(1));

            ModHandler.addShapedRecipe(true, "primitive_robot_arm", PRIMITIVE_ROBOT_ARM.getStackForm(1),
                    "PR", "MC",
                    'R', new UnificationEntry(stick, Bronze),
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'P', PRIMITIVE_PISTON.getStackForm(1),
                    'M', PRIMITIVE_MOTOR.getStackForm(1));

            ModHandler.addShapedRecipe(true, "primitive_fluid_regulator", PRIMITIVE_FLUID_REGULATOR.getStackForm(1),
                    "PC", "Cd",
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'P', PRIMITIVE_PUMP.getStackForm(1));
        }

        if (!ConfigHolder.machines.enableHighTierSolars) return;
        if (GTEConfigHolder.ceuOverride.hardSolarPanel) {
            // Remove solar panels
            ModHandler.removeRecipeByOutput(COVER_SOLAR_PANEL.getStackForm());
            ModHandler.removeRecipeByOutput(COVER_SOLAR_PANEL_ULV.getStackForm());
            ModHandler.removeRecipeByOutput(COVER_SOLAR_PANEL_LV.getStackForm());

            // Solar Panel
            ModHandler.addShapedRecipe("solar_panel_basic", COVER_SOLAR_PANEL.getStackForm(1),
                    "SGS", "CFC",
                    'S', SILICON_WAFER,
                    'G', paneGlass,
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.ULV),
                    'F', CARBON_FIBER_PLATE);
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(SILICON_WAFER, 2)
                    .input("paneGlass", 1)
                    .input(circuit, MarkerMaterials.Tier.ULV, 2)
                    .input(CARBON_FIBER_PLATE, 1)
                    .output(COVER_SOLAR_PANEL, 1)
                    .duration(20).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(PHOSPHORUS_WAFER, 2)
                    .input("paneGlass", 4)
                    .input(circuit, MarkerMaterials.Tier.ULV, 8)
                    .input(CARBON_FIBER_PLATE, 4)
                    .output(COVER_SOLAR_PANEL, 4)
                    .duration(20).EUt(VA[MV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(NAQUADAH_WAFER, 2)
                    .input("paneGlass", 8)
                    .input(circuit, MarkerMaterials.Tier.ULV, 16)
                    .input(CARBON_FIBER_PLATE, 8)
                    .output(COVER_SOLAR_PANEL, 8)
                    .duration(20).EUt(VA[EV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(NEUTRONIUM_WAFER, 2)
                    .input("paneGlass", 16)
                    .input(circuit, MarkerMaterials.Tier.ULV, 32)
                    .input(CARBON_FIBER_PLATE, 16)
                    .output(COVER_SOLAR_PANEL, 16)
                    .duration(20).EUt(VA[LuV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .input(SILICON_WAFER, 16)
                    .input("paneGlass", 8)
                    .input(circuit, MarkerMaterials.Tier.ULV, 16)
                    .input(CARBON_FIBER_PLATE, 8)
                    .output(COVER_SOLAR_PANEL, 8)
                    .duration(20).EUt(VA[LV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(SILICON_WAFER, 32)
                    .input("paneGlass", 16)
                    .input(circuit, MarkerMaterials.Tier.ULV, 32)
                    .input(CARBON_FIBER_PLATE, 16)
                    .output(COVER_SOLAR_PANEL, 16)
                    .duration(20).EUt(VA[MV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(4)
                    .input(SILICON_WAFER, 64)
                    .input("paneGlass", 32)
                    .input(circuit, MarkerMaterials.Tier.ULV, 64)
                    .input(CARBON_FIBER_PLATE, 32)
                    .output(COVER_SOLAR_PANEL, 32)
                    .duration(20).EUt(VA[HV])
                    .buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(5)
                    .input(SILICON_WAFER, 64)
                    .input(SILICON_WAFER, 64)
                    .input("paneGlass", 64)
                    .input(circuit, MarkerMaterials.Tier.ULV, 64)
                    .input(circuit, MarkerMaterials.Tier.ULV, 64)
                    .input(CARBON_FIBER_PLATE, 64)
                    .output(COVER_SOLAR_PANEL, 64)
                    .duration(20).EUt(VA[EV])
                    .buildAndRegister();

            // Solar Panel (8V)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL, 8)
                    .input(Blocks.DAYLIGHT_DETECTOR, 8)
                    .input(NAND_CHIP_ULV, 4)
                    .input(ULTRA_LOW_POWER_INTEGRATED_CIRCUIT, 4)
                    .input(Blocks.GLASS)
                    .input(MetaTileEntities.TRANSFORMER[0])
                    .fluidInputs(Silicon.getFluid(L))
                    .fluidInputs(SolderingAlloy.getFluid(L))
                    .output(COVER_SOLAR_PANEL_ULV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(COVER_SOLAR_PANEL.getStackForm())
                            .CWUt(32).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (LV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_ULV, 4)
                    .input(SENSOR_LV, 8)
                    .input(INTEGRATED_CIRCUIT_LV, 4)
                    .input(ULTRA_LOW_POWER_INTEGRATED_CIRCUIT, 8)
                    .inputs(Loader.isModLoaded(GTEValues.MODID_AE) ?
                            getModItem(GTEValues.MODID_AE, "quartz_glass", 1, 0) :
                            new ItemStack(Blocks.GLASS))
                    .input(MetaTileEntities.TRANSFORMER[1])
                    .fluidInputs(Silicon.getFluid(L << 2))
                    .fluidInputs(SolderingAlloy.getFluid(L << 2))
                    .output(COVER_SOLAR_PANEL_LV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(COVER_SOLAR_PANEL_ULV.getStackForm())
                            .CWUt(32).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (MV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_LV, 4)
                    .input(SENSOR_MV, 8)
                    .input(INTEGRATED_CIRCUIT_MV, 4)
                    .input(LOW_POWER_INTEGRATED_CIRCUIT, 4)
                    .inputs(Loader.isModLoaded(GTEValues.MODID_EIO) ?
                            getModItem(GTEValues.MODID_EIO, "block_fused_quartz", 1, 0) :
                            new ItemStack(Blocks.GLASS))
                    .input(MetaTileEntities.TRANSFORMER[2])
                    .fluidInputs(Silicon.getFluid(L << 3))
                    .fluidInputs(SolderingAlloy.getFluid(L << 3))
                    .output(COVER_SOLAR_PANEL_MV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(COVER_SOLAR_PANEL_LV.getStackForm())
                            .CWUt(32).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (HV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_MV, 4)
                    .input(SENSOR_HV, 8)
                    .input(INTEGRATED_CIRCUIT_HV, 4)
                    .input(LOW_POWER_INTEGRATED_CIRCUIT, 8)
                    .inputs(Loader.isModLoaded(GTEValues.MODID_EIO) ?
                            getModItem(GTEValues.MODID_EIO, "block_fused_quartz", 1, 0) :
                            new ItemStack(Blocks.GLASS))
                    .input(MetaTileEntities.TRANSFORMER[3])
                    .fluidInputs(Silicon.getFluid(L << 4))
                    .fluidInputs(SolderingAlloy.getFluid(L << 4))
                    .output(COVER_SOLAR_PANEL_HV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(COVER_SOLAR_PANEL_MV.getStackForm())
                            .CWUt(48).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (EV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_HV, 4)
                    .input(SENSOR_EV, 8)
                    .input(WORKSTATION_EV, 4)
                    .input(POWER_INTEGRATED_CIRCUIT, 4)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 0)
                    .input(MetaTileEntities.TRANSFORMER[4])
                    .fluidInputs(Silicon.getFluid(L << 5))
                    .fluidInputs(SolderingAlloy.getFluid(L << 5))
                    .output(COVER_SOLAR_PANEL_EV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(COVER_SOLAR_PANEL_HV.getStackForm())
                            .CWUt(48).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (IV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_EV, 4)
                    .input(SENSOR_IV, 8)
                    .input(MAINFRAME_IV, 4)
                    .input(POWER_INTEGRATED_CIRCUIT, 8)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 0)
                    .input(MetaTileEntities.TRANSFORMER[5])
                    .fluidInputs(Silicon.getFluid(L << 6))
                    .fluidInputs(SolderingAlloy.getFluid(L << 6))
                    .output(COVER_SOLAR_PANEL_IV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(COVER_SOLAR_PANEL_EV.getStackForm())
                            .CWUt(48).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (LuV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_IV, 4)
                    .input(SENSOR_LuV, 8)
                    .input(NANO_MAINFRAME_LUV, 4)
                    .input(HIGH_POWER_INTEGRATED_CIRCUIT, 8)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 1)
                    .input(MetaTileEntities.TRANSFORMER[6])
                    .fluidInputs(Silicon.getFluid(L << 7))
                    .fluidInputs(SolderingAlloy.getFluid(L << 7))
                    .output(COVER_SOLAR_PANEL_LUV)
                    .duration(100).EUt(VA[LuV])
                    .stationResearch(b -> b.researchStack(COVER_SOLAR_PANEL_IV.getStackForm())
                            .CWUt(64).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (ZPM)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_LUV, 4)
                    .input(SENSOR_ZPM, 8)
                    .input(QUANTUM_MAINFRAME_ZPM, 4)
                    .input(HIGH_POWER_INTEGRATED_CIRCUIT, 16)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 1)
                    .input(MetaTileEntities.TRANSFORMER[7])
                    .fluidInputs(Silicon.getFluid(L << 8))
                    .fluidInputs(SolderingAlloy.getFluid(L << 8))
                    .output(COVER_SOLAR_PANEL_ZPM)
                    .duration(100).EUt(VA[ZPM])
                    .stationResearch(b -> b.researchStack(COVER_SOLAR_PANEL_LUV.getStackForm())
                            .CWUt(64).EUt(VA[LuV]))
                    .buildAndRegister();

            // Solar Panel (UV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_ZPM, 4)
                    .input(SENSOR_UV, 8)
                    .input(CRYSTAL_MAINFRAME_UV, 4)
                    .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 1)
                    .input(MetaTileEntities.TRANSFORMER[8])
                    .fluidInputs(Silicon.getFluid(L << 9))
                    .fluidInputs(SolderingAlloy.getFluid(L << 9))
                    .output(COVER_SOLAR_PANEL_UV)
                    .duration(100).EUt(VA[UV])
                    .stationResearch(b -> b.researchStack(COVER_SOLAR_PANEL_ZPM.getStackForm())
                            .CWUt(64).EUt(VA[LuV]))
                    .buildAndRegister();
        } else {
            // Solar Panel (8V)
            ModHandler.addShapedRecipe("solar_panel_basic_gt5u", COVER_SOLAR_PANEL_ULV.getStackForm(1),
                    "SSS", "SCS", "SSS",
                    'S', COVER_SOLAR_PANEL,
                    'C', new UnificationEntry(circuit, MarkerMaterials.Tier.HV));

            // Solar Panel (LV)
            ModHandler.addShapedRecipe("solar_panel_lv_gt5u", COVER_SOLAR_PANEL_LV.getStackForm(1),
                    " S ", "SCS", " S ",
                    'S', COVER_SOLAR_PANEL_ULV,
                    'C', MetaTileEntities.TRANSFORMER[0].getStackForm());

            // Solar Panel (MV)
            ModHandler.addShapedRecipe("solar_panel_mv_gt5u", COVER_SOLAR_PANEL_MV.getStackForm(1),
                    " S ", "SCS", " S ",
                    'S', COVER_SOLAR_PANEL_LV,
                    'C', MetaTileEntities.TRANSFORMER[1].getStackForm());

            // Solar Panel (HV)
            ModHandler.addShapedRecipe("solar_panel_hv_gt5u", COVER_SOLAR_PANEL_HV.getStackForm(1),
                    " S ", "SCS", " S ",
                    'S', COVER_SOLAR_PANEL_MV,
                    'C', MetaTileEntities.TRANSFORMER[2].getStackForm());

            // Solar Panel (EV)
            ModHandler.addShapedRecipe("solar_panel_ev_gt5u", COVER_SOLAR_PANEL_EV.getStackForm(1),
                    " S ", "SCS", " S ",
                    'S', COVER_SOLAR_PANEL_HV,
                    'C', MetaTileEntities.TRANSFORMER[3].getStackForm());

            // Solar Panel (IV)
            ModHandler.addShapedRecipe("solar_panel_iv_gt5u", COVER_SOLAR_PANEL_IV.getStackForm(1),
                    " S ", "SCS", " S ",
                    'S', COVER_SOLAR_PANEL_EV,
                    'C', MetaTileEntities.TRANSFORMER[4].getStackForm());

            // Solar Panel (LuV)
            ModHandler.addShapedRecipe("solar_panel_luv_gt5u", COVER_SOLAR_PANEL_LUV.getStackForm(1),
                    " S ", "SCS", " S ",
                    'S', COVER_SOLAR_PANEL_IV,
                    'C', MetaTileEntities.TRANSFORMER[5].getStackForm());

            // Solar Panel (ZPM)
            ModHandler.addShapedRecipe("solar_panel_zpm_gt5u", COVER_SOLAR_PANEL_ZPM.getStackForm(1),
                    " S ", "SCS", " S ",
                    'S', COVER_SOLAR_PANEL_LUV,
                    'C', MetaTileEntities.TRANSFORMER[6].getStackForm());

            // Solar Panel (UV)
            ModHandler.addShapedRecipe("solar_panel_uv_gt5u", COVER_SOLAR_PANEL_UV.getStackForm(1),
                    " S ", "SCS", " S ",
                    'S', COVER_SOLAR_PANEL_ZPM,
                    'C', MetaTileEntities.TRANSFORMER[7].getStackForm());
        }
    }

    private static void blocks() {
        // Sawmill
        ModHandler.addShapedRecipe(true, "gtexpert.machine.sawmill", SAWMILL.getStackForm(),
                "SBs", "MHM", "COC",
                'S', new UnificationEntry(screw, Steel),
                'B', new UnificationEntry(toolHeadBuzzSaw, Steel),
                'M', ELECTRIC_MOTOR_MV.getStackForm(),
                'H', MetaTileEntities.HULL[MV].getStackForm(),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.MV),
                'O', CONVEYOR_MODULE_MV.getStackForm());

        // Large Oil Cracking Unit
        ModHandler.addShapedRecipe(true, "gtexpert.machine.large_oil_cracking_unit", LARGE_CRACKER.getStackForm(),
                "PCP", "FSF", "PCP",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.ZPM),
                'S', MetaTileEntities.CRACKER.getStackForm(),
                'P', ELECTRIC_PUMP_IV.getStackForm(),
                'F', FIELD_GENERATOR_IV.getStackForm());

        // Advanced Chemical Plant
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(LARGE_CHEMICAL_REACTOR, 1)
                .input(foil, Polybenzimidazole, 32)
                .input(plate, IndiumTinBariumTitaniumCuprate, 32)
                .input(stickLong, Cupronickel, 32)
                .input(pipeLargeFluid, Polytetrafluoroethylene, 8)
                .input(circuit, MarkerMaterials.Tier.LuV, 4)
                .input(ELECTRIC_MOTOR_LuV, 4)
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .fluidInputs(Polytetrafluoroethylene.getFluid(2304))
                .output(ADVANCED_CHEMICAL_PLANT)
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Void Ore Miner
        AssemblyLineRecipeBuilder builderVOM = RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.ADVANCED_LARGE_MINER)
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .output(VOIDOREMINER);
        if (GTEValues.isModLoadedDEDA()) {
            builderVOM.inputs(getModItem(GTEValues.MODID_DE, "awakened_core", 4, 0));
            builderVOM.input(ELECTRIC_MOTOR_UV, 4);
            builderVOM.input(ELECTRIC_PUMP_UV, 4);
            builderVOM.input(CONVEYOR_MODULE_UV, 4);
            builderVOM.input(ELECTRIC_PISTON_UV, 4);
            builderVOM.input(ROBOT_ARM_UV, 4);
            builderVOM.input(EMITTER_UV, 4);
            builderVOM.input(SENSOR_UV, 4);
            builderVOM.duration(600).EUt(VA[UV]);
            builderVOM.stationResearch(
                    b -> b.researchStack(MetaTileEntities.ADVANCED_LARGE_MINER.getStackForm()).CWUt(96).EUt(VA[UV]));
        } else {
            builderVOM.input(circuit, MarkerMaterials.Tier.ZPM, 4);
            builderVOM.input(ELECTRIC_MOTOR_ZPM, 4);
            builderVOM.input(ELECTRIC_PUMP_ZPM, 4);
            builderVOM.input(CONVEYOR_MODULE_ZPM, 4);
            builderVOM.input(ELECTRIC_PISTON_ZPM, 4);
            builderVOM.input(ROBOT_ARM_ZPM, 4);
            builderVOM.input(EMITTER_ZPM, 4);
            builderVOM.input(SENSOR_ZPM, 4);
            builderVOM.duration(600).EUt(VA[ZPM]);
            builderVOM.stationResearch(
                    b -> b.researchStack(MetaTileEntities.ADVANCED_LARGE_MINER.getStackForm()).CWUt(64).EUt(VA[ZPM]));
        }
        builderVOM.input(ORE_DICTIONARY_FILTER);
        builderVOM.input(gear, NaquadahAlloy, 4);
        builderVOM.buildAndRegister();

        // Void Ore Miner Recipes
        List<Material> materials = new LinkedList<>(GregTechAPI.materialManager.getRegisteredMaterials());
        materials.forEach(GTERecipeLoader::voidOreMiner);

        // Treated Wood Machine Casing
        ModHandler.addShapedRecipe(true, "casing_treated_wood",
                GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill,
                        ConfigHolder.recipes.casingsPerCraft),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, TreatedWood),
                'F', new UnificationEntry(frameGt, TreatedWood));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, TreatedWood, 6)
                .input(frameGt, TreatedWood, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill, 2))
                .duration(50).EUt(VH[LV])
                .buildAndRegister();

        // Void Ore Miner Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM))
                .inputs(COVER_FLUID_VOIDING_ADVANCED.getStackForm())
                .inputs(VOLTAGE_COIL_ZPM.getStackForm(2))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm())
                .input(plate, NM_HEA_NPs, 6)
                .fluidInputs(EnderPearl.getFluid(GTValues.L << 2))
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER,
                                ConfigHolder.recipes.casingsPerCraft))
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();

        // Sawmill Conveyor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(CONVEYOR_MODULE_MV, 1)
                .input(frameGt, TreatedWood, 1)
                .input(Items.LEATHER, 3)
                .fluidInputs(Glue.getFluid(100))
                .output(GTEMetaBlocks.BLOCK_SAWMILL_CONVEYOR, 1)
                .duration(100).EUt(VA[MV])
                .buildAndRegister();
    }

    private static void tools() {
        // Piston Boots
        ModHandler.addShapedRecipe(true, "piston_boots", PISTON_BOOTS.getStackForm(),
                "EhE", "RLR", "PBP",
                'E', Items.LEATHER,
                'R', new UnificationEntry(plate, Rubber),
                'L', Items.LEATHER_BOOTS,
                'P', ELECTRIC_PISTON_LV,
                'B', BATTERY_LV_SODIUM);
    }

    private static void end_contents() {
        // Infinite GT Energy Unit Emitter
        AssemblyLineRecipeBuilder builderIGTEUE = RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.HULL[UHV])
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(UraniumRhodiumDinaquadide.getFluid(9216))
                .outputs(MetaTileEntities.CREATIVE_ENERGY.getStackForm())
                .duration(2000).EUt(VA[UHV]);
        if (!(GTEValues.isModLoadedDEDA() && Loader.isModLoaded(GTEValues.MODID_AE) &&
                Loader.isModLoaded(GTEValues.MODID_EIO))) {
            builderIGTEUE.input(ENERGY_CLUSTER, 4);
            builderIGTEUE.input(FIELD_GENERATOR_UV, 4);
            builderIGTEUE.input(circuit, MarkerMaterials.Tier.UV, 16);
        } else {
            if (GTEValues.isModLoadedDEDA()) {
                builderIGTEUE.inputs(getModItem(GTEValues.MODID_DE, "chaotic_energy_core", 8, 0));
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
                .input(QUANTUM_STAR, 64)
                .input(ELECTRIC_PUMP_UV, 64)
                .input(FLUID_REGULATOR_UV, 64)
                .input(EMITTER_UV, 64)
                .input(WETWARE_MAINFRAME_UHV, 64)
                .fluidInputs(SolderingAlloy.getFluid(36864))
                .fluidInputs(RutheniumTriniumAmericiumNeutronate.getFluid(9216))
                .outputs(MetaTileEntities.CREATIVE_TANK.getStackForm())
                .duration(2000).EUt(VA[UHV])
                .stationResearch(b -> b.researchStack(MetaTileEntities.CREATIVE_ENERGY.getStackForm())
                        .CWUt(160).EUt(VA[UHV]))
                .buildAndRegister();

        // Creative Quantum Chest
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.CREATIVE_TANK, 8)
                .input(QUANTUM_STAR, 64)
                .input(CONVEYOR_MODULE_UV, 64)
                .input(ROBOT_ARM_UV, 64)
                .input(EMITTER_UV, 64)
                .input(WETWARE_MAINFRAME_UHV, 64)
                .input(NAN_CERTIFICATE)
                .fluidInputs(SolderingAlloy.getFluid(36864))
                .fluidInputs(RutheniumTriniumAmericiumNeutronate.getFluid(9216))
                .outputs(MetaTileEntities.CREATIVE_CHEST.getStackForm())
                .stationResearch(b -> b.researchStack(MetaTileEntities.CREATIVE_TANK.getStackForm())
                        .CWUt(160).EUt(VA[UHV]))
                .duration(2000).EUt(VA[UHV])
                .buildAndRegister();

        // Creative Data Access Hatch
        AssemblyLineRecipeBuilder builderCDAH = RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.ADVANCED_DATA_ACCESS_HATCH)
                .input(TOOL_DATA_MODULE, 4)
                .input(WETWARE_MAINFRAME_UHV, 4)
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(UraniumRhodiumDinaquadide.getFluid(9216))
                .outputs(MetaTileEntities.CREATIVE_DATA_HATCH.getStackForm())
                .duration(2000).EUt(VA[UHV])
                .stationResearch(b -> b.researchStack(MetaTileEntities.ADVANCED_DATA_ACCESS_HATCH.getStackForm())
                        .CWUt(160).EUt(VA[UHV]));
        if (Loader.isModLoaded(GTEValues.MODID_AE)) {
            builderCDAH.input(GTE_ME_FAKE_COMPONENT, 4);
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
                .fluidInputs(EnderPearl.getFluid(576))
                .fluidInputs(DrillingFluid.getFluid(10000))
                .outputs(oreStack)
                .duration(20).EUt(VA[ZPM])
                .buildAndRegister();
        GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                .input(oreNetherrack, material)
                .fluidInputs(EnderPearl.getFluid(576))
                .fluidInputs(DrillingFluid.getFluid(10000))
                .output(oreNetherrack, material, 64)
                .duration(20).EUt(VA[ZPM])
                .buildAndRegister();
        GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                .input(oreEndstone, material)
                .fluidInputs(EnderPearl.getFluid(576))
                .fluidInputs(DrillingFluid.getFluid(10000))
                .output(oreEndstone, material, 64)
                .duration(20).EUt(VA[ZPM])
                .buildAndRegister();
    }
}
