package gtexpert.loaders.recipe.ingredients;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.nbtmatch.*;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.draconic.GTEDraconicRecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;

import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import appeng.api.AEApi;
import com.brandon3055.draconicevolution.DEFeatures;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;
import crazypants.enderio.powertools.init.PowerToolObject;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;

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
                .fluidInputs(Pyrotheum.getFluid(1000))
                .fluidOutputs(Cryotheum.getFluid(1000))
                .duration(400).EUt(VA[LuV])
                .buildAndRegister();
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .input(dust, Electrotine, 1)
                .fluidInputs(Ice.getFluid(4000))
                .fluidInputs(EnderPearl.getFluid(144))
                .fluidOutputs(Cryotheum.getFluid(100))
                .duration(150).EUt(VA[LuV])
                .buildAndRegister();

        // Pyrotheum
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(15)
                .input(dust, Redstone, 1)
                .input(dust, Sulfur, 1)
                .fluidInputs(Argon.getFluid(200))
                .fluidInputs(Blaze.getFluid(2304))
                .fluidOutputs(Pyrotheum.getFluid(1000))
                .blastFurnaceTemp(7200)
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(dust, Redstone, 1)
                .input(dust, Sulfur, 1)
                .fluidInputs(Blaze.getFluid(2304))
                .fluidOutputs(Pyrotheum.getFluid(1000))
                .blastFurnaceTemp(7200)
                .duration(1200).EUt(VA[LuV])
                .buildAndRegister();
    }

    private static void materials() {
        // ########################################
        // Draconic Evolution
        // ########################################
        // Dragon Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, EndSteel, 1)
                .input(dust, Iridium, 1)
                .fluidInputs(SaltWater.getFluid(1000))
                .fluidInputs(EnderEye.getFluid(144))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, Dragon, 2)
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(Blocks.DRAGON_EGG)
                .output(dust, Dragon, 8)
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Chaos Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Dragon, 8)
                .fluidInputs(Pyrotheum.getFluid(8000))
                .fluidInputs(AwakenedDraconium.getFluid(1152))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, Chaos, 2)
                .duration(1200).EUt(VA[ZPM])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.chaosShard, 1, 1))
                .output(dust, Chaos, 1)
                .duration(200).EUt(VA[ZPM])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, Chaos, 1)
                .fluidInputs(DistilledWater.getFluid(50))
                .output(DEFeatures.chaosShard, 1, 1)
                .duration(1200).EUt(VA[ZPM])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, Chaos, 1)
                .fluidInputs(Water.getFluid(250))
                .chancedOutput(new ItemStack(DEFeatures.chaosShard, 1, 1), 7000, 1000)
                .duration(2400).EUt(VA[ZPM])
                .buildAndRegister();

        // Draconium Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Dragon, 1)
                .input(dust, Obsidian, 1)
                .fluidInputs(LiquidEnderAir.getFluid(8000))
                .fluidInputs(EnderPearl.getFluid(576))
                .output(dust, Draconium, 2)
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Draconium Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconium_block"));
        ModHandler.addMirroredShapedRecipe("de_draconium_block", new ItemStack(DEFeatures.draconiumBlock), "B", 'B',
                OreDictUnifier.get(block, Draconium));
        ModHandler.addMirroredShapedRecipe("ceu_draconium_block", OreDictUnifier.get(block, Draconium), "B", 'B',
                new ItemStack(DEFeatures.draconiumBlock));

        // Awakened Draconium Block
        OreDictionary.registerOre("blockAwakenedDraconium", DEFeatures.draconicBlock);
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconic_block"));
        ModHandler.addMirroredShapedRecipe("de_draconic_block", new ItemStack(DEFeatures.draconicBlock), "B", 'B',
                OreDictUnifier.get(block, AwakenedDraconium));
        ModHandler.addMirroredShapedRecipe("ceu_draconic_block", OreDictUnifier.get(block, AwakenedDraconium), "B",
                'B', new ItemStack(DEFeatures.draconicBlock));
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, Draconium, 4)
                .output(block, AwakenedDraconium, 3)
                .output(dustSmall, DarkAsh, 1)
                .explosivesAmount(2)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, Draconium, 4)
                .output(block, AwakenedDraconium, 3)
                .output(dustSmall, DarkAsh, 1)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
    }

    private static void items() {
        // ########################################
        // Draconic Evolution
        // ########################################
        // Dragon Heart
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(STEM_CELLS, 8)
                .input(Items.EGG, 16)
                .fluidInputs(Draconium.getFluid(1152))
                .output(DEFeatures.dragonHeart, 1)
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Advanced Dislocators
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Draconium, 4)
                .input(DEFeatures.wyvernCore, 1)
                .fluidInputs(EnderPearl.getFluid(576))
                .output(DEFeatures.dislocatorAdvanced, 1)
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Wyvern Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_core"));
        RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, Draconium, 16)
                .fluidInputs(Tin.getFluid(144))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(9600)
                .buildAndRegister();
        RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, Draconium, 16)
                .fluidInputs(SolderingAlloy.getFluid(72))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(100).EUt(9600)
                .buildAndRegister();

        // Draconic Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconic_core"));
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, Draconium, 16)
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(200).EUt(38400)
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, Draconium, 16)
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(200).EUt(38400)
                .buildAndRegister();

        // Awakened Core
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 4)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, AwakenedDraconium, 16)
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(400).EUt(153600)
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 4)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, AwakenedDraconium, 16)
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(400).EUt(153600)
                .buildAndRegister();

        // Chaotic Core
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorStellar.getItemNN(), 4)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, AwakenedDraconium, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(600).EUt(614400)
                .buildAndRegister();
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorStellar.getItemNN(), 4)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, AwakenedDraconium, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(600).EUt(614400)
                .buildAndRegister();

        // Wyvern Energy Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_energy_core"));
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, Draconium, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(100).EUt(9600)
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, Draconium, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(100).EUt(9600)
                .buildAndRegister();

        // Draconic Energy Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconic_energy_core"));
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(DEFeatures.draconicCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, AwakenedDraconium, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(200).EUt(38400)
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(DEFeatures.draconicCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, AwakenedDraconium, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(200).EUt(38400)
                .buildAndRegister();

        // Energy Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_storage_core"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[LuV])
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 2)
                .input(plate, Draconium, 6)
                .outputs(new ItemStack(DEFeatures.energyStorageCore, 1, 0))
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Energy Pylon
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_pylon"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(MetaTileEntities.HULL[LuV])
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, Draconium, 4)
                .input(plate, Emerald, 2)
                .input(gem, Diamond, 1)
                .input(Items.ENDER_EYE, 1)
                .outputs(new ItemStack(DEFeatures.energyPylon, 1, 0))
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Frame
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, DarkSteel, 6)
                .input(plate, AwakenedDraconium, 1)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 0))
                .duration(200).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Inner Rotor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, Draconium, 3)
                .input(plate, AwakenedDraconium, 2)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 1))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Outer Rotor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part_2"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(plate, Diamond, 3)
                .input(plate, AwakenedDraconium, 2)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 2))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Rotor Assembly
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part_3"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, Draconium, 2)
                .inputs(new ItemStack(DEFeatures.reactorPart, 2, 1))
                .inputs(new ItemStack(DEFeatures.reactorPart, 2, 2))
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 3))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer Focus Ring
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "reactor_part_4"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.wyvernCore, 1)
                .input(stick, Diamond, 4)
                .input(screw, Gold, 4)
                .outputs(new ItemStack(DEFeatures.reactorPart, 1, 4))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Reactor Stabilizer
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 1, 3)
                .input(DEFeatures.reactorPart, 1, 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .fluidInputs(AwakenedDraconium.getFluid(1152))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 0))
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(400).EUt(38400)
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 1, 3)
                .input(DEFeatures.reactorPart, 1, 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .fluidInputs(AwakenedDraconium.getFluid(1152))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 0))
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(400).EUt(38400)
                .buildAndRegister();

        // Reactor Energy Injector
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 4, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(plate, DarkSteel, 4)
                .fluidInputs(Draconium.getFluid(576))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 1))
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(400).EUt(38400)
                .buildAndRegister();
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorPart, 4, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(plate, DarkSteel, 4)
                .fluidInputs(Draconium.getFluid(576))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .outputs(new ItemStack(DEFeatures.reactorComponent, 1, 1))
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(400).EUt(38400)
                .buildAndRegister();

        // Draconic Reactor Core
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.chaosShard, 1, 0)
                .fluidInputs(Draconium.getFluid(1152))
                .fluidInputs(AwakenedDraconium.getFluid(1152))
                .fluidInputs(Pyrotheum.getFluid(64000))
                .outputs(new ItemStack(DEFeatures.reactorCore, 1, 0))
                .duration(1200).EUt(VA[LuV])
                .buildAndRegister();

        // Upgrade Key (RF Capacity)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade"));
        ModHandler.addShapedRecipe("tool_upgrade", new ItemStack(DEFeatures.toolUpgrade, 1, 0),
                "LCL", "DED", "LCL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'E', new ItemStack(DEFeatures.wyvernEnergyCore, 1, 0));

        // Upgrade Key (Dig Speed)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_1"));
        ModHandler.addShapedRecipe("tool_upgrade_1", new ItemStack(DEFeatures.toolUpgrade, 1, 1),
                "LCL", "DPD", "LCL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'P', Items.GOLDEN_PICKAXE);

        // Upgrade Key (Dig AOE)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_2"));
        ModHandler.addShapedRecipe("tool_upgrade_2", new ItemStack(DEFeatures.toolUpgrade, 1, 2),
                "LCL", "DED", "LCL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'E', AEApi.instance().definitions().materials().fluixPearl().maybeStack(1).get());

        // Upgrade Key (Attack Damage)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_3"));
        ModHandler.addShapedRecipe("tool_upgrade_3", new ItemStack(DEFeatures.toolUpgrade, 1, 3),
                "LCL", "DSD", "LCL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'S', Items.GOLDEN_SWORD);

        // Upgrade Key (Attack AOE)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_4"));
        ModHandler.addShapedRecipe("tool_upgrade_4", new ItemStack(DEFeatures.toolUpgrade, 1, 4),
                "LCL", "DSD", "LCL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'S', Items.DIAMOND_SWORD);

        // Upgrade Key (Arrow Damage)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_5"));
        ModHandler.addShapedRecipe("tool_upgrade_5", new ItemStack(DEFeatures.toolUpgrade, 1, 5),
                "LCG", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'G', OreDictUnifier.get(screw, RoseGold, 1),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'A', Items.ARROW);

        // Upgrade Key (Draw Speed)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_6"));
        ModHandler.addShapedRecipe("tool_upgrade_6", new ItemStack(DEFeatures.toolUpgrade, 1, 6),
                "LCL", "DAD", "LCG",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'G', OreDictUnifier.get(stickLong, RoseGold, 1),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'A', ModObject.itemEndSteelBow.getItemNN());

        // Upgrade Key (Arrow Speed)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_7"));
        ModHandler.addShapedRecipe("tool_upgrade_7", new ItemStack(DEFeatures.toolUpgrade, 1, 7),
                "LCF", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'F', Items.FEATHER,
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'A', Items.ARROW);

        // Upgrade Key (Shield Capacity)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_8"));
        ModHandler.addShapedRecipe("tool_upgrade_8", new ItemStack(DEFeatures.toolUpgrade, 1, 8),
                "LCL", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'A', ModObject.itemEndSteelChestplate.getItemNN());

        // Upgrade Key (Shield Recharge)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_9"));
        ModHandler.addShapedRecipe("tool_upgrade_9", new ItemStack(DEFeatures.toolUpgrade, 1, 9),
                "LCL", "DAD", "LCL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'A', EndergyObject.itemStellarAlloyChestplate.getItemNN());

        // Upgrade Key (Movement Speed)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_10"));
        ModHandler.addShapedRecipe("tool_upgrade_10", new ItemStack(DEFeatures.toolUpgrade, 1, 10),
                "LCL", "DSD", "LBL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'S', Items.GOLDEN_BOOTS,
                'B', OreDictUnifier.get(block, Redstone, 1));

        // Upgrade Key (Jump Boost)
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "tool_upgrade_11"));
        ModHandler.addShapedRecipe("tool_upgrade_11", new ItemStack(DEFeatures.toolUpgrade, 1, 11),
                "LCL", "DSD", "LBL",
                'L', OreDictUnifier.get(plate, Lapis, 1),
                'C', new ItemStack(DEFeatures.draconicCore, 1, 0),
                'D', OreDictUnifier.get(plate, Draconium, 1),
                'S', Items.GOLDEN_BOOTS,
                'B', Blocks.SLIME_BLOCK);

        // ########################################
        // Draconic Additions
        // ########################################
        // Chaotic Energy Core
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, AwakenedDraconium, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(400).EUt(614400)
                .buildAndRegister();
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, AwakenedDraconium, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(Cryotheum.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(400).EUt(614400)
                .buildAndRegister();
    }

    private static void blocks() {
        // Void Ore Miner
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.HULL[UV])
                .input(frameGt, Darmstadtium, 4)
                .input(DEFeatures.awakenedCore, 4)
                .input(ELECTRIC_MOTOR_UV, 4)
                .input(ELECTRIC_PUMP_UV, 4)
                .input(CONVEYOR_MODULE_UV, 4)
                .input(ELECTRIC_PISTON_UV, 4)
                .input(ROBOT_ARM_UV, 4)
                .input(EMITTER_UV, 4)
                .input(SENSOR_UV, 4)
                .input(ORE_DICTIONARY_FILTER)
                .input(gear, Darmstadtium, 4)
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .output(VOIDOREMINER)
                .duration(600).EUt(VA[UV])
                .buildAndRegister();

        // Infinite GT Energy Unit Emitter
        RecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(MetaTileEntities.HULL[UHV])
                .input(DAFeatures.chaoticEnergyCore, 8)
                .input(DAFeatures.chaosStabilizerCore, 8)
                .inputs(AEApi.instance().definitions().blocks().energyCellCreative().maybeStack(4).get())
                .inputNBT(PowerToolObject.block_cap_bank.getBlockNN(), 4, NBTMatcher.ANY, NBTCondition.ANY)
                .input(GTEMetaItems.GTE_ME_FAKE_COMPONENT, 4)
                .fluidInputs(SolderingAlloy.getFluid(18432))
                .fluidInputs(UraniumRhodiumDinaquadide.getFluid(9216))
                .outputs(MetaTileEntities.CREATIVE_ENERGY.getStackForm())
                .duration(2000).EUt(VA[UHV])
                .buildAndRegister();

        // ########################################
        // Draconic Evolution
        // ########################################
        // Dislocator Receptacle
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "dislocator_receptacle"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.infusedObsidian, 1)
                .input(plate, Draconium, 7)
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
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "celestial_manipulator"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.CLOCK, 1)
                .input(plate, Draconium, 4)
                .input(stickLong, DarkSteel, 4)
                .input(screw, DarkSteel, 4)
                .input(stick, Iron, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.celestialManipulator, 1, 0))
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();

        // Dislocation Normalization Field Projector
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "item_dislocation_inhibitor"));

        // Particle Generator
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "particle_generator"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(block, Redstone, 4)
                .input(stick, Blaze, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.particleGenerator, 1, 0))
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();

        // Energy Core Stabilizer
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "particle_generator_1"));
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
                .input(frameGt, Draconium, 4)
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
                .input(frameGt, AwakenedDraconium, 4)
                .input(DEFeatures.awakenedCore, 4)
                .input(ROBOT_ARM_UV, 2)
                .input(SENSOR_UV, 2)
                .input(EMITTER_UV, 2)
                .outputs(AWAKENED_DRACONIUM_FUSION.getStackForm())
                .duration(600).EUt(VA[UV])
                .buildAndRegister();

        // Draconum Casing
        ModHandler.addShapedRecipe("casing_draconum",
                GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING, 2),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, Draconium),
                'F', new UnificationEntry(frameGt, Draconium));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, Draconium, 6)
                .input(frameGt, Draconium, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING, 2))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();
        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING, 1))
                .fluidInputs(Oxygen.getFluid(224))
                .output(ingot, Draconium, 4)
                .duration(225).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING, 1))
                .output(dust, Draconium, 4)
                .duration(225).EUt(8)
                .buildAndRegister();

        // Awakened Draconum Casing
        ModHandler.addShapedRecipe("casing_awakened_draconum",
                GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING, 2),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, AwakenedDraconium),
                'F', new UnificationEntry(frameGt, AwakenedDraconium));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(plate, AwakenedDraconium, 6)
                .input(frameGt, AwakenedDraconium, 1)
                .outputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING, 2))
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();
        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING, 1))
                .fluidInputs(Oxygen.getFluid(224))
                .output(ingot, AwakenedDraconium, 4)
                .duration(225).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING, 1))
                .output(dust, AwakenedDraconium, 4)
                .duration(225).EUt(8)
                .buildAndRegister();

        // Infused Obsidian
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "infused_obsidian"));
        ModHandler.addShapedRecipe("infused_obsidian", new ItemStack(DEFeatures.infusedObsidian, 1, 0),
                "BOB", "ODO", "BOB",
                'B', Items.BLAZE_POWDER,
                'O', ModObject.blockReinforcedObsidian.getBlockNN(),
                'D', OreDictUnifier.get(dust, Draconium));

        // Basic Energy Relay Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_5"));

        // Wyvern Energy Relay Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_1"));
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
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_2"));

        // Basic Wireless Energy Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_8"));

        // Wyvern Wireless Energy Crystal
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_9"));
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
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "energy_crystal_10"));
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
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(Blocks.FURNACE, 5)
                .input(DEFeatures.wyvernCore, 2)
                .input(MetaTileEntities.WORKBENCH)
                .input(MetaTileEntities.TUNGSTENSTEEL_CRATE)
                .fluidInputs(Cryotheum.getFluid(8000))
                .fluidInputs(Draconium.getFluid(1152))
                .output(DEFeatures.draconiumChest)
                .fluidOutputs(Pyrotheum.getFluid(2000))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // ########################################
        // Draconic Additions
        // ########################################
        // Chaos Liquefier
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .inputNBT(DAFeatures.chaosContainer, 1, NBTMatcher.ANY, NBTCondition.ANY)
                .input(DEFeatures.infusedObsidian, 4)
                .input(DEFeatures.draconicCore, 4)
                .fluidInputs(Cryotheum.getFluid(32000))
                .output(DAFeatures.chaosLiquefier, 1)
                .fluidOutputs(Pyrotheum.getFluid(8000))
                .duration(200).EUt(VA[ZPM])
                .buildAndRegister();

        // Chaotic Stability Core
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorCore, 4)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(DEFeatures.infusedObsidian, 4)
                .fluidInputs(Pyrotheum.getFluid(48000))
                .output(DAFeatures.chaosStabilizerCore, 1)
                .duration(1200).EUt(VA[UHV])
                .buildAndRegister();

        // Capacitor Supplier
        GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(Blocks.END_ROD, 1)
                .input(plate, StellarAlloy, 4)
                .input(stick, StellarAlloy, 4)
                .input(ring, Titanium, 4)
                .fluidInputs(Cryotheum.getFluid(16000))
                .output(DAFeatures.capacitorSupplier, 1)
                .fluidOutputs(Pyrotheum.getFluid(4000))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();
    }

    private static void tools() {
        // ########################################
        // Draconic Evolution
        // ########################################
        // Crystal Binder
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "crystal_binder"));
        ModHandler.addShapedRecipe("crystal_binder", new ItemStack(DEFeatures.crystalBinder, 1, 0),
                "PhP", " R ", " C ",
                'P', OreDictUnifier.get(plate, Draconium),
                'R', OreDictUnifier.get(stick, EnergeticAlloy),
                'C', DEFeatures.wyvernCore);

        // Wyvern Flux Capacitor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconium_capacitor"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(new ItemStack(DEFeatures.wyvernEnergyCore, 4, 0))
                .input(plate, Draconium, 4)
                .input(DEFeatures.wyvernCore, 1)
                .outputs(new ItemStack(DEFeatures.draconiumCapacitor, 1, 0))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Draconic Flux Capacitor
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconium_capacitor_1"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(new ItemStack(DEFeatures.draconiumCapacitor, 4, 0))
                .input(plate, AwakenedDraconium, 4)
                .input(DEFeatures.awakenedCore, 1)
                .outputs(new ItemStack(DEFeatures.draconiumCapacitor, 1, 1))
                .duration(100).EUt(VA[ZPM])
                .buildAndRegister();

        // ########################################
        // Draconic Additions
        // ########################################
        // Chaos Container
        GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .inputNBT(DEFeatures.dislocator, NBTMatcher.ANY, NBTCondition.ANY)
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(Cryotheum.getFluid(48000))
                .outputs(new ItemStack(DAFeatures.chaosContainer, 1))
                .fluidOutputs(Pyrotheum.getFluid(12000))
                .duration(100).EUt(VA[UHV])
                .buildAndRegister();

        // Portable Wired Wyvern Charger
        ModHandler.addShapedRecipe("portable_wired_charger_1", new ItemStack(DAFeatures.pwc, 1, 1),
                "DDD", "ICI", "DDD",
                'D', OreDictUnifier.get(plate, DarkSteel),
                'I', OreDictUnifier.get(plate, Iron),
                'C', new ItemStack(DEFeatures.wyvernCore));

        // Portable Wired Wyvern Discharger
        ModHandler.addShapedRecipe("portable_wired_discharger_1", new ItemStack(DAFeatures.pwd, 1, 1),
                "DDD", "GCG", "DDD",
                'D', OreDictUnifier.get(plate, DarkSteel),
                'G', OreDictUnifier.get(plate, Gold),
                'C', new ItemStack(DEFeatures.wyvernCore));

        // Wyvern Necklace of Shielding
        ModHandler.addShapedRecipe("wyvern_shield_necklace", new ItemStack(DAFeatures.wyvernShieldNecklace, 1),
                "SSS", "SES", " C ",
                'S', OreDictUnifier.get(stick, RoseGold),
                'E', new ItemStack(DEFeatures.wyvernEnergyCore),
                'C', new ItemStack(DEFeatures.wyvernCore));

        // Draconic Belt of Overloading
        ModHandler.addShapedRecipe("overload_belt", new ItemStack(DAFeatures.overloadBelt, 1),
                "SSS", "SLS", " C ",
                'S', OreDictUnifier.get(stick, Draconium),
                'C', new ItemStack(DEFeatures.awakenedCore),
                'L', Items.LEATHER);

        // Ring of Inertia Cancellation
        ModHandler.addShapedRecipe("inertia_cancel_ring", new ItemStack(DAFeatures.inertiaCancelRing, 1),
                "SES", "E E", "SES",
                'S', OreDictUnifier.get(stick, RoseGold),
                'E', new ItemStack(DEFeatures.wyvernEnergyCore));
    }
}
