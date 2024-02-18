package gtexpert.integration.additions.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.integration.ae.AEUtil.tierMaterials;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTEUtility;

public class AEAItemsRecipe {

    public static void init() {
        // 256k Storage Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/256k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/256k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.physical"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.physical"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1024k Storage Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/1024k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/1024k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 1))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 1))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4096k Storage Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/4096k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/4096k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 2))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 2))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 2))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 16384k Storage Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/owncasing/16384k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/item/extracasing/16384k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 3))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.physical", 1, 3))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 3))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 256k Fluid Storage Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/owncasing/256k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/extracasing/256k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 4))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.fluid"))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.fluid"))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 4))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 1024k Fluid Storage Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/owncasing/1024k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/extracasing/1024k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 5))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 1))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 5))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // 4096k Fluid Storage Cell
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/owncasing/4096k"));
        ModHandler.removeRecipeByName(
                new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/extracasing/4096k"));
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 6))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 2))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.PACKER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.fluid", 1, 2))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.component", 1, 6))
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();

        // Storage Housing
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/e2acasing"));

        // Fluid Storage Housing
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/case/fluid"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/fluid/a2ecasing"));
        ModHandler.addShapedRecipe(true, "empty_fluid_storage_cell_1",
                GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1),
                "DPS", "P P", "SPH",
                'D', "craftingToolScrewdriver",
                'P', OreDictUnifier.get(plate, tierMaterials[GTEValues.ae2VoltageTier]),
                'S', OreDictUnifier.get(screw, tierMaterials[GTEValues.ae2VoltageTier]),
                'H', "craftingToolHardHammer");
        ModHandler.addShapedRecipe("empty_fluid_storage_cell_2",
                GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1),
                "HPS", "P P", "SPD",
                'D', "craftingToolScrewdriver",
                'P', OreDictUnifier.get(plate, tierMaterials[GTEValues.ae2VoltageTier]),
                'S', OreDictUnifier.get(screw, tierMaterials[GTEValues.ae2VoltageTier]),
                'H', "craftingToolHardHammer");
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(plate, tierMaterials[GTEValues.ae2VoltageTier], 2)
                .input(screw, tierMaterials[GTEValues.ae2VoltageTier], 2)
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing", 1, 1))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Advanced Storage Housing
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AEA, "storagecells/case/item"));
        ModHandler.addShapedRecipe(true, "empty_advanced_storage_cell_1",
                GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"),
                "DPS", "P P", "SPH",
                'D', "craftingToolScrewdriver",
                'P', OreDictUnifier.get(plate, tierMaterials[GTEValues.ae2VoltageTier + 2]),
                'S', OreDictUnifier.get(screw, tierMaterials[GTEValues.ae2VoltageTier + 2]),
                'H', "craftingToolHardHammer");
        ModHandler.addShapedRecipe("empty_advanced_storage_cell_2",
                GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"),
                "HPS", "P P", "SPD",
                'D', "craftingToolScrewdriver",
                'P', OreDictUnifier.get(plate, tierMaterials[GTEValues.ae2VoltageTier + 2]),
                'S', OreDictUnifier.get(screw, tierMaterials[GTEValues.ae2VoltageTier + 2]),
                'H', "craftingToolHardHammer");
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(plate, tierMaterials[GTEValues.ae2VoltageTier + 2], 2)
                .input(screw, tierMaterials[GTEValues.ae2VoltageTier + 2], 2)
                .outputs(GTEUtility.getModItem(GTEValues.MODID_AEA, "storage.casing"))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .withRecycling()
                .buildAndRegister();
    }
}
