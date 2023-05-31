package gtexpert.common.metatileentities;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.metatileentities.multi.*;
import gtexpert.common.metatileentities.single.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.GTValues.*;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gregtech.common.metatileentities.MetaTileEntities.registerSimpleMetaTileEntity;

public class GTEMetaTileEntities {
    public static final MetaTileEntityExtremeMixer[] EXTREME_MIXER = new MetaTileEntityExtremeMixer[1];
    public static final MetaTileEntityAutoChisel[] AUTO_CHISEL = new MetaTileEntityAutoChisel[3];
    public static MetaTileEntitySawmill SAWMILL;
    public static SimpleMachineMetaTileEntity[] VIAL_EXTRACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityVoidOreMiner VOIDOREMINER;
    public static MetaTileEntityDraconiumFusion DRACONIUM_FUSION;
    public static MetaTileEntityDraconiumFusion AWAKENED_DRACONIUM_FUSION;

    public static void init() {
        /*
         * FOR ADDON DEVELOPERS:
         *
         * GTCEu will not take more than 2000 IDs. Anything past ID 1999
         * is considered FAIR GAME, take whatever you like.
         *
         * If you would like to reserve IDs, feel free to reach out to the
         * development team and claim a range of IDs! We will mark any
         * claimed ranges below this comment. Max value is 32767.
         *
         * - Gregicality / Shadows of Greg: 2000-3999
         * - Gregification: 4000-4499
         * - GregTech Food Option: 8500-8999
         * - HtmlTech: 9000-9499
         * - PCM's Ore Addon: 9500-9999
         * - GCM: 10000-10099
         * - MechTech: 10100-10499
         * - MBT 10500 - 10999
         * - CT(MBT) 32000 - ~
         * - FREE RANGE 11000-32767
         */

        // blocks :11001~
        // EXTREME_MIXER 11007
        EXTREME_MIXER[0] = registerMetaTileEntity(11007, new MetaTileEntityExtremeMixer(gteId("extreme_mixer"), GTERecipeMaps.EXTREME_MIXER_RECIPES, GTETextures.EXTREME_MIXER_OVERLAY, ZPM, true, GTUtility.hvCappedTankSizeFunction));

        // VIAL_EXTRACTOR 11010~11022
        registerSimpleMetaTileEntity(VIAL_EXTRACTOR, 11010, "vial_extractor", GTERecipeMaps.VIAL_EXTRACTOR_RECIPES, GTETextures.VIAL_EXTRACTOR_OVERLAY, true, GTEMetaTileEntities::gteId, GTUtility.hvCappedTankSizeFunction);

        // AUTO_CHISEL 11023~11025
        AUTO_CHISEL[0] = registerMetaTileEntity(11023, new MetaTileEntityAutoChisel(gteId("auto_chisel.lv"), GTERecipeMaps.AUTO_CHISEL_RECIPES, GTETextures.AUTO_CHISEL_OVERLAY, LV, true));
        AUTO_CHISEL[1] = registerMetaTileEntity(11024, new MetaTileEntityAutoChisel(gteId("auto_chisel.mv"), GTERecipeMaps.AUTO_CHISEL_RECIPES, GTETextures.AUTO_CHISEL_OVERLAY, MV, true));
        AUTO_CHISEL[2] = registerMetaTileEntity(11025, new MetaTileEntityAutoChisel(gteId("auto_chisel.hv"), GTERecipeMaps.AUTO_CHISEL_RECIPES, GTETextures.AUTO_CHISEL_OVERLAY, HV, true));

        // multiblocks :12000~
        SAWMILL = registerMetaTileEntity(12001, new MetaTileEntitySawmill(gteId("sawmill")));
        // = registerMetaTileEntity(12002, new MetaTileEntityVoidOreMiner(gteId("####")));
        VOIDOREMINER = registerMetaTileEntity(12003, new MetaTileEntityVoidOreMiner(gteId("void_ore_miner")));

        if (Loader.isModLoaded(GTEValues.MODID_DE) && Loader.isModLoaded(GTEValues.MODID_DA)) {
            DRACONIUM_FUSION = registerMetaTileEntity(12004, new MetaTileEntityDraconiumFusion.TierDraconic(gteId("draconium_fusion")));
            AWAKENED_DRACONIUM_FUSION = registerMetaTileEntity(12005, new MetaTileEntityDraconiumFusion.TierAwakened(gteId("awakened_draconium_fusion")));
        }
    }

    @NotNull
    private static ResourceLocation gteId(@NotNull String name) {
        return new ResourceLocation(GTEValues.MODID, name);
    }
}
