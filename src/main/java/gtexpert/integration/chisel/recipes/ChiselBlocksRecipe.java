package gtexpert.integration.chisel.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gtexpert.integration.chisel.metatileentities.ChiselMetaTileEntities.AUTO_CHISEL;

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

import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.integration.chisel.ChiselConfigHolder;
import gtexpert.integration.chisel.ChiselRecipeMaps;
import gtexpert.integration.chisel.ChiselUtil;

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
            registerAutoChiselRecipe("bookshelf" + bookshelf[i].toUpperCase());
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
                IntStream.range(0, 31)
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
                        }
                        ChiselUtil.addVariation("lamp" + colorName,
                                GTEUtility.getModItem(Mods.Names.PROJECT_RED_ILLUMINATION, "lamp", 1, i + 16));
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
                "blockUranium").forEach(ChiselBlocksRecipe::registerAutoChiselRecipe);

        // Andesite
        registerAutoChiselRecipe("stoneAndesite");

        // Antiblock
        for (int i = 0; i < Materials.CHEMICAL_DYES.length; i++) {
            OreDictionary.registerOre("blockAntiblock",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "antiblock", 1, i));
        }
        registerAutoChiselRecipe("blockAntiblock");

        // Basalt
        registerAutoChiselRecipe("stoneBasalt");

        // Brick
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockBrick", GTEUtility.getModItem(Mods.Names.CHISEL, "bricks", 1, i));
        }
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("blockBrick", GTEUtility.getModItem(Mods.Names.CHISEL, "bricks1", 1, i));
        }
        for (int i = 0; i < 6; i++) {
            OreDictionary.registerOre("blockBrick", GTEUtility.getModItem(Mods.Names.CHISEL, "bricks2", 1, i));
        }
        registerAutoChiselRecipe("blockBrick");

        // Brownstone
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("blockBrownstone",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "brownstone", 1, i));
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
                    GTEUtility.getModItem(Mods.Names.CHISEL, "carpet_" + lowerColorName));
            OreDictionary.registerOre("carpet" + upperColorName,
                    GTEUtility.getModItem(Mods.Names.CHISEL, "carpet_" + lowerColorName, 1, 1));
            registerAutoChiselRecipe("carpet" + upperColorName);

            // Concrete
            OreDictionary.registerOre("blockConcrete" + upperColorName, new ItemStack(Blocks.CONCRETE, 1, i));
            registerAutoChiselRecipe("blockConcrete" + lowerColorName);

            // Stained Glass
            OreDictionary.registerOre("blockChisellableGlass" + upperColorName,
                    new ItemStack(Blocks.STAINED_GLASS, 1, i));
            for (int j = 0; j < 6; j++) {
                OreDictionary.registerOre("blockChisellableGlass" + upperColorName,
                        GTEUtility.getModItem(Mods.Names.CHISEL, "glassdyed" + lowerColorName, 1, j));
            }

            // Colorless Glass
            registerAutoChiselRecipe("blockChisellableGlass" + upperColorName);
            OreDictionary.registerOre("blockChisellableGlassColorless", new ItemStack(Blocks.GLASS));
            for (int j = 0; j < 15; j++) {
                OreDictionary.registerOre("blockChisellableGlassColorless",
                        GTEUtility.getModItem(Mods.Names.CHISEL, "glass", 1, j));
            }
            OreDictionary.registerOre("blockChisellableGlassColorless",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "glass1"));
            OreDictionary.registerOre("blockChisellableGlassColorless",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "glass1", 1, 1));
            registerAutoChiselRecipe("blockChisellableGlassColorless");

            // Glass Pane
            registerAutoChiselRecipe("paneGlass" + upperColorName);
            registerAutoChiselRecipe("paneGlassColorless");

            // Wool
            OreDictionary.registerOre("blockWool" + upperColorName,
                    GTEUtility.getModItem(Mods.Names.CHISEL, "wool_" + lowerColorName));
            OreDictionary.registerOre("blockWool" + upperColorName,
                    GTEUtility.getModItem(Mods.Names.CHISEL, "wool_" + lowerColorName, 1, 1));
            registerAutoChiselRecipe("blockWool" + upperColorName);
        }

        // Certus Quartz
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockCertus", GTEUtility.getModItem(Mods.Names.CHISEL, "certus", 1, i));
            OreDictionary.registerOre("blockCertus", GTEUtility.getModItem(Mods.Names.CHISEL, "certus1", 1, i));
        }
        OreDictionary.registerOre("blockCertus", GTEUtility.getModItem(Mods.Names.CHISEL, "certus2"));
        OreDictionary.registerOre("blockCertus", GTEUtility.getModItem(Mods.Names.CHISEL, "certus2", 1, 1));
        registerAutoChiselRecipe("blockCertus");

        // Cloud
        for (int i = 0; i < 5; i++) {
            OreDictionary.registerOre("blockCloud", GTEUtility.getModItem(Mods.Names.CHISEL, "cloud", 1, i));
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
            OreDictionary.registerOre("blockFactory", GTEUtility.getModItem(Mods.Names.CHISEL, "factory", 1, i));
            OreDictionary.registerOre("blockFactory", GTEUtility.getModItem(Mods.Names.CHISEL, "technical", 1, i));
        }
        for (int i = 0; i < 5; i++) {
            OreDictionary.registerOre("blockFactory", GTEUtility.getModItem(Mods.Names.CHISEL, "factory1", 1, i));
            OreDictionary.registerOre("blockFactory",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "technical1", 1, i));
        }
        for (int i = 0; i < 9; i++) {
            OreDictionary.registerOre("blockFactory",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "technicalnew", 1, i));
        }
        registerAutoChiselRecipe("blockFactory");

        // Futura Block
        for (int i = 0; i < 6; i++) {
            OreDictionary.registerOre("blockFutura", GTEUtility.getModItem(Mods.Names.CHISEL, "futura", 1, i));
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
            OreDictionary.registerOre("barsIron", GTEUtility.getModItem(Mods.Names.CHISEL, "ironpane", 1, i));
        }
        registerAutoChiselRecipe("barsIron");

        // Laboratory Block
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockLaboratory",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "laboratory", 1, i));
        }
        registerAutoChiselRecipe("blockLaboratory");

        // Lavastone
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockLavastone",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "lavastone", 1, i));
            OreDictionary.registerOre("blockLavastone",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "lavastone1", 1, i));
        }
        OreDictionary.registerOre("blockLavastone", GTEUtility.getModItem(Mods.Names.CHISEL, "lavastone2"));
        registerAutoChiselRecipe("blockLavastone");

        // Limestone
        registerAutoChiselRecipe("stoneLimestone");

        // Marble
        registerAutoChiselRecipe("stoneMarble");

        // Nether Brick
        OreDictionary.registerOre("brickNether", new ItemStack(Blocks.NETHER_BRICK));
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("brickNether",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "netherbrick", 1, i));
        }
        registerAutoChiselRecipe("brickNether");

        // Netherrack
        registerAutoChiselRecipe("netherrack");

        // Obsidian
        registerAutoChiselRecipe("obsidian");

        // Paper Wall
        for (int i = 0; i < 9; i++) {
            OreDictionary.registerOre("blockPaperWall", GTEUtility.getModItem(Mods.Names.CHISEL, "paper", 1, i));
        }
        registerAutoChiselRecipe("blockPaperWall");

        // Planks
        for (int i = 0; i < 15; i++) {
            OreDictionary.registerOre("plankWoodOak",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "planks-oak", 1, i));
            OreDictionary.registerOre("plankWoodSpruce",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "planks-spruce", 1, i));
            OreDictionary.registerOre("plankWoodBirch",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "planks-birch", 1, i));
            OreDictionary.registerOre("plankWoodJungle",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "planks-jungle", 1, i));
            OreDictionary.registerOre("plankWoodAcacia",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "planks-acacia", 1, i));
            OreDictionary.registerOre("plankWoodDarkOak",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "planks-dark-oak", 1, i));
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
            OreDictionary.registerOre("blockPurpur", GTEUtility.getModItem(Mods.Names.CHISEL, "purpur", 1, i));
        }
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("blockPurpur", GTEUtility.getModItem(Mods.Names.CHISEL, "purpur1", 1, i));
        }
        for (int i = 0; i < 5; i++) {
            OreDictionary.registerOre("blockPurpur", GTEUtility.getModItem(Mods.Names.CHISEL, "purpur2", 1, i));
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
            OreDictionary.registerOre("sandstoneRed",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "sandstonered", 1, i));
            OreDictionary.registerOre("sandstoneRed",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "sandstonered-scribbles", 1, i));
        }
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("sandstoneRed",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "sandstonered1", 1, i));
        }
        for (int i = 0; i < 8; i++) {
            OreDictionary.registerOre("sandstoneRed",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "sandstonered2", 1, i));
        }
        registerAutoChiselRecipe("sandstoneRed");

        // Sandstone
        for (int i = 0; i < 2; i++) {
            OreDictionary.registerOre("sandstoneYellow", new ItemStack(Blocks.SANDSTONE, 1, i));
        }
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("sandstoneYellow",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "sandstoneyellow", 1, i));
            OreDictionary.registerOre("sandstoneYellow",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "sandstone-scribbles", 1, i));
        }
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("sandstoneYellow",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "sandstoneyellow1", 1, i));
        }
        for (int i = 0; i < 8; i++) {
            OreDictionary.registerOre("sandstoneYellow",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "sandstoneyellow2", 1, i));
        }
        registerAutoChiselRecipe("sandstoneYellow");

        // Stone
        for (int i = 0; i < 4; i++) {
            OreDictionary.registerOre("stone", new ItemStack(Blocks.STONEBRICK, 1, i));
        }
        registerAutoChiselRecipe("stone");

        // Temple Block
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockTemple", GTEUtility.getModItem(Mods.Names.CHISEL, "temple", 1, i));
            OreDictionary.registerOre("blockTemple",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "templemossy", 1, i));
        }
        registerAutoChiselRecipe("blockTemple");

        // Tyrian
        for (int i = 0; i < 15; i++) {
            OreDictionary.registerOre("blockTyrian", GTEUtility.getModItem(Mods.Names.CHISEL, "tyrian", 1, i));
        }
        registerAutoChiselRecipe("blockTyrian");

        // Valentines' Block
        for (int i = 0; i < 10; i++) {
            OreDictionary.registerOre("blockValentine",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "valentines", 1, i));
        }
        registerAutoChiselRecipe("blockValentine");

        // Voidstone
        for (int i = 0; i < 8; i++) {
            OreDictionary.registerOre("blockVoidstone",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "voidstone", 1, i));
            OreDictionary.registerOre("blockVoidstone",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "energizedvoidstone", 1, i));
        }
        for (int i = 0; i < 15; i++) {
            OreDictionary.registerOre("blockVoidstone",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "voidstonerunic", 1, i));
        }
        registerAutoChiselRecipe("blockVoidstone");

        // Waterstone
        for (int i = 0; i < 16; i++) {
            OreDictionary.registerOre("blockWaterstone",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "waterstone", 1, i));
            OreDictionary.registerOre("blockWaterstone",
                    GTEUtility.getModItem(Mods.Names.CHISEL, "waterstone1", 1, i));
        }
        OreDictionary.registerOre("blockWaterstone", GTEUtility.getModItem(Mods.Names.CHISEL, "waterstone"));
        registerAutoChiselRecipe("blockWaterstone");
    }

    private static void registerAutoChiselRecipe(String oreDictName) {
        List<ItemStack> targets = OreDictionary.getOres(oreDictName);
        targets.forEach(target -> ChiselRecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .input(oreDictName)
                .notConsumable(target)
                .outputs(target)
                .duration(10).EUt(VH[ULV])
                .buildAndRegister());
    }
}
