package gtexpert.loaders.recipe;

import com.brandon3055.draconicevolution.DEFeatures;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;

public class DERecipeLoader {
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
                .fluidInputs(PYROTHEUM.getFluid(1000))
                .fluidOutputs(CRYOTHEUM.getFluid(1000))
                .duration(400).EUt(VA[LuV])
                .buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Electrotine, 1)
                .input(dust, EnderPearl, 1)
                .fluidInputs(Ice.getFluid(4000))
                .fluidOutputs(CRYOTHEUM.getFluid(100))
                .duration(300).EUt(VA[LuV])
                .buildAndRegister();

        // Pyrotheum
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(dust, Redstone, 1)
                .input(dust, Sulfur, 1)
                .fluidInputs(Argon.getFluid(200))
                .fluidInputs(Blaze.getFluid(2304))
                .fluidOutputs(PYROTHEUM.getFluid(1000))
                .blastFurnaceTemp(7200)
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Redstone, 1)
                .input(dust, Sulfur, 1)
                .fluidInputs(Blaze.getFluid(2304))
                .fluidOutputs(PYROTHEUM.getFluid(1000))
                .blastFurnaceTemp(7200)
                .duration(1200).EUt(VA[LuV])
                .buildAndRegister();
    }

    private static void materials() {
        // Dragon Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, END_STEEL, 1)
                .input(dust, Iridium, 1)
                .fluidInputs(SaltWater.getFluid(1000))
                .fluidInputs(EnderEye.getFluid(144))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, DRAGON, 2)
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(Blocks.DRAGON_EGG)
                .output(dust, DRAGON, 8)
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Chaos Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, DRAGON, 8)
                .fluidInputs(PYROTHEUM.getFluid(8000))
                .fluidInputs(AWAKENED_DRACONIUM.getFluid(1152))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, CHAOS, 2)
                .duration(1200).EUt(VA[ZPM])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.chaosShard, 1, 1))
                .output(dust, CHAOS, 1)
                .duration(200).EUt(VA[ZPM])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, CHAOS, 1)
                .fluidInputs(DistilledWater.getFluid(50))
                .output(DEFeatures.chaosShard, 1, 1)
                .duration(1200).EUt(VA[ZPM])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, CHAOS, 1)
                .fluidInputs(Water.getFluid(250))
                .chancedOutput(new ItemStack(DEFeatures.chaosShard, 1, 1), 7000, 1000)
                .duration(2400).EUt(VA[ZPM])
                .buildAndRegister();

        // Draconium Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, DRAGON, 1)
                .input(dust, Obsidian, 1)
                .fluidInputs(LiquidEnderAir.getFluid(8000))
                .fluidInputs(EnderPearl.getFluid(576))
                .output(dust, DRACONIUM, 2)
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Draconium Block
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconium_block"));
        ModHandler.addMirroredShapedRecipe("de_draconium_block", new ItemStack(DEFeatures.draconiumBlock), "B", 'B', OreDictUnifier.get(block, DRACONIUM));
        ModHandler.addMirroredShapedRecipe("ceu_draconium_block", OreDictUnifier.get(block, DRACONIUM), "B", 'B', new ItemStack(DEFeatures.draconiumBlock));

        // Awakened Draconium Block
        OreDictionary.registerOre("blockAwakenedDraconium", DEFeatures.draconicBlock);
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconic_block"));
        ModHandler.addMirroredShapedRecipe("de_draconic_block", new ItemStack(DEFeatures.draconicBlock), "B", 'B', OreDictUnifier.get(block, AWAKENED_DRACONIUM));
        ModHandler.addMirroredShapedRecipe("ceu_draconic_block", OreDictUnifier.get(block, AWAKENED_DRACONIUM), "B", 'B', new ItemStack(DEFeatures.draconicBlock));
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, DRACONIUM, 4)
                .output(block, AWAKENED_DRACONIUM, 3)
                .output(dustSmall, DarkAsh, 1)
                .explosivesAmount(2)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, DRACONIUM, 4)
                .output(block, AWAKENED_DRACONIUM, 3)
                .output(dustSmall, DarkAsh, 1)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
    }

    private static void items() {
        // Advanced Dislocators
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, DRACONIUM, 4)
                .input(DEFeatures.wyvernCore, 1)
                .fluidInputs(EnderPearl.getFluid(576))
                .output(DEFeatures.dislocatorAdvanced, 1)
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Wyvern Core
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "wyvern_core"));
        RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(Tin.getFluid(144))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(9600)
                .buildAndRegister();
        RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(SolderingAlloy.getFluid(72))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(9600)
                .buildAndRegister();

        // Draconic Core
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconic_core"));
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(200).EUt(38400)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(200).EUt(38400)
                .buildAndRegister();

        // Awakened Core
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 4)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, AWAKENED_DRACONIUM, 16)
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(400).EUt(153600)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 4)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, AWAKENED_DRACONIUM, 16)
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(400).EUt(153600)
                .buildAndRegister();

        // Chaotic Core
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorStellar.getItemNN(), 4)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, AWAKENED_DRACONIUM, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(600).EUt(614400)
                .buildAndRegister();
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorStellar.getItemNN(), 4)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, AWAKENED_DRACONIUM, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(600).EUt(614400)
                .buildAndRegister();

        // Wyvern Energy Core
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "wyvern_energy_core"));
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, DRACONIUM, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(100).EUt(9600)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, DRACONIUM, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(100).EUt(9600)
                .buildAndRegister();

        // Draconic Energy Core
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconic_energy_core"));
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(DEFeatures.draconicCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, AWAKENED_DRACONIUM, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(200).EUt(38400)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(DEFeatures.draconicCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, AWAKENED_DRACONIUM, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(200).EUt(38400)
                .buildAndRegister();

        // Energy Core
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "energy_storage_core"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[LuV])
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 2)
                .input(plate, DRACONIUM, 6)
                .outputs(new ItemStack(DEFeatures.energyStorageCore, 1, 0))
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Energy Pylon
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "energy_pylon"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[LuV])
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, DRACONIUM, 4)
                .input(plate, Emerald, 2)
                .input(gem, Diamond, 1)
                .input(Items.ENDER_EYE, 1)
                .outputs(new ItemStack(DEFeatures.energyPylon, 1, 0))
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Frame
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "reactor_part"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, DARK_STEEL, 6)
                .input(plate, AWAKENED_DRACONIUM, 1)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 0))
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Inner Rotor
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "reactor_part_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, DRACONIUM, 3)
                .input(plate, AWAKENED_DRACONIUM, 2)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 1))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Outer Rotor
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "reactor_part_2"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, Diamond, 3)
                .input(plate, AWAKENED_DRACONIUM, 2)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 2))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Rotor Assembly
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "reactor_part_3"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, DRACONIUM, 2)
                .inputs(new ItemStack(DEFeatures.reactorPart, 2, 1))
                .inputs(new ItemStack(DEFeatures.reactorPart, 2, 2))
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 3))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Focus Ring
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "reactor_part_4"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, Diamond, 4)
                .input(screw, Gold, 4)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 4))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 1, 3)
                .input(DEFeatures.reactorPart, 1, 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .fluidInputs(AWAKENED_DRACONIUM.getFluid(1152))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 0))
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(400).EUt(38400)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 1, 3)
                .input(DEFeatures.reactorPart, 1, 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .fluidInputs(AWAKENED_DRACONIUM.getFluid(1152))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 0))
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(400).EUt(38400)
                .buildAndRegister();

        // Reactor Energy Injector
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 4, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(plate, DARK_STEEL, 4)
                .fluidInputs(DRACONIUM.getFluid(576))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 1))
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(400).EUt(38400)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 4, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(plate, DARK_STEEL, 4)
                .fluidInputs(DRACONIUM.getFluid(576))
                .fluidInputs(CRYOTHEUM.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 1))
                .fluidOutputs(PYROTHEUM.getFluid(4000))
                .duration(400).EUt(38400)
                .buildAndRegister();

        // Draconic Reactor Core
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.chaosShard, 1, 0)
                .fluidInputs(DRACONIUM.getFluid(1152))
                .fluidInputs(AWAKENED_DRACONIUM.getFluid(1152))
                .fluidInputs(PYROTHEUM.getFluid(64000))
                .outputs(new ItemStack(DEFeatures.reactorCore, 1, 0))
                .duration(1200).EUt(VA[LuV])
                .buildAndRegister();
    }

    private static void blocks() {
        // Dislocator Receptacle
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "dislocator_receptacle"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.infusedObsidian, 1)
                .input(plate, DRACONIUM, 7)
                .outputs(new ItemStack(DEFeatures.dislocatorReceptacle, 1, 0))
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();

        // Energy Infuser
        ModHandler.removeRecipeByOutput(new ItemStack(DEFeatures.energyInfuser, 1, 0));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 3)
                .inputs(new ItemStack(DEFeatures.particleGenerator, 1, 2))
                .input(Blocks.ENCHANTING_TABLE, 1)
                .outputs(new ItemStack(DEFeatures.energyInfuser, 1, 0))
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();

        // Celestial Manipulator
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "celestial_manipulator"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.CLOCK, 1)
                .input(plate, DRACONIUM, 4)
                .input(stickLong, DARK_STEEL, 4)
                .input(screw, DARK_STEEL, 4)
                .input(stick, Iron, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.celestialManipulator, 1, 0))
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();

        // Dislocation Normalization Field Projector
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "item_dislocation_inhibitor"));

        // Particle Generator
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "particle_generator"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(block, Redstone, 4)
                .input(stick, Blaze, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.particleGenerator, 1, 0))
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();

        // Energy Core Stabilizer
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "particle_generator_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(block, Diamond, 4)
                .input(stick, Blaze, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.particleGenerator, 1, 2))
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();

        // Draconic Fusion Crafter
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[LuV])
                .input(frameGt, DRACONIUM, 4)
                .input(DEFeatures.wyvernCore, 4)
                .input(ROBOT_ARM_LuV, 2)
                .input(SENSOR_LuV, 2)
                .input(EMITTER_LuV, 2)
                .outputs(DRACONIUM_FUSION.getStackForm())
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();

        // Draconic Awakened Fusion Crafter
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[UV])
                .input(frameGt, AWAKENED_DRACONIUM, 4)
                .input(DEFeatures.awakenedCore, 4)
                .input(ROBOT_ARM_UV, 2)
                .input(SENSOR_UV, 2)
                .input(EMITTER_UV, 2)
                .outputs(AWAKENED_DRACONIUM_FUSION.getStackForm())
                .duration(600).EUt(VA[UV])
                .buildAndRegister();

        // Draconum Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, DRACONIUM, 6)
                .input(frameGt, DRACONIUM, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING, 2))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Awakened Draconum Casing
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, AWAKENED_DRACONIUM, 6)
                .input(frameGt, AWAKENED_DRACONIUM, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING, 2))
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();

        // Infused Obsidian
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "infused_obsidian"));
        ModHandler.addShapedRecipe("infused_obsidian", new ItemStack(DEFeatures.infusedObsidian, 1, 0),
                "BOB", "ODO", "BOB",
                'B', Items.BLAZE_POWDER,
                'O', ModObject.blockReinforcedObsidian.getBlockNN(),
                'D', OreDictUnifier.get(dust, DRACONIUM)
        );

        // Basic Energy Relay Crystal
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "energy_crystal"));
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "energy_crystal_5"));

        // Wyvern Energy Relay Crystal
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "energy_crystal_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Diamond, 4)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 1))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Draconic Energy Relay Crystal
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Diamond, 4)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(DEFeatures.awakenedCore, 1)
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 2))
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();

        // Basic Energy I/O Crystal
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "energy_crystal_2"));

        // Basic Wireless Energy Crystal
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "energy_crystal_8"));

        // Wyvern Wireless Energy Crystal
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "energy_crystal_9"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(gem, EnderPearl, 4)
                .input(gem, EnderEye, 2)
                .inputs(new ItemStack(DEFeatures.particleGenerator, 2, 0))
                .inputs(new ItemStack(DEFeatures.energyCrystal, 1, 1))
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 7))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Draconic Wireless Energy Crystal
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "energy_crystal_10"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(gem, EnderPearl, 4)
                .input(gem, EnderEye, 2)
                .inputs(new ItemStack(DEFeatures.particleGenerator, 2, 0))
                .inputs(new ItemStack(DEFeatures.energyCrystal, 1, 2))
                .outputs(new ItemStack(DEFeatures.energyCrystal, 1, 8))
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();

        // Draconium Chest
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(Blocks.FURNACE, 5)
                .input(DEFeatures.wyvernCore, 2)
                .input(MetaTileEntities.WORKBENCH)
                .input(MetaTileEntities.TUNGSTENSTEEL_CRATE)
                .fluidInputs(CRYOTHEUM.getFluid(8000))
                .fluidInputs(DRACONIUM.getFluid(1152))
                .output(DEFeatures.draconiumChest)
                .fluidOutputs(PYROTHEUM.getFluid(2000))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();
    }

    private static void tools() {
        // Crystal Binder
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "crystal_binder"));
        ModHandler.addShapedRecipe("crystal_binder", new ItemStack(DEFeatures.crystalBinder, 1, 0),
                "PhP", " R ", " C ",
                'P', OreDictUnifier.get(plate, DRACONIUM),
                'R', OreDictUnifier.get(stick, ENERGETIC_ALLOY),
                'C', DEFeatures.wyvernCore);

        // Wyvern Flux Capacitor
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconium_capacitor"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(new ItemStack(DEFeatures.wyvernEnergyCore, 4, 0))
                .input(plate, DRACONIUM, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.draconiumCapacitor, 1, 0))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Draconic Flux Capacitor
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconium_capacitor_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(new ItemStack(DEFeatures.draconiumCapacitor, 4, 0))
                .input(plate, AWAKENED_DRACONIUM, 4)
                .input(DEFeatures.awakenedCore, 1)
                .outputs(new ItemStack(DEFeatures.draconiumCapacitor, 1, 1))
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();
    }
}
