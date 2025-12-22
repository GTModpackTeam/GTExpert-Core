package com.github.gtexpert.core.api.unification.material.ingredients;

import static com.github.gtexpert.core.api.unification.material.GTEMaterials.*;
import static com.github.gtexpert.core.api.util.GTEUtility.gteId;
import static gregicality.multiblocks.api.unification.GCYMMaterialFlags.NO_ALLOY_BLAST_RECIPES;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;
import gregtech.api.unification.ore.OrePrefix;

import com.github.gtexpert.core.api.GTEValues;

public class AEFirstDegreeMaterials {

    /**
     * 24151 - 24175
     */
    public static void init() {
        // Certus Quartz
        OrePrefix.block.modifyMaterialAmount(CertusQuartz, 4);

        // Charged Certus Quartz
        ChargedCertusQuartz = new Material.Builder(24151, gteId("charged_certus_quartz"))
                .dust()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xCFDAFF).iconSet(MaterialIconSet.CERTUS)
                .flags(GENERATE_PLATE, GENERATE_LENS, DISABLE_DECOMPOSITION)
                .components(Silicon, 1, Oxygen, 2)
                .build();
        OrePrefix.block.modifyMaterialAmount(ChargedCertusQuartz, 4);

        // Fluix
        Fluix = new Material.Builder(24152, gteId("fluix"))
                .dust()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x846994).iconSet(MaterialIconSet.CERTUS)
                .flags(GENERATE_PLATE, GENERATE_LENS, DISABLE_DECOMPOSITION)
                .components(Silicon, 2, Oxygen, 4, Redstone, 1)
                .build();
        OrePrefix.block.modifyMaterialAmount(Fluix, 4);

        // Fluix Alloy
        FluixAlloy = new Material.Builder(24153, gteId("fluix_alloy"))
                .ingot()
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x4A3954).iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES)
                .components(Fluix, 2, Carbon, 2, Silicon, 1, Iron, 1)
                .blast(b -> b
                        .temp(2700, GasTier.LOW)
                        .blastStats(VA[GTEValues.ae2VoltageTier], 1072)
                        .vacuumStats(VA[GTEValues.ae2VoltageTier], 268))
                .build();
        FluixAlloy.setFormula(FluixAlloy.getChemicalFormula() + "?", true);
    }
}
