package gtexpert.api.unification.material;

import gregtech.api.fluids.fluidType.FluidTypes;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty;

import static gtexpert.api.unification.material.GTEMaterials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

public class DEDegreeMaterials {
    /**
     * 24176 - 24200
     */
    public static void init() {
        // Cryotheum Dust
        CRYOTHEUM = new Material.Builder(24176, "cryotheum")
                .dust()
                .fluid().fluidTemp(203)
                .color(0x00B6FF).iconSet(MaterialIconSet.DULL)
                .build();

        // Pyrotheum Dust
        PYROTHEUM = new Material.Builder(24177, "pyrotheum")
                .dust()
                .fluid().fluidTemp(10273)
                .color(0xE42C13).iconSet(MaterialIconSet.DULL)
                .build();

        // Dragon Dust
        DRAGON = new Material.Builder(24178, "dragon")
                .dust()
                .color(0x000000).iconSet(MaterialIconSet.ROUGH)
                .build();

        // Draconium
        DRACONIUM = new Material.Builder(24179, "draconium")
                .ore(true)
                .ingot(5)
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x7C46B0).iconSet(MaterialIconSet.METALLIC)
                .blastTemp(7200, BlastProperty.GasTier.HIGHER, 30720, 600)
                .flags(GENERATE_FRAME, GENERATE_FINE_WIRE, GENERATE_PLATE, GENERATE_ROD, GENERATE_RING, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES, EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE, DISABLE_DECOMPOSITION)
                .build();

        // Awakened Draconium
        DRACONIUM_AWAKENED = new Material.Builder(24180, "draconium_awakened")
                .ingot(6)
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xFF6C00).iconSet(MaterialIconSet.METALLIC)
                .blastTemp(7200, BlastProperty.GasTier.HIGHER, 30720, 600)
                .flags(GENERATE_FRAME, GENERATE_FINE_WIRE, GENERATE_PLATE, GENERATE_ROD, GENERATE_RING, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES, EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE, DISABLE_DECOMPOSITION)
                .build();
    }
}
