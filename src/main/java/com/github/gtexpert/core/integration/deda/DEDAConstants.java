package com.github.gtexpert.core.integration.deda;

import net.minecraftforge.fluids.FluidStack;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import com.github.gtexpert.core.api.unification.material.GTEMaterials;

/**
 * Constants and helpers for DEDA (Draconic Evolution / Draconic Additions) integration.
 */
public final class DEDAConstants {

    private DEDAConstants() {}

    // Alloy Blast Furnace constants
    public static final int ABF_PYROTHEUM_AMOUNT = 200;
    public static final double ABF_DURATION_MULTIPLIER = 0.5;

    // Recipe durations (ticks)
    public static final int DURATION_WYVERN = 200;
    public static final int DURATION_DRACONIC = 400;
    public static final int DURATION_CHAOTIC = 600;

    public static FluidStack getCryotheum(DraconicTier tier) {
        return switch (tier) {
            case WYVERN -> GTEMaterials.Cryotheum.getFluid(16000);
            case DRACONIC -> GTEMaterials.Cryotheum.getFluid(32000);
            case CHAOTIC -> GTEMaterials.Cryotheum.getFluid(48000);
        };
    }

    public static FluidStack getPyrotheum(DraconicTier tier) {
        return switch (tier) {
            case WYVERN -> GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000);
            case DRACONIC -> GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000);
            case CHAOTIC -> GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 12000);
        };
    }

    /**
     * Draconic Evolution tool/armor tiers.
     */
    public enum DraconicTier {

        WYVERN(0),
        DRACONIC(1),
        CHAOTIC(2);

        public final int voltageOffset;

        DraconicTier(int voltageOffset) {
            this.voltageOffset = voltageOffset;
        }
    }
}
