package com.github.gtexpert.core.mixins.draconicadditions.items.tools;

import net.foxmcloud.draconicadditions.items.tools.PortableWiredCharger;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Mixin to rebalance Portable Wired Charger capacity from Draconic Additions.
 * <p>
 * Adjusts energy storage capacity for basic and wyvern tiers to better fit
 * the modpack progression.
 */
@Mixin(value = PortableWiredCharger.class, remap = false)
public class PortableWiredChargerMixin {

    /**
     * @author tier940
     * @reason Change energy storage in basic and wyvern.
     */
    @Overwrite
    public int getCapacity(ItemStack stack) {
        return switch (stack.getItemDamage()) {
            case 0, 4 -> PortableWiredCharger.wyvernTransfer;
            case 1, 5 -> PortableWiredCharger.basicTransfer;
            case 2, 6 -> PortableWiredCharger.draconicTransfer;
            case 3, 7 -> PortableWiredCharger.chaoticTransfer;
            default -> 0;
        };
    }
}
