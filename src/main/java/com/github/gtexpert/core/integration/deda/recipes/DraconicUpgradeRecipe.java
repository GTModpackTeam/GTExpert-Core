package com.github.gtexpert.core.integration.deda.recipes;

import static gregtech.api.GTValues.VA;

import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.brandon3055.draconicevolution.DEFeatures;
import com.brandon3055.draconicevolution.api.itemupgrade.IUpgradableItem;
import com.brandon3055.draconicevolution.items.ToolUpgrade;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.api.util.GTELog;
import com.github.gtexpert.core.integration.deda.DEDAConstants.DraconicTier;
import com.github.gtexpert.core.integration.deda.recipemaps.GTEDraconicRecipeMaps;
import com.github.gtexpert.core.integration.deda.recipemaps.upgrade.UpgradeRecipeBuilder;

public class DraconicUpgradeRecipe {

    public static void init() {
        ItemStack[] upgradableItems = new ItemStack[] {
                new ItemStack(DEFeatures.draconiumCapacitor),
                new ItemStack(DEFeatures.draconiumCapacitor, 1, 1),
                new ItemStack(DEFeatures.wyvernAxe),
                new ItemStack(DEFeatures.wyvernPick),
                new ItemStack(DEFeatures.wyvernShovel),
                new ItemStack(DEFeatures.wyvernSword),
                new ItemStack(DEFeatures.wyvernBow),
                new ItemStack(DEFeatures.wyvernHelm),
                new ItemStack(DEFeatures.wyvernChest),
                new ItemStack(DEFeatures.wyvernLegs),
                new ItemStack(DEFeatures.wyvernBoots),
                new ItemStack(DEFeatures.draconicAxe),
                new ItemStack(DEFeatures.draconicPick),
                new ItemStack(DEFeatures.draconicShovel),
                new ItemStack(DEFeatures.draconicSword),
                new ItemStack(DEFeatures.draconicBow),
                new ItemStack(DEFeatures.draconicStaffOfPower),
                new ItemStack(DEFeatures.draconicHelm),
                new ItemStack(DEFeatures.draconicChest),
                new ItemStack(DEFeatures.draconicLegs),
                new ItemStack(DEFeatures.draconicBoots),
                new ItemStack(DAFeatures.chaoticStaffOfPower),
                new ItemStack(DAFeatures.chaoticBow),
                new ItemStack(DAFeatures.chaoticHelm),
                new ItemStack(DAFeatures.chaoticChest),
                new ItemStack(DAFeatures.chaoticLegs),
                new ItemStack(DAFeatures.chaoticBoots),
        };

        for (ItemStack stack : upgradableItems) {
            if (!(stack.getItem() instanceof IUpgradableItem item)) {
                GTELog.logger.error("Item {} is not an instance of IUpgradableItem!", stack);
                continue;
            }
            for (String upgradeName : ToolUpgrade.NAME_TO_ID.keySet()) {
                int currentLevel = 0;
                while (currentLevel < ToolUpgrade.NAME_MAX_LEVEL.get(upgradeName)) {
                    if (item.getValidUpgrades(stack).contains(upgradeName) &&
                            item.getMaxUpgradeLevel(stack, upgradeName) >= currentLevel + 1) {
                        UpgradeRecipeBuilder recipeBuilder = GTEDraconicRecipeMaps.DRACONIC_FUSION_UPGRADE_RECIPES
                                .recipeBuilder();
                        recipeBuilder.catalyst(stack).upgradeName(upgradeName).level(currentLevel);

                        switch (currentLevel) {
                            case 0 -> recipeBuilder
                                    .tier(DraconicTier.WYVERN)
                                    .input(Items.GOLDEN_APPLE, 2)
                                    .input(OrePrefix.gem, Materials.Diamond, 2)
                                    .input(OrePrefix.gem, Materials.EnderEye, 2)
                                    .input(DEFeatures.wyvernCore)
                                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(8000))
                                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 2000))
                                    .duration(100).EUt(VA[GTEValues.dedaVoltageTier - 1]);
                            case 1 -> recipeBuilder
                                    .tier(DraconicTier.WYVERN)
                                    .input(OrePrefix.gem, Materials.NetherStar, 2)
                                    .input(DEFeatures.wyvernCore, 2)
                                    .input(OrePrefix.gem, Materials.Emerald, 2)
                                    .input(DEFeatures.draconicCore)
                                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                                    .duration(200).EUt(VA[GTEValues.dedaVoltageTier]);
                            case 2 -> recipeBuilder
                                    .tier(DraconicTier.DRACONIC)
                                    .input(OrePrefix.gem, Materials.NetherStar, 2)
                                    .input(DEFeatures.draconicCore, 2)
                                    .input(OrePrefix.block, Materials.Emerald, 2)
                                    .input(DEFeatures.awakenedCore)
                                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(32000))
                                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                                    .duration(400).EUt(VA[GTEValues.dedaVoltageTier + 1]);
                            case 3 -> recipeBuilder
                                    .tier(DraconicTier.CHAOTIC)
                                    .input(DEFeatures.draconicCore, 2)
                                    .input(DEFeatures.awakenedCore, 2)
                                    .input(Blocks.DRAGON_EGG, 2)
                                    .input(DEFeatures.chaoticCore)
                                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(48000))
                                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 12000))
                                    .duration(600).EUt(VA[GTEValues.dedaVoltageTier + 2]);
                        }
                        recipeBuilder.buildAndRegister();
                    }
                    currentLevel++;
                }
            }
        }
    }
}
