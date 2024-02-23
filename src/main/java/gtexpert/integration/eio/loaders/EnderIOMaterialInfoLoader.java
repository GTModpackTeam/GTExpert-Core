package gtexpert.integration.eio.loaders;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;

import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;

public class EnderIOMaterialInfoLoader {

    public static void init() {
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 1),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 2),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 4),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));

        OreDictUnifier.registerOre(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_soul_vial"),
                new ItemMaterialInfo(
                        new MaterialStack(GTEMaterials.Soularium, GTValues.M),
                        new MaterialStack(Materials.Glass, GTValues.M * 3)));
        OreDictUnifier.registerOre(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_iron_bars", 8, 0),
                new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, (GTValues.M * 3) / 16)));
        OreDictUnifier.registerOre(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_iron_bars", 8, 0),
                new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, (GTValues.M * 3) / 16)));
        OreDictUnifier.registerOre(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_trapdoor"),
                new ItemMaterialInfo(new MaterialStack(Materials.Iron, GTValues.M * 4)));
        OreDictUnifier.registerOre(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_anvil"),
                new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 31)));
        OreDictUnifier.registerOre((GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_anvil", 1, 1)),
                new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 22)));
        OreDictUnifier.registerOre((GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_anvil", 1, 2)),
                new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 13)));
        OreDictUnifier.registerOre(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_ladder"),
                new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M)));

        if (ConfigHolder.recipes.hardAdvancedIronRecipes) {
            OreDictUnifier.registerOre(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_door"),
                    new ItemMaterialInfo(
                            new MaterialStack(GTEMaterials.DarkSteel, (GTValues.M * 4) + ((GTValues.M * 3) / 16)),
                            new MaterialStack(GTEMaterials.DarkSteel, GTValues.M / 9)));
        } else {
            OreDictUnifier.registerOre(GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_dark_steel_door"),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 2)));
        }
    }
}
