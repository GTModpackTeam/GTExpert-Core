package gtexpert.integration.deda.recipemaps.upgrade;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTBase;

import org.jetbrains.annotations.NotNull;

import com.brandon3055.draconicevolution.api.itemupgrade.FusionUpgradeRecipe;

import gregtech.api.recipes.properties.RecipeProperty;

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
    public @NotNull NBTBase serialize(@NotNull Object value) {
        // TODO: FIX
        return null;
    }

    @Override
    public @NotNull Object deserialize(@NotNull NBTBase nbt) {
        // TODO: FIX
        return null;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_upgrade.property.1"), x, y, color);
        minecraft.fontRenderer.drawString(I18n.format("recipemap.draconic_fusion_upgrade.property.2"), x, y + 10,
                color);
    }
}
