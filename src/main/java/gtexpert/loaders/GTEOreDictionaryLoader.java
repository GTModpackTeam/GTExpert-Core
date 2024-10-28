package gtexpert.loaders;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import gtexpert.common.items.GTEMetaItems;

public class GTEOreDictionaryLoader {

    public static void init() {
        OreDictionary.registerOre("stickArtificialBone", GTEMetaItems.ARTIFICIAL_BONE.getStackForm());
        OreDictionary.registerOre("bookshelf", new ItemStack(Blocks.BOOKSHELF));
    }
}
