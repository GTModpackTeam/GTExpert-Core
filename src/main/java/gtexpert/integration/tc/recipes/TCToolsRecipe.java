package gtexpert.integration.tc.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.common.ConfigHolder;

import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.Mods;
import gtexpert.integration.tc.TCConfigHolder;

public class TCToolsRecipe {

    public static void init() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && TCConfigHolder.hardToolRecipes) {
            // Dark Helm
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumhelm"));
            ModHandler.addShapedRecipe(true, "thaumium_helm",
                    Mods.Thaumcraft.getItem("thaumium_helm"),
                    "PPP", "PhP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium));

            // Dark Chest
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumchest"));
            ModHandler.addShapedRecipe(true, "thaumium_chest",
                    Mods.Thaumcraft.getItem("thaumium_chest"),
                    "PhP", "PPP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium));

            // Dark Leggings
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumlegs"));
            ModHandler.addShapedRecipe(true, "thaumium_legs",
                    Mods.Thaumcraft.getItem("thaumium_legs"),
                    "PPP", "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium));

            // Dark Boots
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumboots"));
            ModHandler.addShapedRecipe(true, "thaumium_boots",
                    Mods.Thaumcraft.getItem("thaumium_boots"),
                    "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium));

            // Void Helm
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidhelm"));
            ModHandler.addShapedRecipe(true, "void_helm",
                    Mods.Thaumcraft.getItem("void_helm"),
                    "PPP", "PhP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal));

            // Void Chest
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidchest"));
            ModHandler.addShapedRecipe(true, "void_chest",
                    Mods.Thaumcraft.getItem("void_chest"),
                    "PhP", "PPP", "PPP",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal));

            // Void Leggings
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidlegs"));
            ModHandler.addShapedRecipe(true, "void_legs",
                    Mods.Thaumcraft.getItem("void_legs"),
                    "PPP", "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal));

            // Void Boots
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidboots"));
            ModHandler.addShapedRecipe(true, "void_boots",
                    Mods.Thaumcraft.getItem("void_boots"),
                    "PhP", "P P",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal));

            // Thaumium Axe
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumaxe"));
            ModHandler.addShapedRecipe(true, "thaumium_axe",
                    Mods.Thaumcraft.getItem("thaumium_axe"),
                    "PIf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Thaumium Hoe
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumhoe"));
            ModHandler.addShapedRecipe(true, "thaumium_hoe",
                    Mods.Thaumcraft.getItem("thaumium_hoe"),
                    "PIf", "hS ", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Thaumium Pickaxe
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumpick"));
            ModHandler.addShapedRecipe(true, "thaumium_pick",
                    Mods.Thaumcraft.getItem("thaumium_pick"),
                    "PII", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Thaumium Shovel
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumshovel"));
            ModHandler.addShapedRecipe(true, "thaumium_shovel",
                    Mods.Thaumcraft.getItem("thaumium_shovel"),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Thaumium Sword
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("thaumiumsword"));
            ModHandler.addShapedRecipe(true, "thaumium_sword",
                    Mods.Thaumcraft.getItem("thaumium_sword"),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.Thaumium),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Axe
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidaxe"));
            ModHandler.addShapedRecipe(true, "void_axe",
                    Mods.Thaumcraft.getItem("void_axe"),
                    "PIf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Hoe
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidhoe"));
            ModHandler.addShapedRecipe(true, "void_hoe",
                    Mods.Thaumcraft.getItem("void_hoe"),
                    "PIf", "hS ", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Pickaxe
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidpick"));
            ModHandler.addShapedRecipe(true, "void_pick",
                    Mods.Thaumcraft.getItem("void_pick"),
                    "PII", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'I', OreDictUnifier.get(ingot, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Shovel
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidshovel"));
            ModHandler.addShapedRecipe(true, "void_shovel",
                    Mods.Thaumcraft.getItem("void_shovel"),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Void Sword
            ModHandler.removeRecipeByName(Mods.Thaumcraft.getResource("voidsword"));
            ModHandler.addShapedRecipe(true, "void_sword",
                    Mods.Thaumcraft.getItem("void_sword"),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, GTEMaterials.VoidMetal),
                    'S', OreDictUnifier.get(stick, Materials.Wood));
        }
    }
}
