package gtexpert.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
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
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
import crazypants.enderio.base.item.soulvial.ItemSoulVial;
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
import static gregtech.common.metatileentities.MetaTileEntities.HULL;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

public class GTERecipeLoader {

    public static void init() {

        // Galvalume Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Steel, 4)
                .input(dust, Zinc, 1)
                .input(dust, Aluminium, 1)
                .notConsumable(new IntCircuitIngredient(1))
                .output(dust, Galvalume, 6)
                .duration(50).EUt(VA[LV]).buildAndRegister();

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
                .duration(100).EUt(VA[ZPM]).buildAndRegister();

        //Extreme Mixer
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[ZPM]).inputs(MetaTileEntities.MIXER[ZPM].getStackForm()).inputs(MetaItems.FIELD_GENERATOR_ZPM.getStackForm()).outputs(GTEMetaTileEntities.EXTREME_MIXER[0].getStackForm()).duration(200).buildAndRegister();

        //Sawmill
        ModHandler.addShapedRecipe("gte_sawmill",GTEMetaTileEntities.SAWMILL.getStackForm() ,
                "SBs", "MHM", "COC", 'S', new UnificationEntry(screw, Steel),'B',new UnificationEntry(toolHeadBuzzSaw, Steel),'M',MetaItems.ELECTRIC_MOTOR_MV.getStackForm(),'H',MetaTileEntities.HULL[2].getStackForm(),'C',new UnificationEntry(circuit, MarkerMaterials.Tier.MV), 'O',MetaItems.CONVEYOR_MODULE_MV.getStackForm());

        //Void Ore Miner
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(VA[ZPM])
                .input(HULL[ZPM])
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
                .outputs(GTEMetaTileEntities.VOIDOREMINER.getStackForm()).duration(600).buildAndRegister();

        //Sawmill Casing
        ModHandler.addShapedRecipe("gte_metal_casing:1", GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill,2) ,
                "PhP", "PFP", "PwP", 'P',new UnificationEntry(plate, TreatedWood),'F',new UnificationEntry(frameGt, TreatedWood));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, TreatedWood, 6).input(OrePrefix.frameGt, TreatedWood, 1).circuitMeta(6).outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill, 2)).duration(50).buildAndRegister();

        //Void Ore Miner Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[ZPM]).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).inputs(MetaItems.COVER_FLUID_VOIDING_ADVANCED.getStackForm()).inputs(MetaItems.VOLTAGE_COIL_ZPM.getStackForm(2)).inputs(MetaItems.FIELD_GENERATOR_ZPM.getStackForm()).input(OrePrefix.plate, NM_HEA_NPs, 6).fluidInputs(EnderPearl.getFluid(GTValues.L * 2)).outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER, 2)).duration(100).buildAndRegister();

        //Sawmill Conveyor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[MV]).input(CONVEYOR_MODULE_MV, 1).input(OrePrefix.frameGt, TreatedWood, 1).input(Items.LEATHER, 3).fluidInputs(Glue.getFluid(100)).output(GTEMetaBlocks.BLOCK_SAWMILL_CONVEYOR,1).duration(100).buildAndRegister();

        //Piston Boots
        ModHandler.addShapedRecipe("piston_boots", GTEMetaItems.PISTON_BOOTS.getStackForm() ,
                "EhE", "RLR", "PBP", 'E',Items.LEATHER,'R',new UnificationEntry(plate, Rubber),'L',Items.LEATHER_BOOTS,'P',ELECTRIC_PISTON_LV, 'B',BATTERY_LV_LITHIUM);

        //Wood Recipes
        List<ItemStack> allWoodLogs = OreDictUnifier.getAllWithOreDictionaryName("logWood").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());
        List<ItemStack> allSaplings = OreDictUnifier.getAllWithOreDictionaryName("treeSapling").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
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
            GTERecipeMaps.SAWMill_RECIPES.recipeBuilder().inputs(GTUtility.copyAmount(6, allWoodLogs.get(i)))
                    .fluidInputs(Water.getFluid(1000))
                    .outputs(GTUtility.copyAmount(48, plankStack), OreDictUnifier.get(dust, Wood, 12))
                    .duration(200).EUt(VA[ULV])
                    .buildAndRegister();
        }

        //plant Recipes
        List<ItemStack> allSeedItem =  Arrays.asList(new ItemStack(Items.PUMPKIN_SEEDS),new ItemStack(Items.BEETROOT_SEEDS),new ItemStack(Items.WHEAT_SEEDS),new ItemStack(Items.MELON_SEEDS),new ItemStack(Items.CARROT),new ItemStack(Items.POTATO),new ItemStack(Items.REEDS),new ItemStack(Blocks.CACTUS),new ItemStack(Blocks.BROWN_MUSHROOM),new ItemStack(Blocks.RED_MUSHROOM),new ItemStack(Items.NETHER_WART));
        List<ItemStack> allPlantItem =  Arrays.asList(new ItemStack(Blocks.PUMPKIN,6),new ItemStack(Items.BEETROOT,16),new ItemStack(Items.WHEAT,16),new ItemStack(Blocks.MELON_BLOCK,6),new ItemStack(Items.CARROT,12),new ItemStack(Items.POTATO,12),new ItemStack(Items.REEDS,12),new ItemStack(Blocks.CACTUS,12),new ItemStack(Blocks.BROWN_MUSHROOM,12),new ItemStack(Blocks.RED_MUSHROOM,12),new ItemStack(Items.NETHER_WART,12));

        //Void Ore Miner Recipes
        List<Material> materialOres = new ArrayList<>();
        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            if (material.hasProperty(PropertyKey.ORE)) {
                materialOres.add(material);
            }
        }
        for(Material materialOre : materialOres){
            GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder().input(ore, materialOre)
                    .fluidInputs(EnderPearl.getFluid(576))
                    .fluidInputs(DrillingFluid.getFluid(10000))
                    .output(ore, materialOre,32)
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
            GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder().input(oreNetherrack, materialOre)
                    .fluidInputs(EnderPearl.getFluid(576))
                    .fluidInputs(DrillingFluid.getFluid(10000))
                    .output(oreNetherrack, materialOre,64)
                    .duration(20).EUt(VA[ZPM])
                    .buildAndRegister();
            GTERecipeMaps.VOID_ORE_MINER_RECIPES.recipeBuilder().input(oreEndstone, materialOre)
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
                .duration(500).EUt(VA[LV])
                .buildAndRegister();
    }
}
