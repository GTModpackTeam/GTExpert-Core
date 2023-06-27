package gtexpert.mixins.interfaces.ezstorage2;

import net.minecraft.item.ItemStack;

public interface IMixinEZInventory {

    ItemStack getItemWithoutExtractAt(int index);
}
