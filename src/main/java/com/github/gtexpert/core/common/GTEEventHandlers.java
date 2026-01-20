package com.github.gtexpert.core.common;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.KilledByPlayer;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.jetbrains.annotations.NotNull;

import gregtech.api.items.armor.ArmorMetaItem;
import gregtech.api.unification.material.event.MaterialEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.common.items.GTEMetaItems;

@Mod.EventBusSubscriber(modid = GTEValues.MODID)
public class GTEEventHandlers {

    private static final float SKULL_DROP_CHANCE = 0.025F;
    private static final String SKULL_POOL_NAME = "gtexpert_skull_drops";

    private GTEEventHandlers() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterialsHigh(MaterialEvent event) {
        GTEMaterials.registerMaterialsHigh();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerMaterialsLow(MaterialEvent event) {
        GTEMaterials.registerMaterialsLow();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerMaterialsLowest(MaterialEvent event) {
        GTEMaterials.registerMaterialsLowest();
    }

    // override GTCEu fall event to enable piston boots fall damage
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onEntityLivingFallEvent(@NotNull LivingFallEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP player) {
            ItemStack armor = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            if (player.fallDistance < 3.2F) {
                return;
            }

            if (!armor.isEmpty() && armor.getItem() instanceof ArmorMetaItem &&
                    ((ArmorMetaItem<?>) armor.getItem()).getItem(armor).equals(GTEMetaItems.PISTON_BOOTS)) {
                ISpecialArmor.ArmorProperties properties = ((ArmorMetaItem<?>) (armor.getItem())).getProperties(player,
                        armor, DamageSource.FALL, (int) (player.fallDistance), EntityEquipmentSlot.FEET.getSlotIndex());
                if (properties.AbsorbRatio > 0) {
                    event.setCanceled(true);
                    EntityLivingBase entityLivingBase = event.getEntityLiving();

                    PotionEffect potioneffect = event.getEntityLiving().getActivePotionEffect(MobEffects.JUMP_BOOST);
                    float f = potioneffect == null ? 0.0F : (float) (potioneffect.getAmplifier() + 1);
                    int i = MathHelper.ceil((properties.AbsorbRatio - f) * event.getDamageMultiplier());

                    if (i > 0) {
                        entityLivingBase.attackEntityFrom(DamageSource.FALL, (float) i);
                        int j = MathHelper.floor(entityLivingBase.posX);
                        int k = MathHelper.floor(entityLivingBase.posY - 0.20000000298023224D);
                        int l = MathHelper.floor(entityLivingBase.posZ);
                        IBlockState iblockstate = entityLivingBase.world.getBlockState(new BlockPos(j, k, l));

                        if (iblockstate.getMaterial() != Material.AIR) {
                            SoundType soundtype = iblockstate.getBlock().getSoundType(iblockstate,
                                    entityLivingBase.world, new BlockPos(j, k, l), entityLivingBase);
                            entityLivingBase.playSound(soundtype.getFallSound(), soundtype.getVolume() * 0.5F,
                                    soundtype.getPitch() * 0.75F);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();

        if (name.equals(LootTableList.ENTITIES_CREEPER)) {
            addSkullDrop(event, 4); // Creeper Head
        } else if (name.equals(LootTableList.ENTITIES_SKELETON)) {
            addSkullDrop(event, 0); // Skeleton Skull
        } else if (name.equals(LootTableList.ENTITIES_ZOMBIE)) {
            addSkullDrop(event, 2); // Zombie Head
        } else if (name.equals(LootTableList.ENTITIES_ENDERMAN) && Mods.EnderIO.isModLoaded()) {
            addEndermanSkullDrop(event);
        }
    }

    private static void addSkullDrop(LootTableLoadEvent event, int meta) {
        LootCondition[] conditions = new LootCondition[] {
                new KilledByPlayer(false),
                new RandomChance(SKULL_DROP_CHANCE)
        };

        LootFunction[] functions = new LootFunction[] {
                new SetMetadata(new LootCondition[0], new RandomValueRange(meta))
        };

        LootEntry entry = new LootEntryItem(
                Items.SKULL,
                1,
                0,
                functions,
                conditions,
                GTEValues.MODID + ":skull_drop");

        LootPool pool = new LootPool(
                new LootEntry[] { entry },
                new LootCondition[0],
                new RandomValueRange(1),
                new RandomValueRange(0),
                SKULL_POOL_NAME);

        event.getTable().addPool(pool);
    }

    private static void addEndermanSkullDrop(LootTableLoadEvent event) {
        ItemStack endermanSkull = Mods.EnderIO.getItem("block_enderman_skull", 1, 0);
        if (endermanSkull.isEmpty()) return;

        LootCondition[] conditions = new LootCondition[] {
                new KilledByPlayer(false),
                new RandomChance(SKULL_DROP_CHANCE)
        };

        LootEntry entry = new LootEntryItem(
                endermanSkull.getItem(),
                1,
                0,
                new LootFunction[0],
                conditions,
                GTEValues.MODID + ":enderman_skull_drop");

        LootPool pool = new LootPool(
                new LootEntry[] { entry },
                new LootCondition[0],
                new RandomValueRange(1),
                new RandomValueRange(0),
                SKULL_POOL_NAME);

        event.getTable().addPool(pool);
    }
}
