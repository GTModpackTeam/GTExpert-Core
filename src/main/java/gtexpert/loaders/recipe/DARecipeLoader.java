package gtexpert.loaders.recipe;

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
                .duration(400).EUt(614400)
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
                .duration(400).EUt(614400)
                .buildAndRegister();
    }

    private static void blocks() {}

    private static void tools() {
        // Chaotic Staff of Power
        GTERecipeMaps.DRACONIUM_AWAKENED_FUSION_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.draconicStaffOfPower, 1))
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticStaffOfPower, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Bow
        GTERecipeMaps.DRACONIUM_AWAKENED_FUSION_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.draconicBow, 1))
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticBow, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Helm
        GTERecipeMaps.DRACONIUM_AWAKENED_FUSION_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.draconicHelm, 1))
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticHelm, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Chestplate
        GTERecipeMaps.DRACONIUM_AWAKENED_FUSION_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.draconicChest, 1))
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticChest, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Leggings
        GTERecipeMaps.DRACONIUM_AWAKENED_FUSION_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.draconicLegs, 1))
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticLegs, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Boots
        GTERecipeMaps.DRACONIUM_AWAKENED_FUSION_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.draconicBoots, 1))
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticBoots, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();
    }
}
