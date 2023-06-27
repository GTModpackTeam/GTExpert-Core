package gtexpert.mixins.interfaces.ezstorage2;

import net.minecraft.item.ItemStack;

public interface IMixinEZInventory {

    ItemStack getItemWithoutExtractAt(int index);

    ItemStack input(ItemStack itemStack, int quantity, boolean sort);

    default ItemStack input(ItemStack itemStack, int quantity) {
        return this.input(itemStack, quantity, true);
    }
}
