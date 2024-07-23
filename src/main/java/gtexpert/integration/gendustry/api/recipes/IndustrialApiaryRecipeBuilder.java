package gtexpert.integration.gendustry.api.recipes;

import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;

public class IndustrialApiaryRecipeBuilder extends RecipeBuilder<IndustrialApiaryRecipeBuilder> {

    public IndustrialApiaryRecipeBuilder() {}

    @SuppressWarnings("unused")
    public IndustrialApiaryRecipeBuilder(Recipe recipe, RecipeMap<IndustrialApiaryRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public IndustrialApiaryRecipeBuilder(@NotNull IndustrialApiaryRecipeBuilder builder) {
        super(builder);
    }

    @Override
    public IndustrialApiaryRecipeBuilder copy() {
        return new IndustrialApiaryRecipeBuilder(this);
    }
}
