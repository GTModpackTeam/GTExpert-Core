package com.github.gtexpert.core.api.capability;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;

/**
 * This recipe logic disables cache used for speeding up recipe check.
 * The reason is that Draconic Fusion recipes (TierUp and Upgrade) do special
 * output processing based on input NBT, and reusing recipe causes items with
 * incorrect NBT to be outputted.
 * Considering draconic fusion multi will not be spammed nor OCed to 1 tick, impact to TPS should be negligible.
 */
public class MultiblockRecipeLogicNoCache extends MultiblockRecipeLogic {

    public MultiblockRecipeLogicNoCache(RecipeMapMultiblockController tileEntity) {
        super(tileEntity);
    }

    @Override
    protected boolean checkPreviousRecipe() {
        return false;
    }
}
