package gtexpert.integration.nae2.recipes;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.VA;

import gregtech.api.recipes.RecipeMaps;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTEUtility;

public class NAE2ItemsRecipe {

    public static void init() {
        // 4k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "coprocessor_4x"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_accelerator"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 36))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "coprocessor_16x"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_accelerator"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 37))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 64k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "coprocessor_64x"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_accelerator"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 38))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 256k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "storage_crafting_256k"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "material", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1024k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "storage_crafting_1024k"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "material", 1, 2))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4096k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "storage_crafting_4096k"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "material", 1, 3))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16384k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "storage_crafting_16384k"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_NAE2, "material", 1, 4))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
    }
}
