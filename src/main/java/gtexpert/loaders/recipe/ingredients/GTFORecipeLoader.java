package gtexpert.loaders.recipe.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;

public class GTFORecipeLoader {

    public static void init() {
        // AmmoniumChloride * 2 & SodiumBicarbonate * 6
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(Materials.CarbonDioxide.getFluid(1000))
                .fluidInputs(Materials.Ammonia.getFluid(1000))
                .fluidInputs(Materials.Water.getFluid(1000))
                .input(dust, Materials.Salt, 2)
                .output(dust, Materials.AmmoniumChloride, 2)
                .output(dust, Materials.SodiumBicarbonate, 6)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(Materials.CarbonDioxide.getFluid(1000))
                .fluidInputs(Materials.Ammonia.getFluid(1000))
                .fluidInputs(Materials.SaltWater.getFluid(1000))
                .output(dust, Materials.AmmoniumChloride, 2)
                .output(dust, Materials.SodiumBicarbonate, 6)
                .duration(200).EUt(VA[LV])
                .buildAndRegister();
    }
}
