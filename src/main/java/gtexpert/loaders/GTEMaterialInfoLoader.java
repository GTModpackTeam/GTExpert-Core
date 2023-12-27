package gtexpert.loaders;

import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.blocks.GTEBlockMetalCasing.MetalCasingType.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.common.blocks.GTEMetaBlocks;

public class GTEMaterialInfoLoader {

    public static void init() {
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 0),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 1),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 2),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 4),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));

        OreDictUnifier.registerOre(GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(SAWMill),
                new ItemMaterialInfo(new MaterialStack(Materials.TreatedWood,
                        (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft)));

        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_EIO, "item_soul_vial", 1, 0),
                    new ItemMaterialInfo(
                            new MaterialStack(GTEMaterials.Soularium, GTValues.M),
                            new MaterialStack(Materials.Glass, GTValues.M * 3)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_EIO, "block_dark_iron_bars", 8, 0),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, (GTValues.M * 3) / 16)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_EIO, "block_dark_iron_bars", 8, 0),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, (GTValues.M * 3) / 16)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_EIO, "block_dark_steel_trapdoor", 1, 0),
                    new ItemMaterialInfo(new MaterialStack(Materials.Iron, GTValues.M * 4)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_EIO, "block_dark_steel_anvil", 1, 0),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 31)));
            OreDictUnifier.registerOre((getModItem(GTEValues.MODID_EIO, "block_dark_steel_anvil", 1, 1)),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 22)));
            OreDictUnifier.registerOre((getModItem(GTEValues.MODID_EIO, "block_dark_steel_anvil", 1, 2)),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 13)));
            if (ConfigHolder.recipes.hardAdvancedIronRecipes) {
                OreDictUnifier.registerOre(getModItem(GTEValues.MODID_EIO, "block_dark_steel_door", 1, 0),
                        new ItemMaterialInfo(
                                new MaterialStack(GTEMaterials.DarkSteel, (GTValues.M * 4) + ((GTValues.M * 3) / 16)),
                                new MaterialStack(GTEMaterials.DarkSteel, GTValues.M / 9)));
            } else {
                OreDictUnifier.registerOre(getModItem(GTEValues.MODID_EIO, "block_dark_steel_door", 1, 0),
                        new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 2)));
            }
        }

        if (GTEValues.isModLoadedDEDA()) {
            OreDictUnifier.registerOre(new ItemStack(Blocks.DRAGON_EGG),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.Dragon, GTValues.M * 8)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_DE, "chaos_shard", 1, 1),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.Chaos, GTValues.M)));
            OreDictUnifier.registerOre(GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(DRACONIUM_CASING),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.Draconium,
                            (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft)));
            OreDictUnifier.registerOre(GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(AWAKENED_DRACONIUM_CASING),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.AwakenedDraconium,
                            (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft)));
        }
    }
}
