package gtexpert.mixins.draconicadditions.items.baubles;

import net.foxmcloud.draconicadditions.items.baubles.BaubleStats;
import net.foxmcloud.draconicadditions.items.baubles.ShieldNecklace;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ShieldNecklace.class, remap = false)
public class ShieldNecklaceMixin {

    /**
     * @author tier940
     * @reason Change energy storage in basic and wyvern.
     */
    @Overwrite
    public int getCapacity(ItemStack stack) {
        return switch (stack.getTranslationKey()) {
            case "item.draconicadditions:basic_shield_necklace" -> BaubleStats.NECKLACE_BASE_CAPACITY * 2;
            case "item.draconicadditions:wyvern_shield_necklace" -> BaubleStats.NECKLACE_BASE_CAPACITY;
            case "item.draconicadditions:draconic_shield_necklace" -> BaubleStats.NECKLACE_BASE_CAPACITY * 4;
            default -> 0;
        };
    }

    /**
     * @author tier940
     * @reason Change max receive in basic and wyvern.
     */
    @Overwrite
    public int getMaxReceive(ItemStack stack) {
        return switch (stack.getTranslationKey()) {
            case "item.draconicadditions:basic_shield_necklace" -> BaubleStats.NECKLACE_MAX_RECIEVE * 2;
            case "item.draconicadditions:wyvern_shield_necklace" -> BaubleStats.NECKLACE_MAX_RECIEVE;
            case "item.draconicadditions:draconic_shield_necklace" -> BaubleStats.NECKLACE_MAX_RECIEVE * 4;
            default -> 0;
        };
    }

    /**
     * @author tier940
     * @reason Change protection points in basic and wyvern.
     */
    @Overwrite
    public float getProtectionPoints(ItemStack arg0) {
        return switch (arg0.getTranslationKey()) {
            case "item.draconicadditions:basic_shield_necklace" -> BaubleStats.NECKLACE_BASE_SHIELD_CAPACITY * 2;
            case "item.draconicadditions:wyvern_shield_necklace" -> BaubleStats.NECKLACE_BASE_SHIELD_CAPACITY;
            case "item.draconicadditions:draconic_shield_necklace" -> BaubleStats.NECKLACE_BASE_SHIELD_CAPACITY * 4;
            default -> 0;
        };
    }

    /**
     * @author tier940
     * @reason Change recovery rate in basic and wyvern.
     */
    @Overwrite
    public float getRecoveryRate(ItemStack arg0) {
        return switch (arg0.getTranslationKey()) {
            case "item.draconicadditions:basic_shield_necklace" -> (float) BaubleStats.NECKLACE_SHIELD_RECOVERY * 2;
            case "item.draconicadditions:wyvern_shield_necklace" -> (float) BaubleStats.NECKLACE_SHIELD_RECOVERY;
            case "item.draconicadditions:draconic_shield_necklace" -> (float) BaubleStats.NECKLACE_SHIELD_RECOVERY * 4;
            default -> 0;
        };
    }
}
