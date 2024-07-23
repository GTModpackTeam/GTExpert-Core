package gtexpert.integration.gendustry.api.recipes;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;

public class IndustrialApiaryLogic extends RecipeLogicEnergy {

    public IndustrialApiaryLogic(@NotNull MetaTileEntity metaTileEntity, Supplier<IEnergyContainer> energyContainer) {
        super(metaTileEntity, null, energyContainer);
    }
}
