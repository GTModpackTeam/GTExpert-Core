package com.github.gtexpert.core.integration.deda.recipemaps.tierup;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.brandon3055.draconicevolution.lib.ToolUpgradeRecipe;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.chance.output.ChancedOutputList;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;

import com.github.gtexpert.core.api.util.GTELog;
import com.github.gtexpert.core.integration.deda.DEDAConstants.DraconicTier;

public class TierUpRecipeBuilder extends RecipeBuilder<TierUpRecipeBuilder> {

    private GTRecipeInput catalyst;
    private ItemStack result;
    private boolean requiresAwakenedCrafter;

    public TierUpRecipeBuilder() {}

    public TierUpRecipeBuilder(TierUpRecipeBuilder recipeBuilder) {
        super(recipeBuilder);
        this.requiresAwakenedCrafter = recipeBuilder.requiresAwakenedCrafter;
    }

    @Override
    public TierUpRecipeBuilder copy() {
        return new TierUpRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(@NotNull String key, @Nullable Object value) {
        if (!key.equals(TierUpRecipeProperty.KEY)) {
            return super.applyProperty(key, value);
        }
        if (!(value instanceof TierUpRecipeInfo)) {
            GTELog.logger.error("Property for draconic upgrade must be an instance of TierUpRecipeInfo!",
                    new Throwable());
        }
        this.applyProperty(TierUpRecipeProperty.getInstance(), value);
        return true;
    }

    @Override
    protected EnumValidationResult validate() {
        super.validate();

        if (catalyst == null) {
            GTELog.logger.error("Catalyst has not been set", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (result == null) {
            GTELog.logger.error("Result has not been set", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (!outputs.isEmpty()) {
            GTELog.logger.error("Do not manually add item output", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }

        return this.recipeStatus;
    }

    @Override
    public ValidationResult<Recipe> build() {
        EnumValidationResult validationResult = finalizeAndValidate();
        if (validationResult != EnumValidationResult.INVALID) {
            setFusionProperties();
        }

        return ValidationResult.newResult(validationResult,
                new Recipe(inputs, outputs, new ChancedOutputList<>(this.chancedOutputLogic, chancedOutputs),
                        fluidInputs, fluidOutputs,
                        new ChancedOutputList<>(this.chancedFluidOutputLogic, chancedFluidOutputs), duration, EUt,
                        hidden, isCTRecipe, recipePropertyStorage, category));
    }

    private void setFusionProperties() {
        ToolUpgradeRecipe toolRecipe = new ToolUpgradeRecipe(
                result,
                catalyst.getInputStacks()[0],
                0,
                0,
                new ItemStack(Blocks.DIRT) // fake item to avoid crash
        );
        setFusionRecipe(new TierUpRecipeInfo(toolRecipe, requiresAwakenedCrafter));
        inputs.add(0, catalyst);
        outputs.add(result);
    }

    public TierUpRecipeBuilder catalyst(GTRecipeInput catalyst) {
        this.catalyst = catalyst;
        return this;
    }

    public TierUpRecipeBuilder catalyst(ItemStack itemStack, NBTMatcher any, NBTCondition nbtCondition) {
        return catalyst(new GTRecipeItemInput(itemStack).setNBTMatchingCondition(any, nbtCondition));
    }

    public TierUpRecipeBuilder catalyst(ItemStack catalyst) {
        return catalyst(new GTRecipeItemInput(catalyst));
    }

    public TierUpRecipeBuilder result(ItemStack result) {
        this.result = result;
        return this;
    }

    public TierUpRecipeBuilder tier(DraconicTier tier) {
        this.requiresAwakenedCrafter = tier.requiresAwakenedCrafter();
        return this;
    }

    public void setFusionRecipe(TierUpRecipeInfo recipeInfo) {
        applyProperty(TierUpRecipeProperty.getInstance(), recipeInfo);
    }

    public GTRecipeInput getCatalyst() {
        return catalyst;
    }

    public ItemStack getResult() {
        return result;
    }

    public TierUpRecipeInfo getFusionRecipe() {
        return this.recipePropertyStorage == null ? null :
                this.recipePropertyStorage.getRecipePropertyValue(TierUpRecipeProperty.getInstance(), null);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(TierUpRecipeProperty.getInstance().getKey(), getFusionRecipe())
                .toString();
    }
}
