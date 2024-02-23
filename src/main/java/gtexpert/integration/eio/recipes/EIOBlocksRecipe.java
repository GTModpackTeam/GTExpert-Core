package gtexpert.integration.eio.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.loaders.recipe.CraftingComponent.*;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.loaders.recipe.MetaTileEntityLoader;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.integration.eio.EnderIOConfigHolder;
import gtexpert.integration.eio.metatileentities.EIOMetaTileEntities;

import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.conduits.init.ConduitObject;
import crazypants.enderio.endergy.init.EndergyObject;
import crazypants.enderio.machines.init.MachineObject;
import crazypants.enderio.powertools.init.PowerToolObject;

public class EIOBlocksRecipe {

    public static void init() {
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
                .outputs(new ItemStack(PowerToolObject.block_cap_bank.getBlockNN()))
                .duration(1200).EUt(VA[UV])
                .buildAndRegister();

        ModHandler.addShapelessNBTClearingRecipe("creative_capacitor_bank_nbt",
                new ItemStack(PowerToolObject.block_cap_bank.getBlockNN()),
                new ItemStack(PowerToolObject.block_cap_bank.getBlockNN()));

        // Vial Extractor
        MetaTileEntityLoader.registerMachineRecipe(true,
                EIOMetaTileEntities.VIAL_EXTRACTOR, "VRV", "PHF", "WCW",
                'V', GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_soul_vial"),
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
                'B', GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_iron_bars"));

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

        if (EnderIOConfigHolder.addShapelessRecipeMachines) {
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
        if (Mods.AppliedEnergistics2.isModLoaded()) {
            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                    .inputs(new ItemStack(ModObject.block_infinity.getBlockNN(), 4, 2))
                    .outputs(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                    .duration(500).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // Fused Quartz
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(block, Materials.NetherQuartz, 1)
                .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_fused_quartz"))
                .duration(56).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Quartz Clear Glass
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_fused_quartz"))
                .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_fused_glass"))
                .duration(56).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        if (ConfigHolder.recipes.hardIronRecipes) {
            // Dark Iron Bars
            ModHandler.addShapedRecipe(true, "dark_iron_bars",
                    GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_iron_bars", 8, 0),
                    " h ", "SSS", "SSS",
                    'S', new UnificationEntry(stick, GTEMaterials.DarkSteel));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(stick, GTEMaterials.DarkSteel, 3)
                    .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_iron_bars", 4, 0))
                    .duration(300).EUt(4)
                    .withRecycling()
                    .buildAndRegister();

            // End Steel Bars
            ModHandler.addShapedRecipe(true, "end_steal_bars",
                    GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_end_iron_bars", 8, 0),
                    " h ", "SSS", "SSS",
                    'S', new UnificationEntry(stick, GTEMaterials.EndSteel));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .input(stick, GTEMaterials.EndSteel, 3)
                    .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_end_iron_bars", 4, 0))
                    .duration(300).EUt(4)
                    .withRecycling()
                    .buildAndRegister();
        }

        // Dark Steel Anvil
        if (ConfigHolder.recipes.hardAdvancedIronRecipes) {
            ModHandler.addShapedRecipe(true, "dark_anvil",
                    GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_anvil"),
                    "BBB", "SBS", "PBP",
                    'B', new UnificationEntry(block, GTEMaterials.DarkSteel),
                    'S', new UnificationEntry(screw, GTEMaterials.DarkSteel),
                    'P', new UnificationEntry(plate, GTEMaterials.DarkSteel));
        }
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_ANVIL)
                .fluidInputs(GTEMaterials.DarkSteel.getFluid(L * 31))
                .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_anvil"))
                .duration(1680).EUt(16)
                .buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_ANVIL)
                .input(ingot, GTEMaterials.DarkSteel, 31)
                .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_anvil"))
                .duration(1680).EUt(16)
                .buildAndRegister();

        // Dark Steel Trapdoor
        if (ConfigHolder.recipes.hardAdvancedIronRecipes) {
            ModHandler.addShapedRecipe(true, "dark_steel_trapdoor",
                    GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_trapdoor"),
                    "SPS", "PTP", "sPd",
                    'S', new UnificationEntry(screw, GTEMaterials.DarkSteel),
                    'P', new UnificationEntry(plate, GTEMaterials.DarkSteel),
                    'T', new ItemStack(Blocks.IRON_TRAPDOOR));
        }
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(plate, GTEMaterials.DarkSteel, 4)
                .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_trapdoor"))
                .duration(100).EUt(16)
                .withRecycling()
                .buildAndRegister();

        // Dark Steel Door
        if (ConfigHolder.recipes.hardAdvancedIronRecipes) {
            ModHandler.addShapedRecipe(true, "dark_steel_door",
                    GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_door"),
                    "PTh", "PRS", "PPd",
                    'P', new UnificationEntry(plate, GTEMaterials.DarkSteel),
                    'T', GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_iron_bars"),
                    'R', new UnificationEntry(ring, GTEMaterials.DarkSteel),
                    'S', new UnificationEntry(screw, GTEMaterials.DarkSteel));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(plate, GTEMaterials.DarkSteel, 4)
                    .inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_iron_bars"))
                    .fluidInputs(GTEMaterials.DarkSteel.getFluid(L / 9))
                    .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_door"))
                    .duration(400).EUt(VA[ULV])
                    .withRecycling()
                    .buildAndRegister();
        } else {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(6)
                    .input(plate, GTEMaterials.DarkSteel, 6)
                    .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_door"))
                    .duration(100).EUt(VH[LV])
                    .withRecycling()
                    .buildAndRegister();
        }

        // Dark Steel Ladder
        if (ConfigHolder.recipes.hardAdvancedIronRecipes) {
            ModHandler.addShapedRecipe(true, "dark_steel_ladder",
                    GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_ladder"),
                    "SrS", "SRS", "ShS",
                    'S', new UnificationEntry(stick, GTEMaterials.DarkSteel),
                    'R', new UnificationEntry(bolt, GTEMaterials.DarkSteel));
        } else {
            ModHandler.addShapedRecipe(true, "dark_steel_ladder",
                    GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_ladder", 3, 0),
                    "S S", "SSS", "S S",
                    'S', new UnificationEntry(stick, GTEMaterials.DarkSteel));
        }
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .input(stick, GTEMaterials.DarkSteel, 7)
                .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_ladder",
                        ConfigHolder.recipes.hardWoodRecipes ? 2 : 3, 0))
                .EUt(1).duration(40)
                .withRecycling()
                .buildAndRegister();

        // Reinforced Obsidian
        ModHandler.addShapedRecipe(true, "reinforced_obsidian",
                GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_reinforced_obsidian"),
                "DBD", "BOB", "DBD",
                'D', GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_material", 1, 20),
                'B', GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_iron_bars"),
                'O', new UnificationEntry(block, Materials.Obsidian));

        // Conduits
        if (Mods.EnderIOConduits.isModLoaded()) {
            // Item Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(pipeSmallItem, Materials.Electrum, 1)
                    .input(plate, GTEMaterials.PulsatingIron, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(ConduitObject.item_item_conduit.getItemNN())
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                    .withRecycling()
                    .buildAndRegister();

            // Fluid Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(pipeNormalFluid, Materials.Copper, 1)
                    .input(plate, GTEMaterials.ElectricalSteel, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(ConduitObject.item_liquid_conduit.getItemNN())
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                    .withRecycling()
                    .buildAndRegister();

            // Pressurized Fluid Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(pipeNormalFluid, Materials.Steel, 1)
                    .input(plate, GTEMaterials.DarkSteel, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(ConduitObject.item_liquid_conduit.getItemNN(), 1, 1)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                    .withRecycling()
                    .buildAndRegister();

            // Ender Fluid Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(pipeTinyFluid, Materials.Polytetrafluoroethylene, 1)
                    .input(plate, GTEMaterials.VibrantAlloy, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(ConduitObject.item_liquid_conduit.getItemNN(), 1, 2)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier + 2])
                    .withRecycling()
                    .buildAndRegister();

            // Energy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.Gold, 1)
                    .input(plate, GTEMaterials.ConductiveIron, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(ConduitObject.item_power_conduit.getItemNN())
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                    .withRecycling()
                    .buildAndRegister();

            // Enhaned Energy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.Aluminium, 1)
                    .input(plate, GTEMaterials.EnergeticAlloy, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(ConduitObject.item_power_conduit.getItemNN(), 1, 1)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier + 1])
                    .withRecycling()
                    .buildAndRegister();

            // Ender Energy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.Osmium, 1)
                    .input(plate, GTEMaterials.VibrantAlloy, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(ConduitObject.item_power_conduit.getItemNN(), 1, 2)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier + 2])
                    .withRecycling()
                    .buildAndRegister();

            // Redstone Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.RedAlloy, 1)
                    .input(plate, GTEMaterials.RedstoneAlloy, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(ConduitObject.item_redstone_conduit.getItemNN())
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                    .withRecycling()
                    .buildAndRegister();

            // Crude Endergy Conduitr
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.Lead, 1)
                    .input(plate, Materials.TinAlloy, 1)
                    .fluidInputs(Materials.Tin.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN())
                    .duration(100).EUt(VA[ULV])
                    .withRecycling()
                    .buildAndRegister();

            // Iron Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.Tin, 1)
                    .input(plate, GTEMaterials.ConductiveIron, 1)
                    .fluidInputs(Materials.Tin.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 1)
                    .duration(100).EUt(VA[LV])
                    .withRecycling()
                    .buildAndRegister();

            // Aluminium Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.Nickel, 1)
                    .input(plate, Materials.Aluminium, 1)
                    .fluidInputs(Materials.Tin.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 2)
                    .duration(100).EUt(VA[LV])
                    .withRecycling()
                    .buildAndRegister();

            // Gold Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.Copper, 1)
                    .input(plate, Materials.Gold, 1)
                    .fluidInputs(Materials.SolderingAlloy.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 3)
                    .duration(100).EUt(VA[MV])
                    .withRecycling()
                    .buildAndRegister();

            // Copper Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.AnnealedCopper, 1)
                    .input(plate, Materials.Copper, 1)
                    .fluidInputs(Materials.SolderingAlloy.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 4)
                    .duration(100).EUt(VA[MV])
                    .withRecycling()
                    .buildAndRegister();

            // Silver Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.Electrum, 1)
                    .input(plate, Materials.Silver, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 5)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                    .withRecycling()
                    .buildAndRegister();

            // Electrum Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.BlackSteel, 1)
                    .input(plate, Materials.Electrum, 1)
                    .fluidInputs(Materials.Polyethylene.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 6)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                    .withRecycling()
                    .buildAndRegister();

            // Electrum Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.TungstenSteel, 1)
                    .input(plate, GTEMaterials.EnergeticSilver, 1)
                    .fluidInputs(Materials.Epoxy.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 7)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier])
                    .withRecycling()
                    .buildAndRegister();

            // Crystalline Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.HSSG, 1)
                    .input(plate, GTEMaterials.CrystallineAlloy, 1)
                    .fluidInputs(Materials.Epoxy.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 8)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier + 1])
                    .withRecycling()
                    .buildAndRegister();

            // Crystalline Pink Slime Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.NiobiumTitanium, 1)
                    .input(plate, GTEMaterials.CrystallinePinkSlime, 1)
                    .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 9)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier + 1])
                    .withRecycling()
                    .buildAndRegister();

            // Melodic Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.Naquadah, 1)
                    .input(plate, GTEMaterials.MelodicAlloy, 1)
                    .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 10)
                    .duration(100).EUt(VA[GTEValues.eioVoltageTier + 2])
                    .withRecycling()
                    .buildAndRegister();

            // Stellar Endergy Conduit
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .input(wireGtSingle, Materials.EnrichedNaquadahTriniumEuropiumDuranide, 1)
                    .input(plate, GTEMaterials.StellarAlloy, 1)
                    .fluidInputs(Materials.Polybenzimidazole.getFluid(144))
                    .output(EndergyObject.itemEndergyConduit.getItemNN(), 1, 11)
                    .duration(100).EUt(VA[UV])
                    .withRecycling()
                    .buildAndRegister();

            if (Mods.AppliedEnergistics2.isModLoaded() && Mods.EnderIOAE2Conduits.isModLoaded()) {
                // ME Conduit
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                        .input("craftGlassCable", 4)
                        .input(plate, Materials.StainlessSteel, 1)
                        .fluidInputs(GTEMaterials.ConductiveIron.getFluid(144))
                        .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_me_conduit", 4, 0))
                        .duration(100).EUt(VA[GTEValues.ae2VoltageTier])
                        .withRecycling()
                        .buildAndRegister();

                // ME Dense Conduit
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                        .inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_me_conduit", 16, 0))
                        .input(plate, Materials.Titanium, 1)
                        .fluidInputs(GTEMaterials.EnergeticAlloy.getFluid(144))
                        .outputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_me_conduit", 4, 1))
                        .duration(100).EUt(VA[GTEValues.ae2VoltageTier + 1])
                        .withRecycling()
                        .buildAndRegister();
            }
        }
    }
}
