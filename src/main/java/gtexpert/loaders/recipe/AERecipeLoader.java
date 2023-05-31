package gtexpert.loaders.recipe;

import crazypants.enderio.base.init.ModObject;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;
import gtexpert.api.GTEValues;
import gtexpert.common.GTEConfigHolder;
import gtexpert.integration.ae.AEHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.common.items.GTEMetaItems.MATRIX_CORE;

public class AERecipeLoader {
    private static final Material[] tierMaterials = new Material[] {
            WroughtIron,
            Steel,
            Aluminium,
            StainlessSteel,
            Titanium,
            TungstenSteel,
            RhodiumPlatedPalladium,
            NaquadahAlloy,
            Darmstadtium,
            Neutronium
    };

    public static void init() {
        // craftStickQuartz
        OreDictionary.registerOre("craftStickQuartz", OreDictUnifier.get(stick, NetherQuartz));
        OreDictionary.registerOre("craftStickQuartz", OreDictUnifier.get(stick, CertusQuartz));
        OreDictionary.registerOre("craftStickQuartz", OreDictUnifier.get(stick, Quartzite));

        // craftNetherQuartz
        OreDictionary.registerOre("craftNetherQuartz", OreDictUnifier.get(gem, NetherQuartz));
        OreDictionary.registerOre("craftNetherQuartz", AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get());

        // craftCertusQuartz
        OreDictionary.registerOre("craftCertusQuartz", OreDictUnifier.get(gem, CertusQuartz));
        OreDictionary.registerOre("craftCertusQuartz", AEHelper.aeMaterials.certusQuartzCrystal().maybeStack(1).get());
        OreDictionary.registerOre("craftCertusQuartz", AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get());

        // craftFluix
        OreDictionary.registerOre("craftFluix", AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get());
        OreDictionary.registerOre("craftFluix", AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(1).get());

        materials();
        items();
        blocks();
        tools();
    }

