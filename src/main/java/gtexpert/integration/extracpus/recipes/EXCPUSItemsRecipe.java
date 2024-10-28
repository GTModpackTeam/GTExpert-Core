package gtexpert.integration.extracpus.recipes;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.VA;

import gregtech.api.recipes.RecipeMaps;

import gtexpert.api.util.Mods;

public class EXCPUSItemsRecipe {

    public static void init() {
        // 256k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.ExtraCPUs.getItem("crafting_storage_256k"))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .outputs(Mods.AEAdditions.getItem("storage.component"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1024k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.ExtraCPUs.getItem("crafting_storage_1024k"))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .outputs(Mods.AEAdditions.getItem("storage.component", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4096k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.ExtraCPUs.getItem("crafting_storage_4096k"))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .outputs(Mods.AEAdditions.getItem("storage.component", 1, 2))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16384k ME Storage Component
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(Mods.ExtraCPUs.getItem("crafting_storage_16384k"))
                .outputs(Mods.AppliedEnergistics2.getItem("crafting_unit"))
                .outputs(Mods.AEAdditions.getItem("storage.component", 1, 3))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
    }
}
