package gtexpert.core;

import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import gregtech.loaders.recipe.RecyclingRecipes;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.modules.IGTEModule;
import gtexpert.common.CommonProxy;
import gtexpert.common.metatileentities.MetaTileEntitiesManager;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_CORE,
           containerID = GTEValues.MODID,
           name = "GTExpert Core",
           description = "Core GTExpert content.",
           coreModule = true)
public class GTECoreModule implements IGTEModule {

    public static final Logger logger = LogManager.getLogger("GTExpert Core");

    @SidedProxy(modId = GTEValues.MODID,
                clientSide = "gtexpert.client.ClientProxy",
                serverSide = "gtexpert.common.CommonProxy")
    public static CommonProxy proxy;

    @Override
    public @NotNull Logger getLogger() {
        return logger;
    }

    @Override
    public void construction(FMLConstructionEvent event) {
        // これのせいでRecipe Managerのconstructionが遅れる
        proxy.onConstruction(event);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

        MetaTileEntitiesManager.init();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);

        RecyclingRecipes.init();
    }
}
