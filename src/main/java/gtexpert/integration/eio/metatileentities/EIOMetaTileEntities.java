package gtexpert.integration.eio.metatileentities;

import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gtexpert.api.util.GTEUtility.gteId;

import java.util.function.Function;

import net.minecraft.util.ResourceLocation;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;

import gtexpert.api.util.GTEUtility;
import gtexpert.client.GTETextures;
import gtexpert.core.metatileentities.GTESimpleMachineMetaTileEntity;
import gtexpert.integration.eio.EnderIORecipeMaps;

public class EIOMetaTileEntities {

    public static GTESimpleMachineMetaTileEntity[] VIAL_EXTRACTOR = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static GTESimpleMachineMetaTileEntity[] SLICE_N_SPLICE = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static GTESimpleMachineMetaTileEntity[] SOUL_BINDER = new GTESimpleMachineMetaTileEntity[V.length - 1];
    public static MetaTileEntityElectricSpawner[] ELECTRIC_SPAWNER = new MetaTileEntityElectricSpawner[V.length - 1];

    public static void init() {
        // VIAL_EXTRACTOR 11010~11022
        registerGTESimpleMetaTileEntity(VIAL_EXTRACTOR, 11010, "vial_extractor",
                EnderIORecipeMaps.VIAL_EXTRACTOR_RECIPES,
                GTETextures.VIAL_EXTRACTOR_OVERLAY, true, GTEUtility::gteId, GTUtility.hvCappedTankSizeFunction);

        // SLICE_N_SPLICE 11023~11035
        registerGTESimpleMetaTileEntity(SLICE_N_SPLICE, 11023, "slice_n_splice",
                EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES,
                GTETextures.SLICE_N_SPLICE_OVERLAY, true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // SOUL_BINDER 11036~11048
        registerGTESimpleMetaTileEntity(SOUL_BINDER, 11036, "soul_binder", EnderIORecipeMaps.SOUL_BINDER_RECIPES,
                GTETextures.SOUL_BINDER_OVERLAY, true, GTEUtility::gteId, GTUtility.defaultTankSizeFunction);

        // ELECTRIC_SPAWNER 11049~11061
        registerMetaTileEntities(ELECTRIC_SPAWNER, 11049, "electric_spawner",
                (tier, voltageName) -> new MetaTileEntityElectricSpawner(
                        gteId(String.format("%s.%s", "electric_spawner", voltageName)),
                        GTETextures.SPAWNER_OVERLAY,
                        tier));
    }

    public static void registerGTESimpleMetaTileEntity(GTESimpleMachineMetaTileEntity[] machines, int startId,
                                                       String name, RecipeMap<?> map, ICubeRenderer texture,
                                                       boolean hasFrontFacing,
                                                       Function<String, ResourceLocation> resourceId,
                                                       Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i < machines.length - 1; ++i) {
            if (i <= 4 || getMidTier(name)) {
                if (i > 7 && !getHighTier(name)) {
                    break;
                }

                String voltageName = GTValues.VN[i + 1].toLowerCase();
                machines[i + 1] = registerMetaTileEntity(startId + i,
                        new GTESimpleMachineMetaTileEntity(resourceId.apply(String.format("%s.%s", name, voltageName)),
                                map, texture, i + 1, hasFrontFacing, tankScalingFunction));
            }
        }
    }
}
