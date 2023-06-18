package gtexpert.common.items;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.items.armor.ArmorLogicSuite;
import gregtech.api.util.GTUtility;
import gregtech.api.util.input.KeyBind;
import gregtech.common.items.armor.IStepAssist;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

public class PistonBoots extends ArmorLogicSuite implements IStepAssist {

    private float charge = 0.0F;

    public PistonBoots(EntityEquipmentSlot slot, int energyPerUse, long maxCapacity, int tier) {
        super(energyPerUse, maxCapacity, tier, slot);
    }

    @Override
    public void onArmorTick(@NotNull World world, @NotNull EntityPlayer player, @NotNull ItemStack itemStack) {
        IElectricItem container = itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        NBTTagCompound data = GTUtility.getOrCreateNbtCompound(itemStack);

        updateStepHeight(player);

        if (container.canUse(energyPerUse / 100) && (player.onGround) && KeyBind.VANILLA_FORWARD.isKeyDown(player) &&
                (player.isSprinting())) {
            byte consumerTicks = data.getByte("consumerTicks");

            ++consumerTicks;
            if (consumerTicks >= 10) {
                consumerTicks = 0;
                container.discharge(energyPerUse / 100, container.getTier(), true, false, false);
            }
            data.setByte("consumerTicks", consumerTicks);
            player.moveRelative(0.0F, 0.0F, 0.25F, 0.10F);
        }

        if (!world.isRemote) {
            boolean onGround = !data.hasKey("onGround") || data.getBoolean("onGround");
            if (onGround && !player.onGround && KeyBind.VANILLA_JUMP.isKeyDown(player)) {
                container.discharge(energyPerUse / 100, container.getTier(), true, false, false);
            }
            if (player.onGround != onGround) {
                data.setBoolean("onGround", player.onGround);
            }
        } else {
            if (container.canUse(energyPerUse / 100) && player.onGround) {
                this.charge = 1.0F;
            }

            if (player.motionY >= 0.0D && this.charge > 0.0F && !player.isInWater()) {
                if (KeyBind.VANILLA_JUMP.isKeyDown(player)) {
                    if (this.charge == 1.0F) {
                        player.motionX *= 1.4D;
                        player.motionZ *= 1.4D;
                        world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_PISTON_EXTEND,
                                SoundCategory.PLAYERS, 1f, 1f);
                    }

                    player.motionY += this.charge * 0.13F;
                    this.charge = (float) (this.charge * 0.7D);
                } else if (this.charge < 1.0F) {
                    this.charge = 0.0F;
                }
            }
        }

        player.inventoryContainer.detectAndSendChanges();
    }

    @Override
    public ISpecialArmor.@NotNull ArmorProperties getProperties(EntityLivingBase player, @NotNull ItemStack armor,
                                                                DamageSource source, double damage,
                                                                EntityEquipmentSlot equipmentSlot) {
        IElectricItem container = armor.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        int damageLimit = Integer.MAX_VALUE;
        if (source == DamageSource.FALL) {
            if (energyPerUse > 0 && container != null) {
                damageLimit = (int) Math.min(damageLimit, 25.0 * container.getCharge() / (energyPerUse * 10.0D));
            }
            return new ISpecialArmor.ArmorProperties(10, (damage <= 5.0) ? 0 : damage - 5.0, damageLimit);
        }
        return new ISpecialArmor.ArmorProperties(0, 0.0D, 0);
    }

    @Override
    public EntityEquipmentSlot getEquipmentSlot(ItemStack itemStack) {
        return SLOT;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @NotNull ItemStack itemStack, DamageSource source, int damage,
                            EntityEquipmentSlot equipmentSlot) {
        IElectricItem item = itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (item != null) {
            item.discharge((long) energyPerUse / 10 * damage, item.getTier(), true, false, false);
        }
    }

    @Override
    public double getDamageAbsorption() {
        return 0;
    }

    @Override
    public @NotNull String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "gtexpert:textures/armor/piston_boots.png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldDrawHUD() {
        return false;
    }
}
