package gtexpert.mixins;

import net.minecraft.item.ItemStack;

import com.zerofall.ezstorage.util.EZInventory;
import com.zerofall.ezstorage.util.ItemGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EZInventory.class)
public abstract class MixinEZInventory {

    @Invoker(value = "extractStack", remap = false)
    public abstract ItemStack invokeExtractStack(ItemGroup group, int size, boolean peek);

    @Inject(method = "getItemsAt(IIIZ)Lnet/minecraft/item/ItemStack;",
            at = @At(value = "INVOKE",
                     target = "Lcom/zerofall/ezstorage/util/EZInventory;extractStack(Lcom/zerofall/ezstorage/util/ItemGroup;IZ)Lnet/minecraft/item/ItemStack;",
                     ordinal = 0),
            remap = false,
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    public void injectGetItemsAtExtractStack(int index, int type, int size, boolean peek,
                                             CallbackInfoReturnable<ItemStack> cir,
                                             ItemGroup group, ItemStack stack) {
        if (size == 0) {
            cir.setReturnValue(ItemStack.EMPTY);
            cir.cancel();
            return;
        }

        if (size < 1 && type == 1) {
            size = (((int) Math.min(stack.getMaxStackSize(), group.count)) + 2 - 1) / 2;
        }

        ItemStack result = this.invokeExtractStack(group, size, peek);
        cir.setReturnValue(result);
        cir.cancel();
    }
}
