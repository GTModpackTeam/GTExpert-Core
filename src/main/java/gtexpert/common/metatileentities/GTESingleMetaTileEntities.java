package gtexpert.common.metatileentities;

import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gtexpert.api.util.GTEUtility.gteId;

import gregtech.api.util.GTUtility;

import gtexpert.client.GTETextures;
import gtexpert.common.metatileentities.single.electric.*;
import gtexpert.common.metatileentities.single.steam.*;
import gtexpert.integration.chisel.ChiselRecipeMaps;

public class GTESingleMetaTileEntities {

    public static MetaTileEntityAutoChisel[] AUTO_CHISEL = new MetaTileEntityAutoChisel[3];
    public static SteamMixer STEAM_MIXER_BRONZE;
    public static SteamMixer STEAM_MIXER_STEEL;
    public static SteamAssembler STEAM_ASSEMBLER_BRONZE;
    public static SteamAssembler STEAM_ASSEMBLER_STEEL;
    public static SteamCircuitAssembler STEAM_CIRCUIT_ASSEMBLER_BRONZE;
    public static SteamCircuitAssembler STEAM_CIRCUIT_ASSEMBLER_STEEL;

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

        // Steam machine 11004~11009
        STEAM_MIXER_BRONZE = registerMetaTileEntity(11004,
                new SteamMixer(gteId("steam_mixer_bronze"), false));
        STEAM_MIXER_STEEL = registerMetaTileEntity(11005,
                new SteamMixer(gteId("steam_mixer_steel"), true));
        STEAM_ASSEMBLER_BRONZE = registerMetaTileEntity(11006,
                new SteamAssembler(gteId("steam_assembler_bronze"), false));
        STEAM_ASSEMBLER_STEEL = registerMetaTileEntity(11007,
                new SteamAssembler(gteId("steam_assembler_steel"), true));
        STEAM_CIRCUIT_ASSEMBLER_BRONZE = registerMetaTileEntity(11008,
                new SteamCircuitAssembler(gteId("steam_circuit_assembler_bronze"), false));
        STEAM_CIRCUIT_ASSEMBLER_STEEL = registerMetaTileEntity(11009,
                new SteamCircuitAssembler(gteId("steam_circuit_assembler_steel"), true));
    }
}
