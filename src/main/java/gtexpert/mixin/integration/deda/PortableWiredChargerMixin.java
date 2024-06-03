package gtexpert.mixin.integration.deda;

import net.foxmcloud.draconicadditions.items.tools.PortableWiredCharger;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PortableWiredCharger.class, remap = false)
public class PortableWiredChargerMixin {

    /**
     * @author tier940
     * @reason Change energy storage in basic and wyvern.
     */
    @Overwrite
    public int getCapacity(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 0:
            case 4:
                return 800000;
            case 1:
            case 5:
                return 100000;
            case 2:
            case 6:
                return PortableWiredCharger.draconicTransfer;
            case 3:
            case 7:
                return PortableWiredCharger.chaoticTransfer;
            default:
                return 0;
        }
    }
}
