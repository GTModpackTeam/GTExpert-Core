package gtexpert.integration.ffm.loaders;

import net.minecraftforge.oredict.OreDictionary;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTEUtility;

public class FFMOreDictionaryLoader {

    public static void init() {
        OreDictionary.registerOre("oreApatite", GTEUtility.getModItem(GTEValues.MODID_FFM, "resources"));
        OreDictionary.registerOre("oreCopper", GTEUtility.getModItem(GTEValues.MODID_FFM, "resources", 1, 1));
        OreDictionary.registerOre("oreTin", GTEUtility.getModItem(GTEValues.MODID_FFM, "resources", 1, 2));
    }
}
