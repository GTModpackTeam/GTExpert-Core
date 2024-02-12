package gtexpert.core.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gtexpert.api.util.GTEUtility.gteId;

import java.util.function.Function;

import net.minecraft.util.ResourceLocation;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;

import gtexpert.core.GTEConfigHolder;
import gtexpert.core.metatileentities.multi.*;
import gtexpert.core.metatileentities.single.SteamAssembler;
import gtexpert.core.metatileentities.single.SteamCircuitAssembler;
import gtexpert.core.metatileentities.single.SteamMixer;

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
        // Steam machine 11004~11009
        if (GTEConfigHolder.gteFlag.steamNewMachine) {
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

        // Multi Machine
        SAWMILL = registerMetaTileEntity(12001,
                new MetaTileEntitySawmill(gteId("sawmill")));
        LARGE_CRACKER = registerMetaTileEntity(12002,
                new MetaTileEntityLargeCrackingUnit(gteId("large_cracking_unit")));
        VOIDOREMINER = registerMetaTileEntity(12003,
                new MetaTileEntityVoidOreMiner(gteId("void_ore_miner")));
        // 12004~12005 is reserved for Draconic Evolution
        ADVANCED_CHEMICAL_PLANT = registerMetaTileEntity(12006,
                new MetaTileEntityAdvancedChemicalPlant(gteId("advanced_chemical_plant")));
        LARGE_GAS_COLLECTOR = registerMetaTileEntity(12007,
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
