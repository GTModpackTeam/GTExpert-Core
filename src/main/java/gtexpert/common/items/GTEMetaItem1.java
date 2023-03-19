package gtexpert.common.items;

import gregtech.api.items.metaitem.StandardMetaItem;
import static gtexpert.common.items.GTEMetaItems.*;

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
