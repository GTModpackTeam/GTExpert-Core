package com.github.gtexpert.core.integration.deda.recipes;

import static gregtech.api.unification.ore.OrePrefix.plate;

import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.item.ItemStack;

import com.brandon3055.draconicevolution.DEFeatures;

import gregtech.api.items.toolitem.ToolHelper;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.api.util.GTEUtility;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.deda.DEDAConstants;
import com.github.gtexpert.core.integration.deda.DEDAConstants.DraconicTier;
import com.github.gtexpert.core.integration.deda.DEDARecipeHelper;
import com.github.gtexpert.core.integration.deda.recipemaps.GTEDraconicRecipeMaps;
import com.github.gtexpert.core.integration.deda.recipemaps.tierup.TierUpRecipeBuilder;

public class DraconicTierupRecipe {

    public static void init() {
        // Axe of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_axe"));
        addTierUpRecipe(
                new GTRecipeItemInput(ToolItems.AXE.get(GTEMaterials.Draconium)).setNBTMatchingCondition(
                        NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", GTEValues.MODID + ":draconium"))),
                new ItemStack(DEFeatures.wyvernAxe),
                DraconicTier.WYVERN, 2);

        // Pickaxe of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_pick"));
        addTierUpRecipe(
                new GTRecipeItemInput(ToolItems.PICKAXE.get(GTEMaterials.Draconium)).setNBTMatchingCondition(
                        NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", GTEValues.MODID + ":draconium"))),
                new ItemStack(DEFeatures.wyvernPick),
                DraconicTier.WYVERN, 2);

        // Shovel of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_shovel"));
        addTierUpRecipe(
                new GTRecipeItemInput(ToolItems.SHOVEL.get(GTEMaterials.Draconium)).setNBTMatchingCondition(
                        NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", GTEValues.MODID + ":draconium"))),
                new ItemStack(DEFeatures.wyvernShovel),
                DraconicTier.WYVERN, 2);

        // Sword of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_sword"));
        addTierUpRecipe(MetaItems.NANO_SABER.getStackForm(),
                new ItemStack(DEFeatures.wyvernSword),
                DraconicTier.WYVERN, 2);

        // Bow of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_bow"));
        addTierUpRecipe(
                new GTRecipeItemInput(Mods.EnderIO.isModLoaded() ?
                        GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_dark_steel_bow") :
                        Mods.Vanilla.getItem("bow"))
                                .setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY),
                new ItemStack(DEFeatures.wyvernBow),
                DraconicTier.WYVERN, 2);

        // Wyvern Helm
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_helm"));
        addTierUpRecipe(
                MetaItems.QUANTUM_HELMET.getStackForm(),
                new ItemStack(DEFeatures.wyvernHelm),
                DraconicTier.WYVERN, 6);

        // Wyvern Chest
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_chest"));
        addTierUpRecipe(
                MetaItems.QUANTUM_CHESTPLATE_ADVANCED.getStackForm(),
                new ItemStack(DEFeatures.wyvernChest),
                DraconicTier.WYVERN,
                6);

        // Wyvern Legs
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_legs"));
        addTierUpRecipe(
                MetaItems.QUANTUM_LEGGINGS.getStackForm(),
                new ItemStack(DEFeatures.wyvernLegs),
                DraconicTier.WYVERN,
                6);

        // Wyvern Boots
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_boots"));
        addTierUpRecipe(
                MetaItems.QUANTUM_BOOTS.getStackForm(),
                new ItemStack(DEFeatures.wyvernBoots),
                DraconicTier.WYVERN,
                6);

        // Draconic Axe
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernAxe),
                new ItemStack(DEFeatures.draconicAxe),
                DraconicTier.DRACONIC);

        // Draconic Pickaxe
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernPick),
                new ItemStack(DEFeatures.draconicPick),
                DraconicTier.DRACONIC);

        // Draconic Shovel
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernShovel),
                new ItemStack(DEFeatures.draconicShovel),
                DraconicTier.DRACONIC);

        // Draconic Sword
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernSword),
                new ItemStack(DEFeatures.draconicSword),
                DraconicTier.DRACONIC);

        // Draconic Bow
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernBow),
                new ItemStack(DEFeatures.draconicBow),
                DraconicTier.DRACONIC);

        // Draconic Staff of Power
        GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_RECIPES.recipeBuilder()
                .catalyst(DEDARecipeHelper.inputWithAnyNBT(new ItemStack(DEFeatures.draconicPick)))
                .result(new ItemStack(DEFeatures.draconicStaffOfPower))
                .tier(DraconicTier.DRACONIC)
                .input(plate, GTEMaterials.Draconium, 6)
                .inputNBT(DEFeatures.draconicAxe, NBTMatcher.ANY, NBTCondition.ANY)
                .inputNBT(DEFeatures.draconicShovel, NBTMatcher.ANY, NBTCondition.ANY)
                .inputNBT(DEFeatures.draconicSword, NBTMatcher.ANY, NBTCondition.ANY)
                .input(DEFeatures.draconicCore, 4)
                .fluidInputs(DEDARecipeHelper.cryotheum(DraconicTier.DRACONIC))
                .fluidOutputs(DEDARecipeHelper.pyrotheum(DraconicTier.DRACONIC))
                .duration(DEDAConstants.DURATION_DRACONIC).EUt(DEDARecipeHelper.voltage(DraconicTier.DRACONIC))
                .buildAndRegister();

        // Draconic Helm
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernHelm),
                new ItemStack(DEFeatures.draconicHelm),
                DraconicTier.DRACONIC);

        // Draconic Chest
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernChest),
                new ItemStack(DEFeatures.draconicChest),
                DraconicTier.DRACONIC);

        // Draconic Legs
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernLegs),
                new ItemStack(DEFeatures.draconicLegs),
                DraconicTier.DRACONIC);

        // Draconic Boots
        addTierUpRecipe(
                new ItemStack(DEFeatures.wyvernBoots),
                new ItemStack(DEFeatures.draconicBoots),
                DraconicTier.DRACONIC);

        // Chaotic Staff of Power
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicStaffOfPower),
                new ItemStack(DAFeatures.chaoticStaffOfPower),
                DraconicTier.CHAOTIC);

        // Chaotic Bow
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicBow),
                new ItemStack(DAFeatures.chaoticBow),
                DraconicTier.CHAOTIC);

        // Chaotic Helm
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicHelm),
                new ItemStack(DAFeatures.chaoticHelm),
                DraconicTier.CHAOTIC);

        // Chaotic Chest
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicChest),
                new ItemStack(DAFeatures.chaoticChest),
                DraconicTier.CHAOTIC);

        // Chaotic Leggings
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicLegs),
                new ItemStack(DAFeatures.chaoticLegs),
                DraconicTier.CHAOTIC);

        // Chaotic Boots
        addTierUpRecipe(
                new ItemStack(DEFeatures.draconicBoots),
                new ItemStack(DAFeatures.chaoticBoots),
                DraconicTier.CHAOTIC);
    }

    private static void addTierUpRecipe(ItemStack catalyst, ItemStack result, DraconicTier tier) {
        addTierUpRecipe(catalyst, result, tier, -1);
    }

    private static void addTierUpRecipe(GTRecipeInput catalyst, ItemStack result, DraconicTier tier) {
        addTierUpRecipe(catalyst, result, tier, -1);
    }

    private static void addTierUpRecipe(ItemStack catalyst, ItemStack result, DraconicTier tier, int plateAmount) {
        addTierUpRecipe(
                new GTRecipeItemInput(catalyst).setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY),
                result, tier, plateAmount);
    }

    private static void addTierUpRecipe(GTRecipeInput catalyst, ItemStack result, DraconicTier tier, int plateAmount) {
        TierUpRecipeBuilder recipeBuilder = GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_RECIPES.recipeBuilder();
        recipeBuilder.catalyst(catalyst).result(result).tier(tier);

        switch (tier) {
            case WYVERN -> recipeBuilder
                    .input(plate, GTEMaterials.Draconium, plateAmount)
                    .input(DEFeatures.wyvernCore, 1)
                    .input(DEFeatures.wyvernEnergyCore, 1)
                    .fluidInputs(DEDARecipeHelper.cryotheum(DraconicTier.WYVERN))
                    .fluidOutputs(DEDARecipeHelper.pyrotheum(DraconicTier.WYVERN))
                    .duration(DEDAConstants.DURATION_WYVERN).EUt(DEDARecipeHelper.voltage(DraconicTier.WYVERN));
            case DRACONIC -> recipeBuilder
                    .input(plate, GTEMaterials.AwakenedDraconium, 2)
                    .input(DEFeatures.draconicCore, 4)
                    .input(DEFeatures.draconicEnergyCore, 1)
                    .fluidInputs(DEDARecipeHelper.cryotheum(DraconicTier.DRACONIC))
                    .fluidOutputs(DEDARecipeHelper.pyrotheum(DraconicTier.DRACONIC))
                    .duration(DEDAConstants.DURATION_DRACONIC).EUt(DEDARecipeHelper.voltage(DraconicTier.DRACONIC));
            case CHAOTIC -> recipeBuilder
                    .input(DEFeatures.chaosShard, 4, 0)
                    .input(DEFeatures.infusedObsidian, 2)
                    .input(DEFeatures.chaoticCore, 1)
                    .input(DAFeatures.chaoticEnergyCore, 1)
                    .fluidInputs(DEDARecipeHelper.cryotheum(DraconicTier.CHAOTIC))
                    .fluidOutputs(DEDARecipeHelper.pyrotheum(DraconicTier.CHAOTIC))
                    .duration(DEDAConstants.DURATION_CHAOTIC).EUt(DEDARecipeHelper.voltage(DraconicTier.CHAOTIC));
        }
        recipeBuilder.buildAndRegister();
    }
}
