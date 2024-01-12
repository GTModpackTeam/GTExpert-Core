package gtexpert.integration.theoneprobe;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.integration.IntegrationSubmodule;
import gtexpert.integration.theoneprobe.provider.ElectricSpawnerInfoProvider;
import gtexpert.modules.GTEModules;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

@GTEModule(
           moduleID = GTEModules.MODULE_INTEGRATION,
           containerID = GTEValues.MODID,
           modDependencies = GTEValues.MODID_TOP,
           name = "GTExpert TheOneProbe Integration",
           description = "TheOneProbe Integration Module")
public class TOPModule extends IntegrationSubmodule {

    @Override
    public void init(FMLInitializationEvent event) {
        getLogger().info("TheOneProbe found. Enabling integration...");
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new ElectricSpawnerInfoProvider());
    }
}
