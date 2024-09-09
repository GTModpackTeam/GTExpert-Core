package gtexpert.integration.eio.metatileentities;

import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntities;
import static gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity;
import static gtexpert.api.util.GTEUtility.gteId;

import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;

import gtexpert.api.util.GTEUtility;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEConfigHolder;
import gtexpert.integration.eio.EnderIORecipeMaps;

public class EIOMetaTileEntities {

    public static SimpleMachineMetaTileEntity[] VIAL_EXTRACTOR = new SimpleMachineMetaTileEntity[V.length - 1];
    public static SimpleMachineMetaTileEntity[] SLICE_N_SPLICE = new SimpleMachineMetaTileEntity[V.length - 1];
    public static SimpleMachineMetaTileEntity[] SOUL_BINDER = new SimpleMachineMetaTileEntity[V.length - 1];
    public static MetaTileEntityElectricSpawner[] ELECTRIC_SPAWNER = new MetaTileEntityElectricSpawner[V.length - 1];

    public static void init() {
        int mteStartId = GTEConfigHolder.gteFeatureFlag.newId ? 200 : 11010;

        // Vial Extractor
        registerSimpleMetaTileEntity(VIAL_EXTRACTOR, mteStartId, "vial_extractor",
                EnderIORecipeMaps.VIAL_EXTRACTOR_RECIPES,
                GTETextures.VIAL_EXTRACTOR_OVERLAY,
                true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // Slice N Splice
        registerSimpleMetaTileEntity(SLICE_N_SPLICE, mteStartId += 13, "slice_n_splice",
                EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES,
                GTETextures.SLICE_N_SPLICE_OVERLAY,
                true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // Soul Binder
        registerSimpleMetaTileEntity(SOUL_BINDER, mteStartId += 13, "soul_binder",
                EnderIORecipeMaps.SOUL_BINDER_RECIPES,
                GTETextures.SOUL_BINDER_OVERLAY,
                true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // Electric Spawner
        registerMetaTileEntities(ELECTRIC_SPAWNER, mteStartId + 13, "electric_spawner",
                (tier, voltageName) -> new MetaTileEntityElectricSpawner(
                        gteId(String.format("%s.%s", "electric_spawner", voltageName)),
                        GTETextures.SPAWNER_OVERLAY,
                        tier));
    }
}
