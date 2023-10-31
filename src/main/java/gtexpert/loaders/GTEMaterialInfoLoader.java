package gtexpert.loaders;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;

import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.GTValues.M;

public class GTEMaterialInfoLoader {

    public static void init() {
        OreDictUnifier.registerOre(
                GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.TreatedWood, (M * 8) / ConfigHolder.recipes.casingsPerCraft)));

        OreDictUnifier.registerOre(
                GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING),
                new ItemMaterialInfo(
                        new MaterialStack(GTEMaterials.Draconium, (M * 8) / ConfigHolder.recipes.casingsPerCraft)));

        OreDictUnifier.registerOre(
                GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING),
                new ItemMaterialInfo(new MaterialStack(GTEMaterials.AwakenedDraconium,
                        (M * 8) / ConfigHolder.recipes.casingsPerCraft)));

        OreDictUnifier.registerOre(new ItemStack(Blocks.SOUL_SAND), OrePrefix.dust, GTEMaterials.SoulSand);
        OreDictUnifier.registerOre(new ItemStack(Items.CHORUS_FRUIT), OrePrefix.dust, GTEMaterials.ChorusFruit);
    }
}
