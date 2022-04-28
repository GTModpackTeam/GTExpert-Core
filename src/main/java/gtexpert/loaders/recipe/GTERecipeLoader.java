package gtexpert.loaders.recipe;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gtexpert.common.GTEMetalCasing;
import gtexpert.common.ModBlocks;

import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;

public class GTERecipeLoader {

    public static void init() {

        // Galvalume Dust
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Steel, 4)
                .input(dust, Zinc, 1)
                .input(dust, Aluminium, 1)
                .notConsumable(new IntCircuitIngredient(1))
                .output(dust, Galvalume, 6)
                .duration(50).EUt(VA[LV]).buildAndRegister();

        //Greenhouse Casing
        ModHandler.addShapedRecipe("gte_metal_casing",ModBlocks.gteMetalCasing.getItemVariant(GTEMetalCasing.MetalCasingType.Greenhouse) ,
                "PhP", "PFP", "PwP", 'P',new UnificationEntry(plate, Galvalume),'F',new UnificationEntry(frameGt, Galvalume));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).input(OrePrefix.plate, Galvalume, 6).input(OrePrefix.frameGt, Galvalume, 1).circuitMeta(6).outputs(ModBlocks.gteMetalCasing.getItemVariant(GTEMetalCasing.MetalCasingType.Greenhouse, 2)).duration(50).buildAndRegister();

    }
}
