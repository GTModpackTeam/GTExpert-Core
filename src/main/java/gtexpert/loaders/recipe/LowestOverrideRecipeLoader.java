package gtexpert.loaders.recipe;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;

import gtexpert.common.GTEConfigHolder;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class LowestOverrideRecipeLoader {

    public static void init() {
        // Bed
        // for (int i = 0; i < Materials.CHEMICAL_DYES.length; i++) {
        // EnumDyeColor color = EnumDyeColor.byMetadata(i);
        //
        // ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_VANILLA, color + "_bed"));
        // ModHandler.addShapedRecipe(color + "_bed", new ItemStack(Blocks.BED, 1, i), // TODO: crash
        // "CCC", "WWW", "FmF",
        // 'C', new ItemStack(Blocks.CARPET, 1, i),
        // 'W', "plankWood",
        // 'F', "fenceWood");
        // GameRegistry.addShapedRecipe(new ResourceLocation(GTEValues.MODID_VANILLA, color + "_bed"), // TODO: add?
        // null, new ItemStack(Blocks.BED, 1, i),
        // "CCC", "WWW", "FMF",
        // 'C', new ItemStack(Blocks.CARPET, 1, i),
        // 'W', "plankWood",
        // 'F', "fenceWood",
        // 'M', ToolItems.SOFT_MALLET.get());
        // }

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
