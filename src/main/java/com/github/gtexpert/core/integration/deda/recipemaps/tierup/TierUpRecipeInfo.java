package com.github.gtexpert.core.integration.deda.recipemaps.tierup;

import com.brandon3055.draconicevolution.lib.ToolUpgradeRecipe;
import com.github.bsideup.jabel.Desugar;

/**
 * Wrapper for tier-up recipe info including the original Draconic Evolution recipe
 * and machine tier requirement.
 */
@Desugar
public record TierUpRecipeInfo(ToolUpgradeRecipe recipe, boolean requiresAwakenedCrafter) {}
