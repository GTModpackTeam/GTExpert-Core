package gtexpert.integration.deda.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gtexpert.api.util.GTEUtility.gteId;

import gtexpert.common.GTEConfigHolder;

public class DEDAMetaTileEntities {

    public static MetaTileEntityDraconiumFusion DRACONIUM_FUSION;
    public static MetaTileEntityDraconiumFusion AWAKENED_DRACONIUM_FUSION;

    public static void init() {
        int mteStartId = GTEConfigHolder.gteFeatureFlag.newId ? 2000 : 12004;

        DRACONIUM_FUSION = registerMetaTileEntity(mteStartId,
                new MetaTileEntityDraconiumFusion.TierDraconic(gteId("draconium_fusion")));
        AWAKENED_DRACONIUM_FUSION = registerMetaTileEntity(mteStartId + 1,
                new MetaTileEntityDraconiumFusion.TierAwakened(gteId("awakened_draconium_fusion")));
    }
}
