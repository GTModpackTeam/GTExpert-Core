package gtexpert.mixin.integration.deda;

import net.foxmcloud.draconicadditions.lib.DARecipes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = DARecipes.class, remap = false)
public class DARecipesMixin {

    /**
     * @author tier940
     * @reason Empty method as recipe cannot be deleted.
     */
    @Overwrite
    public static void addRecipes() {}
}
