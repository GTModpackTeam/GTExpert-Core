package com.github.gtexpert.core.mixins.gcym;

import com.github.gtexpert.core.api.capability.impl.ITiered;
import gregicality.multiblocks.common.metatileentities.multiblock.standard.MetaTileEntityMegaVacuumFreezer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = MetaTileEntityMegaVacuumFreezer.class, remap = false)
public class MetaTileEntityMegaVacuumFreezerMixin implements ITiered {

    @Override
    public boolean isTiered() {
        return false;
    }
}
