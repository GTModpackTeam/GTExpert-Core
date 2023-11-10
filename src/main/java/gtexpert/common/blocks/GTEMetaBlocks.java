package gtexpert.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import static gregtech.common.blocks.MetaBlocks.statePropertiesToString;

public class GTEMetaBlocks {

    public static final GTEBlockWireCoil GTE_BLOCK_WIRE_COIL = new GTEBlockWireCoil();
    public static final GTEBlockMetalCasing GTE_BLOCK_METAL_CASING = new GTEBlockMetalCasing();
    public static final BlockSawmillConveyor BLOCK_SAWMILL_CONVEYOR = new BlockSawmillConveyor();

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        GTE_BLOCK_WIRE_COIL.onModelRegister();
        registerItemModel(GTE_BLOCK_METAL_CASING);
        registerItemModel(BLOCK_SAWMILL_CONVEYOR);
    }

    public static GTEBlockMetalCasing.MetalCasingType getType(IBlockState state) {
        return GTE_BLOCK_METAL_CASING.getState(state);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(@Nonnull Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            // noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(state.getProperties())));
        }
    }
}
