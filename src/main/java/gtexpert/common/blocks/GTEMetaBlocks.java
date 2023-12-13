package gtexpert.common.blocks;

import static gregtech.common.blocks.MetaBlocks.statePropertiesToString;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTEMetaBlocks {

    public static GTEBlockWireCoil GTE_WIRE_COIL;
    public static GTEBlockMetalCasing GTE_METAL_CASING;
    public static BlockSawmillConveyor BLOCK_SAWMILL_CONVEYOR;

    public static void init() {
        GTE_METAL_CASING = new GTEBlockMetalCasing();
        GTE_METAL_CASING.setRegistryName("gte_metal_casing");
        BLOCK_SAWMILL_CONVEYOR = new BlockSawmillConveyor();
        BLOCK_SAWMILL_CONVEYOR.setRegistryName("sawmill_conveyor");
        GTE_WIRE_COIL = new GTEBlockWireCoil();
        GTE_WIRE_COIL.setRegistryName("gte_wire_coil");
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        registerItemModel(GTE_METAL_CASING);
        registerItemModel(BLOCK_SAWMILL_CONVEYOR);
        GTE_WIRE_COIL.onModelRegister();
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            // noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(state.getProperties())));
        }
    }
}
