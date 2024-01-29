package gtexpert.core.loaders;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GTEOreDictionaryLoader {

    public static void init() {
        OreDictionary.registerOre("bookshelf", new ItemStack(Blocks.BOOKSHELF));
    }
}
