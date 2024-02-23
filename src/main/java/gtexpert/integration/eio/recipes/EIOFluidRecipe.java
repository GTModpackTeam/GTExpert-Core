package gtexpert.integration.eio.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;

import crazypants.enderio.base.init.ModObject;

public class EIOFluidRecipe {

    public static void init() {
        // XP Juice
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Materials.Gold, 2)
                .fluidInputs(Materials.Blaze.getFluid(288))
                .fluidInputs(Materials.Glowstone.getFluid(576))
                .fluidOutputs(GTEUtility.getModFluid("xpjuice", 200))
                .duration(200).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CANNER_RECIPES.recipeBuilder()
                .input(Items.GLASS_BOTTLE, 1)
                .fluidInputs(GTEUtility.getModFluid("xpjuice", 200))
                .output(Items.EXPERIENCE_BOTTLE, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CANNER_RECIPES.recipeBuilder()
                .input(Items.EXPERIENCE_BOTTLE, 1)
                .fluidOutputs(GTEUtility.getModFluid("xpjuice", 200))
                .output(Items.GLASS_BOTTLE, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Nutrient Distillation
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input("craftNutrientDistillation", 8)
                .input(Items.SPIDER_EYE, 2)
                .fluidInputs(Materials.Water.getFluid(1000))
                .fluidOutputs(GTEUtility.getModFluid("nutrient_distillation", 1000))
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        if (Mods.GregTechFoodOption.isModLoaded()) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(Mods.GregTechFoodOption.getItem("gtfo_meta_item", 2, 117))
                    .input(Items.SPIDER_EYE, 2)
                    .fluidInputs(Materials.Water.getFluid(1000))
                    .fluidOutputs(GTEUtility.getModFluid("nutrient_distillation", 1000))
                    .duration(100).EUt(VA[LV])
                    .buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(Mods.GregTechFoodOption.getItem("gtfo_meta_item", 2, 118))
                    .input(Items.SPIDER_EYE, 2)
                    .fluidInputs(Materials.Water.getFluid(1000))
                    .fluidOutputs(GTEUtility.getModFluid("nutrient_distillation", 1000))
                    .duration(100).EUt(VA[LV])
                    .buildAndRegister();
        }

        // Dew of Void
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(ModObject.itemMaterial.getItemNN(), 2, 35)
                .input(dust, GTEMaterials.EndSteel, 2)
                .fluidInputs(GTEUtility.getModFluid("nutrient_distillation", 1000))
                .fluidOutputs(GTEUtility.getModFluid("ender_distillation", 1000))
                .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(ModObject.itemMaterial.getItemNN(), 2, 35)
                .fluidInputs(GTEUtility.getModFluid("nutrient_distillation", 1000))
                .fluidInputs(GTEMaterials.EndSteel.getFluid(288))
                .fluidOutputs(GTEUtility.getModFluid("ender_distillation", 1000))
                .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Vapor of levity
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(ModObject.itemMaterial.getItemNN(), 2, 36)
                .input(ModObject.itemMaterial.getItemNN(), 2, 34)
                .fluidInputs(GTEUtility.getModFluid("ender_distillation", 1000))
                .fluidOutputs(GTEUtility.getModFluid("vapor_of_levity", 1000))
                .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Hootch
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input("craftHootch", 4)
                .input(Items.SUGAR, 1)
                .fluidInputs(Materials.Water.getFluid(2000))
                .fluidOutputs(GTEUtility.getModFluid("hootch", 500))
                .duration(200).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();
        if (Mods.GregTechFoodOption.isModLoaded()) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(Mods.GregTechFoodOption.getItem("gtfo_meta_item", 1, 117))
                    .input(Items.SUGAR, 1)
                    .fluidInputs(Materials.Water.getFluid(2000))
                    .fluidOutputs(GTEUtility.getModFluid("hootch", 500))
                    .duration(200).EUt(VA[GTEValues.eioVoltageTier])
                    .buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(Mods.GregTechFoodOption.getItem("gtfo_meta_item", 1, 118))
                    .input(Items.SUGAR, 1)
                    .fluidInputs(Materials.Water.getFluid(2000))
                    .fluidOutputs(GTEUtility.getModFluid("hootch", 500))
                    .duration(200).EUt(VA[GTEValues.eioVoltageTier])
                    .buildAndRegister();
        }

        // Fire Water
        RecipeMaps.FERMENTING_RECIPES.recipeBuilder()
                .input(dust, Materials.Redstone, 2)
                .fluidInputs(Materials.Blaze.getFluid(1152))
                .fluidOutputs(GTEUtility.getModFluid("fire_water", 1000))
                .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Sunshine
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Materials.Glowstone, 2)
                .input(Blocks.DOUBLE_PLANT, 1)
                .fluidInputs(GTEUtility.getModFluid("fire_water", 250))
                .fluidOutputs(GTEUtility.getModFluid("liquid_sunshine", 250))
                .duration(56).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Cloud Seed
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Materials.Silver, 2)
                .fluidInputs(Materials.Water.getFluid(3500))
                .fluidOutputs(GTEUtility.getModFluid("cloud_seed", 3500))
                .duration(300).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Concentrated Cloud
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Materials.Electrum, 2)
                .input(Items.SNOWBALL, 1)
                .fluidInputs(GTEUtility.getModFluid("cloud_seed", 1000))
                .fluidOutputs(GTEUtility.getModFluid("cloud_seed_concentrated", 500))
                .duration(200).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();
    }
}
