package com.github.gtexpert.core.mixins.draconicadditions.lib;

import net.foxmcloud.draconicadditions.lib.DARecipes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Mixin to disable default Draconic Additions recipes.
 * <p>
 * Overwrites the recipe registration with an empty method because
 * the original recipes cannot be removed via config changes.
 * Custom recipes are added through the DEDA integration module instead.
 */
@Mixin(value = DARecipes.class, remap = false)
public class DARecipesMixin {

    /**
     * @author tier940
     * @reason Overwrite with an empty method because the craft recipe is not deletable with cfg changes.
     */
    @Overwrite
    public static void addRecipes() {}
}
