package gtexpert.loaders.recipe.ingredients;

import gregtech.api.GTValues;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gtexpert.api.GTEValues;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class GTFORecipeLoader {
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
        materials();
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

    private static void materials() {
        // AmmoniumChloride * 2 & SodiumBicarbonate * 6
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .input(dust, Salt, 2)
                .output(dust, AmmoniumChloride, 2)
                .output(dust, SodiumBicarbonate, 6)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(SaltWater.getFluid(1000))
                .output(dust, AmmoniumChloride, 2)
                .output(dust, SodiumBicarbonate, 6)
                .duration(200).EUt(VA[LV])
                .buildAndRegister();
    }
}
