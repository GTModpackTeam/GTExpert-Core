package com.github.gtexpert.core.common.metatileentities;

import static com.github.gtexpert.core.api.util.GTEUtility.gteId;
import static gregtech.common.metatileentities.MetaTileEntities.*;

import java.util.function.Function;

import net.minecraft.util.ResourceLocation;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;

import com.github.gtexpert.core.common.GTEConfigHolder;
import com.github.gtexpert.core.common.metatileentities.multi.*;

public class GTEMetaTileEntities {

    // Single Machine

    // Multi Machine
    public static MTELargeCrackingUnit LARGE_CRACKER;
    public static MTEVoidOreMiner VOIDOREMINER;
    public static MTEAdvancedChemicalPlant ADVANCED_CHEMICAL_PLANT;
    public static MTELargeGasCollector LARGE_GAS_COLLECTOR;

    public static void init() {
        // Single Machine
        // Free: 11004~11999

        // Multi Machine
        // Free: 12000~12001
        LARGE_CRACKER = registerMetaTileEntity(12002,
                new MTELargeCrackingUnit(gteId("large_cracking_unit")));
        VOIDOREMINER = registerMetaTileEntity(12003,
                new MTEVoidOreMiner(gteId("void_ore_miner")));
        // 12004~12005 is reserved for Draconic Evolution
        ADVANCED_CHEMICAL_PLANT = registerMetaTileEntity(12006,
                new MTEAdvancedChemicalPlant(gteId("advanced_chemical_plant")));
        LARGE_GAS_COLLECTOR = registerMetaTileEntity(12007,
                new MTELargeGasCollector(gteId(GTEConfigHolder.gteFlag.featureFlag ?
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
