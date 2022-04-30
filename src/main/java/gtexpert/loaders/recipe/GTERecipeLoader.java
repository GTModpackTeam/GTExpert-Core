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
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import org.apache.commons.lang3.tuple.Pair;

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

        //SawMill Recipes
        List<ItemStack> allWoodLogs = OreDictUnifier.getAllWithOreDictionaryName("logWood").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        for (ItemStack stack : allWoodLogs) {

            Pair<IRecipe, ItemStack> outputPair = ModHandler.getRecipeOutput(null, stack);
            ItemStack plankStack = outputPair.getValue();
            if (plankStack.isEmpty()) {
                continue;
            }

            GTERecipeMaps.SAWMill_RECIPES.recipeBuilder().inputs(GTUtility.copyAmount(6, stack))
                    .fluidInputs(Water.getFluid(1000))
                    .outputs(GTUtility.copyAmount(48, plankStack), OreDictUnifier.get(dust, Wood, 12))
                    .duration(200).EUt(VA[ULV])
                    .buildAndRegister();
        }

        //Extreme Mixer
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[ZPM]).inputs(MetaTileEntities.MIXER[ZPM].getStackForm()).inputs(MetaItems.FIELD_GENERATOR_ZPM.getStackForm()).outputs(GTEMetaTileEntities.EXTREME_MIXER[0].getStackForm()).duration(200).buildAndRegister();
    }
}
