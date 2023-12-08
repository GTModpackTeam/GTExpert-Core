package gtexpert.common.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;

import gregtech.api.items.armor.ArmorMetaItem;

public class GTEMetaArmor extends ArmorMetaItem<ArmorMetaItem<?>.ArmorMetaValueItem> {

    @Override
    public void registerSubItems() {
        GTEMetaItems.PISTON_BOOTS = addItem(1, "piston_boots")
                .setArmorLogic(new PistonBoots(EntityEquipmentSlot.FEET, 1024, 80_000L, 1))
                .setRarity(EnumRarity.COMMON);
    }
}
