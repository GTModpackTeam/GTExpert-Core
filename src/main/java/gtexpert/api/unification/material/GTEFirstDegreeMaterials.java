package gtexpert.api.unification.material;

import gregtech.api.fluids.fluidType.FluidTypes;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtexpert.api.unification.material.GTEMaterials.*;

public class GTEFirstDegreeMaterials {

    /**
     * 24001 - 24100
     */
    public static void init() {
        // Galvalume
        Galvalume = new Material.Builder(24001, gregtechId("galvalume"))
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x072743).iconSet(MaterialIconSet.METALLIC)
                .flags(EXT_METAL, GENERATE_FRAME)
                .components(Steel, 4, Zinc, 1, Aluminium, 1)
                .blastTemp(1700, GasTier.HIGHEST, 120, 120)
                .build();

        // NM_HEA_NPs
        NM_HEA_NPs = new Material.Builder(24002, gregtechId("nm_hea_nps"))
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xa90000).iconSet(MaterialIconSet.METALLIC)
                .flags(EXT_METAL, GENERATE_FRAME, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE)
                .components(Gold, 1, Silver, 1, Ruthenium, 1, Rhodium, 1, Palladium, 1, Osmium, 1, Iridium, 1, Platinum,
                        1)
                .blastTemp(9001, GasTier.HIGHER, VA[ZPM], 1000)
                .build();

        // Naquadah Rocket Fuel
        NAQUADAH_ROCKET_FUEL = new Material.Builder(24003, gregtechId("naquadah_rocket_fuel"))
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x2E4C00)
                .flags(NO_WORKING)
                .build();
    }
}
