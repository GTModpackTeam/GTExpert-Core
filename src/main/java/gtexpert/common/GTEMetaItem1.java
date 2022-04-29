package gtexpert.common;

import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

import static gtexpert.common.GTEMetaItems.*;

public class GTEMetaItem1  extends StandardMetaItem {

    public GTEMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {
        GTE_ME_FAKE_COMPONENT = addItem(0, "gte_me_fake_component");
        MATRIX_CORE = addItem(1, "matrix_core");
    }
}
