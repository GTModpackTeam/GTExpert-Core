package gtexpert.api.recipes.draconicupgrade;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import org.jetbrains.annotations.NotNull;

public class RecipeMapOnRecipeCompileHook<R extends RecipeBuilder<R>> extends RecipeMap<R> {

    private RecipeMapDraconicFusion recipeMapToHook;

    public RecipeMapOnRecipeCompileHook(@NotNull String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs, int maxFluidOutputs, @NotNull R defaultRecipeBuilder, boolean isHidden) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden);
    }

    public void setRecipeMapToHook(RecipeMapDraconicFusion recipeMapToHook) {
        this.recipeMapToHook = recipeMapToHook;
    }

    @Override
    public void compileRecipe(Recipe recipe) {
        super.compileRecipe(recipe);
        recipeMapToHook.hookAddRecipe(recipe);
    }
}
