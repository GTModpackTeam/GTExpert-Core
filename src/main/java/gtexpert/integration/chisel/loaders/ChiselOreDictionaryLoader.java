package gtexpert.integration.chisel.loaders;

import net.minecraftforge.oredict.OreDictionary;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTEUtility;

public class ChiselOreDictionaryLoader {

    public static void init() {
        // craftChisel
        OreDictionary.registerOre("craftChisel", GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_iron"));
        OreDictionary.registerOre("craftChisel", GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_diamond"));
        OreDictionary.registerOre("craftChisel", GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_hitech"));
    }
}
