package com.github.gtexpert.core.integration.theoneprobe;

import java.util.List;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import org.jetbrains.annotations.NotNull;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.theoneprobe.provider.ElectricSpawnerInfoProvider;
import com.github.gtexpert.core.modules.GTEModules;
import com.google.common.collect.ImmutableList;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

@GTEModule(
           moduleID = GTEModules.MODULE_TOP,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.THE_ONE_PROBE,
           name = "GTExpert TheOneProbe Integration",
           description = "TheOneProbe Integration Module")
public class TheOneProbeModule extends GTEIntegrationSubmodule {

    @NotNull
    @Override
    public List<Class<?>> getEventBusSubscribers() {
        return ImmutableList.of(TheOneProbeModule.class);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        getLogger().info("TheOneProbe found. Enabling integration...");
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new ElectricSpawnerInfoProvider());
    }
}
