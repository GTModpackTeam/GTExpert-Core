package gtexpert.common.metatileentities;

import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gtexpert.api.util.GTEUtility.gteId;

import gregtech.api.util.GTUtility;

import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.metatileentities.single.*;

public class GTESingleMetaTileEntities {

    public static MetaTileEntityAutoChisel[] AUTO_CHISEL = new MetaTileEntityAutoChisel[3];
    public static PrimitiveMixer PRIMITIVE_MIXER_BRONZE;
    public static PrimitiveMixer PRIMITIVE_MIXER_STEEL;
    public static PrimitiveAssembler PRIMITIVE_ASSEMBLER_BRONZE;
    public static PrimitiveAssembler PRIMITIVE_ASSEMBLER_STEEL;
    public static PrimitiveCircuitAssembler PRIMITIVE_CIRCUIT_ASSEMBLER_BRONZE;
    public static PrimitiveCircuitAssembler PRIMITIVE_CIRCUIT_ASSEMBLER_STEEL;

    public static void init() {
        // Auto Chisel 11001~11003
        AUTO_CHISEL[0] = registerMetaTileEntity(11001,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.lv"), GTERecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, LV, true, GTUtility.defaultTankSizeFunction));
        AUTO_CHISEL[1] = registerMetaTileEntity(11002,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.mv"), GTERecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, MV, true, GTUtility.defaultTankSizeFunction));
        AUTO_CHISEL[2] = registerMetaTileEntity(11003,
                new MetaTileEntityAutoChisel(gteId("auto_chisel.hv"), GTERecipeMaps.AUTO_CHISEL_RECIPES,
                        GTETextures.AUTO_CHISEL_OVERLAY, HV, true, GTUtility.defaultTankSizeFunction));

        // Primitive machine 11004~11009
        PRIMITIVE_MIXER_BRONZE = registerMetaTileEntity(11004,
                new PrimitiveMixer(gteId("primitive_mixer_bronze"), false));
        PRIMITIVE_MIXER_STEEL = registerMetaTileEntity(11005,
                new PrimitiveMixer(gteId("primitive_mixer_steel"), true));
        PRIMITIVE_ASSEMBLER_BRONZE = registerMetaTileEntity(11006,
                new PrimitiveAssembler(gteId("primitive_assembler_bronze"), false));
        PRIMITIVE_ASSEMBLER_STEEL = registerMetaTileEntity(11007,
                new PrimitiveAssembler(gteId("primitive_assembler_steel"), true));
        PRIMITIVE_CIRCUIT_ASSEMBLER_BRONZE = registerMetaTileEntity(11008,
                new PrimitiveCircuitAssembler(gteId("primitive_circuit_assembler_bronze"), false));
        PRIMITIVE_CIRCUIT_ASSEMBLER_STEEL = registerMetaTileEntity(11009,
                new PrimitiveCircuitAssembler(gteId("primitive_circuit_assembler_steel"), true));
    }
}
