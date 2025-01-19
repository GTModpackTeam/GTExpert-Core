package com.github.gtexpert.core.integration.gendustry.metatileentities;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;

public class IndustrialApiaryLogic extends RecipeLogicEnergy {

    public IndustrialApiaryLogic(@NotNull MetaTileEntity metaTileEntity, RecipeMap<?> recipeMap,
                                 Supplier<IEnergyContainer> energyContainer) {
        super(metaTileEntity, recipeMap, energyContainer);
    }
}
