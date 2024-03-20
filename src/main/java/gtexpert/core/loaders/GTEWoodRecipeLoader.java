package gtexpert.core.loaders;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.GTELog;
import gtexpert.api.util.Mods;
import gtexpert.core.GTEConfigHolder;
import gtexpert.core.GTERecipeMaps;

public class GTEWoodRecipeLoader {

    public static void logsEmptyCheck(@NotNull WoodTypeEntry entry) {
        logsEmptyCheck(false, entry);
    }

    public static void logsEmptyCheck(boolean throwException, @NotNull WoodTypeEntry entry) {
        if (entry.log.isEmpty()) {
            String message = "Could not find logs form of WoodTypeEntry '" + entry.woodName + "'.";

            if (throwException) {
                throw new IllegalStateException(message);
            } else {
                GTELog.logger.debug(message);
            }
        }
    }

    public static void planksEmptyCheck(@NotNull WoodTypeEntry entry) {
        planksEmptyCheck(false, entry);
    }

    public static void planksEmptyCheck(boolean throwException, @NotNull WoodTypeEntry entry) {
        if (entry.planks.isEmpty()) {
            String message = "Could not find planks form of WoodTypeEntry '" + entry.woodName + "'.";

            if (throwException) {
                throw new IllegalStateException(
                        "Could not find planks form of WoodTypeEntry '" + entry.woodName + "'.");
            } else {
                GTELog.logger.debug(message);
            }
        }
    }

    public static void addSawmillRecipes(@NotNull WoodTypeEntry entry) {
        logsEmptyCheck(entry);
        planksEmptyCheck(entry);

        if (!entry.log.isEmpty()) {
            GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .inputs(GTUtility.copy(6, entry.log))
                    .fluidInputs(Materials.Water.getFluid(1000))
                    .outputs(GTUtility.copy(48, entry.planks))
                    .output(dust, Materials.Wood, 12)
                    .duration(600).EUt(VA[LV])
                    .buildAndRegister();
            GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .inputs(GTUtility.copy(6, entry.log))
                    .fluidInputs(Materials.Water.getFluid(2500))
                    .outputs(GTUtility.copy(60, entry.planks))
                    .duration(800).EUt(VA[LV])
                    .buildAndRegister();
        }
    }

    public static void addCutterRecipes(@NotNull WoodTypeEntry entry) {
        logsEmptyCheck(entry);
        planksEmptyCheck(entry);

        if (!entry.log.isEmpty()) {
            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                    .inputs(entry.log.copy())
                    .fluidInputs(Materials.Lubricant.getFluid(1))
                    .outputs(GTUtility.copy(6, entry.planks))
                    .output(dust, Materials.Wood, 2)
                    .duration(200).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                    .inputs(entry.log.copy())
                    .fluidInputs(Materials.DistilledWater.getFluid(3))
                    .outputs(GTUtility.copy(6, entry.planks))
                    .output(dust, Materials.Wood, 2)
                    .duration(300).EUt(VA[ULV])
                    .buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                    .inputs(entry.log.copy())
                    .fluidInputs(Materials.Water.getFluid(4))
                    .outputs(GTUtility.copy(6, entry.planks))
                    .output(dust, Materials.Wood, 2)
                    .duration(400).EUt(VA[ULV])
                    .buildAndRegister();
        }
    }

