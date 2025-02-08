package com.github.gtexpert.core.common.metatileentities;

import static com.github.gtexpert.core.api.util.GTEUtility.gteId;
import static gregtech.common.metatileentities.MetaTileEntities.*;

import com.github.gtexpert.core.common.GTEConfigHolder;
import com.github.gtexpert.core.common.metatileentities.multi.*;

public class GTEMetaTileEntities {

    // Single Machine

    // Multi Machine
    public static MetaTileEntityLargeCrackingUnit LARGE_CRACKER;
    public static MetaTileEntityVoidOreMiner VOIDOREMINER;
    public static MetaTileEntityAdvancedChemicalPlant ADVANCED_CHEMICAL_PLANT;
    public static MetaTileEntityLargeGasCollector LARGE_GAS_COLLECTOR;

    public static void init() {
        // Single Machine
        // Free: 11004~11999

        // Multi Machine
        // Free: 12000~12001
        LARGE_CRACKER = registerMetaTileEntity(12002,
                new MetaTileEntityLargeCrackingUnit(gteId("large_cracking_unit")));

        // Void Ore Miner
        VOIDOREMINER = registerMetaTileEntity(12003,
                new MetaTileEntityVoidOreMiner(gteId("void_ore_miner")));

        // Advanced Chemical Plant
        ADVANCED_CHEMICAL_PLANT = registerMetaTileEntity(12006,
                new MetaTileEntityAdvancedChemicalPlant(gteId("advanced_chemical_plant")));

        // Large Gas Collector
        LARGE_GAS_COLLECTOR = registerMetaTileEntity(12007,
                new MetaTileEntityLargeGasCollector(gteId(GTEConfigHolder.gteFeatureFlag.migrationMachine ?
                        "large_gas_collector" : "advanced_gas_collector")));
    }
}
