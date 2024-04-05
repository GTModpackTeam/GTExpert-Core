package gtexpert.integration.gendustry.metatileentities;

import static gregtech.api.GTValues.V;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntities;
import static gtexpert.api.util.GTEUtility.gteId;

import gtexpert.client.GTETextures;
import gtexpert.core.GTEConfigHolder;
import gtexpert.integration.gendustry.recipemaps.GendustryRecipeMaps;

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
