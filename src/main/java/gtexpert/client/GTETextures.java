package gtexpert.client;


import gregtech.client.renderer.texture.cube.*;
import gtexpert.api.GTEValues;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = GTEValues.MODID, value = Side.CLIENT)
public class GTETextures {
    public static SimpleOverlayRenderer SAWMILL_CASING;
    public static OrientedOverlayRenderer SAWMILL_OVERLAY = new OrientedOverlayRenderer("sawmill");
    public static OrientedOverlayRenderer EXTREME_MIXER_OVERLAY = new OrientedOverlayRenderer("extreme_mixer");
    public static OrientedOverlayRenderer VIAL_EXTRACTOR_OVERLAY = new OrientedOverlayRenderer("vial_extractor");
    public static OrientedOverlayRenderer SLICE_N_SPLICE_OVERLAY = new OrientedOverlayRenderer("slice_n_splice");
    public static OrientedOverlayRenderer SOUL_BINDER_OVERLAY = new OrientedOverlayRenderer("soul_binder");
    public static OrientedOverlayRenderer SPAWNER_OVERLAY = new OrientedOverlayRenderer("electric_spawner");
    public static SimpleOverlayRenderer VOID_ORE_MINER_CASING;
    public static SimpleOverlayRenderer DRACONIUM_CASING;
    public static SimpleOverlayRenderer AWAKENED_DRACONIUM_CASING;

    public static void preInit() {
        SAWMILL_CASING = new SimpleOverlayRenderer("sawmill_casing");
        VOID_ORE_MINER_CASING = new SimpleOverlayRenderer("void_ore_miner_casing");
        DRACONIUM_CASING = new SimpleOverlayRenderer("draconium_casing");
        AWAKENED_DRACONIUM_CASING = new SimpleOverlayRenderer("awakened_draconium_casing");
    }
}
