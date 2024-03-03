package gtexpert.integration.ffm.loaders;

import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.Mods;

import static gregtech.loaders.recipe.WoodRecipeLoader.registerWoodTypeRecipe;

public class FFMWoodRecipe {

    // tree name
    // used for fence gate, stair, door
    static final String[] woodName = {"larch", "teak", "acacia", "lime",
            "chestnut", "wenge", "baobab", "sequoia",
            "kapok", "ebony", "mahogany", "balsa",
            "willow", "walnut", "greenheart", "cherry",
            "mahoe", "poplar", "palm", "papaya",
            "pine", "plum", "maple", "citrus",
            "giganteum", "ipe", "padauk", "cocobolo",
            "zebrawood"};

    // tree name (fireproof vanilla wood)
    // used for fence gate, stair
    static final String[] woodNameVanilla = {"oak", "spruce", "birch", "jungle",
            "acacia", "dark_oak"};

    // metadata for log
    static final int[] metaLog = {0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0, 1, 2, 3,
            0};

    // metadata for planks, fence
    static final int[] metaPlank = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    // metadata for slab
    static final int[] metaSlab = {0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4, 5, 6, 7,
            0, 1, 2, 3, 4};

    public static void init() {
        // Forestry Wood
        for (int i = 0; i < woodName.length; i++) {
            if (i < 4) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), woodName[i])
                        .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), woodName[i] + "_planks")
                        .log(Mods.Forestry.getItem("logs.0", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), woodName[i] + "_door")
                        .slab(Mods.Forestry.getItem("slabs.0", 1, metaSlab[i]), woodName[i] + "_wooden_slab")
                        .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), woodName[i] + "_fence")
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), woodName[i] + "_fence_gate")
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), woodName[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.0", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]), woodName[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), woodName[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), woodName[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (4 <= i || i < 8) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), woodName[i])
                        .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), woodName[i] + "_planks")
                        .log(Mods.Forestry.getItem("logs.1", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), woodName[i] + "_door")
                        .slab(Mods.Forestry.getItem("slabs.0", 1, metaSlab[i]), woodName[i] + "_wooden_slab")
                        .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), woodName[i] + "_fence")
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), woodName[i] + "_fence_gate")
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), woodName[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.1", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]), woodName[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), woodName[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), woodName[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (8 <= i || i < 12) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), woodName[i])
                        .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), woodName[i] + "_planks")
                        .log(Mods.Forestry.getItem("logs.2", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), woodName[i] + "_door")
                        .slab(Mods.Forestry.getItem("slabs.1", 1, metaSlab[i]), woodName[i] + "_wooden_slab")
                        .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), woodName[i] + "_fence")
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), woodName[i] + "_fence_gate")
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), woodName[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.2", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, metaSlab[i]), woodName[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), woodName[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), woodName[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (12 <= i || i < 16) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), woodName[i])
                        .planks(Mods.Forestry.getItem("planks.0", 1, metaPlank[i]), woodName[i] + "_planks")
                        .log(Mods.Forestry.getItem("logs.3", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), woodName[i] + "_door")
                        .slab(Mods.Forestry.getItem("slabs.1", 1, metaSlab[i]), woodName[i] + "_wooden_slab")
                        .fence(Mods.Forestry.getItem("fences.0", 1, metaPlank[i]), woodName[i] + "_fence")
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), woodName[i] + "_fence_gate")
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), woodName[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.3", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, metaSlab[i]), woodName[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), woodName[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), woodName[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (16 <= i || i < 20) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), woodName[i])
                        .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), woodName[i] + "_planks")
                        .log(Mods.Forestry.getItem("logs.4", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), woodName[i] + "_door")
                        .slab(Mods.Forestry.getItem("slabs.2", 1, metaSlab[i]), woodName[i] + "_wooden_slab")
                        .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), woodName[i] + "_fence")
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), woodName[i] + "_fence_gate")
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]), woodName[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.4", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, metaSlab[i]), woodName[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]), woodName[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), woodName[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (20 <= i || i < 24) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), woodName[i])
                        .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), woodName[i] + "_planks")
                        .log(Mods.Forestry.getItem("logs.5", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), woodName[i] + "_door")
                        .slab(Mods.Forestry.getItem("slabs.2", 1, metaSlab[i]), woodName[i] + "_wooden_slab")
                        .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), woodName[i] + "_fence")
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), woodName[i] + "_fence_gate")
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]), woodName[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.5", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, metaSlab[i]), woodName[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]), woodName[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), woodName[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (24 <= i || i < 28) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), woodName[i])
                        .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), woodName[i] + "_planks")
                        .log(Mods.Forestry.getItem("logs.6", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), woodName[i] + "_door")
                        .slab(Mods.Forestry.getItem("slabs.3", 1, metaSlab[i]), woodName[i] + "_wooden_slab")
                        .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), woodName[i] + "_fence")
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), woodName[i] + "_fence_gate")
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]), woodName[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.6", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, metaSlab[i]), woodName[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]), woodName[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), woodName[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (28 == i) {
                // Normal
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), woodName[i])
                        .planks(Mods.Forestry.getItem("planks.1", 1, metaPlank[i]), woodName[i] + "_planks")
                        .log(Mods.Forestry.getItem("logs.7", 1, metaLog[i])).removeCharcoalRecipe()
                        .door(Mods.Forestry.getItem("doors." + woodName[i]), woodName[i] + "_door")
                        .slab(Mods.Forestry.getItem("slabs.3", 1, metaSlab[i]), woodName[i] + "_wooden_slab")
                        .fence(Mods.Forestry.getItem("fences.1", 1, metaPlank[i]), woodName[i] + "_fence")
                        .fenceGate(Mods.Forestry.getItem("fence.gates." + woodName[i]), woodName[i] + "_fence_gate")
                        .stairs(Mods.Forestry.getItem("stairs." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());

                // Fireproof
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodName[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, metaPlank[i]), woodName[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.7", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, metaSlab[i]), woodName[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, metaPlank[i]), woodName[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodName[i]), woodName[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodName[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
        }

        // Vanilla Wood (Fireproof)
        for (int i = 0; i < 6; i++) {
            if (i < 4) {
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodNameVanilla[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), woodNameVanilla[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.0", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]), woodNameVanilla[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), woodNameVanilla[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodNameVanilla[i]), woodNameVanilla[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodNameVanilla[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
            if (4 <= i) {
                registerWoodTypeRecipe(new WoodTypeEntry.Builder(
                        Mods.Forestry.name(), "fireproof." + woodNameVanilla[i])
                        .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, metaPlank[i]), woodName[i] + "_planks.fireproof")
                        .log(Mods.Forestry.getItem("logs.fireproof.1", 1, metaLog[i])).removeCharcoalRecipe()
                        .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, metaSlab[i]), woodNameVanilla[i] + "_wooden_slab.fireproof")
                        .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, metaPlank[i]), woodNameVanilla[i] + "_fence.fireproof")
                        .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof." + woodNameVanilla[i]), woodNameVanilla[i] + "_fence_gate.fireproof")
                        .stairs(Mods.Forestry.getItem("stairs.fireproof." + woodNameVanilla[i]))
                        .registerAllUnificationInfo()
                        .build());
            }
        }
        }



}
