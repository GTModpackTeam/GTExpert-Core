package gtexpert.mixin;

import gregtech.api.recipes.RecipeMaps;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(RecipeMaps.class)
public abstract class MixinRecipeMaps {

    @ModifyArgs(method = "<clinit>",
                at = @At(value = "INVOKE",
                         target = "Lgregtech/api/recipes/RecipeMap;<init>(Ljava/lang/String;IIIILgregtech/api/recipes/RecipeBuilder;Z)V",
                         remap = false))
    private static void modifyArgsStaticBlockRecipeMap(Args args) {
        String unlocalizedName = args.get(0);
        if (unlocalizedName.equals("vacuum_freezer")) {
            args.set(4, 2);
        }
    }
}