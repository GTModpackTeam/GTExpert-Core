package com.github.gtexpert.core.api.unification.material;

import static com.github.gtexpert.core.api.unification.material.GTEMaterials.*;
import static com.github.gtexpert.core.api.util.GTEUtility.gteId;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;

public class GTEFirstDegreeMaterials {

    /**
     * 24001 - 24100
     */
    public static void init() {
        // Artificial Bone 24001
        ArtificialBone = new Material.Builder(24001, gteId("artificial_bone"))
                .dust(1)
                .color(0xFAFAFA)
                .flags(FORCE_GENERATE_BLOCK, MORTAR_GRINDABLE, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES)
                .components(Calcium, 10, Phosphate, 6, Hydrogen, 2, Oxygen, 2)
                .build();

        // NM_HEA_NPs
        NM_HEA_NPs = new Material.Builder(24002, gteId("nm_hea_nps"))
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
        NaquadahRocketFuel = new Material.Builder(24003, gteId("naquadah_rocket_fuel"))
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x2E4C00)
                .flags(NO_WORKING)
                .build();
    }
}
