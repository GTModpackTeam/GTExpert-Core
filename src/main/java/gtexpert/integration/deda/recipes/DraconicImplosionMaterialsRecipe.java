package gtexpert.integration.deda.recipes;

import static gregtech.api.unification.ore.OrePrefix.block;

import com.brandon3055.draconicevolution.DEFeatures;
import com.github.gtexpert.inb.api.recipes.INBRecipeMaps;

import gtexpert.api.unification.material.GTEMaterials;

public class DraconicImplosionMaterialsRecipe {

    public static void init() {
        INBRecipeMaps.ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, GTEMaterials.Draconium, 4)
                .output(block, GTEMaterials.AwakenedDraconium, 3)
                .buildAndRegister();
    }
}
