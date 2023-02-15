package gtexpert.api.unification.material;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;
import gregtech.api.unification.material.properties.FluidProperty;
import gregtech.api.unification.material.properties.PropertyKey;

import static gregtech.api.GTValues.VA;
import static gregtech.api.GTValues.ZPM;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gtexpert.api.unification.material.GTEMaterials.*;

public class AEFirstDegreeMaterials {

    /**
     * 25101 - 25200
     */
    public static void init() {
        // Fluix
        FLUIX = new Material.Builder(25101, "fluix")
                .dust()
                .fluid().fluidTemp(1200)
                .color(0x846994).iconSet(MaterialIconSet.QUARTZ)
                .flags(GENERATE_PLATE, GENERATE_LENS, DISABLE_DECOMPOSITION)
                .components(Silicon, 2, Oxygen, 4, Redstone, 1)
                .build();

        // Fluix Alloy
        FLUIX_ALLOY = new Material.Builder(25102, "fluix_alloy")
                .ingot()
                .fluid().fluidTemp(1200)
                .color(0x4A3954).iconSet(MaterialIconSet.SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .blastTemp(2700, GasTier.LOW, 480, 1072)
                .components(FLUIX, 2, Carbon, 2, Silicon, 1)
                .build();

        // Charged Certus Quartz
        CHARGED_CERTUS_QUARTZ = new Material.Builder(25103, "charged_certus_quartz")
                .dust()
                .fluid().fluidTemp(1200)
                .color(0xCFDAFF).iconSet(MaterialIconSet.QUARTZ)
                .flags(GENERATE_PLATE, GENERATE_LENS, DISABLE_DECOMPOSITION)
                .components(Silicon, 1, Oxygen, 2)
                .build();
    }
}
