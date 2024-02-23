package gtexpert.integration.deda.loaders;

import net.minecraftforge.oredict.OreDictionary;

import gtexpert.api.util.Mods;

public class DEDAOreDictionaryLoader {

    public static void init() {
        OreDictionary.registerOre("oreDraconium",
                Mods.DraconicEvolution.getItem("draconium_ore"));
        OreDictionary.registerOre("oreNetherrackDraconium",
                Mods.DraconicEvolution.getItem("resources", 1, 1));
        OreDictionary.registerOre("oreEndstoneDraconium",
                Mods.DraconicEvolution.getItem("resources", 1, 2));

        OreDictionary.registerOre("blockDraconium",
                Mods.DraconicEvolution.getItem("draconium_block"));
        OreDictionary.registerOre("blockAwakenedDraconium",
                Mods.DraconicEvolution.getItem("draconic_block"));
    }
}
