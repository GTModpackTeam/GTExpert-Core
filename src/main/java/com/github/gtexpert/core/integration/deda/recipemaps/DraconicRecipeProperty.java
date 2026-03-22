package com.github.gtexpert.core.integration.deda.recipemaps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import gregtech.api.recipes.recipeproperties.RecipeProperty;

public class DraconicRecipeProperty extends RecipeProperty<DraconicRecipeInfo> {

    public static final String TIER_UP_KEY = "draconic_fusion_tier_up";
    public static final String UPGRADE_KEY = "draconic_fusion_upgrade";

    private static DraconicRecipeProperty TIER_UP_INSTANCE;
    private static DraconicRecipeProperty UPGRADE_INSTANCE;

    private final String langKeyPrefix;

    private DraconicRecipeProperty(String key, String langKeyPrefix) {
        super(key, DraconicRecipeInfo.class);
        this.langKeyPrefix = langKeyPrefix;
    }

    public static DraconicRecipeProperty getTierUpInstance() {
        if (TIER_UP_INSTANCE == null) {
            TIER_UP_INSTANCE = new DraconicRecipeProperty(TIER_UP_KEY, "recipemap.draconic_fusion_tier_up");
        }
        return TIER_UP_INSTANCE;
    }

    public static DraconicRecipeProperty getUpgradeInstance() {
        if (UPGRADE_INSTANCE == null) {
            UPGRADE_INSTANCE = new DraconicRecipeProperty(UPGRADE_KEY, "recipemap.draconic_fusion_upgrade");
        }
        return UPGRADE_INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        DraconicRecipeInfo info = castValue(value);
        String crafterKey = info.requiresAwakenedCrafter() ?
                "recipemap.draconic_fusion.crafter.awakened" :
                "recipemap.draconic_fusion.crafter.draconium";
        // Draw 3 lines: crafter tier, property line 1, property line 2
        // Compensate for getInfoHeight=30 by drawing at y-20, y-10, y
        minecraft.fontRenderer.drawString(I18n.format(crafterKey), x, y - 20, color);
        minecraft.fontRenderer.drawString(I18n.format(langKeyPrefix + ".property.1"), x, y - 10, color);
        minecraft.fontRenderer.drawString(I18n.format(langKeyPrefix + ".property.2"), x, y, color);
    }

    @Override
    public int getInfoHeight(Object value) {
        return 30;
    }
}
