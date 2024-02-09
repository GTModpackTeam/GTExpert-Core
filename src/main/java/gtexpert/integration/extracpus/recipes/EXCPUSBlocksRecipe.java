package gtexpert.integration.extracpus.recipes;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.VA;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTEUtility;

public class EXCPUSBlocksRecipe {

    public static void init() {
        // 256k Crafting Storage
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPUS, "crafting_storage_256k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_EXCPUS, "crafting_storage_256k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1024k Crafting Storage
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPUS, "crafting_storage_1024k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_EXCPUS, "crafting_storage_1024k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4096k Crafting Storage
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPUS, "crafting_storage_4096k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_EXCPUS, "crafting_storage_4096k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16384k Crafting Storage
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_EXCPUS, "crafting_storage_16384k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AE, "crafting_unit"))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_EXCPUS, "crafting_storage_16384k"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
    }
}
