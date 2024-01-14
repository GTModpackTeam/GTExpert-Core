package gtexpert.loaders;

import net.minecraft.init.Blocks;
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
import gtexpert.api.util.GTEUtility;
import gtexpert.common.blocks.GTEBlockMetalCasing;
import gtexpert.common.blocks.GTEMetaBlocks;
import gtexpert.integration.gt.GTHelper;

public class GTEMaterialInfoLoader {

    public static void init() {
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 1),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 2),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));
        OreDictUnifier.registerOre(new ItemStack(Blocks.SKULL, 1, 4),
                new ItemMaterialInfo(new MaterialStack(Materials.Bone, GTValues.M * 4)));

        OreDictUnifier.registerOre(
                GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill),
                new ItemMaterialInfo(new MaterialStack(Materials.TreatedWood,
                        (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft)));

        // TODO Applied Energistics
        if (Loader.isModLoaded(GTEValues.MODID_AE)) {
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 9),
                    new ItemMaterialInfo(
                            new MaterialStack(Materials.EnderPearl, GTValues.M),
                            new MaterialStack(GTEMaterials.Fluix, GTValues.M)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 20),
                    new ItemMaterialInfo(new MaterialStack(Materials.Silicon, GTValues.M)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 16),
                    new ItemMaterialInfo(new MaterialStack(Materials.CertusQuartz, GTValues.M)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 18),
                    new ItemMaterialInfo(new MaterialStack(Materials.Gold, GTValues.M)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 17),
                    new ItemMaterialInfo(new MaterialStack(Materials.Diamond, GTValues.M)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 23),
                    new ItemMaterialInfo(
                            new MaterialStack(Materials.Redstone, GTValues.M),
                            new MaterialStack(Materials.Silicon, GTValues.M),
                            new MaterialStack(Materials.CertusQuartz, GTValues.M)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 22),
                    new ItemMaterialInfo(
                            new MaterialStack(Materials.Redstone, GTValues.M),
                            new MaterialStack(Materials.Silicon, GTValues.M),
                            new MaterialStack(Materials.Gold, GTValues.M)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_AE, "material", 1, 24),
                    new ItemMaterialInfo(
                            new MaterialStack(Materials.Redstone, GTValues.M),
                            new MaterialStack(Materials.Silicon, GTValues.M),
                            new MaterialStack(Materials.Diamond, GTValues.M)));
        }

        // TODO Ender IO Module
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_EIO, "item_soul_vial"),
                    new ItemMaterialInfo(
                            new MaterialStack(GTEMaterials.Soularium, GTValues.M),
                            new MaterialStack(Materials.Glass, GTValues.M * 3)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_EIO, "block_dark_iron_bars", 8, 0),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, (GTValues.M * 3) / 16)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_EIO, "block_dark_iron_bars", 8, 0),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, (GTValues.M * 3) / 16)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_EIO, "block_dark_steel_trapdoor"),
                    new ItemMaterialInfo(new MaterialStack(Materials.Iron, GTValues.M * 4)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_EIO, "block_dark_steel_anvil"),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 31)));
            OreDictUnifier.registerOre((GTEUtility.getModItem(GTEValues.MODID_EIO, "block_dark_steel_anvil", 1, 1)),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 22)));
            OreDictUnifier.registerOre((GTEUtility.getModItem(GTEValues.MODID_EIO, "block_dark_steel_anvil", 1, 2)),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 13)));
            OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_EIO, "block_dark_steel_ladder"),
                    new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M)));

            if (ConfigHolder.recipes.hardAdvancedIronRecipes) {
                OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_EIO, "block_dark_steel_door"),
                        new ItemMaterialInfo(
                                new MaterialStack(GTEMaterials.DarkSteel, (GTValues.M * 4) + ((GTValues.M * 3) / 16)),
                                new MaterialStack(GTEMaterials.DarkSteel, GTValues.M / 9)));
            } else {
                OreDictUnifier.registerOre(GTEUtility.getModItem(GTEValues.MODID_EIO, "block_dark_steel_door"),
                        new ItemMaterialInfo(new MaterialStack(GTEMaterials.DarkSteel, GTValues.M * 2)));
            }
        }

        // TODO Draconic Evolution Module
        if (GTEValues.isModLoadedDEDA()) {
            GTHelper.registerOre(new ItemStack(Blocks.DRAGON_EGG), GTEMaterials.Dragon, GTValues.M * 8);
            GTHelper.registerOre(GTEUtility.getModItem(GTEValues.MODID_DE, "chaos_shard", 1, 1), GTEMaterials.Chaos,
                    GTValues.M);
            GTHelper.registerOre(
                    GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING),
                    GTEMaterials.Draconium,
                    (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft);
            GTHelper.registerOre(
                    GTEMetaBlocks.GTE_METAL_CASING
                            .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING),
                    GTEMaterials.AwakenedDraconium,
                    (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft);
            GTHelper.registerOre(
                    GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING),
                    new ItemMaterialInfo(
                            new MaterialStack(GTEMaterials.Draconium, GTValues.M * 9),
                            new MaterialStack(Materials.Tritanium, GTValues.M * 2)));
        }
    }
}
