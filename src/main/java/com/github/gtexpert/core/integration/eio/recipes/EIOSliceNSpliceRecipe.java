package com.github.gtexpert.core.integration.eio.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Items;

import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;

import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.integration.eio.EnderIORecipeMaps;

import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;

public class EIOSliceNSpliceRecipe {

    public static void init() {
        // Zombie Electrode
        EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.EnergeticAlloy)
                .input(Items.SKULL)
                .input(plate, GTEMaterials.EnergeticAlloy)
                .input(MetaItems.SILICON_WAFER, 1)
                .input("craftCapacitorEIO", 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 40)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Z-Logic Controller
        EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(Items.SKULL, 1, 2)
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(dust, Materials.Redstone)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 41)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Ender Resonator
        EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(ModObject.blockEndermanSkull.getItemNN())
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(plate, GTEMaterials.VibrantAlloy)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 43)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Skeletal Controller
        EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(Items.SKULL)
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input("craftCapacitorEIO", 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.itemMaterial.getItemNN(), 1, 45)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Guardian Diode
        EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.EnergeticAlloy)
                .input(Items.PRISMARINE_SHARD)
                .input(plate, GTEMaterials.EnergeticAlloy)
                .input(ModObject.itemMaterial.getItemNN(), 1, 14)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(ModObject.itemMaterial.getItemNN(), 1, 14)
                .output(ModObject.itemMaterial.getItemNN(), 1, 56)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Tormented Enderman Head
        EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(ModObject.blockEndermanSkull.getItemNN())
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input("craftCapacitorEIO", 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.blockEndermanSkull.getItemNN(), 1, 2)
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Totemic Capacitor
        EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES.recipeBuilder()
                .input(plate, GTEMaterials.Soularium)
                .input(Items.TOTEM_OF_UNDYING)
                .input(plate, GTEMaterials.Soularium)
                .input(MetaItems.SILICON_WAFER, 1)
                .input(EndergyObject.itemCapacitorMelodic.getItemNN(), 1)
                .input(MetaItems.SILICON_WAFER, 1)
                .output(ModObject.blockEndermanSkull.getItemNN(), 1, 2)
                .duration(400).EUt(VA[LV]).buildAndRegister();
    }
}
