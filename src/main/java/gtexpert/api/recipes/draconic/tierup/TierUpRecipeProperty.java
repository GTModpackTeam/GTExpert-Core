package gtexpert.api.recipes.draconic.tierup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import com.brandon3055.draconicevolution.lib.ToolUpgradeRecipe;

import gregtech.api.recipes.recipeproperties.RecipeProperty;

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
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_tier_up.property.1"), x, y, color);
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_tier_up.property.2"), x, y + 10,
                color);
    }
}
