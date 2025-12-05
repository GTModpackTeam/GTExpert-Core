package com.github.gtexpert.core.integration.deda.loaders;

import net.minecraftforge.oredict.OreDictionary;

import com.github.gtexpert.core.api.util.Mods;

public class DEDAOreDictionaryLoader {

    public static void init() {
        OreDictionary.registerOre("oreDraconium",
                Mods.DraconicEvolution.getItem("draconium_ore", 1, 0));
        OreDictionary.registerOre("oreNetherrackDraconium",
                Mods.DraconicEvolution.getItem("draconium_ore", 1, 1));
        OreDictionary.registerOre("oreEndstoneDraconium",
                Mods.DraconicEvolution.getItem("draconium_ore", 1, 2));

        OreDictionary.registerOre("nuggetAwakenedDraconium",
                Mods.DraconicEvolution.getItem("nugget", 1, 1));
        OreDictionary.registerOre("ingotAwakenedDraconium",
                Mods.DraconicEvolution.getItem("draconic_ingot", 1, 0));
        OreDictionary.registerOre("blockAwakenedDraconium",
                Mods.DraconicEvolution.getItem("draconic_block", 1, 0));
    }
}
