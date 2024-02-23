package gtexpert.integration.avaritia.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;

import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.Mods;

public class AvaritiaMaterialsRecipe {

    public static void init() {
        // ########################################
        // Neutronium
        // ########################################
        // Dust
        ModHandler.addMirroredShapedRecipe("avaritia_neutronium_dust",
                Mods.Avaritia.getItem("resource", 1, 2),
                "D", 'D', OreDictUnifier.get(dustTiny, Materials.Neutronium));
        ModHandler.addMirroredShapedRecipe("ceu_neutronium_dust", OreDictUnifier.get(dustTiny, Materials.Neutronium),
                "D", 'D', Mods.Avaritia.getItem("resource", 1, 2));
        ModHandler.removeRecipeByOutput(Mods.Avaritia.getItem("resource", 9, 2));

        // Nugget
        ModHandler.removeRecipeByOutput(Mods.Avaritia.getItem("resource", 1, 3));
        ModHandler.removeRecipeByOutput(Mods.Avaritia.getItem("resource", 9, 3));

        // Ingot
        ModHandler.removeRecipeByName(
                Mods.Avaritia.getResource("blocks/resource/un_neutronium_block"));
        ModHandler.addMirroredShapedRecipe("avaritia_un_neutronium_ingot",
                Mods.Avaritia.getItem("resource", 1, 4),
                "I", 'I', OreDictUnifier.get(ingot, Materials.Neutronium));
        ModHandler.addMirroredShapedRecipe("ceu_un_neutronium_ingot", OreDictUnifier.get(ingot, Materials.Neutronium),
                "I", 'I', Mods.Avaritia.getItem("resource", 1, 4));

        // Block
        ModHandler
                .removeRecipeByName(Mods.Avaritia.getResource("blocks/resource/neutronium_block"));
        ModHandler.addMirroredShapedRecipe("avaritia_neutronium_block",
                Mods.Avaritia.getItem("block_resource"),
                "B", 'B', OreDictUnifier.get(block, Materials.Neutronium));
        ModHandler.addMirroredShapedRecipe("ceu_neutronium_block", OreDictUnifier.get(block, Materials.Neutronium),
                "B", 'B', Mods.Avaritia.getItem("block_resource"));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(ingot, Materials.Neutronium, 9)
                .output(block, Materials.Neutronium)
                .duration(300).EUt(2)
                .buildAndRegister();

        // ########################################
        // Crystal Matrix
        // ########################################
        // Ingot
        ModHandler.removeRecipeByName(
                Mods.Avaritia.getResource("blocks/resource/un_crystal_matrix_block"));
        // ModHandler.addMirroredShapedRecipe("avaritia_crystal_matrix_ingot",
        // Mods.Avaritia.getItem("resource", 1, 1),
        // "I", 'I', OreDictUnifier.get(ingot, GTEMaterials.Infinity));
        // ModHandler.addMirroredShapedRecipe("ceu_crystal_matrix_block", OreDictUnifier.get(ingot,
        // GTEMaterials.Infinity),
        // "I", 'I', Mods.Avaritia.getItem("resource", 1, 1));

        // Block
        ModHandler.removeRecipeByName(
                Mods.Avaritia.getResource("blocks/resource/crystal_matrix_block"));
        // ModHandler.addMirroredShapedRecipe("avaritia_crystal_matrix_ingot",
        // Mods.Avaritia.getItem("block_resource", 1, 2),
        // "B", 'B', OreDictUnifier.get(block, GTEMaterials.CrystalMatrix));
        // ModHandler.addMirroredShapedRecipe("ceu_crystal_matrix_block", OreDictUnifier.get(block,
        // GTEMaterials.CrystalMatrix),
        // "B", 'B', Mods.Avaritia.getItem("block_resource", 1, 2));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(Mods.Avaritia.getItem("resource", 9, 1))
                .outputs(Mods.Avaritia.getItem("block_resource", 1, 2))
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
                .inputs(Mods.Avaritia.getItem("block_resource", 1, 2))
                .outputs(Mods.Avaritia.getItem("resource", 9, 1))
                .duration(4000).EUt(7)
                .buildAndRegister();

        // ########################################
        // Infinity
        // ########################################
        // Ingot
        ModHandler.removeRecipeByName(
                Mods.Avaritia.getResource("blocks/resource/un_infinity_block"));
        ModHandler.addMirroredShapedRecipe("avaritia_un_infinity_ingot",
                Mods.Avaritia.getItem("resource", 1, 6),
                "I", 'I', OreDictUnifier.get(ingot, GTEMaterials.Infinity));
        ModHandler.addMirroredShapedRecipe("ceu_un_infinity_ingot", OreDictUnifier.get(ingot, GTEMaterials.Infinity),
                "I", 'I', Mods.Avaritia.getItem("resource", 1, 6));

        // Block
        ModHandler.removeRecipeByName(Mods.Avaritia.getResource("blocks/resource/infinity_block"));
        ModHandler.addMirroredShapedRecipe("avaritia_infinity_block",
                Mods.Avaritia.getItem("block_resource", 1, 1),
                "B", 'B', OreDictUnifier.get(block, GTEMaterials.Infinity));
        ModHandler.addMirroredShapedRecipe("ceu_infinity_block", OreDictUnifier.get(block, GTEMaterials.Infinity),
                "B", 'B', Mods.Avaritia.getItem("block_resource", 1, 1));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(ingot, GTEMaterials.Infinity, 9)
                .output(block, GTEMaterials.Infinity)
                .duration(300).EUt(2)
                .buildAndRegister();
    }
}
