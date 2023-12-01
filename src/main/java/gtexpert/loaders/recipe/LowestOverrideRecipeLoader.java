package gtexpert.loaders.recipe;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;

import gtexpert.common.GTEConfigHolder;

public class LowestOverrideRecipeLoader {

    public static void init() {
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
