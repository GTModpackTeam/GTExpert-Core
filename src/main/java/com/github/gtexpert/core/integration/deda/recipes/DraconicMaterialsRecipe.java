package com.github.gtexpert.core.integration.deda.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import com.brandon3055.draconicevolution.DEFeatures;

import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.api.util.Mods;

public class DraconicMaterialsRecipe {

    public static final int ABFPyrotheumAmount = 200;
    public static final double ABFDurationMultiplier = 0.5;

    public static void init() {
        // ########################################
        // Draconic Evolution
        // ########################################
        // Dragon Dust
        RecipeBuilder<?> builderDD = RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Materials.Iridium, 1)
                .fluidInputs(Materials.SaltWater.getFluid(1000))
                .fluidInputs(Materials.EnderEye.getFluid(144))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, GTEMaterials.Dragon, 2)
                .duration(600).EUt(VA[GTEValues.dedaVoltageTier]);
        if (Mods.EnderIO.isModLoaded()) {
            builderDD.input(dust, GTEMaterials.EndSteel, 1);
        } else {
            builderDD.input(dust, Materials.Endstone, 1);
        }
        builderDD.buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(Blocks.DRAGON_EGG)
                .output(dust, GTEMaterials.Dragon, 8)
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();

        // Chaos Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Dragon, 8)
                .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                .fluidInputs(GTEMaterials.AwakenedDraconium.getFluid(1152))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, GTEMaterials.Chaos, 2)
                .duration(1200).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.chaosShard, 1, 1))
                .output(dust, GTEMaterials.Chaos, 1)
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Chaos, 1)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .output(DEFeatures.chaosShard, 1, 1)
                .duration(1200).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Chaos, 1)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(new ItemStack(DEFeatures.chaosShard, 1, 1), 7000, 1000)
                .duration(2400).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();

        // Draconium Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Dragon, 1)
                .input(dust, Materials.Obsidian, 1)
                .fluidInputs(Materials.LiquidEnderAir.getFluid(8000))
                .fluidInputs(Materials.EnderPearl.getFluid(576))
                .output(dust, GTEMaterials.Draconium, 2)
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();

        // Draconium Block
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconium_block"));
        ModHandler.addMirroredShapedRecipe("de_draconium_block", new ItemStack(DEFeatures.draconiumBlock), "B", 'B',
                OreDictUnifier.get(block, GTEMaterials.Draconium));
        ModHandler.addMirroredShapedRecipe("ceu_draconium_block", OreDictUnifier.get(block, GTEMaterials.Draconium),
                "B", 'B',
                new ItemStack(DEFeatures.draconiumBlock));

        // Awakened Draconium Block
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconic_block"));
        ModHandler.addMirroredShapedRecipe("de_draconic_block", new ItemStack(DEFeatures.draconicBlock), "B", 'B',
                OreDictUnifier.get(block, GTEMaterials.AwakenedDraconium));
        ModHandler.addMirroredShapedRecipe("ceu_draconic_block",
                OreDictUnifier.get(block, GTEMaterials.AwakenedDraconium), "B",
                'B', new ItemStack(DEFeatures.draconicBlock));
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, GTEMaterials.Draconium, 4)
                .output(block, GTEMaterials.AwakenedDraconium, 3)
                .explosivesType(new ItemStack(MetaBlocks.POWDERBARREL, 8))
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, GTEMaterials.Draconium, 4)
                .output(block, GTEMaterials.AwakenedDraconium, 3)
                .explosivesAmount(4)
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, GTEMaterials.Draconium, 4)
                .output(block, GTEMaterials.AwakenedDraconium, 3)
                .explosivesType(MetaItems.DYNAMITE.getStackForm(2))
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, GTEMaterials.Draconium, 4)
                .output(block, GTEMaterials.AwakenedDraconium, 3)
                .explosivesType(new ItemStack(MetaBlocks.ITNT))
                .buildAndRegister();

        // Extended recipes
        List<Material> materials = new ArrayList<>(GregTechAPI.materialManager.getRegisteredMaterials());
        materials.forEach(DraconicMaterialsRecipe::vacuumFreezerExtended);
    }

    /**
     * Vacuum Freezer to extended recipes
     *
     * @param material The material to add recipes for
     */
    private static void vacuumFreezerExtended(@NotNull Material material) {
        // Check if the material has a blast recipe
        if (!material.hasProperty(GCYMPropertyKey.ALLOY_BLAST)) return;

        // Check if the material has a molten fluid
        Fluid molten = material.getFluid(GCYMFluidStorageKeys.MOLTEN);
        if (molten == null) return;

        // Get the vacuum freezer EUt and duration
        BlastProperty property = material.getProperty(PropertyKey.BLAST);
        int vacuumEUt = property.getVacuumEUtOverride() != -1 ? property.getVacuumEUtOverride() : VA[MV];
        int vacuumDuration = property.getVacuumDurationOverride() != -1 ? property.getVacuumDurationOverride() :
                (int) (material.getMass() * 0.5);

        // Check if the material has a blast temperature above 5000K
        if (property.getBlastTemperature() >= 5000) {
            if (material.hasFlag(GENERATE_PLATE)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_PLATE)
                        .fluidInputs(new FluidStack(molten, 144))
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                        .output(plate, material, 1)
                        .duration(vacuumDuration / 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            if (material.hasFlag(GENERATE_SMALL_GEAR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_GEAR_SMALL)
                        .fluidInputs(new FluidStack(molten, 144))
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                        .output(gearSmall, material, 1)
                        .duration(vacuumDuration / 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            if (material.hasFlag(GENERATE_GEAR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_GEAR)
                        .fluidInputs(new FluidStack(molten, 576))
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(1000))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 200))
                        .output(gear, material, 1)
                        .duration(vacuumDuration * 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            if (material.hasFlag(GENERATE_ROTOR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_ROTOR)
                        .fluidInputs(new FluidStack(molten, 576))
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(1000))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 200))
                        .output(rotor, material, 1)
                        .duration(vacuumDuration / 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .input(ingotHot, material, 1)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                    .output(ingot, material, 1)
                    .duration(vacuumDuration / 2)
                    .EUt(vacuumEUt)
                    .buildAndRegister();
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .fluidInputs(new FluidStack(molten, 144))
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                    .fluidOutputs(material.getFluid(144))
                    .duration(vacuumDuration / 2)
                    .EUt(vacuumEUt)
                    .buildAndRegister();
        }
    }

    public static void remove() {
        // Draconium Ore
        ModHandler.removeFurnaceSmelting(Mods.DraconicEvolution.getItem("draconium_ore", 1));
        ModHandler.removeFurnaceSmelting(Mods.DraconicEvolution.getItem("draconium_ore", 1, 1));
        ModHandler.removeFurnaceSmelting(Mods.DraconicEvolution.getItem("draconium_ore", 1, 2));

        // Draconium Dust
        ModHandler.removeFurnaceSmelting(Mods.DraconicEvolution.getItem("draconium_dust"));

        if (ConfigHolder.recipes.disableManualCompression) {
            // Draconium Nugget
            ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("nugget"));

            // Draconium Ingot
            ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconium_ingot"));
            ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconium_ingot_1"));
            ModHandler.removeFurnaceSmelting(Mods.DraconicEvolution.getItem("draconium_ingot"));

            // Awakened Draconium Nugget
            ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("nugget_1"));

            // Awakened Draconium Ingot
            ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconic_ingot"));
            ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconic_ingot_1"));
        }
    }
}
