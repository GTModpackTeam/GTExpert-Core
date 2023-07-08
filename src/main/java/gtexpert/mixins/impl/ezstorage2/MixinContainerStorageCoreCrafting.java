package gtexpert.mixins.impl.ezstorage2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.zerofall.ezstorage.gui.server.ContainerStorageCore;
import com.zerofall.ezstorage.gui.server.ContainerStorageCoreCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ContainerStorageCoreCrafting.class)
public class MixinContainerStorageCoreCrafting extends ContainerStorageCore {

    public MixinContainerStorageCoreCrafting(EntityPlayer player, World world, int x, int y, int z) {
        super(player, world, x, y, z);
    }

    @Redirect(method = "transferStackInSlot",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/item/ItemStack;isItemEqual(Lnet/minecraft/item/ItemStack;)Z",
                       ordinal = 0),
              remap = false)
    private boolean redirectTransferStackInSlotIsItemEqual0(ItemStack stack, ItemStack other) {
        return true;
    }

    @Redirect(method = "transferStackInSlot",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/item/ItemStack;isItemEqual(Lnet/minecraft/item/ItemStack;)Z",
                       ordinal = 1),
              remap = false)
    private boolean redirectTransferStackInSlotIsItemEqual1(ItemStack stack, ItemStack other) {
        return false;
    }

    @Redirect(method = "transferStackInSlot",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/inventory/InventoryCrafting;getStackInSlot(I)Lnet/minecraft/item/ItemStack;",
                       ordinal = 0))
    private ItemStack redirectTransferStackInSlotGetStackInSlot(InventoryCrafting inv, int index) {
        return inv.getStackInSlot(index).copy();
    }

}