    private static void materials() {
        // Iron Ingot
        ModHandler.removeFurnaceSmelting(AEHelper.aeMaterials.ironDust().maybeStack(1).get());

        // Gold Ingot
        ModHandler.removeFurnaceSmelting(AEHelper.aeMaterials.goldDust().maybeStack(1).get());

        // ########################################
        // Sky Stone
        // ########################################
        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(AEHelper.aeMaterials.skyDust().maybeStack(1).get())
                .duration(500).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Block
        ModHandler.removeFurnaceSmelting(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(Item.getItemFromBlock(ModObject.block_infinity.getBlockNN()), 4, 2)
                .outputs(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .duration(500).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.smoothSkyStoneBlock().maybeStack(1).get())
                .duration(100).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .blastFurnaceTemp(2700)
                .buildAndRegister();
        RecipeMaps.ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .outputs(AEHelper.aeBlocks.skyStoneBlock().maybeStack(1).get())
                .duration(100).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();


        // ########################################
        // Neter Quartz
        // ########################################
        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(NetherQuartz.getFluid(72))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .output(dustSmall, NetherQuartz, 2)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/quartz_block_pure"));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(8).get())
                .output(block, NetherQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get())
                .output(stick, NetherQuartz, 1)
                .duration(40).EUt(16)
                .buildAndRegister();


        // ########################################
        // Certus Quartz
        // ########################################
        OreDictionary.registerOre("gemCertusQuartz", AEHelper.aeMaterials.certusQuartzCrystal().maybeStack(1).get());

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(CertusQuartz.getFluid(72))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.certusQuartzCrystal().maybeStack(1).get())
                .fluidOutputs(CertusQuartz.getFluid(144))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.quartzBlock().maybeStack(1).get())
                .fluidOutputs(CertusQuartz.getFluid(576))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .output(dustSmall, CertusQuartz, 2)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.certusQuartzCrystal().maybeStack(1).get())
                .output(dust, CertusQuartz, 1)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.quartzBlock().maybeStack(1).get())
                .output(dust, CertusQuartz, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/certus_quartz_block"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/certus_quartz_block_pure"));
        ModHandler.removeRecipeByOutput(AEHelper.aeMaterials.certusQuartzCrystal().maybeStack(4).get());
        ModHandler.addMirroredShapedRecipe("ae2_certus_quartz_block", AEHelper.aeBlocks.quartzBlock().maybeStack(1).get(), "B", 'B', new UnificationEntry(block, CertusQuartz));
        ModHandler.addMirroredShapedRecipe("ceu_certus_quartz_block", OreDictUnifier.get(block, CertusQuartz), "B", 'B', AEHelper.aeBlocks.quartzBlock().maybeStack(1).get());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(8).get())
                .output(block, CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get())
                .output(stick, CertusQuartz, 1)
                .duration(40).EUt(16)
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.quartzBlock().maybeStack(1).get())
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, CertusQuartz, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.quartzBlock().maybeStack(1).get())
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, CertusQuartz, 4)
                .duration(240).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.quartzBlock().maybeStack(1).get())
                .fluidInputs(Water.getFluid(15))
                .output(plate, CertusQuartz, 4)
                .duration(300).EUt(VA[LV])
                .buildAndRegister();


        // ########################################
        // Charged Certus Quartz
        // ########################################
        OreDictionary.registerOre("gemChargedCertusQuartz", AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());
        OreDictionary.registerOre("crystalChargedCertusQuartz", AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .fluidOutputs(CHARGED_CERTUS_QUARTZ.getFluid(144))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.certusQuartzDust().maybeStack(1).get())
                .output(dust, CHARGED_CERTUS_QUARTZ, 1)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .output(dust, CHARGED_CERTUS_QUARTZ, 1)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(gem, CertusQuartz, 1)
                .outputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
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
                .outputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get())
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, CHARGED_CERTUS_QUARTZ, 1)
                .fluidInputs(Water.getFluid(250))
                .chancedOutput(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, CHARGED_CERTUS_QUARTZ, 4)
                .explosivesAmount(2)
                .outputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, CHARGED_CERTUS_QUARTZ, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(AEHelper.aeMaterials.certusQuartzCrystalCharged().maybeStack(3).get())
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
        OreDictionary.registerOre("blockFluix", AEHelper.aeBlocks.fluixBlock().maybeStack(1).get());
        OreDictionary.registerOre("gemFluix", AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get());

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .fluidOutputs(FLUIX.getFluid(72))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get())
                .fluidOutputs(FLUIX.getFluid(144))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .fluidOutputs(FLUIX.getFluid(576))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, CHARGED_CERTUS_QUARTZ, 1)
                .input(dust, Redstone, 1)
                .input(dust, NetherQuartz, 1)
                .output(dust, FLUIX, 3)
                .duration(200).EUt(VA[3])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(1).get())
                .output(dustSmall, FLUIX, 2)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get())
                .output(dust, FLUIX, 1)
                .duration(80).EUt(2)
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .output(dust, FLUIX, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, FLUIX, 1)
                .fluidInputs(DistilledWater.getFluid(50))
                .outputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get())
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, FLUIX, 1)
                .fluidInputs(Water.getFluid(250))
                .chancedOutput(AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get(), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, FLUIX, 4)
                .explosivesAmount(2)
                .outputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(dust, FLUIX, 4)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .outputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(3).get())
                .output(dustSmall, DarkAsh, 1)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/fluix_block"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "decorative/fluix_block_pure"));
        ModHandler.removeRecipeByOutput(AEHelper.aeMaterials.fluixCrystal().maybeStack(4).get());
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.fluixCrystal().maybeStack(4).get())
                .outputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(8).get())
                .outputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(FLUIX.getFluid(576))
                .outputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .duration(20).EUt(7)
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, FLUIX, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, FLUIX, 4)
                .duration(240).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeBlocks.fluixBlock().maybeStack(1).get())
                .fluidInputs(Water.getFluid(15))
                .output(plate, FLUIX, 4)
                .duration(300).EUt(VA[LV])
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
                .circuitMeta(2)
                .inputs(AEHelper.aeMaterials.skyDust().maybeStack(2).get())
                .input(dust, FLUIX, 2)
                .input(dust, Carbon, 2)
                .input(dust, Silicon, 1)
                .input(dust, Iron, 1)
                .output(dust, FLUIX_ALLOY, 8)
                .duration(200).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();
    }

    private static void blocks() {
        // Quartz Fiber
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "network/parts/quartz_fiber_part"));
        ModHandler.addMirroredShapedRecipe("nether_quartz_cutter_wire", AEHelper.aeParts.quartzFiber().maybeStack(1).get(), "Px", 'P', OreDictUnifier.get(plate, NetherQuartz));
        ModHandler.addMirroredShapedRecipe("certus_quartz_cutter_wire", AEHelper.aeParts.quartzFiber().maybeStack(1).get(), "Px", 'P', OreDictUnifier.get(plate, CertusQuartz));
        ModHandler.addMirroredShapedRecipe("quartzite_cutter_wire", AEHelper.aeParts.quartzFiber().maybeStack(1).get(), "Px", 'P', OreDictUnifier.get(plate, Quartzite));
        RecipeMaps.WIREMILL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input("craftStickQuartz", 1)
                .outputs(AEHelper.aeParts.quartzFiber().maybeStack(2).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();
    }

    private static void items() {
        // Formation Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "materials/formationcore"));
        ModHandler.addShapedRecipe("formation_core", AEHelper.aeMaterials.formationCore().maybeStack(1).get(),
                "SES", "LQL", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1]),
                'Q', OreDictUnifier.get(gem, NetherQuartz),
                'E', AEHelper.aeMaterials.engProcessor().maybeStack(1).get(),
                'L', AEHelper.aeMaterials.logicProcessor().maybeStack(1).get());
        ModHandler.addShapedRecipe("formation_core_pure", AEHelper.aeMaterials.formationCore().maybeStack(2).get(),
                "SES", "LQL", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1]),
                'Q', AEHelper.aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get(),
                'E', AEHelper.aeMaterials.engProcessor().maybeStack(1).get(),
                'L', AEHelper.aeMaterials.logicProcessor().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1], 2)
                .inputs(AEHelper.aeMaterials.engProcessor().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.logicProcessor().maybeStack(1).get())
                .input("craftNetherQuartz", 1)
                .outputs(AEHelper.aeMaterials.formationCore().maybeStack(4).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2 + 1])
                .buildAndRegister();

        // Annihilation Core
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "materials/annihilationcore"));
        ModHandler.addShapedRecipe("annihilation_core", AEHelper.aeMaterials.annihilationCore().maybeStack(1).get(),
                "SES", "CQC", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1]),
                'Q', "craftCertusQuartz",
                'E', AEHelper.aeMaterials.engProcessor().maybeStack(1).get(),
                'C', AEHelper.aeMaterials.calcProcessor().maybeStack(1).get());
        ModHandler.addShapedRecipe("annihilation_core_pure", AEHelper.aeMaterials.annihilationCore().maybeStack(2).get(),
                "SES", "CQC", "SES",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1]),
                'Q', AEHelper.aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get(),
                'E', AEHelper.aeMaterials.engProcessor().maybeStack(1).get(),
                'C', AEHelper.aeMaterials.calcProcessor().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1], 2)
                .inputs(AEHelper.aeMaterials.engProcessor().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.calcProcessor().maybeStack(1).get())
                .input("craftCertusQuartz", 1)
                .outputs(AEHelper.aeMaterials.annihilationCore().maybeStack(4).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2 + 1])
                .buildAndRegister();

        // Matrix Core
        ModHandler.addShapedRecipe("matrix_core", MATRIX_CORE.getStackForm(),
                "SAS", "FQF", "SAS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1]),
                'Q', AEHelper.aeMaterials.fluixCrystal().maybeStack(1).get(),
                'A', AEHelper.aeMaterials.annihilationCore().maybeStack(1).get(),
                'F', AEHelper.aeMaterials.formationCore().maybeStack(1).get());
        ModHandler.addShapedRecipe("matrix_core_pure", MATRIX_CORE.getStackForm(2),
                "SAS", "FQF", "SAS",
                'S', OreDictUnifier.get(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1]),
                'Q', AEHelper.aeMaterials.purifiedFluixCrystal().maybeStack(1).get(),
                'A', AEHelper.aeMaterials.annihilationCore().maybeStack(1).get(),
                'F', AEHelper.aeMaterials.formationCore().maybeStack(1).get());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1], 2)
                .inputs(AEHelper.aeMaterials.annihilationCore().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.formationCore().maybeStack(1).get())
                .input("craftFluix", 1)
                .output(MATRIX_CORE, 4)
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2 + 1])
                .buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(stick, tierMaterials[GTEConfigHolder.voltageTierAE2 - 1], 4)
                .inputs(AEHelper.aeMaterials.engProcessor().maybeStack(2).get())
                .inputs(AEHelper.aeMaterials.logicProcessor().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.calcProcessor().maybeStack(1).get())
                .input("craftNetherQuartz", 1)
                .input("craftCertusQuartz", 1)
                .input("craftFluix", 1)
                .output(MATRIX_CORE, 4)
                .duration(100).EUt(VA[GTEConfigHolder.voltageTierAE2 + 1])
                .buildAndRegister();

        // Silicon Processor Press
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, NetherQuartz)
                .input(block, Iron, 1)
                .outputs(AEHelper.aeMaterials.siliconPress().maybeStack(1).get())
                .duration(2000).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Logic Processor Press
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, CHARGED_CERTUS_QUARTZ)
                .input(block, Iron, 1)
                .outputs(AEHelper.aeMaterials.logicProcessorPress().maybeStack(1).get())
                .duration(2000).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Calc Processor Press
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, CertusQuartz)
                .input(block, Iron, 1)
                .outputs(AEHelper.aeMaterials.calcProcessorPress().maybeStack(1).get())
                .duration(2000).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Engineer Processor Press
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, FLUIX)
                .input(block, Iron, 1)
                .outputs(AEHelper.aeMaterials.engProcessorPress().maybeStack(1).get())
                .duration(2000).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Silicon Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeMaterials.siliconPress().maybeStack(1).get())
                .input(plate, Silicon, 1)
                .outputs(AEHelper.aeMaterials.siliconPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Logic Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeMaterials.logicProcessorPress().maybeStack(1).get())
                .input(plate, Gold, 1)
                .outputs(AEHelper.aeMaterials.logicProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Calc Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeMaterials.calcProcessorPress().maybeStack(1).get())
                .input(plate, CertusQuartz, 1)
                .outputs(AEHelper.aeMaterials.calcProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Engineer Circuit
        RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder()
                .notConsumable(AEHelper.aeMaterials.engProcessorPress().maybeStack(1).get())
                .input(plate, Diamond, 1)
                .outputs(AEHelper.aeMaterials.engProcessorPrint().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Logic Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.logicProcessorPrint().maybeStack(1).get())
                .fluidInputs(Redstone.getFluid(144))
                .outputs(AEHelper.aeMaterials.logicProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Calc Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.calcProcessorPrint().maybeStack(1).get())
                .fluidInputs(Redstone.getFluid(144))
                .outputs(AEHelper.aeMaterials.calcProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();

        // Engineer Processor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(AEHelper.aeMaterials.siliconPrint().maybeStack(1).get())
                .inputs(AEHelper.aeMaterials.engProcessorPrint().maybeStack(1).get())
                .fluidInputs(Redstone.getFluid(144))
                .outputs(AEHelper.aeMaterials.engProcessor().maybeStack(1).get())
                .duration(20).EUt(VA[GTEConfigHolder.voltageTierAE2])
                .buildAndRegister();
    }

    private static void tools() {
        if (ConfigHolder.recipes.hardToolArmorRecipes) {
            // Nether Quartz Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_axe"));
            ModHandler.addShapedRecipe("nether_quartz_axe", AEHelper.aeItems.netherQuartzAxe().maybeStack(1).get(),
                    "PQf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'Q', OreDictUnifier.get(gem, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_hoe"));
            ModHandler.addShapedRecipe("nether_quartz_hoe", AEHelper.aeItems.netherQuartzHoe().maybeStack(1).get(),
                    "PQf", "hS ", " S ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'Q', OreDictUnifier.get(gem, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_pickaxe"));
            ModHandler.addShapedRecipe("nether_quartz_pickaxe", AEHelper.aeItems.netherQuartzPick().maybeStack(1).get(),
                    "PQQ", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'Q', OreDictUnifier.get(gem, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_spade"));
            ModHandler.addShapedRecipe("nether_quartz_spade", AEHelper.aeItems.netherQuartzShovel().maybeStack(1).get(),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_sword"));
            ModHandler.addShapedRecipe("nether_quartz_sword", AEHelper.aeItems.netherQuartzSword().maybeStack(1).get(),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Cutting Knife
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_cutting_knife"));
            ModHandler.addShapedRecipe("nether_quartz_cutting_knife", AEHelper.aeItems.netherQuartzKnife().maybeStack(1).get(),
                    "fPh", "QSQ", " S ",
                    'Q', new ItemStack(Items.QUARTZ),
                    'P', OreDictUnifier.get(plate, NetherQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Nether Quartz Wrench
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_wrench"));
            ModHandler.addShapedRecipe("ether_quartz_wrench", AEHelper.aeItems.netherQuartzWrench().maybeStack(1).get(),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, NetherQuartz));

            // Certus Quartz Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_axe"));
            ModHandler.addShapedRecipe("certus_quartz_axe", AEHelper.aeItems.certusQuartzAxe().maybeStack(1).get(),
                    "PQf", "PS ", "hS ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_hoe"));
            ModHandler.addShapedRecipe("certus_quartz_hoe", AEHelper.aeItems.certusQuartzHoe().maybeStack(1).get(),
                    "PQf", "hS ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_pickaxe"));
            ModHandler.addShapedRecipe("certus_quartz_pickaxe", AEHelper.aeItems.certusQuartzPick().maybeStack(1).get(),
                    "PQQ", "hSf", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_spade"));
            ModHandler.addShapedRecipe("certus_quartz_spade", AEHelper.aeItems.certusQuartzShovel().maybeStack(1).get(),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_sword"));
            ModHandler.addShapedRecipe("certus_quartz_sword", AEHelper.aeItems.certusQuartzSword().maybeStack(1).get(),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Cutting Knife
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_cutting_knife"));
            ModHandler.addShapedRecipe("certus_quartz_cutting_knife", AEHelper.aeItems.certusQuartzKnife().maybeStack(1).get(),
                    "fPh", "QSQ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, CertusQuartz),
                    'S', OreDictUnifier.get(stick, Wood));

            // Certus Quartz Wrench
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_wrench"));
            ModHandler.addShapedRecipe("certus_quartz_wrench", AEHelper.aeItems.certusQuartzWrench().maybeStack(1).get(),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, CertusQuartz));
        }
    }
}
