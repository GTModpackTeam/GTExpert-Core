package gtexpert.integration.ffm.recipes;

import static gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static net.minecraft.init.Items.BRICK;
import static net.minecraft.init.Items.CLAY_BALL;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.ConfigHolder;

import gtexpert.api.util.Mods;

public class FFMBlockRecipe {

    public static void init() {
        if (Mods.ForestryCore.isModLoaded()) {
            blockCore();
        }
        if (Mods.ForestryCharcoal.isModLoaded()) {
            blockCharcoal();
        }
    }

    public static void blockCore() {
        // Ash Brick
        if (ConfigHolder.recipes.harderBrickRecipes) {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("ash_brick"));
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .input(OrePrefix.dust, Materials.Ash, 4)
                    .inputs(new ItemStack(BRICK, 4))
                    .outputs(Mods.Forestry.getItem("ash_brick"))
                    .duration(400).EUt(2).buildAndRegister();
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .input(OrePrefix.dust, Materials.Ash, 4)
                    .inputs(new ItemStack(Blocks.BRICK_BLOCK))
                    .outputs(Mods.Forestry.getItem("ash_brick"))
                    .duration(100).EUt(2).buildAndRegister();
        }
    }

    public static void blockCharcoal() {
        // Loam
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("loam"));
        MIXER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(CLAY_BALL, 4))
                .input("sand", 2)
                .inputs(Mods.Forestry.getItem("fertilizer_bio", 2))
                .outputs(Mods.Forestry.getItem("loam", 4))
                .duration(200).EUt(16).buildAndRegister();
    }
}
