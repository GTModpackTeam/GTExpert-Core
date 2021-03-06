package gtexpert.common.items;

import gregtech.api.items.armor.ArmorMetaItem;
import gregtech.common.items.armor.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;

public class GTEMetaArmor extends ArmorMetaItem<ArmorMetaItem<?>.ArmorMetaValueItem> {

    @Override
    public void registerSubItems() {
        GTEMetaItems.PISTON_BOOTS = addItem(1, "piston_boots").setArmorLogic(new PistonBoots(EntityEquipmentSlot.FEET, 1024, 80_000L, 1)).setRarity(EnumRarity.COMMON);
    }
}
