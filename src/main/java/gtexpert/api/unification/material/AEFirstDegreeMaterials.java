package gtexpert.api.unification.material;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.common.GTEConfigHolder.ae2Integration;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;
import gregtech.api.unification.ore.OrePrefix;

public class AEFirstDegreeMaterials {

    /**
     * 24151 - 24175
     */
    public static void init() {
        // Charged Certus Quartz
        ChargedCertusQuartz = new Material.Builder(24151, gregtechId("charged_certus_quartz"))
                .dust()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xCFDAFF).iconSet(MaterialIconSet.CERTUS)
                .flags(GENERATE_PLATE, GENERATE_LENS, DISABLE_DECOMPOSITION)
                .components(Silicon, 1, Oxygen, 2)
                .build();
        OrePrefix.block.modifyMaterialAmount(ChargedCertusQuartz, 4);

        // Fluix
        Fluix = new Material.Builder(24152, gregtechId("fluix"))
                .dust()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x846994).iconSet(MaterialIconSet.CERTUS)
                .flags(GENERATE_PLATE, GENERATE_LENS, DISABLE_DECOMPOSITION)
                .components(Silicon, 2, Oxygen, 4, Redstone, 1)
                .build();
        OrePrefix.block.modifyMaterialAmount(Fluix, 4);

        // Fluix Alloy
        FluixAlloy = new Material.Builder(24153, gregtechId("fluix_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x4A3954).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Fluix, 2, Carbon, 2, Silicon, 1, Iron, 1)
                .blast(b -> b
                        .temp(2700, GasTier.LOW)
                        .blastStats(VA[ae2Integration.voltageTier], 1072)
                        .vacuumStats(VA[ae2Integration.voltageTier], 268))
                .build();
    }
}
