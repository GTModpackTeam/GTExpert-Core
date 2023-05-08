package gtexpert.api.recipes.ingredients.draconic;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import org.jetbrains.annotations.NotNull;

public class RecipeMapDraconicUpgrade<R extends RecipeBuilder<R>> extends RecipeMap<R> {

    public RecipeMapDraconicUpgrade(@NotNull String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs, int maxFluidOutputs, @NotNull R defaultRecipeBuilder, boolean isHidden) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden);
    }

    @Override
    public int getPropertyHeightShift() {
        return super.getPropertyHeightShift() + 10;
    }

    @Override
    public int getPropertyListHeight(Recipe recipe) {
        return super.getPropertyListHeight(recipe) + 10;
    }
}
