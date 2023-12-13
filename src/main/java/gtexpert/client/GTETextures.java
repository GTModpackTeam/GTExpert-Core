package gtexpert.client;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import gregtech.client.renderer.texture.cube.*;

import gtexpert.api.GTEValues;

@Mod.EventBusSubscriber(modid = GTEValues.MODID, value = Side.CLIENT)
public class GTETextures {

    public static SimpleOverlayRenderer SAWMILL_CASING;
    public static OrientedOverlayRenderer SAWMILL_OVERLAY = new OrientedOverlayRenderer("machines/sawmill");
    public static OrientedOverlayRenderer AUTO_CHISEL_OVERLAY = new OrientedOverlayRenderer("machines/auto_chisel");
    public static OrientedOverlayRenderer VIAL_EXTRACTOR_OVERLAY = new OrientedOverlayRenderer(
            "machines/vial_extractor");
    public static OrientedOverlayRenderer SLICE_N_SPLICE_OVERLAY = new OrientedOverlayRenderer(
            "machines/slice_n_splice");
    public static OrientedOverlayRenderer SOUL_BINDER_OVERLAY = new OrientedOverlayRenderer("machines/soul_binder");
    public static OrientedOverlayRenderer SPAWNER_OVERLAY = new OrientedOverlayRenderer("machines/powered_spawner");
    public static SimpleOverlayRenderer VOID_ORE_MINER_CASING;
    public static SimpleOverlayRenderer DRACONIUM_CASING;
    public static SimpleOverlayRenderer AWAKENED_DRACONIUM_CASING;

    public static void preInit() {
        SAWMILL_CASING = new SimpleOverlayRenderer("casings/sawmill_casing");
        VOID_ORE_MINER_CASING = new SimpleOverlayRenderer("casings/void_ore_miner_casing");
        DRACONIUM_CASING = new SimpleOverlayRenderer("casings/draconium_casing");
        AWAKENED_DRACONIUM_CASING = new SimpleOverlayRenderer("casings/awakened_draconium_casing");
    }
}
