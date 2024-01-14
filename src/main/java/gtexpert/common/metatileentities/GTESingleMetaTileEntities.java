package gtexpert.common.metatileentities;

import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gtexpert.api.util.GTEUtility.gteId;

import gregtech.api.util.GTUtility;

import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.metatileentities.single.*;

public class GTESingleMetaTileEntities {

    public static final MetaTileEntityAutoChisel[] AUTO_CHISEL = new MetaTileEntityAutoChisel[3];

    public static void init() {
        // AUTO_CHISEL 11001~11003
        AUTO_CHISEL[0] = registerMetaTileEntity(11001,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.lv"), GTERecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, LV, true, GTUtility.defaultTankSizeFunction));
        AUTO_CHISEL[1] = registerMetaTileEntity(11002,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.mv"), GTERecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, MV, true, GTUtility.defaultTankSizeFunction));
        AUTO_CHISEL[2] = registerMetaTileEntity(11003,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.hv"), GTERecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, HV, true, GTUtility.defaultTankSizeFunction));

        // FreeSpace 11004~11009
    }
}
