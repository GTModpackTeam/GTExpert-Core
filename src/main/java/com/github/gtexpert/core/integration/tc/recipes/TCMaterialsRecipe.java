package com.github.gtexpert.core.integration.tc.recipes;

import com.github.gtexpert.core.api.util.Mods;

import gregtech.api.recipes.ModHandler;
import gregtech.common.ConfigHolder;

public class TCMaterialsRecipe {

    public static void init() {
        // Brass Palte
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("brassplate"));

        // Iron Plate
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("ironplate"));

        // Thaumium Plate
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumplate"));

        // Void Plate
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidplate"));
    }

    public static void remove() {
        if (ConfigHolder.recipes.disableManualCompression) {
            // Brass Ingot
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("nuggetatobrass"));
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("brassblocktoingots"));

            // Brass Nugget
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("brasstonuggets"));

            // Brass Block
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("brassingotstoblock"));

            // Thaumium Ingot
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("nuggetatothaumium"));
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumblocktoingots"));

            // Thaumium Nugget
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumtonuggets"));

            // Thaumium Block
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumingotstoblock"));

            // Void Ingot
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("nuggetatovoid"));
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidblocktoingots"));

            // Void Nugget
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidtonuggets"));

            // Void Block
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidingotstoblock"));
        }
    }
}
