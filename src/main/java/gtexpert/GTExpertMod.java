package gtexpert;

import gregtech.GTInternalTags;

import gtexpert.api.GTEValues;
import gtexpert.common.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nonnull;

@Mod(modid = GTEValues.MODID,
     name = GTEValues.MODNAME,
     version = Tags.VERSION,
     dependencies = GTInternalTags.DEP_VERSION_STRING + "required-after:" + GTEValues.MODID_GCYM + ";" + "after:" +
             GTEValues.MODID_GTFO + ";" + "after:" + GTEValues.MODID_GCYS + ";" + "after:" + GTEValues.MODID_GF + ";" +
             "required-after:" + GTEValues.MODID_ECO + ";" + "required-after:" + GTEValues.MODID_EIO + ";" +
             "required-after:" + GTEValues.MODID_AE + ";" + "required-after:" + GTEValues.MODID_AEA + ";" + "after:" +
             GTEValues.MODID_DE + ";" + "after:" + GTEValues.MODID_DA + ";")

public class GTExpertMod {

    @Mod.Instance
    public static GTExpertMod instance;

    @SidedProxy(modId = GTEValues.MODID,
                clientSide = "gtexpert.client.ClientProxy",
                serverSide = "gtexpert.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(@Nonnull FMLPreInitializationEvent event) {
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
