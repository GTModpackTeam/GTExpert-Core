package gtexpert.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockSawmillConveyor extends Block {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public BlockSawmillConveyor() {
        super(Material.IRON);
        setTranslationKey("sawmill_conveyor");
        setHardness(2.5f);
        setResistance(10.0f);
        setSoundType(SoundType.WOOD);
        setHarvestLevel("wrench", 1);
        setRegistryName("sawmill_conveyor");
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos,
                                    @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    protected @Nonnull BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(@Nonnull IBlockState state) {
        return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
    }

    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public @Nonnull IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX,
                                                     float hitY, float hitZ, int meta,
                                                     @Nonnull EntityLivingBase placer) {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
}
