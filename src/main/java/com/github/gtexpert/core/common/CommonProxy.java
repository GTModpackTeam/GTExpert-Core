package com.github.gtexpert.core.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.github.gtexpert.core.api.GTEValues;

@Mod.EventBusSubscriber(modid = GTEValues.MODID)
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {}
}
