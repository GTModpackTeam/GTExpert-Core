package gtexpert.api.unification.material.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.util.GTUtility.gregtechId;
import static gtexpert.api.unification.GTEElements.*;
import static gtexpert.api.unification.material.GTEMaterials.*;

import net.minecraftforge.fml.common.Loader;

import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty.GasTier;
import gregtech.api.unification.material.properties.ToolProperty;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import gtexpert.api.GTEValues;

public class DEFirstDegreeMaterials {

    /**
     * 24176 - 24200
     */
    public static void init() {
        // Cryotheum
        Cryotheum = new Material.Builder(24176, gregtechId("cryotheum"))
                .liquid(new FluidBuilder().temperature(2))
                .color(0x00B6FF).iconSet(MaterialIconSet.FLUID)
                .flags(NO_WORKING, DISABLE_DECOMPOSITION)
                .components(Electrotine, 1, EnderPearl, 1, Ice, 4)
                .build();

        // Pyrotheum
        Pyrotheum = new Material.Builder(24177, gregtechId("pyrotheum"))
                .fluid(GCYMFluidStorageKeys.MOLTEN, new FluidBuilder().temperature(10273))
                .color(0xE42C13).iconSet(MaterialIconSet.BRIGHT)
                .flags(NO_WORKING, DISABLE_DECOMPOSITION)
                .components(Redstone, 1, Sulfur, 1, Blaze, 16)
                .build();

        // Dragon Dust
        Dragon = new Material.Builder(24178, gregtechId("dragon"))
                .dust()
                .color(0x000000).iconSet(MaterialIconSet.ROUGH)
                .flags(NO_WORKING, DISABLE_DECOMPOSITION)
                .components(Iridium, 1, EnderEye, 1, SaltWater, 1,
                        Loader.isModLoaded(GTEValues.MODID_EIO) ? EndSteel : Endstone, 1)
                .build();

        // Draconium
        Draconium = new Material.Builder(24180, gregtechId("draconium"))
                .ore(2, 1, true)
                .ingot(5)
                .liquid(new FluidBuilder().temperature(1200))
                .color(0x7C46B0).iconSet(MaterialIconSet.METALLIC)
                .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FRAME, GENERATE_FINE_WIRE, GENERATE_RING,
                        EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES, EXCLUDE_BLOCK_CRAFTING_RECIPES,
                        EXCLUDE_PLATE_COMPRESSOR_RECIPE, DISABLE_DECOMPOSITION)
                .components(Dragon, 1, Obsidian, 1, EnderPearl, 4, LiquidEnderAir, 8)
                .blast(b -> b
                        .temp(7200, GasTier.HIGHER)
                        .blastStats(VA[GTEValues.dedaVoltageTier], 400)
                        .vacuumStats(VA[LuV], 100))
                .toolStats(ToolProperty.Builder.of(80.0F, 2.0F, 4096, 5)
                        .enchantability(14).build())
                .rotorStats(18.0f, 4.0f, 4096)
                .cableProperties(V[UEV], 8, 4, false)
                .element(De)
                .build();

        // Awakened Draconium
        AwakenedDraconium = new Material.Builder(24181, gregtechId("awakened_draconium"))
                .ingot(6)
                .liquid(new FluidBuilder().temperature(1200))
                .color(0xFF6C00).iconSet(MaterialIconSet.METALLIC)
                .flags(EXT2_METAL, GENERATE_DOUBLE_PLATE, GENERATE_FRAME, GENERATE_FINE_WIRE, GENERATE_RING,
                        EXCLUDE_BLOCK_CRAFTING_BY_HAND_RECIPES, EXCLUDE_BLOCK_CRAFTING_RECIPES,
                        EXCLUDE_PLATE_COMPRESSOR_RECIPE, DISABLE_DECOMPOSITION)
                .blast(b -> b
                        .temp(7200, GasTier.HIGHER)
                        .blastStats(VA[GTEValues.dedaVoltageTier], 600))
                .toolStats(ToolProperty.Builder.of(140.0F, 80.0F, 7168, 6).enchantability(20).build())
                .rotorStats(21.5f, 7.5f, 7168)
                .fluidPipeProperties(10_000_000, 150_000, true, true, true, true)
                .cableProperties(V[MAX], 1, 8, false)
                .element(De1)
                .build();

        // Chaos Dust
        Chaos = new Material.Builder(24179, gregtechId("chaos"))
                .dust()
                .color(0x000000).iconSet(MaterialIconSet.ROUGH)
                .flags(NO_WORKING, DISABLE_DECOMPOSITION)
                .components(AwakenedDraconium, 8, Pyrotheum, 8, Dragon, 8)
                .build();
    }
}
