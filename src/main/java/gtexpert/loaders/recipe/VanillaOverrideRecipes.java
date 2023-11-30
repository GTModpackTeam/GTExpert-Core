package gtexpert.loaders.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.ModHandler;

public class VanillaOverrideRecipes {

    public static void init() {
        // Flint
        ModHandler.addShapelessRecipe("flint", new ItemStack(Items.FLINT, 1),
                new ItemStack(Blocks.GRAVEL, 1),
                new ItemStack(Blocks.GRAVEL, 1),
                new ItemStack(Blocks.GRAVEL, 1));
    }
}
