package gtexpert.integration.gendustry;

import gregtech.api.recipes.RecipeMap;
import gregtech.core.sound.GTSoundEvents;

import gtexpert.integration.gendustry.recipes.builders.IndustrialApiaryRecipeBuilder;
import gtexpert.integration.gendustry.recipes.machines.RecipeMapIndustrialApiary;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenExpansion("mods.gregtech.recipe.RecipeMaps")
@ZenRegister
public class GendustryRecipeMaps {

    @ZenProperty
    public static final RecipeMap<IndustrialApiaryRecipeBuilder> INDUSTRIAL_APIARY_RECIPES = new RecipeMapIndustrialApiary<>(
            "industrial_apiary", 2, 9, 0, 0, new IndustrialApiaryRecipeBuilder(), false)
                    .setSound(GTSoundEvents.ARC);
}
