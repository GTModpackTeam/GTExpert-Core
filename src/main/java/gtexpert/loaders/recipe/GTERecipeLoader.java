package gtexpert.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.recipe.MetaTileEntityLoader;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.powertools.init.PowerToolObject;
import appeng.api.AEApi;
import com.the9grounds.aeadditions.api.AEAApi;
import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import org.apache.commons.lang3.tuple.Pair;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;
import static gtexpert.api.unification.material.GTEMaterials.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GTERecipeLoader {
    public static void init() {
        materias();
        blocks();
        tools();
        end_contents();
    }

    private static void materias() {
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

        // Galvalume Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Steel, 4)
                .input(dust, Zinc, 1)
                .input(dust, Aluminium, 1)
                .output(dust, Galvalume, 6)
                .duration(50).EUt(VA[LV])
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

    private static void blocks() {
        // Extreme Mixer
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaTileEntities.MIXER[ZPM].getStackForm())
                .inputs(FIELD_GENERATOR_ZPM.getStackForm())
                .outputs(EXTREME_MIXER[0].getStackForm())
                .duration(200).EUt(VA[ZPM])
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
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.HULL[ZPM])
                .input(frameGt, NaquadahAlloy, 4)
                .input(circuit, MarkerMaterials.Tier.ZPM,4)
                .input(ELECTRIC_MOTOR_ZPM, 4)
                .input(ELECTRIC_PUMP_ZPM, 4)
                .input(CONVEYOR_MODULE_ZPM,4)
                .input(ELECTRIC_PISTON_ZPM,4)
                .input(ROBOT_ARM_ZPM,4)
                .input(EMITTER_ZPM,4)
                .input(SENSOR_ZPM, 4)
                .input(ORE_DICTIONARY_FILTER)
                .input(gear, NaquadahAlloy, 4)
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .outputs(VOIDOREMINER.getStackForm())
                .duration(600).EUt(VA[ZPM])
                .buildAndRegister();

        // Treated Wood Machine Casing
        ModHandler.addShapedRecipe("casing_treated_wood", GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill,2),
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
                .output(GTEMetaBlocks.BLOCK_SAWMILL_CONVEYOR,1)
                .duration(100).EUt(VA[MV])
                .buildAndRegister();

        // Wood Recipes
        List<ItemStack> allWoodLogs = OreDictUnifier.getAllWithOreDictionaryName("logWood").stream()
                .flatMap(stack -> GTUtility.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        for (ItemStack allWoodLog : allWoodLogs) {
            Pair<IRecipe, ItemStack> outputPair = ModHandler.getRecipeOutput(null, allWoodLog);
            ItemStack plankStack = outputPair.getValue();
            if (plankStack.isEmpty()) continue;

            //Sawmill Recipes
            GTERecipeMaps.SAWMill_RECIPES.recipeBuilder()
                    .inputs(GTUtility.copyAmount(6, allWoodLog))
                    .fluidInputs(Water.getFluid(1000))
                    .outputs(GTUtility.copyAmount(48, plankStack), OreDictUnifier.get(dust, Wood, 12))
                    .duration(200).EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Void Ore Miner Recipes
        List<Material> materialOres = new ArrayList<>();
        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            if (material.hasProperty(PropertyKey.ORE)) {
                materialOres.add(material);
            }
        }
        for(Material materialOre : materialOres){
            GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                    .input(ore, materialOre)
                    .fluidInputs(EnderPearl.getFluid(576))
                    .fluidInputs(DrillingFluid.getFluid(10000))
                    .output(ore, materialOre,32)
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
            GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                    .input(oreNetherrack, materialOre)
                    .fluidInputs(EnderPearl.getFluid(576))
                    .fluidInputs(DrillingFluid.getFluid(10000))
                    .output(oreNetherrack, materialOre,64)
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
            GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder()
                    .input(oreEndstone, materialOre)
                    .fluidInputs(EnderPearl.getFluid(576))
                    .fluidInputs(DrillingFluid.getFluid(10000))
                    .output(oreEndstone, materialOre,64)
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
        }

        // Vial Extractor
        MetaTileEntityLoader.registerMachineRecipe(VIAL_EXTRACTOR, "VRV", "PMF", "WCW",
                'V', ModObject.itemSoulVial.getItem(),
                'R', SENSOR,
                'P', PISTON,
                'M', HULL,
                'F', PUMP,
                'W', CABLE,
                'C', CIRCUIT);
    }

    private static void tools() {
        // Piston Boots
        ModHandler.addShapedRecipe("piston_boots", GTEMetaItems.PISTON_BOOTS.getStackForm(),
                "EhE", "RLR", "PBP",
                'E', Items.LEATHER,
                'R', new UnificationEntry(plate, Rubber),
                'L', Items.LEATHER_BOOTS,
                'P', ELECTRIC_PISTON_LV,
                'B', BATTERY_LV_LITHIUM);
    }

    private static void end_contents() {
        // Creative Energy Cell
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ENERGY_CLUSTER, 4)
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3, 8))
                .input(MetaTileEntities.HULL[UV])
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .inputs(AEApi.instance().definitions().blocks().energyCellDense().maybeStack(8).get())
                .input(COVER_SOLAR_PANEL_UV, 1)
                .fluidInputs(FLUIX.getFluid(18432))
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(Neutronium.getFluid(9216))
                .outputs(AEApi.instance().definitions().blocks().energyCellCreative().maybeStack(1).get())
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        // Creative Capacitor Bank
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ENERGY_CLUSTER, 4)
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3, 8))
                .input(MetaTileEntities.HULL[UV])
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .inputs(new ItemStack(PowerToolObject.block_cap_bank.getBlockNN(), 8, 2))
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
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .inputs(new ItemStack(AEAApi.instance().items().cell256kPart().maybeItem().get(), 16, 3))
                .inputs(new ItemStack(AEAApi.instance().items().cell256kPart().maybeItem().get(), 16, 6))
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(Neutronium.getFluid(9216))
                .output(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 1)
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        // Infinite GT Energy Unit Emitter
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.HULL[UHV])
                .input(DAFeatures.chaoticEnergyCore, 8)
                .input(DAFeatures.chaosStabilizerCore, 8)
                .inputs(AEApi.instance().definitions().blocks().energyCellCreative().maybeStack(4).get())
                .inputNBT(PowerToolObject.block_cap_bank.getBlockNN(), 4, NBTMatcher.EQUAL_TO, NBTCondition.ANY)
                .input(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 4)
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(UraniumRhodiumDinaquadide.getFluid(9216))
                .outputs(MetaTileEntities.CREATIVE_ENERGY.getStackForm())
                .duration(2000).EUt(VA[UHV])
                .buildAndRegister();

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
                .input(MetaTileEntities.CREATIVE_TANK, 16)
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
