package gtexpert.api.unification.material;

import gregtech.api.unification.material.Material;

/**
 * Material Registration.
 * <p>
 * All Material Builders should follow this general formatting:
 * <p>
 * material = new MaterialBuilder(id, name)
 * .ingot().fluid().ore()                <--- types
 * .color().iconSet()                    <--- appearance
 * .flags()                              <--- special generation
 * .element() / .components()            <--- composition
 * .toolStats()                          <---
 * .oreByProducts()                         | additional properties
 * ...                                   <---
 * .blastTemp()                          <--- blast temperature
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
    public static Material Galvalume;
    public static Material NM_HEA_NPs;
    public static Material NAQUADAH_ROCKET_FUEL;
    public static Material SOUL_SAND;
    public static Material CHORUS_FRUIT;
    public static Material ELECTRICAL_STEEL;
    public static Material ENERGETIC_ALLOY;
    public static Material VIBRANT_ALLOY;
    public static Material REDSTONE_ALLOY;
    public static Material CONDUCTIVE_IRON;
    public static Material PULSATING_IRON;
    public static Material DARK_STEEL;
    public static Material SOULARIUM;
    public static Material END_STEEL;
    public static Material CONSTRUCTION_ALLOY;
    public static Material CRYSTALLINE_ALLOY;
    public static Material MELODIC_ALLOY;
    public static Material STELLAR_ALLOY;
    public static Material CRYSTALLINE_PINK_SLIME;
    public static Material ENERGETIC_SILVER;
    public static Material VIVID_ALLOY;
    public static Material FLUIX;
    public static Material FLUIX_ALLOY;
    public static Material CHARGED_CERTUS_QUARTZ;

    // Second Degree Materials

    // Third Degree Materials

    // Organic Chemistry Materials

    // Unknown Composition Materials

    public static void init() {
        GTEFirstDegreeMaterials.init(); // 24001 - 24100
        EIOFirstDegreeMaterials.init(); // 25001 - 25100
        AEFirstDegreeMaterials.init();  // 25101 - 25200
    }
}
