package gtexpert.common;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

public class GreenhouseCasing extends Block {

    public GreenhouseCasing() {
        super(Material.IRON);
        setTranslationKey("greenhousecasing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        setRegistryName("greenhousecasing");
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull SpawnPlacementType type) {
        return false;
    }
}

