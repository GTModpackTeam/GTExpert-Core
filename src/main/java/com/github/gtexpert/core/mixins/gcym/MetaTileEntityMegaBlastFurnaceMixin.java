package com.github.gtexpert.core.mixins.gcym;

import org.spongepowered.asm.mixin.Mixin;

import gregicality.multiblocks.common.metatileentities.multiblock.standard.MetaTileEntityMegaBlastFurnace;

import com.github.gtexpert.core.api.capability.impl.ITiered;

/**
 * Mixin to implement {@link ITiered} interface on the Mega Blast Furnace.
 * <p>
 * Marks this multiblock as non-tiered to exclude it from tier-based
 * recipe filtering or other tier-dependent logic.
 */
@Mixin(value = MetaTileEntityMegaBlastFurnace.class, remap = false)
public abstract class MetaTileEntityMegaBlastFurnaceMixin implements ITiered {

    @Override
    public boolean isTiered() {
        return false;
    }
}
