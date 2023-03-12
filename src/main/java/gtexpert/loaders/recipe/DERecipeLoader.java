package gtexpert.loaders.recipe;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;
import com.brandon3055.draconicevolution.DEFeatures;
import gtexpert.api.recipes.GTERecipeMaps;
import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.common.items.MetaItems.*;
import static gtexpert.api.unification.material.GTEMaterials.*;


public class DERecipeLoader {
    public static void init() {
        materias();
        items();
        blocks();
        tools();
    }

    private static void materias() {
        // Cryotheum Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Electrotine, 1)
                .input(dust, EnderPearl, 1)
                .fluidInputs(Ice.getFluid(8000))
                .output(dust, CRYOTHEUM, 2)
                .duration(300).EUt(VA[LuV])
                .buildAndRegister();

        // Cryotheum Liquid
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(PYROTHEUM.getFluid(1152))
                .fluidOutputs(CRYOTHEUM.getFluid(1152))
                .duration(900).EUt(VA[LuV])
                .buildAndRegister();

        // Pyrotheum Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Redstone, 1)
                .input(dust, Sulfur, 1)
                .fluidInputs(Blaze.getFluid(8000))
                .output(dust, PYROTHEUM, 2)
                .duration(300).EUt(VA[LuV])
                .buildAndRegister();

        // Dragon Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, END_STEEL, 1)
                .input(dust, Iridium, 1)
                .fluidInputs(SaltWater.getFluid(1000))
                .fluidInputs(EnderEye.getFluid(144))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, DRAGON, 2)
                .duration(400).EUt(VA[LuV])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(Blocks.DRAGON_EGG, 1)
                .output(dust, DRAGON, 32)
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();

        // Chaos Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, DRAGON, 8)
                .fluidInputs(PYROTHEUM.getFluid(4608))
                .fluidInputs(DRACONIUM_AWAKENED.getFluid(1152))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, CHAOS, 2)
                .duration(1200).EUt(VA[ZPM])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(DEFeatures.chaosShard, 1)
                .output(dust, CHAOS, 32)
                .duration(600).EUt(VA[LuV])
                .buildAndRegister();


        // ########################################
        // Draconium
        // ########################################
        // Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, DRAGON, 1)
                .input(dust, Obsidian, 1)
                .fluidInputs(LiquidEnderAir.getFluid(8000))
                .fluidInputs(EnderPearl.getFluid(576))
                .output(dust, DRACONIUM, 2)
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();

        // Nugget
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "nugget"));

        // Ingot
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconium_ingot"));
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconium_ingot_1"));

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconium_block"));


        // ########################################
        // Awakened Draconium
        // ########################################
        // Nugget
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution","nugget_1"));

        // Ingot
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution","draconic_ingot"));
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution","draconic_ingot_1"));

        // Block
        OreDictionary.registerOre("blockDraconiumAwakened", DEFeatures.draconicBlock);
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution","draconic_block"));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES, OreDictUnifier.get(ingot, DRACONIUM_AWAKENED, 9));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                new ItemStack[]{SHAPE_MOLD_BLOCK.getStackForm()},
                new FluidStack[]{DRACONIUM_AWAKENED.getFluid(1296)}
        );
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(DRACONIUM_AWAKENED.getFluid(1296))
                .output(DEFeatures.draconicBlock, 1)
                .duration(90).EUt(7)
                .buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(ingot, DRACONIUM_AWAKENED, 9)
                .output(DEFeatures.draconicBlock, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Awakened Draconium Block
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, DRACONIUM, 4)
                .output(DEFeatures.draconicBlock, 4)
                .output(dustSmall, DarkAsh, 1)
                .explosivesAmount(2)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, DRACONIUM, 4)
                .output(DEFeatures.draconicBlock, 4)
                .output(dustSmall, DarkAsh, 1)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
    }

    private static void items() {
        // ########################################
        // Cores
        // ########################################
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "wyvern_core"));
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconic_core"));
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "wyvern_energy_core"));
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconic_energy_core"));

        // Wyvern Core
        RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(Tin.getFluid(144))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(9600)
                .buildAndRegister();
        RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(SolderingAlloy.getFluid(72))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(9600)
                .buildAndRegister();

        // Draconic Core
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(38400)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(DEFeatures.wyvernEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(38400)
                .buildAndRegister();

        // Awakened Core
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 4)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, DRACONIUM_AWAKENED, 16)
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(153600)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 4)
                .input(DEFeatures.draconicCore, 1)
                .input(DEFeatures.draconicEnergyCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, DRACONIUM_AWAKENED, 16)
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.awakenedCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(153600)
                .buildAndRegister();

        // Chaotic Core
        GTERecipeMaps.DRACONIUM_AWAKENED_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorStellar.getItemNN(), 4)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, DRACONIUM_AWAKENED, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(614400)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_AWAKENED_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorStellar.getItemNN(), 4)
                .input(DEFeatures.awakenedCore, 1)
                .input(DEFeatures.draconicEnergyCore, 4)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, DRACONIUM_AWAKENED, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.chaoticCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(614400)
                .buildAndRegister();

        // Wyvern Energy Core
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, DRACONIUM, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(9600)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD, 1)
                .input(DEFeatures.wyvernCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(wireFine, DRACONIUM, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(9600)
                .buildAndRegister();

        // Draconic Energy Core
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(DEFeatures.draconicCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, DRACONIUM_AWAKENED, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(38400)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 1)
                .input(DEFeatures.draconicCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 40)
                .input(wireFine, DRACONIUM_AWAKENED, 24)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(38400)
                .buildAndRegister();

        // Chaotic Energy Core
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, DRACONIUM_AWAKENED, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(153600)
                .buildAndRegister();
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, DRACONIUM_AWAKENED, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(153600)
                .buildAndRegister();
    }

    private static void blocks() {
        // Infused Obsidian
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution","infused_obsidian"));
        ModHandler.addShapedRecipe("infused_obsidian", new ItemStack(DEFeatures.infusedObsidian, 1, 0),
                "BOB", "ODO", "BOB",
                'B', Items.BLAZE_POWDER,
                'O', ModObject.blockReinforcedObsidian.getBlockNN(),
                'D', OreDictUnifier.get(dust, DRACONIUM)
        );

        // Stabilized Mob Spawner
        ModHandler.removeRecipeByOutput(new ItemStack(DEFeatures.stabilizedSpawner, 1));

        // Generator
        ModHandler.removeRecipeByOutput(new ItemStack(DEFeatures.generator, 1));

        // Mob Grinder
        ModHandler.removeRecipeByOutput(new ItemStack(DEFeatures.grinder, 1));

        // Fusion Crafting Core
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution","fusion_crafting_core"));

        // Fusion Crafting Injector
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution","crafting_injector"));


    }

    private static void tools() {
        // Crystal Binder
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution","crystal_binder"));
        ModHandler.addShapedRecipe("crystal_binder", new ItemStack(DEFeatures.crystalBinder, 1, 0),
                "PHP", " R ", " C ",
                'H', ToolItems.HARD_HAMMER,
                'P', OreDictUnifier.get(plate, DRACONIUM),
                'R', OreDictUnifier.get(stick, ENERGETIC_ALLOY),
                'C', DEFeatures.wyvernCore
        );
    }
}
