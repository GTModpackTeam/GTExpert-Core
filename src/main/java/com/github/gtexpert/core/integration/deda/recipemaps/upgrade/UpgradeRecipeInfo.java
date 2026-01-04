package com.github.gtexpert.core.integration.deda.recipemaps.upgrade;

import com.brandon3055.draconicevolution.api.itemupgrade.FusionUpgradeRecipe;
import com.github.bsideup.jabel.Desugar;

/**
 * Wrapper for upgrade recipe info including the original Draconic Evolution recipe
 * and machine tier requirement.
 */
@Desugar
public record UpgradeRecipeInfo(FusionUpgradeRecipe recipe, boolean requiresAwakenedCrafter) {}
