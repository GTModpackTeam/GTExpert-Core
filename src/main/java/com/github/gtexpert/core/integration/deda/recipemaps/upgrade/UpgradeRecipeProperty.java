package com.github.gtexpert.core.integration.deda.recipemaps.upgrade;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import gregtech.api.recipes.recipeproperties.RecipeProperty;

public class UpgradeRecipeProperty extends RecipeProperty<UpgradeRecipeInfo> {

    public static final String KEY = "draconic_fusion_upgrade";
    private static UpgradeRecipeProperty INSTANCE;

    private UpgradeRecipeProperty() {
        super(KEY, UpgradeRecipeInfo.class);
    }

    public static UpgradeRecipeProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UpgradeRecipeProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        UpgradeRecipeInfo info = castValue(value);
        String crafterKey = info.requiresAwakenedCrafter() ?
                "recipemap.draconic_fusion.crafter.awakened" :
                "recipemap.draconic_fusion.crafter.draconium";
        // Draw 3 lines: crafter tier, property line 1, property line 2
        // Compensate for getInfoHeight=30 by drawing at y-20, y-10, y
        minecraft.fontRenderer.drawString(I18n.format(crafterKey), x, y - 20, color);
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_upgrade.property.1"), x, y - 10,
                color);
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_upgrade.property.2"), x, y, color);
    }

    @Override
    public int getInfoHeight(Object value) {
        return 30;
    }
}
