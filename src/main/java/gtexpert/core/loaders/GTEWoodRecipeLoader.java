package gtexpert.core.loaders;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.loaders.WoodTypeEntry;

import gtexpert.core.GTEConfigHolder;
import gtexpert.core.GTERecipeMaps;

public class GTEWoodRecipeLoader {

    public static void addSawmillRecipes(@NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;

        if (entry.planks.isEmpty()) {
            throw new IllegalStateException("Could not find planks form of WoodTypeEntry '" + name + "'.");
        }

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

    public static void addCutterRecipes(@NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;

        if (entry.planks.isEmpty()) {
            throw new IllegalStateException("Could not find planks form of WoodTypeEntry '" + name + "'.");
        }

        if (!entry.log.isEmpty()) {
            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                    .inputs(entry.log.copy())
                    .fluidInputs(Materials.Lubricant.getFluid(1))
                    .outputs(GTUtility.copy(6, entry.planks))
                    .output(dust, Materials.Wood, 2)
                    .duration(200).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                    .inputs(entry.log.copy())
                    .fluidInputs(Materials.DistilledWater.getFluid(3))
                    .outputs(GTUtility.copy(6, entry.planks))
                    .output(dust, Materials.Wood, 2)
                    .duration(300).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                    .inputs(entry.log.copy())
                    .fluidInputs(Materials.Water.getFluid(4))
                    .outputs(GTUtility.copy(6, entry.planks))
                    .output(dust, Materials.Wood, 2)
                    .duration(400).EUt(VA[ULV])
                    .buildAndRegister();
        }
    }

    public static void addPlankRecipes(@NotNull WoodTypeEntry entry) {
        if (entry.log.isEmpty()) return;
        final String name = entry.woodName;
        boolean hasPlanksRecipe = entry.planksRecipeName != null;

        if (entry.planks.isEmpty()) {
            throw new IllegalStateException("Could not find planks form of WoodTypeEntry '" + name + "'.");
        }

        int plank_normal = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 1 : 2;
        plank_normal = ConfigHolder.recipes.nerfWoodCrafting ? plank_normal : 4;
        int plank_saw = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 2 : 4;
        plank_saw = ConfigHolder.recipes.nerfWoodCrafting ? plank_saw : 6;

        if (hasPlanksRecipe) {
            ModHandler.addShapelessRecipe(entry.planksRecipeName,
                    GTUtility.copy(plank_normal, entry.planks), entry.log.copy());
            ModHandler.addMirroredShapedRecipe(entry.planksRecipeName + "_saw",
                    GTUtility.copy(plank_saw, entry.planks), "s", "L",
                    'L', entry.log.copy());
        } else {
            ModHandler.addShapelessRecipe(name + "_planks",
                    GTUtility.copy(plank_normal, entry.planks), entry.log.copy());
            ModHandler.addMirroredShapedRecipe(name + "_planks_saw",
                    GTUtility.copy(plank_saw, entry.planks), "s", "L",
                    'L', entry.log.copy());
        }
    }

    public static void removePlankRecipes(@NotNull WoodTypeEntry entry) {
        removePlankRecipes(false, entry, entry.modid);
    }

    public static void removePlankRecipes(boolean removeSawRecipes, @NotNull WoodTypeEntry entry) {
        removePlankRecipes(removeSawRecipes, entry, entry.modid);
    }

    public static void removePlankRecipes(boolean removeSawRecipes, @NotNull WoodTypeEntry entry,
                                          @NotNull String otherModId) {
        final String name = entry.woodName;
        final String modId = otherModId.isEmpty() ? entry.modid : otherModId;
        final boolean hasPlanksRecipe = entry.planksRecipeName != null;

        if (entry.planks.isEmpty()) {
            throw new IllegalStateException("Could not find planks form of WoodTypeEntry '" + name + "'.");
        }

        if (hasPlanksRecipe) {
            ModHandler.removeRecipeByName(new ResourceLocation(modId, entry.planksRecipeName));

            if (!removeSawRecipes) return;
            ModHandler.removeRecipeByName(new ResourceLocation(modId, entry.planksRecipeName + "_saw"));
        } else {
            ModHandler.removeRecipeByName(new ResourceLocation(modId, name + "_planks"));

            if (!removeSawRecipes) return;
            ModHandler.removeRecipeByName(new ResourceLocation(modId, name + "_planks_saw"));
        }
    }
}
