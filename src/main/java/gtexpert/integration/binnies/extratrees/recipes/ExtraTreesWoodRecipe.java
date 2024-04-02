package gtexpert.integration.binnies.extratrees.recipes;

import java.util.Arrays;
import java.util.List;

import gregtech.api.recipes.ModHandler;
import gregtech.loaders.WoodTypeEntry;
import gregtech.loaders.recipe.WoodRecipeLoader;

import gtexpert.api.GTEValues;
import gtexpert.api.util.Mods;
import gtexpert.core.loaders.GTEWoodRecipeLoader;

public class ExtraTreesWoodRecipe {

    private static List<WoodTypeEntry> DEFAULT_ENTRIES;
    private static List<WoodTypeEntry> FIREPROOF_ENTRIES;

    private static List<WoodTypeEntry> getDefaultEntries() {
        if (DEFAULT_ENTRIES == null) {
            final String mcModId = Mods.ExtraTrees.name();
            return DEFAULT_ENTRIES = Arrays.asList(
                    new WoodTypeEntry.Builder(mcModId, "apple")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 0), null)
                            .log(Mods.ExtraTrees.getItem("logs.0", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.apple"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.0", 1, 0), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 0), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.apple"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.apple")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "fig")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 1), null)
                            .log(Mods.ExtraTrees.getItem("logs.0", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.fig"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.0", 1, 1), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 1), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.fig"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.fig")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "butternut")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 2), null)
                            .log(Mods.ExtraTrees.getItem("logs.0", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.butternut"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.0", 1, 2), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 2), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.butternut"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.butternut")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "whitebeam")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 3), null)
                            .log(Mods.ExtraTrees.getItem("logs.0", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.whitebeam"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.0", 1, 3), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 3), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.whitebeam"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.whitebeam")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "rowan")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 4), null)
                            .log(Mods.ExtraTrees.getItem("logs.1", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.rowan"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.0", 1, 4), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 4), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.rowan"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.rowan")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "hemlock")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 5), null)
                            .log(Mods.ExtraTrees.getItem("logs.1", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.hemlock"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.0", 1, 5), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 5), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.hemlock"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.hemlock")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "ash")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 6), null)
                            .log(Mods.ExtraTrees.getItem("logs.1", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.ash"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.0", 1, 6), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 6), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.ash"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.ash")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "alder")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 7), null)
                            .log(Mods.ExtraTrees.getItem("logs.1", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.alder"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.0", 1, 7), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 7), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.alder"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.alder")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "beech")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 8), null)
                            .log(Mods.ExtraTrees.getItem("logs.2", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.beech"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.1", 1, 0), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 8), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.beech"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.beech")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "hawthorn")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 9), null)
                            .log(Mods.ExtraTrees.getItem("logs.2", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.hawthorn"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.1", 1, 1), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 9), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.hawthorn"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.hawthorn")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "banana")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 10), null)
                            .log(Mods.ExtraTrees.getItem("logs.2", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.banana"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.1", 1, 2), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 10), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.banana"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.banana")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "yew")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 11), null)
                            .log(Mods.ExtraTrees.getItem("logs.2", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.yew"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.1", 1, 3), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 11), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.yew"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.yew")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "cypress")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 12), null)
                            .log(Mods.ExtraTrees.getItem("logs.3", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.cypress"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.1", 1, 4), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 12), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.cypress"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.cypress")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "fir") // TODO: Fix fir log
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 13), null)
                            .log(Mods.ExtraTrees.getItem("logs.3", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.fir"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.1", 1, 5), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 13), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.fir"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.fir")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "hazel")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 14), null)
                            .log(Mods.ExtraTrees.getItem("logs.3", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.hazel"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.1", 1, 6), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 14), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.hazel"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.hazel")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "hickory")
                            .planks(Mods.ExtraTrees.getItem("planks.0", 1, 15), null)
                            .log(Mods.ExtraTrees.getItem("logs.3", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.hickory"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.1", 1, 7), null)
                            .fence(Mods.ExtraTrees.getItem("fences.0", 1, 15), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.hickory"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.hickory")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "elm")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 0), null)
                            .log(Mods.ExtraTrees.getItem("logs.4", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.elm"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.2", 1, 0), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 0), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.elm"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.elm")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "elder")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 1), null)
                            .log(Mods.ExtraTrees.getItem("logs.4", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.elder"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.2", 1, 1), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 1), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.elder"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.elder")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "holly")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 2), null)
                            .log(Mods.ExtraTrees.getItem("logs.4", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.holly"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.2", 1, 2), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 2), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.holly"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.holly")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "hornbeam")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 3), null)
                            .log(Mods.ExtraTrees.getItem("logs.4", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.hornbeam"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.2", 1, 3), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 3), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.hornbeam"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.hornbeam")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "cedar")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 4), null)
                            .log(Mods.ExtraTrees.getItem("logs.5", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.cedar"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.2", 1, 4), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 4), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.cedar"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.cedar")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "olive")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 5), null)
                            .log(Mods.ExtraTrees.getItem("logs.5", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.olive"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.2", 1, 5), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 5), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.olive"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.olive")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "sweetgum")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 6), null)
                            .log(Mods.ExtraTrees.getItem("logs.5", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.sweetgum"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.2", 1, 6), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 6), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.sweetgum"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.sweetgum")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "locust")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 7), null)
                            .log(Mods.ExtraTrees.getItem("logs.5", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.locust"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.2", 1, 7), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 7), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.locust"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.locust")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "pear")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 8), null)
                            .log(Mods.ExtraTrees.getItem("logs.6", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.pear"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.3", 1, 0), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 8), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.pear"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.pear")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "maclura")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 9), null)
                            .log(Mods.ExtraTrees.getItem("logs.6", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.maclura"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.3", 1, 1), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 9), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.maclura"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.maclura")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "brazilwood")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 10), null)
                            .log(Mods.ExtraTrees.getItem("logs.6", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.brazilwood"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.3", 1, 2), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 10), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.brazilwood"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.brazilwood")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "logwood") // TODO: Fix logwood log
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 11), null)
                            .log(Mods.ExtraTrees.getItem("logs.6", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.logwood"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.3", 1, 3), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 11), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.logwood"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.logwood")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "rosewood")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 12), null)
                            .log(Mods.ExtraTrees.getItem("logs.7", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.rosewood"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.3", 1, 4), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 12), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.rosewood"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.rosewood")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "purpleheart")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 13), null)
                            .log(Mods.ExtraTrees.getItem("logs.7", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.purpleheart"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.3", 1, 5), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 13), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.purpleheart"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.purpleheart")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "iroko")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 14), null)
                            .log(Mods.ExtraTrees.getItem("logs.7", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.iroko"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.3", 1, 6), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 14), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.iroko"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.iroko")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "gingko")
                            .planks(Mods.ExtraTrees.getItem("planks.1", 1, 15), null)
                            .log(Mods.ExtraTrees.getItem("logs.7", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.gingko"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.3", 1, 7), null)
                            .fence(Mods.ExtraTrees.getItem("fences.1", 1, 15), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.gingko"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.gingko")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "eucalyptus")
                            .planks(Mods.ExtraTrees.getItem("planks.2", 1, 0), null)
                            .log(Mods.ExtraTrees.getItem("logs.8", 1, 0)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.eucalyptus"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.4", 1, 0), null)
                            .fence(Mods.ExtraTrees.getItem("fences.2", 1, 0), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.eucalyptus"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.eucalyptus")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "box")
                            .planks(Mods.ExtraTrees.getItem("planks.2", 1, 1), null)
                            .log(Mods.ExtraTrees.getItem("logs.8", 1, 1)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.box"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.4", 1, 1), null)
                            .fence(Mods.ExtraTrees.getItem("fences.2", 1, 1), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.box"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.box")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "syzgium")
                            .planks(Mods.ExtraTrees.getItem("planks.2", 1, 2), null)
                            .log(Mods.ExtraTrees.getItem("logs.8", 1, 2)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.syzgium"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.4", 1, 2), null)
                            .fence(Mods.ExtraTrees.getItem("fences.2", 1, 2), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.syzgium"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.syzgium")).addStairsRecipe()
                            .build(),
                    new WoodTypeEntry.Builder(mcModId, "pinkivory")
                            .planks(Mods.ExtraTrees.getItem("planks.2", 1, 3), null)
                            .log(Mods.ExtraTrees.getItem("logs.8", 1, 3)).removeCharcoalRecipe()
                            .door(Mods.ExtraTrees.getItem("doors.pinkivory"), null)
                            .slab(Mods.ExtraTrees.getItem("slabs.4", 1, 3), null)
                            .fence(Mods.ExtraTrees.getItem("fences.2", 1, 3), null)
                            .fenceGate(Mods.ExtraTrees.getItem("fence.gates.pinkivory"), null)
                            .stairs(Mods.ExtraTrees.getItem("stairs.pinkivory")).addStairsRecipe()
                            .build());
        }
        return DEFAULT_ENTRIES;
    }

    private static List<WoodTypeEntry> getFireproofEntries() {
        if (FIREPROOF_ENTRIES == null) {
            final String mcModId = Mods.ExtraTrees.name();
            return FIREPROOF_ENTRIES = Arrays.asList(

            );
        }
        return FIREPROOF_ENTRIES;
    }

    public static void init() {
        for (WoodTypeEntry entry : getDefaultEntries()) {
            ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource(entry.woodName + "_planks"));
            ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource(entry.woodName + "_slabs"));
            ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource(entry.woodName + "_fences"));
            ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource(entry.woodName + "_fence_gates"));
            ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource(entry.woodName + "_stairs"));
            ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource(entry.woodName + "_doors"));

            WoodRecipeLoader.registerWoodTypeRecipe(entry);
            GTEWoodRecipeLoader.overridePlankRecipe(true, entry, GTEValues.MODID);
            GTEWoodRecipeLoader.addSawmillRecipe(entry);
        }

        // for (WoodTypeEntry entry : getFireproofEntries()) {
        // ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource("fireproof_planks_" + entry.woodName));
        // ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource("fireproof_slabs_" + entry.woodName));
        // ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource("fireproof_fences_" + entry.woodName));
        // ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource("fireproof_fence_gates_" + entry.woodName));
        // ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource("fireproof_stairs_" + entry.woodName));
        // ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource("fireproof_doors_" + entry.woodName));
        //
        // WoodRecipeLoader.registerWoodTypeRecipe(entry);
        // GTEWoodRecipeLoader.overridePlankRecipe(true, entry, GTEValues.MODID);
        // GTEWoodRecipeLoader.addSawmillRecipe(entry);
        //
        // ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID, entry.woodName + "_saw"));
        // }
    }
}
