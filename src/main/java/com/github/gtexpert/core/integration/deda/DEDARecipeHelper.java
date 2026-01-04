package com.github.gtexpert.core.integration.deda;

import static gregtech.api.GTValues.VA;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.integration.deda.DEDAConstants.DraconicTier;

/**
 * Helper methods for DEDA integration recipes.
 */
public final class DEDARecipeHelper {

    private DEDARecipeHelper() {}

    public static int voltage(DraconicTier tier) {
        return VA[GTEValues.dedaVoltageTier + tier.voltageOffset];
    }

    public static FluidStack cryotheum(DraconicTier tier) {
        return DEDAConstants.getCryotheum(tier);
    }

    public static FluidStack pyrotheum(DraconicTier tier) {
        return DEDAConstants.getPyrotheum(tier);
    }

    public static GTRecipeInput inputWithAnyNBT(ItemStack stack) {
        return new GTRecipeItemInput(stack).setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY);
    }
}
