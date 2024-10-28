package gtexpert.integration.binnies.extratrees.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ModHandler;
import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.GTEValues;
import gtexpert.api.util.Mods;
import gtexpert.loaders.GTEWoodRecipeLoader;

import binnie.extratrees.wood.EnumETLog;

public class ExtraTreesWoodRecipe {

    private static final String mcModId = Mods.ExtraTrees.name();

    private static List<WoodTypeEntry> DEFAULT_ENTRIES;
    private static List<WoodTypeEntry> FIREPROOF_ENTRIES;

    private static List<WoodTypeEntry> getDefaultEntries() {
        if (DEFAULT_ENTRIES == null) initEntries();
        return DEFAULT_ENTRIES;
    }

    private static List<WoodTypeEntry> getFireproofEntries() {
        if (FIREPROOF_ENTRIES == null) initEntries();
        return FIREPROOF_ENTRIES;
    }

    private static void initEntries() {
        DEFAULT_ENTRIES = new ArrayList<>();
        FIREPROOF_ENTRIES = new ArrayList<>();
        int plankId = -1, logId = -1, slabId = -1;
        for (int i = 0; i < EnumETLog.values().length; i++) {
            String woodName = EnumETLog.values()[i].name().toLowerCase();
            int plankMeta = i % 16;
            int logMeta = i % 4;
            int slabMeta = i % 8;
            if (plankMeta == 0) plankId++;
            if (logMeta == 0) logId++;
            if (slabMeta == 0) slabId++;
            DEFAULT_ENTRIES.add(getEntryByName(woodName, plankId, logId, slabId, plankMeta, logMeta, slabMeta));
            FIREPROOF_ENTRIES
                    .add(getFireProofEntryByName(woodName, plankId, logId, slabId, plankMeta, logMeta, slabMeta));
        }
    }

    private static WoodTypeEntry getEntryByName(String woodName, int plankId, int logId, int slabId, int plankMeta,
                                                int logMeta, int slabMeta) {
        return new WoodTypeEntry.Builder(mcModId, woodName)
                .planks(Mods.ExtraTrees.getItem("planks." + plankId, 1, plankMeta), null)
                .log(Mods.ExtraTrees.getItem("logs." + logId, 1, logMeta)).removeCharcoalRecipe()
                .door(Mods.ExtraTrees.getItem("doors." + woodName), null)
                .slab(Mods.ExtraTrees.getItem("slabs." + slabId, 1, slabMeta), null)
                .fence(Mods.ExtraTrees.getItem("fences." + plankId, 1, plankMeta), null)
                .fenceGate(Mods.ExtraTrees.getItem("fence.gates." + woodName), null)
                .stairs(Mods.ExtraTrees.getItem("stairs." + woodName), null).addStairsRecipe()
                .build();
    }

    private static WoodTypeEntry getFireProofEntryByName(String woodName, int plankId, int logId, int slabId,
                                                         int plankMeta, int logMeta, int slabMeta) {
        return new WoodTypeEntry.Builder(mcModId, woodName)
                .planks(Mods.ExtraTrees.getItem("planks.fireproof." + plankId, 1, plankMeta),
                        "fireproof_planks_" + woodName)
                .log(Mods.ExtraTrees.getItem("logs.fireproof." + logId, 1, logMeta)).removeCharcoalRecipe()
                .slab(Mods.ExtraTrees.getItem("slabs.fireproof." + slabId, 1, slabMeta),
                        "fireproof_slab_" + woodName)
                .fence(Mods.ExtraTrees.getItem("fences.fireproof." + plankId, 1, plankMeta),
                        "fireproof_fence_" + woodName)
                .fenceGate(Mods.ExtraTrees.getItem("fence.gates.fireproof." + woodName),
                        "fireproof_fence_gate_" + woodName)
                .stairs(Mods.ExtraTrees.getItem("stairs.fireproof." + woodName),
                        "fireproof_stair_" + woodName)
                .addStairsRecipe()
                .build();
    }

    public static void init() {
        String[] types = { "_planks", "_slabs", "_fences", "_fence_gates", "_stairs" };
        for (WoodTypeEntry entry : getDefaultEntries()) {
            for (String type : types) {
                ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource(entry.woodName + type));
            }

            // only for normal woods
            ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource(entry.woodName + "_doors"));

            GTEWoodRecipeLoader.registerWoodTypeRecipe(true, entry);
            GTEWoodRecipeLoader.addCuttingRecipe(entry);
            GTEWoodRecipeLoader.addSawmillRecipe(entry);
        }

        for (WoodTypeEntry entry : getFireproofEntries()) {
            for (String type : types) {
                ModHandler.removeRecipeByName(Mods.ExtraTrees.getResource(entry.woodName + "_fireproof" + type));
            }

            GTEWoodRecipeLoader.registerWoodTypeRecipe(true, entry);
            GTEWoodRecipeLoader.addCuttingRecipe(entry);
            GTEWoodRecipeLoader.addSawmillRecipe(entry);

            ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID, entry.woodName + "_saw"));
        }
    }
}
