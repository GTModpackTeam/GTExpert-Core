package gtexpert.common.items;

import static gregtech.common.covers.CoverBehaviors.registerBehavior;
import static gtexpert.api.util.GTEUtility.gteId;
import static gtexpert.common.GTEConfigHolder.gteFlag;

import gregtech.api.GTValues;
import gregtech.common.covers.CoverConveyor;
import gregtech.common.covers.CoverFluidRegulator;
import gregtech.common.covers.CoverPump;
import gregtech.common.covers.CoverRoboticArm;

public class GTECoverBehaviors {

    public static void init() {
        String componentsName = gteFlag.componentsName ? "ulv" : "primitive";
        registerBehavior(gteId(componentsName + ".conveyor"), GTEMetaItems.GTE_CONVEYOR_MODULE,
                (def, tile, side) -> new CoverConveyor(def, tile, side, GTValues.ULV, 2));
        registerBehavior(gteId("primitive.pump"), GTEMetaItems.PRIMITIVE_PUMP,
                (def, tile, side) -> new CoverPump(def, tile, side, GTValues.ULV, 16));
        registerBehavior(gteId("primitive.robot.arm"), GTEMetaItems.PRIMITIVE_ROBOT_ARM,
                (def, tile, side) -> new CoverRoboticArm(def, tile, side, GTValues.ULV, 2));
        registerBehavior(gteId(componentsName + ".fluid.regulator"), GTEMetaItems.GTE_FLUID_REGULATOR,
                (def, tile, side) -> new CoverFluidRegulator(def, tile, side, GTValues.ULV, 16));
    }
}
