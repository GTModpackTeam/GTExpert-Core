package gtexpert.integration.gendustry.metatileentities;

import static gregtech.api.GTValues.V;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.registerGTESimpleMetaTileEntity;

import gtexpert.api.util.GTEUtility;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEConfigHolder;
import gtexpert.integration.gendustry.GendustryRecipeMaps;

public class GendustryMetaTileEntities {

    public static MetaTileEntityIndustrialApiary[] INDUSTRIAL_APIARY = new MetaTileEntityIndustrialApiary[V.length - 1];

    public static void init() {
        int mteStartId = GTEConfigHolder.gteFeatureFlag.newId ? 300 : 20000;

        // Industrial Apiary
        if (GTEConfigHolder.gteFeatureFlag.previewMachines) {
            registerGTESimpleMetaTileEntity(INDUSTRIAL_APIARY, mteStartId, "industrial_apiary",
                    GendustryRecipeMaps.INDUSTRIAL_APIARY_RECIPES,
                    GTETextures.INDUSTRIAL_APIARY_OVERLAY, false, GTEUtility::gteId, null);
        }
    }
}
