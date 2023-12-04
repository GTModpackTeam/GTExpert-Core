package gtexpert;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import gregtech.GTInternalTags;

import gtexpert.api.GTEValues;
import gtexpert.common.CommonProxy;

@Mod(modid = GTEValues.MODID,
     name = GTEValues.MODNAME,
     version = Tags.VERSION,
     dependencies = "required-after:mixinbooter;" +
             GTInternalTags.DEP_VERSION_STRING + "required-after:" + GTEValues.MODID_GCYM + ";" +
             "after:" + GTEValues.MODID_GTFO + ";" + "after:" + GTEValues.MODID_AE + ";" +
             "after:" + GTEValues.MODID_AEA + ";" + "after:" + GTEValues.MODID_AEFC + ";" +
             "after:" + GTEValues.MODID_EXCPU + ";" + "after:" + GTEValues.MODID_ECO + ";" +
             "after:" + GTEValues.MODID_EIO + ";" + "after:" + GTEValues.MODID_EIOE + ";" +
             "after:" + GTEValues.MODID_EIOM + ";" + "after:" + GTEValues.MODID_EIOC + ";" +
             "after:" + GTEValues.MODID_EIOCA + ";" + "after:" + GTEValues.MODID_DE + ";" +
             "after:" + GTEValues.MODID_DA + ";" + "after:" + GTEValues.MODID_CHISEL + ";" +
             "after:" + GTEValues.MODID_AVARITIA + ";" + "after:" + GTEValues.MODID_FFM + ";")

public class GTExpertMod {

    @SidedProxy(modId = GTEValues.MODID,
                clientSide = "gtexpert.client.ClientProxy",
                serverSide = "gtexpert.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {}

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {}

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event) {}

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {}

    @Mod.EventHandler
    public void respondIMC(FMLInterModComms.IMCEvent event) {}
}
