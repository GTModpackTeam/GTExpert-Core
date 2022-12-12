package com.enderio.core.api;

import crazypants.enderio.base.item.soulvial.ItemSoulVial;
import net.minecraft.item.ItemStack;

// EnderIOのItemStackを返すメソッドを持つインターフェース
public class EIOItemHandler {

    public static ItemStack getSoulVial(String name) {
        // https://github.com/SleepyTrousers/EnderIO/blob/master/enderio-base/src/main/java/crazypants/enderio/base/item/soulvial/ItemSoulVial.java
        return new ItemStack(ItemSoulVial.getByNameOrId(name));
    }
}
