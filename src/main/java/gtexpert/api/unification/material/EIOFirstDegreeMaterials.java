package gtexpert.api.unification.material;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtexpert.api.unification.material.GTEMaterials.*;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;

public class EIOFirstDegreeMaterials {

    /**
     * 24101 - 24150
     */
    public static void init() {
        // Soulsand Dust
        SoulSand = new Material.Builder(24101, gregtechId("soul_sand"))
                .dust()
                .color(0x846C4C).iconSet(MaterialIconSet.SAND)
                .flags(NO_WORKING)
                .build();

        // Chorusfruit Dust
        ChorusFruit = new Material.Builder(24102, gregtechId("chorus_fruit"))
                .dust()
                .color(0x8C648C).iconSet(MaterialIconSet.DULL)
                .flags(NO_WORKING)
                .build();

        // Electrical Steel
        ElectricalSteel = new Material.Builder(24103, gregtechId("electrical_steel"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x949494).iconSet(MaterialIconSet.METALLIC)
                .flags(EXT_METAL, GENERATE_GEAR)
                .components(Steel, 1, Coal, 1, Silicon, 1)
                .blast(b -> b
                        .temp(2700, GasTier.HIGH)
                        .blastStats(VA[HV], 120)
                        .vacuumStats(VA[HV], 30))
                .build();

        // Energetic Alloy
        EnergeticAlloy = new Material.Builder(24104, gregtechId("energetic_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xED8009).iconSet(MaterialIconSet.SHINY)
                .flags(EXT_METAL, GENERATE_GEAR, GENERATE_DOUBLE_PLATE)
                .components(Redstone, 1, Gold, 1, Glowstone, 1)
                .blast(b -> b
                        .temp(2700, GasTier.HIGH)
                        .blastStats(VA[HV], 120)
                        .vacuumStats(VA[HV], 30))
                .build();
        EnergeticAlloy.setFormula("Au2(Si(FeS2)5(CrAl2O3)Hg3)2", true);

        // Vibrant Alloy
        VibrantAlloy = new Material.Builder(24105, gregtechId("vibrant_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xBAC63F).iconSet(MaterialIconSet.SHINY)
                .flags(EXT_METAL, GENERATE_GEAR, GENERATE_DOUBLE_PLATE)
                .components(EnergeticAlloy, 1, EnderPearl, 1)
                .blast(b -> b
                        .temp(2700, GasTier.HIGH)
                        .blastStats(VA[HV], 120)
                        .vacuumStats(VA[HV], 30))
                .build();
        VibrantAlloy.setFormula("BeK4N5(Au2(Si(FeS2)5(CrAl2O3)Hg3)2)", true);

        // Redstone Alloy
        RedstoneAlloy = new Material.Builder(24106, gregtechId("redstone_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x942323).iconSet(MaterialIconSet.DULL)
                .flags(GENERATE_PLATE)
                .components(RedAlloy, 1, Silicon, 1)
                .blast(b -> b
                        .temp(2700, GasTier.HIGH)
                        .blastStats(VA[HV], 120)
                        .vacuumStats(VA[HV], 30))
                .build();

        // Conductive Iron
        ConductiveIron = new Material.Builder(24107, gregtechId("conductive_iron"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xD1A79B).iconSet(MaterialIconSet.ROUGH)
                .flags(GENERATE_PLATE)
                .components(Iron, 1, RedstoneAlloy, 1)
                .blast(b -> b
                        .temp(1700, GasTier.HIGH)
                        .blastStats(VA[HV], 120)
                        .vacuumStats(VA[HV], 30))
                .build();

        // Pulsating Iron
        PulsatingIron = new Material.Builder(24108, gregtechId("pulsating_iron"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x6EAC7D).iconSet(MaterialIconSet.SHINY)
                .flags(EXT_METAL)
                .components(Iron, 1, EnderPearl, 1)
                .blast(b -> b
                        .temp(2700, GasTier.HIGH)
                        .blastStats(VA[HV], 120)
                        .vacuumStats(VA[HV]))
                .build();

        // Dark Steel
        DarkSteel = new Material.Builder(24109, gregtechId("dark_steel"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x5F5F5F).iconSet(MaterialIconSet.METALLIC)
                .flags(EXT2_METAL, GENERATE_RING, GENERATE_FRAME)
                .components(Iron, 1, Coal, 1, Obsidian, 1)
                .blast(b -> b
                        .temp(2700, GasTier.HIGH)
                        .blastStats(VA[HV], 120)
                        .vacuumStats(VA[HV]))
                .build();

        // Soularium
        Soularium = new Material.Builder(24110, gregtechId("soularium"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x5A4226).iconSet(MaterialIconSet.DULL)
                .flags(GENERATE_PLATE)
                .components(Gold, 1, Ash, 1, SoulSand, 1)
                .blast(b -> b
                        .temp(3600, GasTier.HIGH)
                        .blastStats(VA[HV], 600)
                        .vacuumStats(VA[HV], 150))
                .build();

        // End Steel
        EndSteel = new Material.Builder(24111, gregtechId("end_steel"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xBCB682).iconSet(MaterialIconSet.METALLIC)
                .flags(EXT2_METAL, GENERATE_RING)
                .components(Endstone, 1, DarkSteel, 1, Obsidian, 1)
                .blast(b -> b
                        .temp(4500, GasTier.HIGHER)
                        .blastStats(VA[EV], 1072)
                        .vacuumStats(VA[EV], 268))
                .build();
        EndSteel.setFormula("FeC(MgFeSi2O4)2?", true);

        // Iron Alloy
        ConstructionAlloy = new Material.Builder(24112, gregtechId("construction_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x524C53).iconSet(MaterialIconSet.ROUGH)
                .flags(GENERATE_PLATE)
                .components(Platinum, 1, Iron, 1, Aluminium, 1)
                .blast(b -> b
                        .temp(1700, GasTier.HIGHER)
                        .blastStats(VA[EV], 1072)
                        .vacuumStats(VA[EV], 268))
                .build();

        // Crystalline Alloy
        CrystallineAlloy = new Material.Builder(24113, gregtechId("crystalline_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x9FE4E4).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE)
                .components(Gold, 1, Platinum, 1, Emerald, 1, VibrantAlloy, 1)
                .blast(b -> b
                        .temp(4500, GasTier.HIGHER)
                        .blastStats(VA[EV], 600)
                        .vacuumStats(VA[EV], 150))
                .build();

        // Melodic Alloy
        MelodicAlloy = new Material.Builder(24114, gregtechId("melodic_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xA877A8).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE)
                .components(EndSteel, 1, ChorusFruit, 1)
                .blast(b -> b
                        .temp(5400, GasTier.HIGHER)
                        .blastStats(VA[EV], 600)
                        .vacuumStats(VA[EV], 150))
                .build();

        // Stellar Alloy
        StellarAlloy = new Material.Builder(24115, gregtechId("stellar_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xDBDECC).iconSet(MaterialIconSet.SHINY)
                .flags(EXT_METAL, GENERATE_LONG_ROD)
                .components(NetherStar, 1, MelodicAlloy, 1, Clay, 1)
                .blast(b -> b
                        .temp(7200, GasTier.HIGHER)
                        .blastStats(VA[LuV], 600)
                        .vacuumStats(VA[LuV], 150))
                .build();

        // Crystalline Pink Slime
        CrystallinePinkSlime = new Material.Builder(24116, gregtechId("crystalline_pink_slime"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xE79EDB).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE)
                .components(MelodicAlloy, 1, RawRubber, 2)
                .blast(b -> b
                        .temp(5400, GasTier.HIGHER)
                        .blastStats(VA[EV], 600)
                        .vacuumStats(VA[EV], 150))
                .build();

        // Energetic Silver
        EnergeticSilver = new Material.Builder(24117, gregtechId("energetic_silver"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x598DB3).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE)
                .components(Silver, 1, Redstone, 1, Glowstone, 1)
                .blast(b -> b
                        .temp(5400, GasTier.HIGHER)
                        .blastStats(VA[EV], 1072)
                        .vacuumStats(VA[EV], 268))
                .build();

        // Vivid Alloy
        VividAlloy = new Material.Builder(24118, gregtechId("vivid_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x469BB1).iconSet(MaterialIconSet.SHINY)
                .components(EnergeticSilver, 1, EnderPearl, 1)
                .blast(b -> b
                        .temp(5400, GasTier.HIGHER)
                        .blastStats(VA[EV], 1072)
                        .vacuumStats(VA[EV], 268))
                .build();
    }
}
