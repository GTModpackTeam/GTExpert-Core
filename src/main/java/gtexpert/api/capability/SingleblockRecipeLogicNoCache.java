package gtexpert.api.capability;

import java.util.function.Supplier;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;

public class SingleblockRecipeLogicNoCache extends RecipeLogicEnergy {

    public SingleblockRecipeLogicNoCache(MetaTileEntity tileEntity, RecipeMap<?> recipeMap,
                                         Supplier<IEnergyContainer> energyContainer) {
        super(tileEntity, recipeMap, energyContainer);
    }

    @Override
    protected boolean checkPreviousRecipe() {
        return false;
    }
}
