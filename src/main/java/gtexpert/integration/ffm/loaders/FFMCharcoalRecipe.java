package gtexpert.integration.ffm.loaders;

import forestry.modules.ForestryModuleUids;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.ConfigHolder;
import gtexpert.api.util.Mods;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static gregtech.api.recipes.RecipeMaps.*;
import static net.minecraft.init.Items.BRICK;
import static net.minecraft.init.Items.CLAY_BALL;

public class FFMCharcoalRecipe {

    public static void init() {
        if (forestry.modules.ModuleHelper.isEnabled(ForestryModuleUids.CHARCOAL)) {
            register();
        }

    }

    public static void register() {
        // Log x4 -> Wood Pile
        if (ConfigHolder.recipes.harderCharcoalRecipe) {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("wood_pile"));
            COMPRESSOR_RECIPES.recipeBuilder()
                    .input("logWood", 4)
                    .outputs(Mods.Forestry.getItem("wood_pile"))
                    .duration(300).EUt(2).buildAndRegister();
        }

        // Loam
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("loam"));
        MIXER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(CLAY_BALL, 4))
                .input("sand", 2)
                .inputs(Mods.Forestry.getItem("fertilizer_bio", 2))
                .outputs(Mods.Forestry.getItem("loam", 4))
                .duration(200).EUt(16).buildAndRegister();

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

        // Block of Charcoal
        if (ConfigHolder.recipes.disableManualCompression) {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("charcoal_block"));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("charcoal"));
        }
    }
}

