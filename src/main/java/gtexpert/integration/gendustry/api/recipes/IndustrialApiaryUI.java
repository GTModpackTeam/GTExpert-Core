package gtexpert.integration.gendustry.api.recipes;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ui.RecipeMapUI;

@ApiStatus.Internal
public class IndustrialApiaryUI<R extends RecipeMap<?>> extends RecipeMapUI<R> {

    /**
     * @param recipeMap the recipemap corresponding to this ui
     */
    public IndustrialApiaryUI(@NotNull R recipeMap) {
        super(recipeMap, false, false, false, false);
    }
}
