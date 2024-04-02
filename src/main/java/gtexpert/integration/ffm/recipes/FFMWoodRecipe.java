package gtexpert.integration.ffm.recipes;

import java.util.Arrays;
import java.util.List;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.common.ConfigHolder;
import gregtech.loaders.WoodTypeEntry;
import gregtech.loaders.recipe.WoodRecipeLoader;

import gtexpert.api.GTEValues;
import gtexpert.api.util.Mods;
import gtexpert.core.loaders.GTEWoodRecipeLoader;

public class FFMWoodRecipe {

    private static List<WoodTypeEntry> DEFAULT_ENTRIES;
    private static List<WoodTypeEntry> FIREPROOF_ENTRIES;

    private static List<WoodTypeEntry> getDefaultEntries() {
        if (DEFAULT_ENTRIES == null) {
            final String mcModId = Mods.Forestry.name();
            return DEFAULT_ENTRIES = Arrays.asList(
                    new WoodTypeEntry.Builder(mcModId, "larch")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 0), null)
                            .log(Mods.Forestry.getItem("logs.0", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.larch"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 0), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 0), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.larch"), null)
                            .stairs(Mods.Forestry.getItem("stairs.larch")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "teak")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 1), null)
                            .log(Mods.Forestry.getItem("logs.0", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.teak"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 1), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 1), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.teak"), null)
                            .stairs(Mods.Forestry.getItem("stairs.teak")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "acacia")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 2), null)
                            .log(Mods.Forestry.getItem("logs.0", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.acacia"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 2), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 2), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.acacia"), null)
                            .stairs(Mods.Forestry.getItem("stairs.acacia")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "lime")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 3), null)
                            .log(Mods.Forestry.getItem("logs.0", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.lime"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 3), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 3), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.lime"), null)
                            .stairs(Mods.Forestry.getItem("stairs.lime")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "chestnut")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 4), null)
                            .log(Mods.Forestry.getItem("logs.1", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.chestnut"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 4), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 4), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.chestnut"), null)
                            .stairs(Mods.Forestry.getItem("stairs.chestnut")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "wenge")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 5), null)
                            .log(Mods.Forestry.getItem("logs.1", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.wenge"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 5), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 5), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.wenge"), null)
                            .stairs(Mods.Forestry.getItem("stairs.wenge")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "baobab")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 6), null)
                            .log(Mods.Forestry.getItem("logs.1", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.baobab"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 6), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 6), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.baobab"), null)
                            .stairs(Mods.Forestry.getItem("stairs.baobab")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "sequoia")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 7), null)
                            .log(Mods.Forestry.getItem("logs.1", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.sequoia"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 7), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 7), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.sequoia"), null)
                            .stairs(Mods.Forestry.getItem("stairs.sequoia")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "kapok")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 8), null)
                            .log(Mods.Forestry.getItem("logs.2", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.kapok"), null)
                            .slab(Mods.Forestry.getItem("slabs.1", 1, 0), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 8), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.kapok"), null)
                            .stairs(Mods.Forestry.getItem("stairs.kapok")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "ebony")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 9), null)
                            .log(Mods.Forestry.getItem("logs.2", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.ebony"), null)
                            .slab(Mods.Forestry.getItem("slabs.1", 1, 1), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 9), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.ebony"), null)
                            .stairs(Mods.Forestry.getItem("stairs.ebony")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mahogany")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 10), null)
                            .log(Mods.Forestry.getItem("logs.2", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.mahogany"), null)
                            .slab(Mods.Forestry.getItem("slabs.1", 1, 2), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 10), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.mahogany"), null)
                            .stairs(Mods.Forestry.getItem("stairs.mahogany")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "balsa")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 11), null)
                            .log(Mods.Forestry.getItem("logs.2", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.balsa"), null)
                            .slab(Mods.Forestry.getItem("slabs.1", 1, 3), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 11), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.balsa"), null)
                            .stairs(Mods.Forestry.getItem("stairs.balsa")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "willow")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 12), null)
                            .log(Mods.Forestry.getItem("logs.3", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.willow"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 4), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 12), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.willow"), null)
                            .stairs(Mods.Forestry.getItem("stairs.willow")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "walnut")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 13), null)
                            .log(Mods.Forestry.getItem("logs.3", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.walnut"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 5), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 13), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.walnut"), null)
                            .stairs(Mods.Forestry.getItem("stairs.walnut")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "greenheart")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 14), null)
                            .log(Mods.Forestry.getItem("logs.3", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.greenheart"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 6), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 14), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.greenheart"), null)
                            .stairs(Mods.Forestry.getItem("stairs.greenheart")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "cherry")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 15), null)
                            .log(Mods.Forestry.getItem("logs.3", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.cherry"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 7), null)
                            .fence(Mods.Forestry.getItem("fences.0", 1, 15), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.cherry"), null)
                            .stairs(Mods.Forestry.getItem("stairs.cherry")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mahoe")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 0), null)
                            .log(Mods.Forestry.getItem("logs.4", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.mahoe"), null)
                            .slab(Mods.Forestry.getItem("slabs.2", 1, 0), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 0), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.mahoe"), null)
                            .stairs(Mods.Forestry.getItem("stairs.mahoe")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "poplar")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 1), null)
                            .log(Mods.Forestry.getItem("logs.4", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.poplar"), null)
                            .slab(Mods.Forestry.getItem("slabs.2", 1, 1), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 1), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.poplar"), null)
                            .stairs(Mods.Forestry.getItem("stairs.poplar")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "palm")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 2), null)
                            .log(Mods.Forestry.getItem("logs.4", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.palm"), null)
                            .slab(Mods.Forestry.getItem("slabs.2", 1, 2), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 2), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.palm"), null)
                            .stairs(Mods.Forestry.getItem("stairs.palm")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "papaya")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 3), null)
                            .log(Mods.Forestry.getItem("logs.4", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.papaya"), null)
                            .slab(Mods.Forestry.getItem("slabs.2", 1, 3), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 3), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.papaya"), null)
                            .stairs(Mods.Forestry.getItem("stairs.papaya")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "pine")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 4), null)
                            .log(Mods.Forestry.getItem("logs.5", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.pine"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 4), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 4), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.pine"), null)
                            .stairs(Mods.Forestry.getItem("stairs.pine")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "plum")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 5), null)
                            .log(Mods.Forestry.getItem("logs.5", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.plum"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 5), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 5), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.plum"), null)
                            .stairs(Mods.Forestry.getItem("stairs.plum")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "maple")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 6), null)
                            .log(Mods.Forestry.getItem("logs.5", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.maple"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 6), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 6), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.maple"), null)
                            .stairs(Mods.Forestry.getItem("stairs.maple")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "citrus")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 7), null)
                            .log(Mods.Forestry.getItem("logs.5", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.citrus"), null)
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 7), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 7), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.citrus"), null)
                            .stairs(Mods.Forestry.getItem("stairs.citrus")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "giganteum")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 8), null)
                            .log(Mods.Forestry.getItem("logs.6", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.giganteum"), null)
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 0), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 8), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.giganteum"), null)
                            .stairs(Mods.Forestry.getItem("stairs.giganteum")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "ipe")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 9), null)
                            .log(Mods.Forestry.getItem("logs.6", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.ipe"), null)
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 1), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 9), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.ipe"), null)
                            .stairs(Mods.Forestry.getItem("stairs.ipe")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "padauk")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 10), null)
                            .log(Mods.Forestry.getItem("logs.6", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.padauk"), null)
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 2), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 10), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.padauk"), null)
                            .stairs(Mods.Forestry.getItem("stairs.padauk")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "cocobolo")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 11), null)
                            .log(Mods.Forestry.getItem("logs.6", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.cocobolo"), null)
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 3), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 11), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.cocobolo"), null)
                            .stairs(Mods.Forestry.getItem("stairs.cocobolo")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "zebrawood")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 12), null)
                            .log(Mods.Forestry.getItem("logs.7", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.zebrawood"), null)
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 4), null)
                            .fence(Mods.Forestry.getItem("fences.1", 1, 12), null)
                            .fenceGate(Mods.Forestry.getItem("fence.gates.zebrawood"), null)
                            .stairs(Mods.Forestry.getItem("stairs.zebrawood")).addStairsRecipe()
                            .build());
        }
        return DEFAULT_ENTRIES;
    }

    private static List<WoodTypeEntry> getFireproofEntries() {
        if (FIREPROOF_ENTRIES == null) {
            final String mcModId = Mods.Forestry.name();
            return FIREPROOF_ENTRIES = Arrays.asList(
                    new WoodTypeEntry.Builder(mcModId, "larch")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 0),
                                    "fireproof_plank_larch")
                            .log(Mods.Forestry.getItem("logs.fireproof.0", 1, 0)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 0),
                                    "fireproof_slab_larch")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 0),
                                    "fireproof_fence_larch")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.larch"),
                                    "fireproof_fence_gate_larch")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.larch")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "teak")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 1),
                                    "fireproof_plank_teak")
                            .log(Mods.Forestry.getItem("logs.fireproof.0", 1, 1)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 1),
                                    "fireproof_slab_teak")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 1),
                                    "fireproof_fence_teak")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.teak"),
                                    "fireproof_fence_gate_teak")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.teak")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "acacia")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 2),
                                    "fireproof_plank_acacia")
                            .log(Mods.Forestry.getItem("logs.fireproof.0", 1, 2)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 2),
                                    "fireproof_slab_acacia")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 2),
                                    "fireproof_fence_acacia")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.acacia"),
                                    "fireproof_fence_gate_acacia")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.acacia")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "lime")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 3),
                                    "fireproof_plank_lime")
                            .log(Mods.Forestry.getItem("logs.fireproof.0", 1, 3)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 3),
                                    "fireproof_slab_lime")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 3),
                                    "fireproof_fence_lime")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.lime"),
                                    "fireproof_fence_gate_lime")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.lime")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "chestnut")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 4),
                                    "fireproof_plank_chestnut")
                            .log(Mods.Forestry.getItem("logs.fireproof.1", 1, 0)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 4),
                                    "fireproof_slab_chestnut")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 4),
                                    "fireproof_fence_chestnut")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.chestnut"),
                                    "fireproof_fence_gate_chestnut")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.chestnut")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "wenge")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 5),
                                    "fireproof_plank_wenge")
                            .log(Mods.Forestry.getItem("logs.fireproof.1", 1, 1)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 5),
                                    "fireproof_slab_wenge")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 5),
                                    "fireproof_fence_wenge")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.wenge"),
                                    "fireproof_fence_gate_wenge")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.wenge")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "baobab")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 6),
                                    "fireproof_plank_baobab")
                            .log(Mods.Forestry.getItem("logs.fireproof.1", 1, 2)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 6),
                                    "fireproof_slab_baobab")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 6),
                                    "fireproof_fence_baobab")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.baobab"),
                                    "fireproof_fence_gate_baobab")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.baobab")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "sequoia")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 7),
                                    "fireproof_plank_sequoia")
                            .log(Mods.Forestry.getItem("logs.fireproof.1", 1, 3)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 7),
                                    "fireproof_slab_sequoia")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 7),
                                    "fireproof_fence_sequoia")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.sequoia"),
                                    "fireproof_fence_gate_sequoia")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.sequoia")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "kapok")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 8),
                                    "fireproof_plank_kapok")
                            .log(Mods.Forestry.getItem("logs.fireproof.2", 1, 0)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, 0),
                                    "fireproof_slab_kapok")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 8),
                                    "fireproof_fence_kapok")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.kapok"),
                                    "fireproof_fence_gate_kapok")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.kapok")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "ebony")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 9),
                                    "fireproof_plank_ebony")
                            .log(Mods.Forestry.getItem("logs.fireproof.2", 1, 1)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, 1),
                                    "fireproof_slab_ebony")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 9),
                                    "fireproof_fence_ebony")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.ebony"),
                                    "fireproof_fence_gate_ebony")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.ebony")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mahogany")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 10),
                                    "fireproof_plank_mahogany")
                            .log(Mods.Forestry.getItem("logs.fireproof.2", 1, 2)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, 2),
                                    "fireproof_slab_mahogany")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 10),
                                    "fireproof_fence_mahogany")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.mahogany"),
                                    "fireproof_fence_gate_mahogany")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.mahogany")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "balsa")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 11),
                                    "fireproof_plank_balsa")
                            .log(Mods.Forestry.getItem("logs.fireproof.2", 1, 3)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.1", 1, 3),
                                    "fireproof_slab_balsa")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 11),
                                    "fireproof_fence_balsa")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.balsa"),
                                    "fireproof_fence_gate_balsa")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.balsa")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "willow")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 12),
                                    "fireproof_plank_willow")
                            .log(Mods.Forestry.getItem("logs.fireproof.3", 1, 0)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 4),
                                    "fireproof_slab_willow")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 12),
                                    "fireproof_fence_willow")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.willow"),
                                    "fireproof_fence_gate_willow")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.willow")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "walnut")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 13),
                                    "fireproof_plank_walnut")
                            .log(Mods.Forestry.getItem("logs.fireproof.3", 1, 1)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 5),
                                    "fireproof_slab_walnut")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 13),
                                    "fireproof_fence_walnut")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.walnut"),
                                    "fireproof_fence_gate_walnut")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.walnut")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "greenheart")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 14),
                                    "fireproof_plank_greenheart")
                            .log(Mods.Forestry.getItem("logs.fireproof.3", 1, 2)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 6),
                                    "fireproof_slab_greenheart")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 14),
                                    "fireproof_fence_greenheart")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.greenheart"),
                                    "fireproof_fence_gate_greenheart")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.greenheart")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "cherry")
                            .planks(Mods.Forestry.getItem("planks.fireproof.0", 1, 15),
                                    "fireproof_plank_cherry")
                            .log(Mods.Forestry.getItem("logs.fireproof.3", 1, 3)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 7),
                                    "fireproof_slab_cherry")
                            .fence(Mods.Forestry.getItem("fences.fireproof.0", 1, 15),
                                    "fireproof_fence_cherry")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.cherry"),
                                    "fireproof_fence_gate_cherry")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.cherry")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mahoe")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 0),
                                    "fireproof_plank_mahoe")
                            .log(Mods.Forestry.getItem("logs.fireproof.4", 1, 0)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, 0),
                                    "fireproof_slab_mahoe")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 0),
                                    "fireproof_fence_mahoe")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.mahoe"),
                                    "fireproof_fence_gate_mahoe")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.mahoe")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "poplar")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 1),
                                    "fireproof_plank_poplar")
                            .log(Mods.Forestry.getItem("logs.fireproof.4", 1, 1)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, 1),
                                    "fireproof_slab_poplar")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 1),
                                    "fireproof_fence_poplar")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.poplar"),
                                    "fireproof_fence_gate_poplar")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.poplar")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "palm")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 2),
                                    "fireproof_plank_palm")
                            .log(Mods.Forestry.getItem("logs.fireproof.4", 1, 2)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, 2),
                                    "fireproof_slab_palm")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 2),
                                    "fireproof_fence_palm")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.palm"),
                                    "fireproof_fence_gate_palm")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.palm")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "papaya")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 3),
                                    "fireproof_plank_papaya")
                            .log(Mods.Forestry.getItem("logs.fireproof.4", 1, 3)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.2", 1, 3),
                                    "fireproof_slab_papaya")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 3),
                                    "fireproof_fence_papaya")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.papaya"),
                                    "fireproof_fence_gate_papaya")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.papaya")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "pine")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 4),
                                    "fireproof_plank_pine")
                            .log(Mods.Forestry.getItem("logs.fireproof.5", 1, 0)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 4),
                                    "fireproof_slab_pine")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 4),
                                    "fireproof_fence_pine")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.pine"),
                                    "fireproof_fence_gate_pine")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.pine")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "plum")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 5),
                                    "fireproof_plank_plum")
                            .log(Mods.Forestry.getItem("logs.fireproof.5", 1, 1)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 5),
                                    "fireproof_slab_plum")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 5),
                                    "fireproof_fence_plum")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.plum"),
                                    "fireproof_fence_gate_plum")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.plum")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "maple")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 6),
                                    "fireproof_plank_maple")
                            .log(Mods.Forestry.getItem("logs.fireproof.5", 1, 2)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 6),
                                    "fireproof_slab_maple")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 6),
                                    "fireproof_fence_maple")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.maple"),
                                    "fireproof_fence_gate_maple")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.maple")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "citrus")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 7),
                                    "fireproof_plank_citrus")
                            .log(Mods.Forestry.getItem("logs.fireproof.5", 1, 3)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.0", 1, 7),
                                    "fireproof_slab_citrus")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 7),
                                    "fireproof_fence_citrus")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.citrus"),
                                    "fireproof_fence_gate_citrus")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.citrus")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "giganteum")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 8),
                                    "fireproof_plank_giganteum")
                            .log(Mods.Forestry.getItem("logs.fireproof.6", 1, 0)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, 0),
                                    "fireproof_slab_giganteum")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 8),
                                    "fireproof_fence_giganteum")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.giganteum"),
                                    "fireproof_fence_gate_giganteum")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.giganteum")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "ipe")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 9),
                                    "fireproof_plank_ipe")
                            .log(Mods.Forestry.getItem("logs.fireproof.6", 1, 1)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, 1),
                                    "fireproof_slab_ipe")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 9),
                                    "fireproof_fence_ipe")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.ipe"),
                                    "fireproof_fence_gate_ipe")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.ipe")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "padauk")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 10),
                                    "fireproof_plank_padauk")
                            .log(Mods.Forestry.getItem("logs.fireproof.6", 1, 2)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, 2),
                                    "fireproof_slab_padauk")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 10),
                                    "fireproof_fence_padauk")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.padauk"),
                                    "fireproof_fence_gate_padauk")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.padauk")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "cocobolo")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 11),
                                    "fireproof_plank_cocobolo")
                            .log(Mods.Forestry.getItem("logs.fireproof.6", 1, 3)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, 3),
                                    "fireproof_slab_cocobolo")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 11),
                                    "fireproof_fence_cocobolo")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.cocobolo"),
                                    "fireproof_fence_gate_cocobolo")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.cocobolo")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "zebrawood")
                            .planks(Mods.Forestry.getItem("planks.fireproof.1", 1, 12),
                                    "fireproof_plank_zebrawood")
                            .log(Mods.Forestry.getItem("logs.fireproof.7", 1, 0)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.fireproof.3", 1, 4),
                                    "fireproof_slab_zebrawood")
                            .fence(Mods.Forestry.getItem("fences.fireproof.1", 1, 12),
                                    "fireproof_fence_zebrawood")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.fireproof.zebrawood"),
                                    "fireproof_fence_gate_zebrawood")
                            .stairs(Mods.Forestry.getItem("stairs.fireproof.zebrawood")).addStairsRecipe()
                            .build(),

                    // Vanilla wood types
                    new WoodTypeEntry.Builder(mcModId, "oak")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 0),
                                    "fireproof_plank_oak")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, 0)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 0),
                                    "fireproof_slab_oak")
                            .fence(Mods.Forestry.getItem("fences.vanilla.fireproof.0", 1, 0),
                                    "fireproof_fence_oak")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.oak"),
                                    "fireproof_fence_gate_oak")
                            .stairs(Mods.Forestry.getItem("stairs.vanilla.fireproof.oak")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "spruce")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 1),
                                    "fireproof_plank_spruce")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, 1)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 1),
                                    "fireproof_slab_spruce")
                            .fence(Mods.Forestry.getItem("fences.vanilla.fireproof.0", 1, 1),
                                    "fireproof_fence_spruce")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.spruce"),
                                    "fireproof_fence_gate_spruce")
                            .stairs(Mods.Forestry.getItem("stairs.vanilla.fireproof.spruce")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "birch")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 2),
                                    "fireproof_plank_birch")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, 2)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 2),
                                    "fireproof_slab_birch")
                            .fence(Mods.Forestry.getItem("fences.vanilla.fireproof.0", 1, 2),
                                    "fireproof_fence_birch")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.birch"),
                                    "fireproof_fence_gate_birch")
                            .stairs(Mods.Forestry.getItem("stairs.vanilla.fireproof.birch")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "jungle")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 3),
                                    "fireproof_plank_jungle")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, 3)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 3),
                                    "fireproof_slab_jungle")
                            .fence(Mods.Forestry.getItem("fences.vanilla.fireproof.0", 1, 3),
                                    "fireproof_fence_jungle")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.jungle"),
                                    "fireproof_fence_gate_jungle")
                            .stairs(Mods.Forestry.getItem("stairs.vanilla.fireproof.jungle")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "acacia") // TODO: Fix acacia planks
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 4),
                                    "fireproof_plank_acacia")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.1", 1, 4)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 4),
                                    "fireproof_slab_acacia")
                            .fence(Mods.Forestry.getItem("fences.vanilla.fireproof.0", 1, 4),
                                    "fireproof_fence_acacia")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.acacia"),
                                    "fireproof_fence_gate_acacia")
                            .stairs(Mods.Forestry.getItem("stairs.vanilla.fireproof.acacia")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "dark_oak")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 5),
                                    "fireproof_plank_dark_oak")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.1", 1, 5)).removeCharcoalRecipe()
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 5),
                                    "fireproof_slab_dark_oak")
                            .fence(Mods.Forestry.getItem("fences.vanilla.fireproof.0", 1, 5),
                                    "fireproof_fence_dark_oak")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.dark_oak"),
                                    "fireproof_fence_gate_dark_oak")
                            .stairs(Mods.Forestry.getItem("stairs.vanilla.fireproof.dark_oak")).addStairsRecipe()
                            .build());
        }
        return FIREPROOF_ENTRIES;
    }

    public static void init() {
        if (!Mods.ForestryArboriculture.isModLoaded()) return;

        for (WoodTypeEntry entry : getDefaultEntries()) {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("planks_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("slabs_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("fences_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("fence_gates_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("stairs_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("doors_" + entry.woodName));

            WoodRecipeLoader.registerWoodTypeRecipe(entry);
            GTEWoodRecipeLoader.overridePlankRecipe(true, entry, GTEValues.MODID);
            GTEWoodRecipeLoader.addSawmillRecipe(entry);

            if (!Mods.ForestryCharcoal.isModLoaded()) return;
            if (!ConfigHolder.recipes.harderCharcoalRecipe) return;
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("wood_pile"));

            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                    .input("logWood", 4)
                    .outputs(Mods.Forestry.getItem("wood_pile"))
                    .duration(300).EUt(2)
                    .buildAndRegister();
        }

        for (WoodTypeEntry entry : getFireproofEntries()) {
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("fireproof_planks_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("fireproof_slabs_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("fireproof_fences_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("fireproof_fence_gates_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("fireproof_stairs_" + entry.woodName));
            ModHandler.removeRecipeByName(Mods.Forestry.getResource("fireproof_doors_" + entry.woodName));

            WoodRecipeLoader.registerWoodTypeRecipe(entry);
            GTEWoodRecipeLoader.overridePlankRecipe(true, entry, GTEValues.MODID);
            GTEWoodRecipeLoader.addSawmillRecipe(entry);
        }

        // Fireproof for vanilla wood types
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID, "oak_planks_saw"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID, "spruce_planks_saw"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID, "birch_planks_saw"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID, "jungle_planks_saw"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID, "acacia_planks_saw"));
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID, "dark_oak_planks_saw"));
    }
}
