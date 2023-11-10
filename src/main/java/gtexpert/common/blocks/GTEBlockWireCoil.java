package gtexpert.common.blocks;

import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.block.VariantActiveBlock;

import gtexpert.api.unification.material.GTEMaterials;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GTEBlockWireCoil extends VariantActiveBlock<GTEBlockWireCoil.CoilType> {

    public GTEBlockWireCoil() {
        super(Material.IRON);
        setTranslationKey("gte_wire_coil");
        setHardness(10.0F);
        setResistance(10.0F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 6);
        setRegistryName("gte_wire_coil");
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos,
                                    @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CoilType implements IStringSerializable, IHeatingCoilBlockStats {

        AWAKENED_DRACONIUM("awakened_draconium", 24000, 20, 12, GTEMaterials.AwakenedDraconium);

        private final String name;
        private final int coilTemperature;
        private final int level;
        private final int energyDiscount;
        private final gregtech.api.unification.material.Material material;

        CoilType(String name, int coilTemperature, int level, int energyDiscount,
                 gregtech.api.unification.material.Material material) {
            this.name = name;
            this.coilTemperature = coilTemperature;
            this.level = level;
            this.energyDiscount = energyDiscount;
            this.material = material;
        }

        @Nonnull
        @Override
        public String getName() {
            return this.name;
        }

        public int getCoilTemperature() {
            return this.coilTemperature;
        }

        public int getLevel() {
            return this.level;
        }

        public int getEnergyDiscount() {
            return this.energyDiscount;
        }

        public int getTier() {
            return this.ordinal();
        }

        @Nullable
        public gregtech.api.unification.material.Material getMaterial() {
            return this.material;
        }

        @Nonnull
        public String toString() {
            return this.getName();
        }
    }
}
