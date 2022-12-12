package gtexpert.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
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
                .input(Blocks.SOUL_SAND, 1)
                .output(dust, SOULARIUM, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();
        /*GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .input(dust, Gold, 1)
                .input(dust, Ash, 1)
                .input(Blocks.SOUL_SAND, 1)
                .blastFurnaceTemp(3600)
                .notConsumable(new IntCircuitIngredient(2))
                .fluidOutputs(SOULARIUM.getFluid(288))
                .duration(900).EUt(VA[EV])
                .buildAndRegister();
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .input(dust, Gold, 1)
                .input(dust, Ash, 1)
                .input(Blocks.SOUL_SAND, 1)
                .fluidInputs(Argon.getFluid(100))
                .blastFurnaceTemp(3600)
                .notConsumable(new IntCircuitIngredient(12))
                .fluidOutputs(SOULARIUM.getFluid(288))
                .duration(603).EUt(VA[EV])
                .buildAndRegister();*/

        // End Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Endstone, 1)
                .input(dust, DARK_STEEL, 1)
                .input(dust, Obsidian, 1)
                .output(dust, END_STEEL)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Construction Iron(Iron Alloy)
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Platinum, 1)
                .input(dust, Iron, 1)
                .output(dust, CONSTRUCTION_IRON)
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
                //.input(dust, , 1) // TODO: EIO:PrecientPowder
                .output(dust, CRYSTALLINE_ALLOY, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Melodic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, END_STEEL, 1)
                .input(Items.CHORUS_FRUIT_POPPED, 1)
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
                .output(dust, VIBRANT_ALLOY, 1)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Vial Extractor TODO: GLASS_BOTTLEをEIOの空Vialに変更
        registerMachineRecipe(VIAL_EXTRACTOR, "VSV", "SRS", "PMP", 'M', HULL, 'V', Items.GLASS_BOTTLE, 'S', Items.DIAMOND_SWORD, 'R', SENSOR, 'P', new UnificationEntry(OrePrefix.plateDouble, Steel));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().notConsumable(new IntCircuitIngredient(1)).input(MetaTileEntities.HULL[LV], 1).input(Items.GLASS_BOTTLE, 2).input(Items.DIAMOND_SWORD, 3).input(SENSOR_LV,1).input(plateDouble, Steel, 2).output(VIAL_EXTRACTOR[LV], 1).duration(40).EUt(VA[LV]).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().notConsumable(new IntCircuitIngredient(1)).input(MetaTileEntities.HULL[MV], 1).input(Items.GLASS_BOTTLE, 2).input(Items.DIAMOND_SWORD, 3).input(SENSOR_MV,1).input(plateDouble, Steel, 2).output(VIAL_EXTRACTOR[MV], 1).duration(40).EUt(VA[MV]).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().notConsumable(new IntCircuitIngredient(1)).input(MetaTileEntities.HULL[HV], 1).input(Items.GLASS_BOTTLE, 2).input(Items.DIAMOND_SWORD, 3).input(SENSOR_HV,1).input(plateDouble, Steel, 2).output(VIAL_EXTRACTOR[HV], 1).duration(40).EUt(VA[HV]).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().notConsumable(new IntCircuitIngredient(1)).input(MetaTileEntities.HULL[EV], 1).input(Items.GLASS_BOTTLE, 2).input(Items.DIAMOND_SWORD, 3).input(SENSOR_EV,1).input(plateDouble, Steel, 2).output(VIAL_EXTRACTOR[EV], 1).duration(40).EUt(VA[EV]).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().notConsumable(new IntCircuitIngredient(1)).input(MetaTileEntities.HULL[IV], 1).input(Items.GLASS_BOTTLE, 2).input(Items.DIAMOND_SWORD, 3).input(SENSOR_IV,1).input(plateDouble, Steel, 2).output(VIAL_EXTRACTOR[IV], 1).duration(40).EUt(VA[IV]).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().notConsumable(new IntCircuitIngredient(1)).input(MetaTileEntities.HULL[LuV], 1).input(Items.GLASS_BOTTLE, 2).input(Items.DIAMOND_SWORD, 3).input(SENSOR_LuV,1).input(plateDouble, Steel, 2).output(VIAL_EXTRACTOR[LuV], 1).duration(40).EUt(VA[LuV]).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().notConsumable(new IntCircuitIngredient(1)).input(MetaTileEntities.HULL[ZPM], 1).input(Items.GLASS_BOTTLE, 2).input(Items.DIAMOND_SWORD, 3).input(SENSOR_ZPM,1).input(plateDouble, Steel, 2).output(VIAL_EXTRACTOR[ZPM], 1).duration(40).EUt(VA[ZPM]).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().notConsumable(new IntCircuitIngredient(1)).input(MetaTileEntities.HULL[UV], 1).input(Items.GLASS_BOTTLE, 2).input(Items.DIAMOND_SWORD, 3).input(SENSOR_UV,1).input(plateDouble, Steel, 2).output(VIAL_EXTRACTOR[UV], 1).duration(40).EUt(VA[UV]).buildAndRegister();

        GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder()
                .input(Items.ENDER_PEARL, 1) //TODO: EIOのエンダーマンが入ったVial
                .output(Items.SKULL, 1) //TODO: EIOのエンダーヘッド
                .output(Items.ENDER_PEARL, 10)
                .output(Items.GLASS_BOTTLE, 1) //TODO: EIOの空Vial
                .fluidOutputs(END_STEEL.getFluid(1000)) //TODO: EIOの液体経験値
                .duration(400).EUt(VA[LV]).buildAndRegister();
    }
}
