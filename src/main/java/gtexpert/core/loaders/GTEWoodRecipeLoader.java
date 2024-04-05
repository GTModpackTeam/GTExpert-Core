package gtexpert.core.loaders;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.GTELog;
import gtexpert.core.GTEConfigHolder;
import gtexpert.core.GTERecipeMaps;

public class GTEWoodRecipeLoader {

    public static void logsEmptyCheck(@NotNull WoodTypeEntry entry) {
        logsEmptyCheck(false, entry);
    }

    public static void logsEmptyCheck(boolean throwException, @NotNull WoodTypeEntry entry) {
        if (entry.log.isEmpty()) {
            String message = "Could not find logs form of WoodTypeEntry '" + entry.woodName + "'.";

            if (throwException) {
                throw new IllegalStateException(message);
            } else {
                GTELog.logger.debug(message);
            }
        }
    }

    public static void planksEmptyCheck(@NotNull WoodTypeEntry entry) {
        planksEmptyCheck(false, entry);
    }

    public static void planksEmptyCheck(boolean throwException, @NotNull WoodTypeEntry entry) {
        if (entry.planks.isEmpty()) {
            String message = "Could not find planks form of WoodTypeEntry '" + entry.woodName + "'.";

            if (throwException) {
                throw new IllegalStateException(
                        "Could not find planks form of WoodTypeEntry '" + entry.woodName + "'.");
            } else {
                GTELog.logger.debug(message);
            }
        }
    }

    public static void addSawmillRecipe(@NotNull WoodTypeEntry entry) {
        logsEmptyCheck(entry);
        planksEmptyCheck(entry);

        if (!entry.log.isEmpty()) {
            GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .inputs(GTUtility.copy(6, entry.log))
                    .fluidInputs(Materials.Water.getFluid(1000))
                    .outputs(GTUtility.copy(48, entry.planks))
                    .output(dust, Materials.Wood, 12)
                    .duration(600).EUt(VA[LV])
                    .buildAndRegister();
            GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .inputs(GTUtility.copy(6, entry.log))
                    .fluidInputs(Materials.Water.getFluid(2500))
                    .outputs(GTUtility.copy(60, entry.planks))
                    .duration(800).EUt(VA[LV])
                    .buildAndRegister();
        }
    }

    public static void addPlankRecipe(@NotNull WoodTypeEntry entry) {
        logsEmptyCheck(entry);
        planksEmptyCheck(entry);
        final String modId = entry.modid;
        final String name = entry.woodName;
        boolean hasPlanksRecipe = entry.planksRecipeName != null;

        int plank_normal = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 1 : 2;
        plank_normal = ConfigHolder.recipes.nerfWoodCrafting ? plank_normal : 4;
        int plank_saw = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 2 : 4;
        plank_saw = ConfigHolder.recipes.nerfWoodCrafting ? plank_saw : 6;

        if (hasPlanksRecipe) {
            ModHandler.addShapelessRecipe(modId + "_" + entry.planksRecipeName,
                    GTUtility.copy(plank_normal, entry.planks), entry.log.copy());
            ModHandler.addMirroredShapedRecipe(modId + "_" + entry.planksRecipeName + "_saw",
                    GTUtility.copy(plank_saw, entry.planks), "s", "L",
                    'L', entry.log.copy());
        } else {
            ModHandler.addShapelessRecipe(modId + "_" + name + "_plank",
                    GTUtility.copy(plank_normal, entry.planks), entry.log.copy());
            ModHandler.addMirroredShapedRecipe(modId + "_" + name + "_plank_saw",
                    GTUtility.copy(plank_saw, entry.planks), "s", "L",
                    'L', entry.log.copy());
        }
    }

    public static void overridePlankRecipe(@NotNull WoodTypeEntry entry) {
        overridePlankRecipe(false, entry, entry.modid);
    }

    public static void overridePlankRecipe(boolean removeSawRecipes, @NotNull WoodTypeEntry entry) {
        overridePlankRecipe(removeSawRecipes, entry, entry.modid);
    }

    public static void overridePlankRecipe(boolean removeSawRecipes, @NotNull WoodTypeEntry entry,
                                           @NotNull String otherModId) {
        removePlankRecipe(removeSawRecipes, entry, otherModId);
        addPlankRecipe(entry);

        if (!ConfigHolder.recipes.harderCharcoalRecipe && !entry.removeCharcoalRecipe) return;
        final ItemStack outputStack = FurnaceRecipes.instance().getSmeltingResult(entry.log);
        if (outputStack.getItem() == Items.COAL && outputStack.getItemDamage() == 1) {
            ModHandler.removeFurnaceSmelting(entry.log);
        }
    }

    public static void removePlankRecipe(@NotNull WoodTypeEntry entry) {
        removePlankRecipe(false, entry, entry.modid);
    }

    public static void removePlankRecipe(boolean removeSawRecipes, @NotNull WoodTypeEntry entry) {
        removePlankRecipe(removeSawRecipes, entry, entry.modid);
    }

    public static void removePlankRecipe(boolean removeSawRecipes, @NotNull WoodTypeEntry entry,
                                         @NotNull String otherModId) {
        final String name = entry.woodName;
        final String modId = otherModId.isEmpty() ? entry.modid : otherModId;
        final boolean hasPlanksRecipe = entry.planksRecipeName != null;

        ModHandler.removeRecipeByName(
                new ResourceLocation(modId, hasPlanksRecipe ? entry.planksRecipeName : name + "_planks"));

        if (!removeSawRecipes) return;
        ModHandler.removeRecipeByName(
                new ResourceLocation(modId, hasPlanksRecipe ? entry.planksRecipeName + "_saw" : name + "_planks_saw"));
    }
}
