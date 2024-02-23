package gtexpert.integration.eio.recipes;

import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.ore.OrePrefix.plate;

import net.minecraft.init.Items;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.core.GTUtil;

import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;

public class EIOItemsRecipe {

    public static void init() {
        // Soul Vial
        ModHandler.addShapedRecipe(true, "soul_vial",
                GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_soul_vial"),
                " S ", "G G", " G ",
                'S', new UnificationEntry(plate, GTEMaterials.Soularium),
                'G', GTEUtility.getModItem(Mods.Names.ENDER_IO, "block_fused_quartz"));

        // Basic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(GTUtil.batteryHull(GTEValues.eioVoltageTier), 1)
                .input(GTUtil.oreDictionaryCircuit(GTEValues.eioVoltageTier), 1)
                .fluidInputs(GTEMaterials.ElectricalSteel.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN())
                .duration(56).EUt(VA[GTEValues.eioVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Double-Layer Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN())
                .input(GTUtil.oreDictionaryCircuit(GTEValues.eioVoltageTier + 1), 1)
                .fluidInputs(GTEMaterials.EnergeticAlloy.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 1)
                .duration(56).EUt(VA[GTEValues.eioVoltageTier + 1])
                .withRecycling()
                .buildAndRegister();

        // Octadic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 2, 1)
                .input(GTUtil.oreDictionaryCircuit(GTEValues.eioVoltageTier + 2), 1)
                .fluidInputs(GTEMaterials.VibrantAlloy.getFluid(1152))
                .output(ModObject.itemBasicCapacitor.getItemNN(), 1, 2)
                .duration(56).EUt(VA[GTEValues.eioVoltageTier + 2])
                .withRecycling()
                .buildAndRegister();

        // Crystaline Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(ModObject.itemBasicCapacitor.getItemNN(), 1, 1)
                .input(GTUtil.oreDictionaryCircuit(GTEValues.eioVoltageTier + 1), 1)
                .fluidInputs(GTEMaterials.CrystallineAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorCrystalline.getItemNN(), 1)
                .duration(56).EUt(VA[GTEValues.eioVoltageTier + 1])
                .withRecycling()
                .buildAndRegister();

        // Melodic Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorCrystalline.getItemNN(), 2)
                .input(GTUtil.oreDictionaryCircuit(GTEValues.eioVoltageTier + 2), 1)
                .fluidInputs(GTEMaterials.MelodicAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorMelodic.getItemNN(), 1)
                .duration(56).EUt(VA[GTEValues.eioVoltageTier + 2])
                .withRecycling()
                .buildAndRegister();

        // Silver Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(GTUtil.batteryHull(GTEValues.eioVoltageTier), 1)
                .input(GTUtil.oreDictionaryCircuit(GTEValues.eioVoltageTier), 1)
                .fluidInputs(Materials.Silver.getFluid(1152))
                .output(EndergyObject.itemCapacitorSilver.getItemNN(), 1)
                .duration(56).EUt(VA[GTEValues.eioVoltageTier])
                .withRecycling()
                .buildAndRegister();

        // Endergenic Silver Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorSilver.getItemNN(), 2)
                .input(GTUtil.oreDictionaryCircuit(GTEValues.eioVoltageTier + 1), 1)
                .fluidInputs(GTEMaterials.EnergeticSilver.getFluid(1152))
                .output(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 1)
                .duration(56).EUt(VA[GTEValues.eioVoltageTier + 1])
                .withRecycling()
                .buildAndRegister();

        // Endergied Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorEnergeticSilver.getItemNN(), 2)
                .input(GTUtil.oreDictionaryCircuit(GTEValues.eioVoltageTier + 2), 1)
                .fluidInputs(GTEMaterials.VividAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorVivid.getItemNN(), 1)
                .duration(56).EUt(VA[GTEValues.eioVoltageTier + 2])
                .withRecycling()
                .buildAndRegister();

        // Stellar Capacitor
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .input(EndergyObject.itemCapacitorVivid.getItemNN(), 2)
                .input(GTUtil.oreDictionaryCircuit(GTEValues.eioVoltageTier + 3), 1)
                .input(Items.SHULKER_SHELL, 1)
                .fluidInputs(GTEMaterials.StellarAlloy.getFluid(1152))
                .output(EndergyObject.itemCapacitorStellar.getItemNN(), 1)
                .duration(56).EUt(VA[GTEValues.eioVoltageTier + 3])
                .withRecycling()
                .buildAndRegister();
    }
}
