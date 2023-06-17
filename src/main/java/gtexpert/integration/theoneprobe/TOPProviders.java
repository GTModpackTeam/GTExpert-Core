package gtexpert.integration.theoneprobe;

import gtexpert.integration.theoneprobe.provider.ElectricSpawnerInfoProvider;
import mcjty.theoneprobe.TheOneProbe;

public class TOPProviders {

    public static void init() {
        TheOneProbe.theOneProbeImp.registerProvider(new ElectricSpawnerInfoProvider());
    }
}
