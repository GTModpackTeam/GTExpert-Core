package gtexpert.loaders.recipe;

import crazypants.enderio.base.fluid.Fluids;
import gregtech.api.items.OreDictNames;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtechfoodoption.GTFOMaterialHandler;
import gtexpert.api.recipes.GTERecipeMaps;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class GTFOOverrideRecipeLoader {
    public static void init() {
        // craftSludge
        OreDictionary.registerOre("craftSludge", new ItemStack(Items.PORKCHOP));
        OreDictionary.registerOre("craftSludge", new ItemStack(Items.BEEF));
        OreDictionary.registerOre("craftSludge", new ItemStack(Items.CHICKEN));
        OreDictionary.registerOre("craftSludge", new ItemStack(Items.RABBIT));
        OreDictionary.registerOre("craftSludge", new ItemStack(Items.MUTTON));
        OreDictionary.registerOre("craftSludge", new ItemStack(Items.FISH, 1, 0));
        OreDictionary.registerOre("craftSludge", new ItemStack(Items.FISH, 1, 1));
        OreDictionary.registerOre("craftSludge", new ItemStack(Items.FISH, 1, 2));

        fluid();
    }

    private static void fluid() {
        // Sludge
        List<ItemStack> craftSludge = new ArrayList<>();
        craftSludge.add(new ItemStack(Items.PORKCHOP));
        craftSludge.add(new ItemStack(Items.BEEF));
        craftSludge.add(new ItemStack(Items.CHICKEN));
        craftSludge.add(new ItemStack(Items.RABBIT));
        craftSludge.add(new ItemStack(Items.MUTTON));
        craftSludge.add(new ItemStack(Items.FISH, 1, 0));
        craftSludge.add(new ItemStack(Items.FISH, 1, 1));
        craftSludge.add(new ItemStack(Items.FISH, 1, 2));
        for (ItemStack itemStack : craftSludge) {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MIXER_RECIPES,
                    new ItemStack[]{itemStack},
                    new FluidStack[]{SulfuricAcid.getFluid(200)}
            );
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MIXER_RECIPES,
                    new ItemStack[]{itemStack},
                    new FluidStack[]{Water.getFluid(400)}
            );
        }
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input("craftSludge", 1)
                .fluidInputs(SulfuricAcid.getFluid(200))
                .fluidOutputs(GTFOMaterialHandler.Sludge.getFluid(200))
                .duration(250).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input("craftSludge", 1)
                .fluidInputs(Water.getFluid(200))
                .fluidOutputs(GTFOMaterialHandler.Sludge.getFluid(100))
                .duration(500).EUt(VA[LV])
                .buildAndRegister();
    }
}
