package gtexpert.loaders;

import gtexpert.api.GTEValues;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.brandon3055.draconicevolution.DEFeatures;

import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.integration.ae.AEHelper.aeBlocks;
import static gtexpert.integration.ae.AEHelper.aeMaterials;

public class GTEOreDictionaryLoader {

    public static void init() {
        OreDictionary.registerOre("bookshelf", new ItemStack(Blocks.BOOKSHELF));

        OreDictionary.registerOre("gemCertusQuartz", aeMaterials.certusQuartzCrystal().maybeStack(1).get());
        OreDictionary.registerOre("gemChargedCertusQuartz",
                aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());

        OreDictionary.registerOre("crystalChargedCertusQuartz",
                aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());

        OreDictionary.registerOre("blockFluix", aeBlocks.fluixBlock().maybeStack(1).get());
        OreDictionary.registerOre("gemFluix", aeMaterials.fluixCrystal().maybeStack(1).get());

        OreDictionary.registerOre("blockAwakenedDraconium", DEFeatures.draconicBlock);

        OreDictionary.registerOre("craftInterfaceItem", getModItem(GTEValues.MODID_AE, "interface", 1, 0));
        OreDictionary.registerOre("craftInterfaceFluid", getModItem(GTEValues.MODID_AE, "fluid_interface", 1, 0));
    }
}
