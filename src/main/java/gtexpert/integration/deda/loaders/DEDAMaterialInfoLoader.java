package gtexpert.integration.deda.loaders;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;

import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.Mods;
import gtexpert.common.blocks.GTEBlockMetalCasing;
import gtexpert.common.blocks.GTEMetaBlocks;
import gtexpert.core.GTUtil;

public class DEDAMaterialInfoLoader {

    public static void init() {
        GTUtil.registerOre(new ItemStack(Blocks.DRAGON_EGG), GTEMaterials.Dragon, GTValues.M * 8);
        GTUtil.registerOre(Mods.DraconicEvolution.getItem("chaos_shard", 1, 1), GTEMaterials.Chaos,
                GTValues.M);
        GTUtil.registerOre(
                GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING),
                GTEMaterials.Draconium,
                (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft);
        GTUtil.registerOre(
                GTEMetaBlocks.GTE_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING),
                GTEMaterials.AwakenedDraconium,
                (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft);
        GTUtil.registerOre(
                GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING),
                new ItemMaterialInfo(
                        new MaterialStack(GTEMaterials.Draconium, GTValues.M * 9),
                        new MaterialStack(Materials.Tritanium, GTValues.M * 2)));
    }
}
