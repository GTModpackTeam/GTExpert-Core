package gtexpert.integration.ffm.recipes;

import static gregtech.api.recipes.RecipeMaps.*;

import java.util.Arrays;
import java.util.List;

import gregtech.api.recipes.ModHandler;
import gregtech.common.ConfigHolder;
import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.Mods;
import gtexpert.core.loaders.GTEWoodRecipeLoader;

public class FFMWoodRecipe {

    private static List<WoodTypeEntry> DEFAULT_ENTRIES;

    private static List<WoodTypeEntry> getDefaultEntries() {
        if (DEFAULT_ENTRIES == null) {
            final String mcModId = Mods.Forestry.name();
            return DEFAULT_ENTRIES = Arrays.asList(
                    new WoodTypeEntry.Builder(mcModId, "larch")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 0), "planks_larch")
                            .log(Mods.Forestry.getItem("logs.0", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.larch"), "doors_larch")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 0), "slabs_larch")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 0), "fences_larch")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.larch"), "fence_gates_larch")
                            .stairs(Mods.Forestry.getItem("stairs.larch"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "teak")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 1), "planks_teak")
                            .log(Mods.Forestry.getItem("logs.0", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.teak"), "doors_teak")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 1), "slabs_teak")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 1), "fences_teak")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.teak"), "fence_gates_teak")
                            .stairs(Mods.Forestry.getItem("stairs.teak"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "acacia")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 2), "planks_acacia")
                            .log(Mods.Forestry.getItem("logs.0", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.acacia"), "doors_acacia")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 2), "slabs_acacia")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 2), "fences_acacia")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.acacia"), "fence_gates_acacia")
                            .stairs(Mods.Forestry.getItem("stairs.acacia"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "lime")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 3), "planks_lime")
                            .log(Mods.Forestry.getItem("logs.0", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.lime"), "doors_lime")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 3), "slabs_lime")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 3), "fences_lime")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.lime"), "fence_gates_lime")
                            .stairs(Mods.Forestry.getItem("stairs.lime"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "chestnut")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 4), "planks_chestnut")
                            .log(Mods.Forestry.getItem("logs.1", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.chestnut"), "doors_chestnut")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 4), "slabs_chestnut")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 4), "fences_chestnut")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.chestnut"), "fence_gates_chestnut")
                            .stairs(Mods.Forestry.getItem("stairs.chestnut"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "wenge")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 5), "planks_wenge")
                            .log(Mods.Forestry.getItem("logs.1", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.wenge"), "doors_wenge")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 5), "slabs_wenge")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 5), "fences_wenge")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.wenge"), "fence_gates_wenge")
                            .stairs(Mods.Forestry.getItem("stairs.wenge"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "baobab")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 6), "planks_baobab")
                            .log(Mods.Forestry.getItem("logs.1", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.baobab"), "doors_baobab")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 6), "slabs_baobab")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 6), "fences_baobab")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.baobab"), "fence_gates_baobab")
                            .stairs(Mods.Forestry.getItem("stairs.baobab"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "sequoia")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 7), "planks_sequoia")
                            .log(Mods.Forestry.getItem("logs.1", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.sequoia"), "doors_sequoia")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 7), "slabs_sequoia")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 7), "fences_sequoia")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.sequoia"), "fence_gates_sequoia")
                            .stairs(Mods.Forestry.getItem("stairs.sequoia"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "kapok")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 8), "planks_kapok")
                            .log(Mods.Forestry.getItem("logs.2", 1, 4)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.kapok"), "doors_kapok")
                            .slab(Mods.Forestry.getItem("slabs.1", 1, 0), "slabs_kapok")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 8), "fences_kapok")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.kapok"), "fence_gates_kapok")
                            .stairs(Mods.Forestry.getItem("stairs.kapok"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "ebony")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 9), "planks_ebony")
                            .log(Mods.Forestry.getItem("logs.2", 1, 5)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.ebony"), "doors_ebony")
                            .slab(Mods.Forestry.getItem("slabs.1", 1, 1), "slabs_ebony")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 9), "fences_ebony")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.ebony"), "fence_gates_ebony")
                            .stairs(Mods.Forestry.getItem("stairs.ebony"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mahogany")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 10), "planks_mahogany")
                            .log(Mods.Forestry.getItem("logs.2", 1, 6)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.mahogany"), "doors_mahogany")
                            .slab(Mods.Forestry.getItem("slabs.1", 1, 2), "slabs_mahogany")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 10), "fences_mahogany")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.mahogany"), "fence_gates_mahogany")
                            .stairs(Mods.Forestry.getItem("stairs.mahogany"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "balsa")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 11), "planks_balsa")
                            .log(Mods.Forestry.getItem("logs.2", 1, 7)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.balsa"), "doors_balsa")
                            .slab(Mods.Forestry.getItem("slabs.1", 1, 3), "slabs_balsa")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 11), "fences_balsa")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.balsa"), "fence_gates_balsa")
                            .stairs(Mods.Forestry.getItem("stairs.balsa"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "willow")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 12), "planks_willow")
                            .log(Mods.Forestry.getItem("logs.3", 1, 8)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.willow"), "doors_willow")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 4), "slabs_willow")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 12), "fences_willow")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.willow"), "fence_gates_willow")
                            .stairs(Mods.Forestry.getItem("stairs.willow"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "walnut")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 13), "planks_walnut")
                            .log(Mods.Forestry.getItem("logs.3", 1, 9)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.walnut"), "doors_walnut")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 5), "slabs_walnut")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 13), "fences_walnut")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.walnut"), "fence_gates_walnut")
                            .stairs(Mods.Forestry.getItem("stairs.walnut"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "greenheart")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 14), "planks_greenheart")
                            .log(Mods.Forestry.getItem("logs.3", 1, 10)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.greenheart"), "doors_greenheart")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 6), "slabs_greenheart")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 14), "fences_greenheart")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.greenheart"), "fence_gates_greenheart")
                            .stairs(Mods.Forestry.getItem("stairs.greenheart"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "cherry")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 15), "planks_cherry")
                            .log(Mods.Forestry.getItem("logs.3", 1, 11)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.cherry"), "doors_cherry")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 7), "slabs_cherry")
                            .fence(Mods.Forestry.getItem("fences.0", 1, 15), "fences_cherry")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.cherry"), "fence_gates_cherry")
                            .stairs(Mods.Forestry.getItem("stairs.cherry"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "mahoe")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 0), "planks_mahoe")
                            .log(Mods.Forestry.getItem("logs.4", 1, 12)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.mahoe"), "doors_mahoe")
                            .slab(Mods.Forestry.getItem("slabs.2", 1, 0), "slabs_mahoe")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 0), "fences_mahoe")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.mahoe"), "fence_gates_mahoe")
                            .stairs(Mods.Forestry.getItem("stairs.mahoe"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "poplar")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 10), "planks_poplar")
                            .log(Mods.Forestry.getItem("logs.4", 1, 13)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.poplar"), "doors_poplar")
                            .slab(Mods.Forestry.getItem("slabs.2", 1, 1), "slabs_poplar")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 1), "fences_poplar")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.poplar"), "fence_gates_poplar")
                            .stairs(Mods.Forestry.getItem("stairs.poplar"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "palm")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 2), "planks_palm")
                            .log(Mods.Forestry.getItem("logs.4", 1, 14)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.palm"), "doors_palm")
                            .slab(Mods.Forestry.getItem("slabs.2", 1, 2), "slabs_palm")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 2), "fences_palm")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.palm"), "fence_gates_palm")
                            .stairs(Mods.Forestry.getItem("stairs.palm"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "papaya")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 3), "planks_papaya")
                            .log(Mods.Forestry.getItem("logs.4", 1, 15)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.papaya"), "doors_papaya")
                            .slab(Mods.Forestry.getItem("slabs.2", 1, 3), "slabs_papaya")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 3), "fences_papaya")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.papaya"), "fence_gates_papaya")
                            .stairs(Mods.Forestry.getItem("stairs.papaya"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "pine")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 4), "planks_pine")
                            .log(Mods.Forestry.getItem("logs.5", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.pine"), "doors_pine")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 4), "slabs_pine")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 4), "fences_pine")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.pine"), "fence_gates_pine")
                            .stairs(Mods.Forestry.getItem("stairs.pine"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "plum")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 5), "planks_plum")
                            .log(Mods.Forestry.getItem("logs.5", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.plum"), "doors_plum")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 5), "slabs_plum")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 5), "fences_plum")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.plum"), "fence_gates_plum")
                            .stairs(Mods.Forestry.getItem("stairs.plum"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "maple")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 6), "planks_maple")
                            .log(Mods.Forestry.getItem("logs.5", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.maple"), "doors_maple")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 6), "slabs_maple")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 6), "fences_maple")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.maple"), "fence_gates_maple")
                            .stairs(Mods.Forestry.getItem("stairs.maple"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "citrus")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 7), "planks_citrus")
                            .log(Mods.Forestry.getItem("logs.5", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.citrus"), "doors_citrus")
                            .slab(Mods.Forestry.getItem("slabs.0", 1, 7), "slabs_citrus")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 7), "fences_citrus")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.citrus"), "fence_gates_citrus")
                            .stairs(Mods.Forestry.getItem("stairs.citrus"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "giganteum")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 8), "planks_giganteum")
                            .log(Mods.Forestry.getItem("logs.6", 1, 4)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.giganteum"), "doors_giganteum")
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 0), "slabs_giganteum")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 8), "fences_giganteum")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.giganteum"), "fence_gates_giganteum")
                            .stairs(Mods.Forestry.getItem("stairs.giganteum"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "ipe")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 9), "planks_ipe")
                            .log(Mods.Forestry.getItem("logs.6", 1, 5)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.ipe"), "doors_ipe")
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 1), "slabs_ipe")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 9), "fences_ipe")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.ipe"), "fence_gates_ipe")
                            .stairs(Mods.Forestry.getItem("stairs.ipe"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "padauk")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 10), "planks_padauk")
                            .log(Mods.Forestry.getItem("logs.6", 1, 6)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.padauk"), "doors_padauk")
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 2), "slabs_padauk")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 10), "fences_padauk")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.padauk"), "fence_gates_padauk")
                            .stairs(Mods.Forestry.getItem("stairs.padauk"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "cocobolo")
                            .planks(Mods.Forestry.getItem("planks.0", 1, 11), "planks_cocobolo")
                            .log(Mods.Forestry.getItem("logs.6", 1, 7)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.cocobolo"), "doors_cocobolo")
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 3), "slabs_cocobolo")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 11), "fences_cocobolo")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.cocobolo"), "fence_gates_cocobolo")
                            .stairs(Mods.Forestry.getItem("stairs.cocobolo"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "zebrawood")
                            .planks(Mods.Forestry.getItem("planks.1", 1, 12), "planks_zebrawood")
                            .log(Mods.Forestry.getItem("logs.7", 1, 8)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("doors.zebrawood"), "doors_zebrawood")
                            .slab(Mods.Forestry.getItem("slabs.3", 1, 4), "slabs_zebrawood")
                            .fence(Mods.Forestry.getItem("fences.1", 1, 12), "fences_zebrawood")
                            .fenceGate(Mods.Forestry.getItem("fence.gates.zebrawood"), "fence_gates_zebrawood")
                            .stairs(Mods.Forestry.getItem("stairs.zebrawood"))
                            .registerAllUnificationInfo()
                            .build(),

                    // Vanilla Wood (Fireproof)
                    new WoodTypeEntry.Builder(mcModId, "oak")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 0), "fireproof_planks_oak")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("wooden_door"), "fireproof_doors_oak")
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 0), "fireproof_slabs_oak")
                            .fence(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.oak", 1, 0),
                                    "fireproof_fence_gates_oak")
                            .fenceGate(Mods.Forestry.getItem("stairs.vanilla.fireproof.oak"), "fireproof_stairs_oak")
                            .stairs(Mods.Forestry.getItem("stairs.oak"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "spruce")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 1),
                                    "fireproof_planks_spruce")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("wooden_door"), "fireproof_doors_spruce")
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 1), "fireproof_slabs_spruce")
                            .fence(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.spruce", 1, 1),
                                    "fireproof_fence_gates_spruce")
                            .fenceGate(Mods.Forestry.getItem("stairs.vanilla.fireproof.spruce"),
                                    "fireproof_stairs_spruce")
                            .stairs(Mods.Forestry.getItem("stairs.spruce"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "birch")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 2), "fireproof_planks_birch")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("wooden_door"), "fireproof_doors_birch")
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 2), "fireproof_slabs_birch")
                            .fence(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.birch", 1, 2),
                                    "fireproof_fence_gates_birch")
                            .fenceGate(Mods.Forestry.getItem("stairs.vanilla.fireproof.birch"),
                                    "fireproof_stairs_birch")
                            .stairs(Mods.Forestry.getItem("stairs.birch"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "jungle")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 3),
                                    "fireproof_planks_jungle")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.0", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("wooden_door"), "fireproof_doors_jungle")
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 3), "fireproof_slabs_jungle")
                            .fence(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.jungle", 1, 3),
                                    "fireproof_fence_gates_jungle")
                            .fenceGate(Mods.Forestry.getItem("stairs.vanilla.fireproof.jungle"),
                                    "fireproof_stairs_jungle")
                            .stairs(Mods.Forestry.getItem("stairs.jungle"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "acacia")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 4),
                                    "fireproof_planks_acacia")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.1", 1, 4)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("wooden_door"), "fireproof_doors_acacia")
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 4), "fireproof_slabs_acacia")
                            .fence(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.acacia", 1, 4),
                                    "fireproof_fence_gates_acacia")
                            .fenceGate(Mods.Forestry.getItem("stairs.vanilla.fireproof.acacia"),
                                    "fireproof_stairs_acacia")
                            .stairs(Mods.Forestry.getItem("stairs.acacia"))
                            .registerAllUnificationInfo()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "dark_oak")
                            .planks(Mods.Forestry.getItem("planks.vanilla.fireproof.0", 1, 5),
                                    "fireproof_planks_dark_oak")
                            .log(Mods.Forestry.getItem("logs.vanilla.fireproof.1", 1, 5)).removeCharcoalRecipe()
                            .door(Mods.Forestry.getItem("wooden_door"), "fireproof_doors_dark_oak")
                            .slab(Mods.Forestry.getItem("slabs.vanilla.fireproof.0", 1, 5), "fireproof_slabs_dark_oak")
                            .fence(Mods.Forestry.getItem("fence.gates.vanilla.fireproof.dark_oak", 1, 5),
                                    "fireproof_fence_gates_dark_oak")
                            .fenceGate(Mods.Forestry.getItem("stairs.vanilla.fireproof.dark_oak"),
                                    "fireproof_stairs_dark_oak")
                            .stairs(Mods.Forestry.getItem("stairs.dark_oak"))
                            .registerAllUnificationInfo()
                            .build());
        }
        return DEFAULT_ENTRIES;
    }

    public static void register() {
        if (Mods.ForestryArboriculture.isModLoaded()) {
            for (WoodTypeEntry entry : getDefaultEntries()) {
                GTEWoodRecipeLoader.removePlankRecipes(entry);

                GTEWoodRecipeLoader.addPlankRecipes(entry);
                GTEWoodRecipeLoader.addCutterRecipes(entry);
                GTEWoodRecipeLoader.addSawmillRecipes(entry);

                if (!ConfigHolder.recipes.harderCharcoalRecipe) return;
                ModHandler.removeFurnaceSmelting(entry.log);
                ModHandler.removeRecipeByName(Mods.Forestry.getResource("wood_pile"));

                COMPRESSOR_RECIPES.recipeBuilder()
                        .input("logWood", 4)
                        .outputs(Mods.Forestry.getItem("wood_pile"))
                        .duration(300).EUt(2)
                        .buildAndRegister();
            }
        }
    }
}
