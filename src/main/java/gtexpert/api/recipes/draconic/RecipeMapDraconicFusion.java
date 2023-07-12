package gtexpert.api.recipes.draconic;

import gregtech.api.capability.FeCompat;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;

import gtexpert.api.recipes.draconic.tierup.TierUpRecipeBuilder;
import gtexpert.api.recipes.draconic.tierup.TierUpRecipeProperty;
import gtexpert.api.recipes.draconic.upgrade.UpgradeRecipeBuilder;
import gtexpert.api.recipes.draconic.upgrade.UpgradeRecipeProperty;
import gtexpert.api.util.GTELog;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;

import cofh.redstoneflux.api.IEnergyContainerItem;
import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.brandon3055.draconicevolution.api.fusioncrafting.IFusionRecipe;
import com.brandon3055.draconicevolution.api.fusioncrafting.SimpleFusionRecipe;
import com.brandon3055.draconicevolution.api.itemupgrade.FusionUpgradeRecipe;
import com.brandon3055.draconicevolution.api.itemupgrade.IUpgradableItem;
import com.brandon3055.draconicevolution.api.itemupgrade.UpgradeHelper;
import com.brandon3055.draconicevolution.items.ToolUpgrade;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RecipeMapDraconicFusion extends RecipeMap<SimpleRecipeBuilder> {

    private final RecipeMap<TierUpRecipeBuilder> tierUpRecipeMap;
    private final RecipeMap<UpgradeRecipeBuilder> upgradeRecipeMap;

    public RecipeMapDraconicFusion(@Nonnull String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs,
                                   int maxFluidOutputs, @Nonnull SimpleRecipeBuilder defaultRecipeBuilder,
                                   boolean isHidden, RecipeMap<TierUpRecipeBuilder> tierUpRecipeMap,
                                   RecipeMap<UpgradeRecipeBuilder> upgradeRecipeMap) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden);
        this.tierUpRecipeMap = tierUpRecipeMap;
        this.upgradeRecipeMap = upgradeRecipeMap;
    }

    @Nullable
    @Override
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
        Recipe superRecipe = super.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (superRecipe != null) {
            return superRecipe;
        }

        applyDefaultUpgradeTag(inputs);

        Recipe tierUpRecipe = tierUpRecipeMap.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (tierUpRecipe != null) {
            return setupOutput(tierUpRecipe, inputs,
                    tierUpRecipe.getProperty(TierUpRecipeProperty.getInstance(), null));
        }

        // We need to manually search RecipeMap here.
        //
        // RecipeMap#recurseIngredientTreeFindRecipe only searches branch first found (`Either<Recipe, Branch> result =
        // targetMap.get(obj);`).
        // This is fine in most of the situations, but here it's not;
        //
        // When recipes get added, many of the catalyst objects are not equal each other, as they don't have level-0 tag
        // and UpgradeRecipeBuilder#EQUAL_TO_RECURSIVE returns false, hence all the recipes are added as separate nodes.
        // example: #lookup -> [draconic_helm -> [tool_upgrade@9], draconic_helm -> [tool_upgrade@8], ...], instead of
        // [draconic_helm -> [tool_upgrade@9, tool_upgrade@8]]
        // But when searching recipe, an itemstack can match (`equals`) to multiple branches.
        // example: when draconic_helm with no upgrade is passed as input, it can match to all branches accepts basic
        // upgrade
        for (Recipe recipe : upgradeRecipeMap.getRecipeList()) {
            if (recipe.getEUt() <= voltage && recipe.matches(false, inputs, fluidInputs)) {
                return setupOutput(recipe, inputs, recipe.getProperty(UpgradeRecipeProperty.getInstance(), null));
            }
        }

        return null;
    }

    private void applyDefaultUpgradeTag(List<ItemStack> inputs) {
        for (ItemStack input : inputs) {
            if (!(input.getItem() instanceof IUpgradableItem item)) continue;
            for (String upgradeName : ToolUpgrade.NAME_TO_ID.keySet()) {
                if (!item.getValidUpgrades(input).contains(upgradeName)) continue;
                NBTTagCompound upgradeTag = input.getOrCreateSubCompound(UpgradeHelper.UPGRADE_TAG);
                if (upgradeTag.hasKey(upgradeName, Constants.NBT.TAG_BYTE)) continue;
                upgradeTag.setByte(upgradeName, (byte) 0);
            }
        }
    }

    private Recipe setupOutput(Recipe gtRecipe, List<ItemStack> inputs, IFusionRecipe fusionRecipe) {
        if (fusionRecipe == null) {
            GTELog.logger.warn("Recipe found, but property not found");
            GTELog.logger.warn("Recipe: " + gtRecipe);
            return null;
        }
        ItemStack catalyst = findCatalyst(inputs, fusionRecipe);
        if (catalyst.isEmpty()) {
            GTELog.logger.warn("Recipe found, but actual catalyst not found in the GT recipe");
            GTELog.logger.warn("Recipe: " + gtRecipe);
            GTELog.logger.warn("Expected catalyst: " + fusionRecipe.getRecipeCatalyst());
            return null;
        }

        ItemStack outputStack = fusionRecipe.getRecipeOutput(catalyst);

        // convert GTEU to FE
        IElectricItem inputElectricItem = catalyst.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (inputElectricItem != null) {
            long euCharge = inputElectricItem.getCharge();
            int feCharge = (int) Math.min(euCharge * FeCompat.ratio(false), Integer.MAX_VALUE);
            if (outputStack.getItem() instanceof IEnergyContainerItem outputEnergyItem) {
                ItemNBTHelper.setInteger(outputStack, "Energy",
                        Math.min(feCharge, outputEnergyItem.getMaxEnergyStored(outputStack)));
            }
        }

        Recipe retRecipe = gtRecipe.copy();
        retRecipe.getOutputs().clear(); // This assumes there's only 1 output
        retRecipe.getOutputs().add(outputStack);
        return retRecipe;
    }

    @Nonnull
    private ItemStack findCatalyst(List<ItemStack> inputs, IFusionRecipe fusionRecipe) {
        ItemStack expectedCatalyst = getCatalyst(fusionRecipe);
        if (expectedCatalyst == null || expectedCatalyst.isEmpty()) {
            return ItemStack.EMPTY;
        }
        for (ItemStack input : inputs) {
            if (expectedCatalyst.getItem() == input.getItem() &&
                    expectedCatalyst.getItemDamage() == input.getItemDamage() && fusionRecipe.isRecipeCatalyst(input)) {
                return input;
            }
        }
        return ItemStack.EMPTY;
    }

    @Nullable
    private static ItemStack getCatalyst(IFusionRecipe fusionRecipe) {
        if (fusionRecipe instanceof SimpleFusionRecipe) {
            return fusionRecipe.getRecipeCatalyst();
        } else if (fusionRecipe instanceof FusionUpgradeRecipe) {
            List<Object> ingredients = ((FusionUpgradeRecipe) fusionRecipe).getRecipeIngredients();
            if (ingredients.isEmpty() || !(ingredients.get(0) instanceof ItemStack)) {
                GTELog.logger.warn("Unknown ingredient: " + (ingredients.isEmpty() ? "empty" : ingredients.get(0)));
                GTELog.logger.warn("Recipe: " + fusionRecipe);
                return null;
            }
            return (ItemStack) ingredients.get(0);
        } else {
            throw new RuntimeException("Unknown type of IFusionRecipe: " + fusionRecipe.getClass().getName());
        }
    }
}
