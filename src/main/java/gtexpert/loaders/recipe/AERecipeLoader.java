package gtexpert.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.fluids.MetaFluids;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
import appeng.api.AEApi;
import appeng.api.definitions.IBlocks;
import appeng.api.definitions.IItems;
import appeng.api.definitions.IMaterials;
import appeng.api.definitions.IParts;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.HULL;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

public class AERecipeLoader {
    public static void init() {
        IItems aeItems = AEApi.instance().definitions().items();
        IBlocks aeBlocks = AEApi.instance().definitions().blocks();
        IMaterials aeMaterials = AEApi.instance().definitions().materials();
        IParts aeParts = AEApi.instance().definitions().parts();

        // Charged Certus Quartz
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(CertusQuartz.getFluid(144))
                .fluidOutputs(CHARGED_CERTUS_QUARTZ.getFluid(144))
                .duration(100).EUt(VA[3])
                .buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(gem, CertusQuartz, 1)
                .output(gem, CHARGED_CERTUS_QUARTZ, 1)
                .duration(100).EUt(VA[3])
                .buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, CertusQuartz, 1)
                .output(dust, CHARGED_CERTUS_QUARTZ, 1)
                .duration(100).EUt(VA[3])
                .buildAndRegister();

        // Fluix
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(1))
                .input(dust, CHARGED_CERTUS_QUARTZ, 1)
                .input(dust, Redstone, 1)
                .input(dust, NetherQuartz, 1)
                .output(dust, FLUIX, 3)
                .duration(200).EUt(VA[3])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .fluidOutputs(FLUIX.getFluid(72))
                .duration(20).EUt(VA[0])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.fluixCrystal().maybeStack(1).get())
                .fluidOutputs(FLUIX.getFluid(144))
                .duration(20).EUt(VA[0])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .fluidOutputs(FLUIX.getFluid(576))
                .duration(20).EUt(VA[0])
                .buildAndRegister();

        // Fluix Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(2))
                .inputs(aeMaterials.skyDust().maybeStack(2).get())
                .input(dust, FLUIX, 2)
                .input(dust, Carbon, 2)
                .input(dust, Silicon, 1)
                .input(dust, Iron, 1)
                .output(dust, FLUIX_ALLOY, 8)
                .duration(200).EUt(VA[3])
                .buildAndRegister();
    }
}
