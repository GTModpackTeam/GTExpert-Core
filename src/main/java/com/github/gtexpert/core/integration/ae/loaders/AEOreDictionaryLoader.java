package com.github.gtexpert.core.integration.ae.loaders;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

import com.github.gtexpert.core.api.util.GTEUtility;
import com.github.gtexpert.core.api.util.Mods;

public class AEOreDictionaryLoader {

    public static void init() {
        GTEUtility.registerOre(
                "blockCertus",
                Mods.AppliedEnergistics2.getItem("quartz_block"),
                Mods.AppliedEnergistics2.getItem("quartz_pillar"),
                Mods.AppliedEnergistics2.getItem("chiseled_quartz_block"),
                Mods.AppliedEnergistics2.getItem("fluix_block"));

        GTEUtility.registerOre(
                "craftStickQuartz",
                OreDictUnifier.get(OrePrefix.stick, Materials.NetherQuartz),
                OreDictUnifier.get(OrePrefix.stick, Materials.CertusQuartz),
                OreDictUnifier.get(OrePrefix.stick, Materials.Quartzite));

        GTEUtility.registerOre(
                "craftNetherQuartz",
                OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz),
                Mods.AppliedEnergistics2.getItem("material", 1, 11));

        GTEUtility.registerOre("craftCertusQuartz",
                OreDictUnifier.get(OrePrefix.gem, Materials.CertusQuartz),
                Mods.AppliedEnergistics2.getItem("material"),
                Mods.AppliedEnergistics2.getItem("material", 1, 10));

        GTEUtility.registerOre("craftFluix",
                Mods.AppliedEnergistics2.getItem("material", 1, 7),
                Mods.AppliedEnergistics2.getItem("material", 1, 12));

        OreDictionary.registerOre("gemCertusQuartz",
                Mods.AppliedEnergistics2.getItem("material"));
        OreDictionary.registerOre("gemChargedCertusQuartz",
                Mods.AppliedEnergistics2.getItem("material", 1, 1));
        OreDictionary.registerOre("gemFluix",
                Mods.AppliedEnergistics2.getItem("material", 1, 7));

        OreDictionary.registerOre("crystalChargedCertusQuartz",
                Mods.AppliedEnergistics2.getItem("material", 1, 1));

        GTEUtility.registerOre("craftInterfaceItem",
                Mods.AppliedEnergistics2.getItem("interface"),
                Mods.AppliedEnergistics2.getItem("part", 1, 440));

        GTEUtility.registerOre("craftInterfaceFluid",
                Mods.AppliedEnergistics2.getItem("fluid_interface"),
                Mods.AppliedEnergistics2.getItem("part", 1, 441));

        GTEUtility.registerOre("dustSkyStone",
                Mods.AppliedEnergistics2.getItem("material", 1, 45));

        // Register AE2 cable's dictionaries
        registerAECableDict(0, 16, "craftGlassCable");
        registerAECableDict(20, 36, "craftCoveredCable");
        registerAECableDict(40, 56, "craftSmartCable");
        registerAECableDict(60, 76, "craftDenseSmartCable");
        registerAECableDict(500, 516, "craftDenseCoveredCable");

        if (Mods.AE2FluidCrafting.isModLoaded()) {
            GTEUtility.registerOre("craftInterfaceDual",
                    Mods.AE2FluidCrafting.getItem("dual_interface"),
                    Mods.AE2FluidCrafting.getItem("part_dual_interface"));
        }
    }

    private static void registerAECableDict(int startMeta, int endMeta, String dictName) {
        for (int i = startMeta; i <= endMeta; i++) {
            ItemStack stack = Mods.AppliedEnergistics2.getItem("part", 1, i);
            ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("part", 8, i));
            OreDictionary.registerOre(dictName, stack);
            if (i != endMeta) {
                OreDictionary.registerOre(dictName + "Colors", stack);
            }
        }
    }
}
