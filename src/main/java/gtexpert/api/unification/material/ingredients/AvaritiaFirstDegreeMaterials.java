package gtexpert.api.unification.material.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gtexpert.api.unification.GTEElements.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.api.util.GTEUtility.gteId;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.ToolProperty;

import gtexpert.api.unification.material.info.GTEMaterialIconSet;

public class AvaritiaFirstDegreeMaterials {

    public static void init() {
        // Neutronium
        // Neutronium.setMaterialRGB(0x000000);
        // Neutronium.setMaterialIconSet(GTEMaterialIconSet.NEUTRONIUM);
        Neutronium.addFlags(GENERATE_SMALL_GEAR, GENERATE_FOIL, GENERATE_RING, GENERATE_ROTOR, NO_SMELTING);

        // Infinity
        Infinity = new Material.Builder(24186, gteId("infinity"))
                .dust(7).ingot(7)
                .liquid(new FluidBuilder().temperature(10800))
                .iconSet(GTEMaterialIconSet.INFINITY)
                .flags(EXT2_METAL, GENERATE_DENSE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FRAME,
                        GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_RING, GENERATE_ROTOR, GENERATE_ROUND,
                        GENERATE_BOLT_SCREW, GENERATE_SPRING, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE, NO_SMASHING, NO_SMELTING)
                .toolStats(ToolProperty.Builder.of(200.0F, 140.0F, 65535, 6).attackSpeed(0.5F).enchantability(38)
                        .magnetic().unbreakable().build())
                .fluidPipeProperties(10_000_000, 200_000, true, true, true, true)
                .cableProperties(V[MAX], 8192, 0, true)
                .element(If)
                .build();
    }
}
