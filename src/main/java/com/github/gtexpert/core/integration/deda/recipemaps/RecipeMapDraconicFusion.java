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
    private final @Nullable RecipeMapDraconicFusion lowerTierMap;

    public RecipeMapDraconicFusion(@NotNull String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs,
                                   int maxFluidOutputs, @NotNull SimpleRecipeBuilder defaultRecipeBuilder,
                                   boolean isHidden,
                                   RecipeMapDraconicTierUp tierUpRecipeMap,
                                   RecipeMapDraconicUpgrade upgradeRecipeMap) {
        this(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden,
                tierUpRecipeMap, upgradeRecipeMap, null);
    }

    public RecipeMapDraconicFusion(@NotNull String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs,
                                   int maxFluidOutputs, @NotNull SimpleRecipeBuilder defaultRecipeBuilder,
                                   boolean isHidden,
                                   RecipeMapDraconicTierUp tierUpRecipeMap,
                                   RecipeMapDraconicUpgrade upgradeRecipeMap,
                                   @Nullable RecipeMapDraconicFusion lowerTierMap) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden);
        this.tierUpRecipeMap = tierUpRecipeMap;
        this.upgradeRecipeMap = upgradeRecipeMap;
        this.lowerTierMap = lowerTierMap;
    }

    @Nullable
    @Override
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
        // 1. Check own Fusion recipes
        Recipe fusionRecipe = super.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (fusionRecipe != null) {
            return fusionRecipe;
        }

        // 2. Check lower-tier Fusion recipes
        if (lowerTierMap != null) {
            Recipe lowerTierRecipe = lowerTierMap.findOwnRecipe(voltage, inputs, fluidInputs, exactVoltage);
            if (lowerTierRecipe != null) {
                return lowerTierRecipe;
            }
        }

        // 3. Check TierUp recipes
        Recipe tierUpRecipe = tierUpRecipeMap.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (tierUpRecipe != null) {
            return tierUpRecipe;
        }

        // 4. Check Upgrade recipes
        return upgradeRecipeMap.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
    }

    /**
     * Searches only this map's own recipes, without delegating to TierUp/Upgrade/lowerTier.
     */
    @Nullable
    Recipe findOwnRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
        return super.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
    }
}
