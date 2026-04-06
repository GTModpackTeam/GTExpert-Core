package com.github.gtexpert.core.integration.deda.metatileentities;

import static com.github.gtexpert.core.api.util.GTEUtility.gteId;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import com.github.gtexpert.core.integration.deda.DEDAConfigHolder;

public class DEDAMetaTileEntities {

    public static MetaTileEntityDraconiumFusion DRACONIUM_FUSION;
    public static MetaTileEntityDraconiumFusion AWAKENED_DRACONIUM_FUSION;

    public static void init() {
        int startId = DEDAConfigHolder.mteStartId;
        DRACONIUM_FUSION = registerMetaTileEntity(startId,
                new MetaTileEntityDraconiumFusion.TierDraconic(gteId("draconium_fusion")));
        AWAKENED_DRACONIUM_FUSION = registerMetaTileEntity(startId + 1,
                new MetaTileEntityDraconiumFusion.TierAwakened(gteId("awakened_draconium_fusion")));
    }
}
