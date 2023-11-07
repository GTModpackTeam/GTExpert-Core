package gtexpert.client;

import gtexpert.common.CommonProxy;
import gtexpert.common.GTEBlockWireCoil;
import gtexpert.common.GTEMetaBlocks;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static gregtech.api.GregTechAPI.HEATING_COILS;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        GTETextures.preInit();

        /* Start API Block Registration */
        for (GTEBlockWireCoil.CoilType type : GTEBlockWireCoil.CoilType.values()) {
            HEATING_COILS.put(GTEMetaBlocks.BLOCK_GTE_WIRE_COIL.getState(type), type);
        }
        /* End API Block Registration */
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void onPreLoad() {
        super.onPreLoad();
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onPostLoad() {
        super.onPostLoad();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GTEMetaBlocks.registerItemModels();
    }
}
