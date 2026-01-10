package com.github.gtexpert.core.mixins.gcym;

import org.spongepowered.asm.mixin.Mixin;

import gregicality.multiblocks.common.metatileentities.multiblock.standard.MetaTileEntityMegaVacuumFreezer;

import com.github.gtexpert.core.api.capability.impl.ITiered;

/**
 * Mixin to implement {@link ITiered} interface on the Mega Vacuum Freezer.
 * <p>
 * Marks this multiblock as non-tiered to exclude it from tier-based
 * recipe filtering or other tier-dependent logic.
 */
@Mixin(value = MetaTileEntityMegaVacuumFreezer.class, remap = false)
public class MetaTileEntityMegaVacuumFreezerMixin implements ITiered {

    @Override
    public boolean isTiered() {
        return false;
    }
}
