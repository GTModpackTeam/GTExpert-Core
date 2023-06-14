package gtexpert.integration.theoneprobe.provider;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gtexpert.api.GTEValues;
import gtexpert.common.metatileentities.single.MetaTileEntityElectricSpawner;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ElectricSpawnerInfoProvider implements IProbeInfoProvider {
    @Override
    public String getID() {
        return GTEValues.MODID + ":electric_spawner";
    }

    @Override
    public void addProbeInfo(@Nonnull ProbeMode mode, @Nonnull IProbeInfo probeInfo, @Nonnull EntityPlayer player, @Nonnull World world, @Nonnull IBlockState blockState, @Nonnull IProbeHitData data) {
        if (!blockState.getBlock().hasTileEntity(blockState)) return;
        TileEntity tileEntity = world.getTileEntity(data.getPos());
        if (!(tileEntity instanceof MetaTileEntityHolder)) return;
        MetaTileEntityHolder mteHolder = (MetaTileEntityHolder) tileEntity;
        MetaTileEntity mte = mteHolder.getMetaTileEntity();
        if (!(mte instanceof MetaTileEntityElectricSpawner)) return;

        MetaTileEntityElectricSpawner spawner = (MetaTileEntityElectricSpawner) mte;
        probeInfo.text(TextStyleClass.WARNING + "{*" + spawner.getSpawnModeTranslationKey() + "*}");
        if (spawner.needsRedstone()) {
            probeInfo.text(TextStyleClass.WARNING + "{*gtexpert.gui.needs_redstone*}");
        }
    }
}
