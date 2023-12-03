package gtexpert.loaders.recipe.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.GTEConfigHolder.dedaIntegration;

import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import com.brandon3055.draconicevolution.DEFeatures;
import com.brandon3055.draconicevolution.api.itemupgrade.IUpgradableItem;
import com.brandon3055.draconicevolution.items.ToolUpgrade;

import gregtech.api.GTValues;
import gregtech.api.items.toolitem.ToolHelper;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.draconic.GTEDraconicRecipeMaps;
import gtexpert.api.recipes.draconic.tierup.TierUpRecipeBuilder;
import gtexpert.api.recipes.draconic.upgrade.UpgradeRecipeBuilder;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTELog;

public class DraconicUpgradeRecipeLoader {

    public static void init() {
        tierUp();
        upgrade();
    }

    private static void tierUp() {
        // Axe of the Wyvern
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_axe"));
        addTierUpRecipe(
                GTRecipeItemInput.getOrCreate(ToolItems.AXE.get(GTEMaterials.Draconium)).setNBTMatchingCondition(
                        NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", GTValues.MODID + ":draconium"))),
                new ItemStack(DEFeatures.wyvernAxe),
                Tier.WYVERN, 2);

        // Pickaxe of the Wyvern
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_pick"));
        addTierUpRecipe(
                GTRecipeItemInput.getOrCreate(ToolItems.PICKAXE.get(GTEMaterials.Draconium)).setNBTMatchingCondition(
                        NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", GTValues.MODID + ":draconium"))),
                new ItemStack(DEFeatures.wyvernPick),
                Tier.WYVERN, 2);

        // Shovel of the Wyvern
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_shovel"));
        addTierUpRecipe(
                GTRecipeItemInput.getOrCreate(ToolItems.SHOVEL.get(GTEMaterials.Draconium)).setNBTMatchingCondition(
                        NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", GTValues.MODID + ":draconium"))),
                new ItemStack(DEFeatures.wyvernShovel),
                Tier.WYVERN, 2);

        // Sword of the Wyvern
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_sword"));
        addTierUpRecipe(MetaItems.NANO_SABER.getStackForm(),
                new ItemStack(DEFeatures.wyvernSword),
                Tier.WYVERN, 2);

        // Bow of the Wyvern
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_bow"));
        addTierUpRecipe(
                GTRecipeItemInput.getOrCreate(Loader.isModLoaded(GTEValues.MODID_EIO) ?
                        getModItem(GTEValues.MODID_EIO, "item_dark_steel_bow", 1, 0) :
                        getModItem(GTEValues.MODID_VANILLA, "bow", 1, 0))
                        .setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY),
                new ItemStack(DEFeatures.wyvernBow),
                Tier.WYVERN, 2);

        // Wyvern Helm
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_helm"));
        addTierUpRecipe(
                MetaItems.QUANTUM_HELMET.getStackForm(),
                new ItemStack(DEFeatures.wyvernHelm),
                Tier.WYVERN, 6);

        // Wyvern Chest
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_chest"));
        addTierUpRecipe(
                MetaItems.QUANTUM_CHESTPLATE_ADVANCED.getStackForm(),
                new ItemStack(DEFeatures.wyvernChest),
                Tier.WYVERN,
                6);

        // Wyvern Legs
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_legs"));
        addTierUpRecipe(
                MetaItems.QUANTUM_LEGGINGS.getStackForm(),
                new ItemStack(DEFeatures.wyvernLegs),
                Tier.WYVERN,
                6);

        // Wyvern Boots
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "wyvern_boots"));
        addTierUpRecipe(
                MetaItems.QUANTUM_BOOTS.getStackForm(),
                new ItemStack(DEFeatures.wyvernBoots),
                Tier.WYVERN,
                6);

        // Draconic Axe
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernAxe),
                new ItemStack(DEFeatures.draconicAxe),
                Tier.DRACONIC);

        // Draconic Pickaxe
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernPick),
                new ItemStack(DEFeatures.draconicPick),
                Tier.DRACONIC);

        // Draconic Shovel
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernShovel),
                new ItemStack(DEFeatures.draconicShovel),
                Tier.DRACONIC);

        // Draconic Sword
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernSword),
                new ItemStack(DEFeatures.draconicSword),
                Tier.DRACONIC);

        // Draconic Bow
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernBow),
                new ItemStack(DEFeatures.draconicBow),
                Tier.DRACONIC);

        // Draconic Staff of Power
        GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.recipeBuilder()
                .catalyst(GTRecipeItemInput.getOrCreate(new ItemStack(DEFeatures.draconicPick))
                        .setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY))
                .result(new ItemStack(DEFeatures.draconicStaffOfPower))
                .input(plate, GTEMaterials.Draconium, 6)
                .inputNBT(DEFeatures.draconicAxe, NBTMatcher.ANY, NBTCondition.ANY)
                .inputNBT(DEFeatures.draconicShovel, NBTMatcher.ANY, NBTCondition.ANY)
                .inputNBT(DEFeatures.draconicSword, NBTMatcher.ANY, NBTCondition.ANY)
                .input(DEFeatures.draconicCore, 4)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(32000))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                .duration(400).EUt(VA[dedaIntegration.voltageTier + 1])
                .buildAndRegister();

        // Draconic Helm
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernHelm),
                new ItemStack(DEFeatures.draconicHelm),
                Tier.DRACONIC);

        // Draconic Chest
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernChest),
                new ItemStack(DEFeatures.draconicChest),
                Tier.DRACONIC);

        // Draconic Legs
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernLegs),
                new ItemStack(DEFeatures.draconicLegs),
                Tier.DRACONIC);

        // Draconic Boots
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernBoots),
                new ItemStack(DEFeatures.draconicBoots),
                Tier.DRACONIC);

        // Chaotic Staff of Power
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicStaffOfPower),
                new ItemStack(DAFeatures.chaoticStaffOfPower),
                Tier.CHAOTIC);

        // Chaotic Bow
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicBow),
                new ItemStack(DAFeatures.chaoticBow),
                Tier.CHAOTIC);

        // Chaotic Helm
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicHelm),
                new ItemStack(DAFeatures.chaoticHelm),
                Tier.CHAOTIC);

        // Chaotic Chest
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicChest),
                new ItemStack(DAFeatures.chaoticChest),
                Tier.CHAOTIC);

        // Chaotic Leggings
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicLegs),
                new ItemStack(DAFeatures.chaoticLegs),
                Tier.CHAOTIC);

        // Chaotic Boots
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicBoots),
                new ItemStack(DAFeatures.chaoticBoots),
                Tier.CHAOTIC);
    }

    private static void addTierUpRecipe(ItemStack catalyst, ItemStack result, Tier tier) {
        addTierUpRecipe(catalyst, result, tier, -1);
    }

    private static void addTierUpRecipe(GTRecipeInput catalyst, ItemStack result, Tier tier) {
        addTierUpRecipe(catalyst, result, tier, -1);
    }

    private static void addTierUpRecipe(ItemStack catalyst, ItemStack result, Tier tier, int plateAmount) {
        addTierUpRecipe(
                GTRecipeItemInput.getOrCreate(catalyst).setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY),
                result, tier, plateAmount);
    }

    private static void addTierUpRecipe(GTRecipeInput catalyst, ItemStack result, Tier tier, int plateAmount) {
        RecipeMap<TierUpRecipeBuilder> recipeMap;
        if (tier == Tier.CHAOTIC) {
            recipeMap = GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES;
        } else {
            recipeMap = GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_FAKE_RECIPES;
        }
        TierUpRecipeBuilder recipeBuilder = recipeMap.recipeBuilder();
        recipeBuilder.catalyst(catalyst).result(result);

        switch (tier) {
            case WYVERN -> recipeBuilder
                    .input(plate, GTEMaterials.Draconium, plateAmount)
                    .input(DEFeatures.wyvernCore, 1)
                    .input(DEFeatures.wyvernEnergyCore, 1)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                    .duration(200).EUt(VA[dedaIntegration.voltageTier]);
            case DRACONIC -> recipeBuilder
                    .input(plate, GTEMaterials.AwakenedDraconium, 2)
                    .input(DEFeatures.draconicCore, 4)
                    .input(DEFeatures.draconicEnergyCore, 1)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(32000))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                    .duration(400).EUt(VA[dedaIntegration.voltageTier + 1]);
            case CHAOTIC -> recipeBuilder
                    .input(DEFeatures.chaosShard, 4, 0)
                    .input(DEFeatures.infusedObsidian, 2)
                    .input(DEFeatures.chaoticCore, 1)
                    .input(DAFeatures.chaoticEnergyCore, 1)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(48000))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 12000))
                    .duration(600).EUt(VA[dedaIntegration.voltageTier + 2]);
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
                new ItemStack(DEFeatures.draconiumCapacitor, 1, 0),
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
                        RecipeMap<UpgradeRecipeBuilder> recipeMap;
                        if (currentLevel == 0 || currentLevel == 1) {
                            recipeMap = GTEDraconicRecipeMaps.DRACONIC_FUSION_UPGRADE_FAKE_RECIPES;
                        } else {
                            recipeMap = GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES;
                        }
                        UpgradeRecipeBuilder recipeBuilder = recipeMap.recipeBuilder();
                        recipeBuilder.catalyst(stack).upgradeName(upgradeName).level(currentLevel);

                        switch (currentLevel) {
                            case 0 -> recipeBuilder
                                    .input(Items.GOLDEN_APPLE, 2)
                                    .input(OrePrefix.gem, Materials.Diamond, 2)
                                    .input(OrePrefix.gem, Materials.EnderEye, 2)
                                    .input(DEFeatures.wyvernCore)
                                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(8000))
                                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 2000))
                                    .duration(100).EUt(VA[dedaIntegration.voltageTier - 1]);
                            case 1 -> recipeBuilder
                                    .input(OrePrefix.gem, Materials.NetherStar, 2)
                                    .input(DEFeatures.wyvernCore, 2)
                                    .input(OrePrefix.gem, Materials.Emerald, 2)
                                    .input(DEFeatures.draconicCore)
                                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(16000))
                                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 4000))
                                    .duration(200).EUt(VA[dedaIntegration.voltageTier]);
                            case 2 -> recipeBuilder
                                    .input(OrePrefix.gem, Materials.NetherStar, 2)
                                    .input(DEFeatures.draconicCore, 2)
                                    .input(OrePrefix.block, Materials.Emerald, 2)
                                    .input(DEFeatures.awakenedCore)
                                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(32000))
                                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                                    .duration(400).EUt(VA[dedaIntegration.voltageTier + 1]);
                            case 3 -> recipeBuilder
                                    .input(DEFeatures.draconicCore, 2)
                                    .input(DEFeatures.awakenedCore, 2)
                                    .input(Blocks.DRAGON_EGG, 2)
                                    .input(DEFeatures.chaoticCore)
                                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(48000))
                                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 12000))
                                    .duration(600).EUt(VA[dedaIntegration.voltageTier + 2]);
                        }
                        recipeBuilder.buildAndRegister();
                    }
                    currentLevel++;
                }
            }
        }
    }
}
