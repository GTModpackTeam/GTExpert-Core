package gtexpert.integration.tc.recipes;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;

import gtexpert.api.GTEValues;

public class TCMaterialsRecipe {

    public static void init() {
        // Brass Ingot
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "nuggetatobrass"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "brassblocktoingots"));

        // Brass Nugget
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "brasstonuggets"));

        // Brass Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "brassingotstoblock"));

        // Brass Palte
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "brassplate"));

        // Iron Plate
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "ironplate"));

        // Thaumium Ingot
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "nuggetatothaumium"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumblocktoingots"));

        // Thaumium Nugget
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumtonuggets"));

        // Thaumium Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumingotstoblock"));

        // Thaumium Plate
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumplate"));

        // Void Plate
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidplate"));

        // Void Ingot
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "nuggetatovoid"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidblocktoingots"));

        // Void Nugget
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidtonuggets"));

        // Void Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidingotstoblock"));
    }
}
