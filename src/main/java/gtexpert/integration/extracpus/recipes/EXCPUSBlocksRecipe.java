package gtexpert.integration.extracpus.recipes;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.VA;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;

import gtexpert.api.util.Mods;
import gtexpert.integration.extracpus.EXCPUSConfigHolder;

public class EXCPUSBlocksRecipe {

    public static void init() {
        // 256k Crafting Storage
        ModHandler.removeRecipeByName(Mods.ExtraCPUs.getResource("crafting_storage_256k"));

        // 1024k Crafting Storage
        ModHandler.removeRecipeByName(Mods.ExtraCPUs.getResource("crafting_storage_1024k"));

        // 4096k Crafting Storage
        ModHandler.removeRecipeByName(Mods.ExtraCPUs.getResource("crafting_storage_4096k"));

        // 16384k Crafting Storage
        ModHandler.removeRecipeByName(Mods.ExtraCPUs.getResource("crafting_storage_16384k"));

        if (!EXCPUSConfigHolder.migrateToNAE2) {
            // 256k Crafting Storage
            ModHandler.removeRecipeByName(Mods.ExtraCPUs.getResource("crafting_storage_256k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AEAdditions.getItem("storage.component"))
                    .outputs(Mods.ExtraCPUs.getItem("crafting_storage_256k"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 1024k Crafting Storage
            ModHandler.removeRecipeByName(Mods.ExtraCPUs.getResource("crafting_storage_1024k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AEAdditions.getItem("storage.component", 1, 1))
                    .outputs(Mods.ExtraCPUs.getItem("crafting_storage_1024k"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 4096k Crafting Storage
            ModHandler.removeRecipeByName(Mods.ExtraCPUs.getResource("crafting_storage_4096k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AEAdditions.getItem("storage.component", 1, 2))
                    .outputs(Mods.ExtraCPUs.getItem("crafting_storage_4096k"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();

            // 16384k Crafting Storage
            ModHandler.removeRecipeByName(Mods.ExtraCPUs.getResource("crafting_storage_16384k"));
            RecipeMaps.PACKER_RECIPES.recipeBuilder()
                    .inputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                    .inputs(Mods.AEAdditions.getItem("storage.component", 1, 3))
                    .outputs(Mods.ExtraCPUs.getItem("crafting_storage_16384k"))
                    .duration(10).EUt(VA[ULV])
                    .buildAndRegister();
        } else {
            // 256k Crafting Storage
            ModHandler.addShapelessRecipe("crafting_storage_256k", Mods.NeevesAE2.getItem("storage_crafting_256k"),
                    Mods.ExtraCPUs.getItem("crafting_storage_256k"));

            // 1024k Crafting Storage
            ModHandler.addShapelessRecipe("crafting_storage_1024k", Mods.NeevesAE2.getItem("storage_crafting_1024k"),
                    Mods.ExtraCPUs.getItem("crafting_storage_1024k"));

            // 4096k Crafting Storage
            ModHandler.addShapelessRecipe("crafting_storage_4096k", Mods.NeevesAE2.getItem("storage_crafting_4096k"),
                    Mods.ExtraCPUs.getItem("crafting_storage_4096k"));

            // 16384k Crafting Storage
            ModHandler.addShapelessRecipe("crafting_storage_16384k", Mods.NeevesAE2.getItem("storage_crafting_16384k"),
                    Mods.ExtraCPUs.getItem("crafting_storage_16384k"));
        }
    }
}
