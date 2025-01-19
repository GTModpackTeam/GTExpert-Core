package com.github.gtexpert.core.integration.deda.loaders;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.api.util.GTEUtility;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.common.blocks.GTEBlockMetalCasing;
import com.github.gtexpert.core.common.blocks.GTEMetaBlocks;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;

public class DEDAMaterialInfoLoader {

    public static void init() {
        GTEUtility.registerOre(new ItemStack(Blocks.DRAGON_EGG), GTEMaterials.Dragon, GTValues.M * 8);
        GTEUtility.registerOre(Mods.DraconicEvolution.getItem("chaos_shard", 1, 1), GTEMaterials.Chaos,
                GTValues.M);
        GTEUtility.registerOre(
                GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING),
                GTEMaterials.Draconium,
                (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft);
        GTEUtility.registerOre(
                GTEMetaBlocks.GTE_METAL_CASING
                        .getItemVariant(GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING),
                GTEMaterials.AwakenedDraconium,
                (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft);
        GTEUtility.registerOre(
                GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING),
                new ItemMaterialInfo(
                        new MaterialStack(GTEMaterials.Draconium, GTValues.M * 9),
                        new MaterialStack(Materials.Tritanium, GTValues.M * 2)));
    }
}
