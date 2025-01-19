package com.github.gtexpert.core.integration.gendustry.recipes.machines;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;

public class RecipeMapIndustrialApiary<R extends RecipeBuilder<R>> extends RecipeMap<R> {

    public RecipeMapIndustrialApiary(String unlocalizedName, int maxInputs, int maxOutputs,
                                     int maxFluidInputs, int maxFluidOutputs, R defaultRecipe, boolean isHidden) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipe, isHidden);
    }
}
