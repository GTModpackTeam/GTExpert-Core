package gtexpert.integration.ae;

import gregtech.api.unification.material.Material;

import appeng.api.AEApi;
import appeng.api.definitions.IBlocks;
import appeng.api.definitions.IItems;
import appeng.api.definitions.IMaterials;
import appeng.api.definitions.IParts;

import static gregtech.api.unification.material.Materials.*;

public class AEHelper {

    public static final IItems aeItems = AEApi.instance().definitions().items();
    public static final IBlocks aeBlocks = AEApi.instance().definitions().blocks();
    public static final IMaterials aeMaterials = AEApi.instance().definitions().materials();
    public static final IParts aeParts = AEApi.instance().definitions().parts();

    public static final Material[] tierMaterials = new Material[] {
            WroughtIron,
            Steel,
            Aluminium,
            StainlessSteel,
            Titanium,
            TungstenSteel,
            RhodiumPlatedPalladium,
            NaquadahAlloy,
            Darmstadtium,
            Neutronium
    };

    // public static void addInscriberRecipe(ItemStack stack, ItemStack input, boolean inscribe,
    // @Optional ItemStack topInput, @Optional ItemStack bottomInput) {
    // InscriberRecipes.addRecipe(
    // CraftTweakerMC.getIItemStack(stack),
    // CraftTweakerMC.getIItemStack(input),
    // inscribe,
    // CraftTweakerMC.getIItemStack(topInput),
    // CraftTweakerMC.getIItemStack(bottomInput));
    // }
    //
    // public static void removeInscriberRecipe(ItemStack stack) {
    // InscriberRecipes.removeRecipe(CraftTweakerMC.getIItemStack(stack));
    // }
}
