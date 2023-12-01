package gtexpert.common.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gtexpert.api.util.GTEUtility.gteId;

import gtexpert.api.GTEValues;
import gtexpert.common.metatileentities.multi.*;

public class GTEMultiMetaTileEntities {

    public static MetaTileEntitySawmill SAWMILL;
    public static MetaTileEntityLargeCrackingUnit LARGE_CRACKER;
    public static MetaTileEntityVoidOreMiner VOIDOREMINER;
    public static MetaTileEntityDraconiumFusion DRACONIUM_FUSION;
    public static MetaTileEntityDraconiumFusion AWAKENED_DRACONIUM_FUSION;
    public static MetaTileEntityAdvancedChemicalPlant ADVANCED_CHEMICAL_PLANT;

    public static void init() {
        SAWMILL = registerMetaTileEntity(12001,
                new MetaTileEntitySawmill(gteId("sawmill")));
        LARGE_CRACKER = registerMetaTileEntity(12002,
                new MetaTileEntityLargeCrackingUnit(gteId("large_cracking_unit")));
        VOIDOREMINER = registerMetaTileEntity(12003,
                new MetaTileEntityVoidOreMiner(gteId("void_ore_miner")));

        if (GTEValues.isModLoadedDEDA()) {
            DRACONIUM_FUSION = registerMetaTileEntity(12004,
                    new MetaTileEntityDraconiumFusion.TierDraconic(gteId("draconium_fusion")));
            AWAKENED_DRACONIUM_FUSION = registerMetaTileEntity(12005,
                    new MetaTileEntityDraconiumFusion.TierAwakened(gteId("awakened_draconium_fusion")));
        }

        ADVANCED_CHEMICAL_PLANT = registerMetaTileEntity(12006,
                new MetaTileEntityAdvancedChemicalPlant(gteId("advanced_chemical_plant")));
    }
}
