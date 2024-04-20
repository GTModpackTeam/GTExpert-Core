package gtexpert.integration.ffm.recipes.machines;

import forestry.factory.MachineUIDs;
import forestry.factory.ModuleFactory;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gtexpert.api.util.Mods;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.unification.ore.OrePrefix.*;

public class CentrifugeLoader {

    public static void init() {
        if (!Mods.ForestryApiculture.isModLoaded()) return;

        ItemStack wax = Mods.Forestry.getItem("beeswax");
        ItemStack drop = Mods.Forestry.getItem("honey_drop");
        // Recipe was not added by GTCEu
        // GenDustry Section
        if (Mods.Gendustry.isModLoaded())
        for (int i = 10; i < 26; i++) {
            registerCombRecipe(Mods.Gendustry.getItem("honey_comb", 1, i), new ItemStack[] {Mods.Gendustry.getItem("honey_drop", 1, i), wax, drop}, new int[] {10000, 5000, 3000});
        }
        // If Forestry's Centrifuge is enabled, recipes will be added by GTCEu
        if (ModuleFactory.machineEnabled(MachineUIDs.CENTRIFUGE)) return;
        // Forestry Section
        registerCombRecipe(Mods.Forestry.getItem("bee_combs", 1, 0), new ItemStack[] {wax, drop}, new int[] {10000, 9000});
        registerCombRecipe(Mods.Forestry.getItem("bee_combs", 1, 2), new ItemStack[] {Mods.Forestry.getItem("refractory_wax"), Mods.Forestry.getItem("phosphor", 2)}, new int[] {10000, 7000});
        registerCombRecipe(Mods.Forestry.getItem("bee_combs", 1, 3), new ItemStack[] {Mods.Forestry.getItem("propolis"), drop}, new int[] {10000, 4000});
        registerCombRecipe(Mods.Forestry.getItem("bee_combs", 1, 4), new ItemStack[] {wax, drop, new ItemStack(Items.SNOWBALL), Mods.Forestry.getItem("pollen", 1, 1)}, new int[] {8000, 7000, 4000, 2000});
        registerCombRecipe(Mods.Forestry.getItem("bee_combs", 1, 5), new ItemStack[] {Mods.Forestry.getItem("honeydew"), drop}, new int[] {10000, 4000});
        registerCombRecipe(Mods.Forestry.getItem("bee_combs", 1, 6), new ItemStack[] {drop, Mods.Forestry.getItem("propolis", 1, 3)}, new int[] {10000, 8000});
        registerCombRecipe(Mods.Forestry.getItem("bee_combs", 1, 7), new ItemStack[] {wax, drop}, new int[] {10000, 9000});
        registerCombRecipe(Mods.Forestry.getItem("bee_combs", 1, 15), new ItemStack[] {wax, drop}, new int[] {10000, 9000});
        registerCombRecipe(Mods.Forestry.getItem("bee_combs", 1, 16), new ItemStack[] {Mods.Forestry.getItem("honeydew"), new ItemStack(Items.QUARTZ), drop}, new int[] {6000, 3000, 2000});
        // ExtraBee Section
        if (Mods.ExtraBees.isModLoaded()) {
            registerCombRecipe(combExtraBee(0), new ItemStack[] {wax, drop}, new int[] {10000, 5000});
            registerCombRecipe(combExtraBee(1), new ItemStack[] {new ItemStack(Items.ROTTEN_FLESH), wax, drop}, new int[] {8000, 2000, 2000});
            registerCombRecipe(combExtraBee(2), new ItemStack[] {new ItemStack(Items.DYE, 1, 15), wax, drop}, new int[] {8000, 2000, 2000});
            registerCombRecipe(combExtraBee(3), new ItemStack[] {drop, Mods.ExtraBees.getItem("propolis", 1, 1)}, new int[] {7500, 600});
            registerCombRecipe(combExtraBee(4), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Coal), wax, drop }, new int[] {10000, 8000, 7500});
            registerCombRecipe(combExtraBee(6), new ItemStack[] {Mods.ExtraBees.getItem("propolis"), drop}, new int[] {10000, 9000});
            registerCombRecipe(combExtraBee(7), new ItemStack[] {Mods.ExtraBees.getItem("honey_drop", 1, 6), drop}, new int[] {10000, 9000});
            registerCombRecipe(combExtraBee(8), new ItemStack[] {Mods.ExtraBees.getItem("honey_drop", 1, 3), drop}, new int[] {10000, 9000});
            registerCombRecipe(combExtraBee(9), new ItemStack[] {Mods.ExtraBees.getItem("honey_drop", 1, 7), drop}, new int[] {10000, 9000});
            registerCombRecipe(combExtraBee(10), new ItemStack[] {Mods.ExtraBees.getItem("honey_drop", 1, 8), drop}, new int[] {10000, 9000});
            registerCombRecipe(combExtraBee(11), new ItemStack[] {wax, drop}, new int[] {5000, 2500});
            registerCombRecipe(combExtraBee(12), new ItemStack[] {OreDictUnifier.get(dust, Materials.Redstone), wax, drop}, new int[] {10000, 8000, 5000});
            registerCombRecipe(combExtraBee(14), new ItemStack[] {Mods.ExtraBees.getItem("honey_drop"), wax, OreDictUnifier.get(dust, Materials.Redstone)}, new int[] {10000, 8000, 7500});
            registerCombRecipe(combExtraBee(15), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Iron), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(16), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Gold), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(17), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Copper), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(18), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Tin), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(19), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Silver), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(21), new ItemStack[] {wax, drop}, new int[] {5000, 2500});
            registerCombRecipe(combExtraBee(22), new ItemStack[] {drop, new ItemStack(Items.CLAY_BALL), wax}, new int[] {8000, 8000, 2500});
            registerCombRecipe(combExtraBee(23), new ItemStack[] {wax, drop}, new int[] {1000, 9000});
            registerCombRecipe(combExtraBee(24), new ItemStack[] {new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK), wax, new ItemStack(Blocks.RED_MUSHROOM_BLOCK)}, new int[] {10000, 9000, 7500});
            registerCombRecipe(combExtraBee(25), new ItemStack[] {Mods.ExtraBees.getItem("propolis", 1, 7), drop}, new int[] {7000, 5000});
            registerCombRecipe(combExtraBee(27), new ItemStack[] {wax, OreDictUnifier.get(dust, Materials.Sulfur), Mods.ExtraBees.getItem("honey_drop", 1, 1)}, new int[] {8000, 7500, 5000});
            registerCombRecipe(combExtraBee(28), new ItemStack[] {wax, Mods.ExtraBees.getItem("honey_drop", 1, 2)}, new int[] {8000, 8000});
            registerCombRecipe(combExtraBee(29), new ItemStack[] {wax, new ItemStack(Items.SLIME_BALL), drop}, new int[] {10000, 7500, 7500});
            registerCombRecipe(combExtraBee(30), new ItemStack[] {new ItemStack(Items.BLAZE_POWDER), wax}, new int[] {10000, 7500});
            registerCombRecipe(combExtraBee(32), new ItemStack[] {Mods.ExtraBees.getItem("honey_drop", 1, 5), drop}, new int[] {8000, 7500});
            registerCombRecipe(combExtraBee(36), new ItemStack[] {OreDictUnifier.get(dust, Materials.Obsidian), drop}, new int[] {7500, 5000});
            registerCombRecipe(combExtraBee(37), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Lead), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(40), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Zinc), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(41), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Titanium), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(42), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Tungsten), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(45), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Platinum), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(46), new ItemStack[] {new ItemStack(Items.DYE, 6, 4), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(48), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Pyrite), OreDictUnifier.get(dustSmall, Materials.Iron), wax, drop}, new int[] {10000, 10000, 5000, 2500});
            registerCombRecipe(combExtraBee(50), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Cinnabar), wax, drop, OreDictUnifier.get(dust, Materials.Redstone)}, new int[] {10000, 5000, 2500, 500});
            registerCombRecipe(combExtraBee(51), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Sphalerite), OreDictUnifier.get(dustSmall, Materials.Zinc), wax, drop}, new int[] {10000, 10000, 5000, 2500});
            registerCombRecipe(combExtraBee(52), new ItemStack[] {Mods.ExtraBees.getItem("misc", 1, 2), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(53), new ItemStack[] {Mods.ExtraBees.getItem("misc", 1, 3), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(54), new ItemStack[] {Mods.ExtraBees.getItem("misc", 1, 4), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(56), new ItemStack[] {Mods.ExtraBees.getItem("misc", 1, 1), wax, drop}, new int[] {10000, 5000, 2500});
            int meta = 13;
            for (int i = 57; i < 73; i++) {
                registerCombRecipe(combExtraBee(i), new ItemStack[] {Mods.ExtraBees.getItem("honey_drop", 1, meta), drop, wax}, new int[] {10000, 8000, 8000});
                meta++;
            }
            registerCombRecipe(combExtraBee(73), new ItemStack[] {OreDictUnifier.get(dustSmall, Materials.Nickel), wax, drop}, new int[] {10000, 5000, 2500});
            registerCombRecipe(combExtraBee(75), new ItemStack[] {OreDictUnifier.get(dust, Materials.Glowstone), drop}, new int[] {10000, 2500});
            registerCombRecipe(combExtraBee(76), new ItemStack[] {OreDictUnifier.get(dust, Materials.Saltpeter), drop}, new int[] {10000, 2500});
            registerCombRecipe(combExtraBee(79), new ItemStack[] {Mods.Forestry.getItem("fertilizer_bio"), drop}, new int[] {10000, 2500});
            registerCombRecipe(combExtraBee(81), new ItemStack[] {drop, new ItemStack(Items.QUARTZ), OreDictUnifier.get(dust, Materials.CertusQuartz)}, new int[] {2500, 2500, 2000});
            registerCombRecipe(combExtraBee(82), new ItemStack[] {drop, OreDictUnifier.get(dust, Materials.EnderPearl)}, new int[] {2500, 2500});
        }
    }



    public static void registerCombRecipe(ItemStack comb, ItemStack[] output,  int[] chance) {

        RecipeBuilder<?> builder = RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(comb)
                .EUt(5)
                .duration(128);

        int outputs = 0;
        for (int i = 0; i < output.length; i++) {
            if (output[i] == null || output[i] == ItemStack.EMPTY) continue;
            if (outputs < RecipeMaps.CENTRIFUGE_RECIPES.getMaxOutputs()) {
                if(chance[i] >= 10000) {
                    builder.outputs(output[i]);
                } else {
                    builder.chancedOutput(output[i], chance[i], 0);
                }
                outputs++;
            }
        }

        builder.buildAndRegister();
    }

    public static ItemStack combExtraBee(int meta) {
        return Mods.ExtraBees.getItem("honey_comb", 1, meta);
    }
}
