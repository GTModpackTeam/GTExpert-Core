package com.github.gtexpert.core.integration.chisel.metatileentities;

import static com.github.gtexpert.core.api.util.GTEUtility.gteId;
import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

import com.github.gtexpert.core.client.GTETextures;
import com.github.gtexpert.core.integration.chisel.ChiselRecipeMaps;

import gregtech.api.util.GTUtility;

public class ChiselMetaTileEntities {

    public static MetaTileEntityAutoChisel[] AUTO_CHISEL = new MetaTileEntityAutoChisel[3];

    public static void init() {
        // Auto Chisel 11001~11003
        AUTO_CHISEL[0] = registerMetaTileEntity(11001,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.lv"), ChiselRecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, LV, true, GTUtility.defaultTankSizeFunction));
        AUTO_CHISEL[1] = registerMetaTileEntity(11002,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.mv"), ChiselRecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, MV, true, GTUtility.defaultTankSizeFunction));
        AUTO_CHISEL[2] = registerMetaTileEntity(11003,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.hv"), ChiselRecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, HV, true, GTUtility.defaultTankSizeFunction));
    }
}
