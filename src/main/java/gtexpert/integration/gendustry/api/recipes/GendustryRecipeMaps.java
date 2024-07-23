package gtexpert.integration.gendustry.api.recipes;

import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;

import gtexpert.integration.gendustry.api.recipes.machines.RecipeMapIndustrialApiary;
import gtexpert.integration.gendustry.api.recipes.ui.IndustrialApiaryUI;

import stanhebben.zenscript.annotations.ZenProperty;

public class GendustryRecipeMaps {

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> INDUSTRIAL_APIARY_RECIPES = new RecipeMapIndustrialApiary(
            "industrial_apiary", new SimpleRecipeBuilder(), IndustrialApiaryUI::new);
}
