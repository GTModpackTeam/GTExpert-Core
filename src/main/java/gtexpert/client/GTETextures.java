package gtexpert.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleSidedCubeRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.client.renderer.texture.cube.OrientedOverlayRenderer.OverlayFace.*;

@Mod.EventBusSubscriber(modid = "gtexpert", value = Side.CLIENT)
public class GTETextures {

    public static SimpleOverlayRenderer SAWMILL_CASING;
    public static OrientedOverlayRenderer SAWMILL_OVERLAY;
    public static OrientedOverlayRenderer VIAL_EXTRACTOR_OVERLAY = new OrientedOverlayRenderer("vial_extractor", FRONT, SIDE, TOP);
    public static OrientedOverlayRenderer EXTREME_MIXER_OVERLAY = new OrientedOverlayRenderer("extreme_mixer", FRONT, SIDE, TOP);
    public static SimpleOverlayRenderer VOID_ORE_MINER_CASING;

    public static void preInit() {
        SAWMILL_CASING = new SimpleOverlayRenderer("sawmill_casing");
        SAWMILL_OVERLAY = new OrientedOverlayRenderer("sawmill", FRONT);
        VOID_ORE_MINER_CASING = new SimpleOverlayRenderer("void_ore_miner_casing");
    }
}
