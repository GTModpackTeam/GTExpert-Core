package gtexpert.integration.ffm.recipes;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Bronze;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;

import gtexpert.api.util.Mods;

import forestry.modules.ForestryModuleUids;

public class FFMItemRecipe {

    public static void register() {
        if (forestry.modules.ModuleHelper.isEnabled(ForestryModuleUids.CORE)) {
            itemCore();
        }
    }

    public static void itemCore() {
        // Study Casing
        ModHandler.removeRecipeByName(Mods.Forestry.getResource("sturdy_casing"));
        ModHandler.addShapedRecipe(true, "sturdy_casing", Mods.Forestry.getItem("sturdy_machine"),
                "PPP", "PwP", "PPP",
                'P', new UnificationEntry(OrePrefix.plate, Bronze));
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(OrePrefix.plate, Bronze, 8)
                .circuitMeta(8)
                .outputs(Mods.Forestry.getItem("sturdy_machine"))
                .duration(50).EUt(16).buildAndRegister();
    }
}
