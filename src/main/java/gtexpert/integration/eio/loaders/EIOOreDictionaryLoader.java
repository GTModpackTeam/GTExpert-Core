package gtexpert.integration.eio.loaders;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;

import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;

import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.base.material.material.Material;
import crazypants.enderio.endergy.init.EndergyObject;

public class EIOOreDictionaryLoader {

    public static void init() {
        OreDictUnifier.registerOre(
                new ItemStack(ModObject.blockEndermanSkull.getItemNN()),
                new ItemMaterialInfo(
                        new MaterialStack(Materials.EnderPearl, 2),
                        new MaterialStack(GTEMaterials.ArtificialBone, 8)));

        // Gears
        OreDictionary.registerOre("gearEnergeticAlloy", Material.GEAR_ENERGIZED.getStack());
        OreDictionary.registerOre("gearVibrantAlloy", Material.GEAR_VIBRANT.getStack());

        // craftNutrientDistillation
        GTEUtility.registerOre(
                "craftNutrientDistillation",
                new ItemStack(Items.PORKCHOP),
                new ItemStack(Items.BEEF),
                new ItemStack(Items.CHICKEN),
                new ItemStack(Items.RABBIT),
                new ItemStack(Items.MUTTON),
                new ItemStack(Items.FISH),
                new ItemStack(Items.FISH, 1, 1),
                new ItemStack(Items.FISH, 1, 2));

        // craftHootch
        GTEUtility.registerOre(
                "craftHootch",
                new ItemStack(Items.WHEAT_SEEDS),
                new ItemStack(Items.DYE, 1, 3),
                new ItemStack(Items.BEETROOT_SEEDS),
                new ItemStack(Items.PUMPKIN_SEEDS),
                new ItemStack(Items.MELON_SEEDS),
                new ItemStack(Items.POISONOUS_POTATO));

        // eio.capacitor
        GTEUtility.registerOre(
                "craftCapacitorEIO",
                new ItemStack(ModObject.itemBasicCapacitor.getItemNN()),
                new ItemStack(EndergyObject.itemCapacitorSilver.getItemNN(), 1));
    }
}
