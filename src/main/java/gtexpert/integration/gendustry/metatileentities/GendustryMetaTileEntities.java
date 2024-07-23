package gtexpert.integration.gendustry.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gtexpert.api.util.GTEUtility.gteId;

import gregtech.api.GTValues;

import gtexpert.client.GTETextures;
import gtexpert.common.GTEConfigHolder;
import gtexpert.common.metatileentities.GTESimpleMachineMetaTileEntity;
import gtexpert.integration.chisel.metatileentities.MetaTileEntityAutoChisel;
import gtexpert.integration.gendustry.api.recipes.GendustryRecipeMaps;

public class GendustryMetaTileEntities {

    public static GTESimpleMachineMetaTileEntity[] INDUSTRIAL_APIARY = new GTESimpleMachineMetaTileEntity[3];

    public static void init() {
        int mteStartId = GTEConfigHolder.gteFeatureFlag.newId ? 300 : 20000;

        // Industrial Apiary
        if (GTEConfigHolder.gteFeatureFlag.previewMachines) {
            INDUSTRIAL_APIARY[0] = registerMetaTileEntity(mteStartId,
                    new MetaTileEntityAutoChisel(gteId("industrial_apiary.ev"),
                            GendustryRecipeMaps.INDUSTRIAL_APIARY_RECIPES,
                            GTETextures.INDUSTRIAL_APIARY_OVERLAY,
                            GTValues.EV, true, null));
            INDUSTRIAL_APIARY[1] = registerMetaTileEntity(mteStartId += 1,
                    new MetaTileEntityAutoChisel(gteId("industrial_apiary.iv"),
                            GendustryRecipeMaps.INDUSTRIAL_APIARY_RECIPES,
                            GTETextures.INDUSTRIAL_APIARY_OVERLAY,
                            GTValues.IV, true, null));
            INDUSTRIAL_APIARY[2] = registerMetaTileEntity(mteStartId + 1,
                    new MetaTileEntityAutoChisel(gteId("industrial_apiary.luv"),
                            GendustryRecipeMaps.INDUSTRIAL_APIARY_RECIPES,
                            GTETextures.INDUSTRIAL_APIARY_OVERLAY,
                            GTValues.LuV, true, null));
        }
    }
}
