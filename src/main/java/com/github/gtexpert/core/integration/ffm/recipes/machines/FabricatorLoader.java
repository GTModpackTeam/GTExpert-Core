package com.github.gtexpert.core.integration.ffm.recipes.machines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.ffm.FFMUtility;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;

import binnie.extratrees.wood.EnumETLog;
import forestry.api.arboriculture.*;

public class FabricatorLoader {

    public static void init() {
        // Fireproof Recipe
        if (!Mods.ForestryArboriculture.isModLoaded()) return;
        if (!Mods.ForestryApiculture.isModLoaded()) return;

        List<IWoodType> allWoodType = new ArrayList<>();
        Collections.addAll(allWoodType, EnumForestryWoodType.VALUES);
        Collections.addAll(allWoodType, EnumVanillaWoodType.VALUES);

        for (IWoodType woodType : allWoodType) {
            ItemStack planks = TreeManager.woodAccess.getStack(woodType, WoodBlockKind.PLANKS, false);
            ItemStack planksFireproof = TreeManager.woodAccess.getStack(woodType, WoodBlockKind.PLANKS, true);

            ItemStack logs = TreeManager.woodAccess.getStack(woodType, WoodBlockKind.LOG, false);
            ItemStack logsFireproof = TreeManager.woodAccess.getStack(woodType, WoodBlockKind.LOG, true);

            registerFireproofPlanksRecipe(planks, planksFireproof);
            registerFireproofLogsRecipe(logs, logsFireproof);
        }
        if (Mods.ExtraTrees.isModLoaded()) {
            for (EnumETLog log : EnumETLog.VALUES) {
                ItemStack logs = TreeManager.woodAccess.getStack(log, WoodBlockKind.LOG, false);
                ItemStack planks = log.getPlank().getStack(false);

                ItemStack logsFireproof = TreeManager.woodAccess.getStack(log, WoodBlockKind.LOG, true);
                ItemStack planksFireproof = log.getPlank().getStack(true);

                registerFireproofPlanksRecipe(planks, planksFireproof);
                registerFireproofLogsRecipe(logs, logsFireproof);
            }
        }
    }

    public static void registerFireproofPlanksRecipe(ItemStack planks, ItemStack planksFireproof) {
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .inputs(GTUtility.copy(5, planks))
                .inputs(Mods.Forestry.getItem("refractory_wax", 4))
                .fluidInputs(Materials.Glass.getFluid(500))
                .outputs(GTUtility.copy(5, planksFireproof))
                .EUt(10).duration(FFMUtility.timeFabricator(10)).buildAndRegister();
    }

    public static void registerFireproofLogsRecipe(ItemStack logs, ItemStack logsFireproof) {
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .inputs(logs)
                .inputs(Mods.Forestry.getItem("refractory_wax", 4))
                .fluidInputs(Materials.Glass.getFluid(500))
                .outputs(logsFireproof)
                .EUt(10).duration(FFMUtility.timeFabricator(10)).buildAndRegister();
    }
}
