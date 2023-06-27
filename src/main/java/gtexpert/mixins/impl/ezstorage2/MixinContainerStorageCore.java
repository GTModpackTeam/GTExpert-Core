package gtexpert.mixins.impl.ezstorage2;

import gtexpert.mixins.interfaces.ezstorage2.IMixinEZInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.zerofall.ezstorage.gui.server.ContainerStorageCore;
import com.zerofall.ezstorage.tileentity.TileEntityStorageCore;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.jetbrains.annotations.NotNull;

@Debug(export = true)
@Mixin(ContainerStorageCore.class)
public abstract class MixinContainerStorageCore extends Container {

    @Shadow(remap = false)
    private TileEntityStorageCore tileEntity;

    @Invoker(value = "rowCount", remap = false)
    protected abstract int invokeRowCount();

    @Inject(method = "customSlotClick", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private void injectCustomSlotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn,
                                       CallbackInfoReturnable<ItemStack> cir) {
        // Always return EMPTY since this return value is never used
        cir.setReturnValue(ItemStack.EMPTY);

        int type = 0;
        if (clickedButton == 1) {
            type = (mode == 0) ? 1 : 2;
        }

        // isShiftLeftClick
        if (clickedButton == 0 && mode == 1) {
            int playerInventoryStartIndex = this.invokeRowCount() * 9;
            int playerInventoryEndIndex = playerInventoryStartIndex + playerIn.inventory.mainInventory.size();

            if (playerIn.inventory.getFirstEmptyStack() < 0) {
                ItemStack targetStack = ((IMixinEZInventory) (Object) this.tileEntity.inventory)
                        .getItemWithoutExtractAt(slotId);
                int emptyCapacity = this.inventorySlots.subList(playerInventoryStartIndex, playerInventoryEndIndex)
                        .stream()
                        .mapToInt(slot -> {
                            ItemStack slotStack = slot.getStack();
                            if (slotStack.isItemEqual(targetStack) &&
                                    ItemStack.areItemStackTagsEqual(slotStack, targetStack)) {
                                return slotStack.getMaxStackSize() - slotStack.getCount();
                            }
                            return 0;
                        }).sum();

                ItemStack retrievedStack = this.tileEntity.inventory.getItemsAt(slotId, type,
                        Math.min(emptyCapacity, targetStack.getMaxStackSize()));
                if (!retrievedStack.isEmpty()) {
                    this.mergeItemStack(retrievedStack, playerInventoryStartIndex, playerInventoryEndIndex, true);
                }
            } else {
                ItemStack retrievedStack = this.tileEntity.inventory.getItemsAt(slotId, type);
                if (!retrievedStack.isEmpty()) {
                    this.mergeItemStack(retrievedStack, playerInventoryStartIndex, playerInventoryEndIndex, true);
                }
            }
        } else {
            ItemStack heldStack = playerIn.inventory.getItemStack();
            if (heldStack.isEmpty()) {
                ItemStack retrievedStack = this.tileEntity.inventory.getItemsAt(slotId, type);
                playerIn.inventory.setItemStack(retrievedStack);
            } else if (clickedButton == 0) {
                playerIn.inventory.setItemStack(this.tileEntity.inventory.input(heldStack));
            } else if (clickedButton == 1 && mode != 1) {
                playerIn.inventory
                        .setItemStack(((IMixinEZInventory) (Object) this.tileEntity.inventory).input(heldStack, 1));
            }
        }
    }

    @Override
    public boolean canDragIntoSlot(@NotNull Slot slotIn) {
        return !(slotIn.inventory instanceof InventoryBasic);
    }
}
