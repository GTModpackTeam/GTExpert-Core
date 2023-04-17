package gtexpert.loaders.recipe;

import com.brandon3055.draconicevolution.DEFeatures;
import com.brandon3055.draconicevolution.api.itemupgrade.IUpgradableItem;
import com.brandon3055.draconicevolution.items.ToolUpgrade;
import crazypants.enderio.base.init.ModObject;
import gregtech.api.items.toolitem.ToolHelper;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.ToolItems;
import gtexpert.GTExpertMod;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.api.recipes.draconicupgrade.tierup.TierUpRecipeBuilder;
import gtexpert.api.recipes.draconicupgrade.upgrade.UpgradeRecipeBuilder;
import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.plate;
import static gregtech.common.items.MetaItems.*;
import static gtexpert.api.unification.material.GTEMaterials.*;

public class DraconicUpgradeRecipeLoader {

    public static void init() {
        tierUp();
        upgrade();
    }

    private static void tierUp() {
        // Axe of the Wyvern
        addTierUpRecipe(
                GTRecipeItemInput.getOrCreate(ToolItems.AXE.get(DRACONIUM)).setNBTMatchingCondition(
                    NBTMatcher.ANY, NBTCondition.create(
                            NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                    NBTTagType.STRING, "Material", "draconium"))), // TODO: Fix NBTMatcher(add NBTMatcher.CONTAINS)
                new ItemStack(DEFeatures.wyvernAxe),
                Tier.WYVERN,
                2);
        // Pickaxe of the Wyvern
        addTierUpRecipe(
                GTRecipeItemInput.getOrCreate(ToolItems.PICKAXE.get(DRACONIUM)).setNBTMatchingCondition(
                        NBTMatcher.ANY, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", "draconium"))), // TODO: Fix NBTMatcher(add NBTMatcher.CONTAINS)
                new ItemStack(DEFeatures.wyvernPick),
                Tier.WYVERN,
                2);
        // Shovel of the Wyvern
        addTierUpRecipe(
                GTRecipeItemInput.getOrCreate(ToolItems.SHOVEL.get(DRACONIUM)).setNBTMatchingCondition(
                        NBTMatcher.ANY, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", "draconium"))), // TODO: Fix NBTMatcher(add NBTMatcher.CONTAINS)
                new ItemStack(DEFeatures.wyvernShovel),
                Tier.WYVERN,
                2);
        // Sword of the Wyvern
        addTierUpRecipe(
                NANO_SABER.getStackForm(),
                new ItemStack(DEFeatures.wyvernSword),
                Tier.WYVERN,
                2);
        // Bow of the Wyvern
        addTierUpRecipe(
                new ItemStack(ModObject.itemDarkSteelBow.getItemNN()),
                new ItemStack(DEFeatures.wyvernBow),
                Tier.WYVERN,
                2);
        // Wyvern Helm
        addTierUpRecipe(
                QUANTUM_HELMET.getStackForm(),
                new ItemStack(DEFeatures.wyvernHelm),
                Tier.WYVERN,
                6);
        // Wyvern Chest
        addTierUpRecipe(
                QUANTUM_CHESTPLATE.getStackForm(),
                new ItemStack(DEFeatures.wyvernChest),
                Tier.WYVERN,
                6);
        // Wyvern Legs
        addTierUpRecipe(
                QUANTUM_LEGGINGS.getStackForm(),
                new ItemStack(DEFeatures.wyvernLegs),
                Tier.WYVERN,
                6);
        // Wyvern Boots
        addTierUpRecipe(
                QUANTUM_BOOTS.getStackForm(),
                new ItemStack(DEFeatures.wyvernBoots),
                Tier.WYVERN,
                6);

        // Draconic Axe
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernAxe),
                new ItemStack(DEFeatures.draconicAxe),
                Tier.DRACONIC
        );
        // Draconic Pickaxe
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernPick),
                new ItemStack(DEFeatures.draconicPick),
                Tier.DRACONIC
        );
        // Draconic Shovel
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernShovel),
                new ItemStack(DEFeatures.draconicShovel),
                Tier.DRACONIC
        );
        // Draconic Sword
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernSword),
                new ItemStack(DEFeatures.draconicSword),
                Tier.DRACONIC
        );
        // Draconic Bow
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernBow),
                new ItemStack(DEFeatures.draconicBow),
                Tier.DRACONIC
        );
        // Draconic Staff of Power
        GTERecipeMaps.DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.recipeBuilder()
                .catalyst(GTRecipeItemInput.getOrCreate(new ItemStack(DEFeatures.draconicPick)).setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY))
                .result(new ItemStack(DEFeatures.draconicStaffOfPower))
                .input(plate, DRACONIUM, 6)
                .inputNBT(DEFeatures.draconicAxe, NBTMatcher.ANY, NBTCondition.ANY)
                .inputNBT(DEFeatures.draconicShovel, NBTMatcher.ANY, NBTCondition.ANY)
                .inputNBT(DEFeatures.draconicSword, NBTMatcher.ANY, NBTCondition.ANY)
                .input(DEFeatures.draconicCore, 4)
                .fluidInputs(CRYOTHEUM.getFluid(32000))
                .fluidOutputs(PYROTHEUM.getFluid(8000))
                .duration(400).EUt(VA[ZPM])
                .buildAndRegister();
        // Draconic Helm
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernHelm),
                new ItemStack(DEFeatures.draconicHelm),
                Tier.DRACONIC
        );
        // Draconic Chest
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernChest),
                new ItemStack(DEFeatures.draconicChest),
                Tier.DRACONIC
        );
        // Draconic Legs
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernLegs),
                new ItemStack(DEFeatures.draconicLegs),
                Tier.DRACONIC
        );
        // Draconic Boots
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernBoots),
                new ItemStack(DEFeatures.draconicBoots),
                Tier.DRACONIC
        );

        // Chaotic Staff of Power
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicStaffOfPower),
                new ItemStack(DAFeatures.chaoticStaffOfPower),
                Tier.CHAOTIC
        );
        // Chaotic Bow
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicBow),
                new ItemStack(DAFeatures.chaoticBow),
                Tier.CHAOTIC
        );
        // Chaotic Helm
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicHelm),
                new ItemStack(DAFeatures.chaoticHelm),
                Tier.CHAOTIC
        );
        // Chaotic Chest
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicChest),
                new ItemStack(DAFeatures.chaoticChest),
                Tier.CHAOTIC
        );
        // Chaotic Leggings
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicLegs),
                new ItemStack(DAFeatures.chaoticLegs),
                Tier.CHAOTIC
        );
        // Chaotic Boots
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicBoots),
                new ItemStack(DAFeatures.chaoticBoots),
                Tier.CHAOTIC
        );
    }

    private static void addTierUpRecipe(ItemStack catalyst, ItemStack result, Tier tier) {
        addTierUpRecipe(catalyst, result, tier, -1);
    }

    private static void addTierUpRecipe(GTRecipeInput catalyst, ItemStack result, Tier tier) {
        addTierUpRecipe(catalyst, result, tier, -1);
    }

    private static void addTierUpRecipe(ItemStack catalyst, ItemStack result, Tier tier, int plateAmount) {
        addTierUpRecipe(GTRecipeItemInput.getOrCreate(catalyst).setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY), result, tier, plateAmount);
    }

    private static void addTierUpRecipe(GTRecipeInput catalyst, ItemStack result, Tier tier, int plateAmount) {
        RecipeMap<TierUpRecipeBuilder> recipeMap;
        if (tier == Tier.CHAOTIC) {
            recipeMap = GTERecipeMaps.AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES;
        } else {
            recipeMap = GTERecipeMaps.DRACONIC_FUSION_TIER_UP_FAKE_RECIPES;
        }
        TierUpRecipeBuilder recipeBuilder = recipeMap.recipeBuilder();

        recipeBuilder
                .catalyst(catalyst)
                .result(result);

        switch (tier) {
            case WYVERN:
                recipeBuilder
                        .input(plate, DRACONIUM, plateAmount)
                        .input(DEFeatures.wyvernCore, 1)
                        .input(DEFeatures.wyvernEnergyCore, 1)
                        .fluidInputs(CRYOTHEUM.getFluid(16000))
                        .fluidOutputs(PYROTHEUM.getFluid(4000))
                        .duration(200).EUt(VA[LuV]);
                break;
            case DRACONIC:
                recipeBuilder
                        .input(plate, AWAKENED_DRACONIUM, 2)
                        .input(DEFeatures.draconicCore, 4)
                        .input(DEFeatures.draconicEnergyCore, 1)
                        .fluidInputs(CRYOTHEUM.getFluid(32000))
                        .fluidOutputs(PYROTHEUM.getFluid(8000))
                        .duration(400).EUt(VA[ZPM]);
                break;
            case CHAOTIC:
                recipeBuilder
                        .input(DEFeatures.chaosShard, 4, 0)
                        .input(DEFeatures.infusedObsidian, 2)
                        .input(DEFeatures.chaoticCore, 1)
                        .input(DAFeatures.chaoticEnergyCore, 1)
                        .fluidInputs(CRYOTHEUM.getFluid(48000))
                        .fluidOutputs(PYROTHEUM.getFluid(12000))
                        .duration(600).EUt(VA[UHV]);
                break;
        }
        recipeBuilder.buildAndRegister();
    }

    private enum Tier {
        WYVERN,
        DRACONIC,
        CHAOTIC
    }

    private static void upgrade() {
        ItemStack[] upgradableItems = new ItemStack[] {
//                new ItemStack(DEFeatures.draconiumCapacitor, 1, 0),
//                new ItemStack(DEFeatures.draconiumCapacitor, 1, 1),
//                new ItemStack(DEFeatures.wyvernAxe),
//                new ItemStack(DEFeatures.wyvernPick),
//                new ItemStack(DEFeatures.wyvernShovel),
//                new ItemStack(DEFeatures.wyvernSword),
//                new ItemStack(DEFeatures.wyvernBow),
//                new ItemStack(DEFeatures.wyvernHelm),
//                new ItemStack(DEFeatures.wyvernChest),
//                new ItemStack(DEFeatures.wyvernLegs),
//                new ItemStack(DEFeatures.wyvernBoots),
//                new ItemStack(DEFeatures.draconicAxe),
//                new ItemStack(DEFeatures.draconicPick),
//                new ItemStack(DEFeatures.draconicShovel),
//                new ItemStack(DEFeatures.draconicSword),
//                new ItemStack(DEFeatures.draconicBow),
//                new ItemStack(DEFeatures.draconicStaffOfPower),
                new ItemStack(DEFeatures.draconicHelm),
//                new ItemStack(DEFeatures.draconicChest),
//                new ItemStack(DEFeatures.draconicLegs),
//                new ItemStack(DEFeatures.draconicBoots),
//                new ItemStack(DAFeatures.chaoticStaffOfPower),
//                new ItemStack(DAFeatures.chaoticBow),
//                new ItemStack(DAFeatures.chaoticHelm),
//                new ItemStack(DAFeatures.chaoticChest),
//                new ItemStack(DAFeatures.chaoticLegs),
//                new ItemStack(DAFeatures.chaoticBoots),
        };

        for (ItemStack stack : upgradableItems) {
            if (!(stack.getItem() instanceof IUpgradableItem)) {
                GTExpertMod.logger.error("Item {} is not an instance of IUpgradableItem!", stack);
                continue;
            }
            IUpgradableItem item = (IUpgradableItem) stack.getItem();
            for (String upgradeName : ToolUpgrade.NAME_TO_ID.keySet()) {
                for (int currentLevel = 0; currentLevel < ToolUpgrade.NAME_MAX_LEVEL.get(upgradeName); currentLevel++) {
                    if (item.getValidUpgrades(stack).contains(upgradeName) && item.getMaxUpgradeLevel(stack, upgradeName) >= currentLevel + 1) {
                        RecipeMap<UpgradeRecipeBuilder> recipeMap;
                        if (currentLevel == 0 || currentLevel == 1) {
                            recipeMap = GTERecipeMaps.DRACONIC_FUSION_UPGRADE_FAKE_RECIPES;
                        } else {
                            recipeMap = GTERecipeMaps.AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES;
                        }
                        UpgradeRecipeBuilder recipeBuilder = recipeMap.recipeBuilder();

                        recipeBuilder
                                .catalyst(stack)
                                .upgradeName(upgradeName)
                                .level(currentLevel);

                        if (currentLevel == 0) {
                            recipeBuilder
                                    .input(Items.GOLDEN_APPLE, 2)
                                    .input(OrePrefix.gem, Diamond, 2)
                                    .input(OrePrefix.gem, EnderEye, 2)
                                    .input(DEFeatures.wyvernCore)
                                    .fluidInputs(CRYOTHEUM.getFluid(8000))
                                    .fluidOutputs(PYROTHEUM.getFluid(2000))
                                    .duration(100).EUt(VA[IV]);
                        } else if (currentLevel == 1) {
                            recipeBuilder
                                    .input(OrePrefix.gem, NetherStar, 2)
                                    .input(DEFeatures.wyvernCore, 2)
                                    .input(OrePrefix.gem, Emerald, 2)
                                    .input(DEFeatures.draconicCore)
                                    .fluidInputs(CRYOTHEUM.getFluid(16000))
                                    .fluidOutputs(PYROTHEUM.getFluid(4000))
                                    .duration(200).EUt(VA[LuV]);
                        } else if (currentLevel == 2) {
                            recipeBuilder
                                    .input(OrePrefix.gem, NetherStar, 2)
                                    .input(DEFeatures.draconicCore, 2)
                                    .input(OrePrefix.block, Emerald, 2)
                                    .input(DEFeatures.awakenedCore)
                                    .fluidInputs(CRYOTHEUM.getFluid(32000))
                                    .fluidOutputs(PYROTHEUM.getFluid(8000))
                                    .duration(400).EUt(VA[ZPM]);
                        } else if (currentLevel == 3) {
                            recipeBuilder
                                    .input(DEFeatures.draconicCore, 2)
                                    .input(DEFeatures.awakenedCore, 2)
                                    .input(Blocks.DRAGON_EGG, 2)
                                    .input(DEFeatures.chaoticCore)
                                    .fluidInputs(CRYOTHEUM.getFluid(48000))
                                    .fluidOutputs(PYROTHEUM.getFluid(12000))
                                    .duration(600).EUt(VA[UHV]);
                        }

                        recipeBuilder.buildAndRegister();
                    }
                }
            }
        }
    }
}
