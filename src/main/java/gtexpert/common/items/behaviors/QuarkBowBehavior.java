package gtexpert.common.items.behaviors;

import java.util.UUID;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import gregtech.api.items.metaitem.stats.IEnchantabilityHelper;
import gregtech.api.util.GTUtility;
import gregtech.common.items.behaviors.ToggleEnergyConsumerBehavior;

import gtexpert.core.GTEConfigHolder;

public class QuarkBowBehavior extends ToggleEnergyConsumerBehavior implements IEnchantabilityHelper {

    public static final ResourceLocation OVERRIDE_KEY_LOCATION = GTUtility.gregtechId("quark_bow_active");
    protected static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("97010023-8775-6F63-7BD2-9AB54919E7C2");
    protected static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("68155C1A-67B4-56B5-3DA8-8317782B15EF");
    private final double baseAttackDamage;
    private final double additionalAttackDamage;

    public QuarkBowBehavior() {
        super(GTEConfigHolder.gteFlag.quarkBow.energyConsumption);
        this.baseAttackDamage = GTEConfigHolder.gteFlag.quarkBow.quarkBowBaseDamage;
        this.additionalAttackDamage = GTEConfigHolder.gteFlag.quarkBow.quarkBowDamageBoost;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        HashMultimap<String, AttributeModifier> modifiers = HashMultimap.create();
        if (slot == EntityEquipmentSlot.MAINHAND) {
            double attackDamage = baseAttackDamage + (isItemActive(stack) ? additionalAttackDamage : 0.0D);
            modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                    new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.0, 0));
            modifiers.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon Modifier", attackDamage, 0));
        }
        return modifiers;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 33;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment.type == null) {
            return false;
        }
        return enchantment != Enchantments.UNBREAKING &&
                enchantment != Enchantments.MENDING &&
                enchantment != Enchantments.INFINITY &&
                enchantment.type.canEnchantItem(Items.BOW);
    }
}
