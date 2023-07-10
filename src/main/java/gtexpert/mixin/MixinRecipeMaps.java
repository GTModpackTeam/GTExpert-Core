package gtexpert.mixin;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RecipeMaps.class)
public abstract class MixinRecipeMaps {

    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "gregtech/api/recipes/RecipeMap", remap = false))
    private static <R extends RecipeBuilder<R>> RecipeMap<?> redirectStaticBlock(String unlocalizedName, int maxInputs,
                                                                                 int maxOutputs, int maxFluidInputs,
                                                                                 int maxFluidOutputs,
                                                                                 R defaultRecipeBuilder,
                                                                                 boolean isHidden) {
        if (unlocalizedName.equals("vacuum_freezer")) {
            return new RecipeMap<>(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, 2, defaultRecipeBuilder,
                    isHidden);
        }
        return new RecipeMap<>(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs,
                defaultRecipeBuilder, isHidden);
    }
}
