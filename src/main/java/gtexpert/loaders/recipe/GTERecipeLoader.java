package gtexpert.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
import com.brandon3055.draconicevolution.DEFeatures;
import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

public class GTERecipeLoader {

    public static void init() {
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

        // Extreme Mixer
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(MetaTileEntities.MIXER[ZPM].getStackForm())
                .inputs(FIELD_GENERATOR_ZPM.getStackForm())
                .outputs(EXTREME_MIXER[0].getStackForm())
                .duration(200).EUt(VA[ZPM])
                .buildAndRegister();

        // Sawmill
        ModHandler.addShapedRecipe("gte_sawmill", SAWMILL.getStackForm(),
                "SBs", "MHM", "COC",
                'S', new UnificationEntry(screw, Steel),
                'B', new UnificationEntry(toolHeadBuzzSaw, Steel),
                'M', ELECTRIC_MOTOR_MV.getStackForm(),
                'H', MetaTileEntities.HULL[2].getStackForm(),
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

        // Draconic Fusion Crafter
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(MetaTileEntities.HULL[LuV])
                .input(frameGt, DRACONIUM, 4)
                .input(DEFeatures.wyvernCore, 4)
                .input(ROBOT_ARM_LuV, 2)
                .input(SENSOR_LuV, 2)
                .input(EMITTER_LuV, 2)
                .outputs(DRACONIUM_FUSION.getStackForm())
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();

        // Draconic Awakened Fusion Crafter
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(MetaTileEntities.HULL[UV])
                .input(frameGt, DRACONIUM_AWAKENED, 4)
                .input(DEFeatures.awakenedCore, 4)
                .input(ROBOT_ARM_UV, 2)
                .input(SENSOR_UV, 2)
                .input(EMITTER_UV, 2)
                .outputs(DRACONIUM_AWAKENED_FUSION.getStackForm())
                .duration(600).EUt(VA[UV])
                .buildAndRegister();

        // Sawmill Casing
        ModHandler.addShapedRecipe("gte_metal_casing:1", GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill,2),
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

        // Draconum Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, DRACONIUM, 6)
                .input(frameGt, DRACONIUM, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING, 2))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Awakened Draconum Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, DRACONIUM_AWAKENED, 6)
                .input(frameGt, DRACONIUM_AWAKENED, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_AWAKENED_CASING, 2))
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

        // Piston Boots
        ModHandler.addShapedRecipe("piston_boots", GTEMetaItems.PISTON_BOOTS.getStackForm(),
                "EhE", "RLR", "PBP",
                'E', Items.LEATHER,
                'R', new UnificationEntry(plate, Rubber),
                'L', Items.LEATHER_BOOTS,
                'P', ELECTRIC_PISTON_LV,
                'B', BATTERY_LV_LITHIUM);

        // Wood Recipes
        List<ItemStack> allWoodLogs = OreDictUnifier.getAllWithOreDictionaryName("logWood").stream()
                .flatMap(stack -> GTUtility.getAllSubItems(stack).stream())
                .collect(Collectors.toList());
        List<ItemStack> allSaplings = OreDictUnifier.getAllWithOreDictionaryName("treeSapling").stream()
                .flatMap(stack -> GTUtility.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        for (int i = 0; i < allWoodLogs.size(); i++) {

            if(allSaplings.get(i)==null)
                break;

            Pair<IRecipe, ItemStack> outputPair = ModHandler.getRecipeOutput(null, allWoodLogs.get(i));
            ItemStack plankStack = outputPair.getValue();
            if (plankStack.isEmpty()) {
                continue;
            }

            //Sawmill Recipes
            GTERecipeMaps.SAWMill_RECIPES.recipeBuilder()
                    .inputs(GTUtility.copyAmount(6, allWoodLogs.get(i)))
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
}
