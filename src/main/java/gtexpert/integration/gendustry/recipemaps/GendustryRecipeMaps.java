package gtexpert.integration.gendustry.recipemaps;

import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenExpansion("mods.gregtech.recipe.RecipeMaps")
@ZenRegister
public class GendustryRecipeMaps {

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> INDUSTRIAL_APIARY_RECIPES = new RecipeMap<>(
            "industrial_apiary", 2, 9, 0, 0, new SimpleRecipeBuilder(), false)
                    .setSound(GTSoundEvents.ARC);
}
