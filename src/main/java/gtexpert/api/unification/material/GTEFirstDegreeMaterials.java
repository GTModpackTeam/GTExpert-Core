package gtexpert.api.unification.material;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;

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
                .blastTemp(1700, GasTier.LOW, 120, 120)
                .build();
    }
}
