package gtexpert.common.items;

import static gtexpert.common.items.GTEMetaItems.*;

import net.minecraft.client.resources.I18n;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.behaviors.TooltipBehavior;

import gtexpert.common.GTEConfigHolder;
import gtexpert.integration.ae.AEConfigHolder;

public class GTEMetaItem1 extends StandardMetaItem {

    public GTEMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {
        int metaStartId = 0;

        if (GTEConfigHolder.gteFeatureFlag.previewItems) {
            GTE_LOGO = addItem(metaStartId, "logo");
            GTE_ME_FAKE_COMPONENT = addItem(metaStartId += 1, "gte_me_fake_component");
        } else {
            GTE_ME_FAKE_COMPONENT = addItem(metaStartId, "gte_me_fake_component");
        }

        // Matrix core
        MATRIX_CORE = addItem(metaStartId += 1, "matrix_core");

        // Artificial bone
        ARTIFICIAL_BONE = addItem(metaStartId + 1, "artificial_bone");

        // Free range: ID 3-10

        // Primitive parts
        metaStartId = 11;
        String componentsName = GTEConfigHolder.gteFlag.componentsName ? "ulv" : "primitive";
        GTE_ELECTRIC_MOTOR = addItem(metaStartId, "electric.motor." + componentsName);
        GTE_ELECTRIC_PUMP = addItem(metaStartId += 1, "electric.pump." + componentsName)
                .addComponents(new TooltipBehavior(lines -> {
                    lines.add(I18n.format("metaitem.electric.pump.tooltip"));
                    lines.add(I18n.format("gregtech.universal.tooltip.fluid_transfer_rate", (1280 / 4) / 20));
                }));
        GTE_CONVEYOR_MODULE = addItem(metaStartId += 1, "conveyor.module." + componentsName)
                .addComponents(new TooltipBehavior(lines -> {
                    lines.add(I18n.format("metaitem.conveyor.module.tooltip"));
                    lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate", 2));
                }));
        GTE_ELECTRIC_PISTON = addItem(metaStartId += 1, "electric.piston." + componentsName);
        GTE_ROBOT_ARM = addItem(metaStartId += 1, "robot.arm." + componentsName)
                .addComponents(new TooltipBehavior(lines -> {
                    lines.add(I18n.format("metaitem.robot.arm.tooltip"));
                    lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate", 2));
                }));
        GTE_FLUID_REGULATOR = addItem(metaStartId += 1, "fluid.regulator." + componentsName)
                .addComponents(new TooltipBehavior(lines -> {
                    lines.add(I18n.format("metaitem.fluid.regulator.tooltip"));
                    lines.add(I18n.format("gregtech.universal.tooltip.fluid_transfer_rate", (1280 / 4) / 20));
                }));
        GTE_FIELD_GENERATOR = addItem(metaStartId += 1, "field.generator." + componentsName);
        GTE_EMITTER = addItem(metaStartId += 1, "emitter." + componentsName);
        GTE_SENSOR = addItem(metaStartId + 1, "sensor." + componentsName);

        // Shapes
        metaStartId = 101;
        if (AEConfigHolder.moveSteelShape) {
            GTE_SHAPE_MOLDS[0] = SHAPE_MOLD_PRINTED_SILICON = addItem(metaStartId, "shape.mold.printed_silicon")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_MOLDS[1] = SHAPE_MOLD_LOGIC_PROCESSOR = addItem(metaStartId += 1, "shape.mold.logic_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_MOLDS[2] = SHAPE_MOLD_CALCULATION_PROCESSOR = addItem(metaStartId += 1,
                    "shape.mold.calculation_processor")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_MOLDS[3] = SHAPE_MOLD_ENGINEERING_PROCESSOR = addItem(metaStartId += 1,
                    "shape.mold.engineering_processor")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));

            GTE_SHAPE_EXTRUDERS[0] = SHAPE_EXTRUDER_PRINTED_SILICON = addItem(metaStartId += 1,
                    "shape.extruder.printed_silicon")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_EXTRUDERS[1] = SHAPE_EXTRUDER_LOGIC_PROCESSOR = addItem(metaStartId += 1,
                    "shape.extruder.logic_processor")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_EXTRUDERS[2] = SHAPE_EXTRUDER_CALCULATION_PROCESSOR = addItem(metaStartId += 1,
                    "shape.extruder.calculation_processor")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
            GTE_SHAPE_EXTRUDERS[3] = SHAPE_EXTRUDER_ENGINEERING_PROCESSOR = addItem(metaStartId + 1,
                    "shape.extruder.engineering_processor")
                            .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, GTValues.M * 4)));
        }
    }
}
