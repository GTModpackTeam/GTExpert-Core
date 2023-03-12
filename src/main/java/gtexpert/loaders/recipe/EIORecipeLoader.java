package gtexpert.loaders.recipe;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;
import crazypants.enderio.base.fluid.Fluids;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

public class EIORecipeLoader {
    public static void init() {
        materias();
        items();
        tools();
        fluid();
    }

    private static void materias() {
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
                .output(dust, ELECTRICAL_STEEL, 3)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Energetic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Redstone, 1)
                .input(dust, Gold, 1)
                .input(dust, Glowstone, 1)
                .output(dust, ENERGETIC_ALLOY, 3)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Vibrant Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, ENERGETIC_ALLOY, 1)
                .input(dust, EnderPearl, 1)
                .output(dust, VIBRANT_ALLOY, 2)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Redstone Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, RedAlloy, 1)
                .input(dust, Silicon, 1)
                .output(dust, REDSTONE_ALLOY, 2)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Conductive Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 1)
                .input(dust, REDSTONE_ALLOY, 1)
                .output(dust, CONDUCTIVE_IRON, 2)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Pulsating Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 1)
                .input(dust, EnderPearl, 1)
                .output(dust, PULSATING_IRON, 2)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Dark Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 1)
                .input(dust, Coal, 1)
                .input(dust, Obsidian, 1)
                .output(dust, DARK_STEEL, 3)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Soularium
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Gold, 1)
                .input(dust, Ash, 1)
                .input(dust, SOUL_SAND, 1)
                .output(dust, SOULARIUM, 3)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // End Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Endstone, 1)
                .input(dust, DARK_STEEL, 1)
                .input(dust, Obsidian, 1)
                .output(dust, END_STEEL, 3)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Iron Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Platinum, 1)
                .input(dust, Iron, 1)
                .input(dust, Aluminium, 1)
                .output(dust, CONSTRUCTION_ALLOY, 3)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Crystalline Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                //.input(ModObject.itemMaterial.getItemNN(), 1, 34)
                .input(dust, Gold, 1)
                .input(dust, Platinum, 1)
                .input(dust, Emerald, 1)
                .input(dust, VIBRANT_ALLOY, 1)
                .output(dust, CRYSTALLINE_ALLOY, 4)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Melodic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, END_STEEL, 1)
                .input(dust, CHORUS_FRUIT, 1)
                .output(dust, MELODIC_ALLOY, 2)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Stellar Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, NetherStar, 1)
                .input(dust, MELODIC_ALLOY, 1)
                .input(dust, Clay, 1)
                .output(dust, STELLAR_ALLOY, 3)
                .duration(40).EUt(VA[LuV])
                .buildAndRegister();

        // Crystalline Pink Slime
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, MELODIC_ALLOY, 1)
                .input(dust, RawRubber, 2)
                .output(dust, CRYSTALLINE_PINK_SLIME, 2)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Energetic Silver
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Silver, 1)
                .input(dust, Redstone, 1)
                .input(dust, Glowstone, 1)
                .output(dust, ENERGETIC_SILVER, 3)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Vivid Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, ENERGETIC_SILVER, 1)
                .input(dust, EnderPearl, 1)
                .output(dust, VIVID_ALLOY, 2)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();


        // Vial Extractor
        registerMachineRecipe(VIAL_EXTRACTOR, "VRV", "PMF", "WCW",
                'V', ModObject.itemSoulVial.getItem(),
                'R', SENSOR,
                'P', PISTON,
                'M', HULL,
                'F', PUMP,
                'W', CABLE,
                'C', CIRCUIT);
    }

    private static void items() {
        // Basic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(MetaItems.BATTERY_HULL_HV, 1)
                .input(circuit, MarkerMaterials.Tier.HV, 1)
                .fluidInputs(ELECTRICAL_STEEL.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 0)
                .duration(56).EUt(VA[HV])
                .buildAndRegister();

        // Double-Layer Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 1, 0)
                .input(circuit, MarkerMaterials.Tier.EV, 1)
                .fluidInputs(ENERGETIC_ALLOY.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 1)
                .duration(56).EUt(VA[EV])
                .buildAndRegister();

        // Octadic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 2, 1)
                .input(circuit, MarkerMaterials.Tier.IV, 1)
                .fluidInputs(VIBRANT_ALLOY.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 2)
                .duration(56).EUt(VA[IV])
                .buildAndRegister();

        // Crystaline Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 1, 1)
                .input(circuit, MarkerMaterials.Tier.EV, 1)
                .fluidInputs(CRYSTALLINE_ALLOY.getFluid(1152))
                .output(EndergyObject.itemCapacitorCrystalline.getItemNN(), 1)
                .duration(56).EUt(VA[EV])
                .buildAndRegister();

        // Melodic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 2)
                .input(circuit, MarkerMaterials.Tier.IV, 1)
                .fluidInputs(MELODIC_ALLOY.getFluid(1152))
                .output(EndergyObject.itemCapacitorMelodic.getItemNN(), 1)
                .duration(56).EUt(VA[IV])
                .buildAndRegister();

        // Silver Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(MetaItems.BATTERY_HULL_HV, 1)
                .input(circuit, MarkerMaterials.Tier.HV, 1)
                .fluidInputs(ENERGETIC_SILVER.getFluid(1152))
                .output(EndergyObject.itemCapacitorSilver.getItemNN(), 1)
                .duration(56).EUt(VA[HV])
                .buildAndRegister();

        // Endergenic Silver Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorSilver.getItemNN(), 2)
                .input(circuit, MarkerMaterials.Tier.EV, 1)
                .fluidInputs(ENERGETIC_SILVER.getFluid(1152))
                .output(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 1)
                .duration(56).EUt(VA[EV])
                .buildAndRegister();

        // Endergied Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 2)
                .input(circuit, MarkerMaterials.Tier.IV, 1)
                .fluidInputs(VIVID_ALLOY.getFluid(1152))
                .output(EndergyObject.itemCapacitorVivid.getItemNN(), 1)
                .duration(56).EUt(VA[IV])
                .buildAndRegister();

        // Stellar Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorVivid.getItemNN(), 2)
                .input(circuit, MarkerMaterials.Tier.LuV, 1)
                .input(Items.SHULKER_SHELL, 1)
                .fluidInputs(STELLAR_ALLOY.getFluid(1152))
                .output(EndergyObject.itemCapacitorStellar.getItemNN(), 1)
                .duration(56).EUt(VA[LuV])
                .buildAndRegister();
    }

    private static void tools() {
        // Yeta Wrench
        ModHandler.addShapedRecipe("yeta_wrench", new ItemStack(ModObject.itemYetaWrench.getItemNN(), 1, 0),
                "PHP", " P ", " P ",
                'H', ToolItems.HARD_HAMMER,
                'P', OreDictUnifier.get(plate, ELECTRICAL_STEEL));
    }

    private static void fluid() {
        // XP Juice
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Gold, 2)
                .fluidInputs(Blaze.getFluid(288))
                .fluidInputs(Glowstone.getFluid(576))
                .fluidOutputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 200))
                .duration(200).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CANNER_RECIPES.recipeBuilder()
                .input(Items.GLASS_BOTTLE, 1)
                .fluidInputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 200))
                .output(Items.EXPERIENCE_BOTTLE, 1)
                .duration(100).EUt(2)
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input(Items.EXPERIENCE_BOTTLE, 1)
                .fluidOutputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 200))
                .output(Items.GLASS_BOTTLE, 1)
                .duration(400).EUt(2)
                .buildAndRegister();

        // Nutrient Distillation
        List<ItemStack> nutrientDistillationItems = new ArrayList<>();
        nutrientDistillationItems.add(new ItemStack(Items.PORKCHOP, 4));
        nutrientDistillationItems.add(new ItemStack(Items.BEEF, 4));
        nutrientDistillationItems.add(new ItemStack(Items.CHICKEN, 4));
        nutrientDistillationItems.add(new ItemStack(Items.RABBIT, 4));
        nutrientDistillationItems.add(new ItemStack(Items.MUTTON, 4));
        nutrientDistillationItems.add(new ItemStack(Items.FISH, 8));
        for (ItemStack itemStack : nutrientDistillationItems) {
            RecipeMaps.MIXER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .inputs(itemStack)
                    .input(Items.SPIDER_EYE, 2)
                    .fluidInputs(Water.getFluid(1000))
                    .fluidOutputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                    .duration(100).EUt(VA[LV])
                    .buildAndRegister();
        }

        // Dew of Void
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(ModObject.itemMaterial.getItemNN(), 2, 35)
                .input(dust, END_STEEL, 2)
                .fluidInputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                .fluidOutputs(new FluidStack(Fluids.ENDER_DISTILLATION.getFluid(), 1000))
                .duration(100).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(ModObject.itemMaterial.getItemNN(), 2, 35)
                .fluidInputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                .fluidInputs(END_STEEL.getFluid(288))
                .fluidOutputs(new FluidStack(Fluids.ENDER_DISTILLATION.getFluid(), 1000))
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Vapor of levity
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(ModObject.itemMaterial.getItemNN(), 2, 36)
                .input(ModObject.itemMaterial.getItemNN(), 2, 34)
                .fluidInputs(new FluidStack(Fluids.ENDER_DISTILLATION.getFluid(), 1000))
                .fluidOutputs(new FluidStack(Fluids.VAPOR_OF_LEVITY.getFluid(), 1000))
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Hootch
        List<ItemStack> hootchItems = new ArrayList<>();
        hootchItems.add(new ItemStack(Items.WHEAT_SEEDS, 4));
        hootchItems.add(new ItemStack(Items.DYE, 4, 3));
        hootchItems.add(new ItemStack(Items.BEETROOT_SEEDS, 4));
        hootchItems.add(new ItemStack(Items.PUMPKIN_SEEDS, 2));
        hootchItems.add(new ItemStack(Items.MELON_SEEDS, 2));
        hootchItems.add(new ItemStack(Items.POISONOUS_POTATO, 1));
        for (ItemStack itemStack : hootchItems) {
            RecipeMaps.MIXER_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .inputs(itemStack)
                    .input(Items.SUGAR, 1)
                    .fluidInputs(Water.getFluid(2000))
                    .fluidOutputs(new FluidStack(Fluids.HOOTCH.getFluid(), 500))
                    .duration(200).EUt(VA[HV])
                    .buildAndRegister();
        }

        // Fire Water
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Redstone, 2)
                .fluidInputs(Blaze.getFluid(1152))
                .fluidOutputs(new FluidStack(Fluids.FIRE_WATER.getFluid(), 1000))
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Sunshine
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Glowstone, 2)
                .input(Blocks.DOUBLE_PLANT, 1)
                .fluidInputs(new FluidStack(Fluids.FIRE_WATER.getFluid(), 250))
                .fluidOutputs(new FluidStack(Fluids.LIQUID_SUNSHINE.getFluid(), 250))
                .duration(56).EUt(VA[HV])
                .buildAndRegister();

        // Cloud Seed
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Silver, 2)
                .fluidInputs(Water.getFluid(3500))
                .fluidOutputs(new FluidStack(Fluids.CLOUD_SEED.getFluid(), 3500))
                .duration(300).EUt(VA[HV])
                .buildAndRegister();

        // Concentrated Cloud
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Electrum, 2)
                .input(Items.SNOWBALL, 1)
                .fluidInputs(new FluidStack(Fluids.CLOUD_SEED.getFluid(), 3500))
                .fluidOutputs(new FluidStack(Fluids.CLOUD_SEED.getFluid(), 1500))
                .duration(200).EUt(VA[HV])
                .buildAndRegister();
    }
}
