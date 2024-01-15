package gtexpert.api.capability;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gtexpert.integration.deda.recipemaps.RecipeMapDraconicFusion;

/**
 * This recipe logic disables cache used for speeding up recipe check.
 * The reason is we do some special things inside {@link RecipeMapDraconicFusion},
 * and reusing recipe causes item with incorrect NBT to be outputted.
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
