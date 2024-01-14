package gtexpert.integration;

import java.util.Collections;
import java.util.List;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.modules.BaseGTEModule;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_INTEGRATION,
           containerID = GTEValues.MODID,
           name = "GTExpert Mod Integration",
           description = "General GTExpert Integration Module. Disabling this disables all integration modules.")
public class GTEIntegrationModule extends BaseGTEModule {

    public static final Logger logger = LogManager.getLogger("GTExpert Mod Integration");

    @NotNull
    @Override
    public Logger getLogger() {
        return logger;
    }

    @NotNull
    @Override
    public List<Class<?>> getEventBusSubscribers() {
        return Collections.singletonList(GTEIntegrationModule.class);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }
}
