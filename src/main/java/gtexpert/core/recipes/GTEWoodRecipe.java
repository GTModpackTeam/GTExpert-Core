package gtexpert.core.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.core.GTEConfigHolder.ceuOverride;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import org.apache.commons.lang3.tuple.Pair;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.wood.BlockGregPlanks;

import gtexpert.api.util.Mods;
import gtexpert.core.GTERecipeMaps;

public class GTEWoodRecipe {

    public static void init() {
        sticks();
        planks();
    }

    private static void sticks() {
        ModHandler.removeRecipeByName(Mods.GregTech.getResource("stick_normal"));
        ModHandler.addMirroredShapedRecipe("stick_normal", ConfigHolder.recipes.harderRods ?
                ceuOverride.moreNerfStickCrafting ?
                        new ItemStack(Items.STICK, 1) : new ItemStack(Items.STICK, 2) :
                new ItemStack(Items.STICK, 4),
                "P", "P", 'P', new UnificationEntry(plank, Materials.Wood));
        ModHandler.removeRecipeByName(Mods.GregTech.getResource("stick_saw"));
        ModHandler.addMirroredShapedRecipe("stick_saw", ConfigHolder.recipes.harderRods ?
                ceuOverride.moreNerfStickCrafting ?
                        new ItemStack(Items.STICK, 2) : new ItemStack(Items.STICK, 4) :
                new ItemStack(Items.STICK, 6),
                "s", "P", "P", 'P', new UnificationEntry(plank, Materials.Wood));

        ModHandler.removeRecipeByName(Mods.GregTech.getResource("treated_wood_stick"));
        ModHandler.addMirroredShapedRecipe("treated_wood_stick", ConfigHolder.recipes.harderRods ?
                ceuOverride.moreNerfStickCrafting ?
                        OreDictUnifier.get(stick, Materials.TreatedWood, 1) :
                        OreDictUnifier.get(stick, Materials.TreatedWood, 2) :
                OreDictUnifier.get(stick, Materials.TreatedWood, 4),
                "P", "P", 'P', MetaBlocks.PLANKS.getItemVariant(BlockGregPlanks.BlockType.TREATED_PLANK));
        ModHandler.removeRecipeByName(Mods.GregTech.getResource("treated_wood_stick_saw"));
        ModHandler.addMirroredShapedRecipe("treated_wood_stick_saw", ConfigHolder.recipes.harderRods ?
                ceuOverride.moreNerfStickCrafting ?
                        OreDictUnifier.get(stick, Materials.TreatedWood, 2) :
                        OreDictUnifier.get(stick, Materials.TreatedWood, 4) :
                OreDictUnifier.get(stick, Materials.TreatedWood, 6),
                "s", "P", "P", 'P', MetaBlocks.PLANKS.getItemVariant(BlockGregPlanks.BlockType.TREATED_PLANK));
    }

    private static void planks() {
        List<ItemStack> allWoodLogs = new ArrayList<>();
        for (ItemStack stack : OreDictUnifier.getAllWithOreDictionaryName("logWood")) {
            allWoodLogs.addAll(GTUtility.getAllSubItems(stack.getItem()));
        }
        IntStream.range(0, allWoodLogs.size()).forEach(i -> {
            Pair<IRecipe, ItemStack> outputPair = ModHandler.getRecipeOutput(null, allWoodLogs.get(i));
            ItemStack plankStack = outputPair.getValue();
            if (plankStack.isEmpty()) return;
            ModHandler.removeRecipeByOutput(GTUtility.copy(ConfigHolder.recipes.nerfWoodCrafting ? 2 : 4, plankStack));
            ModHandler.removeRecipeByOutput(GTUtility.copy(ConfigHolder.recipes.nerfWoodCrafting ? 4 : 6, plankStack));
            ModHandler.addShapelessRecipe("plank_" + i, GTUtility.copy(ConfigHolder.recipes.nerfWoodCrafting ?
                    ceuOverride.moreNerfPlankCrafting ? 1 : 2 : 4,
                    plankStack), allWoodLogs.get(i));
            ModHandler.addMirroredShapedRecipe("plank_saw_" + i, GTUtility.copy(ConfigHolder.recipes.nerfWoodCrafting ?
                    ceuOverride.moreNerfPlankCrafting ? 2 : 4 : 6,
                    plankStack), "s", "P", 'P', allWoodLogs.get(i));
            recipeSawmill(allWoodLogs.get(i), plankStack);

            if (!plankStack.toString().contains(Mods.Vanilla.name()) ||
                    !plankStack.toString().contains(Mods.GregTechFoodOption.name()))
                recipeCutter(allWoodLogs.get(i), plankStack);
        });
    }

    private static void recipeSawmill(ItemStack input, ItemStack output) {
        GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(GTUtility.copy(6, input))
                .fluidInputs(Materials.Water.getFluid(1000))
                .outputs(GTUtility.copy(48, output))
                .output(dust, Materials.Wood, 12)
                .duration(600).EUt(VA[LV])
                .buildAndRegister();
        GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(GTUtility.copy(6, input))
                .fluidInputs(Materials.Water.getFluid(2500))
                .outputs(GTUtility.copy(60, output))
                .duration(800).EUt(VA[LV])
                .buildAndRegister();
    }

    private static void recipeCutter(ItemStack input, ItemStack output) {
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(input)
                .fluidInputs(Materials.Lubricant.getFluid(1))
                .outputs(GTUtility.copy(6, output))
                .output(dust, Materials.Wood, 2)
                .duration(200).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(input)
                .fluidInputs(Materials.DistilledWater.getFluid(3))
                .outputs(GTUtility.copy(6, output))
                .output(dust, Materials.Wood, 2)
                .duration(300).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(input)
                .fluidInputs(Materials.Water.getFluid(4))
                .outputs(GTUtility.copy(6, output))
                .output(dust, Materials.Wood, 2)
                .duration(400).EUt(VA[ULV])
                .buildAndRegister();
    }
}
