package gtexpert.common.metatileentities;

import static gregtech.api.GTValues.V;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntities;
import static gtexpert.api.util.GTEUtility.gteId;
import static gtexpert.common.metatileentities.MetaTileEntitiesManager.registerGTESimpleMetaTileEntity;

import gregtech.api.util.GTUtility;

import gtexpert.core.GTERecipeMaps;
import gtexpert.api.util.GTEUtility;
import gtexpert.client.GTETextures;
import gtexpert.common.metatileentities.single.electric.MetaTileEntityElectricSpawner;

public class EIOMetaTileEntities {

    public static GTESimpleMachineMetaTileEntity[] VIAL_EXTRACTOR = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static GTESimpleMachineMetaTileEntity[] SLICE_N_SPLICE = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static GTESimpleMachineMetaTileEntity[] SOUL_BINDER = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static MetaTileEntityElectricSpawner[] ELECTRIC_SPAWNER = new MetaTileEntityElectricSpawner[V.length - 1];

    public static void init() {
        // VIAL_EXTRACTOR 11010~11022
        registerGTESimpleMetaTileEntity(VIAL_EXTRACTOR, 11010, "vial_extractor", GTERecipeMaps.VIAL_EXTRACTOR_RECIPES,
                GTETextures.VIAL_EXTRACTOR_OVERLAY, true, GTEUtility::gteId, GTUtility.hvCappedTankSizeFunction);

        // SLICE_N_SPLICE 11023~11035
        registerGTESimpleMetaTileEntity(SLICE_N_SPLICE, 11023, "slice_n_splice", GTERecipeMaps.SLICE_N_SPLICE_RECIPES,
                GTETextures.SLICE_N_SPLICE_OVERLAY, true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // SOUL_BINDER 11036~11048
        registerGTESimpleMetaTileEntity(SOUL_BINDER, 11036, "soul_binder", GTERecipeMaps.SOUL_BINDER_RECIPES,
                GTETextures.SOUL_BINDER_OVERLAY, true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // ELECTRIC_SPAWNER 11049~11061
        registerMetaTileEntities(ELECTRIC_SPAWNER, 11049, "electric_spawner",
                (tier, voltageName) -> new MetaTileEntityElectricSpawner(
                        gteId(String.format("%s.%s", "electric_spawner", voltageName)),
                        GTETextures.SPAWNER_OVERLAY,
                        tier));
    }
}
