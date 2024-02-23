package gtexpert.integration.deda.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.brandon3055.draconicevolution.DEFeatures;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.metatileentities.MetaTileEntities;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.common.blocks.GTEBlockMetalCasing;
import gtexpert.common.blocks.GTEBlockWireCoil;
import gtexpert.common.blocks.GTEMetaBlocks;
import gtexpert.core.GTUtil;
import gtexpert.integration.deda.metatileentities.DEDAMetaTileEntities;
import gtexpert.integration.deda.recipemaps.GTEDraconicRecipeMaps;

public class DraconicBlocksRecipe {

    public static void init() {
        // Awakened Draconium Coil Block
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(wireGtDouble, GTEMaterials.AwakenedDraconium, 8)
                .input(foil, Materials.Tritanium, 8)
                .fluidInputs(GTEMaterials.AwakenedDraconium.getFluid(144))
                .outputs(GTEMetaBlocks.GTE_WIRE_COIL.getItemVariant(GTEBlockWireCoil.GTECoilType.AWAKENED_DRACONIUM))
                .duration(900).EUt(VA[UHV])
                .withRecycling()
                .buildAndRegister();

        // Dislocator Receptacle
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("dislocator_receptacle"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.infusedObsidian, 1)
                .input(plate, GTEMaterials.Draconium, 7)
                .outputs(new ItemStack(DEFeatures.dislocatorReceptacle))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .withRecycling()
                .buildAndRegister();

        // Energy Infuser
        ModHandler.removeRecipeByOutput(new ItemStack(DEFeatures.energyInfuser));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 3)
                .inputs(new ItemStack(DEFeatures.particleGenerator, 1, 2))
                .input(Blocks.ENCHANTING_TABLE, 1)
                .outputs(new ItemStack(DEFeatures.energyInfuser))
                .duration(600).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Celestial Manipulator
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("celestial_manipulator"));
        RecipeBuilder<?> builderCM = RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.CLOCK, 1)
                .input(plate, GTEMaterials.Draconium, 4)
                .input(stick, Materials.Iron, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.celestialManipulator))
                .duration(600).EUt(VA[GTEValues.dedaVoltageTier]);
        if (Mods.EnderIO.isModLoaded()) {
            builderCM.input(stickLong, GTEMaterials.DarkSteel, 4);
            builderCM.input(stick, GTEMaterials.DarkSteel, 4);
        } else {
            builderCM.input(stickLong, Materials.BlackSteel, 4);
            builderCM.input(stick, Materials.BlackSteel, 4);
        }
        builderCM.buildAndRegister();

        // Dislocation Normalization Field Projector
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("item_dislocation_inhibitor"));

        // Particle Generator
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("particle_generator"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(block, Materials.Redstone, 4)
                .input(stick, Materials.Blaze, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.particleGenerator))
                .duration(600).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Energy Core Stabilizer
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("particle_generator_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(block, Materials.Diamond, 4)
                .input(stick, Materials.Blaze, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.particleGenerator, 1, 2))
                .duration(600).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Draconic Fusion Crafter
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[GTEValues.dedaVoltageTier])
                .input(frameGt, GTEMaterials.Draconium, 4)
                .input(DEFeatures.wyvernCore, 4)
                .input(GTUtil.robotArm(GTEValues.dedaVoltageTier), 2)
                .input(GTUtil.sensor(LuV), 2)
                .input(GTUtil.emitter(LuV), 2)
                .output(DEDAMetaTileEntities.DRACONIUM_FUSION)
                .duration(600).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Draconic Awakened Fusion Crafter
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[UV])
                .input(frameGt, GTEMaterials.AwakenedDraconium, 4)
                .input(DEFeatures.awakenedCore, 4)
                .input(GTUtil.robotArm(UV), 2)
                .input(GTUtil.sensor(UV), 2)
                .input(GTUtil.emitter(UV), 2)
                .output(DEDAMetaTileEntities.AWAKENED_DRACONIUM_FUSION)
                .duration(600).EUt(VA[UV])
                .withRecycling()
                .buildAndRegister();

        // Draconum Casing
        ModHandler.addShapedRecipe(true, "casing_draconum",
                GTEMetaBlocks.GTE_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING,
                                ConfigHolder.recipes.casingsPerCraft),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, GTEMaterials.Draconium),
                'F', new UnificationEntry(frameGt, GTEMaterials.Draconium));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, GTEMaterials.Draconium, 6)
                .input(frameGt, GTEMaterials.Draconium, 1)
                .outputs(GTEMetaBlocks.GTE_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING, 2))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Awakened Draconum Casing
        ModHandler.addShapedRecipe(true, "casing_awakened_draconum",
                GTEMetaBlocks.GTE_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING,
                                ConfigHolder.recipes.casingsPerCraft),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, GTEMaterials.AwakenedDraconium),
                'F', new UnificationEntry(frameGt, GTEMaterials.AwakenedDraconium));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, GTEMaterials.AwakenedDraconium, 6)
                .input(frameGt, GTEMaterials.AwakenedDraconium, 1)
                .outputs(GTEMetaBlocks.GTE_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING, 2))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .withRecycling()
                .buildAndRegister();

        // Infused Obsidian
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("infused_obsidian"));
        ModHandler.addShapedRecipe(true, "infused_obsidian", new ItemStack(DEFeatures.infusedObsidian),
                "BOB", "ODO", "BOB",
                'B', Items.BLAZE_POWDER,
                'O', Mods.EnderIO.isModLoaded() ?
                        GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_reinforced_obsidian") :
                        Mods.Vanilla.getItem("obsidian"),
                'D', OreDictUnifier.get(dust, GTEMaterials.Draconium));

        // Basic Energy Relay Crystal
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("energy_crystal"));
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("energy_crystal_5"));

        // Wyvern Energy Relay Crystal
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("energy_crystal_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Materials.Diamond, 4)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 1))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Draconic Energy Relay Crystal
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Materials.Diamond, 4)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(DEFeatures.awakenedCore, 1)
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 2))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .withRecycling()
                .buildAndRegister();

        // Basic Energy I/O Crystal
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("energy_crystal_2"));

        // Basic Wireless Energy Crystal
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("energy_crystal_8"));

        // Wyvern Wireless Energy Crystal
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("energy_crystal_9"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(gem, Materials.EnderPearl, 4)
                .input(gem, Materials.EnderEye, 2)
                .inputs(new ItemStack(DEFeatures.particleGenerator, 2, 0))
                .inputs(new ItemStack(DEFeatures.energyCrystal, 1, 1))
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 7))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Draconic Wireless Energy Crystal
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("energy_crystal_10"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(gem, Materials.EnderPearl, 4)
                .input(gem, Materials.EnderEye, 2)
                .inputs(new ItemStack(DEFeatures.particleGenerator, 2, 0))
                .inputs(new ItemStack(DEFeatures.energyCrystal, 1, 2))
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 8))
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .withRecycling()
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
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
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
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier + 1])
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
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier]);
        if (Mods.EnderIO.isModLoaded()) {
            builderCS.input(plate, GTEMaterials.StellarAlloy, 4);
            builderCS.input(stick, GTEMaterials.StellarAlloy, 4);
        } else {
            builderCS.input(plate, Materials.VanadiumSteel, 4);
            builderCS.input(stick, Materials.VanadiumSteel, 4);
        }
        builderCS.buildAndRegister();
    }
}
