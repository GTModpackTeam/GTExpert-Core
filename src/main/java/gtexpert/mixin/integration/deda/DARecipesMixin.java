package gtexpert.mixin.integration.deda;

import net.foxmcloud.draconicadditions.lib.DARecipes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = DARecipes.class, remap = false)
public class DARecipesMixin {

    /**
     * @author tier940
     * @reason Overwrite with an empty method because the craft recipe is not deletable with cfg changes.
     */
    @Overwrite
    public static void addRecipes() {}
}
