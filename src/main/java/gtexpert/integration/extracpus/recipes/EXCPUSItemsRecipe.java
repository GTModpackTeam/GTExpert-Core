package gtexpert.integration.extracpus.recipes;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.VA;

import gregtech.api.recipes.RecipeMaps;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTEUtility;

public class EXCPUSItemsRecipe {

    public static void init() {
        // 256k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_EXCPUS, "crafting_storage_256k"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1024k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_EXCPUS, "crafting_storage_1024k"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4096k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_EXCPUS, "crafting_storage_4096k"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16384k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_EXCPUS, "crafting_storage_16384k"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
    }
}
