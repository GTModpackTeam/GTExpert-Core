package gtexpert.api.unification.material;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;
import gregtech.api.unification.material.properties.FluidProperty;
import gregtech.api.unification.material.properties.PropertyKey;

import static gregtech.api.GTValues.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class GTEFirstDegreeMaterials {

    /**
     * 24001 -24100
     */
    public static void init() {

        Galvalume = new Material.Builder(24001, "galvalume")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x072743)
                .iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Steel, 4, Zinc, 1, Aluminium, 1)
                .blastTemp(1700, GasTier.HIGHEST, 120, 120)
                .build();

        NM_HEA_NPs = new Material.Builder(24002, "nm_hea_nps")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0xa90000)
                .iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Gold, 1, Silver ,1, Ruthenium, 1, Rhodium, 1, Palladium, 1, Osmium, 1, Iridium, 1, Platinum, 1)
                .blastTemp(9001, GasTier.HIGHER, VA[ZPM], 1000)
                .build();

        EnderPearl.setProperty(PropertyKey.FLUID, new FluidProperty());
    }
}
