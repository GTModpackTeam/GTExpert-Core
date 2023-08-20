package gtexpert.loaders.recipe.ingredients;

import gregtech.api.recipes.RecipeMaps;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class GTFORecipeLoader {

    public static void init() {
        // AmmoniumChloride * 2 & SodiumBicarbonate * 6
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .input(dust, Salt, 2)
                .output(dust, AmmoniumChloride, 2)
                .output(dust, SodiumBicarbonate, 6)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(SaltWater.getFluid(1000))
                .output(dust, AmmoniumChloride, 2)
                .output(dust, SodiumBicarbonate, 6)
                .duration(200).EUt(VA[LV])
                .buildAndRegister();
    }
}
