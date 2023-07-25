package gtexpert.mixin;

import net.minecraftforge.fml.common.FMLCommonHandler;

import com.enderio.core.common.config.PacketConfigSync;
import io.netty.buffer.ByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PacketConfigSync.class)
public class MixinPacketConfigSync {

    @Inject(method = "fromBytes",
            at = @At(value = "HEAD"),
            cancellable = true,
            remap = false)
    public void gtexpert$validateFromBytes(ByteBuf buf, CallbackInfo ci) {
        if (FMLCommonHandler.instance().getSide().isServer()) {
            ci.cancel();
        }
    }
}
