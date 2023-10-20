package gtexpert.common.items;

import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;

import gtexpert.common.GTEConfigHolder;

import static gregtech.api.GTValues.M;
import static gtexpert.common.items.GTEMetaItems.*;

public class GTEMetaItem1 extends StandardMetaItem {

    public GTEMetaItem1() {
        super();
    }

    @Override
    public void registerSubItems() {
        GTE_ME_FAKE_COMPONENT = addItem(0, "gte_me_fake_component");
        MATRIX_CORE = addItem(1, "matrix_core");
        if (GTEConfigHolder.ae2Integration.moveSteelShape) {
            SHAPE_MOLD_PRINTED_SILICON = addItem(2, "shape.mold.printed_silicon")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
            SHAPE_MOLD_LOGIC_PROCESSOR = addItem(3, "shape.mold.logic_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
            SHAPE_MOLD_CALCULATION_PROCESSOR = addItem(4, "shape.mold.calculation_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
            SHAPE_MOLD_ENGINEERING_PROCESSOR = addItem(5, "shape.mold.engineering_processor")
                    .setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        }
    }
}