    public static void addPlankRecipes(@NotNull WoodTypeEntry entry) {
        logsEmptyCheck(entry);
        planksEmptyCheck(entry);
        final String modId = entry.modid;
        final String name = entry.woodName;
        boolean hasPlanksRecipe = entry.planksRecipeName != null;

        int plank_normal = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 1 : 2;
        plank_normal = ConfigHolder.recipes.nerfWoodCrafting ? plank_normal : 4;
        int plank_saw = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 2 : 4;
        plank_saw = ConfigHolder.recipes.nerfWoodCrafting ? plank_saw : 6;

        if (hasPlanksRecipe) {
            ModHandler.addShapelessRecipe(modId + "_" + entry.planksRecipeName,
                    GTUtility.copy(plank_normal, entry.planks), entry.log.copy());
            ModHandler.addMirroredShapedRecipe(modId + "_" + entry.planksRecipeName + "_saw",
                    GTUtility.copy(plank_saw, entry.planks), "s", "L",
                    'L', entry.log.copy());
        } else {
            ModHandler.addShapelessRecipe(modId + "_" + name + "_plank",
                    GTUtility.copy(plank_normal, entry.planks), entry.log.copy());
            ModHandler.addMirroredShapedRecipe(modId + "_" + name + "_plank_saw",
                    GTUtility.copy(plank_saw, entry.planks), "s", "L",
                    'L', entry.log.copy());
        }
    }

    public static void addSlabRecipes(@NotNull WoodTypeEntry entry) {
        planksEmptyCheck(entry);
        final String modId = entry.modid;
        final String name = entry.woodName;
        boolean hasSlabsRecipe = entry.slabRecipeName != null;

        ModHandler.addShapedRecipe(
                hasSlabsRecipe ? modId + "_" + entry.slabRecipeName + "_saw" : modId + "_" + name + "_slab_saw",
                GTUtility.copy(2, entry.slab), "sP ",
                'P', entry.planks.copy());
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(entry.planks.copy())
                .fluidInputs(Materials.Lubricant.getFluid(1))
                .outputs(GTUtility.copy(2, entry.slab))
                .duration(200).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(entry.planks.copy())
                .fluidInputs(Materials.DistilledWater.getFluid(3))
                .outputs(GTUtility.copy(2, entry.slab))
                .duration(300).EUt(VA[ULV])
                .buildAndRegister();
        RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                .inputs(entry.planks.copy())
                .fluidInputs(Materials.Water.getFluid(4))
                .outputs(GTUtility.copy(2, entry.slab))
                .duration(400).EUt(VA[ULV])
                .buildAndRegister();
    }

    public static void addFenceRecipes(@NotNull WoodTypeEntry entry) {
        planksEmptyCheck(entry);
        final String modId = entry.modid;
        final String name = entry.woodName;
        boolean hasFencesRecipe = entry.fenceRecipeName != null;

        ModHandler.addShapedRecipe(
                hasFencesRecipe ? modId + "_" + entry.fenceRecipeName : modId + "_" + name + "_fence",
                entry.fence.copy(), "PSP", "PSP", "PSP",
                'P', entry.planks.copy(),
                'S', new UnificationEntry(stick, Materials.Wood));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(entry.planks.copy())
                .outputs(entry.fence.copy())
                .duration(100).EUt(4)
                .buildAndRegister();
    }

    public static void addFenceGateRecipes(@NotNull WoodTypeEntry entry) {
        planksEmptyCheck(entry);
        final String modId = entry.modid;
        final String name = entry.woodName;
        boolean hasFenceGatesRecipe = entry.fenceGateRecipeName != null;

        if (hasFenceGatesRecipe) {
            ModHandler.addShapedRecipe(modId + "_" + entry.fenceGateRecipeName,
                    entry.fenceGate.copy(), "F F", "SPS", "SPS",
                    'F', Mods.Vanilla.getItem("flint"),
                    'S', new UnificationEntry(stick, Materials.Wood),
                    'P', entry.planks.copy());
            ModHandler.addShapedRecipe(modId + "_" + entry.fenceGateRecipeName + "_screw",
                    entry.fenceGate.copy(), "IdI", "SPS", "SPS",
                    'I', new UnificationEntry(screw, Materials.Iron),
                    'S', new UnificationEntry(stick, Materials.Wood),
                    'P', entry.planks.copy());
        } else {
            ModHandler.addShapedRecipe(modId + "_" + name + "_fence_gate",
                    entry.fenceGate.copy(), "F F", "SPS", "SPS",
                    'F', Mods.Vanilla.getItem("flint"),
                    'S', new UnificationEntry(stick, Materials.Wood),
                    'P', entry.planks.copy());
            ModHandler.addShapedRecipe(modId + "_" + name + "_fence_gate_screw",
                    entry.fenceGate.copy(), "IdI", "SPS", "SPS",
                    'I', new UnificationEntry(screw, Materials.Iron),
                    'S', new UnificationEntry(stick, Materials.Wood),
                    'P', entry.planks.copy());
        }

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(GTUtility.copy(2, entry.planks))
                .input(stick, Materials.Wood, 2)
                .outputs(entry.fenceGate.copy())
                .duration(100).EUt(4)
                .buildAndRegister();
    }

