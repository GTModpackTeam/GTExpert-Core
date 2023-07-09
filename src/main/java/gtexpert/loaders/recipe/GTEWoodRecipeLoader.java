package gtexpert.loaders.recipe;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.util.GTUtility.copyAmount;
import static gtexpert.api.util.GTEUtils.getModItem;
import static gtexpert.common.GTEConfigHolder.*;

public class GTEWoodRecipeLoader {

    public static void init() {
        sticks();
        planks();
    }

    private static void sticks() {
        if (ConfigHolder.recipes.nerfWoodCrafting) {
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CEU, "stick_normal"));
            ModHandler.addMirroredShapedRecipe("stick_normal", ceuOverride.moreNerfWoodCrafting ?
                    new ItemStack(Items.STICK, 1) : new ItemStack(Items.STICK, 2), "P", "P", 'P',
                    new UnificationEntry(plank, Wood));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CEU, "stick_saw"));
            ModHandler
                    .addMirroredShapedRecipe("stick_saw",
                            ceuOverride.moreNerfWoodCrafting ? new ItemStack(Items.STICK, 2) :
                                    new ItemStack(Items.STICK, 4),
                            "s", "P", "P", 'P', new UnificationEntry(plank, Wood));
        }
    }

    private static void planks() {
        List<ItemStack> allWoodLogs = new ArrayList<>();
        for (ItemStack stack : OreDictUnifier.getAllWithOreDictionaryName("logWood")) {
            allWoodLogs.addAll(GTUtility.getAllSubItems(stack));
        }
        for (int i = 0; i < allWoodLogs.size(); i++) {
            Pair<IRecipe, ItemStack> outputPair = ModHandler.getRecipeOutput(null, allWoodLogs.get(i));
            ItemStack plankStack = outputPair.getValue();
            if (plankStack.isEmpty()) continue;

            ModHandler.removeRecipeByOutput(
                    GTUtility.copyAmount(ConfigHolder.recipes.nerfWoodCrafting ? 2 : 4, plankStack));
            ModHandler.removeRecipeByOutput(
                    GTUtility.copyAmount(ConfigHolder.recipes.nerfWoodCrafting ? 4 : 6, plankStack));
            ModHandler.addShapelessRecipe("plank_" + i,
                    GTUtility.copyAmount(
                            ConfigHolder.recipes.nerfWoodCrafting ? ceuOverride.moreNerfWoodCrafting ? 1 : 2 : 4,
                            plankStack),
                    allWoodLogs.get(i));
            ModHandler.addMirroredShapedRecipe("plank_saw_" + i,
                    GTUtility.copyAmount(
                            ConfigHolder.recipes.nerfWoodCrafting ? ceuOverride.moreNerfWoodCrafting ? 2 : 4 : 6,
                            plankStack),
                    "s", "P", 'P', allWoodLogs.get(i));

            recipeSawmill(allWoodLogs.get(i), plankStack);
            recipeCutter(allWoodLogs.get(i), plankStack);
        }
    }

    public static void recipeSawmill(ItemStack input, ItemStack output) {
        GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(copyAmount(6, input))
                .fluidInputs(Water.getFluid(1000))
                .outputs(copyAmount(48, output))
                .output(dust, Wood, 12)
                .duration(600).EUt(VA[LV])
                .buildAndRegister();
        GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(copyAmount(6, input))
                .fluidInputs(Water.getFluid(2500))
                .outputs(copyAmount(60, output))
                .duration(800).EUt(VA[LV])
                .buildAndRegister();
    }

    public static void recipeCutter(ItemStack input, ItemStack output) {
        if (input.equals(new ItemStack(Blocks.LOG, 1, 0)) ||
                input.equals(new ItemStack(Blocks.LOG, 1, 1)) ||
                input.equals(new ItemStack(Blocks.LOG, 1, 2)) ||
                input.equals(new ItemStack(Blocks.LOG, 1, 3)) ||
                input.equals(new ItemStack(Blocks.LOG2, 1, 0)) ||
                input.equals(new ItemStack(Blocks.LOG2, 1, 1)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_0", 1, 0)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_0", 1, 4)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_0", 1, 8)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_0", 1, 12)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_1", 1, 0)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_1", 1, 4)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_1", 1, 8)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_1", 1, 12)) ||
                input.equals(getModItem(GTEValues.MODID_GTFO, "gtfo_log_2", 1, 0)))
            return;

        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(input)
                .fluidInputs(Lubricant.getFluid(1))
                .outputs(copyAmount(6, output))
                .output(dust, Wood, 2)
                .duration(200).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(input)
                .fluidInputs(DistilledWater.getFluid(3))
                .outputs(copyAmount(6, output))
                .output(dust, Wood, 2)
                .duration(300).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(input)
                .fluidInputs(Water.getFluid(4))
                .outputs(copyAmount(6, output))
                .output(dust, Wood, 2)
                .duration(400).EUt(VA[ULV])
                .buildAndRegister();
    }
}
