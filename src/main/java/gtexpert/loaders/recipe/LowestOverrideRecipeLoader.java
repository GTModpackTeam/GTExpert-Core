package gtexpert.loaders.recipe;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.common.ConfigHolder;

import gtexpert.api.GTEValues;
import gtexpert.common.GTEConfigHolder;

public class LowestOverrideRecipeLoader {

    public static void init() {
        // Bed
        if (ConfigHolder.recipes.hardMiscRecipes) {
            for (int i = 0; i < Materials.CHEMICAL_DYES.length; i++) {
                EnumDyeColor color = EnumDyeColor.byMetadata(i);
                String colorName = color.getName().equals("silver") ? "light_gray" : color.getName();
                ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, colorName + "_bed"));

                ModHandler.addShapedRecipe(colorName + "_bed", new ItemStack(Items.BED, 1, i),
                        "CCC", "WWW", "FrF",
                        'C', new ItemStack(Blocks.CARPET, 1, i),
                        'W', "plankWood",
                        'F', "fenceWood");

                if (!colorName.equals("white"))
                    ModHandler.addShapelessRecipe("white_bed_from_" + colorName + "_bed",
                            new ItemStack(Items.BED, 1, 0),
                            new ItemStack(Items.BED, 1, i), "dyeWhite");
            }
        }

        // Terracotta
        if (GTEConfigHolder.ceuOverride.nerfTerracottaCrafting) {
            for (int i = 0; i < Materials.CHEMICAL_DYES.length; i++) {
                // Remove terracotta macerator recipes
                GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES,
                        new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, i));

                // Add terracotta macerator recipes
                RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, i))
                        .output(dust, Clay, 4)
                        .duration(60).EUt(VA[MV])
                        .buildAndRegister();
            }
        }
    }
}
