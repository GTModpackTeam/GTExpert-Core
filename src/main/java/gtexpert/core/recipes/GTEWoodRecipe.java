package gtexpert.core.recipes;

import static gregtech.api.unification.ore.OrePrefix.*;

import java.util.Arrays;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.wood.BlockGregPlanks;
import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.Mods;
import gtexpert.core.GTEConfigHolder;
import gtexpert.core.loaders.GTEWoodRecipeLoader;

public class GTEWoodRecipe {

    private static List<WoodTypeEntry> DEFAULT_ENTRIES;

    private static List<WoodTypeEntry> getDefaultEntries() {
        if (DEFAULT_ENTRIES == null) {
            final String mcModId = Mods.Vanilla.name();
            return DEFAULT_ENTRIES = Arrays.asList(
                    new WoodTypeEntry.Builder(mcModId, "oak")
                            .planks(new ItemStack(Blocks.PLANKS), "oak_planks")
                            .log(new ItemStack(Blocks.LOG))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "spruce")
                            .planks(new ItemStack(Blocks.PLANKS, 1, 1), "spruce_planks")
                            .log(new ItemStack(Blocks.LOG, 1, 1))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "birch")
                            .planks(new ItemStack(Blocks.PLANKS, 1, 2), "birch_planks")
                            .log(new ItemStack(Blocks.LOG, 1, 2))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "jungle")
                            .planks(new ItemStack(Blocks.PLANKS, 1, 3), "jungle_planks")
                            .log(new ItemStack(Blocks.LOG, 1, 3))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "acacia")
                            .planks(new ItemStack(Blocks.PLANKS, 1, 4), "acacia_planks")
                            .log(new ItemStack(Blocks.LOG2))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "dark_oak")
                            .planks(new ItemStack(Blocks.PLANKS, 1, 5), "dark_oak_planks")
                            .log(new ItemStack(Blocks.LOG2, 1, 1))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(GTValues.MODID, "rubber")
                            .planks(MetaBlocks.PLANKS.getItemVariant(BlockGregPlanks.BlockType.RUBBER_PLANK), null)
                            .log(new ItemStack(MetaBlocks.RUBBER_LOG))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(GTValues.MODID, "treated")
                            .planks(MetaBlocks.PLANKS.getItemVariant(BlockGregPlanks.BlockType.TREATED_PLANK), null)
                            .registerAllUnificationInfo()
                            .build());
        }
        return DEFAULT_ENTRIES;
    }

    public static void init() {
        sticks();
        planks();
    }

    private static void sticks() {
        int stick_normal = GTEConfigHolder.ceuOverride.moreNerfStickCrafting ? 1 : 2;
        stick_normal = ConfigHolder.recipes.harderRods ? stick_normal : 4;
        int stick_saw = GTEConfigHolder.ceuOverride.moreNerfStickCrafting ? 2 : 4;
        stick_saw = ConfigHolder.recipes.harderRods ? stick_saw : 6;

        ModHandler.removeRecipeByName(Mods.GregTech.getResource("stick_normal"));
        ModHandler.addMirroredShapedRecipe("stick_normal", new ItemStack(Items.STICK, stick_normal), "P", "P",
                'P', new UnificationEntry(plank, Materials.Wood));
        ModHandler.removeRecipeByName(Mods.GregTech.getResource("stick_saw"));
        ModHandler.addMirroredShapedRecipe("stick_saw", new ItemStack(Items.STICK, stick_saw), "s", "P", "P",
                'P', new UnificationEntry(plank, Materials.Wood));

        ModHandler.removeRecipeByName(Mods.GregTech.getResource("treated_wood_stick"));
        ModHandler.addMirroredShapedRecipe("treated_wood_stick",
                OreDictUnifier.get(stick, Materials.TreatedWood, stick_normal), "P", "P",
                'P', MetaBlocks.PLANKS.getItemVariant(BlockGregPlanks.BlockType.TREATED_PLANK));
        ModHandler.removeRecipeByName(Mods.GregTech.getResource("treated_wood_stick_saw"));
        ModHandler.addMirroredShapedRecipe("treated_wood_stick_saw",
                OreDictUnifier.get(stick, Materials.TreatedWood, stick_saw), "s", "P", "P",
                'P', MetaBlocks.PLANKS.getItemVariant(BlockGregPlanks.BlockType.TREATED_PLANK));
    }

    private static void planks() {
        if (ConfigHolder.recipes.nerfWoodCrafting) {
            for (WoodTypeEntry entry : getDefaultEntries()) {
                GTEWoodRecipeLoader.removeGTWoodRecipes(entry);

                GTEWoodRecipeLoader.addWoodRecipes(entry);
                GTEWoodRecipeLoader.recipeSawmill(entry.log, entry.planks);
            }
        }
    }
}
