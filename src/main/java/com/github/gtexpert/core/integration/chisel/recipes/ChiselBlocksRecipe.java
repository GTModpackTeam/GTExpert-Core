package com.github.gtexpert.core.integration.chisel.recipes;

import static com.github.gtexpert.core.integration.chisel.metatileentities.ChiselMetaTileEntities.AUTO_CHISEL;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.loaders.recipe.CraftingComponent.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.CaseFormat;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.*;
import gregtech.loaders.recipe.MetaTileEntityLoader;

import com.github.gtexpert.core.api.util.GTEUtility;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.chisel.ChiselConfigHolder;
import com.github.gtexpert.core.integration.chisel.ChiselRecipeMaps;
import com.github.gtexpert.core.integration.chisel.ChiselUtil;

import team.chisel.api.carving.ICarvingGroup;
import team.chisel.common.carving.Carving;

public class ChiselBlocksRecipe {

    public static void init() {
        // Bookshelf
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack(Blocks.PLANKS, 6, 0),
                new ItemStack(Items.BOOK, 3));
        String[] bookshelf = new String[] { "oak", "spruce", "birch", "jungle", "acacia", "darkoak" };
        for (int i = 0; i < bookshelf.length; i++) {
            ChiselUtil.addGroup("bookshelf" + bookshelf[i].toUpperCase());
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(new ItemStack(Blocks.PLANKS, 6, i))
                    .inputs(new ItemStack(Items.BOOK, 3))
                    .outputs(GTEUtility.getModItem(Mods.Names.CHISEL, "bookshelf_" + bookshelf[i]))
                    .duration(100).EUt(VH[ULV])
                    .buildAndRegister();
        }

        // Material Blocks
        if (ConfigHolder.recipes.disableManualCompression) {
            Arrays.asList("charcoal_uncraft", "diamond", "emerald", "redstone", "coal", "uncraft_blocksilver",
                    "uncraft_blocklead", "uncraft_blocktin", "uncraft_blocksteel", "uncraft_blockplatinum",
                    "uncraft_blockiron", "uncraft_blockaluminium", "uncraft_blockcobalt", "uncraft_blocknickel",
                    "uncraft_blockelectrum", "uncraft_blockuranium", "uncraft_blockcopper", "uncraft_blockbronze",
                    "uncraft_blockinvar", "uncraft_blockgold").forEach(
                            block -> ModHandler
                                    .removeRecipeByName(Mods.Chisel.getResource(block)));
        }

        // Glass Panes
        if (ConfigHolder.recipes.hardGlassRecipes) {
            Arrays.asList("glass/terrain-glassbubble", "glass/terrain-glassnoborder", "glass/terrain-glassshale",
                    "glass/terrain-glass-thingrid", "glass/chinese", "glass/japanese", "glass/terrain-glassdungeon",
                    "glass/terrain-glasslight", "glass/terrain-glass-ornatesteel", "glass/terrain-glass-screen",
                    "glass/terrain-glass-steelframe", "glass/terrain-glassstone", "glass/terrain-glassstreak",
                    "glass/terrain-glass-thickgrid", "glass/a1-glasswindow-ironfencemodern", "glass/chrono",
                    "glass/chinese2", "glass/japanese2").forEach(
                            block -> ModHandler
                                    .removeRecipeByName(Mods.Chisel.getResource(block)));
        }

        // Auto Chisel
        ModHandler.removeRecipeByName(Mods.Chisel.getResource("autochisel"));
        ModHandler.addShapelessRecipe("normal_auto_chisel",
                GTEUtility.getModItem(Mods.Names.CHISEL, "auto_chisel", 1),
                AUTO_CHISEL[2].getStackForm());
        ModHandler.addShapelessRecipe("ceu_auto_chisel", AUTO_CHISEL[2].getStackForm(),
                GTEUtility.getModItem(Mods.Names.CHISEL, "auto_chisel", 1));
        MetaTileEntityLoader.registerMachineRecipe(true, AUTO_CHISEL,
                "BSB", "THT", "MCM",
                'B', new UnificationEntry(toolHeadBuzzSaw, Materials.Invar),
                'S', SENSOR,
                'T', "craftChisel",
                'H', HULL,
                'M', MOTOR,
                'C', CIRCUIT);

        // Lamp
        if (ChiselConfigHolder.hardLedRecipes) {
            if (Mods.ProjectRedIllumination.isModLoaded()) {
                IntStream.range(0, 32)
                        .mapToObj(i -> GTEUtility.getModItem(Mods.Names.PROJECT_RED_ILLUMINATION, "lamp", 1, i))
                        .forEach(ModHandler::removeRecipeByOutput);
            }

            int i = 0;
            while (i < Materials.CHEMICAL_DYES.length) {
                EnumDyeColor color = EnumDyeColor.byMetadata(i);
                EnumDyeColor dyeColor = EnumDyeColor.values()[i];
                String colorName = dyeColor.toString().equals("silver") ?
                        "LightGray" :
                        CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dyeColor.getName());
                BlockLamp lamp = MetaBlocks.LAMPS.get(color);

                ChiselUtil.addGroup("lamp" + colorName);
                {
                    int lampMeta = 0;
                    while (lampMeta < lamp.getItemMetadataStates()) {
                        if (Mods.ProjectRedIllumination.isModLoaded()) {
                            ChiselUtil.addVariation("lamp" + colorName,
                                    GTEUtility.getModItem(Mods.Names.PROJECT_RED_ILLUMINATION, "lamp", 1, i));
                            ChiselUtil.addVariation("lamp" + colorName,
                                    GTEUtility.getModItem(Mods.Names.PROJECT_RED_ILLUMINATION, "lamp", 1, i + 16));
                        }
                        ChiselUtil.addVariation("lamp" + colorName, new ItemStack(lamp, 1, lampMeta));
                        lampMeta++;
                    }
                }

                lamp = MetaBlocks.BORDERLESS_LAMPS.get(color);
                ChiselUtil.addGroup("lampBorderless" + colorName);
                int lampMeta = 0;
                while (lampMeta < lamp.getItemMetadataStates()) {
                    ChiselUtil.addVariation("lampBorderless" + colorName, new ItemStack(lamp, 1, lampMeta));
                    lampMeta++;
                }
                i++;
            }
        }

        // ######################
        // Default chiseling
        // ######################
        // Material Blocks
        List<ItemStack> aluminums = OreDictionary.getOres("blockAluminum");
        aluminums.forEach(alumimun -> OreDictionary.registerOre("blockAluminium", alumimun));
    }

    public static void registerAutoChiselRecipe() {
        Carving.chisel.getSortedGroupNames().forEach(groupName -> {
            ICarvingGroup group = Carving.chisel.getGroup(groupName);

            // Safety check: skip if the group does not exist
            if (group == null) {
                return;
            }

            List<ItemStack> stacks = Carving.chisel.getItemsForChiseling(group);
            stacks.stream()
                    // Exclude ItemStack.EMPTY
                    .filter(itemStack -> !itemStack.isEmpty())
                    .forEach(target -> stacks.stream()
                            // Exclude cases where input and target are the same
                            .filter(stack -> stack != target)
                            .forEach(stack -> ChiselRecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                                    .inputs(stack.copy())
                                    .notConsumable(target.copy())
                                    .outputs(target.copy())
                                    .duration(10).EUt(VH[ULV])
                                    .buildAndRegister()));
        });
    }
}
