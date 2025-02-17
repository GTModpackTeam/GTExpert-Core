package com.github.gtexpert.core.integration.eio.metatileentities;

import static com.github.gtexpert.core.api.util.GTEUtility.gteId;
import static com.github.gtexpert.core.common.metatileentities.GTEMetaTileEntities.registerGTESimpleMetaTileEntity;
import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntities;

import gregtech.api.util.GTUtility;

import com.github.gtexpert.core.api.util.GTEUtility;
import com.github.gtexpert.core.client.GTETextures;
import com.github.gtexpert.core.common.metatileentities.GTESimpleMachineMetaTileEntity;
import com.github.gtexpert.core.integration.eio.EnderIORecipeMaps;

public class EIOMetaTileEntities {

    public static GTESimpleMachineMetaTileEntity[] VIAL_EXTRACTOR = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static GTESimpleMachineMetaTileEntity[] SLICE_N_SPLICE = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static GTESimpleMachineMetaTileEntity[] SOUL_BINDER = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static MetaTileEntityElectricSpawner[] ELECTRIC_SPAWNER = new MetaTileEntityElectricSpawner[V.length - 1];

    public static void init() {
        // VIAL_EXTRACTOR 11010~11022
        registerGTESimpleMetaTileEntity(VIAL_EXTRACTOR, 11010, "vial_extractor",
                EnderIORecipeMaps.VIAL_EXTRACTOR_RECIPES,
                GTETextures.VIAL_EXTRACTOR_OVERLAY, true, GTEUtility::gteId, GTUtility.hvCappedTankSizeFunction);

        // SLICE_N_SPLICE 11023~11035
        registerGTESimpleMetaTileEntity(SLICE_N_SPLICE, 11023, "slice_n_splice",
                EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES,
                GTETextures.SLICE_N_SPLICE_OVERLAY, true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // SOUL_BINDER 11036~11048
        registerGTESimpleMetaTileEntity(SOUL_BINDER, 11036, "soul_binder", EnderIORecipeMaps.SOUL_BINDER_RECIPES,
                GTETextures.SOUL_BINDER_OVERLAY, true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // ELECTRIC_SPAWNER 11049~11061
        registerMetaTileEntities(ELECTRIC_SPAWNER, 11049, "electric_spawner",
                (tier, voltageName) -> new MetaTileEntityElectricSpawner(
                        gteId(String.format("%s.%s", "electric_spawner", voltageName)),
                        GTETextures.SPAWNER_OVERLAY,
                        tier));
    }
}
