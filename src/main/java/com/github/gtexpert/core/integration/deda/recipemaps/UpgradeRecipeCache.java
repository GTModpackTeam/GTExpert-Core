package com.github.gtexpert.core.integration.deda.recipemaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Nullable;

import com.brandon3055.draconicevolution.api.itemupgrade.IUpgradableItem;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.GTRecipeInput;

import com.github.gtexpert.core.api.util.GTELog;
import com.github.gtexpert.core.integration.deda.DEDAConfigHolder;

/**
 * Cache for upgrade recipe lookups to improve performance.
 * <p>
 * The standard RecipeMap tree indexing doesn't work well for upgrade recipes
 * because catalyst items with different NBT states create separate branches.
 * This cache indexes recipes by catalyst item type (ignoring NBT) to allow
 * O(m) lookup where m is the number of recipes for that specific item,
 * instead of O(n) for all upgrade recipes.
 * <p>
 * The cache is built lazily on first recipe lookup after all recipes are registered.
 */
public class UpgradeRecipeCache {

    /**
     * Index: ItemStackKey (item + meta, ignoring NBT) -> List of applicable upgrade recipes
     */
    private final Map<ItemStackKey, List<Recipe>> catalystIndex = new HashMap<>();

    private boolean initialized = false;
    private int totalRecipes = 0;
    private int indexedItems = 0;

    /**
     * Build the cache from upgrade recipe map.
     * Called once after all recipes are registered (lazy initialization).
     *
     * @param upgradeRecipeMap the recipe map containing upgrade recipes
     */
    public void buildCache(RecipeMap<?> upgradeRecipeMap) {
        if (initialized) return;

        long startTime = System.currentTimeMillis();

        for (Recipe recipe : upgradeRecipeMap.getRecipeList()) {
            // Get the catalyst input (first input slot by convention in upgrade recipes)
            if (recipe.getInputs().isEmpty()) continue;

            GTRecipeInput catalystInput = recipe.getInputs().get(0);
            for (ItemStack stack : catalystInput.getInputStacks()) {
                // Only index upgradable items
                if (!(stack.getItem() instanceof IUpgradableItem)) continue;

                ItemStackKey key = new ItemStackKey(stack.getItem(), stack.getMetadata());
                catalystIndex.computeIfAbsent(key, k -> new ArrayList<>()).add(recipe);
            }
            totalRecipes++;
        }

        indexedItems = catalystIndex.size();
        initialized = true;

        long elapsed = System.currentTimeMillis() - startTime;
        if (DEDAConfigHolder.debugRecipeLookup) {
            GTELog.logger.info("UpgradeRecipeCache built: {} recipes indexed across {} item types in {}ms",
                    totalRecipes, indexedItems, elapsed);
        }
    }

    /**
     * Find matching upgrade recipe for given inputs.
     * O(m) where m is the number of recipes for this specific catalyst item,
     * instead of O(n) for all upgrade recipes.
     *
     * @param voltage     maximum voltage
     * @param inputs      item inputs
     * @param fluidInputs fluid inputs
     * @return matching recipe or null if not found
     */
    @Nullable
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
        for (ItemStack input : inputs) {
            if (!(input.getItem() instanceof IUpgradableItem)) continue;

            ItemStackKey key = new ItemStackKey(input.getItem(), input.getMetadata());
            List<Recipe> candidates = catalystIndex.get(key);

            if (candidates == null) continue;

            if (DEDAConfigHolder.debugRecipeLookup) {
                GTELog.logger.debug("Checking {} candidate recipes for {}", candidates.size(), key);
            }

            for (Recipe recipe : candidates) {
                if (recipe.getEUt() <= voltage && recipe.matches(false, inputs, fluidInputs)) {
                    return recipe;
                }
            }
        }
        return null;
    }

    /**
     * Check if the cache has been initialized.
     *
     * @return true if cache is ready
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Get the total number of indexed recipes.
     *
     * @return recipe count
     */
    public int getTotalRecipes() {
        return totalRecipes;
    }

    /**
     * Get the number of unique item types indexed.
     *
     * @return item type count
     */
    public int getIndexedItems() {
        return indexedItems;
    }

    /**
     * Clear the cache. Useful for testing or if recipes need to be reloaded.
     */
    public void clear() {
        catalystIndex.clear();
        initialized = false;
        totalRecipes = 0;
        indexedItems = 0;
    }

    /**
     * Key for indexing items by type and metadata, ignoring NBT.
     * This allows grouping all upgrade recipes for the same item type together.
     */
    private static final class ItemStackKey {

        private final Item item;
        private final int meta;

        ItemStackKey(Item item, int meta) {
            this.item = item;
            this.meta = meta;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ItemStackKey that = (ItemStackKey) o;
            return meta == that.meta && Objects.equals(item, that.item);
        }

        @Override
        public int hashCode() {
            return Objects.hash(item, meta);
        }

        @Override
        public String toString() {
            return item.getRegistryName() + "@" + meta;
        }
    }
}
