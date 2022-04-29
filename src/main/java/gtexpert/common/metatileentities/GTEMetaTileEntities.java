package gtexpert.common.metatileentities;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.MetaTileEntities;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.client.GTETextures;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import java.util.function.Function;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity;

public class GTEMetaTileEntities {

    public static MetaTileEntitySawmill SAWMILL;
    public static SimpleMachineMetaTileEntity[] EXTREME_MIXER = new SimpleMachineMetaTileEntity[1]; //ZPM Only

    public static void init() {
        SAWMILL = registerMetaTileEntity(11001, new MetaTileEntitySawmill(gteId("sawmill")));
        EXTREME_MIXER[0]=registerMetaTileEntity(11002,
                new SimpleMachineMetaTileEntity(gteId(String.format("%s.%s", "extreme_mixer", GTValues.VN[7].toLowerCase())), GTERecipeMaps.EXTREME_MIXER_RECIPES, GTETextures.EXTREME_MIXER_OVERLAY, 7, true, GTUtility.hvCappedTankSizeFunction));
    }

    @Nonnull
    private static ResourceLocation gteId(String name) {
        return new ResourceLocation("gtexpert", name);
    }
}
