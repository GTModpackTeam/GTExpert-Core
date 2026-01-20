package com.github.gtexpert.core.integration.deda.recipemaps;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.brandon3055.draconicevolution.api.fusioncrafting.IFusionRecipe;
import com.brandon3055.draconicevolution.api.fusioncrafting.SimpleFusionRecipe;
import com.brandon3055.draconicevolution.api.itemupgrade.FusionUpgradeRecipe;
import com.brandon3055.draconicevolution.api.itemupgrade.IUpgradableItem;
import com.brandon3055.draconicevolution.api.itemupgrade.UpgradeHelper;
import com.brandon3055.draconicevolution.items.ToolUpgrade;

import gregtech.api.capability.FeCompat;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.recipes.Recipe;

import com.github.gtexpert.core.api.util.GTELog;

import cofh.redstoneflux.api.IEnergyContainerItem;

/**
 * Utility methods shared between TierUp and Upgrade recipe maps.
 */
public final class DraconicRecipeUtils {

    private DraconicRecipeUtils() {}

    /**
     * Applies default upgrade tags to upgradable items.
     * This ensures consistent NBT state for recipe matching.
     */
    public static void applyDefaultUpgradeTag(List<ItemStack> inputs) {
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

    /**
     * Sets up the output item for a TierUp or Upgrade recipe.
     * Handles NBT copying and energy conversion.
     */
    @Nullable
    public static Recipe setupOutput(Recipe gtRecipe, List<ItemStack> inputs, IFusionRecipe fusionRecipe) {
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

    @NotNull
    public static ItemStack findCatalyst(List<ItemStack> inputs, IFusionRecipe fusionRecipe) {
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
    public static ItemStack getCatalyst(IFusionRecipe fusionRecipe) {
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
