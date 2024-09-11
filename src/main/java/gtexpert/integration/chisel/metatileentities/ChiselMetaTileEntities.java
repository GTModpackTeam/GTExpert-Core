package gtexpert.integration.chisel.metatileentities;

import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gtexpert.api.util.GTEUtility.gteId;

import gregtech.api.util.GTUtility;

import gtexpert.client.GTETextures;
import gtexpert.common.GTEConfigHolder;
import gtexpert.integration.chisel.ChiselRecipeMaps;

public class ChiselMetaTileEntities {

    public static MetaTileEntityAutoChisel[] AUTO_CHISEL = new MetaTileEntityAutoChisel[3];

    public static void init() {
        int mteStartId = GTEConfigHolder.gteFeatureFlag.newId ? 100 : 11001;

        AUTO_CHISEL[0] = registerMetaTileEntity(mteStartId,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.lv"), ChiselRecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, LV, true, GTUtility.defaultTankSizeFunction));
        AUTO_CHISEL[1] = registerMetaTileEntity(mteStartId += 1,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.mv"), ChiselRecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, MV, true, GTUtility.defaultTankSizeFunction));
        AUTO_CHISEL[2] = registerMetaTileEntity(mteStartId + 1,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.hv"), ChiselRecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, HV, true, GTUtility.defaultTankSizeFunction));
    }
}
