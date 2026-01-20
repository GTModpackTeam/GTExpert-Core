package com.github.gtexpert.core.integration.deda.recipemaps;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;

import com.github.gtexpert.core.integration.deda.recipemaps.tierup.TierUpRecipeBuilder;
import com.github.gtexpert.core.integration.deda.recipemaps.tierup.TierUpRecipeInfo;
import com.github.gtexpert.core.integration.deda.recipemaps.tierup.TierUpRecipeProperty;

/**
 * Recipe map for TierUp recipes (item tier upgrades like Wyvern → Draconic → Awakened).
 * Handles special output processing for TierUp recipes.
 */
public class RecipeMapDraconicTierUp extends RecipeMap<TierUpRecipeBuilder> {

    public RecipeMapDraconicTierUp(@NotNull String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs,
                                   int maxFluidOutputs, @NotNull TierUpRecipeBuilder defaultRecipeBuilder,
                                   boolean isHidden) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden);
    }

    @Nullable
    @Override
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
        DraconicRecipeUtils.applyDefaultUpgradeTag(inputs);

        Recipe recipe = super.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (recipe == null) {
            return null;
        }

        TierUpRecipeInfo tierUpInfo = recipe.getProperty(TierUpRecipeProperty.getInstance(), null);
        return DraconicRecipeUtils.setupOutput(recipe, inputs, tierUpInfo != null ? tierUpInfo.recipe() : null);
    }
}
