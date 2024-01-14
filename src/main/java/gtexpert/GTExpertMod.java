package gtexpert;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;

import gregtech.GTInternalTags;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTELog;
import gtexpert.modules.GTEModules;
import gtexpert.modules.ModuleManager;

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

    private ModuleManager moduleManager;

    @Mod.EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        GTELog.logger.info("Hello World!!!!!!!!!");
        moduleManager = ModuleManager.getInstance();
        moduleManager.registerContainer(new GTEModules());
        moduleManager.setup(event.getASMHarvestedData(), Loader.instance().getConfigDir());
        moduleManager.onConstruction(event);
        GTELog.logger.info("finish construction!");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        moduleManager.onPreInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        moduleManager.onInit(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        moduleManager.onPostInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        moduleManager.onLoadComplete(event);
    }

    @Mod.EventHandler
    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        moduleManager.onServerAboutToStart(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        moduleManager.onServerStarting(event);
    }

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        moduleManager.onServerStarted(event);
    }

    @Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        moduleManager.onServerStopping(event);
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        moduleManager.onServerStopped(event);
    }

    @Mod.EventHandler
    public void respondIMC(FMLInterModComms.IMCEvent event) {
        moduleManager.processIMC(event.getMessages());
    }
}
