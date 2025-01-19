package com.github.gtexpert.core.integration.chisel.metatileentities;

import java.util.function.Function;

import net.minecraft.util.ResourceLocation;

import com.github.gtexpert.core.api.capability.SingleblockRecipeLogicNoCache;
import com.github.gtexpert.core.common.metatileentities.GTESimpleMachineMetaTileEntity;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;

public class MetaTileEntityAutoChisel extends GTESimpleMachineMetaTileEntity {

    public MetaTileEntityAutoChisel(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer,
                                    int tier, boolean hasFrontFacing, Function<Integer, Integer> tankScalingFunction) {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction);
        new SingleblockRecipeLogicNoCache(this, recipeMap, () -> this.energyContainer);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityAutoChisel(metaTileEntityId, workable.getRecipeMap(), renderer, getTier(),
                hasFrontFacing(), getTankScalingFunction());
    }
}
