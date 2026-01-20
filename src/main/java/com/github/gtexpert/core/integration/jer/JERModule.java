package com.github.gtexpert.core.integration.jer;

import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableList;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_JER,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.JUST_ENOUGH_RESOURCES,
           name = "GTExpert JER Integration",
           description = "Just Enough Resources Integration Module")
public class JERModule extends GTEIntegrationSubmodule {

    private static final float SKULL_DROP_CHANCE = 0.025F;
    private static final String POOL_NAME = "gtexpert_skull_drops";

    @NotNull
    @Override
    public List<Class<?>> getEventBusSubscribers() {
        return ImmutableList.of(JERModule.class);
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
                POOL_NAME);

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
                POOL_NAME);

        event.getTable().addPool(pool);
    }
}
