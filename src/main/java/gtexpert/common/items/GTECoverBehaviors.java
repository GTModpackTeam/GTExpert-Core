package gtexpert.common.items;

import static gregtech.common.covers.CoverBehaviors.registerBehavior;
import static gtexpert.api.util.GTEUtility.gteId;

import gregtech.api.GTValues;
import gregtech.common.covers.CoverConveyor;
import gregtech.common.covers.CoverFluidRegulator;
import gregtech.common.covers.CoverPump;
import gregtech.common.covers.CoverRoboticArm;

import gtexpert.core.GTEConfigHolder;

public class GTECoverBehaviors {

    public static void init() {
        String componentsName = GTEConfigHolder.gteFlag.componentsName ? "ulv" : "primitive";
        registerBehavior(gteId("conveyor." + componentsName), GTEMetaItems.GTE_CONVEYOR_MODULE,
                (def, tile, side) -> new CoverConveyor(def, tile, side, GTValues.ULV, 2));
        registerBehavior(gteId("pump." + componentsName), GTEMetaItems.GTE_ELECTRIC_PUMP,
                (def, tile, side) -> new CoverPump(def, tile, side, GTValues.ULV, 1280 / 4));
        registerBehavior(gteId("robotic_arm." + componentsName), GTEMetaItems.GTE_ROBOT_ARM,
                (def, tile, side) -> new CoverRoboticArm(def, tile, side, GTValues.ULV, 2));
        registerBehavior(gteId("fluid.regulator." + componentsName), GTEMetaItems.GTE_FLUID_REGULATOR,
                (def, tile, side) -> new CoverFluidRegulator(def, tile, side, GTValues.ULV, 1280 / 4));
    }
}
