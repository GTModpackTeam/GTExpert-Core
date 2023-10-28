package gtexpert.integration.ae;

import net.minecraft.item.ItemStack;

import appeng.api.AEApi;
import appeng.api.definitions.IBlocks;
import appeng.api.definitions.IItems;
import appeng.api.definitions.IMaterials;
import appeng.api.definitions.IParts;
import appeng.integration.modules.crafttweaker.InscriberRecipes;
import crafttweaker.api.minecraft.CraftTweakerMC;
import stanhebben.zenscript.annotations.Optional;

public class AEHelper {

    public static final IItems aeItems = AEApi.instance().definitions().items();
    public static final IBlocks aeBlocks = AEApi.instance().definitions().blocks();
    public static final IMaterials aeMaterials = AEApi.instance().definitions().materials();
    public static final IParts aeParts = AEApi.instance().definitions().parts();

    public static void addInscriberRecipe(ItemStack stack, ItemStack input, boolean inscribe,
                                          @Optional ItemStack topInput, @Optional ItemStack bottomInput) {
        InscriberRecipes.addRecipe(
                CraftTweakerMC.getIItemStack(stack),
                CraftTweakerMC.getIItemStack(input),
                inscribe,
                CraftTweakerMC.getIItemStack(topInput),
                CraftTweakerMC.getIItemStack(bottomInput));
    }

    public static void removeInscriberRecipe(ItemStack stack) {
        InscriberRecipes.removeRecipe(CraftTweakerMC.getIItemStack(stack));
    }
}
