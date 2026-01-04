package com.github.gtexpert.core.integration.deda.recipemaps.upgrade;

import net.minecraft.item.ItemStack;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.brandon3055.draconicevolution.DEFeatures;
import com.brandon3055.draconicevolution.api.itemupgrade.FusionUpgradeRecipe;
import com.brandon3055.draconicevolution.api.itemupgrade.IUpgradableItem;
import com.brandon3055.draconicevolution.api.itemupgrade.UpgradeHelper;
import com.brandon3055.draconicevolution.items.ToolUpgrade;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.chance.output.ChancedOutputList;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;

import com.github.gtexpert.core.api.util.GTELog;
import com.github.gtexpert.core.integration.deda.DEDAConstants.DraconicTier;

public class UpgradeRecipeBuilder extends RecipeBuilder<UpgradeRecipeBuilder> {

    private ItemStack catalyst;
    private String upgradeName;
    private int currentLevel = -1;
    private boolean requiresAwakenedCrafter;

    public UpgradeRecipeBuilder() {}

    public UpgradeRecipeBuilder(UpgradeRecipeBuilder recipeBuilder) {
        super(recipeBuilder);
        this.catalyst = recipeBuilder.catalyst;
        this.upgradeName = recipeBuilder.upgradeName;
        this.currentLevel = recipeBuilder.currentLevel;
        this.requiresAwakenedCrafter = recipeBuilder.requiresAwakenedCrafter;
    }

    @Override
    public UpgradeRecipeBuilder copy() {
        return new UpgradeRecipeBuilder(this);
    }

    @Override
    public boolean applyProperty(@NotNull String key, @Nullable Object value) {
        if (!key.equals(UpgradeRecipeProperty.KEY)) {
            return super.applyProperty(key, value);
        }
        if (!(value instanceof UpgradeRecipeInfo)) {
            GTELog.logger.error("Property for draconic upgrade must be an instance of UpgradeRecipeInfo!",
                    new Throwable());
        }
        this.applyProperty(UpgradeRecipeProperty.getInstance(), value);
        return true;
    }

    @Override
    protected EnumValidationResult validate() {
        super.validate();

        if (upgradeName == null) {
            GTELog.logger.error("Upgrade name has not been set", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (currentLevel < 0) {
            GTELog.logger.error("Level has not been set", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }

        if (recipeStatus == EnumValidationResult.INVALID) {
            return this.recipeStatus;
        }

        if (catalyst == null) {
            GTELog.logger.error("Catalyst has not been set", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        } else if (!(catalyst.getItem() instanceof IUpgradableItem)) {
            GTELog.logger.error("Catalyst is not an instance of IUpgradableItem", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        } else if (!((IUpgradableItem) catalyst.getItem()).getValidUpgrades(catalyst).contains(upgradeName)) {
            GTELog.logger.error("Upgrade " + upgradeName + " is not valid for this catalyst",
                    new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        } else
            if (((IUpgradableItem) catalyst.getItem()).getMaxUpgradeLevel(catalyst, upgradeName) < currentLevel + 1) {
                GTELog.logger.error("Max level of upgrade " + upgradeName + " is " +
                        ((IUpgradableItem) catalyst.getItem()).getMaxUpgradeLevel(catalyst, upgradeName) +
                        ", which supplied level is going to exceed", new Throwable());
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
        final int upgradeLevel = currentLevel + 1;

        ItemStack input = catalyst.copy();
        ItemStack output = catalyst.copy();
        UpgradeHelper.setUpgradeLevel(input, upgradeName, currentLevel);
        UpgradeHelper.setUpgradeLevel(output, upgradeName, upgradeLevel);
        inputs.add(0, new GTRecipeItemInput(input).setNBTMatchingCondition(
                NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                        NBTTagType.COMPOUND, UpgradeHelper.UPGRADE_TAG, NBTCondition.create(
                                NBTTagType.BYTE, upgradeName, (byte) currentLevel))));
        outputs.add(output);

        ItemStack upgradeKey = new ItemStack(DEFeatures.toolUpgrade, 1, ToolUpgrade.NAME_TO_ID.get(upgradeName));
        FusionUpgradeRecipe fusionRecipe = new FusionUpgradeRecipe(
                upgradeName,
                upgradeKey,
                0,
                currentLevel,
                upgradeLevel,
                input);
        setFusionRecipe(new UpgradeRecipeInfo(fusionRecipe, requiresAwakenedCrafter));
        inputs.add(1, new GTRecipeItemInput(upgradeKey, upgradeKey.getCount()).setNonConsumable());
    }

    public UpgradeRecipeBuilder catalyst(ItemStack catalyst) {
        this.catalyst = catalyst;
        return this;
    }

    public UpgradeRecipeBuilder upgradeName(String upgradeName) {
        this.upgradeName = upgradeName;
        return this;
    }

    public UpgradeRecipeBuilder level(int level) {
        this.currentLevel = level;
        return this;
    }

    public UpgradeRecipeBuilder tier(DraconicTier tier) {
        this.requiresAwakenedCrafter = tier.requiresAwakenedCrafter();
        return this;
    }

    public void setFusionRecipe(UpgradeRecipeInfo recipeInfo) {
        applyProperty(UpgradeRecipeProperty.getInstance(), recipeInfo);
    }

    public ItemStack getCatalyst() {
        return catalyst;
    }

    public String getUpgradeName() {
        return upgradeName;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public UpgradeRecipeInfo getFusionRecipe() {
        return this.recipePropertyStorage == null ? null :
                this.recipePropertyStorage.getRecipePropertyValue(UpgradeRecipeProperty.getInstance(), null);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(UpgradeRecipeProperty.getInstance().getKey(), getFusionRecipe())
                .toString();
    }
}
