package gtexpert.api.unification.material;

import gregtech.api.fluids.fluidType.FluidTypes;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;

import gregicality.multiblocks.api.fluids.fluidType.GCYMFluidTypes;

import gtexpert.api.unification.material.info.GTEMaterialIconSet;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gtexpert.api.unification.GTEElements.If;
import static gtexpert.api.unification.material.GTEMaterials.*;

public class GTEFirstDegreeMaterials {

    /**
     * 24001 - 24100
     */
    public static void init() {
        // Galvalume
        Galvalume = new Material.Builder(24001, "galvalume")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x072743).iconSet(METALLIC)
                .flags(EXT_METAL, GENERATE_FRAME)
                .components(Steel, 4, Zinc, 1, Aluminium, 1)
                .blastTemp(1700, GasTier.HIGHEST, 120, 120)
                .build();

        // NM_HEA_NPs
        NM_HEA_NPs = new Material.Builder(24002, "nm_hea_nps")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xa90000).iconSet(METALLIC)
                .flags(EXT_METAL, GENERATE_FRAME, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE)
                .components(Gold, 1, Silver, 1, Ruthenium, 1, Rhodium, 1, Palladium, 1, Osmium, 1, Iridium, 1, Platinum,
                        1)
                .blastTemp(9001, GasTier.HIGHER, VA[ZPM], 1000)
                .build();

        // Naquadah Rocket Fuel
        NaquadahRocketFuel = new Material.Builder(24003, "naquadah_rocket_fuel")
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x2E4C00)
                .flags(NO_WORKING)
                .build();

        // Infinity
        Infinity = new Material.Builder(24004, "infinity")
                .dust(7).ingot(7)
                .fluid(GCYMFluidTypes.MOLTEN, false).fluidTemp(10800)
                .iconSet(GTEMaterialIconSet.INFINITY)
                .flags(EXT2_METAL, GENERATE_DENSE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FRAME,
                        GENERATE_FINE_WIRE, GENERATE_RING, GENERATE_ROTOR, GENERATE_ROUND,
                        GENERATE_SPRING, GENERATE_SPRING_SMALL, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE, DISABLE_DECOMPOSITION,
                        NO_SMASHING, NO_SMELTING)
                .element(If)
                .build();
    }
}
