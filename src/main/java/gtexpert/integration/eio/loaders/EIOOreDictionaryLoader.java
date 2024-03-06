package gtexpert.integration.eio.loaders;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.base.material.material.Material;
import crazypants.enderio.endergy.init.EndergyObject;

public class EIOOreDictionaryLoader {

    public static void init() {
        // Gears
        OreDictionary.registerOre("gearEnergeticAlloy", Material.GEAR_ENERGIZED.getStack());
        OreDictionary.registerOre("gearVibrantAlloy", Material.GEAR_VIBRANT.getStack());

        // craftNutrientDistillation
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.PORKCHOP));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.BEEF));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.CHICKEN));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.RABBIT));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.MUTTON));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.FISH));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.FISH, 1, 1));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.FISH, 1, 2));

        // craftHootch
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.WHEAT_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.DYE, 1, 3));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.BEETROOT_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.PUMPKIN_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.MELON_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.POISONOUS_POTATO));

        // eio.capacitor
        OreDictionary.registerOre("craftCapacitorEIO", new ItemStack(ModObject.itemBasicCapacitor.getItemNN()));
        OreDictionary.registerOre("craftCapacitorEIO", new ItemStack(EndergyObject.itemCapacitorSilver.getItemNN(), 1));
    }
}
