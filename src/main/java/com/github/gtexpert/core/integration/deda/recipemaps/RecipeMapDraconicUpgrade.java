package com.github.gtexpert.core.integration.deda.recipemaps;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;

import com.github.gtexpert.core.integration.deda.DEDAConfigHolder;
import com.github.gtexpert.core.integration.deda.recipemaps.upgrade.UpgradeRecipeBuilder;
import com.github.gtexpert.core.integration.deda.recipemaps.upgrade.UpgradeRecipeInfo;
import com.github.gtexpert.core.integration.deda.recipemaps.upgrade.UpgradeRecipeProperty;

/**
 * Recipe map for Upgrade recipes (item stat upgrades like Attack Damage, Speed, etc.).
 * Handles special output processing and caching for Upgrade recipes.
 */
public class RecipeMapDraconicUpgrade extends RecipeMap<UpgradeRecipeBuilder> {

    /** Cache for upgrade recipe lookups - improves O(n) to O(m) where m << n */
    private final UpgradeRecipeCache upgradeCache = new UpgradeRecipeCache();

    public RecipeMapDraconicUpgrade(@NotNull String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs,
                                    int maxFluidOutputs, @NotNull UpgradeRecipeBuilder defaultRecipeBuilder,
                                    boolean isHidden) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden);
    }

    @Nullable
    @Override
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
        DraconicRecipeUtils.applyDefaultUpgradeTag(inputs);

        // Use cached lookup for upgrade recipes if enabled
        // This improves performance from O(n) to O(m) where m is the number of recipes
        // for the specific catalyst item type, instead of all upgrade recipes
        Recipe recipe = findUpgradeRecipe(voltage, inputs, fluidInputs);
        if (recipe == null) {
            return null;
        }

        UpgradeRecipeInfo upgradeInfo = recipe.getProperty(UpgradeRecipeProperty.getInstance(), null);
        return DraconicRecipeUtils.setupOutput(recipe, inputs, upgradeInfo != null ? upgradeInfo.recipe() : null);
    }

    /**
     * Find upgrade recipe using cache if enabled, otherwise fall back to linear search.
     */
    @Nullable
    private Recipe findUpgradeRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
        if (DEDAConfigHolder.enableUpgradeRecipeCache) {
            // Ensure cache is built (lazy initialization)
            if (!upgradeCache.isInitialized()) {
                upgradeCache.buildCache(this);
            }
            return upgradeCache.findRecipe(voltage, inputs, fluidInputs);
        } else {
            // Fallback to original O(n) linear search
            return findUpgradeRecipeLinear(voltage, inputs, fluidInputs);
        }
    }

    /**
     * Linear search for upgrade recipes.
     * O(n) complexity where n is the total number of upgrade recipes.
     * <p>
     * We need to manually search RecipeMap here because:
     * RecipeMap#recurseIngredientTreeFindRecipe only searches branch first found.
     * When recipes get added, catalyst objects with different NBT states create separate branches.
     * But when searching, an itemstack can match to multiple branches.
     */
    @Nullable
    private Recipe findUpgradeRecipeLinear(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
        for (Recipe recipe : getRecipeList()) {
            if (recipe.getEUt() <= voltage && recipe.matches(false, inputs, fluidInputs)) {
                return recipe;
            }
        }
        return null;
    }
}
