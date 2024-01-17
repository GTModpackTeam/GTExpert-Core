package gtexpert.integration.tc.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.stick;
import static gtexpert.common.GTEConfigHolder.tcIntegration;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.common.ConfigHolder;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;

public class TCToolsRecipe {

    public static void init() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && tcIntegration.hardToolRecipes) {
            // Dark Helm
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumhelm"));
            ModHandler.addShapedRecipe(true, "thaumium_helm",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "thaumium_helm"),
                    "PPP", "PhP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium));

            // Dark Chest
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumchest"));
            ModHandler.addShapedRecipe(true, "thaumium_chest",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "thaumium_chest"),
                    "PhP", "PPP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium));

            // Dark Leggings
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumlegs"));
            ModHandler.addShapedRecipe(true, "thaumium_legs",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "thaumium_legs"),
                    "PPP", "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium));

            // Dark Boots
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumboots"));
            ModHandler.addShapedRecipe(true, "thaumium_boots",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "thaumium_boots"),
                    "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium));

            // Void Helm
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidhelm"));
            ModHandler.addShapedRecipe(true, "void_helm",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "void_helm"),
                    "PPP", "PhP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal));

            // Void Chest
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidchest"));
            ModHandler.addShapedRecipe(true, "void_chest",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "void_chest"),
                    "PhP", "PPP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal));

            // Void Leggings
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidlegs"));
            ModHandler.addShapedRecipe(true, "void_legs",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "void_legs"),
                    "PPP", "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal));

            // Void Boots
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidboots"));
            ModHandler.addShapedRecipe(true, "void_boots",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "void_boots"),
                    "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal));

            // Thaumium Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumaxe"));
            ModHandler.addShapedRecipe(true, "thaumium_axe",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "thaumium_axe"),
                    "PIf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Thaumium Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumhoe"));
            ModHandler.addShapedRecipe(true, "thaumium_hoe",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "thaumium_hoe"),
                    "PIf", "hS ", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Thaumium Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumpick"));
            ModHandler.addShapedRecipe(true, "thaumium_pick",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "thaumium_pick"),
                    "PII", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Thaumium Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumshovel"));
            ModHandler.addShapedRecipe(true, "thaumium_shovel",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "thaumium_shovel"),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Thaumium Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "thaumiumsword"));
            ModHandler.addShapedRecipe(true, "thaumium_sword",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "thaumium_sword"),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidaxe"));
            ModHandler.addShapedRecipe(true, "void_axe",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "void_axe"),
                    "PIf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidhoe"));
            ModHandler.addShapedRecipe(true, "void_hoe",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "void_hoe"),
                    "PIf", "hS ", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidpick"));
            ModHandler.addShapedRecipe(true, "void_pick",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "void_pick"),
                    "PII", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidshovel"));
            ModHandler.addShapedRecipe(true, "void_shovel",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "void_shovel"),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_TC, "voidsword"));
            ModHandler.addShapedRecipe(true, "void_sword",
                    GTEUtility.getModItem(GTEValues.MODID_TC, "void_sword"),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));
        }
    }
}