    public static void addStairsRecipes(@NotNull WoodTypeEntry entry) {
        planksEmptyCheck(entry);
        final String modId = entry.modid;
        final String name = entry.woodName;

        ModHandler.addShapedRecipe(modId + "_" + name + "_stairs",
                GTUtility.copy(4, entry.stairs), "P  ", "PP ", "PPP",
                'P', entry.planks.copy());
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .inputs(GTUtility.copy(6, entry.planks))
                .outputs(GTUtility.copy(4, entry.stairs))
                .duration(100).EUt(1)
                .buildAndRegister();
    }

    public static void addDoorRecipes(@NotNull WoodTypeEntry entry) {
        planksEmptyCheck(entry);
        final String modId = entry.modid;
        final String name = entry.woodName;
        boolean hasDoorRecipe = entry.doorRecipeName != null;

        ModHandler.addShapedRecipe(
                hasDoorRecipe ? modId + "_" + entry.doorRecipeName : modId + "_" + name + "_door",
                entry.door.copy(), "PTd", "PRS", "PPs",
                'P', entry.planks.copy(),
                'T', Mods.Vanilla.getItem("trapdoor"),
                'R', new UnificationEntry(ring, Materials.Iron),
                'S', new UnificationEntry(screw, Materials.Iron));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(Mods.Vanilla.getItem("trapdoor"))
                .inputs(GTUtility.copy(4, entry.planks))
                .fluidInputs(Materials.Iron.getFluid(16))
                .outputs(entry.door.copy())
                .duration(400).EUt(4)
                .buildAndRegister();
    }

    public static void removePlankRecipes(@NotNull WoodTypeEntry entry) {
        removePlankRecipes(false, entry, entry.modid);
    }

    public static void removePlankRecipes(boolean removeSawRecipes, @NotNull WoodTypeEntry entry) {
        removePlankRecipes(removeSawRecipes, entry, entry.modid);
    }

    public static void removePlankRecipes(boolean removeSawRecipes, @NotNull WoodTypeEntry entry,
                                          @NotNull String otherModId) {
        final String name = entry.woodName;
        final String modId = otherModId.isEmpty() ? entry.modid : otherModId;
        final boolean hasPlanksRecipe = entry.planksRecipeName != null;

        ModHandler.removeRecipeByName(
                new ResourceLocation(modId, hasPlanksRecipe ? entry.planksRecipeName : name + "_planks"));

        if (!removeSawRecipes) return;
        ModHandler.removeRecipeByName(
                new ResourceLocation(modId, hasPlanksRecipe ? entry.planksRecipeName + "_saw" : name + "_planks_saw"));
    }

    public static void removePlankSlsbRecipes(@NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;

        ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, "slabs_" + name));
    }

    public static void removeFenceRecipes(@NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;

        ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, "fences_" + name));
    }

    public static void removeFenceGateRecipes(@NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;

        ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, "fence_gates_" + name));
    }

    public static void removeStairsRecipes(@NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;

        ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, "stairs_" + name));
    }

    public static void removeDoorRecipes(@NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;

        ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, "doors_" + name));
    }
}
