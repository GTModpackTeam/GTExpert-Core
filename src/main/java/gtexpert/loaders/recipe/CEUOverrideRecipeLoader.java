package gtexpert.loaders.recipe;

import gregtech.api.GregTechAPI;
import gregtech.api.items.OreDictNames;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.metatileentities.MetaTileEntities;

import gregicality.multiblocks.api.AlloyBlastUtil;
import gregicality.multiblocks.api.unification.GCYMMaterialFlags;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;

import gtexpert.api.GTEValues;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CEUOverrideRecipeLoader {

    public static void init() {
        materials();
        items();
        blocks();
    }

    private static void materials() {
        // Iron Nugget
        ModHandler.addShapelessRecipe("wrought_iron_nugget", OreDictUnifier.get(nugget, Iron, 9),
                OreDictUnifier.get(ingot, Iron, 1));

        // Gold Nugget
        ModHandler.addShapelessRecipe("gold_nugget", OreDictUnifier.get(nugget, Gold, 9),
                OreDictUnifier.get(ingot, Gold, 1));

        // Wrought Iron Nugget
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(nugget, WroughtIron, 1),
                OreDictUnifier.get(nugget, WroughtIron, 1));

        // Wrought Iron Ingot
        ModHandler.addShapedRecipe("wrought_iron_ingot", OreDictUnifier.get(ingot, WroughtIron, 1), "XXX", "XXX", "XXX",
                'X', OreDictUnifier.get(nugget, WroughtIron, 1));

        // Stone Rod
        ModHandler.addMirroredShapedRecipe("stone_rod", OreDictUnifier.get(stick, Stone), "s", "S", 'S',
                new UnificationEntry(block, Stone));

        // Glowstone Dust
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Glowstone, 2));
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Glowstone, 2)
                .output(dust, Redstone, 1)
                .output(dust, Gold, 1)
                .duration(488).EUt(80)
                .buildAndRegister();

        // Netherrack Dust
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Netherrack, 1));
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Netherrack, 1)
                .chancedOutput(dustTiny, Gold, 620, 120)
                .chancedOutput(dustTiny, Redstone, 5600, 850)
                .chancedOutput(dustTiny, Coal, 5600, 850)
                .chancedOutput(dustTiny, Glowstone, 5600, 850)
                .chancedOutput(dust, Sulfur, 9900, 100)
                .duration(160).EUt(20)
                .buildAndRegister();

        // ########################################
        // Ice (Bug Fix)
        // ########################################
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                new ItemStack[] { OreDictUnifier.get(dust, Ice, 1) },
                new FluidStack[] { Ice.getFluid(144) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                new ItemStack[] { OreDictUnifier.get(block, Ice, 1) },
                new FluidStack[] { Ice.getFluid(144) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_HEATER_RECIPES,
                new ItemStack[] { IntCircuitIngredient.getIntegratedCircuit(1) },
                new FluidStack[] { Ice.getFluid(144) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                new ItemStack[] { SHAPE_MOLD_BLOCK.getStackForm() },
                new FluidStack[] { Ice.getFluid(144) });

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input(dust, Ice, 1)
                .fluidOutputs(Ice.getFluid(1000))
                .duration(6).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input(block, Ice, 1)
                .fluidOutputs(Ice.getFluid(1000))
                .duration(6).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Ice.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .duration(32).EUt(4)
                .buildAndRegister();

        // Block
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK.getStackForm())
                .fluidInputs(Ice.getFluid(1000))
                .output(block, Ice, 1)
                .duration(6).EUt(VA[ULV])
                .buildAndRegister();

        // ########################################
        // Quartzite (Bug Fix)
        // ########################################
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, OreDictUnifier.get(block, Quartzite, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES, OreDictUnifier.get(gem, Quartzite, 9));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FORGE_HAMMER_RECIPES, OreDictUnifier.get(block, Quartzite, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[] { OreDictUnifier.get(block, Quartzite, 1) },
                new FluidStack[] { Lubricant.getFluid(3) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[] { OreDictUnifier.get(block, Quartzite, 1) },
                new FluidStack[] { DistilledWater.getFluid(11) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[] { OreDictUnifier.get(block, Quartzite, 1) },
                new FluidStack[] { Water.getFluid(15) });

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .output(dust, Quartzite, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .output(gem, Quartzite, 4)
                .duration(20).EUt(2)
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(gem, Quartzite, 4)
                .output(block, Quartzite, 1)
                .duration(300).EUt(2)
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, Quartzite, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, Quartzite, 4)
                .duration(240).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, Quartzite, 1)
                .fluidInputs(Water.getFluid(15))
                .output(plate, Quartzite, 4)
                .duration(300).EUt(VA[LV])
                .buildAndRegister();

        // ########################################
        // Certus Quartz (Bug Fix)
        // ########################################
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, OreDictUnifier.get(block, CertusQuartz, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES, OreDictUnifier.get(gem, CertusQuartz, 9));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FORGE_HAMMER_RECIPES,
                OreDictUnifier.get(block, CertusQuartz, 1));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.EXTRACTOR_RECIPES,
                new ItemStack[] { OreDictUnifier.get(block, CertusQuartz, 1) },
                new FluidStack[] { CertusQuartz.getFluid(1296) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                new ItemStack[] { SHAPE_MOLD_BLOCK.getStackForm() },
                new FluidStack[] { CertusQuartz.getFluid(1296) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[] { OreDictUnifier.get(block, CertusQuartz, 1) },
                new FluidStack[] { Lubricant.getFluid(3) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[] { OreDictUnifier.get(block, CertusQuartz, 1) },
                new FluidStack[] { DistilledWater.getFluid(11) });
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES,
                new ItemStack[] { OreDictUnifier.get(block, CertusQuartz, 1) },
                new FluidStack[] { Water.getFluid(15) });

        // Fluid
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidOutputs(CertusQuartz.getFluid(576))
                .duration(20).EUt(VA[LV])
                .buildAndRegister();

        // Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .output(dust, CertusQuartz, 4)
                .duration(80).EUt(2)
                .buildAndRegister();

        // Gem
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .output(gem, CertusQuartz, 4)
                .duration(20).EUt(2)
                .buildAndRegister();

        // Block
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                .input(gem, CertusQuartz, 4)
                .output(block, CertusQuartz, 1)
                .duration(300).EUt(2)
                .buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(CertusQuartz.getFluid(576))
                .output(block, CertusQuartz, 1)
                .duration(20).EUt(VA[ULV])
                .buildAndRegister();

        // Plate
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidInputs(Lubricant.getFluid(3))
                .output(plate, CertusQuartz, 4)
                .duration(160).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidInputs(DistilledWater.getFluid(11))
                .output(plate, CertusQuartz, 4)
                .duration(240).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .input(block, CertusQuartz, 1)
                .fluidInputs(Water.getFluid(15))
                .output(plate, CertusQuartz, 4)
                .duration(300).EUt(VA[LV])
                .buildAndRegister();

        List<Material> materials = new ArrayList<>();
        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            if (material.hasProperty(PropertyKey.FLUID) && material.hasProperty(GCYMPropertyKey.ALLOY_BLAST)) {
                materials.add(material);
            }
        }
        for (Material material : materials) {
            vacuumFreezerMolten(material);
        }
    }

    private static void items() {
        // Tiny Pile of Ashes (Bookshelf Override)
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.ARC_FURNACE_RECIPES,
                new ItemStack[] { new ItemStack(Blocks.BOOKSHELF) },
                new FluidStack[] { Oxygen.getFluid(21) });
        RecipeMaps.ARC_FURNACE_RECIPES.recipeBuilder()
                .input("bookshelf")
                .fluidInputs(Oxygen.getFluid(21))
                .output(dustTiny, Ash, 16)
                .duration(1).EUt(VA[LV])
                .buildAndRegister();

        // Chad & Wood Pulp (Bookshelf Override)
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, new ItemStack(Blocks.BOOKSHELF));
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input("bookshelf")
                .output(dust, Paper, 9)
                .output(dust, Wood, 6)
                .duration(196).EUt(2)
                .buildAndRegister();
    }

    private static void blocks() {
        // Redstone Lamp
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_CEU, "redstone_lamp"));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Redstone, 4)
                .input(plate, Glowstone, 4)
                .output(Blocks.REDSTONE_LAMP)
                .duration(100).EUt(1)
                .buildAndRegister();

        // Crafting Station
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(plank, Wood, 4)
                .input(OreDictNames.chestWood.toString(), 2)
                .input(slab, Wood, 1)
                .input(Blocks.CRAFTING_TABLE)
                .output(MetaTileEntities.WORKBENCH)
                .duration(100).EUt(16)
                .buildAndRegister();

        // Ultra High Voltage 4x Battery Buffer
        ModHandler.addShapedRecipe(true, "gregtech.machine.battery_buffer.uhv.4",
                MetaTileEntities.BATTERY_BUFFER[0][UHV].getStackForm(),
                "WTW", "WMW",
                'M', MetaTileEntities.HULL[UHV].getStackForm(),
                'W', new UnificationEntry(wireGtQuadruple, Europium),
                'T', OreDictNames.chestWood);

        // Ultra High Voltage 8x Battery Buffer
        ModHandler.addShapedRecipe(true, "gregtech.machine.battery_buffer.uhv.8",
                MetaTileEntities.BATTERY_BUFFER[1][UHV].getStackForm(),
                "WTW", "WMW",
                'M', MetaTileEntities.HULL[UHV].getStackForm(),
                'W', new UnificationEntry(wireGtOctal, Europium),
                'T', OreDictNames.chestWood);

        // Ultra High Voltage 16x Battery Buffer
        ModHandler.addShapedRecipe(true, "gregtech.machine.battery_buffer.uhv.16",
                MetaTileEntities.BATTERY_BUFFER[2][UHV].getStackForm(),
                "WTW", "WMW",
                'M', MetaTileEntities.HULL[UHV].getStackForm(),
                'W', new UnificationEntry(wireGtHex, Europium),
                'T', OreDictNames.chestWood);

        // Ultra High Voltage Turbo Charger
        ModHandler.addShapedRecipe(true, "gregtech.machine.turbo_charger.uhv",
                MetaTileEntities.CHARGER[UHV].getStackForm(),
                "QTQ", "QMQ", "WCW",
                'M', MetaTileEntities.HULL[UHV].getStackForm(),
                'Q', new UnificationEntry(wireGtQuadruple, Europium),
                'W', new UnificationEntry(cableGtSingle, Europium),
                'C', WETWARE_MAINFRAME_UHV,
                'T', OreDictNames.chestWood);
    }

    private static void vacuumFreezerMolten(Material material) {
        // do not generate for disabled materials
        if (material.hasFlag(GCYMMaterialFlags.DISABLE_ALLOY_BLAST)) return;

        // get the amount of components
        final int componentAmount = material.getMaterialComponents().size();

        // ignore non-alloys
        if (componentAmount < 2) return;

        // get the output fluid
        Fluid molten = AlloyBlastUtil.getMoltenFluid(material);
        if (molten == null) return;

        // if the material does not need a vacuum freezer, exit
        if (!OrePrefix.ingotHot.doGenerateItem(material)) return;
        if (material.getBlastTemperature() < 5000) {
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .fluidInputs(new FluidStack(molten, 1000))
                    .fluidOutputs(material.getFluid(1000))
                    .duration((int) material.getMass() * 3)
                    .buildAndRegister();
        } else {
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .fluidInputs(new FluidStack(molten, 1000))
                    .fluidInputs(LiquidHelium.getFluid(750))
                    .fluidOutputs(Helium.getFluid(200))
                    .fluidOutputs(material.getFluid(1000))
                    .duration((int) material.getMass() * 3)
                    .buildAndRegister();
        }
    }
}
