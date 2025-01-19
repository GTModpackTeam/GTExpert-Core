package com.github.gtexpert.core.client;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import com.github.gtexpert.core.api.GTEValues;

import gregtech.client.renderer.texture.cube.*;

@Mod.EventBusSubscriber(modid = GTEValues.MODID, value = Side.CLIENT)
public class GTETextures {

    // Core
    public static SimpleOverlayRenderer VOID_ORE_MINER_CASING;

    // Ender IO
    public static OrientedOverlayRenderer AUTO_CHISEL_OVERLAY = new OrientedOverlayRenderer("machines/auto_chisel");
    public static OrientedOverlayRenderer VIAL_EXTRACTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/vial_extractor");
    public static OrientedOverlayRenderer SLICE_N_SPLICE_OVERLAY = new OrientedOverlayRenderer(
            "machines/slice_n_splice");
    public static OrientedOverlayRenderer SOUL_BINDER_OVERLAY = new OrientedOverlayRenderer("machines/soul_binder");
    public static OrientedOverlayRenderer SPAWNER_OVERLAY = new OrientedOverlayRenderer("machines/powered_spawner");

    // Draconic Evolution
    public static SimpleOverlayRenderer DRACONIUM_CASING;
    public static SimpleOverlayRenderer AWAKENED_DRACONIUM_CASING;

    // Gendustry
    public static OrientedOverlayRenderer INDUSTRIAL_APIARY_OVERLAY = new OrientedOverlayRenderer(
            "machines/industrial_apiary");

    public static void preInit() {
        // Core
        VOID_ORE_MINER_CASING = new SimpleOverlayRenderer("casings/void_ore_miner_casing");

        // Draconic Evolution
        DRACONIUM_CASING = new SimpleOverlayRenderer("casings/draconium_casing");
        AWAKENED_DRACONIUM_CASING = new SimpleOverlayRenderer("casings/awakened_draconium_casing");
    }
}
