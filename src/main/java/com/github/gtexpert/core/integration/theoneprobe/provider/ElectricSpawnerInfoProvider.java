package com.github.gtexpert.core.integration.theoneprobe.provider;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.integration.eio.metatileentities.MetaTileEntityElectricSpawner;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;

import mcjty.theoneprobe.api.*;

public class ElectricSpawnerInfoProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return GTEValues.MODID + ":electric_spawner";
    }

    @Override
    public void addProbeInfo(@NotNull ProbeMode mode, @NotNull IProbeInfo probeInfo, @NotNull EntityPlayer player,
                             @NotNull World world, @NotNull IBlockState blockState, @NotNull IProbeHitData data) {
        if (!blockState.getBlock().hasTileEntity(blockState)) return;
        TileEntity tileEntity = world.getTileEntity(data.getPos());
        if (!(tileEntity instanceof MetaTileEntityHolder mteHolder)) return;
        MetaTileEntity mte = mteHolder.getMetaTileEntity();
        if (!(mte instanceof MetaTileEntityElectricSpawner spawner)) return;

        probeInfo.text(TextStyleClass.WARNING + "{*" + spawner.getSpawnModeTranslationKey() + "*}");
        if (spawner.needsRedstone()) {
            probeInfo.text(TextStyleClass.WARNING + "{*gtexpert.gui.needs_redstone*}");
        }
    }
}
