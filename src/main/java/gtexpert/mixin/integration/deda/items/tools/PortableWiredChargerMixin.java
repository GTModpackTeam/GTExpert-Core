package gtexpert.mixin.integration.deda.items.tools;

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
        return switch (stack.getItemDamage()) {
            case 0, 4 -> 800000;
            case 1, 5 -> 100000;
            case 2, 6 -> 6400000;
            case 3, 7 -> 51200000;
            default -> 0;
        };
    }
}
