package gtexpert.integration.ae.loaders;

import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;

public class AEMaterialInfoLoader {

    public static void init() {
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
}
