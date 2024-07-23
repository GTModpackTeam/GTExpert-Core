package gtexpert.integration.gendustry.metatileentities;

import static gregtech.api.GTValues.V;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntities;
import static gtexpert.api.util.GTEUtility.gteId;

import gtexpert.client.GTETextures;
import gtexpert.common.GTEConfigHolder;

public class GendustryMetaTileEntities {

    public static MetaTileEntityIndustrialApiary[] INDUSTRIAL_APIARY = new MetaTileEntityIndustrialApiary[V.length - 1];

    public static void init() {
        int mteStartId = GTEConfigHolder.gteFeatureFlag.newId ? 300 : 20000;

        // Industrial Apiary
        if (GTEConfigHolder.gteFeatureFlag.previewMachines) {
            registerMetaTileEntities(INDUSTRIAL_APIARY, mteStartId, "industrial_apiary",
                    (tier, voltageName) -> new MetaTileEntityIndustrialApiary(
                            gteId(String.format("%s.%s", "industrial_apiary", voltageName)),
                            GTETextures.INDUSTRIAL_APIARY_OVERLAY,
                            tier));
        }
    }
}
