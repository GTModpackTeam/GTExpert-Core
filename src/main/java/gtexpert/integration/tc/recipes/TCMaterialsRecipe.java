package gtexpert.integration.tc.recipes;

import gregtech.api.recipes.ModHandler;

import gtexpert.api.util.Mods;

public class TCMaterialsRecipe {

    public static void init() {
        // Brass Ingot
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("nuggetatobrass"));
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("brassblocktoingots"));

        // Brass Nugget
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("brasstonuggets"));

        // Brass Block
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("brassingotstoblock"));

        // Brass Palte
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("brassplate"));

        // Iron Plate
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("ironplate"));

        // Thaumium Ingot
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("nuggetatothaumium"));
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumblocktoingots"));

        // Thaumium Nugget
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumtonuggets"));

        // Thaumium Block
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumingotstoblock"));

        // Thaumium Plate
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumplate"));

        // Void Plate
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidplate"));

        // Void Ingot
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("nuggetatovoid"));
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidblocktoingots"));

        // Void Nugget
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidtonuggets"));

        // Void Block
        ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidingotstoblock"));
    }
}
