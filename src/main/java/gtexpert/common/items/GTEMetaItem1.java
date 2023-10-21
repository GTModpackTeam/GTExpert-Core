package gtexpert.common.items;

import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.common.items.behaviors.TooltipBehavior;

import gtexpert.common.GTEConfigHolder;

import net.minecraft.client.resources.I18n;

import static gtexpert.common.items.GTEMetaItems.*;

public class GTEMetaItem1 extends StandardMetaItem {

    public GTEMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {
        GTE_ME_FAKE_COMPONENT = addItem(0, "gte_me_fake_component");
        MATRIX_CORE = addItem(1, "matrix_core");

        // Primitive modules: ID 2-6
        if (GTEConfigHolder.ceuOverride.enablePrimitiveCovers) {
            PRIMITIVE_MOTOR = addItem(2, "primitive.motor");
            PRIMITIVE_PUMP = addItem(3, "primitive.pump").addComponents(new TooltipBehavior(lines -> {
                lines.add(I18n.format("metaitem.electric.pump.tooltip"));
                lines.add(I18n.format("gregtech.universal.tooltip.fluid_transfer_rate", 1280 / 4));
            }));
            PRIMITIVE_CONVEYOR = addItem(4, "primitive.conveyor").addComponents(new TooltipBehavior(lines -> {
                lines.add(I18n.format("metaitem.conveyor.module.tooltip"));
                lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate", 2));
            }));
            PRIMITIVE_PISTON = addItem(5, "primitive.piston");
            PRIMITIVE_ROBOT_ARM = addItem(6, "primitive.robot.arm").addComponents(new TooltipBehavior(lines -> {
                lines.add(I18n.format("metaitem.robot.arm.tooltip"));
                lines.add(I18n.format("gregtech.universal.tooltip.item_transfer_rate", 2));
            }));
        }
    }
}
