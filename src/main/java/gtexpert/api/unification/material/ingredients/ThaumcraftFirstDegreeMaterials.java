package gtexpert.api.unification.material.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtexpert.api.unification.GTEElements.*;
import static gtexpert.api.unification.material.GTEMaterials.*;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class ThaumcraftFirstDegreeMaterials {

    /**
     * 24191 - 24199
     */
    public static void init() {
        // Thaumium
        Thaumium = new Material.Builder(24191, gregtechId("thaumium"))
                .dust(3).ingot(3)
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x9664c8).iconSet(MaterialIconSet.METALLIC)
                .flags(EXT2_METAL, GENERATE_DENSE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FRAME,
                        GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_RING, GENERATE_ROTOR, GENERATE_ROUND,
                        GENERATE_BOLT_SCREW, GENERATE_SPRING, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE, NO_SMASHING, NO_SMELTING)
                .element(FeMa)
                .build();

        // Void Metal
        VoidMetal = new Material.Builder(24192, gregtechId("void_metal"))
                .dust(4).ingot(4)
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x1c0639).iconSet(MaterialIconSet.SHINY)
                .flags(EXT2_METAL, GENERATE_DENSE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FRAME,
                        GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_RING, GENERATE_ROTOR, GENERATE_ROUND,
                        GENERATE_BOLT_SCREW, GENERATE_SPRING, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE, NO_SMASHING, NO_SMELTING)
                .build();
    }
}
