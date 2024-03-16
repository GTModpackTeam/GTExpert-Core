package gtexpert.core.loaders;

import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;

import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.Mods;
import gtexpert.common.blocks.GTEBlockMetalCasing;
import gtexpert.common.blocks.GTEMetaBlocks;

public class GTEMaterialInfoLoader {

    public static void init() {
        OreDictUnifier.registerOre(
                Mods.Vanilla.getItem("bone", 1, 0),
                new ItemMaterialInfo(
                        new MaterialStack(GTEMaterials.ArtificialBone, 8)));
        OreDictUnifier.registerOre(
                Mods.Vanilla.getItem("bone", 1, 1),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.Coal, 2),
                        new MaterialStack(GTEMaterials.ArtificialBone, 8)));
        OreDictUnifier.registerOre(
                Mods.Vanilla.getItem("bone", 1, 2),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.Meat, 2),
                        new MaterialStack(GTEMaterials.ArtificialBone, 4)));
        OreDictUnifier.registerOre(
                Mods.Vanilla.getItem("bone", 1, 4),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.Gunpowder, 6),
                        new MaterialStack(GTEMaterials.ArtificialBone, 8)));

        OreDictUnifier.registerOre(
                GTEMetaBlocks.GTE_METAL_CASING.getItemVariant(GTEBlockMetalCasing.MetalCasingType.SAWMill),
                new ItemMaterialInfo(new MaterialStack(Materials.TreatedWood,
                        (GTValues.M * 8) / ConfigHolder.recipes.casingsPerCraft)));
    }
}
