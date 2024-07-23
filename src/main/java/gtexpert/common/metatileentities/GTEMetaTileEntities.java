package gtexpert.common.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gtexpert.api.util.GTEUtility.gteId;

import java.util.function.Function;

import net.minecraft.util.ResourceLocation;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;

import gtexpert.api.util.GTEUtility;
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
        int mteSingleStartId = GTEConfigHolder.gteFeatureFlag.newId ? 0 : 11004;
        int mteMultiStartId = GTEConfigHolder.gteFeatureFlag.newId ? 1000 : 12001;

        // Steam machines
        if (GTEConfigHolder.gteFlag.steamNewMachine) {
            STEAM_MIXER_BRONZE = registerMetaTileEntity(mteSingleStartId,
                    new SteamMixer(gteId("steam_mixer_bronze"), false));
            STEAM_MIXER_STEEL = registerMetaTileEntity(mteSingleStartId += 1,
                    new SteamMixer(gteId("steam_mixer_steel"), true));
            STEAM_ASSEMBLER_BRONZE = registerMetaTileEntity(mteSingleStartId += 1,
                    new SteamAssembler(gteId("steam_assembler_bronze"), false));
            STEAM_ASSEMBLER_STEEL = registerMetaTileEntity(mteSingleStartId += 1,
                    new SteamAssembler(gteId("steam_assembler_steel"), true));
            STEAM_CIRCUIT_ASSEMBLER_BRONZE = registerMetaTileEntity(mteSingleStartId += 1,
                    new SteamCircuitAssembler(gteId("steam_circuit_assembler_bronze"), false));
            STEAM_CIRCUIT_ASSEMBLER_STEEL = registerMetaTileEntity(mteSingleStartId + 1,
                    new SteamCircuitAssembler(gteId("steam_circuit_assembler_steel"), true));
        }

        // Sawmill
        SAWMILL = registerMetaTileEntity(mteMultiStartId,
                new MetaTileEntitySawmill(gteId("sawmill")));

        // Large Cracking Unit
        LARGE_CRACKER = registerMetaTileEntity(mteMultiStartId += 1,
                new MetaTileEntityLargeCrackingUnit(gteId("large_cracking_unit")));

        // Void Ore Miner
        VOIDOREMINER = registerMetaTileEntity(mteMultiStartId += 1,
                new MetaTileEntityVoidOreMiner(gteId("void_ore_miner")));

        // Advanced Chemical Plant
        mteMultiStartId = GTEConfigHolder.gteFeatureFlag.newId ? mteMultiStartId + 1 : 12006;
        ADVANCED_CHEMICAL_PLANT = registerMetaTileEntity(mteMultiStartId,
                new MetaTileEntityAdvancedChemicalPlant(gteId("advanced_chemical_plant")));

        // Large Gas Collector
        LARGE_GAS_COLLECTOR = registerMetaTileEntity(mteMultiStartId + 1,
                new MetaTileEntityLargeGasCollector(gteId(GTEConfigHolder.gteFeatureFlag.migrationMachine ?
                        "large_gas_collector" : "advanced_gas_collector")));
    }

    public static void registerGTESimpleMetaTileEntity(GTESimpleMachineMetaTileEntity[] machines,
                                                       int startId,
                                                       String name,
                                                       RecipeMap<?> map,
                                                       ICubeRenderer texture,
                                                       boolean hasFrontFacing) {
        registerGTESimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing, GTEUtility::gteId,
                GTUtility.defaultTankSizeFunction);
    }

    public static void registerGTESimpleMetaTileEntity(GTESimpleMachineMetaTileEntity[] machines,
                                                       int startId,
                                                       String name,
                                                       RecipeMap<?> map,
                                                       ICubeRenderer texture,
                                                       boolean hasFrontFacing,
                                                       Function<Integer, Integer> tankScalingFunction) {
        registerGTESimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing, GTEUtility::gteId,
                tankScalingFunction);
    }

    public static void registerGTESimpleMetaTileEntity(GTESimpleMachineMetaTileEntity[] machines, int startId,
                                                       String name, RecipeMap<?> map, ICubeRenderer texture,
                                                       boolean hasFrontFacing,
                                                       Function<String, ResourceLocation> resourceId,
                                                       Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i < machines.length - 1; ++i) {
            if (i <= 4 || getMidTier(name)) continue;
            if (i > 7 && !getHighTier(name)) break;

            String voltageName = GTValues.VN[i + 1].toLowerCase();
            machines[i + 1] = registerMetaTileEntity(startId + i,
                    new GTESimpleMachineMetaTileEntity(resourceId.apply(String.format("%s.%s", name, voltageName)),
                            map, texture, i + 1, hasFrontFacing, tankScalingFunction));
        }
    }
}
