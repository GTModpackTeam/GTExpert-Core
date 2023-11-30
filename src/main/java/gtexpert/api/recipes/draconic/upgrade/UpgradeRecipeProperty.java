package gtexpert.api.recipes.draconic.upgrade;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

import com.brandon3055.draconicevolution.api.itemupgrade.FusionUpgradeRecipe;

import gregtech.api.recipes.recipeproperties.RecipeProperty;

public class UpgradeRecipeProperty extends RecipeProperty<FusionUpgradeRecipe> {

    public static final String KEY = "draconic_fusion_upgrade";
    private static UpgradeRecipeProperty INSTANCE;

    private UpgradeRecipeProperty() {
        super(KEY, FusionUpgradeRecipe.class);
    }

    public static UpgradeRecipeProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UpgradeRecipeProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_upgrade.property.1"), x, y, color);
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_upgrade.property.2"), x, y + 10,
                color);
    }
}
