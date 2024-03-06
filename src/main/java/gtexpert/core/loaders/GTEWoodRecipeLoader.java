package gtexpert.core.loaders;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.Mods;
import gtexpert.core.GTEConfigHolder;
import gtexpert.core.GTERecipeMaps;

public class GTEWoodRecipeLoader {

    public static void recipeSawmill(@NotNull ItemStack logName, @NotNull ItemStack plankName) {
        if (logName.isEmpty()) return;

        GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(GTUtility.copy(6, logName))
                .fluidInputs(Materials.Water.getFluid(1000))
                .outputs(GTUtility.copy(48, plankName))
                .output(dust, Materials.Wood, 12)
                .duration(600).EUt(VA[LV])
                .buildAndRegister();
        GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(GTUtility.copy(6, logName))
                .fluidInputs(Materials.Water.getFluid(2500))
                .outputs(GTUtility.copy(60, plankName))
                .duration(800).EUt(VA[LV])
                .buildAndRegister();
    }

    public static void recipeCutter(@NotNull ItemStack logName, @NotNull ItemStack plankName) {
        if (logName.isEmpty()) return;

        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(logName)
                .fluidInputs(Materials.Lubricant.getFluid(1))
                .outputs(GTUtility.copy(6, plankName))
                .output(dust, Materials.Wood, 2)
                .duration(200).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(logName)
                .fluidInputs(Materials.DistilledWater.getFluid(3))
                .outputs(GTUtility.copy(6, plankName))
                .output(dust, Materials.Wood, 2)
                .duration(300).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(logName)
                .fluidInputs(Materials.Water.getFluid(4))
                .outputs(GTUtility.copy(6, plankName))
                .output(dust, Materials.Wood, 2)
                .duration(400).EUt(VA[ULV])
                .buildAndRegister();
    }

    public static void addWoodRecipes(@NotNull WoodTypeEntry entry) {
        if (entry.log.isEmpty()) return;
        final String name = entry.woodName;

        int plank_normal = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 1 : 2;
        plank_normal = ConfigHolder.recipes.nerfWoodCrafting ? plank_normal : 4;
        int plank_saw = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 2 : 4;
        plank_saw = ConfigHolder.recipes.nerfWoodCrafting ? plank_saw : 6;

        ModHandler.addShapelessRecipe(name + "_planks",
                GTUtility.copy(plank_normal, entry.planks), entry.log.copy());
        ModHandler.addShapedRecipe(name + "_planks_saw",
                GTUtility.copy(plank_saw, entry.planks),
                "s", "L", 'L', entry.log.copy());
    }

    public static void removeWoodRecipes(@NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;
        final String modId = entry.modid;

        ModHandler.removeRecipeByName(new ResourceLocation(modId, name));
    }

    public static void removeGTWoodRecipes(@NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;

        ModHandler.removeRecipeByName(Mods.GregTech.getResource(name + "_planks"));
        ModHandler.removeRecipeByName(Mods.GregTech.getResource(name + "_planks_saw"));
    }
}
