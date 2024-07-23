package gtexpert.integration.gendustry.api.recipes.machines;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ui.RecipeMapUIFunction;
import gregtech.core.sound.GTSoundEvents;

@ApiStatus.Internal
public class RecipeMapIndustrialApiary extends RecipeMap<SimpleRecipeBuilder> {

    public RecipeMapIndustrialApiary(@NotNull String unlocalizedName, @NotNull SimpleRecipeBuilder defaultRecipeBuilder,
                                     @NotNull RecipeMapUIFunction recipeMapUI) {
        super(unlocalizedName, defaultRecipeBuilder, recipeMapUI, 2, 9, 0, 0);
        setSound(GTSoundEvents.ARC);
    }
}
