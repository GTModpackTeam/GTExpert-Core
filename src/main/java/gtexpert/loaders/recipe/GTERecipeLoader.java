package gtexpert.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEMetalCasing;
import gtexpert.common.ModBlocks;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.loaders.recipe.CraftingComponent.FIELD_GENERATOR;
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

        //Greenhouse Casing
        ModHandler.addShapedRecipe("gte_metal_casing:0",ModBlocks.gteMetalCasing.getItemVariant(GTEMetalCasing.MetalCasingType.GREENHOUSE,2) ,
                "PhP", "PFP", "PwP", 'P',new UnificationEntry(plate, Galvalume),'F',new UnificationEntry(frameGt, Galvalume));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Galvalume, 6).input(OrePrefix.frameGt, Galvalume, 1).circuitMeta(6).outputs(ModBlocks.gteMetalCasing.getItemVariant(GTEMetalCasing.MetalCasingType.GREENHOUSE, 2)).duration(50).buildAndRegister();

        //Sawmill Casing
        ModHandler.addShapedRecipe("gte_metal_casing:1",ModBlocks.gteMetalCasing.getItemVariant(GTEMetalCasing.MetalCasingType.SAWMill,2) ,
                "PhP", "PFP", "PwP", 'P',new UnificationEntry(plate, TreatedWood),'F',new UnificationEntry(frameGt, TreatedWood));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, TreatedWood, 6).input(OrePrefix.frameGt, TreatedWood, 1).circuitMeta(6).outputs(ModBlocks.gteMetalCasing.getItemVariant(GTEMetalCasing.MetalCasingType.SAWMill, 2)).duration(50).buildAndRegister();

        //Void Miner Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[ZPM]).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).inputs(MetaItems.COVER_FLUID_VOIDING_ADVANCED.getStackForm()).inputs(MetaItems.VOLTAGE_COIL_ZPM.getStackForm(2)).inputs(MetaItems.FIELD_GENERATOR_ZPM.getStackForm()).input(OrePrefix.plate, NM_HEA_NPs, 6).fluidInputs(EnderPearl.getFluid(GTValues.L * 2)).outputs(ModBlocks.gteMetalCasing.getItemVariant(GTEMetalCasing.MetalCasingType.VOID_MINER, 2)).duration(100).buildAndRegister();

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

            //Greenhouse Recipes
            GTERecipeMaps.GREENHOUSE_RECIPES.recipeBuilder().notConsumable(GTUtility.copyAmount(16, allSaplings.get(i)))
                    .notConsumable(new IntCircuitIngredient(1))
                    .fluidInputs(Water.getFluid(1000))
                    .outputs(GTUtility.copyAmount(32, allWoodLogs.get(i)))
                    .outputs(GTUtility.copyAmount(6,allSaplings.get(i)))
                    .duration(1200).EUt(40)
                    .buildAndRegister();
            GTERecipeMaps.GREENHOUSE_RECIPES.recipeBuilder().notConsumable(GTUtility.copyAmount(16, allSaplings.get(i)))
                    .input(MetaItems.FERTILIZER,4)
                    .notConsumable(new IntCircuitIngredient(2))
                    .fluidInputs(Water.getFluid(1000))
                    .outputs(GTUtility.copyAmount(64, allWoodLogs.get(i)))
                    .outputs(GTUtility.copyAmount(6,allSaplings.get(i)))
                    .duration(900).EUt(20)
                    .buildAndRegister();
        }

        //plant Recipes
        List<ItemStack> allSeedItem =  Arrays.asList(new ItemStack(Items.PUMPKIN_SEEDS),new ItemStack(Items.BEETROOT_SEEDS),new ItemStack(Items.WHEAT_SEEDS),new ItemStack(Items.MELON_SEEDS),new ItemStack(Items.CARROT),new ItemStack(Items.POTATO),new ItemStack(Items.REEDS),new ItemStack(Blocks.CACTUS),new ItemStack(Blocks.BROWN_MUSHROOM),new ItemStack(Blocks.RED_MUSHROOM),new ItemStack(Items.NETHER_WART));
        List<ItemStack> allPlantItem =  Arrays.asList(new ItemStack(Blocks.PUMPKIN,6),new ItemStack(Items.BEETROOT,16),new ItemStack(Items.WHEAT,16),new ItemStack(Blocks.MELON_BLOCK,6),new ItemStack(Items.CARROT,12),new ItemStack(Items.POTATO,12),new ItemStack(Items.REEDS,12),new ItemStack(Blocks.CACTUS,12),new ItemStack(Blocks.BROWN_MUSHROOM,12),new ItemStack(Blocks.RED_MUSHROOM,12),new ItemStack(Items.NETHER_WART,12));

        for (int i = 0; i < allSeedItem.size(); i++) {
            //Greenhouse Recipes
            GTERecipeMaps.GREENHOUSE_RECIPES.recipeBuilder().notConsumable(allSeedItem.get(i))
                    .notConsumable(new IntCircuitIngredient(1))
                    .fluidInputs(Water.getFluid(1000))
                    .outputs(allPlantItem.get(i))
                    .duration(1200).EUt(40)
                    .buildAndRegister();
            GTERecipeMaps.GREENHOUSE_RECIPES.recipeBuilder().notConsumable(allSeedItem.get(i))
                    .input(MetaItems.FERTILIZER,4)
                    .notConsumable(new IntCircuitIngredient(2))
                    .fluidInputs(Water.getFluid(1000))
                    .outputs(allPlantItem.get(i))
                    .duration(900).EUt(20)
                    .buildAndRegister();
        }
    }
}
