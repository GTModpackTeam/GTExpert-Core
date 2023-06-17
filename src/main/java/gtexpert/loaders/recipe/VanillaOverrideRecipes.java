package gtexpert.loaders.recipe;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;

import gtexpert.api.GTEValues;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class VanillaOverrideRecipes {

    public static void init() {
        // bookshelf
        OreDictionary.registerOre("bookshelf", new ItemStack(Blocks.BOOKSHELF));

        items();
        blocks();
    }

    private static void items() {
        // Book
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES, new ItemStack(Blocks.BOOKSHELF));
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input("bookshelf", 1)
                .outputs(new ItemStack(Items.BOOK, 3))
                .duration(300).EUt(2)
                .buildAndRegister();
    }

    private static void blocks() {
        // Diorite
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, "diorite"));

        // Granite
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, "granite"));

        // Andesite
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, "andesite"));

        // Comparator
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/vanilla_comparator"));

        // Daylight Sensor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "misc/vanilla_daylight_detector"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, NetherQuartz, 3)
                .input(slab, Wood, 2)
                .fluidInputs(Glass.getFluid(144))
                .output(Blocks.DAYLIGHT_DETECTOR)
                .duration(200).EUt(10)
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, CertusQuartz, 3)
                .input(slab, Wood, 2)
                .fluidInputs(Glass.getFluid(144))
                .output(Blocks.DAYLIGHT_DETECTOR)
                .duration(200).EUt(10)
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, Quartzite, 3)
                .input(slab, Wood, 2)
                .fluidInputs(Glass.getFluid(144))
                .output(Blocks.DAYLIGHT_DETECTOR)
                .duration(200).EUt(10)
                .buildAndRegister();

        // Ennchanting Table
        ModHandler.removeRecipeByOutput(new ItemStack(Blocks.ENCHANTING_TABLE));
        ModHandler.addShapedRecipe("enchantment_table", new ItemStack(Blocks.ENCHANTING_TABLE),
                "DCD", "PBP", "DPD",
                'D', OreDictUnifier.get(gem, Diamond),
                'C', new ItemStack(Blocks.CARPET, 1, 14),
                'P', OreDictUnifier.get(plate, Obsidian),
                'B', "bookshelf");
    }
}
