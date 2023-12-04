package gtexpert.loaders.recipe.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.ModHandler.removeRecipeByName;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockWarningSign.SignType.*;
import static gregtech.common.blocks.BlockWarningSign1.SignType.*;
import static gregtech.common.blocks.MetaBlocks.*;
import static gregtech.common.blocks.StoneVariantBlock.StoneType.*;
import static gregtech.common.blocks.StoneVariantBlock.StoneVariant.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.api.util.GTEUtility.getModItem;
import static gtexpert.common.metatileentities.GTESingleMetaTileEntities.AUTO_CHISEL;
import static gtexpert.integration.chisel.ChiselHelper.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
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

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.api.util.GTEUtility;
import gtexpert.common.GTEConfigHolder;

import team.chisel.common.init.ChiselBlocks;
import team.chisel.common.init.ChiselItems;

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
        // Bookshelf
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack(Blocks.PLANKS, 6, 0),
                new ItemStack(Items.BOOK, 3));
        String[] bookshelf = new String[] { "oak", "spruce", "birch", "jungle", "acacia", "darkoak" };
        for (int i = 0; i < bookshelf.length; i++) {
            addGroup("bookshelf" + bookshelf[i].toUpperCase());
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(new ItemStack(Blocks.PLANKS, 6, i))
                    .inputs(new ItemStack(Items.BOOK, 3))
                    .outputs(getModItem(GTEValues.MODID_CHISEL, "bookshelf_" + bookshelf[i], 1, 0))
                    .duration(100).EUt(VH[ULV])
                    .buildAndRegister();
            registerAutoChiselRecipe("bookshelf" + bookshelf[i].toUpperCase());
        }

        // Material Blocks
        if (ConfigHolder.recipes.disableManualCompression) {
            Arrays.asList("charcoal_uncraft", "diamond", "emerald", "redstone", "coal", "uncraft_blocksilver",
                    "uncraft_blocklead", "uncraft_blocktin", "uncraft_blocksteel", "uncraft_blockplatinum",
                    "uncraft_blockiron", "uncraft_blockaluminium", "uncraft_blockcobalt", "uncraft_blocknickel",
                    "uncraft_blockelectrum", "uncraft_blockuranium", "uncraft_blockcopper", "uncraft_blockbronze",
                    "uncraft_blockinvar", "uncraft_blockgold").forEach(
                            block -> removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, block)));
        }

        // Glass Panes
        if (ConfigHolder.recipes.hardGlassRecipes) {
            Arrays.asList("glass/terrain-glassbubble", "glass/terrain-glassnoborder", "glass/terrain-glassshale",
                    "glass/terrain-glass-thingrid", "glass/chinese", "glass/japanese", "glass/terrain-glassdungeon",
                    "glass/terrain-glasslight", "glass/terrain-glass-ornatesteel", "glass/terrain-glass-screen",
                    "glass/terrain-glass-steelframe", "glass/terrain-glassstone", "glass/terrain-glassstreak",
                    "glass/terrain-glass-thickgrid", "glass/a1-glasswindow-ironfencemodern", "glass/chrono",
                    "glass/chinese2", "glass/japanese2").forEach(
                            block -> removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, block)));
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
                'B', new UnificationEntry(toolHeadBuzzSaw, Materials.Invar),
                'S', SENSOR,
                'T', "craftChisel",
                'H', HULL,
                'M', MOTOR,
                'C', CIRCUIT);

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

        // GT Stones
        Arrays.asList(BLACK_GRANITE, RED_GRANITE, MARBLE, BASALT, CONCRETE_LIGHT, CONCRETE_DARK).forEach(stoneType -> {
            String groupName = "stone" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, stoneType.getName());
            addGroup(groupName);
            Arrays.asList(SMOOTH, COBBLE, COBBLE_MOSSY, POLISHED, BRICKS, BRICKS_CRACKED, BRICKS_MOSSY, CHISELED,
                    TILED, TILED_SMALL, BRICKS_SMALL, WINDMILL_A, WINDMILL_B, BRICKS_SQUARE)
                    .forEach(stoneVariant -> addVariation(groupName,
                            STONE_BLOCKS.get(stoneVariant).getItemVariant(stoneType)));
            registerAutoChiselRecipe(groupName);
        });

        // Lamp
        if (GTEConfigHolder.chiselIntegration.hardLedRecipes) {
            if (Loader.isModLoaded("projectred-illumination")) {
                IntStream.range(0, 31).mapToObj(i -> getModItem("projectred-illumination", "lamp", 1, i))
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

                addGroup("lamp" + colorName);
                {
                    int lampMeta = 0;
                    while (lampMeta < lamp.getItemMetadataStates()) {
                        if (Loader.isModLoaded("projectred-illumination")) {
                            addVariation("lamp" + colorName, getModItem("projectred-illumination", "lamp", 1, i));
                        }
                        addVariation("lamp" + colorName,
                                getModItem("projectred-illumination", "lamp", 1, i + 16));
                        addVariation("lamp" + colorName, new ItemStack(lamp, 1, lampMeta));
                        lampMeta++;
                    }
                }

                lamp = MetaBlocks.BORDERLESS_LAMPS.get(color);
                addGroup("lampBorderless" + colorName);
                int lampMeta = 0;
                while (lampMeta < lamp.getItemMetadataStates()) {
                    addVariation("lampBorderless" + colorName, new ItemStack(lamp, 1, lampMeta));
                    lampMeta++;
                }
                registerAutoChiselRecipe("lamp" + colorName);
                registerAutoChiselRecipe("lampBorderless" + colorName);
                i++;
            }
        }

        // ######################
        // Default chiseling
        // ######################
        // Material Blocks
        List<ItemStack> aluminums = OreDictionary.getOres("blockAluminum");
        aluminums.forEach(alumimun -> OreDictionary.registerOre("blockAluminium", alumimun));
        Arrays.asList("blockAluminium", "blockBronze", "blockCharcoal", "blockCoal", "blockFuelCoke", "blockCobalt",
                "blockCopper", "blockDiamond", "blockElectrum", "blockEmerald", "blockGold", "blockInvar", "blockIron",
                "blockLapis", "blockLead", "blockNickel", "blockPlatinum", "blockSilver", "blockSteel", "blockTin",
                "blockUranium").forEach(ChiselRecipeLoader::registerAutoChiselRecipe);

        // Andesite
        registerAutoChiselRecipe("stoneAndesite");

        // Antiblock
        for (int i = 0; i < Materials.CHEMICAL_DYES.length; i++) {
            OreDictionary.registerOre("blockAntiblock", new ItemStack(ChiselBlocks.antiblock, 1, i));
        }
        registerAutoChiselRecipe("blockAntiblock");

        // Basalt
        registerAutoChiselRecipe("stoneBasalt");

        // Brick
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockBrick", getModItem(GTEValues.MODID_CHISEL, "bricks", 1, i));
        }
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("blockBrick", getModItem(GTEValues.MODID_CHISEL, "bricks1", 1, i));
        }
        for (int i = 0; i < 6; i++) {
            OreDictionary.registerOre("blockBrick", getModItem(GTEValues.MODID_CHISEL, "bricks2", 1, i));
        }
        registerAutoChiselRecipe("blockBrick");

        // Brownstone
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("blockBrownstone", new ItemStack(ChiselBlocks.brownstone, 1, i));
        }
        registerAutoChiselRecipe("blockBrownstone");

        // Colored Blocks
        for (int i = 0; i < Materials.CHEMICAL_DYES.length; i++) {
            EnumDyeColor dyeColor = EnumDyeColor.values()[i];
            String upperColorName = dyeColor.toString().equals("silver") ?
                    "LightGray" : CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, dyeColor.getName());
            String lowerColorName = dyeColor.toString().equals("silver") ?
                    "lightgray" : dyeColor.toString().toLowerCase();

            // Carpet
            OreDictionary.registerOre("carpet" + upperColorName, new ItemStack(Blocks.CARPET, 1, i));
            OreDictionary.registerOre("carpet" + upperColorName,
                    getModItem(GTEValues.MODID_CHISEL, "carpet_" + lowerColorName, 1, 0));
            OreDictionary.registerOre("carpet" + upperColorName,
                    getModItem(GTEValues.MODID_CHISEL, "carpet_" + lowerColorName, 1, 1));
            registerAutoChiselRecipe("carpet" + upperColorName);

            // Concrete
            OreDictionary.registerOre("blockConcrete" + upperColorName, new ItemStack(Blocks.CONCRETE, 1, i));
            registerAutoChiselRecipe("blockConcrete" + lowerColorName);

            // Stained Glass
            OreDictionary.registerOre("blockChisellableGlass" + upperColorName,
                    new ItemStack(Blocks.STAINED_GLASS, 1, i));
            for (int j = 0; j < 6; j++) {
                OreDictionary.registerOre("blockChisellableGlass" + upperColorName,
                        getModItem(GTEValues.MODID_CHISEL, "glassdyed" + lowerColorName, 1, j));
            }

            // Colorless Glass
            registerAutoChiselRecipe("blockChisellableGlass" + upperColorName);
            OreDictionary.registerOre("blockChisellableGlassColorless", new ItemStack(Blocks.GLASS));
            for (int j = 0; j < 15; j++) {
                OreDictionary.registerOre("blockChisellableGlassColorless",
                        getModItem(GTEValues.MODID_CHISEL, "glass", 1, j));
            }
            OreDictionary.registerOre("blockChisellableGlassColorless",
                    getModItem(GTEValues.MODID_CHISEL, "glass1", 1, 0));
            OreDictionary.registerOre("blockChisellableGlassColorless",
                    getModItem(GTEValues.MODID_CHISEL, "glass1", 1, 1));
            registerAutoChiselRecipe("blockChisellableGlassColorless");

            // Glass Pane
            registerAutoChiselRecipe("paneGlass" + upperColorName);
            registerAutoChiselRecipe("paneGlassColorless");

            // Wool
            OreDictionary.registerOre("blockWool" + upperColorName,
                    getModItem(GTEValues.MODID_CHISEL, "wool_" + lowerColorName, 1, 0));
            OreDictionary.registerOre("blockWool" + upperColorName,
                    getModItem(GTEValues.MODID_CHISEL, "wool_" + lowerColorName, 1, 1));
            registerAutoChiselRecipe("blockWool" + upperColorName);
        }

        // Certus Quartz
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockCertus", getModItem(GTEValues.MODID_CHISEL, "certus", 1, i));
            OreDictionary.registerOre("blockCertus", getModItem(GTEValues.MODID_CHISEL, "certus1", 1, i));
        }
        OreDictionary.registerOre("blockCertus", getModItem(GTEValues.MODID_CHISEL, "certus2", 1, 0));
        OreDictionary.registerOre("blockCertus", getModItem(GTEValues.MODID_CHISEL, "certus2", 1, 1));
        registerAutoChiselRecipe("blockCertus");

        // Cloud
        for (int i = 0; i < 5; i++) {
            OreDictionary.registerOre("blockCloud", new ItemStack(ChiselBlocks.cloud, 1, i));
        }
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
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockFactory", new ItemStack(ChiselBlocks.factory, 1, i));
            OreDictionary.registerOre("blockFactory", getModItem(GTEValues.MODID_CHISEL, "technical", 1, i));
        }
        for (int i = 0; i < 5; i++) {
            OreDictionary.registerOre("blockFactory", getModItem(GTEValues.MODID_CHISEL, "factory1", 1, i));
            OreDictionary.registerOre("blockFactory", getModItem(GTEValues.MODID_CHISEL, "technical1", 1, i));
        }
        for (int i = 0; i < 9; i++) {
            OreDictionary.registerOre("blockFactory", getModItem(GTEValues.MODID_CHISEL, "technicalnew", 1, i));
        }
        registerAutoChiselRecipe("blockFactory");

        // Futura Block
        for (int i = 0; i < 6; i++) {
            OreDictionary.registerOre("blockFutura", new ItemStack(ChiselBlocks.futura, 1, i));
        }
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
        for (int i = 0; i < 13; i++) {
            OreDictionary.registerOre("barsIron", getModItem(GTEValues.MODID_CHISEL, "ironpane", 1, i));
        }
        registerAutoChiselRecipe("barsIron");

        // Laboratory Block
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockLaboratory", new ItemStack(ChiselBlocks.laboratory, 1, i));
        }
        registerAutoChiselRecipe("blockLaboratory");

        // Lavastone
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockLavastone", new ItemStack(ChiselBlocks.lavastone, 1, i));
            OreDictionary.registerOre("blockLavastone", new ItemStack(ChiselBlocks.lavastone1, 1, i));
        }
        OreDictionary.registerOre("blockLavastone", new ItemStack(ChiselBlocks.lavastone2, 1));
        registerAutoChiselRecipe("blockLavastone");

        // Limestone
        registerAutoChiselRecipe("stoneLimestone");

        // Marble
        registerAutoChiselRecipe("stoneMarble");

        // Nether Brick
        OreDictionary.registerOre("brickNether", new ItemStack(Blocks.NETHER_BRICK));
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("brickNether", getModItem(GTEValues.MODID_CHISEL, "netherbrick", 1, i));
        }
        registerAutoChiselRecipe("brickNether");

        // Netherrack
        registerAutoChiselRecipe("netherrack");

        // Obsidian
        registerAutoChiselRecipe("obsidian");

        // Paper Wall
        for (int i = 0; i < 9; i++) {
            OreDictionary.registerOre("blockPaperWall", new ItemStack(ChiselBlocks.paper, 1, i));
        }
        registerAutoChiselRecipe("blockPaperWall");

        // Planks
        for (int i = 0; i < 15; i++) {
            OreDictionary.registerOre("plankWoodOak", getModItem(GTEValues.MODID_CHISEL, "planks-oak", 1, i));
            OreDictionary.registerOre("plankWoodSpruce", getModItem(GTEValues.MODID_CHISEL, "planks-spruce", 1, i));
            OreDictionary.registerOre("plankWoodBirch", getModItem(GTEValues.MODID_CHISEL, "planks-birch", 1, i));
            OreDictionary.registerOre("plankWoodJungle", getModItem(GTEValues.MODID_CHISEL, "planks-jungle", 1, i));
            OreDictionary.registerOre("plankWoodAcacia", getModItem(GTEValues.MODID_CHISEL, "planks-acacia", 1, i));
            OreDictionary.registerOre("plankWoodDarkOak", getModItem(GTEValues.MODID_CHISEL, "planks-dark-oak", 1, i));
        }
        OreDictionary.registerOre("plankWoodOak", new ItemStack(Blocks.PLANKS, 1));
        registerAutoChiselRecipe("plankWoodOak");
        OreDictionary.registerOre("plankWoodSpruce", new ItemStack(Blocks.PLANKS, 1, 1));
        registerAutoChiselRecipe("plankWoodSpruce");
        OreDictionary.registerOre("plankWoodBirch", new ItemStack(Blocks.PLANKS, 1, 2));
        registerAutoChiselRecipe("plankWoodBirch");
        OreDictionary.registerOre("plankWoodJungle", new ItemStack(Blocks.PLANKS, 1, 3));
        registerAutoChiselRecipe("plankWoodJungle");
        OreDictionary.registerOre("plankWoodAcacia", new ItemStack(Blocks.PLANKS, 1, 4));
        registerAutoChiselRecipe("plankWoodAcacia");
        OreDictionary.registerOre("plankWoodDarkOak", new ItemStack(Blocks.PLANKS, 1, 5));
        registerAutoChiselRecipe("plankWoodDarkOak");

        // Prismarine
        OreDictionary.registerOre("prismarineBrick", new ItemStack(Blocks.PRISMARINE, 1, 1));
        registerAutoChiselRecipe("prismarineBrick");

        // Purpur
        OreDictionary.registerOre("blockPurpur", new ItemStack(Blocks.PURPUR_BLOCK));
        OreDictionary.registerOre("blockPurpur", new ItemStack(Blocks.PURPUR_PILLAR));
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockPurpur", getModItem(GTEValues.MODID_CHISEL, "purpur", 1, i));
        }
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("blockPurpur", getModItem(GTEValues.MODID_CHISEL, "purpur1", 1, i));
        }
        for (int i = 0; i < 5; i++) {
            OreDictionary.registerOre("blockPurpur", getModItem(GTEValues.MODID_CHISEL, "purpur2", 1, i));
        }
        registerAutoChiselRecipe("blockPurpur");

        // Quartz Block
        OreDictionary.registerOre("blockQuartz", new ItemStack(Blocks.QUARTZ_BLOCK, 1, 1));
        OreDictionary.registerOre("blockQuartz", new ItemStack(Blocks.QUARTZ_BLOCK, 1, 2));
        registerAutoChiselRecipe("blockQuartz");

        // Redstone Block
        registerAutoChiselRecipe("blockRedstone");

        // Red Sandstone
        for (int i = 0; i < 2; i++) {
            OreDictionary.registerOre("sandstoneRed", new ItemStack(Blocks.RED_SANDSTONE, 1, i));
        }
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("sandstoneRed", getModItem(GTEValues.MODID_CHISEL, "sandstonered", 1, i));
            OreDictionary.registerOre("sandstoneRed",
                    getModItem(GTEValues.MODID_CHISEL, "sandstonered-scribbles", 1, i));
        }
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("sandstoneRed", getModItem(GTEValues.MODID_CHISEL, "sandstonered1", 1, i));
        }
        for (int i = 0; i < 8; i++) {
            OreDictionary.registerOre("sandstoneRed", getModItem(GTEValues.MODID_CHISEL, "sandstonered2", 1, i));
        }
        registerAutoChiselRecipe("sandstoneRed");

        // Sandstone
        for (int i = 0; i < 2; i++) {
            OreDictionary.registerOre("sandstoneYellow", new ItemStack(Blocks.SANDSTONE, 1, i));
        }
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("sandstoneYellow", getModItem(GTEValues.MODID_CHISEL, "sandstoneyellow", 1, i));
            OreDictionary.registerOre("sandstoneYellow",
                    getModItem(GTEValues.MODID_CHISEL, "sandstone-scribbles", 1, i));
        }
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("sandstoneYellow", getModItem(GTEValues.MODID_CHISEL, "sandstoneyellow1", 1, i));
        }
        for (int i = 0; i < 8; i++) {
            OreDictionary.registerOre("sandstoneYellow", getModItem(GTEValues.MODID_CHISEL, "sandstoneyellow2", 1, i));
        }
        registerAutoChiselRecipe("sandstoneYellow");

        // Stone
        for (int i = 0; i < 4; i++) {
            OreDictionary.registerOre("stone", new ItemStack(Blocks.STONEBRICK, 1, i));
        }
        registerAutoChiselRecipe("stone");

        // Temple Block
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockTemple", new ItemStack(ChiselBlocks.temple, 1, i));
            OreDictionary.registerOre("blockTemple", getModItem(GTEValues.MODID_CHISEL, "templemossy", 1, i));
        }
        registerAutoChiselRecipe("blockTemple");

        // Tyrian
        for (int i = 0; i < 15; i++) {
            OreDictionary.registerOre("blockTyrian", new ItemStack(ChiselBlocks.tyrian, 1, i));
        }
        registerAutoChiselRecipe("blockTyrian");

        // Valentines' Block
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("blockValentine", new ItemStack(ChiselBlocks.valentines, 1, i));
        }
        registerAutoChiselRecipe("blockValentine");

        // Voidstone
        for (int i = 0; i < 8; i++) {
            OreDictionary.registerOre("blockVoidstone", new ItemStack(ChiselBlocks.voidstone, 1, i));
            OreDictionary.registerOre("blockVoidstone", getModItem(GTEValues.MODID_CHISEL, "energizedvoidstone", 1, i));
        }
        for (int i = 0; i < 15; i++) {
            OreDictionary.registerOre("blockVoidstone", getModItem(GTEValues.MODID_CHISEL, "voidstonerunic", 1, i));
        }
        registerAutoChiselRecipe("blockVoidstone");

        // Waterstone
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockWaterstone", new ItemStack(ChiselBlocks.waterstone, 1, i));
            OreDictionary.registerOre("blockWaterstone", new ItemStack(ChiselBlocks.waterstone1, 1, i));
        }
        OreDictionary.registerOre("blockWaterstone", new ItemStack(ChiselBlocks.waterstone2, 1));
        registerAutoChiselRecipe("blockWaterstone");
    }

    private static void tools() {
        if (ConfigHolder.recipes.hardToolArmorRecipes && GTEConfigHolder.chiselIntegration.hardToolRecipes) {
            // Iron Chisel
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_iron"));
            ModHandler.addShapedRecipe(true, "chisel_iron", new ItemStack(ChiselItems.chisel_iron),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Materials.Iron),
                    'C', new UnificationEntry(screw, Materials.Iron),
                    'S', new UnificationEntry(stick, Materials.Bronze));

            // Diamond Chisel
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_diamond"));
            ModHandler.addShapedRecipe(true, "chisel_diamond", new ItemStack(ChiselItems.chisel_diamond),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Materials.Diamond),
                    'C', new ItemStack(ChiselItems.chisel_iron),
                    'S', new UnificationEntry(stick, Materials.RoseGold));

            // iChisel
            removeRecipeByName(new ResourceLocation(GTEValues.MODID_CHISEL, "chisel_hitech"));
            ModHandler.addShapedRecipe(true, "chisel_hitech", new ItemStack(ChiselItems.chisel_hitech),
                    "fPP", " CP", "S h",
                    'P', new UnificationEntry(plate, Materials.Diamond),
                    'C', new ItemStack(ChiselItems.chisel_diamond),
                    'S', new UnificationEntry(stick, Materials.StainlessSteel));
        }
    }

    private static void registerAutoChiselRecipe(String oreDictName) {
        List<ItemStack> targets = OreDictionary.getOres(oreDictName);
        targets.forEach(target -> GTERecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .input(oreDictName)
                .notConsumable(target)
                .outputs(target)
                .duration(10).EUt(VH[ULV])
                .hidden().buildAndRegister());
    }
}
