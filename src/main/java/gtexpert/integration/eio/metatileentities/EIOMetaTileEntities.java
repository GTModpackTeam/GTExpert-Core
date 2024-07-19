package gtexpert.integration.eio.metatileentities;

import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntities;
import static gtexpert.api.util.GTEUtility.gteId;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.registerGTESimpleMetaTileEntity;

import gregtech.api.util.GTUtility;

import gtexpert.api.util.GTEUtility;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEConfigHolder;
import gtexpert.common.metatileentities.GTESimpleMachineMetaTileEntity;
import gtexpert.integration.eio.EnderIORecipeMaps;

public class EIOMetaTileEntities {

    public static GTESimpleMachineMetaTileEntity[] VIAL_EXTRACTOR = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static GTESimpleMachineMetaTileEntity[] SLICE_N_SPLICE = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static GTESimpleMachineMetaTileEntity[] SOUL_BINDER = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static MetaTileEntityElectricSpawner[] ELECTRIC_SPAWNER = new MetaTileEntityElectricSpawner[V.length - 1];

    public static void init() {
        int mteStartId = GTEConfigHolder.gteFeatureFlag.newId ? 200 : 11010;

        // Vial Extractor
        registerGTESimpleMetaTileEntity(VIAL_EXTRACTOR, mteStartId, "vial_extractor",
                EnderIORecipeMaps.VIAL_EXTRACTOR_RECIPES,
                GTETextures.VIAL_EXTRACTOR_OVERLAY, true, GTEUtility::gteId, GTUtility.hvCappedTankSizeFunction);

        // Slice N Splice
        registerGTESimpleMetaTileEntity(SLICE_N_SPLICE, mteStartId += 14, "slice_n_splice",
                EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES,
                GTETextures.SLICE_N_SPLICE_OVERLAY, true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // Soul Binder
        registerGTESimpleMetaTileEntity(SOUL_BINDER, mteStartId += 14, "soul_binder",
                EnderIORecipeMaps.SOUL_BINDER_RECIPES,
                GTETextures.SOUL_BINDER_OVERLAY, true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // Electric Spawner
        registerMetaTileEntities(ELECTRIC_SPAWNER, mteStartId + 14, "electric_spawner",
                (tier, voltageName) -> new MetaTileEntityElectricSpawner(
                        gteId(String.format("%s.%s", "electric_spawner", voltageName)),
                        GTETextures.SPAWNER_OVERLAY,
                        tier));
    }
}
