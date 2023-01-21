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


public class EIOFirstDegreeMaterials {
    /**
     * 25001 - 25100
     */
    public static void init() {
        // Soulsand Dust
        SOUL_SAND = new Material.Builder(25001, "soul_sand")
                .dust()
                .color(0x846C4C).iconSet(MaterialIconSet.SAND)
                .flags(NO_WORKING)
                .build();

        // Chorusfruit Dust
        CHORUS_FRUIT = new Material.Builder(25002, "chorus_fruit")
                .dust()
                .color(0x8C648C).iconSet(MaterialIconSet.DULL)
                .flags(NO_WORKING)
                .build();

        // Electrical Steel
        ELECTRICAL_STEEL = new Material.Builder(25003, "electrical_steel")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0x949494).iconSet(MaterialIconSet.METALLIC)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, DISABLE_DECOMPOSITION)
                .components(Steel, 1, Coal, 1, Silicon, 1)
                .build();

        // Energetic Alloy
        ENERGETIC_ALLOY = new Material.Builder(25004, "energetic_alloy")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0xED8009).iconSet(MaterialIconSet.SHINY)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, DISABLE_DECOMPOSITION)
                .components(Redstone, 1, Gold, 1, Glowstone, 1)
                .build();

        // Vibrant Alloy
        VIBRANT_ALLOY = new Material.Builder(25005, "vibrant_alloy")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0xBAC63F).iconSet(MaterialIconSet.SHINY)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR, DISABLE_DECOMPOSITION)
                .components(ENERGETIC_ALLOY, 1, EnderPearl, 1)
                .build();

        // Redstone Alloy
        REDSTONE_ALLOY = new Material.Builder(25006, "redstone_alloy")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0x942323).iconSet(MaterialIconSet.DULL)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(RedAlloy, 1, Silicon, 1)
                .build();

        // Conductive Iron
        CONDUCTIVE_IRON = new Material.Builder(25007, "conductive_iron")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0xD1A79B).iconSet(MaterialIconSet.ROUGH)
                .blastTemp(1700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Iron, 1, REDSTONE_ALLOY, 1)
                .build();

        // Pulsating Iron
        PULSATING_IRON = new Material.Builder(25008, "pulsating_iron")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0x6EAC7D).iconSet(MaterialIconSet.SHINY)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD, DISABLE_DECOMPOSITION)
                .components(Iron, 1, EnderPearl, 1)
                .build();

        // Dark Steel
        DARK_STEEL = new Material.Builder(25009, "dark_steel")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0x5F5F5F).iconSet(MaterialIconSet.METALLIC)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_RING, GENERATE_BOLT_SCREW, GENERATE_FRAME, DISABLE_DECOMPOSITION)
                .components(Iron, 1, Coal, 1, Obsidian, 1)
                .build();

        // Soularium
        SOULARIUM = new Material.Builder(25010, "soularium")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0x5A4226).iconSet(MaterialIconSet.DULL)
                .blastTemp(3600, GasTier.HIGH, 480, 600)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Gold, 1, Ash, 1)
                .build();

        // End Steel
        END_STEEL = new Material.Builder(25011, "end_steel")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0xBCB682).iconSet(MaterialIconSet.METALLIC)
                .blastTemp(4500, GasTier.HIGHER, 1920, 1072)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_RING, GENERATE_BOLT_SCREW, DISABLE_DECOMPOSITION)
                .components(Endstone, 1, DARK_STEEL, 1, Obsidian, 1)
                .build();

        // Iron Alloy
        CONSTRUCTION_ALLOY = new Material.Builder(25012, "construction_alloy")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0x524C53).iconSet(MaterialIconSet.ROUGH)
                .blastTemp(1700, GasTier.HIGHER, 1920, 1072)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Platinum, 1, Iron, 1, Aluminium, 1)
                .build();

        // Crystalline Alloy
        CRYSTALLINE_ALLOY = new Material.Builder(25013, "crystalline_alloy")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0x9FE4E4).iconSet(MaterialIconSet.SHINY)
                .blastTemp(4500, GasTier.HIGHER, 1920, 600)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Gold, 1, Platinum, 1, Emerald, 1, VIBRANT_ALLOY, 1)
                .build();

        // Melodic Alloy
        MELODIC_ALLOY = new Material.Builder(25014, "melodic_alloy")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0xA877A8).iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, 1920, 600)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                // .components(END_STEEL, 1, ChousFruit, 1)
                .build();
        MELODIC_ALLOY.setFormula("FeC(MgFeSi2O4)2?", true);

        // Stellar Alloy
        STELLAR_ALLOY = new Material.Builder(25015, "stellar_alloy")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0xDBDECC).iconSet(MaterialIconSet.SHINY)
                .blastTemp(7200, GasTier.HIGHER, 30720, 600)
                .flags(GENERATE_PLATE, GENERATE_ROD, DISABLE_DECOMPOSITION)
                .components(NetherStar, 1, MELODIC_ALLOY, 1, Clay, 1)
                .build();
        STELLAR_ALLOY.setFormula("FeC(MgFeSi2O4)2(Na2LiAl2Si2(H2O)6)?", true);

        // Crystalline Pink Slime
        CRYSTALLINE_PINK_SLIME = new Material.Builder(25016, "crystalline_pink_slime")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0xE79EDB).iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, 1920, 600)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(MELODIC_ALLOY, 1, RawRubber, 2)
                .build();

        // Energetic Silver
        ENERGETIC_SILVER = new Material.Builder(25017, "energetic_silver")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0x598DB3).iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, 1920, 1072)
                .flags(DISABLE_DECOMPOSITION)
                .components(Silver, 1, Redstone, 1, Glowstone, 1)
                .build();

        // Vivid Alloy
        VIVID_ALLOY = new Material.Builder(25018, "vivid_alloy")
                .dust().ingot()
                .fluid().fluidTemp(1200)
                .color(0x469BB1).iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, 1920, 1072)
                .flags(DISABLE_DECOMPOSITION)
                .components(ENERGETIC_SILVER, 1, EnderPearl, 1)
                .build();
    }
}
