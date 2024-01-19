package gtexpert.integration.deda.loaders;

import net.minecraftforge.oredict.OreDictionary;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTEUtility;

public class DEDAOreDictionaryLoader {

    public static void init() {
        OreDictionary.registerOre("oreDraconium",
                GTEUtility.getModItem(GTEValues.MODID_DE, "draconium_ore"));
        OreDictionary.registerOre("oreNetherrackDraconium",
                GTEUtility.getModItem(GTEValues.MODID_DE, "resources", 1, 1));
        OreDictionary.registerOre("oreEndstoneDraconium",
                GTEUtility.getModItem(GTEValues.MODID_DE, "resources", 1, 2));

        OreDictionary.registerOre("blockDraconium",
                GTEUtility.getModItem(GTEValues.MODID_DE, "draconium_block"));
        OreDictionary.registerOre("blockAwakenedDraconium",
                GTEUtility.getModItem(GTEValues.MODID_DE, "draconic_block"));
    }
}
