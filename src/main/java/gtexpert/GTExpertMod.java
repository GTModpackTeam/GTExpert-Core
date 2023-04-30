package gtexpert;

import gregtech.api.GTValues;
import gtexpert.api.GTEValues;
import gtexpert.api.util.GTELog;
import gtexpert.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.jetbrains.annotations.NotNull;

@Mod(modid = GTEValues.MODID,
        name = GTEValues.MODNAME,
        version = GTEVersion.VERSION,
        dependencies = GTValues.MOD_VERSION_DEP)
public class GTExpertMod {
    @Mod.Instance
    public static GTExpertMod instance;

    @SidedProxy(modId = GTEValues.MODID, clientSide = "gtexpert.client.ClientProxy", serverSide = "gtexpert.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(@NotNull FMLPreInitializationEvent event) {
        GTELog.init(event.getModLog());

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
