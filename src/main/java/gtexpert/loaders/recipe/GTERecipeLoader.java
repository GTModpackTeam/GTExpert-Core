package gtexpert.loaders.recipe;

import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.machines.init.MachineObject;
import crazypants.enderio.powertools.init.PowerToolObject;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.recipe.MetaTileEntityLoader;
import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEConfigHolder;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.integration.ae.AEHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;

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
        GTERecipeMaps.EXTREME_MIXER_RECIPES.recipeBuilder()
                .input(dust, Gold, 1)
                .input(dust, Silver, 1)
                .input(dust, Ruthenium, 1)
                .input(dust, Rhodium, 1)
                .input(dust, Palladium, 1)
                .input(dust, Osmium, 1)
                .input(dust, Iridium, 1)
                .input(dust, Platinum, 1)
                .output(dust, NM_HEA_NPs, 8)
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, NM_HEA_NPs, 1)
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
                .input(dust, NM_HEA_NPs, 1)
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
                .fluidInputs(Naquadah.getFluid(1296))
                .fluidInputs(RocketFuel.getFluid(5000))
                .fluidOutputs(NAQUADAH_ROCKET_FUEL.getFluid(6000))
                .duration(20).EUt(VA[EV])
                .buildAndRegister();
        RecipeMaps.COMBUSTION_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(NAQUADAH_ROCKET_FUEL.getFluid(1))
                .duration(500).EUt(32)
                .buildAndRegister();
    }

    private static void items() {
        if (!ConfigHolder.machines.enableHighTierSolars) return;
        if (GTEConfigHolder.hardSolarPanel) {
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
                    .input(GLOWSTONE_WAFER, 2)
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
                    .buildAndRegister();

            // Solar Panel (LV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_ULV, 4)
                    .input(SENSOR_LV, 8)
                    .input(INTEGRATED_CIRCUIT_LV, 4)
                    .input(ULTRA_LOW_POWER_INTEGRATED_CIRCUIT, 8)
                    .inputs(AEHelper.aeBlocks.quartzGlass().maybeStack(1).get())
                    .input(MetaTileEntities.TRANSFORMER[1])
                    .fluidInputs(Silicon.getFluid(L * 2))
                    .fluidInputs(SolderingAlloy.getFluid(L * 2))
                    .output(COVER_SOLAR_PANEL_LV)
                    .duration(100).EUt(VA[LuV])
                    .buildAndRegister();

            // Solar Panel (MV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_LV, 4)
                    .input(SENSOR_MV, 8)
                    .input(INTEGRATED_CIRCUIT_MV, 4)
                    .input(LOW_POWER_INTEGRATED_CIRCUIT, 4)
                    .input(ModObject.blockFusedQuartz.getBlockNN())
                    .input(MetaTileEntities.TRANSFORMER[2])
                    .fluidInputs(Silicon.getFluid(L * 3))
                    .fluidInputs(SolderingAlloy.getFluid(L * 3))
                    .output(COVER_SOLAR_PANEL_MV)
                    .duration(100).EUt(VA[LuV])
                    .buildAndRegister();

            // Solar Panel (HV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_MV, 4)
                    .input(SENSOR_HV, 8)
                    .input(INTEGRATED_CIRCUIT_HV, 4)
                    .input(LOW_POWER_INTEGRATED_CIRCUIT, 8)
                    .input(ModObject.blockFusedGlass.getBlockNN())
                    .input(MetaTileEntities.TRANSFORMER[3])
                    .fluidInputs(Silicon.getFluid(L * 4))
                    .fluidInputs(SolderingAlloy.getFluid(L * 4))
                    .output(COVER_SOLAR_PANEL_HV)
                    .duration(100).EUt(VA[LuV])
                    .buildAndRegister();

            // Solar Panel (EV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_HV, 4)
                    .input(SENSOR_EV, 8)
                    .input(WORKSTATION_EV, 4)
                    .input(POWER_INTEGRATED_CIRCUIT, 4)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 0)
                    .input(MetaTileEntities.TRANSFORMER[4])
                    .fluidInputs(Silicon.getFluid(L * 5))
                    .fluidInputs(SolderingAlloy.getFluid(L * 5))
                    .output(COVER_SOLAR_PANEL_EV)
                    .duration(100).EUt(VA[LuV])
                    .buildAndRegister();

            // Solar Panel (IV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_EV, 4)
                    .input(SENSOR_IV, 8)
                    .input(MAINFRAME_IV, 4)
                    .input(POWER_INTEGRATED_CIRCUIT, 8)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 0)
                    .input(MetaTileEntities.TRANSFORMER[5])
                    .fluidInputs(Silicon.getFluid(L * 6))
                    .fluidInputs(SolderingAlloy.getFluid(L * 6))
                    .output(COVER_SOLAR_PANEL_IV)
                    .duration(100).EUt(VA[LuV])
                    .buildAndRegister();

            // Solar Panel (LuV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_IV, 4)
                    .input(SENSOR_LuV, 8)
                    .input(NANO_MAINFRAME_LUV, 4)
                    .input(HIGH_POWER_INTEGRATED_CIRCUIT, 8)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 1)
                    .input(MetaTileEntities.TRANSFORMER[6])
                    .fluidInputs(Silicon.getFluid(L * 7))
                    .fluidInputs(SolderingAlloy.getFluid(L * 7))
                    .output(COVER_SOLAR_PANEL_LUV)
                    .duration(100).EUt(VA[LuV])
                    .buildAndRegister();

            // Solar Panel (ZPM)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_LUV, 4)
                    .input(SENSOR_ZPM, 8)
                    .input(QUANTUM_MAINFRAME_ZPM, 4)
                    .input(HIGH_POWER_INTEGRATED_CIRCUIT, 16)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 1)
                    .input(MetaTileEntities.TRANSFORMER[7])
                    .fluidInputs(Silicon.getFluid(L * 8))
                    .fluidInputs(SolderingAlloy.getFluid(L * 8))
                    .output(COVER_SOLAR_PANEL_ZPM)
                    .duration(100).EUt(VA[ZPM])
                    .buildAndRegister();

            // Solar Panel (UV)
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(COVER_SOLAR_PANEL_ZPM, 4)
                    .input(SENSOR_UV, 8)
                    .input(CRYSTAL_MAINFRAME_UV, 4)
                    .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                    .input(new ItemStack(MetaBlocks.TRANSPARENT_CASING).getItem(), 1, 1)
                    .input(MetaTileEntities.TRANSFORMER[8])
                    .fluidInputs(Silicon.getFluid(L * 9))
                    .fluidInputs(SolderingAlloy.getFluid(L * 9))
                    .output(COVER_SOLAR_PANEL_UV)
                    .duration(100).EUt(VA[UV])
                    .buildAndRegister();
        }
        else {
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
        // Extreme Mixer
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .input(EXTREME_MIXER[0])
                .outputs(MetaTileEntities.MIXER[ZPM].getStackForm())
                .outputs(FIELD_GENERATOR_ZPM.getStackForm())
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Sawmill
        ModHandler.addShapedRecipe("gtexpert.machine.sawmill", SAWMILL.getStackForm(),
                "SBs", "MHM", "COC",
                'S', new UnificationEntry(screw, Steel),
                'B', new UnificationEntry(toolHeadBuzzSaw, Steel),
                'M', ELECTRIC_MOTOR_MV.getStackForm(),
                'H', MetaTileEntities.HULL[MV].getStackForm(),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.MV),
                'O', CONVEYOR_MODULE_MV.getStackForm());

        // Void Ore Miner
        if (!Loader.isModLoaded(GTEValues.MODID_DE) && !Loader.isModLoaded(GTEValues.MODID_DA)) {
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaTileEntities.HULL[ZPM])
                    .input(frameGt, NaquadahAlloy, 4)
                    .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                    .input(ELECTRIC_MOTOR_ZPM, 4)
                    .input(ELECTRIC_PUMP_ZPM, 4)
                    .input(CONVEYOR_MODULE_ZPM, 4)
                    .input(ELECTRIC_PISTON_ZPM, 4)
                    .input(ROBOT_ARM_ZPM, 4)
                    .input(EMITTER_ZPM, 4)
                    .input(SENSOR_ZPM, 4)
                    .input(ORE_DICTIONARY_FILTER)
                    .input(gear, NaquadahAlloy, 4)
                    .fluidInputs(SolderingAlloy.getFluid(18432))
                    .output(VOIDOREMINER)
                    .duration(600).EUt(VA[ZPM])
                    .buildAndRegister();
        }

        // Treated Wood Machine Casing
        ModHandler.addShapedRecipe("casing_treated_wood", GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill, 2),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, TreatedWood),
                'F', new UnificationEntry(frameGt, TreatedWood));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, TreatedWood, 6)
                .input(frameGt, TreatedWood, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill, 2))
                .duration(50).EUt(16)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill, 1))
                .output(dust, Wood, 4)
                .duration(225).EUt(8)
                .buildAndRegister();

        // Void Ore Miner Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM))
                .inputs(COVER_FLUID_VOIDING_ADVANCED.getStackForm())
                .inputs(VOLTAGE_COIL_ZPM.getStackForm(2))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm())
                .input(plate, NM_HEA_NPs, 6)
                .fluidInputs(EnderPearl.getFluid(GTValues.L * 2))
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER, 2))
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

        // Void Ore Miner Recipes
        List<Material> materialOres = new ArrayList<>();
        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            if (material.hasProperty(PropertyKey.ORE)) {
                materialOres.add(material);
            }
        }
        for (Material materialOre : materialOres) {
            List<ItemStack> ores = OreDictUnifier.getAll(new UnificationEntry(ore, materialOre));
            ItemStack oreStack = ores.get(ores.size() - 1);
            oreStack.setCount(32);
            GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                    .input(ore, materialOre)
                    .fluidInputs(EnderPearl.getFluid(576))
                    .fluidInputs(DrillingFluid.getFluid(10000))
                    .outputs(oreStack)
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
            GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                    .input(oreNetherrack, materialOre)
                    .fluidInputs(EnderPearl.getFluid(576))
                    .fluidInputs(DrillingFluid.getFluid(10000))
                    .output(oreNetherrack, materialOre, 64)
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
            GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                    .input(oreEndstone, materialOre)
                    .fluidInputs(EnderPearl.getFluid(576))
                    .fluidInputs(DrillingFluid.getFluid(10000))
                    .output(oreEndstone, materialOre, 64)
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
        }

        // Vial Extractor
        MetaTileEntityLoader.registerMachineRecipe(VIAL_EXTRACTOR, "VRV", "PHF", "WCW",
                'V', ModObject.itemSoulVial.getItemNN(),
                'R', SENSOR,
                'P', PISTON,
                'H', HULL,
                'F', PUMP,
                'W', CABLE,
                'C', CIRCUIT);

        // Slice'N'Splice
        ModHandler.addShapelessRecipe("slice_n_splice", SLICE_N_SPLICE[HV].getStackForm(), new ItemStack(MachineObject.block_slice_and_splice.getBlockNN()));
        MetaTileEntityLoader.registerMachineRecipe(SLICE_N_SPLICE, "PSP", "CHC", "MBM",
                'P', new UnificationEntry(plate, SOULARIUM),
                'S', "itemSkull",
                'C', CIRCUIT,
                'H', HULL,
                'M', MOTOR,
                'B', ModObject.blockDarkIronBars.getItemNN());

        // Soul Binder
        ModHandler.addShapelessRecipe("soul_binder", SOUL_BINDER[HV].getStackForm(), new ItemStack(MachineObject.block_soul_binder.getBlockNN()));
        MetaTileEntityLoader.registerMachineRecipe(SOUL_BINDER, "PSP", "CHC", "MZM",
                'P', new UnificationEntry(plate, SOULARIUM),
                'S', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 43),
                'C', CIRCUIT,
                'H', HULL,
                'M', MOTOR,
                'Z', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 41));

        // Powered Spawer
        //ModHandler.addShapelessRecipe("powered_spawner", POWERED_SPAWNER[HV].getStackForm(), new ItemStack(MachineObject.block_powered_spawner.getBlockNN()));
    }

    private static void tools() {
        // Piston Boots
        ModHandler.addShapedRecipe("piston_boots", GTEMetaItems.PISTON_BOOTS.getStackForm(),
                "EhE", "RLR", "PBP",
                'E', Items.LEATHER,
                'R', new UnificationEntry(plate, Rubber),
                'L', Items.LEATHER_BOOTS,
                'P', ELECTRIC_PISTON_LV,
                'B', BATTERY_LV_SODIUM);
    }

    private static void end_contents() {
        // Creative Energy Cell
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ENERGY_CLUSTER, 4)
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3, 8))
                .input(MetaTileEntities.HULL[UV])
                .input(CRYSTAL_MAINFRAME_UV, 4)
                .inputs(AEHelper.aeBlocks.energyCellDense().maybeStack(8).get())
                .input(COVER_SOLAR_PANEL_UV, 1)
                .fluidInputs(FLUIX.getFluid(18432))
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(Neutronium.getFluid(9216))
                .outputs(AEHelper.aeBlocks.energyCellCreative().maybeStack(1).get())
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        // Creative Capacitor Bank
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ENERGY_CLUSTER, 4)
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3, 8))
                .input(MetaTileEntities.HULL[UV])
                .input(CRYSTAL_MAINFRAME_UV, 4)
                .inputs(new ItemStack(PowerToolObject.block_cap_bank.getBlockNN(), 8, 3))
                .input(COVER_SOLAR_PANEL_UV, 1)
                .fluidInputs(VIBRANT_ALLOY.getFluid(18432))
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(Neutronium.getFluid(9216))
                .outputs(new ItemStack(PowerToolObject.block_cap_bank.getBlockNN(), 1, 0))
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        // GTE ME Storage Fake Component
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(screw, Neutronium, 8)
                .input(CRYSTAL_MAINFRAME_UV, 4)
                .inputs(new ItemStack(AEHelper.aeaItems.cell256kPart().maybeItem().get(), 16, 3))
                .inputs(new ItemStack(AEHelper.aeaItems.cell256kPart().maybeItem().get(), 16, 6))
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(Neutronium.getFluid(9216))
                .output(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 1)
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        // Infinite GT Energy Unit Emitter
        if (!Loader.isModLoaded(GTEValues.MODID_DE) && !Loader.isModLoaded(GTEValues.MODID_DA)) {
            RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                    .input(MetaTileEntities.HULL[UHV])
                    .inputs(AEHelper.aeBlocks.energyCellCreative().maybeStack(4).get())
                    .inputNBT(PowerToolObject.block_cap_bank.getBlockNN(), 4, NBTMatcher.ANY, NBTCondition.ANY)
                    .input(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 4)
                    .fluidInputs(SolderingAlloy.getFluid(18432))
                    .fluidInputs(UraniumRhodiumDinaquadide.getFluid(9216))
                    .outputs(MetaTileEntities.CREATIVE_ENERGY.getStackForm())
                    .duration(2000).EUt(VA[UHV])
                    .buildAndRegister();
        }

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
                .duration(2000).EUt(VA[UHV])
                .buildAndRegister();
    }
}
