package com.github.gtexpert.core.integration.deda.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static com.github.gtexpert.core.api.util.GTEUtility.gteId;

public class DEDAMetaTileEntities {

    public static MetaTileEntityDraconiumFusion DRACONIUM_FUSION;
    public static MetaTileEntityDraconiumFusion AWAKENED_DRACONIUM_FUSION;

    public static void init() {
        DRACONIUM_FUSION = registerMetaTileEntity(12004,
                new MetaTileEntityDraconiumFusion.TierDraconic(gteId("draconium_fusion")));
        AWAKENED_DRACONIUM_FUSION = registerMetaTileEntity(12005,
                new MetaTileEntityDraconiumFusion.TierAwakened(gteId("awakened_draconium_fusion")));
    }
}
