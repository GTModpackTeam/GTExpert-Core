package com.github.gtexpert.core.integration.deda.recipes;

import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.ore.OrePrefix.dust;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.unification.material.GTEMaterials;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;

public class DraconicFluidRecipe {

    public static void init() {
        // Cryotheum
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 1000))
                .fluidOutputs(GTEMaterials.Cryotheum.getFluid(1000))
                .duration(400).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .input(dust, Materials.Electrotine, 1)
                .fluidInputs(Materials.Ice.getFluid(4000))
                .fluidInputs(Materials.EnderPearl.getFluid(144))
                .fluidOutputs(GTEMaterials.Cryotheum.getFluid(200))
                .duration(150).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();

        // Pyrotheum
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.Sulfur, 1)
                .fluidInputs(Materials.Argon.getFluid(200))
                .fluidInputs(Materials.Blaze.getFluid(2304))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 1000))
                .blastFurnaceTemp(7200)
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.Sulfur, 1)
                .fluidInputs(Materials.Blaze.getFluid(2304))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 1000))
                .blastFurnaceTemp(7200)
                .duration(1200).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();
    }
}
