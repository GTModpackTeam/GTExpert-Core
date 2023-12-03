package gtexpert.loaders.recipe.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.GTEConfigHolder.dedaIntegration;

import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import com.brandon3055.draconicevolution.DEFeatures;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.nbtmatch.*;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.draconic.GTEDraconicRecipeMaps;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.metatileentities.GTEMultiMetaTileEntities;
import gtexpert.integration.gt.GTHelper;

public class DraconicRecipeLoader {

    public static void init() {
        fluid();
        materials();
        items();
        blocks();
        tools();
    }

    private static void fluid() {
        // Cryotheum
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 1000))
                .fluidOutputs(GTEMaterials.Cryotheum.getFluid(1000))
                .duration(400).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .input(dust, Materials.Electrotine, 1)
                .fluidInputs(Materials.Ice.getFluid(4000))
                .fluidInputs(Materials.EnderPearl.getFluid(144))
                .fluidOutputs(GTEMaterials.Cryotheum.getFluid(200))
                .duration(150).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Pyrotheum
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.Sulfur, 1)
                .fluidInputs(Materials.Argon.getFluid(200))
                .fluidInputs(Materials.Blaze.getFluid(2304))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 1000))
                .blastFurnaceTemp(7200)
                .duration(200).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.Sulfur, 1)
                .fluidInputs(Materials.Blaze.getFluid(2304))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 1000))
                .blastFurnaceTemp(7200)
                .duration(1200).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();
    }

    private static void materials() {
        // ########################################
        // Draconic Evolution
        // ########################################
        // Dragon Dust
        RecipeBuilder<?> builderDD = RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Materials.Iridium, 1)
                .fluidInputs(Materials.SaltWater.getFluid(1000))
                .fluidInputs(Materials.EnderEye.getFluid(144))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, GTEMaterials.Dragon, 2)
                .duration(600).EUt(VA[dedaIntegration.voltageTier]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderDD.input(dust, GTEMaterials.EndSteel, 1);
        } else {
            builderDD.input(dust, Materials.Endstone, 1);
        }
        builderDD.buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(Blocks.DRAGON_EGG)
                .output(dust, GTEMaterials.Dragon, 8)
                .duration(200).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Chaos Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Dragon, 8)
                .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                .fluidInputs(GTEMaterials.AwakenedDraconium.getFluid(1152))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, GTEMaterials.Chaos, 2)
                .duration(1200).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.chaosShard, 1, 1))
                .output(dust, GTEMaterials.Chaos, 1)
                .duration(200).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Chaos, 1)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .output(DEFeatures.chaosShard, 1, 1)
                .duration(1200).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Chaos, 1)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(new ItemStack(DEFeatures.chaosShard, 1, 1), 7000, 1000)
                .duration(2400).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // Draconium Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Dragon, 1)
                .input(dust, Materials.Obsidian, 1)
                .fluidInputs(Materials.LiquidEnderAir.getFluid(8000))
                .fluidInputs(Materials.EnderPearl.getFluid(576))
                .output(dust, GTEMaterials.Draconium, 2)
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Draconium Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconium_block"));
        ModHandler.addMirroredShapedRecipe("de_draconium_block", new ItemStack(DEFeatures.draconiumBlock), "B", 'B',
                OreDictUnifier.get(block, GTEMaterials.Draconium));
        ModHandler.addMirroredShapedRecipe("ceu_draconium_block", OreDictUnifier.get(block, GTEMaterials.Draconium),
                "B", 'B',
                new ItemStack(DEFeatures.draconiumBlock));

        // Awakened Draconium Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconic_block"));
        ModHandler.addMirroredShapedRecipe("de_draconic_block", new ItemStack(DEFeatures.draconicBlock), "B", 'B',
                OreDictUnifier.get(block, GTEMaterials.AwakenedDraconium));
        ModHandler.addMirroredShapedRecipe("ceu_draconic_block",
                OreDictUnifier.get(block, GTEMaterials.AwakenedDraconium), "B",
                'B', new ItemStack(DEFeatures.draconicBlock));
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, GTEMaterials.Draconium, 4)
                .output(block, GTEMaterials.AwakenedDraconium, 3)
                .output(dustSmall, Materials.DarkAsh, 1)
                .explosivesAmount(2)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, GTEMaterials.Draconium, 4)
                .output(block, GTEMaterials.AwakenedDraconium, 3)
                .output(dustSmall, Materials.DarkAsh, 1)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
    }

    private static void items() {
        // Dragon Egg
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(MetaItems.STEM_CELLS, 32)
                .input(Items.EGG, 16)
                .fluidInputs(Materials.LiquidEnderAir.getFluid(8000))
                .output(Blocks.DRAGON_EGG, 1)
                .duration(2000).EUt(VH[dedaIntegration.voltageTier])
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
                .duration(500).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // Advanced Dislocators
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Draconium, 4)
                .input(DEFeatures.wyvernCore, 1)
                .fluidInputs(Materials.EnderPearl.getFluid(576))
                .output(DEFeatures.dislocatorAdvanced, 1)
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Wyvern Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_core"));
        RecipeBuilder<?> builderWyvernCore1 = RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier - 2), 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, GTEMaterials.Draconium, 16)
                .fluidInputs(Materials.Tin.getFluid(144))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(VA[dedaIntegration.voltageTier]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderWyvernCore1.inputs(getModItem(GTEValues.MODID_EIO, "item_capacitor_energetic_silver", 4, 0));
        } else {
            builderWyvernCore1.input(GTHelper.oreDictionaryCircuit(dedaIntegration.voltageTier - 2), 4);
        }
        builderWyvernCore1.buildAndRegister();

        RecipeBuilder<?> builderWyvernCore2 = RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier - 2), 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, GTEMaterials.Draconium, 16)
                .fluidInputs(Materials.SolderingAlloy.getFluid(72))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(VA[dedaIntegration.voltageTier]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderWyvernCore2.inputs(getModItem(GTEValues.MODID_EIO, "item_capacitor_energetic_silver", 4, 0));
        } else {
            builderWyvernCore2.input(GTHelper.oreDictionaryCircuit(dedaIntegration.voltageTier - 2), 4);
        }
        builderWyvernCore2.buildAndRegister();

        // Draconic Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconic_core"));
        RecipeBuilder<?> builderDraconicCore1 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier - 1), 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, GTEMaterials.Draconium, 16)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(200).EUt(VA[dedaIntegration.voltageTier + 1]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderDraconicCore1.inputs(getModItem(GTEValues.MODID_EIO, "item_capacitor_crystalline", 4, 0));
        } else {
            builderDraconicCore1.input(GTHelper.oreDictionaryCircuit(dedaIntegration.voltageTier - 2), 4);
        }
        builderDraconicCore1.buildAndRegister();

        RecipeBuilder<?> builderDraconicCore2 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier - 1), 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, GTEMaterials.Draconium, 16)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(200).EUt(VA[dedaIntegration.voltageTier + 1]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderDraconicCore2.inputs(getModItem(GTEValues.MODID_EIO, "item_capacitor_crystalline", 4, 0));
        } else {
            builderDraconicCore2.input(GTHelper.oreDictionaryCircuit(dedaIntegration.voltageTier - 2), 4);
        }
        builderDraconicCore2.buildAndRegister();

        // Awakened Core
        RecipeBuilder<?> builderAwakenedCore1 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier), 1)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 16)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(VA[dedaIntegration.voltageTier + 2]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderAwakenedCore1.inputs(getModItem(GTEValues.MODID_EIO, "item_capacitor_melodic", 4, 0));
        } else {
            builderAwakenedCore1.input(GTHelper.oreDictionaryCircuit(dedaIntegration.voltageTier - 1), 4);
        }
        builderAwakenedCore1.buildAndRegister();

        RecipeBuilder<?> builderAwakenedCore2 = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier), 1)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 16)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(VA[dedaIntegration.voltageTier + 2]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderAwakenedCore2.inputs(getModItem(GTEValues.MODID_EIO, "item_capacitor_melodic", 4, 0));
        } else {
            builderAwakenedCore2.input(GTHelper.oreDictionaryCircuit(dedaIntegration.voltageTier - 1), 4);
        }
        builderAwakenedCore2.buildAndRegister();

        // Wyvern Energy Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_energy_core"));
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier - 1), 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, GTEMaterials.Draconium, 24)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier - 1), 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, GTEMaterials.Draconium, 24)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Draconic Energy Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconic_energy_core"));
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier + 1), 1)
                .input(DEFeatures.draconicCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 24)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(200).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier + 1), 1)
                .input(DEFeatures.draconicCore, 1)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 24)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(200).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // Energy Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_storage_core"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[dedaIntegration.voltageTier])
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 2)
                .input(plate, GTEMaterials.Draconium, 6)
                .outputs(new ItemStack(DEFeatures.energyStorageCore, 1, 0))
                .duration(200).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Energy Pylon
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_pylon"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[dedaIntegration.voltageTier])
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, GTEMaterials.Draconium, 4)
                .input(plate, Materials.Emerald, 2)
                .input(gem, Materials.Diamond, 1)
                .input(Items.ENDER_EYE, 1)
                .outputs(new ItemStack(DEFeatures.energyPylon, 1, 0))
                .duration(200).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Reactor Stabilizer Frame
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part"));
        RecipeBuilder<?> builderRSF = RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, GTEMaterials.AwakenedDraconium, 1)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 0))
                .duration(200).EUt(VA[dedaIntegration.voltageTier]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderRSF.input(stick, GTEMaterials.DarkSteel, 6);
        } else {
            builderRSF.input(stick, Materials.BlackSteel, 6);
        }
        builderRSF.buildAndRegister();

        // Reactor Stabilizer Inner Rotor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, GTEMaterials.Draconium, 3)
                .input(plate, GTEMaterials.AwakenedDraconium, 2)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 1))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Reactor Stabilizer Outer Rotor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part_2"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, Materials.Diamond, 3)
                .input(plate, GTEMaterials.AwakenedDraconium, 2)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 2))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Reactor Stabilizer Rotor Assembly
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part_3"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, GTEMaterials.Draconium, 2)
                .inputs(new ItemStack(DEFeatures.reactorPart, 2, 1))
                .inputs(new ItemStack(DEFeatures.reactorPart, 2, 2))
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 3))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Reactor Stabilizer Focus Ring
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part_4"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, Materials.Diamond, 4)
                .input(screw, Materials.Gold, 4)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 4))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
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
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 0))
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
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 0))
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
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
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
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderREI2.input(plate, GTEMaterials.DarkSteel, 4);
        } else {
            builderREI2.input(plate, Materials.BlackSteel, 4);
        }
        builderREI2.buildAndRegister();

        // Draconic Reactor Core
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.chaosShard, 1, 0)
                .fluidInputs(GTEMaterials.Draconium.getFluid(1152))
                .fluidInputs(GTEMaterials.AwakenedDraconium.getFluid(1152))
                .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 64000))
                .outputs(new ItemStack(DEFeatures.reactorCore, 1, 0))
                .duration(1200).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Upgrade Key (RF Capacity)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade"));
        ModHandler.addShapedRecipe("tool_upgrade", new ItemStack(DEFeatures.toolUpgrade, 1, 0),
                "LCL", "DED", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'E', new ItemStack(DEFeatures.wyvernEnergyCore, 1, 0));

        // Upgrade Key (Dig Speed)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_1"));
        ModHandler.addShapedRecipe("tool_upgrade_1", new ItemStack(DEFeatures.toolUpgrade, 1, 1),
                "LCL", "DPD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'P', Items.GOLDEN_PICKAXE);

        // Upgrade Key (Dig AOE)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_2"));
        ModHandler.addShapedRecipe("tool_upgrade_2", new ItemStack(DEFeatures.toolUpgrade, 1, 2),
                "LCL", "DED", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'E', Loader.isModLoaded(GTEValues.MODID_AE) ?
                        getModItem(GTEValues.MODID_AE, "material", 1, 9) :
                        getModItem(GTEValues.MODID_VANILLA, "ender_pearl", 1, 0));

        // Upgrade Key (Attack Damage)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_3"));
        ModHandler.addShapedRecipe("tool_upgrade_3", new ItemStack(DEFeatures.toolUpgrade, 1, 3),
                "LCL", "DSD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'S', Items.GOLDEN_SWORD);

        // Upgrade Key (Attack AOE)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_4"));
        ModHandler.addShapedRecipe("tool_upgrade_4", new ItemStack(DEFeatures.toolUpgrade, 1, 4),
                "LCL", "DSD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'S', Items.DIAMOND_SWORD);

        // Upgrade Key (Arrow Damage)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_5"));
        ModHandler.addShapedRecipe("tool_upgrade_5", new ItemStack(DEFeatures.toolUpgrade, 1, 5),
                "LCG", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'G', OreDictUnifier.get(screw, Materials.RoseGold, 1),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Items.ARROW);

        // Upgrade Key (Draw Speed)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_6"));
        ModHandler.addShapedRecipe("tool_upgrade_6", new ItemStack(DEFeatures.toolUpgrade, 1, 6),
                "LCL", "DAD", "LCG",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'G', OreDictUnifier.get(stickLong, Materials.RoseGold, 1),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Loader.isModLoaded(GTEValues.MODID_EIO) ?
                        getModItem(GTEValues.MODID_EIO, "item_end_steel_bow", 1, 0) :
                        getModItem(GTEValues.MODID_VANILLA, "bow", 1, 0));

        // Upgrade Key (Arrow Speed)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_7"));
        ModHandler.addShapedRecipe("tool_upgrade_7", new ItemStack(DEFeatures.toolUpgrade, 1, 7),
                "LCF", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'F', Items.FEATHER,
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Items.ARROW);

        // Upgrade Key (Shield Capacity)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_8"));
        ModHandler.addShapedRecipe("tool_upgrade_8", new ItemStack(DEFeatures.toolUpgrade, 1, 8),
                "LCL", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Loader.isModLoaded(GTEValues.MODID_EIO) ?
                        getModItem(GTEValues.MODID_EIO, "item_end_steel_chestplate", 1, 0) :
                        getModItem(GTEValues.MODID_VANILLA, "iron_chestplate", 1, 0));

        // Upgrade Key (Shield Recovery)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_9"));
        ModHandler.addShapedRecipe("tool_upgrade_9", new ItemStack(DEFeatures.toolUpgrade, 1, 9),
                "LCL", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'A', Loader.isModLoaded(GTEValues.MODID_EIO) ?
                        getModItem(GTEValues.MODID_EIO, "item_stellar_alloy_chestplate", 1, 0) :
                        getModItem(GTEValues.MODID_VANILLA, "diamond_chestplate", 1, 0));

        // Upgrade Key (Movement Speed)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_10"));
        ModHandler.addShapedRecipe("tool_upgrade_10", new ItemStack(DEFeatures.toolUpgrade, 1, 10),
                "LCL", "DSD", "LBL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'S', Items.GOLDEN_BOOTS,
                'B', OreDictUnifier.get(block, Materials.Redstone, 1));

        // Upgrade Key (Jump Boost)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_11"));
        ModHandler.addShapedRecipe("tool_upgrade_11", new ItemStack(DEFeatures.toolUpgrade, 1, 11),
                "LCL", "DSD", "LBL",
                'L', OreDictUnifier.get(plate, Materials.Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, GTEMaterials.Draconium, 1),
                'S', Items.GOLDEN_BOOTS,
                'B', Blocks.SLIME_BLOCK);

        // ########################################
        // Draconic Additions
        // ########################################
        // Chaotic Core
        RecipeBuilder<?> builderChaoticCore1 = GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier + 1), 1)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 32)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(600).EUt(VA[dedaIntegration.voltageTier + 3]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderChaoticCore1.inputs(getModItem(GTEValues.MODID_EIO, "item_capacitor_stellar", 4, 0));
        } else {
            builderChaoticCore1.input(GTHelper.oreDictionaryCircuit(dedaIntegration.voltageTier), 4);
        }
        builderChaoticCore1.buildAndRegister();

        RecipeBuilder<?> builderChaoticCore2 = GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier + 1), 1)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 32)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(600).EUt(VA[dedaIntegration.voltageTier + 3]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderChaoticCore2.inputs(getModItem(GTEValues.MODID_EIO, "item_capacitor_stellar", 4, 0));
        } else {
            builderChaoticCore2.input(GTHelper.oreDictionaryCircuit(dedaIntegration.voltageTier), 4);
        }
        builderChaoticCore2.buildAndRegister();

        // Chaotic Energy Core
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier + 1), 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 32)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polyethylene.getFluid(288))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(VA[dedaIntegration.voltageTier + 3])
                .buildAndRegister();
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(GTHelper.circuitBoard(dedaIntegration.voltageTier + 1), 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(MetaItems.RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, GTEMaterials.AwakenedDraconium, 32)
                .fluidInputs(Materials.Redstone.getFluid(5184))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .fluidInputs(Materials.Polytetrafluoroethylene.getFluid(144))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(400).EUt(VA[dedaIntegration.voltageTier + 3])
                .buildAndRegister();
    }

    private static void blocks() {
        // Dislocator Receptacle
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "dislocator_receptacle"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.infusedObsidian, 1)
                .input(plate, GTEMaterials.Draconium, 7)
                .outputs(new ItemStack(DEFeatures.dislocatorReceptacle, 1, 0))
                .duration(100).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // Energy Infuser
        ModHandler.removeRecipeByOutput(new ItemStack(DEFeatures.energyInfuser, 1, 0));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 3)
                .inputs(new ItemStack(DEFeatures.particleGenerator, 1, 2))
                .input(Blocks.ENCHANTING_TABLE, 1)
                .outputs(new ItemStack(DEFeatures.energyInfuser, 1, 0))
                .duration(600).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Celestial Manipulator
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "celestial_manipulator"));
        RecipeBuilder<?> builderCM = RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.CLOCK, 1)
                .input(plate, GTEMaterials.Draconium, 4)
                .input(stick, Materials.Iron, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.celestialManipulator, 1, 0))
                .duration(600).EUt(VA[dedaIntegration.voltageTier]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderCM.input(stickLong, GTEMaterials.DarkSteel, 4);
            builderCM.input(stick, GTEMaterials.DarkSteel, 4);
        } else {
            builderCM.input(stickLong, Materials.BlackSteel, 4);
            builderCM.input(stick, Materials.BlackSteel, 4);
        }
        builderCM.buildAndRegister();

        // Dislocation Normalization Field Projector
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "item_dislocation_inhibitor"));

        // Particle Generator
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "particle_generator"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(block, Materials.Redstone, 4)
                .input(stick, Materials.Blaze, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.particleGenerator, 1, 0))
                .duration(600).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Energy Core Stabilizer
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "particle_generator_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(block, Materials.Diamond, 4)
                .input(stick, Materials.Blaze, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.particleGenerator, 1, 2))
                .duration(600).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Draconic Fusion Crafter
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[dedaIntegration.voltageTier])
                .input(frameGt, GTEMaterials.Draconium, 4)
                .input(DEFeatures.wyvernCore, 4)
                .input(GTHelper.robotArm(dedaIntegration.voltageTier), 2)
                .input(GTHelper.sensor(LuV), 2)
                .input(GTHelper.emitter(LuV), 2)
                .output(GTEMultiMetaTileEntities.DRACONIUM_FUSION)
                .duration(600).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Draconic Awakened Fusion Crafter
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[UV])
                .input(frameGt, GTEMaterials.AwakenedDraconium, 4)
                .input(DEFeatures.awakenedCore, 4)
                .input(GTHelper.robotArm(UV), 2)
                .input(GTHelper.sensor(UV), 2)
                .input(GTHelper.emitter(UV), 2)
                .output(GTEMultiMetaTileEntities.AWAKENED_DRACONIUM_FUSION)
                .duration(600).EUt(VA[UV])
                .buildAndRegister();

        // Draconum Casing
        ModHandler.addShapedRecipe(true, "casing_draconum",
                GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING,
                                ConfigHolder.recipes.casingsPerCraft),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, GTEMaterials.Draconium),
                'F', new UnificationEntry(frameGt, GTEMaterials.Draconium));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, GTEMaterials.Draconium, 6)
                .input(frameGt, GTEMaterials.Draconium, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING, 2))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Awakened Draconum Casing
        ModHandler.addShapedRecipe(true, "casing_awakened_draconum",
                GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING,
                                ConfigHolder.recipes.casingsPerCraft),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, GTEMaterials.AwakenedDraconium),
                'F', new UnificationEntry(frameGt, GTEMaterials.AwakenedDraconium));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, GTEMaterials.AwakenedDraconium, 6)
                .input(frameGt, GTEMaterials.AwakenedDraconium, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING, 2))
                .duration(100).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // Infused Obsidian
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "infused_obsidian"));
        ModHandler.addShapedRecipe("infused_obsidian", new ItemStack(DEFeatures.infusedObsidian, 1, 0),
                "BOB", "ODO", "BOB",
                'B', Items.BLAZE_POWDER,
                'O', Loader.isModLoaded(GTEValues.MODID_EIO) ?
                        getModItem(GTEValues.MODID_EIO, "block_reinforced_obsidian") :
                        getModItem(GTEValues.MODID_VANILLA, "obsidian"),
                'D', OreDictUnifier.get(dust, GTEMaterials.Draconium));

        // Basic Energy Relay Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_5"));

        // Wyvern Energy Relay Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Materials.Diamond, 4)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 1))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Draconic Energy Relay Crystal
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Materials.Diamond, 4)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(DEFeatures.awakenedCore, 1)
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 2))
                .duration(100).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // Basic Energy I/O Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_2"));

        // Basic Wireless Energy Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_8"));

        // Wyvern Wireless Energy Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_9"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(gem, Materials.EnderPearl, 4)
                .input(gem, Materials.EnderEye, 2)
                .inputs(new ItemStack(DEFeatures.particleGenerator, 2, 0))
                .inputs(new ItemStack(DEFeatures.energyCrystal, 1, 1))
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 7))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Draconic Wireless Energy Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_10"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(gem, Materials.EnderPearl, 4)
                .input(gem, Materials.EnderEye, 2)
                .inputs(new ItemStack(DEFeatures.particleGenerator, 2, 0))
                .inputs(new ItemStack(DEFeatures.energyCrystal, 1, 2))
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 8))
                .duration(100).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // Draconium Chest
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(Blocks.FURNACE, 5)
                .input(DEFeatures.wyvernCore, 2)
                .input(MetaTileEntities.WORKBENCH)
                .input(MetaTileEntities.TUNGSTENSTEEL_CRATE)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(8000))
                .fluidInputs(GTEMaterials.Draconium.getFluid(1152))
                .output(DEFeatures.draconiumChest)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 2000))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // ########################################
        // Draconic Additions
        // ########################################
        // Chaos Liquefier
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .inputNBT(DAFeatures.chaosContainer, 1, NBTMatcher.ANY, NBTCondition.ANY)
                .input(DEFeatures.infusedObsidian, 4)
                .input(DEFeatures.draconicCore, 4)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(32000))
                .output(DAFeatures.chaosLiquefier, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                .duration(200).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // Chaotic Stability Core
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorCore, 4)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(DEFeatures.infusedObsidian, 4)
                .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 48000))
                .output(DAFeatures.chaosStabilizerCore, 1)
                .duration(1200).EUt(VA[UHV])
                .buildAndRegister();

        // Capacitor Supplier
        SimpleRecipeBuilder builderCS = GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(Blocks.END_ROD, 1)
                .input(ring, Materials.Titanium, 4)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                .output(DAFeatures.capacitorSupplier, 1)
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                .duration(100).EUt(VA[dedaIntegration.voltageTier]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderCS.input(plate, GTEMaterials.StellarAlloy, 4);
            builderCS.input(stick, GTEMaterials.StellarAlloy, 4);
        } else {
            builderCS.input(plate, Materials.VanadiumSteel, 4);
            builderCS.input(stick, Materials.VanadiumSteel, 4);
        }
        builderCS.buildAndRegister();
    }

    private static void tools() {
        // ########################################
        // Draconic Evolution
        // ########################################
        // Crystal Binder
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "crystal_binder"));
        ModHandler.addShapedRecipe("crystal_binder", new ItemStack(DEFeatures.crystalBinder, 1, 0),
                "PhP", " R ", " C ",
                'P', OreDictUnifier.get(plate, GTEMaterials.Draconium),
                'R', Loader.isModLoaded(GTEValues.MODID_EIO) ?
                        OreDictUnifier.get(stick, GTEMaterials.EnergeticAlloy) :
                        OreDictUnifier.get(stick, Materials.BlueAlloy),
                'C', DEFeatures.wyvernCore);

        // Wyvern Flux Capacitor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconium_capacitor"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(new ItemStack(DEFeatures.wyvernEnergyCore, 4, 0))
                .input(plate, GTEMaterials.Draconium, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.draconiumCapacitor, 1, 0))
                .duration(100).EUt(VA[dedaIntegration.voltageTier])
                .buildAndRegister();

        // Draconic Flux Capacitor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconium_capacitor_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(new ItemStack(DEFeatures.draconiumCapacitor, 4, 0))
                .input(plate, GTEMaterials.AwakenedDraconium, 4)
                .input(DEFeatures.awakenedCore, 1)
                .outputs(new ItemStack(DEFeatures.draconiumCapacitor, 1, 1))
                .duration(100).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // ########################################
        // Draconic Additions
        // ########################################
        // GTEMaterials.Chaos Container
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .inputNBT(DEFeatures.dislocator, NBTMatcher.ANY, NBTCondition.ANY)
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(48000))
                .outputs(new ItemStack(DAFeatures.chaosContainer, 1))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 12000))
                .duration(100).EUt(VA[UHV])
                .buildAndRegister();

        // Portable Wired Wyvern Charger
        ModHandler.addShapedRecipe("portable_wired_charger_1", new ItemStack(DAFeatures.pwc, 1, 1),
                "DDD", "ICI", "DDD",
                'D', Loader.isModLoaded(GTEValues.MODID_EIO) ?
                        OreDictUnifier.get(plate, GTEMaterials.DarkSteel) :
                        OreDictUnifier.get(plate, Materials.Steel),
                'I', OreDictUnifier.get(plate, Materials.Iron),
                'C', new ItemStack(DEFeatures.wyvernCore));

        // Portable Wired Wyvern Discharger
        ModHandler.addShapedRecipe("portable_wired_discharger_1", new ItemStack(DAFeatures.pwd, 1, 1),
                "DDD", "GCG", "DDD",
                'D', Loader.isModLoaded(GTEValues.MODID_EIO) ?
                        OreDictUnifier.get(plate, GTEMaterials.DarkSteel) :
                        OreDictUnifier.get(plate, Materials.Steel),
                'G', OreDictUnifier.get(plate, Materials.Gold),
                'C', new ItemStack(DEFeatures.wyvernCore));

        // Wyvern Necklace of Shielding
        ModHandler.addShapedRecipe("wyvern_shield_necklace", new ItemStack(DAFeatures.wyvernShieldNecklace, 1),
                "SSS", "SES", " C ",
                'S', OreDictUnifier.get(stick, Materials.RoseGold),
                'E', new ItemStack(DEFeatures.wyvernEnergyCore),
                'C', new ItemStack(DEFeatures.wyvernCore));

        // Draconic Belt of Overloading
        ModHandler.addShapedRecipe("overload_belt", new ItemStack(DAFeatures.overloadBelt, 1),
                "SSS", "SLS", " C ",
                'S', OreDictUnifier.get(stick, GTEMaterials.Draconium),
                'C', new ItemStack(DEFeatures.awakenedCore),
                'L', Items.LEATHER);

        // Ring of Inertia Cancellation
        ModHandler.addShapedRecipe("inertia_cancel_ring", new ItemStack(DAFeatures.inertiaCancelRing, 1),
                "SES", "E E", "SES",
                'S', OreDictUnifier.get(stick, Materials.RoseGold),
                'E', new ItemStack(DEFeatures.wyvernEnergyCore));
    }
}
