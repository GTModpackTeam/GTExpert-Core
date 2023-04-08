package gtexpert.loaders.recipe;

import gregtech.api.items.OreDictNames;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.metatileentities.MetaTileEntities;
import gtexpert.api.recipes.GTERecipeMaps;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

import java.util.List;
import java.util.stream.Collectors;

public class CEUOverrideRecipeLoader {
    public static void init() {
        materials();
        blocks();
        woods();
    }

    private static void materials() {
        // Iron Nugget
        ModHandler.addShapelessRecipe("wrought_iron_nugget", OreDictUnifier.get(nugget, Iron, 9), OreDictUnifier.get(ingot, Iron, 1));

        // Gold Nugget
        ModHandler.addShapelessRecipe("gold_nugget", OreDictUnifier.get(nugget, Gold, 9), OreDictUnifier.get(ingot, Gold, 1));

        // Wrought Iron Nugget
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(nugget, WroughtIron, 1), OreDictUnifier.get(nugget, WroughtIron, 1));

        // Wrought Iron Ingot
        ModHandler.addShapedRecipe("wrought_iron_ingot", OreDictUnifier.get(ingot, WroughtIron, 1), "XXX", "XXX", "XXX", 'X', OreDictUnifier.get(nugget, WroughtIron, 1));

        // Stone Rod
        ModHandler.addMirroredShapedRecipe("stone_rod", OreDictUnifier.get(stick, Stone), "s", "S", 'S', new UnificationEntry(block, Stone));

        // Glowstone Dust
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Glowstone, 2));
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Glowstone, 2)
                .output(dust, Redstone, 1)
                .output(dust, Gold, 1)
                .duration(488).EUt(80)
                .buildAndRegister();

        // Netherrack Dust
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Netherrack, 1));
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Netherrack, 1)
                .chancedOutput(dustTiny, Gold, 620, 120)
                .chancedOutput(dustTiny, Redstone, 5600, 850)
                .chancedOutput(dustTiny, Coal, 5600, 850)
                .chancedOutput(dustTiny, Glowstone, 5600, 850)
                .chancedOutput(dust, Sulfur, 9900, 100)
                .duration(160).EUt(20)
                .buildAndRegister();

        // ########################################
        // Quartzite (Bug Fix)
        // ########################################
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, OreDictUnifier.get(block, Quartzite, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES, OreDictUnifier.get(gem, Quartzite, 9));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FORGE_HAMMER_RECIPES, OreDictUnifier.get(block, Quartzite, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, Quartzite, 1)},
                new FluidStack[]{Lubricant.getFluid(3)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, Quartzite, 1)},
                new FluidStack[]{DistilledWater.getFluid(11)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, Quartzite, 1)},
                new FluidStack[]{Water.getFluid(15)}
        );

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .output(dust, Quartzite, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .output(gem, Quartzite, 4)
                .duration(20).EUt(2)
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(gem, Quartzite, 4)
                .output(block, Quartzite, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, Quartzite, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, Quartzite, 4)
                .duration(240).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .fluidInputs(Water.getFluid(15))
                .output(plate, Quartzite, 4)
                .duration(300).EUt(VA[LV])
                .buildAndRegister();


        // ########################################
        // Certus Quartz (Bug Fix)
        // ########################################
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, OreDictUnifier.get(block, CertusQuartz, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES, OreDictUnifier.get(gem, CertusQuartz, 9));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FORGE_HAMMER_RECIPES, OreDictUnifier.get(block, CertusQuartz, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, CertusQuartz, 1)},
                new FluidStack[]{CertusQuartz.getFluid(1296)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                new ItemStack[]{SHAPE_MOLD_BLOCK.getStackForm()},
                new FluidStack[]{CertusQuartz.getFluid(1296)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, CertusQuartz, 1)},
                new FluidStack[]{Lubricant.getFluid(3)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, CertusQuartz, 1)},
                new FluidStack[]{DistilledWater.getFluid(11)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, CertusQuartz, 1)},
                new FluidStack[]{Water.getFluid(15)}
        );

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidOutputs(CertusQuartz.getFluid(576))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .output(dust, CertusQuartz, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .output(gem, CertusQuartz, 4)
                .duration(20).EUt(2)
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(gem, CertusQuartz, 4)
                .output(block, CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(CertusQuartz.getFluid(576))
                .output(block, CertusQuartz, 1)
                .duration(20).EUt(7)
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, CertusQuartz, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, CertusQuartz, 4)
                .duration(240).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidInputs(Water.getFluid(15))
                .output(plate, CertusQuartz, 4)
                .duration(300).EUt(VA[LV])
                .buildAndRegister();
    }

    private static void blocks() {
        // Redstone Lamp
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech", "redstone_lamp"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Redstone, 4)
                .input(plate, Glowstone, 4)
                .output(Blocks.REDSTONE_LAMP)
                .duration(100).EUt(1)
                .buildAndRegister();

        // Crafting Station
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(plank, Wood, 4)
                .input(OreDictNames.chestWood.toString(), 2)
                .input(slab, Wood, 1)
                .input(Blocks.CRAFTING_TABLE)
                .output(MetaTileEntities.WORKBENCH)
                .duration(100).EUt(16)
                .buildAndRegister();
    }

    private static void woods() {
        // Wood sticks
        ModHandler.removeRecipeByOutput(new ItemStack(Items.STICK, 2));
        ModHandler.removeRecipeByOutput(new ItemStack(Items.STICK, 4));
        ModHandler.addMirroredShapedRecipe("stick_normal", new ItemStack(Items.STICK, 1), "P", "P", 'P', new UnificationEntry(plank, Wood));
        ModHandler.addMirroredShapedRecipe("stick_saw", new ItemStack(Items.STICK, 2), "s", "P", "P", 'P', new UnificationEntry(plank, Wood));

        // Wood planks
        List<ItemStack> allWoodLogs = OreDictUnifier.getAllWithOreDictionaryName("logWood").stream()
                .flatMap(stack -> GTUtility.getAllSubItems(stack).stream())
                .collect(Collectors.toList());
        for (int i = 0; i < allWoodLogs.size(); i++) {
            Pair<IRecipe, ItemStack> outputPair = ModHandler.getRecipeOutput(null, allWoodLogs.get(i));
            ItemStack plankStack = outputPair.getValue();
            if (plankStack.isEmpty()) continue;

            ModHandler.removeRecipeByOutput(GTUtility.copyAmount(2, plankStack));
            ModHandler.removeRecipeByOutput(GTUtility.copyAmount(4, plankStack));
            ModHandler.addShapelessRecipe("plank_" + i, GTUtility.copyAmount(1, plankStack), allWoodLogs.get(i));
            ModHandler.addMirroredShapedRecipe("plank_saw_" + i, GTUtility.copyAmount(2, plankStack), "s", "P", "P", 'P', allWoodLogs.get(i));

            GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .inputs(GTUtility.copyAmount(6, allWoodLogs.get(i)))
                    .fluidInputs(Water.getFluid(1000))
                    .outputs(GTUtility.copyAmount(48, plankStack))
                    .output(dust, Wood, 12)
                    .duration(300).EUt(VA[LV])
                    .buildAndRegister();
            GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .inputs(GTUtility.copyAmount(6, allWoodLogs.get(i)))
                    .fluidInputs(Water.getFluid(2500))
                    .outputs(GTUtility.copyAmount(60, plankStack))
                    .duration(600).EUt(VA[LV])
                    .buildAndRegister();
        }
    }
}
