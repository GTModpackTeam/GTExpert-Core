package com.github.gtexpert.core.mixins.gcym;

import static com.github.gtexpert.core.integration.deda.recipes.DraconicMaterialsRecipe.ABFDurationMultiplier;
import static com.github.gtexpert.core.integration.deda.recipes.DraconicMaterialsRecipe.ABFPyrotheumAmount;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.loaders.recipe.CraftingComponent;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregicality.multiblocks.api.recipes.alloyblast.AlloyBlastRecipeProducer;

import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.api.util.Mods;

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

            if (Mods.DraconicEvolution.isModLoaded()) {
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
}
