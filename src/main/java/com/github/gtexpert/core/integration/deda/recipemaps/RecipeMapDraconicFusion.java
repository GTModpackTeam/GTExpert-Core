package com.github.gtexpert.core.integration.deda.recipemaps;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;

/**
 * RecipeMap for Draconic Fusion machines.
 * Holds Fusion recipes (via parent class) and delegates to TierUp and Upgrade RecipeMaps.
 * <p>
 * Search order: Fusion (own recipes) → TierUp → Upgrade
 */
public class RecipeMapDraconicFusion extends RecipeMap<SimpleRecipeBuilder> {

    private final RecipeMapDraconicTierUp tierUpRecipeMap;
    private final RecipeMapDraconicUpgrade upgradeRecipeMap;

    public RecipeMapDraconicFusion(@NotNull String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs,
                                   int maxFluidOutputs, @NotNull SimpleRecipeBuilder defaultRecipeBuilder,
                                   boolean isHidden,
                                   RecipeMapDraconicTierUp tierUpRecipeMap,
                                   RecipeMapDraconicUpgrade upgradeRecipeMap) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden);
        this.tierUpRecipeMap = tierUpRecipeMap;
        this.upgradeRecipeMap = upgradeRecipeMap;
    }

    @Nullable
    @Override
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
        // 1. Check own Fusion recipes
        Recipe fusionRecipe = super.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (fusionRecipe != null) {
            return fusionRecipe;
        }

        // 2. Check TierUp recipes
        Recipe tierUpRecipe = tierUpRecipeMap.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (tierUpRecipe != null) {
            return tierUpRecipe;
        }

        // 3. Check Upgrade recipes
        return upgradeRecipeMap.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
    }
}
