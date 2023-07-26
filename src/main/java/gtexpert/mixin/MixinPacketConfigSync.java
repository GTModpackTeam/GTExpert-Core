package gtexpert.mixin;

import gtexpert.api.util.EnderCoreConfigValidatingObjectInputStream;

import net.minecraftforge.fml.common.FMLCommonHandler;

import com.enderio.core.common.config.PacketConfigSync;
import io.netty.buffer.ByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

@Mixin(PacketConfigSync.class)
public class MixinPacketConfigSync {

    @Inject(method = "fromBytes",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    public void gtexpert$preventServerReadingPacket(ByteBuf buf, CallbackInfo ci) {
        if (FMLCommonHandler.instance().getSide().isServer()) {
            ci.cancel();
        }
    }

    @Redirect(method = "fromBytes",
              at = @At(value = "NEW",
                       target = "(Ljava/io/InputStream;)Ljava/io/ObjectInputStream;"),
              remap = false)
    public ObjectInputStream gtexpert$redirectObjectInputStreamConstructor(InputStream in) throws IOException {
        return new EnderCoreConfigValidatingObjectInputStream(in);
    }
}
