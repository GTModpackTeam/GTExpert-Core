package com.github.gtexpert.core.mixins.gcym;

import static com.github.gtexpert.core.integration.deda.recipes.DraconicMaterialsRecipe.ABFDurationMultiplier;
import static com.github.gtexpert.core.integration.deda.recipes.DraconicMaterialsRecipe.ABFPyrotheumAmount;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtech.loaders.recipe.CraftingComponent;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregicality.multiblocks.api.recipes.alloyblast.AlloyBlastRecipeProducer;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.unification.material.GTEMaterials;

@Mixin(value = AlloyBlastRecipeProducer.class, remap = false)
public abstract class AlloyBlastRecipeProducerMixin {

    @Inject(
            method = "buildRecipes",
            at = @At(
                     value = "INVOKE",
                     target = "Lgregtech/api/unification/material/properties/BlastProperty;getGasTier()Lgregtech/api/unification/material/properties/BlastProperty$GasTier;"),
            cancellable = true)
    private void buildRecipesMixin(@NotNull BlastProperty property, @NotNull Fluid molten, int outputAmount,
                                   int componentAmount, @NotNull RecipeBuilder<BlastRecipeBuilder> builder,
                                   CallbackInfo ci) {
        int duration = builder.getDuration() * outputAmount * 3 / 4;

        if (property.getGasTier() != null) {
            RecipeBuilder<BlastRecipeBuilder> builderGas = builder.copy();
            FluidStack gas = CraftingComponent.EBF_GASES.get(property.getGasTier());
            builderGas.notConsumable(new IntCircuitIngredient(getGasCircuitNum(componentAmount)))
                    .fluidInputs(new FluidStack(gas, gas.amount * outputAmount))
                    .duration((int) (duration * 0.67))
                    .buildAndRegister();

            if (GTEValues.isModLoadedDEDA()) {
                RecipeBuilder<BlastRecipeBuilder> builderPyrotheum = builder.copy();
                builderPyrotheum.notConsumable(new IntCircuitIngredient(getPyrotheumCircuitNum(componentAmount)))
                        .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, ABFPyrotheumAmount))
                        .duration((int) (duration * 0.67 * ABFDurationMultiplier))
                        .buildAndRegister();
            }
        }
        // build the non-gas recipe
        builder.notConsumable(new IntCircuitIngredient(getCircuitNum(componentAmount)))
                .duration(duration)
                .buildAndRegister();
        ci.cancel();
    }

    /**
     * @param componentAmount the amount of different components in the material
     * @return the circuit number for the regular recipe
     */
    protected int getCircuitNum(int componentAmount) {
        return componentAmount;
    }

    /**
     * @param componentAmount the amount of different components in the material
     * @return the circuit number for the gas-boosted recipe
     */
    protected int getGasCircuitNum(int componentAmount) {
        return componentAmount + 10;
    }

    protected int getPyrotheumCircuitNum(int componentAmount) {
        return componentAmount + 11;
    }

    @Inject(method = "addFreezerRecipes", at = @At("HEAD"), cancellable = true)
    protected void addFreezerRecipesMixin(@NotNull Material material, @NotNull Fluid molten,
                                          @NotNull BlastProperty property, CallbackInfo ci) {
        int vacuumEUt = property.getVacuumEUtOverride() == -1 ? VA[MV] : property.getVacuumEUtOverride();
        int vacuumDuration = property.getVacuumDurationOverride() == -1 ? (int) material.getMass() * 3 :
                property.getVacuumDurationOverride();
        boolean highTemp = property.getBlastTemperature() >= 5000;

        // Standard recipes
        addMoldRecipe(material, molten, vacuumEUt, Math.max(1, vacuumDuration / 9), highTemp,
                null, nugget, MetaItems.SHAPE_MOLD_NUGGET.getStackForm(), (int) (L / 9));
        addMoldRecipe(material, molten, vacuumEUt, vacuumDuration, highTemp,
                null, ingot, MetaItems.SHAPE_MOLD_INGOT.getStackForm(), (int) L);
        addMoldRecipe(material, molten, vacuumEUt, vacuumDuration, highTemp,
                GENERATE_PLATE, plate, MetaItems.SHAPE_MOLD_PLATE.getStackForm(), (int) L);
        addMoldRecipe(material, molten, vacuumEUt, vacuumDuration, highTemp,
                GENERATE_SMALL_GEAR, gearSmall, MetaItems.SHAPE_MOLD_GEAR_SMALL.getStackForm(), (int) L);
        addMoldRecipe(material, molten, vacuumEUt, vacuumDuration * 4, highTemp,
                GENERATE_GEAR, gear, MetaItems.SHAPE_MOLD_GEAR.getStackForm(), (int) (L * 4));
        addMoldRecipe(material, molten, vacuumEUt, vacuumDuration * 4, highTemp,
                GENERATE_ROTOR, rotor, MetaItems.SHAPE_MOLD_ROTOR.getStackForm(), (int) (L * 4));

        // Molten -> Fluid conversion
        addFluidConversionRecipe(material, molten, vacuumEUt, vacuumDuration, highTemp);

        // Cryotheum recipes (DEDA only, high temp only)
        if (highTemp && GTEValues.isModLoadedDEDA()) {
            int cryotheumDuration = property.getVacuumDurationOverride() != -1 ?
                    property.getVacuumDurationOverride() : (int) (material.getMass() * 0.5);

            addCryotheumMoldRecipe(material, molten, vacuumEUt, Math.max(1, cryotheumDuration / 18),
                    null, nugget, MetaItems.SHAPE_MOLD_NUGGET.getStackForm(), (int) (L / 9));
            addCryotheumMoldRecipe(material, molten, vacuumEUt, cryotheumDuration / 2,
                    null, ingot, MetaItems.SHAPE_MOLD_INGOT.getStackForm(), (int) L);
            addCryotheumMoldRecipe(material, molten, vacuumEUt, cryotheumDuration / 2,
                    GENERATE_PLATE, plate, MetaItems.SHAPE_MOLD_PLATE.getStackForm(), (int) L);
            addCryotheumMoldRecipe(material, molten, vacuumEUt, cryotheumDuration / 2,
                    GENERATE_SMALL_GEAR, gearSmall, MetaItems.SHAPE_MOLD_GEAR_SMALL.getStackForm(), (int) L);
            addCryotheumMoldRecipe(material, molten, vacuumEUt, cryotheumDuration * 2,
                    GENERATE_GEAR, gear, MetaItems.SHAPE_MOLD_GEAR.getStackForm(), (int) (L * 4));
            addCryotheumMoldRecipe(material, molten, vacuumEUt, cryotheumDuration / 2,
                    GENERATE_ROTOR, rotor, MetaItems.SHAPE_MOLD_ROTOR.getStackForm(), (int) (L * 4));

            // Hot Ingot -> Ingot
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .input(ingotHot, material, 1)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                    .output(ingot, material, 1)
                    .duration(cryotheumDuration / 2)
                    .EUt(vacuumEUt)
                    .buildAndRegister();

            // Molten -> Fluid with Cryotheum
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .fluidInputs(new FluidStack(molten, L))
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                    .fluidOutputs(material.getFluid(L))
                    .duration(cryotheumDuration / 2)
                    .EUt(vacuumEUt)
                    .buildAndRegister();
        }

        ci.cancel();
    }

    private void addMoldRecipe(@NotNull Material material, @NotNull Fluid molten,
                               int vacuumEUt, int vacuumDuration, boolean highTemp,
                               @Nullable MaterialFlag flag, @NotNull OrePrefix prefix,
                               @NotNull ItemStack mold, int fluidAmount) {
        if (flag != null && !material.hasFlag(flag)) return;

        RecipeBuilder<SimpleRecipeBuilder> builder = RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .notConsumable(mold)
                .fluidInputs(new FluidStack(molten, fluidAmount))
                .output(prefix, material, 1)
                .duration(vacuumDuration)
                .EUt(vacuumEUt);

        if (highTemp) {
            builder.fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, fluidAmount * 500 / L))
                    .fluidOutputs(Materials.Helium.getFluid(fluidAmount * 250 / L));
        }

        builder.buildAndRegister();
    }

    private void addFluidConversionRecipe(@NotNull Material material, @NotNull Fluid molten,
                                          int vacuumEUt, int vacuumDuration, boolean highTemp) {
        RecipeBuilder<SimpleRecipeBuilder> builder = RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(new FluidStack(molten, L))
                .fluidOutputs(material.getFluid(L))
                .duration(vacuumDuration)
                .EUt(vacuumEUt);

        if (highTemp) {
            builder.fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 500))
                    .fluidOutputs(Materials.Helium.getFluid(250));
        }

        builder.buildAndRegister();
    }

    private void addCryotheumMoldRecipe(@NotNull Material material, @NotNull Fluid molten,
                                        int vacuumEUt, int duration,
                                        @Nullable MaterialFlag flag, @NotNull OrePrefix prefix,
                                        @NotNull ItemStack mold, int fluidAmount) {
        if (flag != null && !material.hasFlag(flag)) return;

        RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                .notConsumable(mold)
                .fluidInputs(new FluidStack(molten, fluidAmount))
                .fluidInputs(GTEMaterials.Cryotheum.getFluid((int) (fluidAmount * 250 / L)))
                .fluidOutputs(
                        GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, (int) (fluidAmount * 50 / L)))
                .output(prefix, material, 1)
                .duration(duration)
                .EUt(vacuumEUt)
                .buildAndRegister();
    }
}
