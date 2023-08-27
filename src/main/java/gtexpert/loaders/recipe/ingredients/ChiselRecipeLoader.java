package gtexpert.loaders.recipe.ingredients;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.*;
import gregtech.loaders.recipe.MetaTileEntityLoader;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.api.util.GTEUtility;
import gtexpert.common.GTEConfigHolder;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import team.chisel.common.init.ChiselItems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.ModHandler.removeRecipeByName;
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
import static gtexpert.integration.chisel.ChiselHelper.addGroup;
import static gtexpert.integration.chisel.ChiselHelper.addVariation;

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
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "charcoal_uncraft"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "diamond"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "emerald"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "redstone"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "coal"));

            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocksilver"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocklead"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocktin"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocksteel"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockplatinum"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockiron"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockaluminium"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockcobalt"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blocknickel"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockelectrum"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockuranium"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockcopper"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockbronze"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockinvar"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "uncraft_blockgold"));
        }

        // Auto Chisel
        removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "autochisel"));
        ModHandler.addShapelessRecipe("normal_auto_chisel",
                GTEUtility.getModItem(GTEValues.MODID_CHISEL, "auto_chisel", 1),
                AUTO_CHISEL[2].getStackForm());
        ModHandler.addShapelessRecipe("ceu_auto_chisel", AUTO_CHISEL[2].getStackForm(),
                GTEUtility.getModItem(GTEValues.MODID_CHISEL, "auto_chisel", 1));
        MetaTileEntityLoader.registerMachineRecipe(true, AUTO_CHISEL,
                "BSB", "THT", "MCM",
                'B', new UnificationEntry(toolHeadBuzzSaw, Invar),
                'S', SENSOR,
                'T', "craftChisel",
                'H', HULL,
                'M', MOTOR,
                'C', CIRCUIT);

        // Glass Pane
        if (ConfigHolder.recipes.hardGlassRecipes) {
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassbubble"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassnoborder"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassshale"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-thingrid"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/chinese"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/japanese"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassdungeon"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glasslight"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-ornatesteel"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-screen"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-steelframe"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassstone"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glassstreak"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/terrain-glass-thickgrid"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/a1-glasswindow-ironfencemodern"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/chrono"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/chinese2"));
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "glass/japanese2"));
        }

        // Hazard Sign 1
        addGroup("hazardSign");
        Arrays.asList(YELLOW_STRIPES, SMALL_YELLOW_STRIPES,
                RADIOACTIVE_HAZARD, BIO_HAZARD, EXPLOSION_HAZARD, FIRE_HAZARD, ACID_HAZARD, MAGIC_HAZARD, FROST_HAZARD,
                NOISE_HAZARD, GENERIC_HAZARD, HIGH_VOLTAGE_HAZARD, MAGNETIC_HAZARD, ANTIMATTER_HAZARD,
                HIGH_TEMPERATURE_HAZARD, VOID_HAZARD)
                .forEach(hazardSign -> addVariation("hazardSign", WARNING_SIGN.getItemVariant(hazardSign)));
        List<ItemStack> hazardSigns = OreDictionary.getOres("hazardSign");
        hazardSigns.forEach(hazardSign -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .circuitMeta(hazardSign.getMetadata())
                .input("hazardSign")
                .outputs(hazardSign)
                .duration(20)
                .EUt(VA[ULV])
                .buildAndRegister());

        // Hazard Sign 2
        addGroup("hazardSign1");
        Arrays.asList(MOB_SPAWNER_HAZARD, SPATIAL_STORAGE_HAZARD,
                LASER_HAZARD, MOB_HAZARD, BOSS_HAZARD, GREGIFICATION_HAZARD, CAUSALITY_HAZARD,
                AUTOMATED_DEFENSES_HAZARD, HIGH_PRESSURE_HAZARD)
                .forEach(hazardSign1 -> addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(hazardSign1)));
        List<ItemStack> hazardSigns1 = OreDictionary.getOres("hazardSign1");
        hazardSigns1.forEach(hazardSign1 -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .circuitMeta(hazardSign1.getMetadata())
                .input("hazardSign1")
                .outputs(hazardSign1)
                .duration(20)
                .EUt(VA[ULV])
                .buildAndRegister());

        // Black Granite
        addGroup("blackGranite");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(blackGranite -> addVariation("blackGranite",
                        STONE_BLOCKS.get(blackGranite).getItemVariant(BLACK_GRANITE)));
        List<ItemStack> blackGranites = OreDictionary.getOres("blackGranite");
        blackGranites.forEach(blackGranite -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .circuitMeta(blackGranite.getMetadata())
                .input("blackGranite")
                .outputs(blackGranite)
                .duration(20)
                .EUt(VA[ULV])
                .buildAndRegister());

        // Red Granite
        addGroup("redGranite");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(redGranite -> addVariation("redGranite",
                        STONE_BLOCKS.get(redGranite).getItemVariant(RED_GRANITE)));
        List<ItemStack> redGranites = OreDictionary.getOres("redGranite");
        redGranites.forEach(redGranite -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .circuitMeta(redGranite.getMetadata())
                .input("redGranite")
                .outputs(redGranite)
                .duration(20)
                .EUt(VA[ULV])
                .buildAndRegister());

        // Marble
        addGroup("marbleGt");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(marbleGt -> addVariation("marbleGt", STONE_BLOCKS.get(marbleGt).getItemVariant(MARBLE)));
        List<ItemStack> marbleGts = OreDictionary.getOres("marbleGt");
        marbleGts.forEach(marbleGt -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .circuitMeta(marbleGt.getMetadata())
                .input("marbleGt")
                .outputs(marbleGt)
                .duration(20)
                .EUt(VA[ULV])
                .buildAndRegister());

        // Basalt
        addGroup("basaltGt");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(basaltGt -> addVariation("basaltGt", STONE_BLOCKS.get(basaltGt).getItemVariant(BASALT)));
        List<ItemStack> basaltGts = OreDictionary.getOres("basaltGt");
        basaltGts.forEach(basaltGt -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .circuitMeta(basaltGt.getMetadata())
                .input("basaltGt")
                .outputs(basaltGt)
                .duration(20)
                .EUt(VA[ULV])
                .buildAndRegister());

        // Light Concrete
        addGroup("lightConcrete");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED,
                BRICKS, BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A,
                WINDMILL_B, BRICKS_SQUARE)
                .forEach(lightConcrete -> addVariation("lightConcrete",
                        STONE_BLOCKS.get(lightConcrete).getItemVariant(CONCRETE_LIGHT)));
        List<ItemStack> lightConcretes = OreDictionary.getOres("lightConcrete");
        lightConcretes.forEach(lightConcrete -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .circuitMeta(lightConcrete.getMetadata())
                .input("lightConcrete")
                .outputs(lightConcrete)
                .duration(20)
                .EUt(VA[ULV])
                .buildAndRegister());

        // Dark Concrete
        addGroup("darkConcrete");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(darkConcrete -> addVariation("darkConcrete",
                        STONE_BLOCKS.get(darkConcrete).getItemVariant(CONCRETE_DARK)));
        List<ItemStack> darkConcretes = OreDictionary.getOres("darkConcrete");
        darkConcretes.forEach(darkConcrete -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .circuitMeta(darkConcrete.getMetadata())
                .input("darkConcrete")
                .outputs(darkConcrete)
                .duration(20)
                .EUt(VA[ULV])
                .buildAndRegister());

        // Lamp
        if (GTEConfigHolder.chiselIntegration.hardLedRecipes && Loader.isModLoaded("projectred-illumination")) {
            IntStream.range(0, 31).mapToObj(i -> getModItem("projectred-illumination", "lamp", 1, i))
                    .forEach(ModHandler::removeRecipeByOutput);

            int i = 0;
            while (i < Materials.CHEMICAL_DYES.length) {
                EnumDyeColor color = EnumDyeColor.byMetadata(i);
                BlockLamp lamp = MetaBlocks.LAMPS.get(color);

                addGroup("lamp_" + color);
                {
                    int lampMeta = 0;
                    while (lampMeta < lamp.getItemMetadataStates()) {
                        addVariation("lamp_" + color, getModItem("projectred-illumination", "lamp", 1, i));
                        addVariation("lamp_" + color,
                                getModItem("projectred-illumination", "lamp", 1, i + 16));
                        addVariation("lamp_" + color, new ItemStack(lamp, 1, lampMeta));
                        lampMeta++;
                    }
                }

                lamp = MetaBlocks.BORDERLESS_LAMPS.get(color);
                addGroup("lamp_borderless_" + color);
                int lampMeta = 0;
                while (lampMeta < lamp.getItemMetadataStates()) {
                    addVariation("lamp_borderless_" + color, new ItemStack(lamp, 1, lampMeta));
                    lampMeta++;
                }
                i++;
            }
        } else {
            int i = 0;
            while (i < Materials.CHEMICAL_DYES.length) {
                EnumDyeColor color = EnumDyeColor.byMetadata(i);
                BlockLamp lamp = MetaBlocks.LAMPS.get(color);

                addGroup("lamp_" + color);
                {
                    int lampMeta = 0;
                    while (lampMeta < lamp.getItemMetadataStates()) {
                        addVariation("lamp_" + color, new ItemStack(lamp, 1, lampMeta));
                        lampMeta++;
                    }
                }

                lamp = MetaBlocks.BORDERLESS_LAMPS.get(color);
                addGroup("lamp_borderless_" + color);
                int lampMeta = 0;
                while (lampMeta < lamp.getItemMetadataStates()) {
                    addVariation("lamp_borderless_" + color, new ItemStack(lamp, 1, lampMeta));
                    lampMeta++;
                }
                i++;
            }
        }
    }

    private static void tools() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && GTEConfigHolder.chiselIntegration.hardToolRecipes) {
            // Iron Chisel
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_iron"));
            ModHandler.addShapedRecipe("chisel_iron", new ItemStack(ChiselItems.chisel_iron),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Iron),
                    'C', new UnificationEntry(screw, Iron),
                    'S', new UnificationEntry(stick, Bronze));

            // Diamond Chisel
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_diamond"));
            ModHandler.addShapedRecipe("chisel_diamond", new ItemStack(ChiselItems.chisel_diamond),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Diamond),
                    'C', new ItemStack(ChiselItems.chisel_iron),
                    'S', new UnificationEntry(stick, RoseGold));

            // iChisel
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_hitech"));
            ModHandler.addShapedRecipe("chisel_hitech", new ItemStack(ChiselItems.chisel_hitech),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Diamond),
                    'C', new ItemStack(ChiselItems.chisel_diamond),
                    'S', new UnificationEntry(stick, StainlessSteel));
        }
    }
}
