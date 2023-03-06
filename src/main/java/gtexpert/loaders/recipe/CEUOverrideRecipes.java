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
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
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

public class CEUOverrideRecipes {
    public static void init() {
        // Glowstone Dust
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Glowstone, 2));
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Glowstone, 2)
                .output(dust, Redstone, 1)
                .output(dust, Gold, 1)
                .duration(488).EUt(80)
                .buildAndRegister();

        // Netherrack Dust
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Netherrack, 1));
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Netherrack, 1)
                .chancedOutput(dustTiny, Gold, 620, 120)
                .chancedOutput(dustTiny, Redstone, 5600, 850)
                .chancedOutput(dustTiny, Coal, 5600, 850)
                .chancedOutput(dustTiny, Glowstone, 5600, 850)
                .chancedOutput(dust, Sulfur, 9900, 100)
                .duration(160).EUt(20)
                .buildAndRegister();

        // ########################################
        // Quartzite (Bug Fix)
        // ########################################
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, OreDictUnifier.get(block, Quartzite, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES, OreDictUnifier.get(gem, Quartzite, 9));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FORGE_HAMMER_RECIPES, OreDictUnifier.get(block, Quartzite, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, Quartzite, 1)},
                new FluidStack[]{Lubricant.getFluid(3)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, Quartzite, 1)},
                new FluidStack[]{DistilledWater.getFluid(11)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, Quartzite, 1)},
                new FluidStack[]{Water.getFluid(15)}
        );

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .output(dust, Quartzite, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .output(gem, Quartzite, 4)
                .duration(20).EUt(2)
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(gem, Quartzite, 4)
                .output(block, Quartzite, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, Quartzite, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, Quartzite, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .fluidInputs(Water.getFluid(15))
                .output(plate, Quartzite, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();


        // ########################################
        // Certus Quartz (Bug Fix)
        // ########################################
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, OreDictUnifier.get(block, CertusQuartz, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES, OreDictUnifier.get(gem, CertusQuartz, 9));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FORGE_HAMMER_RECIPES, OreDictUnifier.get(block, CertusQuartz, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                new ItemStack[]{SHAPE_MOLD_BLOCK.getStackForm()},
                new FluidStack[]{CertusQuartz.getFluid(1296)
        });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, CertusQuartz, 1)},
                new FluidStack[]{Lubricant.getFluid(3)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, CertusQuartz, 1)},
                new FluidStack[]{DistilledWater.getFluid(11)}
        );
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[]{OreDictUnifier.get(block, CertusQuartz, 1)},
                new FluidStack[]{Water.getFluid(15)}
        );

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidOutputs(CertusQuartz.getFluid(576))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .output(dust, CertusQuartz, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .output(gem, CertusQuartz, 4)
                .duration(20).EUt(2)
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(gem, CertusQuartz, 4)
                .output(block, CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(CertusQuartz.getFluid(576))
                .output(block, CertusQuartz, 1)
                .duration(20).EUt(7)
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, CertusQuartz, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, CertusQuartz, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidInputs(Water.getFluid(15))
                .output(plate, CertusQuartz, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
    }
}
