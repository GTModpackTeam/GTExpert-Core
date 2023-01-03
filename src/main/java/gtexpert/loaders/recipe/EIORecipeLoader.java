package gtexpert.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
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
import crazypants.enderio.base.fluid.Fluids;
import crazypants.enderio.base.init.ModObject;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

public class EIORecipeLoader {
    public static void init() {
        // Soul Sand Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.SOUL_SAND))
                .outputs(OreDictUnifier.get(dust, SOUL_SAND))
                .duration(25)
                .EUt(2)
                .buildAndRegister();

        // Chorus fruit Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.CHORUS_FRUIT))
                .outputs(OreDictUnifier.get(dust, CHORUS_FRUIT))
                .duration(25)
                .EUt(2)
                .buildAndRegister();

        // Electrical Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Steel, 1)
                .input(dust, Coal, 1)
                .input(dust, Silicon, 1)
                .output(dust, ELECTRICAL_STEEL, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Energetic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Redstone, 1)
                .input(dust, Gold, 1)
                .input(dust, Glowstone, 1)
                .output(dust, ENERGETIC_ALLOY, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Vibrant Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, ENERGETIC_ALLOY, 1)
                .input(dust, EnderPearl, 1)
                .output(dust, VIBRANT_ALLOY, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Redstone Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, RedAlloy, 1)
                .input(dust, Silicon, 1)
                .output(dust, REDSTONE_ALLOY, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Conductive Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 1)
                .input(dust, REDSTONE_ALLOY, 1)
                .output(dust, CONDUCTIVE_IRON, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Pulsating Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 1)
                .input(dust, EnderPearl, 1)
                .output(dust, PULSATING_IRON, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Dark Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 1)
                .input(dust, Coal, 1)
                .input(dust, Obsidian, 1)
                .output(dust, DARK_STEEL, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Soularium
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Gold, 1)
                .input(dust, Ash, 1)
                .input(dust, SOUL_SAND, 1)
                .output(dust, SOULARIUM, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // End Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Endstone, 1)
                .input(dust, DARK_STEEL, 1)
                .input(dust, Obsidian, 1)
                .output(dust, END_STEEL)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Iron Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Platinum, 1)
                .input(dust, Iron, 1)
                .output(dust, CONSTRUCTION_ALLOY)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Crude Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Steel, 1)
                .input(dust, Clay, 1)
                .input(dust, Flint, 1)
                .output(dust, CRUDE_STEEL)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Crystalline Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Gold, 1)
                .input(ModObject.itemMaterial.getItemNN(), 1, 34)
                .output(dust, CRYSTALLINE_ALLOY, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Melodic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, END_STEEL, 1)
                .input(dust, CHORUS_FRUIT, 1)
                .output(dust, MELODIC_ALLOY, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Stellar Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, NetherStar, 1)
                .input(dust, MELODIC_ALLOY, 1)
                .input(dust, Clay, 1)
                .output(dust, STELLAR_ALLOY, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Crystalline Pink Slime
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, MELODIC_ALLOY, 1)
                .input(dust, RawRubber, 2)
                .output(dust, CRYSTALLINE_PINK_SLIME, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Energetic Silver
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Silver, 1)
                .input(dust, Redstone, 1)
                .input(dust, Glowstone, 1)
                .output(dust, ENERGETIC_SILVER, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Vivid Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, ENERGETIC_SILVER, 1)
                .input(dust, EnderPearl, 1)
                .output(dust, VIVID_ALLOY, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();
    }
}
