package com.github.gtexpert.core.integration.deda.recipemaps.tierup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTBase;

import org.jetbrains.annotations.NotNull;

import com.brandon3055.draconicevolution.lib.ToolUpgradeRecipe;

import gregtech.api.recipes.properties.RecipeProperty;

public class TierUpRecipeProperty extends RecipeProperty<ToolUpgradeRecipe> {

    public static final String KEY = "draconic_fusion_tier_up";
    private static TierUpRecipeProperty INSTANCE;

    private TierUpRecipeProperty() {
        super(KEY, ToolUpgradeRecipe.class);
    }

    public static TierUpRecipeProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TierUpRecipeProperty();
        }
        return INSTANCE;
    }

    @Override
    public @NotNull NBTBase serialize(@NotNull Object value) {
        return null;
    }

    @Override
    public @NotNull Object deserialize(@NotNull NBTBase nbt) {
        return null;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_tier_up.property.1"), x, y, color);
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_tier_up.property.2"), x, y + 10,
                color);
    }
}
