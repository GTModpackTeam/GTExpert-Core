package gtexpert.integration.ae.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;
import static gtexpert.integration.ae.AEUtil.aeItems;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.common.ConfigHolder;

import gtexpert.api.GTEValues;
import gtexpert.integration.ae.AEConfig;

public class AEToolsRecipe {

    public static void init() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && AEConfig.hardToolRecipes) {
            // Nether Quartz Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_axe"));
            ModHandler.addShapedRecipe(true, "nether_quartz_axe",
                    aeItems.netherQuartzAxe().maybeStack(1).get(),
                    "PQf", "PS ", "hS ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_hoe"));
            ModHandler.addShapedRecipe(true, "nether_quartz_hoe",
                    aeItems.netherQuartzHoe().maybeStack(1).get(),
                    "PQf", "hS ", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_pickaxe"));
            ModHandler.addShapedRecipe(true, "nether_quartz_pickaxe",
                    aeItems.netherQuartzPick().maybeStack(1).get(),
                    "PQQ", "hSf", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_spade"));
            ModHandler.addShapedRecipe(true, "nether_quartz_spade",
                    aeItems.netherQuartzShovel().maybeStack(1).get(),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_sword"));
            ModHandler.addShapedRecipe(true, "nether_quartz_sword",
                    aeItems.netherQuartzSword().maybeStack(1).get(),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Cutting Knife
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_cutting_knife"));
            ModHandler.addShapedRecipe(true, "nether_quartz_cutting_knife",
                    aeItems.netherQuartzKnife().maybeStack(1).get(),
                    "fPh", "QSQ", " S ",
                    'Q', OreDictUnifier.get(gem, Materials.NetherQuartz),
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Nether Quartz Wrench
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/nether_quartz_wrench"));
            ModHandler.addShapedRecipe(true, "ether_quartz_wrench",
                    aeItems.netherQuartzWrench().maybeStack(1).get(),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, Materials.NetherQuartz));

            // Certus Quartz Axe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_axe"));
            ModHandler.addShapedRecipe(true, "certus_quartz_axe",
                    aeItems.certusQuartzAxe().maybeStack(1).get(),
                    "PQf", "PS ", "hS ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Hoe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_hoe"));
            ModHandler.addShapedRecipe(true, "certus_quartz_hoe",
                    aeItems.certusQuartzHoe().maybeStack(1).get(),
                    "PQf", "hS ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Pickaxe
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_pickaxe"));
            ModHandler.addShapedRecipe(true, "certus_quartz_pickaxe",
                    aeItems.certusQuartzPick().maybeStack(1).get(),
                    "PQQ", "hSf", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Shovel
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_spade"));
            ModHandler.addShapedRecipe(true, "certus_quartz_spade",
                    aeItems.certusQuartzShovel().maybeStack(1).get(),
                    "hPf", " S ", " S ",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Sword
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_sword"));
            ModHandler.addShapedRecipe(true, "certus_quartz_sword",
                    aeItems.certusQuartzSword().maybeStack(1).get(),
                    " P ", "hPf", " S ",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Cutting Knife
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_cutting_knife"));
            ModHandler.addShapedRecipe(true, "certus_quartz_cutting_knife",
                    aeItems.certusQuartzKnife().maybeStack(1).get(),
                    "fPh", "QSQ", " S ",
                    'Q', "gemCertusQuartz",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz),
                    'S', OreDictUnifier.get(stick, Materials.Wood));

            // Certus Quartz Wrench
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_AE, "tools/certus_quartz_wrench"));
            ModHandler.addShapedRecipe(true, "certus_quartz_wrench",
                    aeItems.certusQuartzWrench().maybeStack(1).get(),
                    "PhP", " P ", " P ",
                    'P', OreDictUnifier.get(plate, Materials.CertusQuartz));
        }
    }
}
