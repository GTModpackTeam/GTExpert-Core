package gtexpert.integration.ffm.loaders;

import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.Mods;

import static gregtech.loaders.recipe.WoodRecipeLoader.registerWoodTypeRecipe;

public class FFMWoodRecipe {

    private static final String ffmModId = "forestry";

    // tree name
    // used for fence gate, stair, door
    private static final String[] woodName = {"larch", "teak", "acacia", "lime",
            "chestnut", "wenge", "baobab", "sequoia",
            "kapok", "ebony", "mahogany", "balsa",
            "willow", "walnut", "greenheart", "cherry",
            "mahoe", "poplar", "palm", "papaya",
            "pine", "plum", "maple", "citrus",
            "giganteum", "ipe", "padauk", "cocobolo",
            "zebrawood"};

    // tree name (fireproof vanilla wood)
    // used for fence gate, stair
    private static final String[] woodNameVanilla = {"oak", "spruce", "birch", "jungle",
            "acacia", "dark_oak"};

    // metadata for log
    private static final int[] metaLog = {0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0};

    // metadata for planks, fence
    private static final int[] metaPlank = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    // metadata for slab
    private static final int[] metaSlab = {0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4};

    public static void init() {

        // Forestry Wood
        for (int i = 0; i < woodName.length; i++) {
            if (i < 4) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                        .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), "planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.0", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                        .slab(Mods.Forestry.getItem("slabs.0", 1, metaSlab[i]), "slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), "fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), "fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), "fireproof_planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.0", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]), "fireproof_slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), "fireproof_fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), "fireproof_fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (4 <= i || i < 8) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                        .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), "planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.1", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                        .slab(Mods.Forestry.getItem("slabs.0", 1, metaSlab[i]), "slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), "fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), "fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), "fireproof_planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.1", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]), "fireproof_slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), "fireproof_fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), "fireproof_fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (8 <= i || i < 12) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                        .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), "planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.2", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                        .slab(Mods.Forestry.getItem("slabs.1", 1, metaSlab[i]), "slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), "fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), "fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), "fireproof_planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.2", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, metaSlab[i]), "fireproof_slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), "fireproof_fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), "fireproof_fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (12 <= i || i < 16) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                        .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), "planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.3", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                        .slab(Mods.Forestry.getItem("slabs.1", 1, metaSlab[i]), "slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), "fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), "fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), "fireproof_planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.3", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, metaSlab[i]), "fireproof_slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), "fireproof_fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), "fireproof_fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (16 <= i || i < 20) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                        .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), "planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.4", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                        .slab(Mods.Forestry.getItem("slabs.2", 1, metaSlab[i]), "slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), "fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), "fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]), "fireproof_planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.4", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, metaSlab[i]), "fireproof_slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]), "fireproof_fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), "fireproof_fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (20 <= i || i < 24) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                        .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), "planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.5", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                        .slab(Mods.Forestry.getItem("slabs.2", 1, metaSlab[i]), "slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), "fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), "fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]), "fireproof_planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.5", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, metaSlab[i]), "fireproof_slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]), "fireproof_fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), "fireproof_fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (24 <= i || i < 28) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                        .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), "planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.6", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                        .slab(Mods.Forestry.getItem("slabs.3", 1, metaSlab[i]), "slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), "fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), "fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]), "fireproof_planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.6", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, metaSlab[i]), "fireproof_slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]), "fireproof_fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), "fireproof_fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (28 == i) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, woodName[i])
                        .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), "planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.7", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), "doors_" + woodName[i])
                        .slab(Mods.Forestry.getItem("slabs.3", 1, metaSlab[i]), "slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), "fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), "fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]), "fireproof_planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.7", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, metaSlab[i]), "fireproof_slabs_" + woodName[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]), "fireproof_fences_" + woodName[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), "fireproof_fence_gates_" + woodName[i] )
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
        }

        // Vanilla Wood (Fireproof)
        for (int i = 0; i < 6; i++) {
            if (i < 4) {
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodNameVanilla[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), "fireproof_planks_" + woodNameVanilla[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.0", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]), "fireproof_slabs_" + woodNameVanilla[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), "fireproof_fences_" + woodNameVanilla[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodNameVanilla[i]), "fireproof_fence_gates_" + woodNameVanilla[i])
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodNameVanilla[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (4 <= i) {
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        ffmModId, "fireproof." + woodNameVanilla[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), "fireproof_planks_" + woodName[i])
                        .log(Mods.Forestry.getItem("logs.fireproof.1", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]), "fireproof_slabs_" + woodNameVanilla[i])
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), "fireproof_fences_" + woodNameVanilla[i])
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodNameVanilla[i]), "fireproof_fence_gates_" + woodNameVanilla[i])
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodNameVanilla[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
        }
    }
}
