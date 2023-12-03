package gtexpert.loaders.recipe.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.GTEConfigHolder.ae2Integration;
import static gtexpert.common.GTEConfigHolder.eioIntegration;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.recipe.MetaTileEntityLoader;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.common.GTEConfigHolder;
import gtexpert.common.metatileentities.EIOMetaTileEntities;
import gtexpert.integration.gt.GTHelper;

import crazypants.enderio.base.fluid.Fluids;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.conduits.init.ConduitObject;
import crazypants.enderio.endergy.init.EndergyObject;
import crazypants.enderio.machines.init.MachineObject;
import crazypants.enderio.powertools.init.PowerToolObject;

public class EIORecipeLoader {

    public static void init() {
        // craftNutrientDistillation
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.PORKCHOP));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.BEEF));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.CHICKEN));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.RABBIT));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.MUTTON));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.FISH, 1, 0));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.FISH, 1, 1));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.FISH, 1, 2));

        // craftHootch
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.WHEAT_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.DYE, 1, 3));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.BEETROOT_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.PUMPKIN_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.MELON_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.POISONOUS_POTATO));

        // eio.capacitor
        OreDictionary.registerOre("craftCapacitorEIO", new ItemStack(ModObject.itemBasicCapacitor.getItemNN(), 1, 0));
        OreDictionary.registerOre("craftCapacitorEIO", new ItemStack(EndergyObject.itemCapacitorSilver.getItemNN(), 1));

        fluid();
        materials();
        items();
        blocks();
        tools();
        slice_n_splice();
    }

    private static void fluid() {
        // XP Juice
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Materials.Gold, 2)
                .fluidInputs(Materials.Blaze.getFluid(288))
                .fluidInputs(Materials.Glowstone.getFluid(576))
                .fluidOutputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 200))
                .duration(200).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CANNER_RECIPES.recipeBuilder()
                .input(Items.GLASS_BOTTLE, 1)
                .fluidInputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 200))
                .output(Items.EXPERIENCE_BOTTLE, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CANNER_RECIPES.recipeBuilder()
                .input(Items.EXPERIENCE_BOTTLE, 1)
                .fluidOutputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 200))
                .output(Items.GLASS_BOTTLE, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Nutrient Distillation
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input("craftNutrientDistillation", 8)
                .input(Items.SPIDER_EYE, 2)
                .fluidInputs(Materials.Water.getFluid(1000))
                .fluidOutputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        if (Loader.isModLoaded(GTEValues.MODID_GTFO)) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_GTFO, "gtfo_meta_item", 2, 117))
                    .input(Items.SPIDER_EYE, 2)
                    .fluidInputs(Materials.Water.getFluid(1000))
                    .fluidOutputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                    .duration(100).EUt(VA[LV])
                    .buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_GTFO, "gtfo_meta_item", 2, 118))
                    .input(Items.SPIDER_EYE, 2)
                    .fluidInputs(Materials.Water.getFluid(1000))
                    .fluidOutputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                    .duration(100).EUt(VA[LV])
                    .buildAndRegister();
        }

        // Dew of Void
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(ModObject.itemMaterial.getItemNN(), 2, 35)
                .input(dust, GTEMaterials.EndSteel, 2)
                .fluidInputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                .fluidOutputs(new FluidStack(Fluids.ENDER_DISTILLATION.getFluid(), 1000))
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(ModObject.itemMaterial.getItemNN(), 2, 35)
                .fluidInputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                .fluidInputs(GTEMaterials.EndSteel.getFluid(288))
                .fluidOutputs(new FluidStack(Fluids.ENDER_DISTILLATION.getFluid(), 1000))
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Vapor of levity
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(ModObject.itemMaterial.getItemNN(), 2, 36)
                .input(ModObject.itemMaterial.getItemNN(), 2, 34)
                .fluidInputs(new FluidStack(Fluids.ENDER_DISTILLATION.getFluid(), 1000))
                .fluidOutputs(new FluidStack(Fluids.VAPOR_OF_LEVITY.getFluid(), 1000))
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Hootch
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input("craftHootch", 4)
                .input(Items.SUGAR, 1)
                .fluidInputs(Materials.Water.getFluid(2000))
                .fluidOutputs(new FluidStack(Fluids.HOOTCH.getFluid(), 500))
                .duration(200).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();
        if (Loader.isModLoaded(GTEValues.MODID_GTFO)) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_GTFO, "gtfo_meta_item", 1, 117))
                    .input(Items.SUGAR, 1)
                    .fluidInputs(Materials.Water.getFluid(2000))
                    .fluidOutputs(new FluidStack(Fluids.HOOTCH.getFluid(), 500))
                    .duration(200).EUt(VA[eioIntegration.voltageTier])
                    .buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_GTFO, "gtfo_meta_item", 1, 118))
                    .input(Items.SUGAR, 1)
                    .fluidInputs(Materials.Water.getFluid(2000))
                    .fluidOutputs(new FluidStack(Fluids.HOOTCH.getFluid(), 500))
                    .duration(200).EUt(VA[eioIntegration.voltageTier])
                    .buildAndRegister();
        }

        // Fire Water
        RecipeMaps.FERMENTING_RECIPES.recipeBuilder()
                .input(dust, Materials.Redstone, 2)
                .fluidInputs(Materials.Blaze.getFluid(1152))
                .fluidOutputs(new FluidStack(Fluids.FIRE_WATER.getFluid(), 1000))
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Sunshine
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Materials.Glowstone, 2)
                .input(Blocks.DOUBLE_PLANT, 1)
                .fluidInputs(new FluidStack(Fluids.FIRE_WATER.getFluid(), 250))
                .fluidOutputs(new FluidStack(Fluids.LIQUID_SUNSHINE.getFluid(), 250))
                .duration(56).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Cloud Seed
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Materials.Silver, 2)
                .fluidInputs(Materials.Water.getFluid(3500))
                .fluidOutputs(new FluidStack(Fluids.CLOUD_SEED.getFluid(), 3500))
                .duration(300).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Concentrated Cloud
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Materials.Electrum, 2)
                .input(Items.SNOWBALL, 1)
                .fluidInputs(new FluidStack(Fluids.CLOUD_SEED.getFluid(), 1000))
                .fluidOutputs(new FluidStack(Fluids.CLOUD_SEED_CONCENTRATED.getFluid(), 500))
                .duration(200).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();
    }

    private static void materials() {
        // Soul Sand Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.SOUL_SAND))
                .outputs(OreDictUnifier.get(dust, GTEMaterials.SoulSand))
                .duration(25)
                .EUt(2)
                .buildAndRegister();

        // Chorus fruit Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.CHORUS_FRUIT))
                .outputs(OreDictUnifier.get(dust, GTEMaterials.ChorusFruit))
                .duration(25)
                .EUt(2)
                .buildAndRegister();

        // Electrical Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Steel, 1)
                .input(dust, Materials.Coal, 1)
                .input(dust, Materials.Silicon, 1)
                .output(dust, GTEMaterials.ElectricalSteel, 3)
                .duration(40).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Energetic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.Gold, 1)
                .input(dust, Materials.Glowstone, 1)
                .output(dust, GTEMaterials.EnergeticAlloy, 3)
                .duration(40).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Vibrant Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.EnergeticAlloy, 1)
                .input(dust, Materials.EnderPearl, 1)
                .output(dust, GTEMaterials.VibrantAlloy, 2)
                .duration(40).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Redstone Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.RedAlloy, 1)
                .input(dust, Materials.Silicon, 1)
                .output(dust, GTEMaterials.RedstoneAlloy, 2)
                .duration(40).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Conductive Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Iron, 1)
                .input(dust, GTEMaterials.RedstoneAlloy, 1)
                .output(dust, GTEMaterials.ConductiveIron, 2)
                .duration(40).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Pulsating Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Iron, 1)
                .input(dust, Materials.EnderPearl, 1)
                .output(dust, GTEMaterials.PulsatingIron, 2)
                .duration(40).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Dark Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Iron, 1)
                .input(dust, Materials.Coal, 1)
                .input(dust, Materials.Obsidian, 1)
                .output(dust, GTEMaterials.DarkSteel, 3)
                .duration(40).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Soularium
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Gold, 1)
                .input(dust, Materials.Ash, 1)
                .input(dust, GTEMaterials.SoulSand, 1)
                .output(dust, GTEMaterials.Soularium, 3)
                .duration(40).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // End Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Endstone, 1)
                .input(dust, GTEMaterials.DarkSteel, 1)
                .input(dust, Materials.Obsidian, 1)
                .output(dust, GTEMaterials.EndSteel, 3)
                .duration(40).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Iron Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Platinum, 1)
                .input(dust, Materials.Iron, 1)
                .input(dust, Materials.Aluminium, 1)
                .output(dust, GTEMaterials.ConstructionAlloy, 3)
                .duration(40).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Crystalline Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                // .input(ModObject.itemMaterial.getItemNN(), 1, 34)
                .input(dust, Materials.Gold, 1)
                .input(dust, Materials.Platinum, 1)
                .input(dust, Materials.Emerald, 1)
                .input(dust, GTEMaterials.VibrantAlloy, 1)
                .output(dust, GTEMaterials.CrystallineAlloy, 4)
                .duration(40).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Melodic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.EndSteel, 1)
                .input(dust, GTEMaterials.ChorusFruit, 1)
                .output(dust, GTEMaterials.MelodicAlloy, 2)
                .duration(40).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Stellar Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.NetherStar, 1)
                .input(dust, GTEMaterials.MelodicAlloy, 1)
                .input(dust, Materials.Clay, 1)
                .output(dust, GTEMaterials.StellarAlloy, 3)
                .duration(40).EUt(VA[eioIntegration.voltageTier + 3])
                .buildAndRegister();

        // Crystalline Pink Slime
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.MelodicAlloy, 1)
                .input(dust, Materials.RawRubber, 2)
                .output(dust, GTEMaterials.CrystallinePinkSlime, 2)
                .duration(40).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Energetic Silver
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Silver, 1)
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.Glowstone, 1)
                .output(dust, GTEMaterials.EnergeticSilver, 3)
                .duration(40).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Vivid Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.EnergeticSilver, 1)
                .input(dust, Materials.EnderPearl, 1)
                .output(dust, GTEMaterials.VividAlloy, 2)
                .duration(40).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();
    }

    private static void items() {
        // Basic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(GTHelper.batteryHull(eioIntegration.voltageTier), 1)
                .input(GTHelper.oreDictionaryCircuit(eioIntegration.voltageTier), 1)
                .fluidInputs(GTEMaterials.ElectricalSteel.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 0)
                .duration(56).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Double-Layer Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 1, 0)
                .input(GTHelper.oreDictionaryCircuit(eioIntegration.voltageTier + 1), 1)
                .fluidInputs(GTEMaterials.EnergeticAlloy.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 1)
                .duration(56).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Octadic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 2, 1)
                .input(GTHelper.oreDictionaryCircuit(eioIntegration.voltageTier + 2), 1)
                .fluidInputs(GTEMaterials.VibrantAlloy.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 2)
                .duration(56).EUt(VA[eioIntegration.voltageTier + 2])
                .buildAndRegister();

        // Crystaline Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 1, 1)
                .input(GTHelper.oreDictionaryCircuit(eioIntegration.voltageTier + 1), 1)
                .fluidInputs(GTEMaterials.CrystallineAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorCrystalline.getItemNN(), 1)
                .duration(56).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Melodic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 2)
                .input(GTHelper.oreDictionaryCircuit(eioIntegration.voltageTier + 2), 1)
                .fluidInputs(GTEMaterials.MelodicAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorMelodic.getItemNN(), 1)
                .duration(56).EUt(VA[eioIntegration.voltageTier + 2])
                .buildAndRegister();

        // Silver Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(GTHelper.batteryHull(eioIntegration.voltageTier), 1)
                .input(GTHelper.oreDictionaryCircuit(eioIntegration.voltageTier), 1)
                .fluidInputs(Materials.Silver.getFluid(1152))
                .output(EndergyObject.itemCapacitorSilver.getItemNN(), 1)
                .duration(56).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Endergenic Silver Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorSilver.getItemNN(), 2)
                .input(GTHelper.oreDictionaryCircuit(eioIntegration.voltageTier + 1), 1)
                .fluidInputs(GTEMaterials.EnergeticSilver.getFluid(1152))
                .output(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 1)
                .duration(56).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Endergied Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 2)
                .input(GTHelper.oreDictionaryCircuit(eioIntegration.voltageTier + 2), 1)
                .fluidInputs(GTEMaterials.VividAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorVivid.getItemNN(), 1)
                .duration(56).EUt(VA[eioIntegration.voltageTier + 2])
                .buildAndRegister();

        // Stellar Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorVivid.getItemNN(), 2)
                .input(GTHelper.oreDictionaryCircuit(eioIntegration.voltageTier + 3), 1)
                .input(Items.SHULKER_SHELL, 1)
                .fluidInputs(GTEMaterials.StellarAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorStellar.getItemNN(), 1)
                .duration(56).EUt(VA[eioIntegration.voltageTier + 3])
                .buildAndRegister();
    }

    private static void blocks() {
        // Creative Capacitor Bank
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaItems.ENERGY_CLUSTER, 4)
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3, 8))
                .input(MetaTileEntities.HULL[UV])
                .input(MetaItems.CRYSTAL_MAINFRAME_UV, 4)
                .inputs(new ItemStack(PowerToolObject.block_cap_bank.getBlockNN(), 8, 3))
                .input(MetaItems.COVER_SOLAR_PANEL_UV, 1)
                .fluidInputs(GTEMaterials.VibrantAlloy.getFluid(18432))
                .fluidInputs(Materials.SolderingAlloy.getFluid(18432))
                .fluidInputs(Materials.Neutronium.getFluid(9216))
                .outputs(new ItemStack(PowerToolObject.block_cap_bank.getBlockNN(), 1, 0))
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        // Vial Extractor
        MetaTileEntityLoader.registerMachineRecipe(true,
                EIOMetaTileEntities.VIAL_EXTRACTOR, "VRV", "PHF", "WCW",
                'V', ModObject.itemSoulVial.getItemNN(),
                'R', SENSOR,
                'P', PISTON,
                'H', HULL,
                'F', PUMP,
                'W', CABLE,
                'C', CIRCUIT);

        // Slice'N'Splice
        MetaTileEntityLoader.registerMachineRecipe(true,
                EIOMetaTileEntities.SLICE_N_SPLICE, "PSP", "CHC", "MBM",
                'P', new UnificationEntry(plate, GTEMaterials.Soularium),
                'S', "itemSkull",
                'C', CIRCUIT,
                'H', HULL,
                'M', MOTOR,
                'B', ModObject.blockDarkIronBars.getItemNN());

        // Soul Binder
        MetaTileEntityLoader.registerMachineRecipe(true,
                EIOMetaTileEntities.SOUL_BINDER, "PEP", "CHC", "MZM",
                'P', new UnificationEntry(plate, GTEMaterials.Soularium),
                'E', "skullEnderResonator",
                'C', CIRCUIT,
                'H', HULL,
                'M', MOTOR,
                'Z', "skullZombieController");

        // Electric Spawner
        MetaTileEntityLoader.registerMachineRecipe(true,
                EIOMetaTileEntities.ELECTRIC_SPAWNER, "PEP", "SHS", "CZC",
                'P', new UnificationEntry(plate, GTEMaterials.ConstructionAlloy),
                'E', "skullSentientEnder",
                'S', new UnificationEntry(plate, GTEMaterials.Soularium),
                'H', HULL,
                'C', "itemEnderCrystal",
                'Z', "skullZombieFrankenstein");

        if (eioIntegration.addShapelessRecipeMachines) {
            // Slice'N'Splice
            ModHandler.addShapelessRecipe("eio_slice_n_splice",
                    new ItemStack(MachineObject.block_slice_and_splice.getBlockNN()),
                    EIOMetaTileEntities.SLICE_N_SPLICE[HV].getStackForm());
            ModHandler.addShapelessRecipe("ceu_slice_n_splice",
                    EIOMetaTileEntities.SLICE_N_SPLICE[HV].getStackForm(),
                    new ItemStack(MachineObject.block_slice_and_splice.getBlockNN()));

            // Soul Binder
            ModHandler.addShapelessRecipe("eio_soul_binder",
                    new ItemStack(MachineObject.block_soul_binder.getBlockNN()),
                    EIOMetaTileEntities.SOUL_BINDER[HV].getStackForm());
            ModHandler.addShapelessRecipe("ceu_soul_binder",
                    EIOMetaTileEntities.SOUL_BINDER[HV].getStackForm(),
                    new ItemStack(MachineObject.block_soul_binder.getBlockNN()));

            // Electric Spawner
            ModHandler.addShapelessRecipe("eio_electric_spawner",
                    new ItemStack(MachineObject.block_powered_spawner.getBlockNN()),
                    EIOMetaTileEntities.ELECTRIC_SPAWNER[HV].getStackForm());
            ModHandler.addShapelessRecipe("ceu_electric_spawner",
                    EIOMetaTileEntities.ELECTRIC_SPAWNER[HV].getStackForm(),
                    new ItemStack(MachineObject.block_powered_spawner.getBlockNN()));
        }

        // Sky Stone Block
        if (Loader.isModLoaded(GTEValues.MODID_AE)) {
            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                    .inputs(new ItemStack(ModObject.block_infinity.getBlockNN(), 4, 2))
                    .outputs(getModItem(GTEValues.MODID_AE, "sky_stone_block", 1, 0))
                    .duration(500).EUt(VA[GTEConfigHolder.ae2Integration.voltageTier])
                    .buildAndRegister();
        }

        // Item Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeSmallItem, Materials.Electrum, 1)
                .input(plate, GTEMaterials.PulsatingIron, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(ConduitObject.item_item_conduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Fluid Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalFluid, Materials.Copper, 1)
                .input(plate, GTEMaterials.ElectricalSteel, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(ConduitObject.item_liquid_conduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Pressurized Fluid Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalFluid, Materials.Steel, 1)
                .input(plate, GTEMaterials.DarkSteel, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(ConduitObject.item_liquid_conduit.getItemNN(), 1, 1)
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Ender Fluid Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeTinyFluid, Materials.Polytetrafluoroethylene, 1)
                .input(plate, GTEMaterials.VibrantAlloy, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(ConduitObject.item_liquid_conduit.getItemNN(), 1, 2)
                .duration(100).EUt(VA[eioIntegration.voltageTier + 2])
                .buildAndRegister();

        // Energy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.Gold, 1)
                .input(plate, GTEMaterials.ConductiveIron, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(ConduitObject.item_power_conduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Enhaned Energy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.Aluminium, 1)
                .input(plate, GTEMaterials.EnergeticAlloy, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(ConduitObject.item_power_conduit.getItemNN(), 1, 1)
                .duration(100).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Ender Energy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.Osmium, 1)
                .input(plate, GTEMaterials.VibrantAlloy, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(ConduitObject.item_power_conduit.getItemNN(), 1, 2)
                .duration(100).EUt(VA[eioIntegration.voltageTier + 2])
                .buildAndRegister();

        // Redstone Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.RedAlloy, 1)
                .input(plate, GTEMaterials.RedstoneAlloy, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(ConduitObject.item_redstone_conduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Crude Endergy Conduitr
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.Lead, 1)
                .input(plate, Materials.TinAlloy, 1)
                .fluidInputs(Materials.Tin.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();

        // Iron Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.Tin, 1)
                .input(plate, GTEMaterials.ConductiveIron, 1)
                .fluidInputs(Materials.Tin.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 1)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();

        // Aluminium Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.Nickel, 1)
                .input(plate, Materials.Aluminium, 1)
                .fluidInputs(Materials.Tin.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 2)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();

        // Gold Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.Copper, 1)
                .input(plate, Materials.Gold, 1)
                .fluidInputs(Materials.SolderingAlloy.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 3)
                .duration(100).EUt(VA[MV])
                .buildAndRegister();

        // Copper Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.AnnealedCopper, 1)
                .input(plate, Materials.Copper, 1)
                .fluidInputs(Materials.SolderingAlloy.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 4)
                .duration(100).EUt(VA[MV])
                .buildAndRegister();

        // Silver Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.Electrum, 1)
                .input(plate, Materials.Silver, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 5)
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Electrum Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.BlackSteel, 1)
                .input(plate, Materials.Electrum, 1)
                .fluidInputs(Materials.Polyethylene.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 6)
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Electrum Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.TungstenSteel, 1)
                .input(plate, GTEMaterials.EnergeticSilver, 1)
                .fluidInputs(Materials.Epoxy.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 7)
                .duration(100).EUt(VA[eioIntegration.voltageTier])
                .buildAndRegister();

        // Crystalline Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.HSSG, 1)
                .input(plate, GTEMaterials.CrystallineAlloy, 1)
                .fluidInputs(Materials.Epoxy.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 8)
                .duration(100).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Crystalline Pink Slime Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.NiobiumTitanium, 1)
                .input(plate, GTEMaterials.CrystallinePinkSlime, 1)
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 9)
                .duration(100).EUt(VA[eioIntegration.voltageTier + 1])
                .buildAndRegister();

        // Melodic Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.Naquadah, 1)
                .input(plate, GTEMaterials.MelodicAlloy, 1)
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 10)
                .duration(100).EUt(VA[eioIntegration.voltageTier + 2])
                .buildAndRegister();

        // Stellar Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Materials.EnrichedNaquadahTriniumEuropiumDuranide, 1)
                .input(plate, GTEMaterials.StellarAlloy, 1)
                .fluidInputs(Materials.Polybenzimidazole.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 11)
                .duration(100).EUt(VA[UV])
                .buildAndRegister();

        if (Loader.isModLoaded(GTEValues.MODID_AE)) {
            // ME Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input("craftGlassCable", 4)
                    .input(plate, Materials.StainlessSteel, 1)
                    .fluidInputs(GTEMaterials.ConductiveIron.getFluid(144))
                    .outputs(getModItem(GTEValues.MODID_EIO, "item_me_conduit", 4, 0))
                    .duration(100).EUt(VA[ae2Integration.voltageTier])
                    .buildAndRegister();

            // ME Dense Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EIO, "item_me_conduit", 16, 0))
                    .input(plate, Materials.Titanium, 1)
                    .fluidInputs(GTEMaterials.EnergeticAlloy.getFluid(144))
                    .outputs(getModItem(GTEValues.MODID_EIO, "item_me_conduit", 4, 1))
                    .duration(100).EUt(VA[ae2Integration.voltageTier + 1])
                    .buildAndRegister();
        }
    }

    private static void tools() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && eioIntegration.hardToolArmorRecipes) {
            // Dark Helm
            ModHandler.addShapedRecipe(true, "dark_steel_helmet",
                    new ItemStack(ModObject.itemDarkSteelHelmet.getItemNN(), 1),
                    "PPP", "PhP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Dark Chest
            ModHandler.addShapedRecipe(true, "dark_steel_chestplate",
                    new ItemStack(ModObject.itemDarkSteelChestplate.getItemNN(), 1),
                    "PhP", "PPP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Dark Leggings
            ModHandler.addShapedRecipe(true, "dark_steel_leggings",
                    new ItemStack(ModObject.itemDarkSteelLeggings.getItemNN(), 1),
                    "PPP", "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Dark Boots
            ModHandler.addShapedRecipe(true, "dark_steel_boots",
                    new ItemStack(ModObject.itemDarkSteelBoots.getItemNN(), 1),
                    "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Ender Helm
            ModHandler.addShapedRecipe(true, "end_steel_helmet",
                    new ItemStack(ModObject.itemEndSteelHelmet.getItemNN(), 1),
                    "PPP", "PhP", " G ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Ender Chest
            ModHandler.addShapedRecipe(true, "end_steel_chestplate",
                    new ItemStack(ModObject.itemEndSteelChestplate.getItemNN(), 1),
                    "PhP", "PGP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Ender Leggings
            ModHandler.addShapedRecipe(true, "end_steel_leggings",
                    new ItemStack(ModObject.itemEndSteelLeggings.getItemNN(), 1),
                    "PPP", "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Ender Boots
            ModHandler.addShapedRecipe(true, "end_steel_boots",
                    new ItemStack(ModObject.itemEndSteelBoots.getItemNN(), 1),
                    "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Stellar Helm
            ModHandler.addShapedRecipe(true, "stellar_alloy_helmet",
                    new ItemStack(EndergyObject.itemStellarAlloyHelmet.getItemNN(), 1),
                    "PPP", "PhP", " G ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Stellar Chest
            ModHandler.addShapedRecipe(true, "stellar_alloy_chestplate",
                    new ItemStack(EndergyObject.itemStellarAlloyChestplate.getItemNN(), 1),
                    "PhP", "PGP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Stellar Leggings
            ModHandler.addShapedRecipe(true, "stellar_alloy_leggings",
                    new ItemStack(EndergyObject.itemStellarAlloyLeggings.getItemNN(), 1),
                    "PPP", "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Stellar Boots
            ModHandler.addShapedRecipe(true, "stellar_alloy_boots",
                    new ItemStack(EndergyObject.itemStellarAlloyBoots.getItemNN(), 1),
                    "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Dark Axe
            ModHandler.addShapedRecipe(true, "dark_steel_axe",
                    new ItemStack(ModObject.itemDarkSteelAxe.getItemNN(), 1),
                    "PIf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Pickaxe
            ModHandler.addShapedRecipe(true, "dark_steel_pickaxe",
                    new ItemStack(ModObject.itemDarkSteelPickaxe.getItemNN(), 1),
                    "PII", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Sword
            ModHandler.addShapedRecipe(true, "dark_steel_sword",
                    new ItemStack(ModObject.itemDarkSteelSword.getItemNN(), 1),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Crook
            ModHandler.addShapedRecipe(true, "dark_steel_crook",
                    new ItemStack(ModObject.itemDarkSteelCrook.getItemNN(), 1),
                    "PPS", "shS", " fS",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Backhoe
            ModHandler.addShapedRecipe(true, "dark_steel_backhoe",
                    new ItemStack(ModObject.itemDarkSteelHand.getItemNN(), 1),
                    "PPP", "PHP", "hsf",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'H', new ItemStack(Items.DIAMOND_HOE, 1));

            // Dark Shears
            ModHandler.addShapedRecipe(true, "dark_steel_shears",
                    new ItemStack(ModObject.itemDarkSteelShears.getItemNN(), 1),
                    "PTP", "hRf", "SsS",
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel),
                    'T', OreDictUnifier.get(screw, GTEMaterials.DarkSteel),
                    'R', OreDictUnifier.get(ring, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel));

            // Dark Bow
            ModHandler.addShapedRecipe(true, "dark_steel_bow",
                    new ItemStack(ModObject.itemDarkSteelBow.getItemNN(), 1),
                    "hSW", "PRW", "fSW",
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel),
                    'W', new ItemStack(Items.STRING, 1),
                    'P', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 45),
                    'R', OreDictUnifier.get(ring, GTEMaterials.DarkSteel));

            // Dark Shield
            ModHandler.addShapedRecipe(true, "dark_steel_shield",
                    new ItemStack(ModObject.itemDarkSteelShield.getItemNN(), 1),
                    "BSB", "LPL", "BSB",
                    'B', OreDictUnifier.get(bolt, GTEMaterials.DarkSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.DarkSteel),
                    'L', OreDictUnifier.get(stickLong, GTEMaterials.DarkSteel),
                    'P', OreDictUnifier.get(plate, GTEMaterials.DarkSteel));

            // Ender Axe
            ModHandler.addShapedRecipe(true, "ender_steel_axe",
                    new ItemStack(ModObject.itemEndSteelAxe.getItemNN(), 1),
                    "PIf", "PG ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.EndSteel));

            // Ender Pickaxe
            ModHandler.addShapedRecipe(true, "ender_steel_pickaxe",
                    new ItemStack(ModObject.itemEndSteelPickaxe.getItemNN(), 1),
                    "PII", "hGf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.EndSteel));

            // Ender Sword
            ModHandler.addShapedRecipe(true, "ender_steel_sword",
                    new ItemStack(ModObject.itemEndSteelSword.getItemNN(), 1),
                    " P ", "hGf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.EndSteel));

            // Ender Bow
            ModHandler.addShapedRecipe(true, "ender_steel_bow",
                    new ItemStack(ModObject.itemEndSteelBow.getItemNN(), 1),
                    "hSW", "PRW", "fSW",
                    'S', OreDictUnifier.get(stick, GTEMaterials.EndSteel),
                    'W', new ItemStack(Items.STRING, 1),
                    'P', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 45),
                    'R', OreDictUnifier.get(ring, GTEMaterials.EndSteel));

            // Ender Shield
            ModHandler.addShapedRecipe(true, "ender_steel_shield",
                    new ItemStack(ModObject.itemEndSteelShield.getItemNN(), 1),
                    "BSB", "LPL", "BSB",
                    'B', OreDictUnifier.get(bolt, GTEMaterials.EndSteel),
                    'S', OreDictUnifier.get(stick, GTEMaterials.EndSteel),
                    'L', OreDictUnifier.get(stickLong, GTEMaterials.EndSteel),
                    'P', OreDictUnifier.get(plate, GTEMaterials.EndSteel));

            // Stellar Axe
            ModHandler.addShapedRecipe(true, "stellar_alloy_axe",
                    new ItemStack(EndergyObject.itemStellarAlloyAxe.getItemNN(), 1),
                    "PIf", "PO ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.StellarAlloy),
                    'O', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.StellarAlloy));

            // Stellar Pickaxe
            ModHandler.addShapedRecipe("stellar_alloy_pickaxe",
                    new ItemStack(EndergyObject.itemStellarAlloyPickaxe.getItemNN(), 1),
                    "PII", "hOf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.StellarAlloy),
                    'O', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.StellarAlloy));

            // Stellar Sword
            ModHandler.addShapedRecipe("stellar_alloy_sword",
                    new ItemStack(EndergyObject.itemStellarAlloySword.getItemNN(), 1),
                    " P ", "hOf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.StellarAlloy),
                    'O', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44),
                    'S', OreDictUnifier.get(stickLong, GTEMaterials.StellarAlloy));

            // Yeta Wrench
            ModHandler.addShapedRecipe("yeta_wrench", new ItemStack(ModObject.itemYetaWrench.getItemNN(), 1),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.ElectricalSteel));
        }
    }

    private static void slice_n_splice() {
        // Zombie Electrode
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.EnergeticAlloy)
                .input(Items.SKULL, 1, 0)
                .input(plate, GTEMaterials.EnergeticAlloy)
                .input(MetaItems.SILICON_WAFER, 1)
                .input("craftCapacitorEIO", 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 40)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Z-Logic Controller
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(Items.SKULL, 1, 2)
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(dust, Materials.Redstone)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 41)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Ender Resonator
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(ModObject.blockEndermanSkull.getItemNN(), 1, 0)
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(plate, GTEMaterials.VibrantAlloy)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 43)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Skeletal Controller
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(Items.SKULL, 1, 0)
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input("craftCapacitorEIO", 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 45)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Guardian Diode
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.EnergeticAlloy)
                .input(Items.PRISMARINE_SHARD, 1, 0)
                .input(plate, GTEMaterials.EnergeticAlloy)
                .input(ModObject.itemMaterial.getItemNN(), 1, 14)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(ModObject.itemMaterial.getItemNN(), 1, 14)
                .output(ModObject.itemMaterial.getItemNN(), 1, 56)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Tormented Enderman Head
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(ModObject.blockEndermanSkull.getItemNN(), 1, 0)
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input("craftCapacitorEIO", 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.blockEndermanSkull.getItemNN(), 1, 2)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Totemic Capacitor
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(Items.TOTEM_OF_UNDYING, 1, 0)
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.blockEndermanSkull.getItemNN(), 1, 2)
                .duration(400).EUt(VA[LV]).buildAndRegister();
    }
}
