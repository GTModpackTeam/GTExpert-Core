package com.github.gtexpert.core.integration.deda.recipemaps;

import com.brandon3055.draconicevolution.api.fusioncrafting.IFusionRecipe;
import com.github.bsideup.jabel.Desugar;

/**
 * Wrapper for draconic recipe info including the original Draconic Evolution recipe
 * and machine tier requirement. Used by both TierUp and Upgrade recipes.
 */
@Desugar
public record DraconicRecipeInfo(IFusionRecipe recipe, boolean requiresAwakenedCrafter) {}
