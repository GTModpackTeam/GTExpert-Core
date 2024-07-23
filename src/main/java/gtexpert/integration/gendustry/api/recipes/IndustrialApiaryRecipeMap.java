package gtexpert.integration.gendustry.api.recipes;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ui.RecipeMapUIFunction;
import gregtech.core.sound.GTSoundEvents;

@ApiStatus.Internal
public class IndustrialApiaryRecipeMap<R extends RecipeBuilder<R>> extends RecipeMap<R> {

    public IndustrialApiaryRecipeMap(@NotNull String unlocalizedName, @NotNull R defaultRecipeBuilder,
                                     @NotNull RecipeMapUIFunction recipeMapUI) {
        super(unlocalizedName, defaultRecipeBuilder, recipeMapUI, 6, 9, 0, 0);
        setSound(GTSoundEvents.ARC);
    }
}
