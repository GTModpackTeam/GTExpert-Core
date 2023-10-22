package gtexpert.common.items;

import gregtech.api.GTValues;
import gregtech.common.covers.CoverConveyor;
import gregtech.common.covers.CoverFluidRegulator;
import gregtech.common.covers.CoverPump;
import gregtech.common.covers.CoverRoboticArm;

import static gregtech.common.covers.CoverBehaviors.registerBehavior;
import static gtexpert.api.util.GTEUtility.gteId;

public class GTECoverBehaviors {

    public static void init() {
        registerBehavior(gteId("primitive.conveyor"), GTEMetaItems.PRIMITIVE_CONVEYOR,
                (tile, side) -> new CoverConveyor(tile, side, GTValues.ULV, 2));
        registerBehavior(gteId("primitive.pump"), GTEMetaItems.PRIMITIVE_PUMP,
                (tile, side) -> new CoverPump(tile, side, GTValues.ULV, 320));
        registerBehavior(gteId("primitive.robot.arm"), GTEMetaItems.PRIMITIVE_ROBOT_ARM,
                (tile, side) -> new CoverRoboticArm(tile, side, GTValues.ULV, 2));
        registerBehavior(gteId("primitive.fluid.regulator"), GTEMetaItems.PRIMITIVE_FLUID_REGULATOR,
                (tile, side) -> new CoverFluidRegulator(tile, side, GTValues.ULV, 16));
    }
}
