package gtexpert.common.items;

import gregtech.api.items.armor.ArmorMetaItem;
import gregtech.api.items.metaitem.MetaItem;

public final class GTEMetaItems {

    private GTEMetaItems() {}

    public static MetaItem<?>.MetaValueItem GTE_ME_FAKE_COMPONENT;
    public static MetaItem<?>.MetaValueItem MATRIX_CORE;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_MOTOR;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_PUMP;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_CONVEYOR;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_PISTON;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_ROBOT_ARM;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_FLUID_REGULATOR;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_FIELD_GENERATOR;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_EMITTER;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_SENSOR;

    public static final MetaItem<?>.MetaValueItem[] GTE_SHAPE_MOLDS = new MetaItem.MetaValueItem[4];
    public static MetaItem<?>.MetaValueItem SHAPE_MOLD_PRINTED_SILICON;
    public static MetaItem<?>.MetaValueItem SHAPE_MOLD_LOGIC_PROCESSOR;
    public static MetaItem<?>.MetaValueItem SHAPE_MOLD_CALCULATION_PROCESSOR;
    public static MetaItem<?>.MetaValueItem SHAPE_MOLD_ENGINEERING_PROCESSOR;

    public static final MetaItem<?>.MetaValueItem[] GTE_SHAPE_EXTRUDERS = new MetaItem.MetaValueItem[4];
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_PRINTED_SILICON;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_LOGIC_PROCESSOR;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_CALCULATION_PROCESSOR;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_ENGINEERING_PROCESSOR;

    public static ArmorMetaItem<?>.ArmorMetaValueItem PISTON_BOOTS;

    public static void init() {
        GTEMetaItem1 metaItem1 = new GTEMetaItem1();
        metaItem1.setRegistryName("meta_item_1");
        GTEMetaArmor armor = new GTEMetaArmor();
        armor.setRegistryName("gte_armor");
    }
}
