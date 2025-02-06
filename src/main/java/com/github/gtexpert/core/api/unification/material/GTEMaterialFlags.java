package com.github.gtexpert.core.api.unification.material;

import static gregtech.api.unification.material.info.MaterialFlags.*;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.*;
import gregtech.api.unification.ore.OrePrefix;

public class GTEMaterialFlags {

    public static void init() {
        // Iron
        Materials.Iron.addFlags(GENERATE_DOUBLE_PLATE);

        // Ender Peral
        FluidProperty enderPeralProp = new FluidProperty();
        enderPeralProp.enqueueRegistration(FluidStorageKeys.LIQUID, new FluidBuilder());
        Materials.EnderPearl.setProperty(PropertyKey.FLUID, enderPeralProp);

        // Ender Eye
        FluidProperty enderEyeProp = new FluidProperty();
        enderEyeProp.enqueueRegistration(FluidStorageKeys.LIQUID, new FluidBuilder());
        Materials.EnderEye.setProperty(PropertyKey.FLUID, enderEyeProp);
        Materials.EnderEye.setFormula(
                Materials.EnderPearl.getChemicalFormula() +
                        Materials.Blaze.getChemicalFormula(),
                true);

        // Nether Quartz
        FluidProperty netherQuartzProp = new FluidProperty();
        netherQuartzProp.enqueueRegistration(FluidStorageKeys.LIQUID, new FluidBuilder());
        Materials.NetherQuartz.setProperty(PropertyKey.FLUID, netherQuartzProp);
        Materials.NetherQuartz.addFlags(GENERATE_LENS, GENERATE_ROD);

        // Certus Quartz
        FluidProperty certusQuartzProp = new FluidProperty();
        certusQuartzProp.enqueueRegistration(FluidStorageKeys.LIQUID, new FluidBuilder());
        Materials.CertusQuartz.setProperty(PropertyKey.FLUID, certusQuartzProp);
        Materials.CertusQuartz.addFlags(GENERATE_LENS, GENERATE_ROD);

        // Quartzite
        Materials.Quartzite.addFlags(GENERATE_ROD);
        OrePrefix.block.modifyMaterialAmount(Materials.Quartzite, 4);

        // Red Alloy
        Materials.RedAlloy.addFlags(MORTAR_GRINDABLE);

        // Glowstone
        Materials.Glowstone.setFormula(
                Materials.Gold.getChemicalFormula() +
                        Materials.Redstone.getChemicalFormula(),
                true);

        // Darmstadtium
        Materials.Darmstadtium.addFlags(GENERATE_GEAR, GENERATE_FRAME);

        // Osmium
        Materials.Osmium.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Osmium.getProperty(PropertyKey.ORE).setOreByProducts(Materials.Iridium);

        // Iridium
        Materials.Iridium.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Iridium.getProperty(PropertyKey.ORE).setOreByProducts(Materials.Platinum, Materials.Osmium);
    }
}
