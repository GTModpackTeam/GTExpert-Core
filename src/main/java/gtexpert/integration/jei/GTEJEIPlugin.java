package gtexpert.integration.jei;

import gregtech.api.GTValues;
import gtexpert.api.GTEValues;
import gtexpert.api.recipes.ingredients.draconic.GTEDraconicRecipeMaps;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraftforge.fml.common.Loader;
import org.jetbrains.annotations.NotNull;

@JEIPlugin
public class GTEJEIPlugin implements IModPlugin {

    @Override
    public void register(@NotNull IModRegistry registry) {
        if (!Loader.isModLoaded(GTEValues.MODID_DE) && !Loader.isModLoaded(GTEValues.MODID_DA)) {
            registry.addRecipeCatalyst(GTEMetaTileEntities.DRACONIUM_FUSION.getStackForm(), GTValues.MODID + ":" + GTEDraconicRecipeMaps.DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
            registry.addRecipeCatalyst(GTEMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), GTValues.MODID + ":" + GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_TIER_UP_FAKE_RECIPES.unlocalizedName);
            registry.addRecipeCatalyst(GTEMetaTileEntities.DRACONIUM_FUSION.getStackForm(), GTValues.MODID + ":" + GTEDraconicRecipeMaps.DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
            registry.addRecipeCatalyst(GTEMetaTileEntities.AWAKENED_DRACONIUM_FUSION.getStackForm(), GTValues.MODID + ":" + GTEDraconicRecipeMaps.AWAKENED_DRACONIC_FUSION_UPGRADE_FAKE_RECIPES.unlocalizedName);
        }
    }
}
