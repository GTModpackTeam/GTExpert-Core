package gtexpert.loaders.recipe;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.ToolItems;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;
import com.brandon3055.draconicevolution.DEFeatures;
import gtexpert.api.recipes.GTERecipeMaps;
import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
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
        tools();
    }

    public static void materias() {
        // Cryotheum Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Redstone, 1)
                .input(dust, EnderPearl, 1)
                .fluidInputs(Ice.getFluid(8000))
                .output(dust, CRYOTHEUM, 2)
                .duration(300).EUt(VA[HV])
                .buildAndRegister();

        // Cryotheum Liquid
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .fluidInputs(PYROTHEUM.getFluid(1000))
                .fluidOutputs(CRYOTHEUM.getFluid(1000))
                .duration(100).EUt(VA[HV])
                .buildAndRegister();

        // Pyrotheum Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Redstone, 1)
                .input(dust, Sulfur, 1)
                .fluidInputs(Blaze.getFluid(8000))
                .output(dust, PYROTHEUM, 2)
                .duration(300).EUt(VA[HV])
                .buildAndRegister();

        // Dragon Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, END_STEEL, 1)
                .input(dust, Iridium, 1)
                .fluidInputs(SaltWater.getFluid(1000))
                .fluidInputs(EnderEye.getFluid(144))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, DRAGON, 2)
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(Blocks.DRAGON_EGG, 1)
                .output(dust, DRAGON, 32)
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
    }

    public static void items() {
        // ########################################
        // Cores
        // ########################################
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "wyvern_core"));
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution", "draconic_core"));

        // Wyvern Core
        RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(Tin.getFluid(144))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(9600)
                .buildAndRegister();
        RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD, 1)
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 4)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(SolderingAlloy.getFluid(72))
                .output(DEFeatures.wyvernCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(9600)
                .buildAndRegister();

        // Draconic Core
        GTERecipeMaps.DRACONIC_FUSION_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(WETWARE_BOARD, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 4)
                .input(DEFeatures.wyvernCore, 1)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, DRACONIUM, 16)
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.draconicCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(38400)
                .buildAndRegister();

        // Wyvern Energy Core
        GTERecipeMaps.DRACONIC_FUSION_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(plate, DRACONIUM, 4)
                .input(DEFeatures.wyvernCore, 1)
                .fluidInputs(Redstone.getFluid(16000))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DEFeatures.wyvernEnergyCore, 1)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(38400)
                .buildAndRegister();
    }

    public static void tools() {
        // Crystal Binder
        ModHandler.removeRecipeByName(new ResourceLocation("draconicevolution","crystal_binder"));
        ModHandler.addShapedRecipe("crystal_binder", new ItemStack(DEFeatures.crystalBinder, 1, 0),
                "PHP", " R ", " C ",
                'H', ToolItems.HARD_HAMMER,
                'P', OreDictUnifier.get(plate, DRACONIUM),
                'R', OreDictUnifier.get(stick, ENERGETIC_ALLOY),
                'C', DEFeatures.draconicCore
        );
    }
}
