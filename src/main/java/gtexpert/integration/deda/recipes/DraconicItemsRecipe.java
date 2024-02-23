package gtexpert.integration.deda.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.brandon3055.draconicevolution.DEFeatures;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.core.GTUtil;
import gtexpert.integration.deda.recipemaps.GTEDraconicRecipeMaps;

public class DraconicItemsRecipe {

    public static void init() {
        // Dragon Egg
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(MetaItems.STEM_CELLS, 32)
                .input(Items.EGG, 16)
                .fluidInputs(Materials.LiquidEnderAir.getFluid(8000))
                .output(Blocks.DRAGON_EGG, 1)
                .duration(2000).EUt(VH[GTEValues.dedaVoltageTier])
                .buildAndRegister();

        // ########################################
        // Draconic Evolution
        // ########################################
        // Dragon Heart
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(MetaItems.STEM_CELLS, 8)
                .input(Items.EGG, 4)
                .fluidInputs(GTEMaterials.Draconium.getFluid(1152))
                .output(DEFeatures.dragonHeart, 1)
                .duration(500).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();

        // Advanced Dislocators
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Draconium, 4)
                .input(DEFeatures.wyvernCore, 1)
                .fluidInputs(Materials.EnderPearl.getFluid(576))
                .output(DEFeatures.dislocatorAdvanced, 1)
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();

