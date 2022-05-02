package gtexpert.common;
import gregtech.api.items.metaitem.MetaItem;

public class GTEMetaItems {

    public static MetaItem<?>.MetaValueItem GTE_ME_FAKE_COMPONENT;
    public static MetaItem<?>.MetaValueItem MATRIX_CORE;

    public static void init() {
        GTEMetaItem1 metaItem1 = new GTEMetaItem1();
        metaItem1.setRegistryName("meta_item_1");
    }
}
