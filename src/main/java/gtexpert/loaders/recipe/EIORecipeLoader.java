package gtexpert.loaders.recipe;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.recipe.MetaTileEntityLoader;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEConfigHolder;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import crazypants.enderio.base.fluid.Fluids;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.conduits.init.ConduitObject;
import crazypants.enderio.endergy.init.EndergyObject;
import crazypants.enderio.machines.init.MachineObject;
import crazypants.enderio.powertools.init.PowerToolObject;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;

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
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        if (Loader.isModLoaded(GTEValues.MODID_GTFO)) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_GTFO, "gtfo_meta_item", 2, 117))
                    .input(Items.SPIDER_EYE, 2)
                    .fluidInputs(Water.getFluid(1000))
                    .fluidOutputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                    .duration(100).EUt(VA[LV])
                    .buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_GTFO, "gtfo_meta_item", 2, 118))
                    .input(Items.SPIDER_EYE, 2)
                    .fluidInputs(Water.getFluid(1000))
                    .fluidOutputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                    .duration(100).EUt(VA[LV])
                    .buildAndRegister();
        }

        // Dew of Void
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(ModObject.itemMaterial.getItemNN(), 2, 35)
                .input(dust, EndSteel, 2)
                .fluidInputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                .fluidOutputs(new FluidStack(Fluids.ENDER_DISTILLATION.getFluid(), 1000))
                .duration(100).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(ModObject.itemMaterial.getItemNN(), 2, 35)
                .fluidInputs(new FluidStack(Fluids.NUTRIENT_DISTILLATION.getFluid(), 1000))
                .fluidInputs(EndSteel.getFluid(288))
                .fluidOutputs(new FluidStack(Fluids.ENDER_DISTILLATION.getFluid(), 1000))
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Vapor of levity
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(ModObject.itemMaterial.getItemNN(), 2, 36)
                .input(ModObject.itemMaterial.getItemNN(), 2, 34)
                .fluidInputs(new FluidStack(Fluids.ENDER_DISTILLATION.getFluid(), 1000))
                .fluidOutputs(new FluidStack(Fluids.VAPOR_OF_LEVITY.getFluid(), 1000))
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Hootch
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input("craftHootch", 4)
                .input(Items.SUGAR, 1)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(new FluidStack(Fluids.HOOTCH.getFluid(), 500))
                .duration(200).EUt(VA[HV])
                .buildAndRegister();
        if (Loader.isModLoaded(GTEValues.MODID_GTFO)) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_GTFO, "gtfo_meta_item", 1, 117))
                    .input(Items.SUGAR, 1)
                    .fluidInputs(Water.getFluid(2000))
                    .fluidOutputs(new FluidStack(Fluids.HOOTCH.getFluid(), 500))
                    .duration(200).EUt(VA[HV])
                    .buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_GTFO, "gtfo_meta_item", 1, 118))
                    .input(Items.SUGAR, 1)
                    .fluidInputs(Water.getFluid(2000))
                    .fluidOutputs(new FluidStack(Fluids.HOOTCH.getFluid(), 500))
                    .duration(200).EUt(VA[HV])
                    .buildAndRegister();
        }

        // Fire Water
        RecipeMaps.FERMENTING_RECIPES.recipeBuilder()
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
                .fluidInputs(new FluidStack(Fluids.CLOUD_SEED.getFluid(), 1000))
                .fluidOutputs(new FluidStack(Fluids.CLOUD_SEED_CONCENTRATED.getFluid(), 500))
                .duration(200).EUt(VA[HV])
                .buildAndRegister();
    }

    private static void materials() {
        // Soul Sand Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.SOUL_SAND))
                .outputs(OreDictUnifier.get(dust, SoulSand))
                .duration(25)
                .EUt(2)
                .buildAndRegister();

        // Chorus fruit Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.CHORUS_FRUIT))
                .outputs(OreDictUnifier.get(dust, ChorusFruit))
                .duration(25)
                .EUt(2)
                .buildAndRegister();

        // Electrical Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Steel, 1)
                .input(dust, Coal, 1)
                .input(dust, Silicon, 1)
                .output(dust, ElectricalSteel, 3)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Energetic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Redstone, 1)
                .input(dust, Gold, 1)
                .input(dust, Glowstone, 1)
                .output(dust, EnergeticAlloy, 3)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Vibrant Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, EnergeticAlloy, 1)
                .input(dust, EnderPearl, 1)
                .output(dust, VibrantAlloy, 2)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Redstone Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, RedAlloy, 1)
                .input(dust, Silicon, 1)
                .output(dust, RedstoneAlloy, 2)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Conductive Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 1)
                .input(dust, RedstoneAlloy, 1)
                .output(dust, ConductiveIron, 2)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Pulsating Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 1)
                .input(dust, EnderPearl, 1)
                .output(dust, PulsatingIron, 2)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Dark Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Iron, 1)
                .input(dust, Coal, 1)
                .input(dust, Obsidian, 1)
                .output(dust, DarkSteel, 3)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // Soularium
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Gold, 1)
                .input(dust, Ash, 1)
                .input(dust, SoulSand, 1)
                .output(dust, Soularium, 3)
                .duration(40).EUt(VA[HV])
                .buildAndRegister();

        // End Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Endstone, 1)
                .input(dust, DarkSteel, 1)
                .input(dust, Obsidian, 1)
                .output(dust, EndSteel, 3)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Iron Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Platinum, 1)
                .input(dust, Iron, 1)
                .input(dust, Aluminium, 1)
                .output(dust, ConstructionAlloy, 3)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Crystalline Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                // .input(ModObject.itemMaterial.getItemNN(), 1, 34)
                .input(dust, Gold, 1)
                .input(dust, Platinum, 1)
                .input(dust, Emerald, 1)
                .input(dust, VibrantAlloy, 1)
                .output(dust, CrystallineAlloy, 4)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Melodic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, EndSteel, 1)
                .input(dust, ChorusFruit, 1)
                .output(dust, MelodicAlloy, 2)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Stellar Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, NetherStar, 1)
                .input(dust, MelodicAlloy, 1)
                .input(dust, Clay, 1)
                .output(dust, StellarAlloy, 3)
                .duration(40).EUt(VA[LuV])
                .buildAndRegister();

        // Crystalline Pink Slime
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, MelodicAlloy, 1)
                .input(dust, RawRubber, 2)
                .output(dust, CrystallinePinkSlime, 2)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Energetic Silver
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Silver, 1)
                .input(dust, Redstone, 1)
                .input(dust, Glowstone, 1)
                .output(dust, EnergeticSilver, 3)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();

        // Vivid Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, EnergeticSilver, 1)
                .input(dust, EnderPearl, 1)
                .output(dust, VividAlloy, 2)
                .duration(40).EUt(VA[EV])
                .buildAndRegister();
    }

    private static void items() {
        // Basic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(MetaItems.BATTERY_HULL_HV, 1)
                .input(circuit, MarkerMaterials.Tier.HV, 1)
                .fluidInputs(ElectricalSteel.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 0)
                .duration(56).EUt(VA[HV])
                .buildAndRegister();

        // Double-Layer Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 1, 0)
                .input(circuit, MarkerMaterials.Tier.EV, 1)
                .fluidInputs(EnergeticAlloy.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 1)
                .duration(56).EUt(VA[EV])
                .buildAndRegister();

        // Octadic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 2, 1)
                .input(circuit, MarkerMaterials.Tier.IV, 1)
                .fluidInputs(VibrantAlloy.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 2)
                .duration(56).EUt(VA[IV])
                .buildAndRegister();

        // Crystaline Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 1, 1)
                .input(circuit, MarkerMaterials.Tier.EV, 1)
                .fluidInputs(CrystallineAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorCrystalline.getItemNN(), 1)
                .duration(56).EUt(VA[EV])
                .buildAndRegister();

        // Melodic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 2)
                .input(circuit, MarkerMaterials.Tier.IV, 1)
                .fluidInputs(MelodicAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorMelodic.getItemNN(), 1)
                .duration(56).EUt(VA[IV])
                .buildAndRegister();

        // Silver Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(MetaItems.BATTERY_HULL_HV, 1)
                .input(circuit, MarkerMaterials.Tier.HV, 1)
                .fluidInputs(Silver.getFluid(1152))
                .output(EndergyObject.itemCapacitorSilver.getItemNN(), 1)
                .duration(56).EUt(VA[HV])
                .buildAndRegister();

        // Endergenic Silver Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorSilver.getItemNN(), 2)
                .input(circuit, MarkerMaterials.Tier.EV, 1)
                .fluidInputs(EnergeticSilver.getFluid(1152))
                .output(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 1)
                .duration(56).EUt(VA[EV])
                .buildAndRegister();

        // Endergied Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 2)
                .input(circuit, MarkerMaterials.Tier.IV, 1)
                .fluidInputs(VividAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorVivid.getItemNN(), 1)
                .duration(56).EUt(VA[IV])
                .buildAndRegister();

        // Stellar Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorVivid.getItemNN(), 2)
                .input(circuit, MarkerMaterials.Tier.LuV, 1)
                .input(Items.SHULKER_SHELL, 1)
                .fluidInputs(StellarAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorStellar.getItemNN(), 1)
                .duration(56).EUt(VA[LuV])
                .buildAndRegister();
    }

    private static void blocks() {
        // Creative Capacitor Bank
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ENERGY_CLUSTER, 4)
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3, 8))
                .input(MetaTileEntities.HULL[UV])
                .input(CRYSTAL_MAINFRAME_UV, 4)
                .inputs(new ItemStack(PowerToolObject.block_cap_bank.getBlockNN(), 8, 3))
                .input(COVER_SOLAR_PANEL_UV, 1)
                .fluidInputs(VibrantAlloy.getFluid(18432))
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(Neutronium.getFluid(9216))
                .outputs(new ItemStack(PowerToolObject.block_cap_bank.getBlockNN(), 1, 0))
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        // Vial Extractor
        MetaTileEntityLoader.registerMachineRecipe(true, VIAL_EXTRACTOR, "VRV", "PHF", "WCW",
                'V', ModObject.itemSoulVial.getItemNN(),
                'R', SENSOR,
                'P', PISTON,
                'H', HULL,
                'F', PUMP,
                'W', CABLE,
                'C', CIRCUIT);

        // Slice'N'Splice
        MetaTileEntityLoader.registerMachineRecipe(true, SLICE_N_SPLICE, "PSP", "CHC", "MBM",
                'P', new UnificationEntry(plate, Soularium),
                'S', "itemSkull",
                'C', CIRCUIT,
                'H', HULL,
                'M', MOTOR,
                'B', ModObject.blockDarkIronBars.getItemNN());

        // Soul Binder
        MetaTileEntityLoader.registerMachineRecipe(true, SOUL_BINDER, "PEP", "CHC", "MZM",
                'P', new UnificationEntry(plate, Soularium),
                'E', "skullEnderResonator",
                'C', CIRCUIT,
                'H', HULL,
                'M', MOTOR,
                'Z', "skullZombieController");

        // Electric Spawner
        MetaTileEntityLoader.registerMachineRecipe(true, ELECTRIC_SPAWNER, "PEP", "SHS", "CZC",
                'P', new UnificationEntry(plate, ConstructionAlloy),
                'E', "skullSentientEnder",
                'S', new UnificationEntry(plate, Soularium),
                'H', HULL,
                'C', "itemEnderCrystal",
                'Z', "skullZombieFrankenstein");

        if (GTEConfigHolder.eioIntegration.addShapelessRecipeMachines) {
            // Slice'N'Splice
            ModHandler.addShapelessRecipe("eio_slice_n_splice",
                    new ItemStack(MachineObject.block_slice_and_splice.getBlockNN()),
                    SLICE_N_SPLICE[HV].getStackForm());
            ModHandler.addShapelessRecipe("ceu_slice_n_splice", SLICE_N_SPLICE[HV].getStackForm(),
                    new ItemStack(MachineObject.block_slice_and_splice.getBlockNN()));

            // Soul Binder
            ModHandler.addShapelessRecipe("eio_soul_binder",
                    new ItemStack(MachineObject.block_soul_binder.getBlockNN()),
                    SOUL_BINDER[HV].getStackForm());
            ModHandler.addShapelessRecipe("ceu_soul_binder", SOUL_BINDER[HV].getStackForm(),
                    new ItemStack(MachineObject.block_soul_binder.getBlockNN()));

            // Electric Spawner
            ModHandler.addShapelessRecipe("eio_electric_spawner",
                    new ItemStack(MachineObject.block_powered_spawner.getBlockNN()),
                    ELECTRIC_SPAWNER[HV].getStackForm());
            ModHandler.addShapelessRecipe("ceu_electric_spawner", ELECTRIC_SPAWNER[HV].getStackForm(),
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
                .input(pipeSmallItem, Electrum, 1)
                .input(plate, PulsatingIron, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(ConduitObject.item_item_conduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Fluid Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalFluid, Copper, 1)
                .input(plate, ElectricalSteel, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(ConduitObject.item_liquid_conduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Pressurized Fluid Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeNormalFluid, Steel, 1)
                .input(plate, DarkSteel, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(ConduitObject.item_liquid_conduit.getItemNN(), 1, 1)
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Ender Fluid Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(pipeTinyFluid, Polytetrafluoroethylene, 1)
                .input(plate, VibrantAlloy, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(ConduitObject.item_liquid_conduit.getItemNN(), 1, 2)
                .duration(100).EUt(VA[EV])
                .buildAndRegister();

        // Energy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Gold, 1)
                .input(plate, ConductiveIron, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(ConduitObject.item_power_conduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Enhaned Energy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Aluminium, 1)
                .input(plate, EnergeticAlloy, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(ConduitObject.item_power_conduit.getItemNN(), 1, 1)
                .duration(100).EUt(VA[EV])
                .buildAndRegister();

        // Ender Energy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Osmium, 1)
                .input(plate, VibrantAlloy, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(ConduitObject.item_power_conduit.getItemNN(), 1, 2)
                .duration(100).EUt(VA[IV])
                .buildAndRegister();

        // Redstone Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, RedAlloy, 1)
                .input(plate, RedstoneAlloy, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(ConduitObject.item_redstone_conduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Crude Endergy Conduitr
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Lead, 1)
                .input(plate, TinAlloy, 1)
                .fluidInputs(Tin.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 0)
                .duration(100).EUt(VA[ULV])
                .buildAndRegister();

        // Iron Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Tin, 1)
                .input(plate, ConductiveIron, 1)
                .fluidInputs(Tin.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 1)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();

        // Aluminium Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Nickel, 1)
                .input(plate, Aluminium, 1)
                .fluidInputs(Tin.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 2)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();

        // Gold Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Copper, 1)
                .input(plate, Gold, 1)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 3)
                .duration(100).EUt(VA[MV])
                .buildAndRegister();

        // Copper Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, AnnealedCopper, 1)
                .input(plate, Copper, 1)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 4)
                .duration(100).EUt(VA[MV])
                .buildAndRegister();

        // Silver Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Electrum, 1)
                .input(plate, Silver, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 5)
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Electrum Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, BlackSteel, 1)
                .input(plate, Electrum, 1)
                .fluidInputs(Polyethylene.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 6)
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Electrum Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, TungstenSteel, 1)
                .input(plate, EnergeticSilver, 1)
                .fluidInputs(Epoxy.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 7)
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Crystalline Alloy Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, HSSG, 1)
                .input(plate, CrystallineAlloy, 1)
                .fluidInputs(Epoxy.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 8)
                .duration(100).EUt(VA[EV])
                .buildAndRegister();

        // Crystalline Pink Slime Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, NiobiumTitanium, 1)
                .input(plate, CrystallinePinkSlime, 1)
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 9)
                .duration(100).EUt(VA[EV])
                .buildAndRegister();

        // Melodic Alloy Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, Naquadah, 1)
                .input(plate, MelodicAlloy, 1)
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 10)
                .duration(100).EUt(VA[IV])
                .buildAndRegister();

        // Stellar Alloy Endergy Conduit
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 1)
                .input(plate, StellarAlloy, 1)
                .fluidInputs(Polybenzimidazole.getFluid(144))
                .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 11)
                .duration(100).EUt(VA[UV])
                .buildAndRegister();

        if (Loader.isModLoaded(GTEValues.MODID_AE)) {
            // ME Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input("craftGlassCable", 4)
                    .input(plate, StainlessSteel, 1)
                    .fluidInputs(ConductiveIron.getFluid(144))
                    .outputs(getModItem(GTEValues.MODID_EIO, "item_me_conduit", 4, 0))
                    .duration(100).EUt(VA[HV])
                    .buildAndRegister();

            // ME Dense Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(getModItem(GTEValues.MODID_EIO, "item_me_conduit", 16, 0))
                    .input(plate, Titanium, 1)
                    .fluidInputs(EnergeticAlloy.getFluid(144))
                    .outputs(getModItem(GTEValues.MODID_EIO, "item_me_conduit", 4, 1))
                    .duration(100).EUt(VA[EV])
                    .buildAndRegister();
        }
    }

    private static void tools() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && GTEConfigHolder.eioIntegration.hardToolArmorRecipes) {
            // Dark Helm
            ModHandler.addShapedRecipe(true, "dark_steel_helmet",
                    new ItemStack(ModObject.itemDarkSteelHelmet.getItemNN(), 1),
                    "PPP", "PhP",
                    'P', OreDictUnifier.get(plate, DarkSteel));

            // Dark Chest
            ModHandler.addShapedRecipe(true, "dark_steel_chestplate",
                    new ItemStack(ModObject.itemDarkSteelChestplate.getItemNN(), 1),
                    "PhP", "PPP", "PPP",
                    'P', OreDictUnifier.get(plate, DarkSteel));

            // Dark Leggings
            ModHandler.addShapedRecipe(true, "dark_steel_leggings",
                    new ItemStack(ModObject.itemDarkSteelLeggings.getItemNN(), 1),
                    "PPP", "PhP", "P P",
                    'P', OreDictUnifier.get(plate, DarkSteel));

            // Dark Boots
            ModHandler.addShapedRecipe(true, "dark_steel_boots",
                    new ItemStack(ModObject.itemDarkSteelBoots.getItemNN(), 1),
                    "PhP", "P P",
                    'P', OreDictUnifier.get(plate, DarkSteel));

            // Ender Helm
            ModHandler.addShapedRecipe(true, "end_steel_helmet",
                    new ItemStack(ModObject.itemEndSteelHelmet.getItemNN(), 1),
                    "PPP", "PhP", " G ",
                    'P', OreDictUnifier.get(plate, EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Ender Chest
            ModHandler.addShapedRecipe(true, "end_steel_chestplate",
                    new ItemStack(ModObject.itemEndSteelChestplate.getItemNN(), 1),
                    "PhP", "PGP", "PPP",
                    'P', OreDictUnifier.get(plate, EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Ender Leggings
            ModHandler.addShapedRecipe(true, "end_steel_leggings",
                    new ItemStack(ModObject.itemEndSteelLeggings.getItemNN(), 1),
                    "PPP", "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Ender Boots
            ModHandler.addShapedRecipe(true, "end_steel_boots",
                    new ItemStack(ModObject.itemEndSteelBoots.getItemNN(), 1),
                    "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56));

            // Stellar Helm
            ModHandler.addShapedRecipe(true, "stellar_alloy_helmet",
                    new ItemStack(EndergyObject.itemStellarAlloyHelmet.getItemNN(), 1),
                    "PPP", "PhP", " G ",
                    'P', OreDictUnifier.get(plate, StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Stellar Chest
            ModHandler.addShapedRecipe(true, "stellar_alloy_chestplate",
                    new ItemStack(EndergyObject.itemStellarAlloyChestplate.getItemNN(), 1),
                    "PhP", "PGP", "PPP",
                    'P', OreDictUnifier.get(plate, StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Stellar Leggings
            ModHandler.addShapedRecipe(true, "stellar_alloy_leggings",
                    new ItemStack(EndergyObject.itemStellarAlloyLeggings.getItemNN(), 1),
                    "PPP", "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Stellar Boots
            ModHandler.addShapedRecipe(true, "stellar_alloy_boots",
                    new ItemStack(EndergyObject.itemStellarAlloyBoots.getItemNN(), 1),
                    "PhP", "PGP",
                    'P', OreDictUnifier.get(plate, StellarAlloy),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44));

            // Dark Axe
            ModHandler.addShapedRecipe(true, "dark_steel_axe",
                    new ItemStack(ModObject.itemDarkSteelAxe.getItemNN(), 1),
                    "PIf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, DarkSteel),
                    'I', OreDictUnifier.get(ingot, DarkSteel),
                    'S', OreDictUnifier.get(stick, DarkSteel));

            // Dark Pickaxe
            ModHandler.addShapedRecipe(true, "dark_steel_pickaxe",
                    new ItemStack(ModObject.itemDarkSteelPickaxe.getItemNN(), 1),
                    "PII", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, DarkSteel),
                    'I', OreDictUnifier.get(ingot, DarkSteel),
                    'S', OreDictUnifier.get(stick, DarkSteel));

            // Dark Sword
            ModHandler.addShapedRecipe(true, "dark_steel_sword",
                    new ItemStack(ModObject.itemDarkSteelSword.getItemNN(), 1),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, DarkSteel),
                    'S', OreDictUnifier.get(stick, DarkSteel));

            // Dark Crook
            ModHandler.addShapedRecipe(true, "dark_steel_crook",
                    new ItemStack(ModObject.itemDarkSteelCrook.getItemNN(), 1),
                    "PPS", "shS", " fS",
                    'P', OreDictUnifier.get(plate, DarkSteel),
                    'S', OreDictUnifier.get(stick, DarkSteel));

            // Dark Backhoe
            ModHandler.addShapedRecipe(true, "dark_steel_backhoe",
                    new ItemStack(ModObject.itemDarkSteelHand.getItemNN(), 1),
                    "PPP", "PHP", "hsf",
                    'P', OreDictUnifier.get(plate, DarkSteel),
                    'H', new ItemStack(Items.DIAMOND_HOE, 1));

            // Dark Shears
            ModHandler.addShapedRecipe(true, "dark_steel_shears",
                    new ItemStack(ModObject.itemDarkSteelShears.getItemNN(), 1),
                    "PTP", "hRf", "SsS",
                    'P', OreDictUnifier.get(plate, DarkSteel),
                    'T', OreDictUnifier.get(screw, DarkSteel),
                    'R', OreDictUnifier.get(ring, DarkSteel),
                    'S', OreDictUnifier.get(stick, DarkSteel));

            // Dark Bow
            ModHandler.addShapedRecipe(true, "dark_steel_bow",
                    new ItemStack(ModObject.itemDarkSteelBow.getItemNN(), 1),
                    "hSW", "PRW", "fSW",
                    'S', OreDictUnifier.get(stick, DarkSteel),
                    'W', new ItemStack(Items.STRING, 1),
                    'P', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 45),
                    'R', OreDictUnifier.get(ring, DarkSteel));

            // Dark Shield
            ModHandler.addShapedRecipe(true, "dark_steel_shield",
                    new ItemStack(ModObject.itemDarkSteelShield.getItemNN(), 1),
                    "BSB", "LPL", "BSB",
                    'B', OreDictUnifier.get(bolt, DarkSteel),
                    'S', OreDictUnifier.get(stick, DarkSteel),
                    'L', OreDictUnifier.get(stickLong, DarkSteel),
                    'P', OreDictUnifier.get(plate, DarkSteel));

            // Ender Axe
            ModHandler.addShapedRecipe(true, "ender_steel_axe",
                    new ItemStack(ModObject.itemEndSteelAxe.getItemNN(), 1),
                    "PIf", "PG ", "hS ",
                    'P', OreDictUnifier.get(plate, EndSteel),
                    'I', OreDictUnifier.get(ingot, EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56),
                    'S', OreDictUnifier.get(stickLong, EndSteel));

            // Ender Pickaxe
            ModHandler.addShapedRecipe(true, "ender_steel_pickaxe",
                    new ItemStack(ModObject.itemEndSteelPickaxe.getItemNN(), 1),
                    "PII", "hGf", " S ",
                    'P', OreDictUnifier.get(plate, EndSteel),
                    'I', OreDictUnifier.get(ingot, EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56),
                    'S', OreDictUnifier.get(stickLong, EndSteel));

            // Ender Sword
            ModHandler.addShapedRecipe(true, "ender_steel_sword",
                    new ItemStack(ModObject.itemEndSteelSword.getItemNN(), 1),
                    " P ", "hGf", " S ",
                    'P', OreDictUnifier.get(plate, EndSteel),
                    'G', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 56),
                    'S', OreDictUnifier.get(stickLong, EndSteel));

            // Ender Bow
            ModHandler.addShapedRecipe(true, "ender_steel_bow",
                    new ItemStack(ModObject.itemEndSteelBow.getItemNN(), 1),
                    "hSW", "PRW", "fSW",
                    'S', OreDictUnifier.get(stick, EndSteel),
                    'W', new ItemStack(Items.STRING, 1),
                    'P', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 45),
                    'R', OreDictUnifier.get(ring, EndSteel));

            // Ender Shield
            ModHandler.addShapedRecipe(true, "ender_steel_shield",
                    new ItemStack(ModObject.itemEndSteelShield.getItemNN(), 1),
                    "BSB", "LPL", "BSB",
                    'B', OreDictUnifier.get(bolt, EndSteel),
                    'S', OreDictUnifier.get(stick, EndSteel),
                    'L', OreDictUnifier.get(stickLong, EndSteel),
                    'P', OreDictUnifier.get(plate, EndSteel));

            // Stellar Axe
            ModHandler.addShapedRecipe(true, "stellar_alloy_axe",
                    new ItemStack(EndergyObject.itemStellarAlloyAxe.getItemNN(), 1),
                    "PIf", "PO ", "hS ",
                    'P', OreDictUnifier.get(plate, StellarAlloy),
                    'I', OreDictUnifier.get(ingot, StellarAlloy),
                    'O', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44),
                    'S', OreDictUnifier.get(stickLong, StellarAlloy));

            // Stellar Pickaxe
            ModHandler.addShapedRecipe("stellar_alloy_pickaxe",
                    new ItemStack(EndergyObject.itemStellarAlloyPickaxe.getItemNN(), 1),
                    "PII", "hOf", " S ",
                    'P', OreDictUnifier.get(plate, StellarAlloy),
                    'I', OreDictUnifier.get(ingot, StellarAlloy),
                    'O', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44),
                    'S', OreDictUnifier.get(stickLong, StellarAlloy));

            // Stellar Sword
            ModHandler.addShapedRecipe("stellar_alloy_sword",
                    new ItemStack(EndergyObject.itemStellarAlloySword.getItemNN(), 1),
                    " P ", "hOf", " S ",
                    'P', OreDictUnifier.get(plate, StellarAlloy),
                    'O', new ItemStack(ModObject.itemMaterial.getItemNN(), 1, 44),
                    'S', OreDictUnifier.get(stickLong, StellarAlloy));

            // Yeta Wrench
            ModHandler.addShapedRecipe("yeta_wrench", new ItemStack(ModObject.itemYetaWrench.getItemNN(), 1),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, ElectricalSteel));
        }
    }

    private static void slice_n_splice() {
        // Zombie Electrode
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, EnergeticAlloy)
                .input(Items.SKULL, 1, 0)
                .input(plate, EnergeticAlloy)
                .input(MetaItems.SILICON_WAFER, 1)
                .input("craftCapacitorEIO", 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 40)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Z-Logic Controller
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, Soularium)
                .input(Items.SKULL, 1, 2)
                .input(plate, Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(dust, Redstone)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 41)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Ender Resonator
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, Soularium)
                .input(ModObject.blockEndermanSkull.getItemNN(), 1, 0)
                .input(plate, Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(plate, VibrantAlloy)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 43)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Skeletal Controller
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, Soularium)
                .input(Items.SKULL, 1, 0)
                .input(plate, Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input("craftCapacitorEIO", 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 45)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Guardian Diode
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, EnergeticAlloy)
                .input(Items.PRISMARINE_SHARD, 1, 0)
                .input(plate, EnergeticAlloy)
                .input(ModObject.itemMaterial.getItemNN(), 1, 14)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(ModObject.itemMaterial.getItemNN(), 1, 14)
                .output(ModObject.itemMaterial.getItemNN(), 1, 56)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Tormented Enderman Head
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, Soularium)
                .input(ModObject.blockEndermanSkull.getItemNN(), 1, 0)
                .input(plate, Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input("craftCapacitorEIO", 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.blockEndermanSkull.getItemNN(), 1, 2)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Totemic Capacitor
        GTERecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, Soularium)
                .input(Items.TOTEM_OF_UNDYING, 1, 0)
                .input(plate, Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.blockEndermanSkull.getItemNN(), 1, 2)
                .duration(400).EUt(VA[LV]).buildAndRegister();
    }
}
