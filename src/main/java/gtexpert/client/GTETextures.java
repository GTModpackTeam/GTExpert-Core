package gtexpert.client;

import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = "gtexpert", value = Side.CLIENT)
public class GTETextures {

    public static SimpleOverlayRenderer SAWMILL_CASING = new SimpleOverlayRenderer("sawmill_casing");

    public static void preInit() {
        SAWMILL_CASING = new SimpleOverlayRenderer("sawmill_casing");
    }
}
