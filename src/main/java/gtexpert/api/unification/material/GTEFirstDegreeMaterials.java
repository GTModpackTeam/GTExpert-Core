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

public class GTEFirstDegreeMaterials {

    /**
     * 24001 -24100
     */
    public static void init() {
        // Ender Peral
        EnderPearl.setProperty(PropertyKey.FLUID, new FluidProperty());

        // NetherQuartz
        NetherQuartz.setProperty(PropertyKey.FLUID, new FluidProperty());
        NetherQuartz.addFlags(GENERATE_LENS, GENERATE_ROD);

        // Certus Quartz
        CertusQuartz.setProperty(PropertyKey.FLUID, new FluidProperty());
        CertusQuartz.addFlags(GENERATE_LENS, GENERATE_ROD);

        // Quartzite
        Quartzite.addFlags(GENERATE_ROD);

        // Glowstone
        Glowstone.setFormula("Au(Si(FeS2)5(CrAl2O3)Hg3)");

        // Galvalume
        Galvalume = new Material.Builder(24001, "galvalume")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x072743)
                .iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Steel, 4, Zinc, 1, Aluminium, 1)
                .blastTemp(1700, GasTier.HIGHEST, 120, 120)
                .build();

        // NM_HEA_NPs
        NM_HEA_NPs = new Material.Builder(24002, "nm_hea_nps")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0xa90000)
                .iconSet(MaterialIconSet.METALLIC)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FRAME)
                .components(Gold, 1, Silver ,1, Ruthenium, 1, Rhodium, 1, Palladium, 1, Osmium, 1, Iridium, 1, Platinum, 1)
                .blastTemp(9001, GasTier.HIGHER, VA[ZPM], 1000)
                .build();

        // Naquadah Rocket Fuel
        NAQUADAH_ROCKET_FUEL = new Material.Builder(24003, "naquadah_rocket_fuel")
                .fluid()
                .fluidTemp(1200)
                .color(0x2E4C00)
                .iconSet(MaterialIconSet.FLUID)
                .build();

        // Electrical Steel
        ELECTRICAL_STEEL = new Material.Builder(24004, "electrical_steel")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x949494)
                .iconSet(MaterialIconSet.METALLIC)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR)
                .components(Steel, 1, Coal, 1, Silicon, 1)
                .build();

        // Energetic Alloy
        ENERGETIC_ALLOY = new Material.Builder(24005, "energetic_alloy")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0xED8009)
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR)
                .components(Redstone, 1, Gold, 1, Glowstone, 1)
                .build();

        // Vibrant Alloy
        VIBRANT_ALLOY = new Material.Builder(24006, "vibrant_alloy")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0xBAC63F)
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_GEAR)
                .components(ENERGETIC_ALLOY, 1, EnderPearl, 1)
                .build();

        // Redstone Alloy
        REDSTONE_ALLOY = new Material.Builder(24007, "redstone_alloy")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x942323)
                .iconSet(MaterialIconSet.DULL)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE)
                .components(RedAlloy, 1, Silicon, 1)
                .build();

        // Conductive Iron
        CONDUCTIVE_IRON = new Material.Builder(24008, "conductive_iron")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0xD1A79B)
                .iconSet(MaterialIconSet.ROUGH)
                .blastTemp(1700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE)
                .components(Iron, 1, REDSTONE_ALLOY, 1)
                .build();

        // Pulsating Iron
        PULSATING_IRON = new Material.Builder(24009, "pulsating_iron")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x6EAC7D)
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD)
                .components(Iron, 1, EnderPearl, 1)
                .build();

        // Dark Steel
        DARK_STEEL = new Material.Builder(24010, "dark_steel")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x5F5F5F)
                .iconSet(MaterialIconSet.METALLIC)
                .blastTemp(2700, GasTier.HIGH, 480, 120)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_RING, GENERATE_BOLT_SCREW, GENERATE_FRAME)
                .components(Iron, 1, Coal, 1, Obsidian, 1)
                .build();

        // Soularium
        SOULARIUM = new Material.Builder(24011, "soularium")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x5A4226)
                .iconSet(MaterialIconSet.DULL)
                .blastTemp(3600, GasTier.HIGH, 480, 600)
                .flags(GENERATE_PLATE)
                .components(Gold, 1, Ash, 1)
                .build();

        // End Steel
        END_STEEL = new Material.Builder(24012, "end_steel")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0xBCB682)
                .iconSet(MaterialIconSet.METALLIC)
                .blastTemp(4500, GasTier.HIGHER, 1920, 1072)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_RING, GENERATE_BOLT_SCREW)
                .components(Endstone, 1, DARK_STEEL, 1, Obsidian, 1)
                .build();

        // Construction Alloy(Iron Alloy)
        CONSTRUCTION_ALLOY = new Material.Builder(24013, "construction_alloy")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x524C53)
                .iconSet(MaterialIconSet.ROUGH)
                .blastTemp(1700, GasTier.HIGHER, 1920, 1072)
                .flags(GENERATE_PLATE)
                .components(Platinum, 1, Iron, 1, Aluminium, 1)
                .build();

        // Crude Steel
        CRUDE_STEEL = new Material.Builder(24014, "crude_steel")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x8C8682)
                .iconSet(MaterialIconSet.ROUGH)
                .blastTemp(1700, GasTier.HIGHER, 1920, 600)
                .components(Steel, 1, Clay, 1, Flint, 1)
                .build();

        // Crystalline Alloy
        CRYSTALLINE_ALLOY = new Material.Builder(24015, "crystalline_alloy")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x9FE4E4)
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(4500, GasTier.HIGHER, 1920, 600)
                .flags(GENERATE_PLATE)
                .components(Gold, 1, Platinum, 1, Emerald, 1, VIBRANT_ALLOY, 1)
                .build();

        // Melodic Alloy
        MELODIC_ALLOY = new Material.Builder(24016, "melodic_alloy")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0xA877A8)
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, 1920, 600)
                .flags(GENERATE_PLATE)
                // .components(END_STEEL, 1, ChousFruit, 1)
                .build();
        // MELODIC_ALLOY.setFormula("FeC(MgFeSi2O4)2?", true);

        // Stellar Alloy
        STELLAR_ALLOY = new Material.Builder(24017, "stellar_alloy")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0xDBDECC)
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(7200, GasTier.HIGHER, 30720, 600)
                .flags(GENERATE_PLATE, GENERATE_ROD)
                .components(NetherStar, 1, MELODIC_ALLOY, 1, Clay, 1)
                .build();
        // STELLAR_ALLOY.setFormula("FeC(MgFeSi2O4)2(Na2LiAl2Si2(H2O)6)?", true);

        // Crystalline Pink Slime
        CRYSTALLINE_PINK_SLIME = new Material.Builder(24018, "crystalline_pink_slime")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0xE79EDB)
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, 1920, 600)
                .flags(GENERATE_PLATE)
                .components(MELODIC_ALLOY, 1, RawRubber, 2)
                .build();

        // Energetic Silver
        ENERGETIC_SILVER = new Material.Builder(24019, "energetic_silver")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x598DB3)
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, 1920, 1072)
                .components(Silver, 1, Redstone, 1, Glowstone, 1)
                .build();

        // Vivid Alloy
        VIVID_ALLOY = new Material.Builder(24020, "vivid_alloy")
                .dust().ingot().fluid()
                .fluidTemp(1200)
                .color(0x469BB1)
                .iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, 1920, 1072)
                .components(ENERGETIC_SILVER, 1, EnderPearl, 1)
                .build();

        // Fluix
        FLUIX = new Material.Builder(24021, "fluix")
                .dust().fluid()
                .fluidTemp(1200)
                .color(0x846994)
                .iconSet(MaterialIconSet.QUARTZ)
                .flags(GENERATE_PLATE, GENERATE_LENS)
                .components(Silicon, 2, Oxygen, 4, Redstone, 1)
                .build();

        // Fluix Steel
        //FLUIX_STEEL = new Material.Builder(24022, "fluix_steel")
        //        .dust().ingot().fluid()
        //        .fluidTemp(1200)
        //        .color(0x846994)
        //        .iconSet(MaterialIconSet.SHINY)
        //        .blastTemp(5400, GasTier.HIGHER, 1920, 1072)
        //        .components(FLUIX, 1, Steel, 1)
        //        .build();

        // Charged Certus Quartz
        CHARGED_CERTUS_QUARTZ = new Material.Builder(24023, "charged_certus_quartz")
                .dust().fluid()
                .fluidTemp(1200)
                .color(0xCFDAFF)
                .iconSet(MaterialIconSet.QUARTZ)
                .flags(GENERATE_PLATE, GENERATE_LENS)
                .components(Silicon, 1, Oxygen, 2)
                .build();
    }
}
