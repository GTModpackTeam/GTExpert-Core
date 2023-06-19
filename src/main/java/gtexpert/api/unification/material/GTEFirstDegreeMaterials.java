package gtexpert.api.unification.material;

import gregtech.api.fluids.fluidType.FluidTypes;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;

import gtexpert.api.unification.material.info.GTEMaterialIconSet;
import net.minecraftforge.fml.common.Loader;

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

        // Neutronium
//        if (Loader.isModLoaded("avaritia")) {
//            Neutronium.setMaterialRGB(0x000000);
//            Neutronium.setMaterialIconSet(GTEMaterialIconSet.NEUTRONIUM);
//            Neutronium.addFlags(GENERATE_SMALL_GEAR, GENERATE_FOIL, GENERATE_RING, GENERATE_ROTOR);
//        }

        // Infinity
        Infinity = new Material.Builder(24005, "infinity")
                .dust(7).ingot(7)
                .fluid(FluidTypes.LIQUID, false).fluidTemp(10800)
                .iconSet(GTEMaterialIconSet.INFINITY)
                .flags(EXT2_METAL, GENERATE_DENSE, GENERATE_GEAR, GENERATE_SMALL_GEAR, GENERATE_FRAME,
                        GENERATE_FOIL, GENERATE_FINE_WIRE, GENERATE_RING, GENERATE_ROTOR, // GENERATE_ROUND,
                        GENERATE_BOLT_SCREW, GENERATE_SPRING, EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES,
                        EXCLUDE_BLOCK_CRAFTING_RECIPES, EXCLUDE_PLATE_COMPRESSOR_RECIPE, NO_SMASHING, NO_SMELTING)
                .fluidPipeProperties(150_000, 7500, true, true, true, true)
                .cableProperties(V[UEV], 32, 0, true, 1) //TODO: Texture is not applied[gregtech.client.renderer.pipe]
                .element(If)
                .build();
    }
}
