package gtexpert.common.items;

import static gtexpert.common.items.GTEMetaItems.*;

import net.minecraft.client.resources.I18n;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.behaviors.TooltipBehavior;

import gtexpert.core.GTEConfigHolder;
import gtexpert.integration.ae.AEConfigHolder;

public class GTEMetaItem1 extends StandardMetaItem {

    public GTEMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {
        GTE_ME_FAKE_COMPONENT = addItem(0, "gte_me_fake_component");
        MATRIX_CORE = addItem(1, "matrix_core");

        // Free range: ID 2-10

        // Primitive parts: ID 11-20
        String componentsName = GTEConfigHolder.gteFlag.componentsName ? "ulv" : "primitive";
        GTE_ELECTRIC_MOTOR = addItem(11, "electric.motor." + componentsName);
        GTE_ELECTRIC_PUMP = addItem(12, "electric.pump." + componentsName)
                .addComponents(new TooltipBehavior(lines -> {
                    lines.add(I18n.format("metaitem.electric.pump.tooltip"));
                    lines.add(I18n.format("gregtech.universal.tooltip.fluid_transfer_rate", (1280 / 4) / 20));
                }));
        GTE_CONVEYOR_MODULE = addItem(13, "conveyor.module." + componentsName)
                .addComponents(new TooltipBehavior(lines -> {
                    lines.add(I18n.format("metaitem.conveyor.module.tooltip"));
                    lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate", 2));
                }));
        GTE_ELECTRIC_PISTON = addItem(14, "electric.piston." + componentsName);
        GTE_ROBOT_ARM = addItem(15, "robot.arm." + componentsName)
                .addComponents(new TooltipBehavior(lines -> {
                    lines.add(I18n.format("metaitem.robot.arm.tooltip"));
                    lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate", 2));
                }));
        GTE_FLUID_REGULATOR = addItem(16, "fluid.regulator." + componentsName)
                .addComponents(new TooltipBehavior(lines -> {
                    lines.add(I18n.format("metaitem.fluid.regulator.tooltip"));
                    lines.add(I18n.format("gregtech.universal.tooltip.fluid_transfer_rate", (1280 / 4) / 20));
                }));
        GTE_FIELD_GENERATOR = addItem(17, "field.generator." + componentsName);
        GTE_EMITTER = addItem(18, "emitter." + componentsName);
        GTE_SENSOR = addItem(19, "sensor." + componentsName);

        // Shapes: ID 101-110
        if (AEConfigHolder.moveSteelShape) {
            GTE_SHAPE_MOLDS[0] = SHAPE_MOLD_PRINTED_SILICON = addItem(101, "shape.mold.printed_silicon")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_MOLDS[1] = SHAPE_MOLD_LOGIC_PROCESSOR = addItem(102, "shape.mold.logic_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_MOLDS[2] = SHAPE_MOLD_CALCULATION_PROCESSOR = addItem(103, "shape.mold.calculation_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_MOLDS[3] = SHAPE_MOLD_ENGINEERING_PROCESSOR = addItem(104, "shape.mold.engineering_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));

            GTE_SHAPE_EXTRUDERS[0] = SHAPE_EXTRUDER_PRINTED_SILICON = addItem(105, "shape.extruder.printed_silicon")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_EXTRUDERS[1] = SHAPE_EXTRUDER_LOGIC_PROCESSOR = addItem(106, "shape.extruder.logic_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_EXTRUDERS[2] = SHAPE_EXTRUDER_CALCULATION_PROCESSOR = addItem(107,
                    "shape.extruder.calculation_processor")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_EXTRUDERS[3] = SHAPE_EXTRUDER_ENGINEERING_PROCESSOR = addItem(108,
                    "shape.extruder.engineering_processor")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
        }
    }
}
