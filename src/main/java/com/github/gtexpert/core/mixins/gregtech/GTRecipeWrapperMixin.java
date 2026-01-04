package com.github.gtexpert.core.mixins.gregtech;

import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.integration.jei.recipe.GTRecipeWrapper;

import com.github.gtexpert.core.integration.deda.recipemaps.GTEDraconicRecipeMaps;

@Mixin(value = GTRecipeWrapper.class, remap = false)
public class GTRecipeWrapperMixin {

    @Shadow
    @Final
    private RecipeMap<?> recipeMap;

    @Shadow
    @Final
    private Recipe recipe;

    /**
     * Modify yPosition calculation to use actual getInfoHeight() values instead of assuming 10px per property.
     * Only applies to DEDA recipe maps where properties have custom heights.
     * Original: recipeHeight - ((recipe.getUnhiddenPropertyCount() + defaultLines) * 10 - 3)
     * Fixed: recipeHeight - (sumOfPropertyHeights + defaultLines * 10 - 3)
     */
    @ModifyVariable(method = "drawInfo", at = @At(value = "STORE"), ordinal = 5)
    private int gteCore$fixYPosition(int original) {
        // Only apply fix for DEDA recipe maps
        if (recipeMap != GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_RECIPES &&
                recipeMap != GTEDraconicRecipeMaps.DRACONIC_FUSION_UPGRADE_RECIPES) {
            return original;
        }

        // Calculate defaultLines the same way as the original method
        var properties = recipe.getPropertyTypes();
        boolean drawTotalEU = properties.isEmpty() ||
                properties.stream().noneMatch(RecipeProperty::hideTotalEU);
        boolean drawEUt = properties.isEmpty() ||
                properties.stream().noneMatch(RecipeProperty::hideEUt);
        boolean drawDuration = properties.isEmpty() ||
                properties.stream().noneMatch(RecipeProperty::hideDuration);

        int defaultLines = 0;
        if (drawTotalEU) defaultLines++;
        if (drawEUt) defaultLines++;
        if (drawDuration) defaultLines++;

        // Reconstruct recipeHeight from original calculation:
        // original = recipeHeight - ((propertyCount + defaultLines) * 10 - 3)
        int propertyCount = recipe.getUnhiddenPropertyCount();
        int originalOffset = (propertyCount + defaultLines) * 10 - 3;
        int recipeHeight = original + originalOffset;

        // Calculate actual property height from getInfoHeight values
        int actualPropertyHeight = 0;
        for (Map.Entry<RecipeProperty<?>, Object> entry : recipe.getPropertyValues()) {
            if (!entry.getKey().isHidden()) {
                actualPropertyHeight += entry.getKey().getInfoHeight(entry.getValue());
            }
        }

        return recipeHeight - (actualPropertyHeight + defaultLines * 10 - 3);
    }
}
