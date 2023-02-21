package gtexpert.loaders.recipe;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.fluids.MetaFluids;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
import appeng.api.AEApi;
import appeng.api.definitions.IBlocks;
import appeng.api.definitions.IItems;
import appeng.api.definitions.IMaterials;
import appeng.api.definitions.IParts;
import crazypants.enderio.base.init.ModObject;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.HULL;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

public class AERecipeLoader {
    public static void init() {
        IItems aeItems = AEApi.instance().definitions().items();
        IBlocks aeBlocks = AEApi.instance().definitions().blocks();
        IMaterials aeMaterials = AEApi.instance().definitions().materials();
        IParts aeParts = AEApi.instance().definitions().parts();


        // ########################################
        // Sky Stone
        // ########################################
        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(aeMaterials.skyDust().maybeStack(1).get())
                .duration(500).EUt(VA[HV])
                .buildAndRegister();

        // Block
        ModHandler.removeFurnaceSmelting(aeBlocks.skyStoneBlock().maybeStack(1).get());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(Item.getItemFromBlock(ModObject.block_infinity.getBlockNN()), 4, 2)
                .outputs(aeBlocks.skyStoneBlock().maybeStack(1).get())
                .duration(500).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder()
                .inputs(aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(aeBlocks.smoothSkyStoneBlock().maybeStack(1).get())
                .duration(100).EUt(VA[HV])
                .blastFurnaceTemp(2700)
                .buildAndRegister();
        RecipeMaps.ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(aeBlocks.skyStoneBlock().maybeStack(1).get())
                .duration(100).EUt(VA[HV])
                .buildAndRegister();


        // ########################################
        // Inscriber
        // ########################################
        // Press
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, NetherQuartz)
                .input(block, Iron, 1)
                .outputs(aeMaterials.siliconPress().maybeStack(1).get())
                .duration(2000).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Gold)
                .input(block, Iron, 1)
                .outputs(aeMaterials.logicProcessorPress().maybeStack(1).get())
                .duration(2000).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, CertusQuartz)
                .input(block, Iron, 1)
                .outputs(aeMaterials.calcProcessorPress().maybeStack(1).get())
                .duration(2000).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Diamond)
                .input(block, Iron, 1)
                .outputs(aeMaterials.engProcessorPress().maybeStack(1).get())
                .duration(2000).EUt(VA[HV])
                .buildAndRegister();

        // Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(aeMaterials.siliconPress().maybeStack(1).get())
                .input(plate, Silicon, 1)
                .outputs(aeMaterials.siliconPrint().maybeStack(1).get())
                .duration(20).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(aeMaterials.logicProcessorPress().maybeStack(1).get())
                .input(plate, Gold, 1)
                .outputs(aeMaterials.logicProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(aeMaterials.calcProcessorPress().maybeStack(1).get())
                .input(plate, CertusQuartz, 1)
                .outputs(aeMaterials.calcProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(aeMaterials.engProcessorPress().maybeStack(1).get())
                .input(plate, Diamond, 1)
                .outputs(aeMaterials.engProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[HV])
                .buildAndRegister();

        // Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(aeMaterials.logicProcessorPrint().maybeStack(1).get())
                .fluidInputs(Redstone.getFluid(144))
                .outputs(aeMaterials.logicProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(aeMaterials.calcProcessorPrint().maybeStack(1).get())
                .fluidInputs(Redstone.getFluid(144))
                .outputs(aeMaterials.calcProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[HV])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(aeMaterials.engProcessorPrint().maybeStack(1).get())
                .fluidInputs(Redstone.getFluid(144))
                .outputs(aeMaterials.engProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[HV])
                .buildAndRegister();


        // ########################################
        // Neter Quartz
        // ########################################
        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(NetherQuartz.getFluid(72))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .output(dustSmall, NetherQuartz, 2)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(8).get())
                .output(block, NetherQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .output(stick, NetherQuartz, 1)
                .duration(40).EUt(16)
                .buildAndRegister();


        // ########################################
        // Certus Quartz
        // ########################################
        OreDictionary.registerOre("blockCertusQuartz", aeBlocks.quartzBlock().maybeStack(1).get());
        OreDictionary.registerOre("gemCertusQuartz", aeMaterials.certusQuartzCrystal().maybeStack(1).get());

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(CertusQuartz.getFluid(72))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.certusQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(CertusQuartz.getFluid(144))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeBlocks.quartzBlock().maybeStack(1).get())
                .fluidOutputs(CertusQuartz.getFluid(576))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .output(dustSmall, CertusQuartz, 2)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.certusQuartzCrystal().maybeStack(1).get())
                .output(dust, CertusQuartz, 1)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeBlocks.quartzBlock().maybeStack(1).get())
                .output(dust, CertusQuartz, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.certusQuartzCrystal().maybeStack(4).get())
                .output(block, CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(8).get())
                .output(block, CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .output(stick, CertusQuartz, 1)
                .duration(40).EUt(16)
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.quartzBlock().maybeStack(1).get())
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, CertusQuartz, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.quartzBlock().maybeStack(1).get())
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, CertusQuartz, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.quartzBlock().maybeStack(1).get())
                .fluidInputs(Water.getFluid(15))
                .output(plate, CertusQuartz, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();


        // ########################################
        // Charged Certus Quartz
        // ########################################
        OreDictionary.registerOre("gemChargedCertusQuartz", aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());
        OreDictionary.registerOre("crystalChargedCertusQuartz", aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .fluidOutputs(CHARGED_CERTUS_QUARTZ.getFluid(144))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.certusQuartzDust().maybeStack(1).get())
                .output(dust, CHARGED_CERTUS_QUARTZ, 1)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .output(dust, CHARGED_CERTUS_QUARTZ, 1)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(gem, CertusQuartz, 1)
                .outputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, CertusQuartz, 1)
                .output(dust, CHARGED_CERTUS_QUARTZ, 1)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, CHARGED_CERTUS_QUARTZ, 1)
                .fluidInputs(DistilledWater.getFluid(50))
                .outputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, CHARGED_CERTUS_QUARTZ, 1)
                .fluidInputs(Water.getFluid(250))
                .chancedOutput(aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, CHARGED_CERTUS_QUARTZ, 4)
                .explosivesAmount(2)
                .outputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, CHARGED_CERTUS_QUARTZ, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(aeMaterials.certusQuartzCrystalCharged().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Lens
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .input(plate, CHARGED_CERTUS_QUARTZ, 1)
                .output(lens, CHARGED_CERTUS_QUARTZ, 1)
                .output(dustSmall, CHARGED_CERTUS_QUARTZ, 1)
                .duration(1200).EUt(VA[MV])
                .buildAndRegister();


        // ########################################
        // Fluix
        // ########################################
        OreDictionary.registerOre("blockFluix", aeBlocks.fluixBlock().maybeStack(1).get());
        OreDictionary.registerOre("gemFluix", aeMaterials.fluixCrystal().maybeStack(1).get());

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .fluidOutputs(FLUIX.getFluid(72))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.fluixCrystal().maybeStack(1).get())
                .fluidOutputs(FLUIX.getFluid(144))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .fluidOutputs(FLUIX.getFluid(576))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(1))
                .input(dust, CHARGED_CERTUS_QUARTZ, 1)
                .input(dust, Redstone, 1)
                .input(dust, NetherQuartz, 1)
                .output(dust, FLUIX, 3)
                .duration(200).EUt(VA[3])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .output(dustSmall, FLUIX, 2)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.fluixCrystal().maybeStack(1).get())
                .output(dust, FLUIX, 1)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .output(dust, FLUIX, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, FLUIX, 1)
                .fluidInputs(DistilledWater.getFluid(50))
                .outputs(aeMaterials.fluixCrystal().maybeStack(1).get())
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, FLUIX, 1)
                .fluidInputs(Water.getFluid(250))
                .chancedOutput(aeMaterials.fluixCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, FLUIX, 4)
                .explosivesAmount(2)
                .outputs(aeMaterials.fluixCrystal().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, FLUIX, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(aeMaterials.fluixCrystal().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.fluixCrystal().maybeStack(4).get())
                .outputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(aeMaterials.purifiedFluixCrystal().maybeStack(8).get())
                .outputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(FLUIX.getFluid(576))
                .outputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .duration(20).EUt(7)
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, FLUIX, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, FLUIX, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(aeBlocks.fluixBlock().maybeStack(1).get())
                .fluidInputs(Water.getFluid(15))
                .output(plate, FLUIX, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();

        // Lens
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .input(plate, FLUIX, 1)
                .output(lens, FLUIX, 1)
                .output(dustSmall, FLUIX, 1)
                .duration(1200).EUt(VA[MV])
                .buildAndRegister();


        // ########################################
        // Fluix Alloy
        // ########################################
        // Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(2))
                .inputs(aeMaterials.skyDust().maybeStack(2).get())
                .input(dust, FLUIX, 2)
                .input(dust, Carbon, 2)
                .input(dust, Silicon, 1)
                .input(dust, Iron, 1)
                .output(dust, FLUIX_ALLOY, 8)
                .duration(200).EUt(VA[HV])
                .buildAndRegister();
    }
}
