package gtexpert.api.recipes.draconicupgrade.upgrade;

import com.brandon3055.draconicevolution.DEFeatures;
import com.brandon3055.draconicevolution.api.itemupgrade.FusionUpgradeRecipe;
import com.brandon3055.draconicevolution.api.itemupgrade.IUpgradableItem;
import com.brandon3055.draconicevolution.api.itemupgrade.UpgradeHelper;
import com.brandon3055.draconicevolution.items.ToolUpgrade;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.ValidationResult;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class UpgradeRecipeBuilder extends RecipeBuilder<UpgradeRecipeBuilder> {

    private ItemStack catalyst;
    private String upgradeName;
    private int currentLevel = -1;

    public UpgradeRecipeBuilder() {}

    public UpgradeRecipeBuilder(UpgradeRecipeBuilder recipeBuilder) {
        super(recipeBuilder);
        this.catalyst = recipeBuilder.catalyst;
        this.upgradeName = recipeBuilder.upgradeName;
        this.currentLevel = recipeBuilder.currentLevel;
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
        if (!(value instanceof FusionUpgradeRecipe)) {
            throw new IllegalArgumentException("Property for draconic upgrade must be an instance of FusionUpgradeRecipe!");
        }
        this.applyProperty(UpgradeRecipeProperty.getInstance(), value);
        return true;
    }

    @Override
    protected EnumValidationResult validate() {
        super.validate();

        if (upgradeName == null) {
            GTLog.logger.error("Upgrade name has not been set", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        if (currentLevel < 0) {
            GTLog.logger.error("Level has not been set", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }

        if (recipeStatus == EnumValidationResult.INVALID) {
            return this.recipeStatus;
        }

        if (catalyst == null) {
            GTLog.logger.error("Catalyst has not been set", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        } else if (!(catalyst.getItem() instanceof IUpgradableItem)) {
            GTLog.logger.error("Catalyst is not an instance of IUpgradableItem", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        } else if (!((IUpgradableItem) catalyst.getItem()).getValidUpgrades(catalyst).contains(upgradeName)) {
            GTLog.logger.error("Upgrade " + upgradeName + " is not valid for this catalyst", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        } else if (((IUpgradableItem) catalyst.getItem()).getMaxUpgradeLevel(catalyst, upgradeName) < currentLevel + 1) {
            GTLog.logger.error("Max level of upgrade " + upgradeName + " is " + ((IUpgradableItem) catalyst.getItem()).getMaxUpgradeLevel(catalyst, upgradeName) + ", which supplied level is going to exceed", new IllegalArgumentException());
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
                fluidInputs, fluidOutputs, duration, EUt, hidden, isCTRecipe, recipePropertyStorage));
    }

    private void setFusionProperties() {
        final int upgradeLevel = currentLevel + 1;

        ItemStack input = catalyst.copy();
        ItemStack output = catalyst.copy();
        UpgradeHelper.setUpgradeLevel(input, upgradeName, currentLevel);
        UpgradeHelper.setUpgradeLevel(output, upgradeName, upgradeLevel);
        inputs.add(0, GTRecipeItemInput.getOrCreate(input)
                .setNBTMatchingCondition(
                        EQUAL_TO_RECURSIVE, NBTCondition.create(
                                NBTTagType.COMPOUND, UpgradeHelper.UPGRADE_TAG, NBTCondition.create(
                                        NBTTagType.BYTE, upgradeName, (byte) currentLevel))));
        outputs.add(output);

        ItemStack upgradeKey = new ItemStack(DEFeatures.toolUpgrade, 1, ToolUpgrade.NAME_TO_ID.get(upgradeName));
        setFusionRecipe(new FusionUpgradeRecipe(
                upgradeName,
                upgradeKey,
                0,
                currentLevel,
                upgradeLevel
        ));
        inputs.add(1, GTRecipeItemInput.getOrCreate(upgradeKey, upgradeKey.getCount()).setNonConsumable());
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

    public UpgradeRecipeBuilder setFusionRecipe(FusionUpgradeRecipe fusionRecipe) {
        applyProperty(UpgradeRecipeProperty.getInstance(), fusionRecipe);
        return this;
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

    public FusionUpgradeRecipe getFusionRecipe() {
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

    /**
     * Return true if tag has an entry where the value is equal to the condition's value.
     * If NBTTagCompound is found, evaluates recursively.
     */
    private static final NBTMatcher EQUAL_TO_RECURSIVE = new NBTMatcher() {
        @Override
        public boolean evaluate(@Nullable NBTTagCompound tag, @Nullable NBTCondition condition)  {
            if (condition == null || condition.tagType == null) {
                return false;
            }
            if (NBTMatcher.hasKey(tag, condition.nbtKey, condition.tagType.typeId)) {
                if (NBTTagType.isNumeric(condition.tagType)) {
                    return Objects.equals(tag.getLong(condition.nbtKey), ((Number) condition.value).longValue());
                }
                switch (condition.tagType) {
                    case BYTE_ARRAY:
                        return tag.getByteArray(condition.nbtKey).equals(condition.value);
                    case STRING:
                        return tag.getString(condition.nbtKey).equals(condition.value);
                    case LIST:
                    case LONG_ARRAY:
                        return false; // skip as we don't have AT
                    case COMPOUND:
                        if (condition.value instanceof NBTCondition) {
                            return evaluate(tag.getCompoundTag(condition.nbtKey), (NBTCondition) condition.value);
                        } else {
                            return tag.getCompoundTag(condition.nbtKey).equals(condition.value);
                        }
                    case INT_ARRAY:
                        return tag.getIntArray(condition.nbtKey).equals(condition.value);
                }
            }
            return false;
        }
    };
}
