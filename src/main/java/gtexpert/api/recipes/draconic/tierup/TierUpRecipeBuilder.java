package gtexpert.api.recipes.draconic.tierup;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;

import gtexpert.api.util.GTELog;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.brandon3055.draconicevolution.lib.ToolUpgradeRecipe;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TierUpRecipeBuilder extends RecipeBuilder<TierUpRecipeBuilder> {

    protected GTRecipeCategory recipeCategory;
    private GTRecipeInput catalyst;
    private ItemStack result;

    public TierUpRecipeBuilder() {}

    public TierUpRecipeBuilder(TierUpRecipeBuilder recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public TierUpRecipeBuilder copy() {
        return new TierUpRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(@Nonnull String key, @Nullable Object value) {
        if (!key.equals(TierUpRecipeProperty.KEY)) {
            return super.applyProperty(key, value);
        }
        if (!(value instanceof ToolUpgradeRecipe)) {
            throw new IllegalArgumentException(
                    "Property for draconic upgrade must be an instance of ToolUpgradeRecipe!");
        }
        this.applyProperty(TierUpRecipeProperty.getInstance(), value);
        return true;
    }

    @Override
    protected EnumValidationResult validate() {
        super.validate();

        if (catalyst == null) {
            GTELog.logger.error("Catalyst has not been set", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (result == null) {
            GTELog.logger.error("Result has not been set", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (!outputs.isEmpty()) {
            GTELog.logger.error("Do not manually add item output", new IllegalArgumentException());
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

        return ValidationResult.newResult(validationResult, new Recipe(inputs, outputs, chancedOutputs,
                fluidInputs, fluidOutputs, duration, EUt, hidden, isCTRecipe, recipePropertyStorage, recipeCategory));
    }

    private void setFusionProperties() {
        setFusionRecipe(new ToolUpgradeRecipe(
                result,
                catalyst.getInputStacks()[0],
                0,
                0,
                new ItemStack(Blocks.DIRT) // fake item to avoid crash
        ));
        inputs.add(0, catalyst);
        outputs.add(result);
    }

    public TierUpRecipeBuilder catalyst(GTRecipeInput catalyst) {
        this.catalyst = catalyst;
        return this;
    }

    public TierUpRecipeBuilder catalyst(ItemStack itemStack, NBTMatcher any, NBTCondition nbtCondition) {
        return catalyst(GTRecipeItemInput.getOrCreate(itemStack).setNBTMatchingCondition(any, nbtCondition));
    }

    public TierUpRecipeBuilder catalyst(ItemStack catalyst) {
        return catalyst(GTRecipeItemInput.getOrCreate(catalyst));
    }

    public TierUpRecipeBuilder result(ItemStack result) {
        this.result = result;
        return this;
    }

    public void setFusionRecipe(ToolUpgradeRecipe fusionRecipe) {
        applyProperty(TierUpRecipeProperty.getInstance(), fusionRecipe);
    }

    public GTRecipeInput getCatalyst() {
        return catalyst;
    }

    public ItemStack getResult() {
        return result;
    }

    public ToolUpgradeRecipe getFusionRecipe() {
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
