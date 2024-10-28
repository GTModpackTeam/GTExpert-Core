package gtexpert.integration.deda.recipes;

import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.ore.OrePrefix.plate;

import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.item.ItemStack;

import com.brandon3055.draconicevolution.DEFeatures;

import gregtech.api.GTValues;
import gregtech.api.items.toolitem.ToolHelper;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.integration.deda.recipemaps.GTEDraconicRecipeMaps;
import gtexpert.integration.deda.recipemaps.tierup.TierUpRecipeBuilder;

public class DraconicTierupRecipe {

    public static void init() {
        // Axe of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_axe"));
        addTierUpRecipe(
                new GTRecipeItemInput(ToolItems.AXE.get(GTEMaterials.Draconium)).setNBTMatchingCondition(
                        NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", GTValues.MODID + ":draconium"))),
                new ItemStack(DEFeatures.wyvernAxe),
                Tier.WYVERN, 2);

        // Pickaxe of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_pick"));
        addTierUpRecipe(
                new GTRecipeItemInput(ToolItems.PICKAXE.get(GTEMaterials.Draconium)).setNBTMatchingCondition(
                        NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", GTValues.MODID + ":draconium"))),
                new ItemStack(DEFeatures.wyvernPick),
                Tier.WYVERN, 2);

        // Shovel of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_shovel"));
        addTierUpRecipe(
                new GTRecipeItemInput(ToolItems.SHOVEL.get(GTEMaterials.Draconium)).setNBTMatchingCondition(
                        NBTMatcher.RECURSIVE_EQUAL_TO, NBTCondition.create(
                                NBTTagType.COMPOUND, ToolHelper.TOOL_TAG_KEY, NBTCondition.create(
                                        NBTTagType.STRING, "Material", GTValues.MODID + ":draconium"))),
                new ItemStack(DEFeatures.wyvernShovel),
                Tier.WYVERN, 2);

        // Sword of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_sword"));
        addTierUpRecipe(MetaItems.NANO_SABER.getStackForm(),
                new ItemStack(DEFeatures.wyvernSword),
                Tier.WYVERN, 2);

        // Bow of the Wyvern
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_bow"));
        addTierUpRecipe(
                new GTRecipeItemInput(Mods.EnderIO.isModLoaded() ?
                        GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_dark_steel_bow") :
                        Mods.Vanilla.getItem("bow"))
                                .setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY),
                new ItemStack(DEFeatures.wyvernBow),
                Tier.WYVERN, 2);

        // Wyvern Helm
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_helm"));
        addTierUpRecipe(
                MetaItems.QUANTUM_HELMET.getStackForm(),
                new ItemStack(DEFeatures.wyvernHelm),
                Tier.WYVERN, 6);

        // Wyvern Chest
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_chest"));
        addTierUpRecipe(
                MetaItems.QUANTUM_CHESTPLATE_ADVANCED.getStackForm(),
                new ItemStack(DEFeatures.wyvernChest),
                Tier.WYVERN,
                6);

        // Wyvern Legs
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_legs"));
        addTierUpRecipe(
                MetaItems.QUANTUM_LEGGINGS.getStackForm(),
                new ItemStack(DEFeatures.wyvernLegs),
                Tier.WYVERN,
                6);

        // Wyvern Boots
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("wyvern_boots"));
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
                .catalyst(new GTRecipeItemInput(new ItemStack(DEFeatures.draconicPick))
                        .setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY))
                .result(new ItemStack(DEFeatures.draconicStaffOfPower))
                .input(plate, GTEMaterials.Draconium, 6)
                .inputNBT(DEFeatures.draconicAxe, NBTMatcher.ANY, NBTCondition.ANY)
                .inputNBT(DEFeatures.draconicShovel, NBTMatcher.ANY, NBTCondition.ANY)
                .inputNBT(DEFeatures.draconicSword, NBTMatcher.ANY, NBTCondition.ANY)
                .input(DEFeatures.draconicCore, 4)
                .fluidInputs(GTEMaterials.Cryotheum.getFluid(32000))
                .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                .duration(400).EUt(VA[GTEValues.dedaVoltageTier + 1])
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
                new GTRecipeItemInput(catalyst).setNBTMatchingCondition(NBTMatcher.ANY, NBTCondition.ANY),
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
                    .duration(200).EUt(VA[GTEValues.dedaVoltageTier]);
            case DRACONIC -> recipeBuilder
                    .input(plate, GTEMaterials.AwakenedDraconium, 2)
                    .input(DEFeatures.draconicCore, 4)
                    .input(DEFeatures.draconicEnergyCore, 1)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(32000))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                    .duration(400).EUt(VA[GTEValues.dedaVoltageTier + 1]);
            case CHAOTIC -> recipeBuilder
                    .input(DEFeatures.chaosShard, 4, 0)
                    .input(DEFeatures.infusedObsidian, 2)
                    .input(DEFeatures.chaoticCore, 1)
                    .input(DAFeatures.chaoticEnergyCore, 1)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(48000))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 12000))
                    .duration(600).EUt(VA[GTEValues.dedaVoltageTier + 2]);
        }
        recipeBuilder.buildAndRegister();
    }

    private enum Tier {
        WYVERN,
        DRACONIC,
        CHAOTIC
    }
}
