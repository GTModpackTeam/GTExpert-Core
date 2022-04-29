package gtexpert.common.metatileentities;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class GTEMetaTileEntities {

    public static MetaTileEntitySawmill SAWMILL;

    public static void init() {
        SAWMILL = registerMetaTileEntity(11001, new MetaTileEntitySawmill(gteId("sawmill")));
    }

    @Nonnull
    private static ResourceLocation gteId(String name) {
        return new ResourceLocation("gtexpert", name);
    }
}
