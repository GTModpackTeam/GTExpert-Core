package gtexpert.mixin;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import gregtech.api.recipes.RecipeMaps;

@Mixin(RecipeMaps.class)
public abstract class MixinRecipeMaps {

    @ModifyArgs(method = "<clinit>",
                at = @At(value = "INVOKE",
                         target = "Lgregtech/api/recipes/RecipeMap;<init>(Ljava/lang/String;IIIILgregtech/api/recipes/RecipeBuilder;Z)V",
                         remap = false))
    private static void gtexpert$modifyArgsStaticBlockRecipeMap(@NotNull Args args) {
        String unlocalizedName = args.get(0);
        if (unlocalizedName.equals("vacuum_freezer")) {
            args.set(4, 2); // maxFluidOutputs
        }
    }
}
