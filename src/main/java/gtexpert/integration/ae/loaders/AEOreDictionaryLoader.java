package gtexpert.integration.ae.loaders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

import gtexpert.api.util.Mods;

public class AEOreDictionaryLoader {

    public static void init() {
        OreDictionary.registerOre("blockCertus",
                Mods.AppliedEnergistics2.getItem("quartz_block"));
        OreDictionary.registerOre("blockCertus",
                Mods.AppliedEnergistics2.getItem("quartz_pillar"));
        OreDictionary.registerOre("blockCertus",
                Mods.AppliedEnergistics2.getItem("chiseled_quartz_block"));
        OreDictionary.registerOre("blockFluix",
                Mods.AppliedEnergistics2.getItem("fluix_block"));

        OreDictionary.registerOre("craftStickQuartz",
                OreDictUnifier.get(OrePrefix.stick, Materials.NetherQuartz));
        OreDictionary.registerOre("craftStickQuartz",
                OreDictUnifier.get(OrePrefix.stick, Materials.CertusQuartz));
        OreDictionary.registerOre("craftStickQuartz",
                OreDictUnifier.get(OrePrefix.stick, Materials.Quartzite));

        OreDictionary.registerOre("craftNetherQuartz",
                OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz));
        OreDictionary.registerOre("craftNetherQuartz",
                Mods.AppliedEnergistics2.getItem("material", 1, 11));

        OreDictionary.registerOre("craftCertusQuartz",
                OreDictUnifier.get(OrePrefix.gem, Materials.CertusQuartz));
        OreDictionary.registerOre("craftCertusQuartz",
                Mods.AppliedEnergistics2.getItem("material"));
        OreDictionary.registerOre("craftCertusQuartz",
                Mods.AppliedEnergistics2.getItem("material", 1, 10));

        OreDictionary.registerOre("craftFluix",
                Mods.AppliedEnergistics2.getItem("material", 1, 7));
        OreDictionary.registerOre("craftFluix",
                Mods.AppliedEnergistics2.getItem("material", 1, 12));

        OreDictionary.registerOre("gemCertusQuartz",
                Mods.AppliedEnergistics2.getItem("material"));
        OreDictionary.registerOre("gemChargedCertusQuartz",
                Mods.AppliedEnergistics2.getItem("material", 1, 1));
        OreDictionary.registerOre("gemFluix",
                Mods.AppliedEnergistics2.getItem("material", 1, 7));

        OreDictionary.registerOre("crystalChargedCertusQuartz",
                Mods.AppliedEnergistics2.getItem("material", 1, 1));

        List<ItemStack> craftGlassCable = new ArrayList<>();
        IntStream.rangeClosed(0, 16).forEach(i -> {
            craftGlassCable.add(Mods.AppliedEnergistics2.getItem("part", 1, i));
            ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftGlassCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftGlassCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftGlassCableColors", stack);
            }
        }

        List<ItemStack> craftCoveredCable = new ArrayList<>();
        IntStream.rangeClosed(20, 36).forEach(i -> {
            craftCoveredCable.add(Mods.AppliedEnergistics2.getItem("part", 1, i));
            ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftCoveredCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftCoveredCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftCoveredCableColors", stack);
            }
        }

        List<ItemStack> craftSmartCable = new ArrayList<>();
        IntStream.rangeClosed(40, 56).forEach(i -> {
            craftSmartCable.add(Mods.AppliedEnergistics2.getItem("part", 1, i));
            ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftSmartCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftSmartCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftSmartCableColors", stack);
            }
        }

        List<ItemStack> craftDenseCoveredCable = new ArrayList<>();
        IntStream.rangeClosed(500, 516).forEach(i -> {
            craftDenseCoveredCable.add(Mods.AppliedEnergistics2.getItem("part", 1, i));
            ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftDenseCoveredCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftDenseCoveredCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftDenseCoveredCableColors", stack);
            }
        }

        List<ItemStack> craftDenseSmartCable = new ArrayList<>();
        IntStream.rangeClosed(60, 76).forEach(i -> {
            craftDenseSmartCable.add(Mods.AppliedEnergistics2.getItem("part", 1, i));
            ModHandler.removeRecipeByOutput(Mods.AppliedEnergistics2.getItem("part", 8, i));
        });
        Iterator<ItemStack> iterator = craftDenseSmartCable.iterator();
        while (iterator.hasNext()) {
            ItemStack stack = iterator.next();
            OreDictionary.registerOre("craftDenseSmartCable", stack);

            if (iterator.hasNext()) OreDictionary.registerOre("craftDenseSmartCableColors", stack);
        }

        OreDictionary.registerOre("craftInterfaceItem",
                Mods.AppliedEnergistics2.getItem("interface"));
        OreDictionary.registerOre("craftInterfaceFluid",
                Mods.AppliedEnergistics2.getItem("fluid_interface"));
        OreDictionary.registerOre("craftInterfaceItem",
                Mods.AppliedEnergistics2.getItem("part", 1, 440));
        OreDictionary.registerOre("craftInterfaceFluid",
                Mods.AppliedEnergistics2.getItem("part", 1, 441));

        if (Mods.AE2FluidCrafting.isModLoaded()) {
            OreDictionary.registerOre("craftInterfaceDual",
                    Mods.AE2FluidCrafting.getItem("dual_interface"));
            OreDictionary.registerOre("craftInterfaceDual",
                    Mods.AE2FluidCrafting.getItem("part_dual_interface"));
        }
    }
}
