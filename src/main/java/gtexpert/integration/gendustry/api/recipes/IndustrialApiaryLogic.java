package gtexpert.integration.gendustry.api.recipes;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class IndustrialApiaryLogic extends RecipeLogicEnergy {

    public IndustrialApiaryLogic(@NotNull MetaTileEntity metaTileEntity, Supplier<IEnergyContainer> energyContainer) {
        super(metaTileEntity, null, energyContainer);
    }
}
