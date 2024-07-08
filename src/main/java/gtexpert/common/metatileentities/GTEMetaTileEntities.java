package gtexpert.common.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gtexpert.api.util.GTEUtility.gteId;

import java.util.function.Function;

import net.minecraft.util.ResourceLocation;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;

import gtexpert.common.GTEConfigHolder;
import gtexpert.common.metatileentities.multi.*;
import gtexpert.common.metatileentities.single.SteamAssembler;
import gtexpert.common.metatileentities.single.SteamCircuitAssembler;
import gtexpert.common.metatileentities.single.SteamMixer;

public class GTEMetaTileEntities {

    // Single Machine
    public static SteamMixer STEAM_MIXER_BRONZE;
    public static SteamMixer STEAM_MIXER_STEEL;
    public static SteamAssembler STEAM_ASSEMBLER_BRONZE;
    public static SteamAssembler STEAM_ASSEMBLER_STEEL;
    public static SteamCircuitAssembler STEAM_CIRCUIT_ASSEMBLER_BRONZE;
    public static SteamCircuitAssembler STEAM_CIRCUIT_ASSEMBLER_STEEL;

    // Multi Machine
    public static MetaTileEntitySawmill SAWMILL;
    public static MetaTileEntityLargeCrackingUnit LARGE_CRACKER;
    public static MetaTileEntityVoidOreMiner VOIDOREMINER;
    public static MetaTileEntityAdvancedChemicalPlant ADVANCED_CHEMICAL_PLANT;
    public static MetaTileEntityLargeGasCollector LARGE_GAS_COLLECTOR;

    public static void init() {
        // Single Machine
        // Steam machine 0~5
        if (GTEConfigHolder.gteFlag.steamNewMachine) {
            STEAM_MIXER_BRONZE = registerMetaTileEntity(0,
                    new SteamMixer(gteId("steam_mixer_bronze"), false));
            STEAM_MIXER_STEEL = registerMetaTileEntity(1,
                    new SteamMixer(gteId("steam_mixer_steel"), true));
            STEAM_ASSEMBLER_BRONZE = registerMetaTileEntity(2,
                    new SteamAssembler(gteId("steam_assembler_bronze"), false));
            STEAM_ASSEMBLER_STEEL = registerMetaTileEntity(3,
                    new SteamAssembler(gteId("steam_assembler_steel"), true));
            STEAM_CIRCUIT_ASSEMBLER_BRONZE = registerMetaTileEntity(4,
                    new SteamCircuitAssembler(gteId("steam_circuit_assembler_bronze"), false));
            STEAM_CIRCUIT_ASSEMBLER_STEEL = registerMetaTileEntity(5,
                    new SteamCircuitAssembler(gteId("steam_circuit_assembler_steel"), true));
        }

        // Multi Machine
        SAWMILL = registerMetaTileEntity(1000,
                new MetaTileEntitySawmill(gteId("sawmill")));
        LARGE_CRACKER = registerMetaTileEntity(1001,
                new MetaTileEntityLargeCrackingUnit(gteId("large_cracking_unit")));
        VOIDOREMINER = registerMetaTileEntity(1002,
                new MetaTileEntityVoidOreMiner(gteId("void_ore_miner")));
        // 12004~12005 is reserved for Draconic Evolution
        ADVANCED_CHEMICAL_PLANT = registerMetaTileEntity(1003,
                new MetaTileEntityAdvancedChemicalPlant(gteId("advanced_chemical_plant")));
        LARGE_GAS_COLLECTOR = registerMetaTileEntity(1004,
                new MetaTileEntityLargeGasCollector(gteId(GTEConfigHolder.gteFlag.featureFlag ?
                        "large_gas_collector" : "advanced_gas_collector")));
    }

    public static void registerGTESimpleMetaTileEntity(GTESimpleMachineMetaTileEntity[] machines, int startId,
                                                       String name, RecipeMap<?> map, ICubeRenderer texture,
                                                       boolean hasFrontFacing,
                                                       Function<String, ResourceLocation> resourceId,
                                                       Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i < machines.length - 1; ++i) {
            if (i <= 4 || getMidTier(name)) {
                if (i > 7 && !getHighTier(name)) {
                    break;
                }

                String voltageName = GTValues.VN[i + 1].toLowerCase();
                machines[i + 1] = registerMetaTileEntity(startId + i,
                        new GTESimpleMachineMetaTileEntity(resourceId.apply(String.format("%s.%s", name, voltageName)),
                                map, texture, i + 1, hasFrontFacing, tankScalingFunction));
            }
        }
    }
}
