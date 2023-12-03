package gtexpert.loaders.recipe;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GregTechAPI;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.items.OreDictNames;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregicality.multiblocks.api.unification.GCYMMaterialFlags;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;

public class CEUOverrideRecipeLoader {

    public static void init() {
        materials();
        items();
        blocks();
    }

    private static void materials() {
        // Vacuum Freezer
        List<Material> materials = new LinkedList<>(GregTechAPI.materialManager.getRegisteredMaterials());
        materials.forEach(CEUOverrideRecipeLoader::vacuumFreezerExtended);

        // Iron Nugget
        ModHandler.addShapelessRecipe("wrought_iron_nugget", OreDictUnifier.get(nugget, Materials.Iron, 9),
                OreDictUnifier.get(ingot, Materials.Iron, 1));

        // Gold Nugget
        ModHandler.addShapelessRecipe("gold_nugget", OreDictUnifier.get(nugget, Materials.Gold, 9),
                OreDictUnifier.get(ingot, Materials.Gold, 1));

        // Wrought Iron Nugget
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(nugget, Materials.WroughtIron, 1),
                OreDictUnifier.get(nugget, Materials.WroughtIron, 1));

        // Wrought Iron Ingot
        ModHandler.addShapedRecipe("wrought_iron_ingot", OreDictUnifier.get(ingot, Materials.WroughtIron, 1), "XXX",
                "XXX", "XXX",
                'X', OreDictUnifier.get(nugget, Materials.WroughtIron, 1));

        // Stone Rod
        ModHandler.addMirroredShapedRecipe("stone_rod", OreDictUnifier.get(stick, Materials.Stone), "s", "S", 'S',
                new UnificationEntry(block, Materials.Stone));

