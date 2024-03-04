package gtexpert.integration.ffm.loaders;

import static gregtech.loaders.recipe.WoodRecipeLoader.registerWoodTypeRecipe;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.Mods;

public class FFMWoodRecipe {

    private static final String ffmModId = "forestry";

    // tree name
    // used for fence gate, stair, door
    private static final String[] woodName = { "larch", "teak", "acacia", "lime",
            "chestnut", "wenge", "baobab", "sequoia",
            "kapok", "ebony", "mahogany", "balsa",
            "willow", "walnut", "greenheart", "cherry",
            "mahoe", "poplar", "palm", "papaya",
            "pine", "plum", "maple", "citrus",
            "giganteum", "ipe", "padauk", "cocobolo",
            "zebrawood" };

    // tree name (fireproof vanilla wood)
    // used for fence gate, stair
    private static final String[] woodNameVanilla = { "oak", "spruce", "birch", "jungle",
            "acacia", "dark_oak" };

    // metadata for log
    private static final int[] metaLog = { 0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0 };

    // metadata for planks, fence
    private static final int[] metaPlank = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

    // metadata for slab
    private static final int[] metaSlab = { 0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4 };

    public static void init() {
        // Forestry Wood (Normal)
        for (int i = 0; i < woodName.length; i++) {
            // Normal
            switch (i) {
                case 0, 1, 2, 3 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                                .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), "planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.0", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.0", 1, metaSlab[i]), "slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), "fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]),
                                        "fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());
                case 4, 5, 6, 7 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                                .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), "planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.1", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.0", 1, metaSlab[i]), "slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), "fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]),
                                        "fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 8, 9, 10, 11 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                                .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), "planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.2", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.1", 1, metaSlab[i]), "slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), "fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]),
                                        "fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 12, 13, 14, 15 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                                .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), "planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.3", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.1", 1, metaSlab[i]), "slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), "fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]),
                                        "fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 16, 17, 18, 19 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                                .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), "planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.4", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.2", 1, metaSlab[i]), "slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), "fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]),
                                        "fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 20, 21, 22, 23 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                                .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), "planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.5", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.2", 1, metaSlab[i]), "slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), "fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]),
                                        "fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 24, 25, 26, 27 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                                .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), "planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.6", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.3", 1, metaSlab[i]), "slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), "fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]),
                                        "fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 28 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                                .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), "planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.7", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.3", 1, metaSlab[i]), "slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), "fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]),
                                        "fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());
            }

            // Fireproof
            switch (i) {
                case 0, 1, 2, 3 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof_" + woodName[i])
                                .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.fireproof.0", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "fireproof_doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]),
                                        "fireproof_fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 4, 5, 6, 7 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof_" + woodName[i])
                                .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.fireproof.1", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "fireproof_doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]),
                                        "fireproof_fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 8, 9, 10, 11 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof_" + woodName[i])
                                .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.fireproof.2", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "fireproof_doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]),
                                        "fireproof_fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 12, 13, 14, 15 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof_" + woodName[i])
                                .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.fireproof.3", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "fireproof_doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]),
                                        "fireproof_fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 16, 17, 18, 19 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof_" + woodName[i])
                                .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.fireproof.4", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "fireproof_doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]),
                                        "fireproof_fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 20, 21, 22, 23 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof_" + woodName[i])
                                .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.fireproof.5", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "fireproof_doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]),
                                        "fireproof_fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 24, 25, 26, 27 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof_" + woodName[i])
                                .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.fireproof.6", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "fireproof_doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]),
                                        "fireproof_fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 28 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof_" + woodName[i])
                                .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.fireproof.7", 1, metaLog[i])).removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("doors." + woodName[i]), "fireproof_doors_" + woodName[i])
                                .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodName[i])
                                .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodName[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]),
                                        "fireproof_fence_gates_" + woodName[i])
                                .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                                .registerAllUnificationInfo()
                                .build());
            }
        }

        // Vanilla Wood (Fireproof)
        for (int i = 0; i < woodNameVanilla.length; i++) {
            switch (i) {
                case 0 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodNameVanilla[i])
                                .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodNameVanilla[i])
                                .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, metaLog[i]))
                                .removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem("wooden_door"), "fireproof_doors_" + woodNameVanilla[i])
                                .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodNameVanilla[i])
                                .fence(Mods.Forestry.getItem("fences.vanilla.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodNameVanilla[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.vanilla.fireproof." + woodNameVanilla[i]),
                                        "fireproof_fence_gates_" + woodNameVanilla[i])
                                .stairs(Mods.Forestry.getItem("stairs.vanilla.fireproof." + woodNameVanilla[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 1, 2, 3 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodNameVanilla[i])
                                .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodNameVanilla[i])
                                .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, metaLog[i]))
                                .removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem(woodNameVanilla[i] + "_door"),
                                        "fireproof_doors_" + woodNameVanilla[i])
                                .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodNameVanilla[i])
                                .fence(Mods.Forestry.getItem("fences.vanilla.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodNameVanilla[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.vanilla.fireproof." + woodNameVanilla[i]),
                                        "fireproof_fence_gates_" + woodNameVanilla[i])
                                .stairs(Mods.Forestry.getItem("stairs.vanilla.fireproof." + woodNameVanilla[i]))
                                .registerAllUnificationInfo()
                                .build());

                case 4, 5 -> registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof_" + woodNameVanilla[i])
                                .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_planks_" + woodName[i])
                                .log(Mods.Forestry.getItem("logs.vanilla.fireproof.1", 1, metaLog[i]))
                                .removeCharcoalRecipe()
                                .door(Mods.Forestry.getItem(woodNameVanilla[i] + "_door"),
                                        "fireproof_doors_" + woodNameVanilla[i])
                                .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, metaSlab[i]),
                                        "fireproof_slabs_" + woodNameVanilla[i])
                                .fence(Mods.Forestry.getItem("fences.vanilla.fireproof.0", 1, metaPlank[i]),
                                        "fireproof_fences_" + woodNameVanilla[i])
                                .fenceGate(Mods.Forestry.getItem("fence.gates.vanilla.fireproof." + woodNameVanilla[i]),
                                        "fireproof_fence_gates_" + woodNameVanilla[i])
                                .stairs(Mods.Forestry.getItem("stairs.vanilla.fireproof." + woodNameVanilla[i]))
                                .registerAllUnificationInfo()
                                .build());
            }
        }
    }

    public static void remove() {
        // Crafting Recipe
        for (String name : woodName) {
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "planks_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "slabs_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "doors_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "stairs_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fences_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fence_gates_" + name));

            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_planks_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_slabs_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_doors_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_stairs_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_fences_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_fence_gates_" + name));
        }

        for (String name : woodNameVanilla) {
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_planks_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_slabs_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_doors_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_stairs_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_fences_" + name));
            ModHandler.removeRecipeByName(new ResourceLocation(ffmModId, "fireproof_fence_gates_" + name));
        }

        // Smelting Recipe
        for (int i = 0; i < 4; i++) {
            ModHandler.removeFurnaceSmelting(Mods.Forestry.getItem("logs.0", 1, i));
            ModHandler.removeFurnaceSmelting(Mods.Forestry.getItem("logs.1", 1, i));
            ModHandler.removeFurnaceSmelting(Mods.Forestry.getItem("logs.2", 1, i));
            ModHandler.removeFurnaceSmelting(Mods.Forestry.getItem("logs.3", 1, i));
            ModHandler.removeFurnaceSmelting(Mods.Forestry.getItem("logs.4", 1, i));
            ModHandler.removeFurnaceSmelting(Mods.Forestry.getItem("logs.5", 1, i));
            ModHandler.removeFurnaceSmelting(Mods.Forestry.getItem("logs.6", 1, i));
        }

        ModHandler.removeFurnaceSmelting(Mods.Forestry.getItem("logs.", 1, 0));
    }
}
