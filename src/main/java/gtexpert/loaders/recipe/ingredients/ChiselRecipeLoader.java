package gtexpert.loaders.recipe.ingredients;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockLamp;
import gregtech.common.blocks.MetaBlocks;
import gregtech.loaders.recipe.MetaTileEntityLoader;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.integration.chisel.ChiselHelper;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import team.chisel.common.init.ChiselBlocks;
import team.chisel.common.init.ChiselItems;

import java.util.List;
import java.util.stream.IntStream;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockWarningSign.SignType.*;
import static gregtech.common.blocks.BlockWarningSign1.SignType.*;
import static gregtech.common.blocks.MetaBlocks.*;
import static gregtech.common.blocks.StoneVariantBlock.StoneType.*;
import static gregtech.common.blocks.StoneVariantBlock.StoneVariant.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.AUTO_CHISEL;

public class ChiselRecipeLoader {

    public static void init() {
        // craftChisel
        OreDictionary.registerOre("craftChisel", new ItemStack(ChiselItems.chisel_iron));
        OreDictionary.registerOre("craftChisel", new ItemStack(ChiselItems.chisel_diamond));
        OreDictionary.registerOre("craftChisel", new ItemStack(ChiselItems.chisel_hitech));

        blocks();
        tools();
    }

    private static void blocks() {
        // Oak Bookshelf
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack(Blocks.PLANKS, 6, 0),
                new ItemStack(Items.BOOK, 3));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.PLANKS, 6, 0))
                .inputs(new ItemStack(Items.BOOK, 3))
                .outputs(getModItem(GTEValues.MODID_CHISEL, "bookshelf_oak", 1, 0))
                .duration(100).EUt(4)
                .buildAndRegister();

        // Spruce Bookshelf
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.PLANKS, 6, 1))
                .inputs(new ItemStack(Items.BOOK, 3))
                .outputs(getModItem(GTEValues.MODID_CHISEL, "bookshelf_spruce", 1, 0))
                .duration(100).EUt(4)
                .buildAndRegister();

        // Birch Bookshelf
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.PLANKS, 6, 2))
                .inputs(new ItemStack(Items.BOOK, 3))
                .outputs(getModItem(GTEValues.MODID_CHISEL, "bookshelf_birch", 1, 0))
                .duration(100).EUt(4)
                .buildAndRegister();

        // Jungle Bookshelf
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.PLANKS, 6, 3))
                .inputs(new ItemStack(Items.BOOK, 3))
                .outputs(getModItem(GTEValues.MODID_CHISEL, "bookshelf_jungle", 1, 0))
                .duration(100).EUt(4)
                .buildAndRegister();

        // Acacia Bookshelf
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.PLANKS, 6, 4))
                .inputs(new ItemStack(Items.BOOK, 3))
                .outputs(getModItem(GTEValues.MODID_CHISEL, "bookshelf_acacia", 1, 0))
                .duration(100).EUt(4)
                .buildAndRegister();

        // Dark Oak Bookshelf
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.PLANKS, 6, 5))
                .inputs(new ItemStack(Items.BOOK, 3))
                .outputs(getModItem(GTEValues.MODID_CHISEL, "bookshelf_darkoak", 1, 0))
                .duration(100).EUt(4)
                .buildAndRegister();

        // Material Blocks
        if (ConfigHolder.recipes.disableManualCompression) {
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "charcoal_uncraft"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "diamond"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "emerald"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "redstone"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "coal"));

            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocksilver"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocklead"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocktin"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocksteel"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockplatinum"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockiron"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockaluminium"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockcobalt"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocknickel"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockelectrum"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockuranium"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockcopper"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockbronze"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockinvar"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockgold"));
        }

        // Auto Chisel
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "autochisel"));
        ModHandler.addShapedRecipe("autochisel", new ItemStack(ChiselBlocks.auto_chisel),
                "PPP", "PTP", "SSS",
                'P', "paneGlass",
                'T', "craftChisel",
                'S', new UnificationEntry(slab, Stone));
        MetaTileEntityLoader.registerMachineRecipe(true, AUTO_CHISEL, "BSB", "THT", "MCM",
                'B', new UnificationEntry(toolHeadBuzzSaw, Invar),
                'S', SENSOR,
                'T', "craftChisel",
                'H', HULL,
                'M', MOTOR,
                'C', CIRCUIT);

        // Glass Pane
        if (ConfigHolder.recipes.hardGlassRecipes) {
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassbubble"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassnoborder"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassshale"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-thingrid"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/chinese"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/japanese"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassdungeon"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glasslight"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-ornatesteel"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-screen"));
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-steelframe"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassstone"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassstreak"));
            ModHandler
                    .removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-thickgrid"));
            ModHandler.removeRecipeByName(
                    new ResourceLocation(GTEValues.MODID_CHISEL, "glass/a1-glasswindow-ironfencemodern"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/chrono"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/chinese2"));
            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/japanese2"));
        }

        // Hazard Sign 1
        ChiselHelper.addGroup("hazardSign");
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(YELLOW_STRIPES));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(SMALL_YELLOW_STRIPES));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(RADIOACTIVE_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(BIO_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(EXPLOSION_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(FIRE_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(ACID_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(MAGIC_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(FROST_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(NOISE_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(GENERIC_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(HIGH_VOLTAGE_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(MAGNETIC_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(ANTIMATTER_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(HIGH_TEMPERATURE_HAZARD));
        ChiselHelper.addVariation("hazardSign", WARNING_SIGN.getItemVariant(VOID_HAZARD));
        List<ItemStack> hazardSigns = OreDictionary.getOres("hazardSign");
        for (ItemStack hazardSign : hazardSigns) {
            GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                    .notConsumable(hazardSign)
                    .input("hazardSign")
                    .outputs(hazardSign)
                    .duration(20)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Hazard Sign 2
        ChiselHelper.addGroup("hazardSign1");
        ChiselHelper.addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(MOB_SPAWNER_HAZARD));
        ChiselHelper.addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(SPATIAL_STORAGE_HAZARD));
        ChiselHelper.addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(LASER_HAZARD));
        ChiselHelper.addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(MOB_HAZARD));
        ChiselHelper.addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(BOSS_HAZARD));
        ChiselHelper.addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(GREGIFICATION_HAZARD));
        ChiselHelper.addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(CAUSALITY_HAZARD));
        ChiselHelper.addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(AUTOMATED_DEFENSES_HAZARD));
        ChiselHelper.addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(HIGH_PRESSURE_HAZARD));
        List<ItemStack> hazardSigns1 = OreDictionary.getOres("hazardSign1");
        for (ItemStack hazardSign1 : hazardSigns1) {
            GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                    .notConsumable(hazardSign1)
                    .input("hazardSign1")
                    .outputs(hazardSign1)
                    .duration(20)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Black Granite
        ChiselHelper.addGroup("blackGranite");
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(SMOOTH).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(COBBLE).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(COBBLE_MOSSY).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(POLISHED).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(BRICKS).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(BRICKS_CRACKED).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(BRICKS_MOSSY).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(CHISELED).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(TILED).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(TILED_SMALL).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(BRICKS_SMALL).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(WINDMILL_A).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(WINDMILL_B).getItemVariant(BLACK_GRANITE));
        ChiselHelper.addVariation("blackGranite", STONE_BLOCKS.get(BRICKS_SQUARE).getItemVariant(BLACK_GRANITE));
        List<ItemStack> blackGranites = OreDictionary.getOres("blackGranite");
        for (ItemStack blackGranite : blackGranites) {
            GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                    .notConsumable(blackGranite)
                    .input("blackGranite")
                    .outputs(blackGranite)
                    .duration(20)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Red Granite
        ChiselHelper.addGroup("redGranite");
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(SMOOTH).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(COBBLE).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(COBBLE_MOSSY).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(POLISHED).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(BRICKS).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(BRICKS_CRACKED).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(BRICKS_MOSSY).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(CHISELED).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(TILED).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(TILED_SMALL).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(BRICKS_SMALL).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(WINDMILL_A).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(WINDMILL_B).getItemVariant(RED_GRANITE));
        ChiselHelper.addVariation("redGranite", STONE_BLOCKS.get(BRICKS_SQUARE).getItemVariant(RED_GRANITE));
        List<ItemStack> redGranites = OreDictionary.getOres("redGranite");
        for (ItemStack redGranite : redGranites) {
            GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                    .notConsumable(redGranite)
                    .input("redGranite")
                    .outputs(redGranite)
                    .duration(20)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Marble
        ChiselHelper.addGroup("marbleGt");
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(SMOOTH).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(COBBLE).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(COBBLE_MOSSY).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(POLISHED).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(BRICKS).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(BRICKS_CRACKED).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(BRICKS_MOSSY).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(CHISELED).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(TILED).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(TILED_SMALL).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(BRICKS_SMALL).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(WINDMILL_A).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(WINDMILL_B).getItemVariant(MARBLE));
        ChiselHelper.addVariation("marbleGt", STONE_BLOCKS.get(BRICKS_SQUARE).getItemVariant(MARBLE));
        List<ItemStack> marbleGts = OreDictionary.getOres("marbleGt");
        for (ItemStack marbleGt : marbleGts) {
            GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                    .notConsumable(marbleGt)
                    .input("marbleGt")
                    .outputs(marbleGt)
                    .duration(20)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Basalt
        ChiselHelper.addGroup("basaltGt");
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(SMOOTH).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(COBBLE).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(COBBLE_MOSSY).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(POLISHED).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(BRICKS).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(BRICKS_CRACKED).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(BRICKS_MOSSY).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(CHISELED).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(TILED).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(TILED_SMALL).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(BRICKS_SMALL).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(WINDMILL_A).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(WINDMILL_B).getItemVariant(BASALT));
        ChiselHelper.addVariation("basaltGt", STONE_BLOCKS.get(BRICKS_SQUARE).getItemVariant(BASALT));
        List<ItemStack> basaltGts = OreDictionary.getOres("basaltGt");
        for (ItemStack basaltGt : basaltGts) {
            GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                    .notConsumable(basaltGt)
                    .input("basaltGt")
                    .outputs(basaltGt)
                    .duration(20)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Light Concrete
        ChiselHelper.addGroup("lightConcrete");
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(SMOOTH).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(COBBLE).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(COBBLE_MOSSY).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(POLISHED).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(BRICKS).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(BRICKS_CRACKED).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(BRICKS_MOSSY).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(CHISELED).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(TILED).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(TILED_SMALL).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(BRICKS_SMALL).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(WINDMILL_A).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(WINDMILL_B).getItemVariant(CONCRETE_LIGHT));
        ChiselHelper.addVariation("lightConcrete", STONE_BLOCKS.get(BRICKS_SQUARE).getItemVariant(CONCRETE_LIGHT));
        List<ItemStack> lightConcretes = OreDictionary.getOres("lightConcrete");
        for (ItemStack lightConcrete : lightConcretes) {
            GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                    .notConsumable(lightConcrete)
                    .input("lightConcrete")
                    .outputs(lightConcrete)
                    .duration(20)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Dark Concrete
        ChiselHelper.addGroup("darkConcrete");
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(SMOOTH).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(COBBLE).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(COBBLE_MOSSY).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(POLISHED).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(BRICKS).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(BRICKS_CRACKED).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(BRICKS_MOSSY).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(CHISELED).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(TILED).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(TILED_SMALL).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(BRICKS_SMALL).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(WINDMILL_A).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(WINDMILL_B).getItemVariant(CONCRETE_DARK));
        ChiselHelper.addVariation("darkConcrete", STONE_BLOCKS.get(BRICKS_SQUARE).getItemVariant(CONCRETE_DARK));
        List<ItemStack> darkConcretes = OreDictionary.getOres("darkConcrete");
        for (ItemStack darkConcrete : darkConcretes) {
            GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                    .notConsumable(darkConcrete)
                    .input("darkConcrete")
                    .outputs(darkConcrete)
                    .duration(20)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        // Lamp
        if (Loader.isModLoaded("projectred-illumination")) {
            IntStream.range(0, 31).mapToObj(i -> getModItem("projectred-illumination", "lamp", 1, i))
                    .forEach(ModHandler::removeRecipeByOutput);

            int i = 0;
            while (i < Materials.CHEMICAL_DYES.length) {
                EnumDyeColor color = EnumDyeColor.byMetadata(i);
                BlockLamp lamp = MetaBlocks.LAMPS.get(color);

                ChiselHelper.addGroup("lamp_" + color);
                {
                    int lampMeta = 0;
                    while (lampMeta < lamp.getItemMetadataStates()) {
                        ChiselHelper.addVariation("lamp_" + color, getModItem("projectred-illumination", "lamp", 1, i));
                        ChiselHelper.addVariation("lamp_" + color,
                                getModItem("projectred-illumination", "lamp", 1, i + 16));
                        ChiselHelper.addVariation("lamp_" + color, new ItemStack(lamp, 1, lampMeta));
                        lampMeta++;
                    }
                }

                lamp = MetaBlocks.BORDERLESS_LAMPS.get(color);
                ChiselHelper.addGroup("lamp_borderless_" + color);
                int lampMeta = 0;
                while (lampMeta < lamp.getItemMetadataStates()) {
                    ChiselHelper.addVariation("lamp_borderless_" + color, new ItemStack(lamp, 1, lampMeta));
                    lampMeta++;
                }
                i++;
            }
        } else {
            int i = 0;
            while (i < Materials.CHEMICAL_DYES.length) {
                EnumDyeColor color = EnumDyeColor.byMetadata(i);
                BlockLamp lamp = MetaBlocks.LAMPS.get(color);

                ChiselHelper.addGroup("lamp_" + color);
                {
                    int lampMeta = 0;
                    while (lampMeta < lamp.getItemMetadataStates()) {
                        ChiselHelper.addVariation("lamp_" + color, new ItemStack(lamp, 1, lampMeta));
                        lampMeta++;
                    }
                }

                lamp = MetaBlocks.BORDERLESS_LAMPS.get(color);
                ChiselHelper.addGroup("lamp_borderless_" + color);
                int lampMeta = 0;
                while (lampMeta < lamp.getItemMetadataStates()) {
                    ChiselHelper.addVariation("lamp_borderless_" + color, new ItemStack(lamp, 1, lampMeta));
                    lampMeta++;
                }
                i++;
            }
        }
    }

    private static void tools() {
        // Iron Chisel
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_iron"));
        ModHandler.addShapedRecipe("chisel_iron", new ItemStack(ChiselItems.chisel_iron),
                "fPP", " CP", "S h",
                'P', new UnificationEntry(plate, Iron),
                'C', new UnificationEntry(screw, Iron),
                'S', new UnificationEntry(stick, Bronze));

        // Diamond Chisel
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_diamond"));
        ModHandler.addShapedRecipe("chisel_diamond", new ItemStack(ChiselItems.chisel_diamond),
                "fPP", " CP", "S h",
                'P', new UnificationEntry(plate, Diamond),
                'C', new ItemStack(ChiselItems.chisel_iron),
                'S', new UnificationEntry(stick, RoseGold));

        // iChisel
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_hitech"));
        ModHandler.addShapedRecipe("chisel_hitech", new ItemStack(ChiselItems.chisel_hitech),
                "fPP", " CP", "S h",
                'P', new UnificationEntry(plate, Diamond),
                'C', new ItemStack(ChiselItems.chisel_diamond),
                'S', new UnificationEntry(stick, StainlessSteel));
    }
}