        // Wyvern Core
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_core"));
        RecipeBuilder<?> builderWyvernCore1 = RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier - 2), 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, GTEMaterials.Draconium, 16)
                .fluidInputs(Materials.Tin.getFluid(144))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier]);
        if (Mods.EnderIO.isModLoaded()) {
            builderWyvernCore1
                    .inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_capacitor_energetic_silver", 4, 0));
        } else {
            builderWyvernCore1.input(GTUtil.oreDictionaryCircuit(GTEValues.dedaVoltageTier - 2), 4);
        }
        builderWyvernCore1.buildAndRegister();

        RecipeBuilder<?> builderWyvernCore2 = RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier - 2), 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, GTEMaterials.Draconium, 16)
                .fluidInputs(Materials.SolderingAlloy.getFluid(72))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier]);
        if (Mods.EnderIO.isModLoaded()) {
            builderWyvernCore2
                    .inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_capacitor_energetic_silver", 4, 0));
        } else {
            builderWyvernCore2.input(GTUtil.oreDictionaryCircuit(GTEValues.dedaVoltageTier - 2), 4);
        }
        builderWyvernCore2.buildAndRegister();

        // Draconic Core
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconic_core"));
        RecipeBuilder<?> builderDraconicCore1 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier - 1), 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, GTEMaterials.Draconium, 16)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier + 1]);
        if (Mods.EnderIO.isModLoaded()) {
            builderDraconicCore1.inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_capacitor_crystalline", 4, 0));
        } else {
            builderDraconicCore1.input(GTUtil.oreDictionaryCircuit(GTEValues.dedaVoltageTier - 2), 4);
        }
        builderDraconicCore1.buildAndRegister();

        RecipeBuilder<?> builderDraconicCore2 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier - 1), 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, GTEMaterials.Draconium, 16)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier + 1]);
        if (Mods.EnderIO.isModLoaded()) {
            builderDraconicCore2.inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_capacitor_crystalline", 4, 0));
        } else {
            builderDraconicCore2.input(GTUtil.oreDictionaryCircuit(GTEValues.dedaVoltageTier - 2), 4);
        }
        builderDraconicCore2.buildAndRegister();

        // Awakened Core
        RecipeBuilder<?> builderAwakenedCore1 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier), 1)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 16)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(VA[GTEValues.dedaVoltageTier + 2]);
        if (Mods.EnderIO.isModLoaded()) {
            builderAwakenedCore1.inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_capacitor_melodic", 4, 0));
        } else {
            builderAwakenedCore1.input(GTUtil.oreDictionaryCircuit(GTEValues.dedaVoltageTier - 1), 4);
        }
        builderAwakenedCore1.buildAndRegister();

        RecipeBuilder<?> builderAwakenedCore2 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier), 1)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 16)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(VA[GTEValues.dedaVoltageTier + 2]);
        if (Mods.EnderIO.isModLoaded()) {
            builderAwakenedCore2.inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_capacitor_melodic", 4, 0));
        } else {
            builderAwakenedCore2.input(GTUtil.oreDictionaryCircuit(GTEValues.dedaVoltageTier - 1), 4);
        }
        builderAwakenedCore2.buildAndRegister();

        // Wyvern Energy Core
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_energy_core"));
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier - 1), 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, GTEMaterials.Draconium, 24)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier - 1), 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, GTEMaterials.Draconium, 24)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();

        // Draconic Energy Core
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconic_energy_core"));
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier + 1), 1)
                .input(DEFeatures.draconicCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 24)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier + 1), 1)
                .input(DEFeatures.draconicCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 24)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();

        // Energy Core
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("energy_storage_core"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[GTEValues.dedaVoltageTier])
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 2)
                .input(plate, GTEMaterials.Draconium, 6)
                .outputs(new ItemStack(DEFeatures.energyStorageCore))
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Energy Pylon
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("energy_pylon"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[GTEValues.dedaVoltageTier])
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, GTEMaterials.Draconium, 4)
                .input(plate, Materials.Emerald, 2)
                .input(gem, Materials.Diamond, 1)
                .input(Items.ENDER_EYE, 1)
                .outputs(new ItemStack(DEFeatures.energyPylon))
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Reactor Stabilizer Frame
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("reactor_part"));
        RecipeBuilder<?> builderRSF = RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, GTEMaterials.AwakenedDraconium, 1)
                .outputs(new ItemStack(DEFeatures.reactorPart))
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier]);
        if (Mods.EnderIO.isModLoaded()) {
            builderRSF.input(stick, GTEMaterials.DarkSteel, 6);
        } else {
            builderRSF.input(stick, Materials.BlackSteel, 6);
        }
        builderRSF.buildAndRegister();

        // Reactor Stabilizer Inner Rotor
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("reactor_part_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, GTEMaterials.Draconium, 3)
                .input(plate, GTEMaterials.AwakenedDraconium, 2)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 1))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Reactor Stabilizer Outer Rotor
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("reactor_part_2"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, Materials.Diamond, 3)
                .input(plate, GTEMaterials.AwakenedDraconium, 2)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 2))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Reactor Stabilizer Rotor Assembly
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("reactor_part_3"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, GTEMaterials.Draconium, 2)
                .inputs(new ItemStack(DEFeatures.reactorPart, 2, 1))
                .inputs(new ItemStack(DEFeatures.reactorPart, 2, 2))
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 3))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Reactor Stabilizer Focus Ring
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("reactor_part_4"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, Materials.Diamond, 4)
                .input(screw, Materials.Gold, 4)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 4))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Reactor Stabilizer
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 1, 3)
                .input(DEFeatures.reactorPart, 1, 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .fluidInputs(GTEMaterials.AwakenedDraconium.getFluid(1152))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .outputs(new ItemStack(DEFeatures.reactorComponent))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(38400)
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 1, 3)
                .input(DEFeatures.reactorPart, 1, 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .fluidInputs(GTEMaterials.AwakenedDraconium.getFluid(1152))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .outputs(new ItemStack(DEFeatures.reactorComponent))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(38400)
                .buildAndRegister();

        // Reactor Energy Injector
        SimpleRecipeBuilder builderREI1 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 4, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .fluidInputs(GTEMaterials.Draconium.getFluid(576))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 1))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(38400);
        if (Mods.EnderIO.isModLoaded()) {
            builderREI1.input(plate, GTEMaterials.DarkSteel, 4);
        } else {
            builderREI1.input(plate, Materials.BlackSteel, 4);
        }
        builderREI1.buildAndRegister();

        SimpleRecipeBuilder builderREI2 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 4, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .fluidInputs(GTEMaterials.Draconium.getFluid(576))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 1))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(38400);
        if (Mods.EnderIO.isModLoaded()) {
            builderREI2.input(plate, GTEMaterials.DarkSteel, 4);
        } else {
            builderREI2.input(plate, Materials.BlackSteel, 4);
        }
        builderREI2.buildAndRegister();

        // Draconic Reactor Core
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.chaosShard)
                .fluidInputs(GTEMaterials.Draconium.getFluid(1152))
                .fluidInputs(GTEMaterials.AwakenedDraconium.getFluid(1152))
                .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 64000))
                .outputs(new ItemStack(DEFeatures.reactorCore))
                .duration(1200).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();

        // Upgrade Key (RF Capacity)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade"));
        ModHandler.addShapedRecipe(true, "tool_upgrade", new ItemStack(DEFeatures.toolUpgrade),
                "LCL", "DED", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'E', new ItemStack(DEFeatures.wyvernEnergyCore));

        // Upgrade Key (Dig Speed)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_1"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_1", new ItemStack(DEFeatures.toolUpgrade, 1, 1),
                "LCL", "DPD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'P', Items.GOLDEN_PICKAXE);

        // Upgrade Key (Dig AOE)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_2"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_2", new ItemStack(DEFeatures.toolUpgrade, 1, 2),
                "LCL", "DED", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'E', Mods.AppliedEnergistics2.isModLoaded() ?
                        Mods.AppliedEnergistics2.getItem("material", 1, 9) :
                        Mods.Vanilla.getItem("ender_pearl"));

        // Upgrade Key (Attack Damage)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_3"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_3", new ItemStack(DEFeatures.toolUpgrade, 1, 3),
                "LCL", "DSD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'S', Items.GOLDEN_SWORD);

        // Upgrade Key (Attack AOE)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_4"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_4", new ItemStack(DEFeatures.toolUpgrade, 1, 4),
                "LCL", "DSD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'S', Items.DIAMOND_SWORD);

        // Upgrade Key (Arrow Damage)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_5"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_5", new ItemStack(DEFeatures.toolUpgrade, 1, 5),
                "LCG", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'G', OreDictUnifier.get(screw, Materials.RoseGold, 1),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Items.ARROW);

        // Upgrade Key (Draw Speed)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_6"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_6", new ItemStack(DEFeatures.toolUpgrade, 1, 6),
                "LCL", "DAD", "LCG",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'G', OreDictUnifier.get(stickLong, Materials.RoseGold, 1),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Mods.EnderIO.isModLoaded() ?
                        GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_end_steel_bow") :
                        Mods.Vanilla.getItem("bow"));

        // Upgrade Key (Arrow Speed)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_7"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_7", new ItemStack(DEFeatures.toolUpgrade, 1, 7),
                "LCF", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'F', Items.FEATHER,
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Items.ARROW);

        // Upgrade Key (Shield Capacity)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_8"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_8", new ItemStack(DEFeatures.toolUpgrade, 1, 8),
                "LCL", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Mods.EnderIO.isModLoaded() ?
                        GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_end_steel_chestplate") :
                        Mods.Vanilla.getItem("iron_chestplate"));

        // Upgrade Key (Shield Recovery)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_9"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_9", new ItemStack(DEFeatures.toolUpgrade, 1, 9),
                "LCL", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Mods.EnderIO.isModLoaded() ?
                        GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_stellar_alloy_chestplate") :
                        Mods.Vanilla.getItem("diamond_chestplate"));

        // Upgrade Key (Movement Speed)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_10"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_10", new ItemStack(DEFeatures.toolUpgrade, 1, 10),
                "LCL", "DSD", "LBL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'S', Items.GOLDEN_BOOTS,
                'B', OreDictUnifier.get(block, Materials.Redstone, 1));

        // Upgrade Key (Jump Boost)
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("tool_upgrade_11"));
        ModHandler.addShapedRecipe(true, "tool_upgrade_11", new ItemStack(DEFeatures.toolUpgrade, 1, 11),
                "LCL", "DSD", "LBL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'S', Items.GOLDEN_BOOTS,
                'B', Blocks.SLIME_BLOCK);

        // ########################################
        // Draconic Additions
        // ########################################
        // Chaotic Core
        RecipeBuilder<?> builderChaoticCore1 = GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier + 1), 1)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 32)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(600).EUt(VA[GTEValues.dedaVoltageTier + 3]);
        if (Mods.EnderIO.isModLoaded()) {
            builderChaoticCore1.inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_capacitor_stellar", 4, 0));
        } else {
            builderChaoticCore1.input(GTUtil.oreDictionaryCircuit(GTEValues.dedaVoltageTier), 4);
        }
        builderChaoticCore1.buildAndRegister();

        RecipeBuilder<?> builderChaoticCore2 = GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier + 1), 1)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 32)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(600).EUt(VA[GTEValues.dedaVoltageTier + 3]);
        if (Mods.EnderIO.isModLoaded()) {
            builderChaoticCore2.inputs(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_capacitor_stellar", 4, 0));
        } else {
            builderChaoticCore2.input(GTUtil.oreDictionaryCircuit(GTEValues.dedaVoltageTier), 4);
        }
        builderChaoticCore2.buildAndRegister();

        // Chaotic Energy Core
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier + 1), 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 32)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(VA[GTEValues.dedaVoltageTier + 3])
                .buildAndRegister();
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTUtil.circuitBoard(GTEValues.dedaVoltageTier + 1), 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 32)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(VA[GTEValues.dedaVoltageTier + 3])
                .buildAndRegister();
    }
}
