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
    public static MetaTileEntityLargeRockBreaker LARGE_ROCK_BREAKER;
    public static MetaTileEntityLargeCrackingUnit LARGE_CRACKER;
    public static MetaTileEntityVoidOreMiner VOIDOREMINER;
    public static MetaTileEntityAdvancedChemicalPlant ADVANCED_CHEMICAL_PLANT;
    public static MetaTileEntityLargeGasCollector LARGE_GAS_COLLECTOR;
    public static MetaTileEntityLargeTransformer LARGE_TRANSFORMER;
    public static MetaTileEntityVoidFluidPump VOID_FLUID_PUMP;

    public static void init() {
        int startId = GTEConfigHolder.startIDs.coreMTE;

        // Single Machine
        // Free: 11004~11999

        // Multi Machine
        LARGE_ROCK_BREAKER = registerMetaTileEntity(startId,
                new MetaTileEntityLargeRockBreaker(gteId("large_rock_breaker")));
        LARGE_CRACKER = registerMetaTileEntity(startId + 1,
                new MetaTileEntityLargeCrackingUnit(gteId("large_cracking_unit")));
        VOIDOREMINER = registerMetaTileEntity(startId + 2,
                new MetaTileEntityVoidOreMiner(gteId("void_ore_miner")));
        // startId + 3 ~ startId + 4 is reserved for Draconic Evolution
        ADVANCED_CHEMICAL_PLANT = registerMetaTileEntity(startId + 5,
                new MetaTileEntityAdvancedChemicalPlant(gteId("advanced_chemical_plant")));
        LARGE_GAS_COLLECTOR = registerMetaTileEntity(startId + 6,
                new MetaTileEntityLargeGasCollector(gteId(GTEConfigHolder.gteFlag.featureFlag ?
                        "large_gas_collector" : "advanced_gas_collector")));
        LARGE_TRANSFORMER = registerMetaTileEntity(startId + 7,
                new MetaTileEntityLargeTransformer(gteId("large_transformer")));
        VOID_FLUID_PUMP = registerMetaTileEntity(startId + 8,
                new MetaTileEntityVoidFluidPump((gteId("void_fluid_pump"))));
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
