package gtexpert.mixins;

import com.zerofall.ezstorage.item.EZItem;
import com.zerofall.ezstorage.item.ItemDolly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemDolly.class)
public abstract class MixinItemDolly extends EZItem {

    public MixinItemDolly(String name) {
        super(name);
    }

    @Inject(method = "onItemUse", at = @At("HEAD"), remap = false, cancellable = true)
    public void injectOnItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<EnumActionResult> cir) {
        cir.setReturnValue(EnumActionResult.PASS);
        cir.cancel();
    }

}
