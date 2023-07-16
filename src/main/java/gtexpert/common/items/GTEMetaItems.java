package gtexpert.common.items;

import gregtech.api.items.armor.ArmorMetaItem;
import gregtech.api.items.materialitem.MetaPrefixItem;
import gregtech.api.items.metaitem.MetaItem;

import java.util.List;

public final class GTEMetaItems {

    private GTEMetaItems() {}

    public static final List<MetaItem<?>> ITEMS = MetaItem.getMetaItems();

    public static MetaItem<?>.MetaValueItem GTE_ME_FAKE_COMPONENT;
    public static MetaItem<?>.MetaValueItem MATRIX_CORE;

    public static ArmorMetaItem<?>.ArmorMetaValueItem PISTON_BOOTS;

    public static void init() {
        GTEMetaItem1 metaItem1 = new GTEMetaItem1();
        metaItem1.setRegistryName("meta_item_1");
        GTEMetaArmor armor = new GTEMetaArmor();
        armor.setRegistryName("gte_armor");
    }

    public static void registerOreDict() {
        for (MetaItem<?> item : ITEMS) {
            if (item instanceof MetaPrefixItem) {
                ((MetaPrefixItem) item).registerOreDict();
            }
        }
    }
}
