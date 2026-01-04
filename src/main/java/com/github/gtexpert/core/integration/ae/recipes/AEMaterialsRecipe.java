package com.github.gtexpert.core.integration.ae.recipes;

import static com.github.gtexpert.core.integration.deda.DEDAConstants.ABF_DURATION_MULTIPLIER;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraftforge.fluids.FluidStack;

import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.category.RecipeCategories;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.loaders.recipe.handlers.GTEImplosionRecipeHandler;

public class AEMaterialsRecipe {

    public static void init() {
        // Iron Ingot
        ModHandler.removeFurnaceSmelting(Mods.AppliedEnergistics2.getItem("material", 1, 49));

        // Gold Ingot
        ModHandler.removeFurnaceSmelting(Mods.AppliedEnergistics2.getItem("material", 1, 51));

        // Pure Certus Quartz Crystal
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("misc/seeds_certus"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.CertusQuartz, 1)
                .input("sand", 1)
                .outputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 2, 0))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("crystal_seed").getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 10))
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("crystal_seed").getItem(), NBTMatcher.ANY, NBTCondition.ANY)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 10), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Pure Nether Quartz Crystal
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("misc/seeds_nether"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.NetherQuartz, 1)
                .input("sand", 1)
                .outputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 2, 600))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("crystal_seed").getItem(), 1, 600, NBTMatcher.ANY,
                        NBTCondition.ANY)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 11))
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("crystal_seed").getItem(), 1, 600, NBTMatcher.ANY,
                        NBTCondition.ANY)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 11), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Pure Fluix Crystal
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("misc/seeds_fluix"));
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .input("sand", 1)
                .outputs(Mods.AppliedEnergistics2.getItem("crystal_seed", 2, 1200))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("crystal_seed").getItem(), 1, 1200, NBTMatcher.ANY,
                        NBTCondition.ANY)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 12))
                .duration(600).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .inputNBT(Mods.AppliedEnergistics2.getItem("crystal_seed").getItem(), 1, 1200, NBTMatcher.ANY,
                        NBTCondition.ANY)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 12), 7000, 1000)
                .duration(1200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Fluix Pearl
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("misc/fluixpearl"));
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(gem, Materials.EnderPearl, 1)
                .fluidInputs(GTEMaterials.Fluix.getFluid(144))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 9))
                .duration(20).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // ########################################
        // Sky Stone
        // ########################################
        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 45))
                .category(RecipeCategories.MACERATOR_RECYCLING)
                .duration(500).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("smooth_sky_stone_block"))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 45))
                .category(RecipeCategories.MACERATOR_RECYCLING)
                .duration(500).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Block
        ModHandler.removeFurnaceSmelting(Mods.AppliedEnergistics2.getItem("sky_stone_block"));
        RecipeMaps.BLAST_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                .outputs(Mods.AppliedEnergistics2.getItem("smooth_sky_stone_block"))
                .duration(100).EUt(VA[GTEValues.ae2VoltageTier])
                .blastFurnaceTemp(2700)
                .buildAndRegister();
        RecipeMaps.ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                .outputs(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                .duration(100).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        if (Mods.EnderIO.isModLoaded()) {
            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                    .inputs(Mods.EnderIO.getItem("block_infinity", 1, 2))
                    .outputs(Mods.AppliedEnergistics2.getItem("sky_stone_block"))
                    .duration(500).EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }

        // ########################################
        // Nether Quartz
        // ########################################
        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 11))
                .fluidOutputs(Materials.NetherQuartz.getFluid(72))
                .category(RecipeCategories.EXTRACTOR_RECYCLING)
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 11))
                .output(dustSmall, Materials.NetherQuartz, 2)
                .category(RecipeCategories.MACERATOR_RECYCLING)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 8, 11))
                .output(block, Materials.NetherQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 11))
                .output(stick, Materials.NetherQuartz, 1)
                .duration(40).EUt(VH[LV])
                .buildAndRegister();

        // ########################################
        // Certus Quartz
        // ########################################
        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 10))
                .fluidOutputs(Materials.CertusQuartz.getFluid(72))
                .category(RecipeCategories.EXTRACTOR_RECYCLING)
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 10))
                .output(dustSmall, Materials.CertusQuartz, 2)
                .category(RecipeCategories.MACERATOR_RECYCLING)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Block
        ModHandler.addMirroredShapedRecipe("ae2_certus_quartz_block",
                Mods.AppliedEnergistics2.getItem("quartz_block"), "B",
                'B', new UnificationEntry(block, Materials.CertusQuartz));
        ModHandler.addMirroredShapedRecipe("ceu_certus_quartz_block",
                OreDictUnifier.get(block, Materials.CertusQuartz), "B",
                'B', Mods.AppliedEnergistics2.getItem("quartz_block"));
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 8, 10))
                .output(block, Materials.CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Rod
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 10))
                .output(stick, Materials.CertusQuartz, 1)
                .duration(40).EUt(VH[LV])
                .buildAndRegister();

        // ########################################
        // Charged Certus Quartz
        // ########################################
        // Gem
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(gem, Materials.CertusQuartz, 1)
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 1))
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, Materials.CertusQuartz, 1)
                .output(dust, GTEMaterials.ChargedCertusQuartz, 1)
                .duration(100).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.ChargedCertusQuartz, 1)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 1))
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.ChargedCertusQuartz, 1)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 1), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        GTEImplosionRecipeHandler.add(GTEMaterials.ChargedCertusQuartz,
                Mods.AppliedEnergistics2.getItem("material", 3, 1));

        // Lens
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.ChargedCertusQuartz, 1)
                .output(lens, GTEMaterials.ChargedCertusQuartz, 1)
                .output(dustSmall, GTEMaterials.ChargedCertusQuartz, 1)
                .duration(1200).EUt(VA[MV])
                .buildAndRegister();

        // ########################################
        // Fluix
        // ########################################
        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 12))
                .fluidOutputs(GTEMaterials.Fluix.getFluid(72))
                .category(RecipeCategories.EXTRACTOR_RECYCLING)
                .duration(14).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, GTEMaterials.ChargedCertusQuartz, 1)
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.NetherQuartz, 1)
                .output(dust, GTEMaterials.Fluix, 3)
                .duration(200).EUt(VA[3])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 1, 12))
                .output(dustSmall, GTEMaterials.Fluix, 2)
                .category(RecipeCategories.MACERATOR_RECYCLING)
                .duration(14).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .outputs(Mods.AppliedEnergistics2.getItem("material", 1, 7))
                .duration(600).EUt(24)
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Fluix, 1)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(Mods.AppliedEnergistics2.getItem("material", 1, 7), 7000, 1000)
                .duration(1200).EUt(24)
                .buildAndRegister();
        GTEImplosionRecipeHandler.add(GTEMaterials.Fluix,
                Mods.AppliedEnergistics2.getItem("material", 3, 7));

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 4, 7))
                .outputs(Mods.AppliedEnergistics2.getItem("fluix_block"))
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(Mods.AppliedEnergistics2.getItem("material", 8, 12))
                .outputs(Mods.AppliedEnergistics2.getItem("fluix_block"))
                .duration(300).EUt(2)
                .buildAndRegister();

        // Lens
        RecipeMaps.LATHE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Fluix, 1)
                .output(lens, GTEMaterials.Fluix, 1)
                .output(dustSmall, GTEMaterials.Fluix, 1)
                .duration(1200).EUt(VA[MV])
                .buildAndRegister();

        // ########################################
        // Fluix Alloy
        // ########################################
        BlastProperty propertyFluixAlloy = GTEMaterials.FluixAlloy.getProperty(PropertyKey.BLAST);
        int durationFluixAlloy = propertyFluixAlloy.getDurationOverride();
        if (durationFluixAlloy < 0) durationFluixAlloy = Math.max(1,
                (int) (GTEMaterials.FluixAlloy.getMass() * propertyFluixAlloy.getBlastTemperature() / 100L));
        int vacuumEUt = propertyFluixAlloy.getVacuumEUtOverride() != -1 ? propertyFluixAlloy.getVacuumEUtOverride() :
                VA[MV];
        int vacuumDuration = propertyFluixAlloy.getVacuumDurationOverride() != -1 ?
                propertyFluixAlloy.getVacuumDurationOverride() :
                (int) GTEMaterials.FluixAlloy.getMass() * 3;

        // Fluid
        if (GTEValues.isModLoadedDEDA()) {
            GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                    .circuitMeta(15)
                    .inputs(Mods.AppliedEnergistics2.getItem("material", 2, 45))
                    .input(dust, GTEMaterials.Fluix, 2)
                    .input(dust, Materials.Carbon, 2)
                    .input(dust, Materials.Silicon, 1)
                    .input(dust, Materials.Iron, 1)
                    .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 200))
                    .fluidOutputs(GTEMaterials.FluixAlloy.getFluid(GCYMFluidStorageKeys.MOLTEN, 1152))
                    .blastFurnaceTemp(propertyFluixAlloy.getBlastTemperature())
                    .duration((int) ((durationFluixAlloy * 0.67 * ABF_DURATION_MULTIPLIER) / 2))
                    .EUt(VA[GTEValues.ae2VoltageTier])
                    .buildAndRegister();
        }
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(14)
                .inputs(Mods.AppliedEnergistics2.getItem("material", 2, 45))
                .input(dust, GTEMaterials.Fluix, 2)
                .input(dust, Materials.Carbon, 2)
                .input(dust, Materials.Silicon, 1)
                .input(dust, Materials.Iron, 1)
                .fluidInputs(Materials.Nitrogen.getFluid(6000))
                .fluidOutputs(GTEMaterials.FluixAlloy.getFluid(GCYMFluidStorageKeys.MOLTEN, 1152))
                .blastFurnaceTemp(propertyFluixAlloy.getBlastTemperature())
                .duration((int) (durationFluixAlloy * 0.67 * ABF_DURATION_MULTIPLIER)).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();
        GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .inputs(Mods.AppliedEnergistics2.getItem("material", 2, 45))
                .input(dust, GTEMaterials.Fluix, 2)
                .input(dust, Materials.Carbon, 2)
                .input(dust, Materials.Silicon, 1)
                .input(dust, Materials.Iron, 1)
                .fluidOutputs(GTEMaterials.FluixAlloy.getFluid(GCYMFluidStorageKeys.MOLTEN, 1152))
                .blastFurnaceTemp(propertyFluixAlloy.getBlastTemperature())
                .duration(durationFluixAlloy).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(Mods.AppliedEnergistics2.getItem("material", 2, 45))
                .input(dust, GTEMaterials.Fluix, 2)
                .input(dust, Materials.Carbon, 2)
                .input(dust, Materials.Silicon, 1)
                .input(dust, Materials.Iron, 1)
                .output(dust, GTEMaterials.FluixAlloy, 8)
                .duration(200).EUt(VA[GTEValues.ae2VoltageTier])
                .buildAndRegister();

        // Ingot
        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
                .fluidInputs(new FluidStack(GTEMaterials.FluixAlloy.getFluid(GCYMFluidStorageKeys.MOLTEN), 144))
                .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 500))
                .fluidOutputs(Materials.Helium.getFluid(250))
                .output(ingot, GTEMaterials.FluixAlloy, 1)
                .duration(vacuumDuration)
                .EUt(vacuumEUt)
                .buildAndRegister();
        if (Mods.DraconicEvolution.isModLoaded()) {
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
                    .fluidInputs(new FluidStack(GTEMaterials.FluixAlloy.getFluid(GCYMFluidStorageKeys.MOLTEN), 144))
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                    .output(ingot, GTEMaterials.FluixAlloy, 1)
                    .duration(vacuumDuration / 2)
                    .EUt(vacuumEUt)
                    .buildAndRegister();
        }
    }

    public static void remove() {
        // Certus Quartz Block
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/certus_quartz_block"));
        ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/certus_quartz_block_pure"));

        if (ConfigHolder.recipes.disableManualCompression) {
            // Nether Quartz Block
            ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/quartz_block_pure"));

            // Certus Quartz Block
            ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("quartz_block", 4, 0));

            // Certus Quartz Gem
            ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("material", 4, 0));

            // Fluix Block
            ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("material", 4, 7));
            ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/fluix_block"));
            ModHandler.removeRecipeByName(Mods.AppliedEnergistics2.getResource("decorative/fluix_block_pure"));
        }
    }
}
