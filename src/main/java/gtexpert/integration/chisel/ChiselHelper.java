package gtexpert.integration.chisel;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import team.chisel.api.carving.CarvingUtils;

public class ChiselHelper {

    public static void addGroup(String groupName) {
        CarvingUtils.getChiselRegistry()
                .addGroup(CarvingUtils.getDefaultGroupFor(groupName));
    }

    public static void addVariation(String groupName, ItemStack stack) {
        CarvingUtils.getChiselRegistry()
                .addVariation(groupName, CarvingUtils.variationFor(stack, stack.getItemDamage()));
        OreDictionary.registerOre(groupName, stack);
    }

    public static void removeVariation(ItemStack stack, String groupName) {
        CarvingUtils.getChiselRegistry()
                .removeVariation(stack, groupName);
    }
}
