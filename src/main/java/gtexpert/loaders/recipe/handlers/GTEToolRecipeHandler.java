package gtexpert.loaders.recipe.handlers;

import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.loaders.recipe.handlers.ToolRecipeHandler.addElectricToolRecipe;

import gregtech.api.items.toolitem.IGTTool;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;

import gtexpert.common.items.GTEToolItems;

public class GTEToolRecipeHandler {

    public static void register() {
        OrePrefix.plate.addProcessingHandler(PropertyKey.TOOL, GTEToolRecipeHandler::processElectricTool);
    }

    private static void processElectricTool(OrePrefix prefix, Material material, ToolProperty property) {
        OrePrefix toolPrefix;

        if (material.hasFlag(GENERATE_PLATE)) {
            // Chainsaw
            toolPrefix = OrePrefix.toolHeadChainsaw;
            addElectricToolRecipe(toolPrefix, material, new IGTTool[] {
                    GTEToolItems.CHAINSAW_HV, GTEToolItems.CHAINSAW_IV
            });
        }
    }
}
