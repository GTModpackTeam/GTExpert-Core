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

import gtexpert.integration.ae.AEHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import team.chisel.common.init.ChiselBlocks;
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
        registerAutoChiselRecipe("hazardSign");

        // Hazard Sign 2
        addGroup("hazardSign1");
        Arrays.asList(MOB_SPAWNER_HAZARD, SPATIAL_STORAGE_HAZARD,
                LASER_HAZARD, MOB_HAZARD, BOSS_HAZARD, GREGIFICATION_HAZARD, CAUSALITY_HAZARD,
                AUTOMATED_DEFENSES_HAZARD, HIGH_PRESSURE_HAZARD)
                .forEach(hazardSign1 -> addVariation("hazardSign1", WARNING_SIGN_1.getItemVariant(hazardSign1)));
        registerAutoChiselRecipe("hazardSign1");

        // Black Granite
        addGroup("blackGranite");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(blackGranite -> addVariation("blackGranite",
                        STONE_BLOCKS.get(blackGranite).getItemVariant(BLACK_GRANITE)));
        registerAutoChiselRecipe("blackGranite");

        // Red Granite
        addGroup("redGranite");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(redGranite -> addVariation("redGranite",
                        STONE_BLOCKS.get(redGranite).getItemVariant(RED_GRANITE)));
        registerAutoChiselRecipe("redGranite");

        // Marble
        addGroup("marbleGt");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(marbleGt -> addVariation("marbleGt", STONE_BLOCKS.get(marbleGt).getItemVariant(MARBLE)));
        registerAutoChiselRecipe("marbleGt");

        // Basalt
        addGroup("basaltGt");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(basaltGt -> addVariation("basaltGt", STONE_BLOCKS.get(basaltGt).getItemVariant(BASALT)));
        registerAutoChiselRecipe("basaltGt");

        // Light Concrete
        addGroup("lightConcrete");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED,
                BRICKS, BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A,
                WINDMILL_B, BRICKS_SQUARE)
                .forEach(lightConcrete -> addVariation("lightConcrete",
                        STONE_BLOCKS.get(lightConcrete).getItemVariant(CONCRETE_LIGHT)));
        registerAutoChiselRecipe("lightConcrete");

        // Dark Concrete
        addGroup("darkConcrete");
        Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS,
                BRICKS_CRACKED, BRICKS_MOSSY, CHISELED, TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B,
                BRICKS_SQUARE)
                .forEach(darkConcrete -> addVariation("darkConcrete",
                        STONE_BLOCKS.get(darkConcrete).getItemVariant(CONCRETE_DARK)));
        registerAutoChiselRecipe("darkConcrete");

        // Lamp
        if (GTEConfigHolder.chiselIntegration.hardLedRecipes && Loader.isModLoaded("projectred-illumination")) {
            IntStream.range(0, 31).mapToObj(i -> getModItem("projectred-illumination", "lamp", 1, i))
                    .forEach(ModHandler::removeRecipeByOutput);

            int i = 0;
            while (i < Materials.CHEMICAL_DYES.length) {
                EnumDyeColor color = EnumDyeColor.byMetadata(i);
                String colorName = color.getName().equals("silver") ? "light_gray" : color.getName();
                BlockLamp lamp = MetaBlocks.LAMPS.get(color);

                addGroup("lamp_" + colorName);
                {
                    int lampMeta = 0;
                    while (lampMeta < lamp.getItemMetadataStates()) {
                        addVariation("lamp_" + colorName, getModItem("projectred-illumination", "lamp", 1, i));
                        addVariation("lamp_" + colorName,
                                getModItem("projectred-illumination", "lamp", 1, i + 16));
                        addVariation("lamp_" + colorName, new ItemStack(lamp, 1, lampMeta));
                        lampMeta++;
                    }
                }

                lamp = MetaBlocks.BORDERLESS_LAMPS.get(color);
                addGroup("lamp_borderless_" + colorName);
                int lampMeta = 0;
                while (lampMeta < lamp.getItemMetadataStates()) {
                    addVariation("lamp_borderless_" + colorName, new ItemStack(lamp, 1, lampMeta));
                    lampMeta++;
                }
                i++;
            }
        } else {
            int i = 0;
            while (i < Materials.CHEMICAL_DYES.length) {
                EnumDyeColor color = EnumDyeColor.byMetadata(i);
                String colorName = color.getName().equals("silver") ? "light_gray" : color.getName();
                BlockLamp lamp = MetaBlocks.LAMPS.get(color);

                addGroup("lamp_" + colorName);
                {
                    int lampMeta = 0;
                    while (lampMeta < lamp.getItemMetadataStates()) {
                        addVariation("lamp_" + colorName, new ItemStack(lamp, 1, lampMeta));
                        lampMeta++;
                    }
                }

                lamp = MetaBlocks.BORDERLESS_LAMPS.get(color);
                addGroup("lamp_borderless_" + colorName);
                int lampMeta = 0;
                while (lampMeta < lamp.getItemMetadataStates()) {
                    addVariation("lamp_borderless_" + colorName, new ItemStack(lamp, 1, lampMeta));
                    lampMeta++;
                }
                i++;
            }
        }

        // ######################
        // Default chiseling
        // ######################
        // Andesite
        registerAutoChiselRecipe("stoneAndesite");
        // Antiblock
        OreDictionary.registerOre("blockAntiblock", new ItemStack(ChiselBlocks.antiblock, 1, W));
        registerAutoChiselRecipe("blockAntiblock");
        // Basalt
        registerAutoChiselRecipe("stoneBasalt");
        // Aluminium
        List<ItemStack> aluminums = OreDictionary.getOres("blockAluminum");
        aluminums.forEach(alumimun ->
                OreDictionary.registerOre("blockAluminium", alumimun));
        registerAutoChiselRecipe("blockAluminium");
        // Bronze
        registerAutoChiselRecipe("blockBronze");
        // Charcoal
        registerAutoChiselRecipe("blockCharcoal");
        // Coal
        registerAutoChiselRecipe("blockCoal");
        // Coal Coke
        registerAutoChiselRecipe("blockFuelCoke");
        // Cobalt
        registerAutoChiselRecipe("blockCobalt");
        // Copper
        registerAutoChiselRecipe("blockCopper");
        // Diamond
        registerAutoChiselRecipe("blockDiamond");
        // Electrum
        registerAutoChiselRecipe("blockElectrum");
        // Emerald
        registerAutoChiselRecipe("blockEmerald");
        // Gold
        registerAutoChiselRecipe("blockGold");
        // Invar
        registerAutoChiselRecipe("blockInvar");
        // Iron
        registerAutoChiselRecipe("blockIron");
        // Lapis
        registerAutoChiselRecipe("blockLapis");
        // Lead
        registerAutoChiselRecipe("blockLead");
        // Nickel
        registerAutoChiselRecipe("blockNickel");
        // Platinum
        registerAutoChiselRecipe("blockPlatinum");
        // Silver
        registerAutoChiselRecipe("blockSilver");
        // Steel
        registerAutoChiselRecipe("blockSteel");
        // Tin
        registerAutoChiselRecipe("blockTin");
        // Uranium
        registerAutoChiselRecipe("blockUranium");
        // Bookshelves
        registerAutoChiselRecipe("bookshelfOak");
        registerAutoChiselRecipe("bookshelfBirch");
        registerAutoChiselRecipe("bookshelfJungle");
        registerAutoChiselRecipe("bookshelfJungle");
        registerAutoChiselRecipe("bookshelfAcacia");
        registerAutoChiselRecipe("bookshelfDarkOak");
        // Brick
        OreDictionary.registerOre("blockBrick", getModItem(GTEValues.MODID_CHISEL, "bricks", 1, W));
        OreDictionary.registerOre("blockBrick", getModItem(GTEValues.MODID_CHISEL, "bricks1", 1, W));
        OreDictionary.registerOre("blockBrick", getModItem(GTEValues.MODID_CHISEL, "bricks2", 1, W));
        registerAutoChiselRecipe("blockBrick");
        // Brownstone
        OreDictionary.registerOre("blockBrownstone", new ItemStack(ChiselBlocks.brownstone, 1, W));
        registerAutoChiselRecipe("blockBrownstone");
        // Colored Blocks
        for (int i = 0; i < Materials.CHEMICAL_DYES.length; i++) {
            EnumDyeColor dyeColor = EnumDyeColor.values()[i];
            String colorName = dyeColor.toString().equals("silver") ? "LightGray" : dyeColor.toString().substring(0,1).toUpperCase() + dyeColor.toString().substring(1); // LightBlue
            String colorname = dyeColor.toString().equals("silver") ? "lightgray" : dyeColor.toString().toLowerCase(); // lightblue
            // Carpet
            OreDictionary.registerOre(String.format("carpet" + colorName), new ItemStack(Blocks.CARPET, 1, i));
            OreDictionary.registerOre(String.format("carpet" + colorName), getModItem(GTEValues.MODID_CHISEL, String.format("carpet_" + colorname), 1, W));
            registerAutoChiselRecipe(String.format("carpet" + colorName));
            // Concrete
            OreDictionary.registerOre(String.format("blockConcrete" + colorName), new ItemStack(Blocks.CONCRETE, 1, i));
            registerAutoChiselRecipe(String.format("blockConcrete" + colorName));
            // Glass
            OreDictionary.registerOre(String.format("blockChisellableGlass" + colorName), new ItemStack(Blocks.STAINED_GLASS, 1, i));
            OreDictionary.registerOre(String.format("blockChisellableGlass" + colorName), getModItem(GTEValues.MODID_CHISEL, String.format("glassdyed" + colorname), 1, W));
            registerAutoChiselRecipe(String.format("blockChisellableGlass" + colorName));
            OreDictionary.registerOre("blockChisellableGlassColorless", new ItemStack(Blocks.GLASS));
            OreDictionary.registerOre("blockChisellableGlassColorless", getModItem(GTEValues.MODID_CHISEL, "glass", 1, W));
            OreDictionary.registerOre("blockChisellableGlassColorless", getModItem(GTEValues.MODID_CHISEL, "glass1", 1, W));
            registerAutoChiselRecipe("blockChisellableGlassColorless");
            // Glass Pane
            registerAutoChiselRecipe(String.format("paneGlass" + colorName));
            registerAutoChiselRecipe("paneGlassColorless");
            // Wool
            OreDictionary.registerOre(String.format("blockWool" + colorName), getModItem(GTEValues.MODID_CHISEL, String.format("wool_" + colorname), 1, W));
            registerAutoChiselRecipe(String.format("blockWool" + colorName));
        }
        // Certus Quartz
        OreDictionary.registerOre("blockCertus", AEHelper.aeBlocks.quartzBlock().maybeStack(1).get());
        OreDictionary.registerOre("blockCertus", AEHelper.aeBlocks.quartzPillar().maybeStack(1).get());
        OreDictionary.registerOre("blockCertus", AEHelper.aeBlocks.chiseledQuartzBlock().maybeStack(1).get());
        OreDictionary.registerOre("blockCertus", getModItem(GTEValues.MODID_CHISEL, "certus", 1, W));
        OreDictionary.registerOre("blockCertus", getModItem(GTEValues.MODID_CHISEL, "certus1", 1, W));
        OreDictionary.registerOre("blockCertus", getModItem(GTEValues.MODID_CHISEL, "certus2", 1, W));
        registerAutoChiselRecipe("blockCertus");
        // Cloud
        OreDictionary.registerOre("blockCloud", new ItemStack(ChiselBlocks.cloud, 1, W));
        registerAutoChiselRecipe("blockCloud");
        // Cobblestone
        registerAutoChiselRecipe("cobblestone");
        // Moss Stone
        OreDictionary.registerOre("blockMossy", new ItemStack(Blocks.MOSSY_COBBLESTONE));
        registerAutoChiselRecipe("blockMossy");
        // Diorite
        registerAutoChiselRecipe("stoneDiorite");
        // Dirt
        registerAutoChiselRecipe("dirt");
        // Endstone
        OreDictionary.registerOre("endstone", new ItemStack(Blocks.END_BRICKS));
        registerAutoChiselRecipe("endstone");
        // Factory Block
        OreDictionary.registerOre("blockFactory", new ItemStack(ChiselBlocks.factory, 1, W));
        OreDictionary.registerOre("blockFactory", getModItem(GTEValues.MODID_CHISEL, "factory1", 1, W));
        OreDictionary.registerOre("blockFactory", getModItem(GTEValues.MODID_CHISEL, "technical", 1, W));
        OreDictionary.registerOre("blockFactory", getModItem(GTEValues.MODID_CHISEL, "technical1", 1, W));
        OreDictionary.registerOre("blockFactory", getModItem(GTEValues.MODID_CHISEL, "technicalnew", 1, W));
        registerAutoChiselRecipe("blockFactory");
        // Futura Block
        OreDictionary.registerOre("blockFutura", new ItemStack(ChiselBlocks.futura, 1,W));
        registerAutoChiselRecipe("blockFutura");
        // Glowstone
        registerAutoChiselRecipe("glowstone");
        // Granite
        registerAutoChiselRecipe("stoneGranite");
        // Terracotta
        OreDictionary.registerOre("hardenedClay", new ItemStack(Blocks.HARDENED_CLAY));
        registerAutoChiselRecipe("hardenedClay");
        // Ice
        registerAutoChiselRecipe("blockIce");
        // Iron Bars
        OreDictionary.registerOre("barsIron", getModItem(GTEValues.MODID_CHISEL, "ironpane", 1, W));
        registerAutoChiselRecipe("barsIron");
        // Laboratory Block
        OreDictionary.registerOre("blockLaboratory", new ItemStack(ChiselBlocks.laboratory, 1, W));
        registerAutoChiselRecipe("blockLaboratory");
        // Lavastone
        OreDictionary.registerOre("blockLavastone", new ItemStack(ChiselBlocks.lavastone, 1, W));
        OreDictionary.registerOre("blockLavastone", new ItemStack(ChiselBlocks.lavastone1, 1, W));
        OreDictionary.registerOre("blockLavastone", new ItemStack(ChiselBlocks.lavastone2, 1, W));
        registerAutoChiselRecipe("blockLavastone");
        // Limestone
        registerAutoChiselRecipe("stoneLimestone");
        // Marble
        registerAutoChiselRecipe("stoneMarble");
        // Nether Brick
        OreDictionary.registerOre("brickNether", new ItemStack(Blocks.NETHER_BRICK));
        OreDictionary.registerOre("brickNether", getModItem(GTEValues.MODID_CHISEL, "netherbrick", 1, W));
        registerAutoChiselRecipe("brickNether");
        // Netherrack
        registerAutoChiselRecipe("netherrack");
        // Obsidian
        registerAutoChiselRecipe("obsidian");
        // Paper Wall
        OreDictionary.registerOre("blockPaperWall", new ItemStack(ChiselBlocks.paper, 1, W));
        registerAutoChiselRecipe("blockPaperWall");
        // Planks
        OreDictionary.registerOre("plankWoodOak", new ItemStack(Blocks.PLANKS, 1));
        OreDictionary.registerOre("plankWoodOak", getModItem(GTEValues.MODID_CHISEL, "planks-oak", 1, W));
        registerAutoChiselRecipe("plankWoodOak");
        OreDictionary.registerOre("plankWoodSpruce", new ItemStack(Blocks.PLANKS, 1, 1));
        OreDictionary.registerOre("plankWoodSpruce", getModItem(GTEValues.MODID_CHISEL, "planks-spruce", 1, W));
        registerAutoChiselRecipe("plankWoodSpruce");
        OreDictionary.registerOre("plankWoodBirch", new ItemStack(Blocks.PLANKS, 1, 2));
        OreDictionary.registerOre("plankWoodBirch", getModItem(GTEValues.MODID_CHISEL, "planks-birch", 1, W));
        registerAutoChiselRecipe("plankWoodBirch");
        OreDictionary.registerOre("plankWoodJungle", new ItemStack(Blocks.PLANKS, 1, 3));
        OreDictionary.registerOre("plankWoodJungle", getModItem(GTEValues.MODID_CHISEL, "planks-jungle", 1, W));
        registerAutoChiselRecipe("plankWoodJungle");
        OreDictionary.registerOre("plankWoodAcacia", new ItemStack(Blocks.PLANKS, 1, 4));
        OreDictionary.registerOre("plankWoodAcacia", getModItem(GTEValues.MODID_CHISEL, "planks-acacia", 1, W));
        registerAutoChiselRecipe("plankWoodAcacia");
        OreDictionary.registerOre("plankWoodDarkOak", new ItemStack(Blocks.PLANKS, 1, 5));
        OreDictionary.registerOre("plankWoodDarkOak", getModItem(GTEValues.MODID_CHISEL, "planks-dark-oak", 1, W));
        registerAutoChiselRecipe("plankWoodDarkOak");
        // Prismarine
        OreDictionary.registerOre("prismarineBrick", new ItemStack(Blocks.PRISMARINE, 1, 1));
        registerAutoChiselRecipe("prismarineBrick");
        // Purpur
        OreDictionary.registerOre("blockPurpur", new ItemStack(Blocks.PURPUR_BLOCK));
        OreDictionary.registerOre("blockPurpur", new ItemStack(Blocks.PURPUR_PILLAR));
        OreDictionary.registerOre("blockPurpur", getModItem(GTEValues.MODID_CHISEL, "purpur", 1, W));
        OreDictionary.registerOre("blockPurpur", getModItem(GTEValues.MODID_CHISEL, "purpur1", 1, W));
        OreDictionary.registerOre("blockPurpur", getModItem(GTEValues.MODID_CHISEL, "purpur2", 1, W));
        registerAutoChiselRecipe("blockPurpur");
        // Quartz Block
        OreDictionary.registerOre("blockQuartz", new ItemStack(Blocks.QUARTZ_BLOCK, 1, 1));
        OreDictionary.registerOre("blockQuartz", new ItemStack(Blocks.QUARTZ_BLOCK, 1, 2));
        registerAutoChiselRecipe("blockQuartz");
        // Redstone Block
        registerAutoChiselRecipe("blockRedstone");
        // Red Sandstone
        OreDictionary.registerOre("sandstoneRed", new ItemStack(Blocks.RED_SANDSTONE, 1, W));
        OreDictionary.registerOre("sandstoneRed", getModItem(GTEValues.MODID_CHISEL, "sandstonered", 1, W));
        OreDictionary.registerOre("sandstoneRed", getModItem(GTEValues.MODID_CHISEL, "sandstonered1", 1, W));
        OreDictionary.registerOre("sandstoneRed", getModItem(GTEValues.MODID_CHISEL, "sandstonered2", 1, W));
        OreDictionary.registerOre("sandstoneRed", getModItem(GTEValues.MODID_CHISEL, "sandstonered-scribbles", 1, W));
        registerAutoChiselRecipe("sandstoneRed");
        // Sandstone
        OreDictionary.registerOre("sandstoneYellow", new ItemStack(Blocks.SANDSTONE, 1, W));
        OreDictionary.registerOre("sandstoneYellow", getModItem(GTEValues.MODID_CHISEL, "sandstoneyellow1", 1, W));
        OreDictionary.registerOre("sandstoneYellow", getModItem(GTEValues.MODID_CHISEL, "sandstoneyellow", 1, W));
        OreDictionary.registerOre("sandstoneYellow", getModItem(GTEValues.MODID_CHISEL, "sandstoneyellow2", 1, W));
        OreDictionary.registerOre("sandstoneYellow", getModItem(GTEValues.MODID_CHISEL, "sandstone-scribbles", 1, W));
        registerAutoChiselRecipe("sandstoneYellow");
        // Stone
        OreDictionary.registerOre("stone", new ItemStack(Blocks.STONEBRICK, 1, W));
        registerAutoChiselRecipe("stone");
        // Temple Block
        OreDictionary.registerOre("blockTemple", new ItemStack(ChiselBlocks.temple, 1, W));
        OreDictionary.registerOre("blockTemple", getModItem(GTEValues.MODID_CHISEL, "templemossy", 1, W));
        registerAutoChiselRecipe("blockTemple");
        // Tyrian
        OreDictionary.registerOre("blockTyrian", new ItemStack(ChiselBlocks.tyrian, 1, W));
        registerAutoChiselRecipe("blockTyrian");
        // Valentines' Block
        OreDictionary.registerOre("blockValentine", new ItemStack(ChiselBlocks.valentines, 1, W));
        registerAutoChiselRecipe("blockValentine");
        // Voidstone
        OreDictionary.registerOre("blockVoidstone", new ItemStack(ChiselBlocks.voidstone, 1, W));
        OreDictionary.registerOre("blockVoidstone", getModItem(GTEValues.MODID_CHISEL, "energizedvoidstone", 1, W));
        OreDictionary.registerOre("blockVoidstone", getModItem(GTEValues.MODID_CHISEL, "voidstonerunic", 1, W));
        registerAutoChiselRecipe("blockVoidstone");
        // Waterstone
        OreDictionary.registerOre("blockWaterstone", new ItemStack(ChiselBlocks.waterstone, 1, W));
        OreDictionary.registerOre("blockWaterstone", new ItemStack(ChiselBlocks.waterstone1, 1, W));
        OreDictionary.registerOre("blockWaterstone", new ItemStack(ChiselBlocks.waterstone2, 1, W));
        registerAutoChiselRecipe("blockWaterstone");
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

    private static void registerAutoChiselRecipe(String oreDictName) {
        List<ItemStack> targets = OreDictionary.getOres(oreDictName);
        targets.forEach(target -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .input(oreDictName)
                .inputs(target)
                .outputs(target, target)
                .duration(20)
                .EUt(VA[ULV])
                //.hidden()
                .buildAndRegister());
    }
}
