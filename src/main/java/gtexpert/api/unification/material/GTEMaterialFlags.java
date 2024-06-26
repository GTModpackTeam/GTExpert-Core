package gtexpert.api.unification.material;

import static gregtech.api.unification.material.info.MaterialFlags.*;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.*;
import gregtech.api.unification.ore.OrePrefix;

import gtexpert.api.util.Mods;

public class GTEMaterialFlags {

    public static void init() {
        // Iron
        Materials.Iron.addFlags(GENERATE_DOUBLE_PLATE);

        // Ender Peral
        Materials.EnderPearl.setProperty(PropertyKey.FLUID, new FluidProperty());

        // Ender Eye
        Materials.EnderEye.setProperty(PropertyKey.FLUID, new FluidProperty());
        Materials.EnderEye.setFormula(
                Materials.EnderPearl.getChemicalFormula() +
                        Materials.Blaze.getChemicalFormula(),
                true);

        // Nether Quartz
        Materials.NetherQuartz.setProperty(PropertyKey.FLUID, new FluidProperty());
        Materials.NetherQuartz.addFlags(GENERATE_LENS, GENERATE_ROD);

        // Certus Quartz
        Materials.CertusQuartz.setProperty(PropertyKey.FLUID, new FluidProperty());
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

        if (Mods.Forestry.isModLoaded()) {
            // Copper
            Materials.Copper.addFlags(GENERATE_GEAR);

            // Tin
            Materials.Tin.addFlags(GENERATE_GEAR);

            // Iron
            Materials.Iron.addFlags(GENERATE_FINE_WIRE, GENERATE_FOIL);

            // Bronze
            Materials.Bronze.addFlags(GENERATE_FINE_WIRE);

            // Rose Gold
            Materials.RoseGold.addFlags(GENERATE_FOIL);
        }
    }
}
