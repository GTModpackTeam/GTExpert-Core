package gtexpert.loaders;

import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.blocks.GTEBlockMetalCasing.MetalCasingType.*;

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
import gtexpert.common.blocks.GTEMetaBlocks;

public class GTEMaterialInfoLoader {

    public static void init() {
        OreDictUnifier.registerOre(GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(SAWMill),
                new ItemMaterialInfo(new MaterialStack(Materials.TreatedWood,
                        (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft)));

        if (Loader.isModLoaded(GTEValues.MODID_AE)) {
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_AE, "material", 1, 9),
                    new ItemMaterialInfo(
                            new MaterialStack(Materials.EnderPearl, GTValues.M),
                            new MaterialStack(GTEMaterials.Fluix, GTValues.M)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_AE, "material", 1, 20),
                    new ItemMaterialInfo(new MaterialStack(Materials.Silicon, GTValues.M)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_AE, "material", 1, 16),
                    new ItemMaterialInfo(new MaterialStack(Materials.CertusQuartz, GTValues.M)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_AE, "material", 1, 18),
                    new ItemMaterialInfo(new MaterialStack(Materials.Gold, GTValues.M)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_AE, "material", 1, 17),
                    new ItemMaterialInfo(new MaterialStack(Materials.Diamond, GTValues.M)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_AE, "material", 1, 23),
                    new ItemMaterialInfo(
                            new MaterialStack(Materials.Redstone, GTValues.M),
                            new MaterialStack(Materials.Silicon, GTValues.M),
                            new MaterialStack(Materials.CertusQuartz, GTValues.M)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_AE, "material", 1, 22),
                    new ItemMaterialInfo(
                            new MaterialStack(Materials.Redstone, GTValues.M),
                            new MaterialStack(Materials.Silicon, GTValues.M),
                            new MaterialStack(Materials.Gold, GTValues.M)));
            OreDictUnifier.registerOre(getModItem(GTEValues.MODID_AE, "material", 1, 24),
                    new ItemMaterialInfo(
                            new MaterialStack(Materials.Redstone, GTValues.M),
                            new MaterialStack(Materials.Silicon, GTValues.M),
                            new MaterialStack(Materials.Diamond, GTValues.M)));
        }

        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            OreDictUnifier.registerOre(new ItemStack(Blocks.SOUL_SAND), OrePrefix.dust, GTEMaterials.SoulSand);
            OreDictUnifier.registerOre(new ItemStack(Items.CHORUS_FRUIT), OrePrefix.dust, GTEMaterials.ChorusFruit);
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
            OreDictUnifier.registerOre(GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(DRACONIUM_CASING),
                    new ItemMaterialInfo(
                            new MaterialStack(GTEMaterials.Draconium, GTValues.M * 9),
                            new MaterialStack(Materials.Tritanium, GTValues.M * 2)));

            if (Loader.isModLoaded(GTEValues.MODID_EIO)) {} else {
                OreDictUnifier.registerOre(getModItem(GTEValues.MODID_DE, "infused_obsidian", 1, 0),
                        new ItemMaterialInfo(
                                new MaterialStack(Materials.Blaze, GTValues.M * 4),
                                new MaterialStack(Materials.Obsidian, GTValues.M * 4),
                                new MaterialStack(GTEMaterials.Draconium, GTValues.M)));
            }
        }
    }
}
