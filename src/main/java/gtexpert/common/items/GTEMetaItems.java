package gtexpert.common.items;
import gregtech.api.items.armor.ArmorMetaItem;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.items.armor.MetaArmor;

public final class GTEMetaItems {

    private GTEMetaItems() {
    }

    public static MetaItem<?>.MetaValueItem GTE_ME_FAKE_COMPONENT;
    public static MetaItem<?>.MetaValueItem MATRIX_CORE;

    public static ArmorMetaItem<?>.ArmorMetaValueItem PISTON_BOOTS;

    public static void init() {
        GTEMetaItem1 metaItem1 = new GTEMetaItem1();
        metaItem1.setRegistryName("meta_item_1");
        GTEMetaArmor armor = new GTEMetaArmor();
        armor.setRegistryName("gte_armor");
    }
}