        // Glowstone Dust
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES,
                OreDictUnifier.get(dust, Materials.Glowstone, 2));
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Materials.Glowstone, 2)
                .output(dust, Materials.Redstone, 1)
                .output(dust, Materials.Gold, 1)
                .duration(488).EUt(80)
                .buildAndRegister();

        // Netherrack Dust
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES,
                OreDictUnifier.get(dust, Materials.Netherrack, 1));
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Materials.Netherrack, 1)
                .chancedOutput(dustTiny, Materials.Gold, 620, 120)
                .chancedOutput(dustTiny, Materials.Redstone, 5600, 850)
                .chancedOutput(dustTiny, Materials.Coal, 5600, 850)
                .chancedOutput(dustTiny, Materials.Glowstone, 5600, 850)
                .chancedOutput(dust, Materials.Sulfur, 9900, 100)
                .duration(160).EUt(20)
                .buildAndRegister();

        // ########################################
        // Ice (Bug Fix)
        // ########################################
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                new ItemStack[] { OreDictUnifier.get(dust, Materials.Ice, 1) },
                new FluidStack[] { Materials.Ice.getFluid(144) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                new ItemStack[] { OreDictUnifier.get(block, Materials.Ice, 1) },
                new FluidStack[] { Materials.Ice.getFluid(144) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_HEATER_RECIPES,
                new ItemStack[] { IntCircuitIngredient.getIntegratedCircuit(1) },
                new FluidStack[] { Materials.Ice.getFluid(144) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                new ItemStack[] { MetaItems.SHAPE_MOLD_BLOCK.getStackForm() },
                new FluidStack[] { Materials.Ice.getFluid(144) });

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input(dust, Materials.Ice, 1)
                .fluidOutputs(Materials.Ice.getFluid(1000))
                .duration(6).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input(block, Materials.Ice, 1)
                .fluidOutputs(Materials.Ice.getFluid(1000))
                .duration(6).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Materials.Ice.getFluid(1000))
                .fluidOutputs(Materials.Water.getFluid(1000))
                .duration(32).EUt(VH[ULV])
                .buildAndRegister();

        // Block
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_BLOCK.getStackForm())
                .fluidInputs(Materials.Ice.getFluid(1000))
                .output(block, Materials.Ice, 1)
                .duration(6).EUt(VA[ULV])
                .buildAndRegister();
    }

    private static void items() {
        // Book
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES, new ItemStack(Blocks.BOOKSHELF));
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input("bookshelf", 1)
                .outputs(new ItemStack(Items.BOOK, 3))
                .duration(300).EUt(2)
                .buildAndRegister();

        // Tiny Pile of Ashes (Bookshelf Override)
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.ARC_FURNACE_RECIPES,
                new ItemStack[] { new ItemStack(Blocks.BOOKSHELF) },
                new FluidStack[] { Materials.Oxygen.getFluid(21) });
        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .input("bookshelf")
                .fluidInputs(Materials.Oxygen.getFluid(21))
                .output(dustTiny, Materials.Ash, 16)
                .duration(21).EUt(VA[LV])
                .buildAndRegister();

        // Chad & Wood Pulp (Bookshelf Override)
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, new ItemStack(Blocks.BOOKSHELF));
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input("bookshelf")
                .output(dust, Materials.Paper, 9)
                .output(dust, Materials.Wood, 6)
                .duration(1470).EUt(2)
                .buildAndRegister();
    }

    private static void blocks() {
        // Enchantment Table
        ModHandler.removeRecipeByOutput(new ItemStack(Blocks.ENCHANTING_TABLE));
        ModHandler.addShapedRecipe("enchantment_table", new ItemStack(Blocks.ENCHANTING_TABLE),
                "DCD", "PBP", "DPD",
                'D', OreDictUnifier.get(gem, Materials.Diamond),
                'C', new ItemStack(Blocks.CARPET, 1, 14),
                'P', OreDictUnifier.get(plate, Materials.Obsidian),
                'B', "bookshelf");

        // Redstone Lamp
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES,
                OreDictUnifier.get(dust, Materials.Redstone, 4),
                OreDictUnifier.get(dust, Materials.Glowstone, 4));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Materials.Redstone, 4)
                .input(plate, Materials.Glowstone, 4)
                .output(Blocks.REDSTONE_LAMP)
                .duration(100).EUt(1)
                .buildAndRegister();

        // Carpet
        if (ConfigHolder.recipes.hardMiscRecipes) {
            for (int i = 0; i < Materials.CHEMICAL_DYES.length; i++) {
                EnumDyeColor color = EnumDyeColor.byMetadata(i);
                String colorName = color.getName().equals("silver") ? "light_gray" : color.getName();

                // Remove vanilla recipes
                ModHandler.removeRecipeByOutput(new ItemStack(Blocks.CARPET, 3, i));
                GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                        new ItemStack[] { new ItemStack(Blocks.WOOL, 2, i) },
                        new FluidStack[] { Materials.Lubricant.getFluid(2) });
                GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                        new ItemStack[] { new ItemStack(Blocks.WOOL, 2, i) },
                        new FluidStack[] { Materials.DistilledWater.getFluid(3) });
                GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                        new ItemStack[] { new ItemStack(Blocks.WOOL, 2, i) },
                        new FluidStack[] { Materials.Water.getFluid(4) });

                // Add GT recipes
                ModHandler.addMirroredShapedRecipe(colorName + "_wool", new ItemStack(Blocks.CARPET, 1, i),
                        "WW ",
                        'W', new ItemStack(Blocks.WOOL, 1, i));
                ModHandler.addMirroredShapedRecipe(colorName + "_wool_saw", new ItemStack(Blocks.CARPET, 2, i),
                        "WWs",
                        'W', new ItemStack(Blocks.WOOL, 1, i));

                // Add GT cutter recipes
                RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(Blocks.WOOL, 1, i))
                        .fluidInputs(Materials.Lubricant.getFluid(1))
                        .outputs(new ItemStack(Blocks.CARPET, 3, i))
                        .duration(50).EUt(7)
                        .buildAndRegister();
                RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(Blocks.WOOL, 1, i))
                        .fluidInputs(Materials.DistilledWater.getFluid(3))
                        .outputs(new ItemStack(Blocks.CARPET, 3, i))
                        .duration(100).EUt(7)
                        .buildAndRegister();
                RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(Blocks.WOOL, 1, i))
                        .fluidInputs(Materials.Water.getFluid(4))
                        .outputs(new ItemStack(Blocks.CARPET, 3, i))
                        .duration(150).EUt(7)
                        .buildAndRegister();
            }
        }

        // Crafting Station
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(plank, Materials.Wood, 4)
                .input(OreDictNames.chestWood.toString(), 2)
                .input(slab, Materials.Wood, 1)
                .input(Blocks.CRAFTING_TABLE)
                .output(MetaTileEntities.WORKBENCH)
                .duration(100).EUt(VH[LV])
                .buildAndRegister();

        // Ultra High Voltage 4x Battery Buffer
        ModHandler.addShapedRecipe(true, "gregtech.machine.battery_buffer.uhv.4",
                MetaTileEntities.BATTERY_BUFFER[0][UHV].getStackForm(),
                "WTW", "WMW",
                'M', MetaTileEntities.HULL[UHV].getStackForm(),
                'W', new UnificationEntry(wireGtQuadruple, Materials.Europium),
                'T', OreDictNames.chestWood);

        // Ultra High Voltage 8x Battery Buffer
        ModHandler.addShapedRecipe(true, "gregtech.machine.battery_buffer.uhv.8",
                MetaTileEntities.BATTERY_BUFFER[1][UHV].getStackForm(),
                "WTW", "WMW",
                'M', MetaTileEntities.HULL[UHV].getStackForm(),
                'W', new UnificationEntry(wireGtOctal, Materials.Europium),
                'T', OreDictNames.chestWood);

        // Ultra High Voltage 16x Battery Buffer
        ModHandler.addShapedRecipe(true, "gregtech.machine.battery_buffer.uhv.16",
                MetaTileEntities.BATTERY_BUFFER[2][UHV].getStackForm(),
                "WTW", "WMW",
                'M', MetaTileEntities.HULL[UHV].getStackForm(),
                'W', new UnificationEntry(wireGtHex, Materials.Europium),
                'T', OreDictNames.chestWood);

        // Ultra High Voltage Turbo Charger
        ModHandler.addShapedRecipe(true, "gregtech.machine.turbo_charger.uhv",
                MetaTileEntities.CHARGER[UHV].getStackForm(),
                "QTQ", "QMQ", "WCW",
                'M', MetaTileEntities.HULL[UHV].getStackForm(),
                'Q', new UnificationEntry(wireGtQuadruple, Materials.Europium),
                'W', new UnificationEntry(cableGtSingle, Materials.Europium),
                'C', MetaItems.WETWARE_MAINFRAME_UHV,
                'T', OreDictNames.chestWood);
    }

    /**
     * Vacuum Freezer to extended recipes
     *
     * @param material The material to add recipes for
     */
    private static void vacuumFreezerExtended(@NotNull Material material) {
        // Do not generate for disabled materials
        if (material.hasFlag(GCYMMaterialFlags.NO_ALLOY_BLAST_RECIPES)) return;

        // Check if the material has a blast recipe
        if (!material.hasProperty(GCYMPropertyKey.ALLOY_BLAST)) return;

        // Check if the material has a molten fluid
        Fluid molten = material.getFluid(GCYMFluidStorageKeys.MOLTEN);
        if (molten == null) return;

        // Get the vacuum freezer EUt and duration
        BlastProperty property = material.getProperty(PropertyKey.BLAST);
        int vacuumEUt = property.getVacuumEUtOverride() != -1 ? property.getVacuumEUtOverride() : VA[MV];
        int vacuumDuration = property.getVacuumDurationOverride() != -1 ? property.getVacuumDurationOverride() :
                (int) material.getMass() * 3;

        // Check if the material has a blast temperature above 5000K
        if (property.getBlastTemperature() > 5000) {
            if (material.hasFlag(GENERATE_PLATE)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_PLATE)
                        .fluidInputs(new FluidStack(molten, 144))
                        .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 500))
                        .fluidOutputs(Materials.Helium.getFluid(250))
                        .output(plate, material, 1)
                        .duration(vacuumDuration)
                        .EUt(vacuumEUt)
                        .buildAndRegister();

                if (GTEValues.isModLoadedDEDA()) {
                    RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                            .notConsumable(MetaItems.SHAPE_MOLD_PLATE)
                            .fluidInputs(new FluidStack(molten, 144))
                            .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                            .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                            .output(plate, material, 1)
                            .duration(vacuumDuration / 2)
                            .EUt(vacuumEUt)
                            .buildAndRegister();
                }
            }
            if (material.hasFlag(GENERATE_SMALL_GEAR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_GEAR_SMALL)
                        .fluidInputs(new FluidStack(molten, 144))
                        .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 500))
                        .fluidOutputs(Materials.Helium.getFluid(250))
                        .output(gearSmall, material, 1)
                        .duration(vacuumDuration)
                        .EUt(vacuumEUt)
                        .buildAndRegister();

                if (GTEValues.isModLoadedDEDA()) {
                    RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                            .notConsumable(MetaItems.SHAPE_MOLD_GEAR_SMALL)
                            .fluidInputs(new FluidStack(molten, 144))
                            .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                            .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                            .output(gearSmall, material, 1)
                            .duration(vacuumDuration / 2)
                            .EUt(vacuumEUt)
                            .buildAndRegister();
                }
            }
            if (material.hasFlag(GENERATE_GEAR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_GEAR)
                        .fluidInputs(new FluidStack(molten, 576))
                        .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
                        .fluidOutputs(Materials.Helium.getFluid(1000))
                        .output(gear, material, 1)
                        .duration(vacuumDuration * 4)
                        .EUt(vacuumEUt)
                        .buildAndRegister();

                if (GTEValues.isModLoadedDEDA()) {
                    RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                            .notConsumable(MetaItems.SHAPE_MOLD_GEAR)
                            .fluidInputs(new FluidStack(molten, 576))
                            .fluidInputs(GTEMaterials.Cryotheum.getFluid(1000))
                            .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 200))
                            .output(gear, material, 1)
                            .duration(vacuumDuration * 2)
                            .EUt(vacuumEUt)
                            .buildAndRegister();
                }
            }
            if (material.hasFlag(GENERATE_ROTOR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_ROTOR)
                        .fluidInputs(new FluidStack(molten, 576))
                        .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 2000))
                        .fluidOutputs(Materials.Helium.getFluid(1000))
                        .output(rotor, material, 1)
                        .duration(vacuumDuration * 4)
                        .EUt(vacuumEUt)
                        .buildAndRegister();

                if (GTEValues.isModLoadedDEDA()) {
                    RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                            .notConsumable(MetaItems.SHAPE_MOLD_ROTOR)
                            .fluidInputs(new FluidStack(molten, 576))
                            .fluidInputs(GTEMaterials.Cryotheum.getFluid(1000))
                            .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 200))
                            .output(rotor, material, 1)
                            .duration(vacuumDuration / 2)
                            .EUt(vacuumEUt)
                            .buildAndRegister();
                }
            }
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .fluidInputs(new FluidStack(molten, 144))
                    .fluidInputs(Materials.Helium.getFluid(FluidStorageKeys.LIQUID, 500))
                    .fluidOutputs(Materials.Helium.getFluid(250))
                    .fluidOutputs(material.getFluid(144))
                    .duration(vacuumDuration)
                    .EUt(vacuumEUt)
                    .buildAndRegister();

            if (GTEValues.isModLoadedDEDA()) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .input(ingotHot, material, 1)
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                        .output(ingot, material, 1)
                        .duration(vacuumDuration / 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .circuitMeta(1)
                        .fluidInputs(new FluidStack(molten, 144))
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                        .fluidOutputs(material.getFluid(144))
                        .duration(vacuumDuration / 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
        } else {
            if (material.hasFlag(GENERATE_PLATE)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_PLATE)
                        .fluidInputs(new FluidStack(molten, 144))
                        .output(plate, material, 1)
                        .duration(vacuumDuration)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            if (material.hasFlag(GENERATE_SMALL_GEAR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_GEAR_SMALL)
                        .fluidInputs(new FluidStack(molten, 144))
                        .output(gearSmall, material, 1)
                        .duration(vacuumDuration)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            if (material.hasFlag(GENERATE_GEAR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_GEAR)
                        .fluidInputs(new FluidStack(molten, 576))
                        .output(gear, material, 1)
                        .duration(vacuumDuration * 4)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            if (material.hasFlag(GENERATE_ROTOR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_ROTOR)
                        .fluidInputs(new FluidStack(molten, 576))
                        .output(rotor, material, 1)
                        .duration(vacuumDuration * 4)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .fluidInputs(new FluidStack(molten, 144))
                    .fluidOutputs(material.getFluid(144))
                    .duration(vacuumDuration)
                    .EUt(vacuumEUt)
                    .buildAndRegister();
        }
    }
}
