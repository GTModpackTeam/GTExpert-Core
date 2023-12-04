package gtexpert.api.unification.material;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtexpert.api.unification.material.GTEMaterials.*;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;

public class GTEFirstDegreeMaterials {

    /**
     * 24001 - 24100
     */
    public static void init() {
        // FreeSpace 24001

        // NM_HEA_NPs
        NM_HEA_NPs = new Material.Builder(24002, gregtechId("nm_hea_nps"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xa90000).iconSet(METALLIC)
                .flags(EXT_METAL, GENERATE_FRAME, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE)
                .components(Gold, 1, Silver, 1, Ruthenium, 1, Rhodium, 1,
                        Palladium, 1, Osmium, 1, Iridium, 1, Platinum, 1)
                .blast(b -> b
                        .temp(9001, GasTier.HIGHER)
                        .blastStats(VA[ZPM], 1000)
                        .vacuumStats(VA[ZPM], 250))
                .build();

        // Naquadah Rocket Fuel
        NaquadahRocketFuel = new Material.Builder(24003, gregtechId("naquadah_rocket_fuel"))
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x2E4C00)
                .flags(NO_WORKING)
                .build();
    }
}
