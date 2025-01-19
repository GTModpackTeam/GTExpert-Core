package com.github.gtexpert.core.integration.gendustry.metatileentities;

import static gregtech.api.GTValues.V;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntities;
import static com.github.gtexpert.core.api.util.GTEUtility.gteId;

import com.github.gtexpert.core.client.GTETextures;
import com.github.gtexpert.core.common.GTEConfigHolder;
import com.github.gtexpert.core.integration.gendustry.GendustryRecipeMaps;

public class GendustryMetaTileEntities {

    public static MetaTileEntityIndustrialApiary[] INDUSTRIAL_APIARY = new MetaTileEntityIndustrialApiary[V.length - 1];

    public static void init() {
        // INDUSTRIAL_APIARY 20000~20012
        // TODO: IDの変更

        if (GTEConfigHolder.gteFlag.featureFlag) {
            registerMetaTileEntities(INDUSTRIAL_APIARY, 20000, "industrial_apiary",
                    (tier, voltageName) -> new MetaTileEntityIndustrialApiary(
                            gteId(String.format("%s.%s", "industrial_apiary", voltageName)),
                            GendustryRecipeMaps.INDUSTRIAL_APIARY_RECIPES,
                            GTETextures.INDUSTRIAL_APIARY_OVERLAY,
                            tier, false, null));
        }
    }
}
