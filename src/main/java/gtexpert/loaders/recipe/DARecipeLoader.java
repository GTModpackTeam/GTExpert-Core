package gtexpert.loaders.recipe;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;
import com.brandon3055.draconicevolution.DEFeatures;
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
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;

public class DARecipeLoader {
    public static void init() {
        items();
        blocks();
        tools();
    }

    private static void items() {
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

    private static void blocks() {}

    private static void tools() {}
}
