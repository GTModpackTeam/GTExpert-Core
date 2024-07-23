package gtexpert.integration.gendustry.api.recipes;

import gregtech.api.recipes.RecipeMap;

import stanhebben.zenscript.annotations.ZenProperty;

public class GendustryRecipeMaps {

    @ZenProperty
    public static final RecipeMap<IndustrialApiaryRecipeBuilder> INDUSTRIAL_APIARY_RECIPES = new IndustrialApiaryRecipeMap<>(
            "industrial_apiary", new IndustrialApiaryRecipeBuilder(), IndustrialApiaryUI::new);
}
