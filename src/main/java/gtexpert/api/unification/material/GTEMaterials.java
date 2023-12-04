package gtexpert.api.unification.material;

import net.minecraftforge.fml.common.Loader;

import gregtech.api.unification.material.Material;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.ingredients.*;

/**
 * Material Registration.
 * <p>
 * All Material Builders should follow this general formatting:
 * <p>
 * material = new MaterialBuilder(id, name)
 * .ingot().fluid().ore() <--- types
 * .color().iconSet() <--- appearance
 * .flags() <--- special generation
 * .element() / .components() <--- composition
 * .toolStats() <---
 * .oreByProducts() | additional properties
 * ... <---
 * .blastTemp() <--- blast temperature
 * .build();
 * <p>
 * Use defaults to your advantage! Some defaults:
 * - iconSet: DULL
 * - color: 0xFFFFFF
 */
public class GTEMaterials {

    /*
     * FOR ADDON DEVELOPERS:
     *
     * GTCEu will not take more than 3000 IDs. Anything past ID 2999
     * is considered FAIR GAME, take whatever you like.
     *
     * If you would like to reserve IDs, feel free to reach out to the
     * development team and claim a range of IDs! We will mark any
     * claimed ranges below this comment. Max value is 32767.
     *
     * - Gregicality: 3000-19999
     * - FREE RANGE 20000-20999
     * - HtmlTech: 21000-21499
     * - GregTech Food Option: 21500-21999
     * - PCM's Ore Addon: 22000-23599
     * - MechTech: 23600-23999
     * - FREE RANGE 24000-31999
     * - Reserved for CraftTweaker: 32000-32767
     */

    // Element Materials

    // First Degree Materials
    public static Material NM_HEA_NPs;
    public static Material NaquadahRocketFuel;
    public static Material SoulSand;
    public static Material ChorusFruit;
    public static Material ElectricalSteel;
    public static Material EnergeticAlloy;
    public static Material VibrantAlloy;
    public static Material RedstoneAlloy;
    public static Material ConductiveIron;
    public static Material PulsatingIron;
    public static Material DarkSteel;
    public static Material Soularium;
    public static Material EndSteel;
    public static Material ConstructionAlloy;
    public static Material CrystallineAlloy;
    public static Material MelodicAlloy;
    public static Material StellarAlloy;
    public static Material CrystallinePinkSlime;
    public static Material EnergeticSilver;
    public static Material VividAlloy;
    public static Material ChargedCertusQuartz;
    public static Material Fluix;
    public static Material FluixAlloy;
    public static Material Cryotheum;
    public static Material Pyrotheum;
    public static Material Dragon;
    public static Material Chaos;
    public static Material Draconium;
    public static Material AwakenedDraconium;
    public static Material Infinity;

    // Second Degree Materials

    // Third Degree Materials

    // Organic Chemistry Materials

    // Unknown Composition Materials

    public static void registerMaterialsHigh() {
        GTEMaterialFlags.init();
    }

    public static void registerMaterialsLow() {
        GTEFirstDegreeMaterials.init(); // 24001 - 24100
    }

    public static void registerMaterialsLowest() {
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            EIOFirstDegreeMaterials.init(); // 24101 - 24150
        }
        if (Loader.isModLoaded(GTEValues.MODID_AE)) {
            AEFirstDegreeMaterials.init();  // 24151 - 24175
        }
        if (GTEValues.isModLoadedDEDA()) {
            DEFirstDegreeMaterials.init(); // 24176 - 24185
        }
        if (Loader.isModLoaded(GTEValues.MODID_AVARITIA)) {
            AvaritiaFirstDegreeMaterials.init(); // 24186 - 24190
        }
    }
}
