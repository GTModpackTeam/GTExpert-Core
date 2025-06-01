package com.github.gtexpert.core.mixins.gcym;

import org.spongepowered.asm.mixin.Mixin;

import gregicality.multiblocks.common.metatileentities.multiblock.standard.MetaTileEntityMegaBlastFurnace;

import com.github.gtexpert.core.api.capability.impl.ITiered;

@Mixin(value = MetaTileEntityMegaBlastFurnace.class, remap = false)
public abstract class MetaTileEntityMegaBlastFurnaceMixin implements ITiered {

    @Override
    public boolean isTiered() {
        return false;
    }
}
