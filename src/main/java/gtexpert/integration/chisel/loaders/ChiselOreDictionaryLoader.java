package gtexpert.integration.chisel.loaders;

import net.minecraftforge.oredict.OreDictionary;

import gtexpert.api.util.Mods;

public class ChiselOreDictionaryLoader {

    public static void init() {
        // craftChisel
        OreDictionary.registerOre("craftChisel", Mods.Chisel.getItem("chisel_iron"));
        OreDictionary.registerOre("craftChisel", Mods.Chisel.getItem("chisel_diamond"));
        OreDictionary.registerOre("craftChisel", Mods.Chisel.getItem("chisel_hitech"));
    }
}
