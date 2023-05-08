package gtexpert.api.unification.material;

import gregtech.api.fluids.fluidType.FluidTypes;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gtexpert.api.unification.material.GTEMaterials.*;

public class EIOFirstDegreeMaterials {
    /**
     * 24101 - 24150
     */
    public static void init() {
        // Soulsand Dust
        SOUL_SAND = new Material.Builder(24101, "soul_sand")
                .dust()
                .color(0x846C4C).iconSet(MaterialIconSet.SAND)
                .flags(NO_WORKING)
                .build();

        // Chorusfruit Dust
        CHORUS_FRUIT = new Material.Builder(24102, "chorus_fruit")
                .dust()
                .color(0x8C648C).iconSet(MaterialIconSet.DULL)
                .flags(NO_WORKING)
                .build();

        // Electrical Steel
        ELECTRICAL_STEEL = new Material.Builder(24103, "electrical_steel")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x949494).iconSet(MaterialIconSet.METALLIC)
                .blastTemp(2700, GasTier.HIGH, VA[HV], 120)
                .flags(EXT_METAL, GENERATE_GEAR)
                .components(Steel, 1, Coal, 1, Silicon, 1)
                .build();

        // Energetic Alloy
        ENERGETIC_ALLOY = new Material.Builder(24104, "energetic_alloy")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xED8009).iconSet(MaterialIconSet.SHINY)
                .blastTemp(2700, GasTier.HIGH, VA[HV], 120)
                .flags(EXT_METAL, GENERATE_GEAR)
                .components(Redstone, 1, Gold, 1, Glowstone, 1)
                .build();
        ENERGETIC_ALLOY.setFormula("Au2(Si(FeS2)5(CrAl2O3)Hg3)2", true);

        // Vibrant Alloy
        VIBRANT_ALLOY = new Material.Builder(24105, "vibrant_alloy")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xBAC63F).iconSet(MaterialIconSet.SHINY)
                .blastTemp(2700, GasTier.HIGH, VA[HV], 120)
                .flags(EXT_METAL, GENERATE_GEAR)
                .components(ENERGETIC_ALLOY, 1, EnderPearl, 1)
                .build();
        VIBRANT_ALLOY.setFormula("BeK4N5(Au2(Si(FeS2)5(CrAl2O3)Hg3)2)", true);

        // Redstone Alloy
        REDSTONE_ALLOY = new Material.Builder(24106, "redstone_alloy")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x942323).iconSet(MaterialIconSet.DULL)
                .blastTemp(2700, GasTier.HIGH, VA[HV], 120)
                .flags(GENERATE_PLATE)
                .components(RedAlloy, 1, Silicon, 1)
                .build();

        // Conductive Iron
        CONDUCTIVE_IRON = new Material.Builder(24107, "conductive_iron")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xD1A79B).iconSet(MaterialIconSet.ROUGH)
                .blastTemp(1700, GasTier.HIGH, VA[HV], 120)
                .flags(GENERATE_PLATE)
                .components(Iron, 1, REDSTONE_ALLOY, 1)
                .build();

        // Pulsating Iron
        PULSATING_IRON = new Material.Builder(24108, "pulsating_iron")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x6EAC7D).iconSet(MaterialIconSet.SHINY)
                .blastTemp(2700, GasTier.HIGH, VA[HV], 120)
                .flags(EXT_METAL)
                .components(Iron, 1, EnderPearl, 1)
                .build();

        // Dark Steel
        DARK_STEEL = new Material.Builder(24109, "dark_steel")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x5F5F5F).iconSet(MaterialIconSet.METALLIC)
                .blastTemp(2700, GasTier.HIGH, VA[HV], 120)
                .flags(EXT2_METAL, GENERATE_RING, GENERATE_FRAME)
                .components(Iron, 1, Coal, 1, Obsidian, 1)
                .build();

        // Soularium
        SOULARIUM = new Material.Builder(24110, "soularium")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x5A4226).iconSet(MaterialIconSet.DULL)
                .blastTemp(3600, GasTier.HIGH, VA[HV], 600)
                .flags(GENERATE_PLATE)
                .components(Gold, 1, Ash, 1, SOUL_SAND, 1)
                .build();

        // End Steel
        END_STEEL = new Material.Builder(24111, "end_steel")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xBCB682).iconSet(MaterialIconSet.METALLIC)
                .blastTemp(4500, GasTier.HIGHER, VA[EV], 1072)
                .flags(EXT2_METAL, GENERATE_RING)
                .components(Endstone, 1, DARK_STEEL, 1, Obsidian, 1)
                .build();
        END_STEEL.setFormula("FeC(MgFeSi2O4)2?", true);

        // Iron Alloy
        CONSTRUCTION_ALLOY = new Material.Builder(24112, "construction_alloy")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x524C53).iconSet(MaterialIconSet.ROUGH)
                .blastTemp(1700, GasTier.HIGHER, VA[EV], 1072)
                .flags(GENERATE_PLATE)
                .components(Platinum, 1, Iron, 1, Aluminium, 1)
                .build();

        // Crystalline Alloy
        CRYSTALLINE_ALLOY = new Material.Builder(24113, "crystalline_alloy")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x9FE4E4).iconSet(MaterialIconSet.SHINY)
                .blastTemp(4500, GasTier.HIGHER, VA[EV], 600)
                .flags(GENERATE_PLATE)
                .components(Gold, 1, Platinum, 1, Emerald, 1, VIBRANT_ALLOY, 1)
                .build();

        // Melodic Alloy
        MELODIC_ALLOY = new Material.Builder(24114, "melodic_alloy")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xA877A8).iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, VA[EV], 600)
                .flags(GENERATE_PLATE)
                .components(END_STEEL, 1, CHORUS_FRUIT, 1)
                .build();

        // Stellar Alloy
        STELLAR_ALLOY = new Material.Builder(24115, "stellar_alloy")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xDBDECC).iconSet(MaterialIconSet.SHINY)
                .blastTemp(7200, GasTier.HIGHER, VA[LuV], 600)
                .flags(EXT_METAL, GENERATE_LONG_ROD)
                .components(NetherStar, 1, MELODIC_ALLOY, 1, Clay, 1)
                .build();

        // Crystalline Pink Slime
        CRYSTALLINE_PINK_SLIME = new Material.Builder(24116, "crystalline_pink_slime")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0xE79EDB).iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, VA[EV], 600)
                .flags(GENERATE_PLATE)
                .components(MELODIC_ALLOY, 1, RawRubber, 2)
                .build();

        // Energetic Silver
        ENERGETIC_SILVER = new Material.Builder(24117, "energetic_silver")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x598DB3).iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, VA[EV], 1072)
                .components(Silver, 1, Redstone, 1, Glowstone, 1)
                .build();

        // Vivid Alloy
        VIVID_ALLOY = new Material.Builder(24118, "vivid_alloy")
                .ingot()
                .fluid(FluidTypes.LIQUID, false).fluidTemp(1200)
                .color(0x469BB1).iconSet(MaterialIconSet.SHINY)
                .blastTemp(5400, GasTier.HIGHER, 1920, 1072)
                .components(ENERGETIC_SILVER, 1, EnderPearl, 1)
                .build();
    }
}
