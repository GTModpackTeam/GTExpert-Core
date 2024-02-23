package gtexpert.integration.nae2.recipes;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.VA;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;

import gtexpert.api.util.Mods;

public class NAE2BlocksRecipe {

    public static void init() {
        // 4x Crafting Co-Processing Unit
        ModHandler.removeRecipeByName(Mods.NeevesAE2.getResource("block/crafting/4x_coprocessor"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator"))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 36))
                .outputs(Mods.NeevesAE2.getItem("coprocessor_4x"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16x Crafting Co-Processing Unit
        ModHandler.removeRecipeByName(Mods.NeevesAE2.getResource("block/crafting/16x_coprocessor"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator"))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 37))
                .outputs(Mods.NeevesAE2.getItem("coprocessor_16x"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64x Crafting Co-Processing Unit
        ModHandler.removeRecipeByName(Mods.NeevesAE2.getResource("block/crafting/64x_coprocessor"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_accelerator"))
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 38))
                .outputs(Mods.NeevesAE2.getItem("coprocessor_64x"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 256k Crafting Storage
        ModHandler.removeRecipeByName(Mods.NeevesAE2.getResource("block/crafting/storage_256k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .inputs(Mods.NeevesAE2.getItem("material", 1, 1))
                .outputs(Mods.NeevesAE2.getItem("storage_crafting_256k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1024k Crafting Storage
        ModHandler.removeRecipeByName(Mods.NeevesAE2.getResource("block/crafting/storage_1024k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .inputs(Mods.NeevesAE2.getItem("material", 1, 2))
                .outputs(Mods.NeevesAE2.getItem("storage_crafting_1024k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4096k Crafting Storage
        ModHandler.removeRecipeByName(Mods.NeevesAE2.getResource("block/crafting/storage_4096k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .inputs(Mods.NeevesAE2.getItem("material", 1, 3))
                .outputs(Mods.NeevesAE2.getItem("storage_crafting_4096k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16384k Crafting Storage
        ModHandler.removeRecipeByName(Mods.NeevesAE2.getResource("block/crafting/storage_16384k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .inputs(Mods.NeevesAE2.getItem("material", 1, 4))
                .outputs(Mods.NeevesAE2.getItem("storage_crafting_16384k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
    }
}
