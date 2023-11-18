package gtexpert.loaders;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

import gtexpert.api.GTEValues;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.integration.ae.AEHelper.aeBlocks;
import static gtexpert.integration.ae.AEHelper.aeMaterials;

public class GTEOreDictionaryLoader {

    public static void init() {
        OreDictionary.registerOre("bookshelf", new ItemStack(Blocks.BOOKSHELF));

        OreDictionary.registerOre("craftStickQuartz", OreDictUnifier.get(OrePrefix.stick, Materials.NetherQuartz));
        OreDictionary.registerOre("craftStickQuartz", OreDictUnifier.get(OrePrefix.stick, Materials.CertusQuartz));
        OreDictionary.registerOre("craftStickQuartz", OreDictUnifier.get(OrePrefix.stick, Materials.Quartzite));

        OreDictionary.registerOre("craftNetherQuartz", OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz));
        OreDictionary.registerOre("craftNetherQuartz", aeMaterials.purifiedNetherQuartzCrystal().maybeStack(1).get());

        OreDictionary.registerOre("craftCertusQuartz", OreDictUnifier.get(OrePrefix.gem, Materials.CertusQuartz));
        OreDictionary.registerOre("craftCertusQuartz", aeMaterials.certusQuartzCrystal().maybeStack(1).get());
        OreDictionary.registerOre("craftCertusQuartz", aeMaterials.purifiedCertusQuartzCrystal().maybeStack(1).get());

        OreDictionary.registerOre("craftFluix", aeMaterials.fluixCrystal().maybeStack(1).get());
        OreDictionary.registerOre("craftFluix", aeMaterials.purifiedFluixCrystal().maybeStack(1).get());

        OreDictionary.registerOre("gemCertusQuartz", aeMaterials.certusQuartzCrystal().maybeStack(1).get());
        OreDictionary.registerOre("gemChargedCertusQuartz",
                aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());

        OreDictionary.registerOre("crystalChargedCertusQuartz",
                aeMaterials.certusQuartzCrystalCharged().maybeStack(1).get());

        OreDictionary.registerOre("blockFluix", aeBlocks.fluixBlock().maybeStack(1).get());
        OreDictionary.registerOre("gemFluix", aeMaterials.fluixCrystal().maybeStack(1).get());

        List<ItemStack> craftGlassCable = new LinkedList<>();
        IntStream.rangeClosed(0, 16).forEach(i -> {
            craftGlassCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftGlassCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftGlassCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftGlassCableColors", stack);
            }
        }

        List<ItemStack> craftCoveredCable = new LinkedList<>();
        IntStream.rangeClosed(20, 36).forEach(i -> {
            craftCoveredCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftCoveredCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftCoveredCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftCoveredCableColors", stack);
            }
        }

        List<ItemStack> craftSmartCable = new LinkedList<>();
        IntStream.rangeClosed(40, 56).forEach(i -> {
            craftSmartCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftSmartCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftSmartCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftSmartCableColors", stack);
            }
        }

        List<ItemStack> craftDenseCoveredCable = new LinkedList<>();
        IntStream.rangeClosed(500, 516).forEach(i -> {
            craftDenseCoveredCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        {
            Iterator<ItemStack> iterator = craftDenseCoveredCable.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                OreDictionary.registerOre("craftDenseCoveredCable", stack);

                if (iterator.hasNext()) OreDictionary.registerOre("craftDenseCoveredCableColors", stack);
            }
        }

        List<ItemStack> craftDenseSmartCable = new LinkedList<>();
        IntStream.rangeClosed(60, 76).forEach(i -> {
            craftDenseSmartCable.add(getModItem(GTEValues.MODID_AE, "part", 1, i));
            ModHandler.removeRecipeByOutput(getModItem(GTEValues.MODID_AE, "part", 8, i));
        });
        Iterator<ItemStack> iterator = craftDenseSmartCable.iterator();
        while (iterator.hasNext()) {
            ItemStack stack = iterator.next();
            OreDictionary.registerOre("craftDenseSmartCable", stack);

            if (iterator.hasNext()) OreDictionary.registerOre("craftDenseSmartCableColors", stack);
        }

        OreDictionary.registerOre("craftInterfaceItem", getModItem(GTEValues.MODID_AE, "interface", 1, 0));
        OreDictionary.registerOre("craftInterfaceFluid", getModItem(GTEValues.MODID_AE, "fluid_interface", 1, 0));
        OreDictionary.registerOre("craftInterfaceItem", getModItem(GTEValues.MODID_AE, "part", 1, 440));
        OreDictionary.registerOre("craftInterfaceFluid", getModItem(GTEValues.MODID_AE, "part", 1, 441));

        if (Loader.isModLoaded(GTEValues.MODID_AEFC)) {
            OreDictionary.registerOre("craftInterfaceDual",
                    getModItem(GTEValues.MODID_AEFC, "dual_interface", 1, 0));
            OreDictionary.registerOre("craftInterfaceDual",
                    getModItem(GTEValues.MODID_AEFC, "part_dual_interface", 1, 0));
        }

        if (GTEValues.isModLoadedDEDA()) {
            OreDictionary.registerOre("blockDraconium", getModItem(GTEValues.MODID_DE, "draconium_block", 1, 0));
            OreDictionary.registerOre("blockAwakenedDraconium", getModItem(GTEValues.MODID_DE, "draconic_block", 1, 0));
        }
    }
}
