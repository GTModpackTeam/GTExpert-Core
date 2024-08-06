package gtexpert.integration.gendustry.metatileentities;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;

import org.jetbrains.annotations.Nullable;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.client.renderer.ICubeRenderer;

import gtexpert.common.metatileentities.GTESimpleMachineMetaTileEntity;

public class MetaTileEntityIndustrialApiary extends GTESimpleMachineMetaTileEntity {

    public MetaTileEntityIndustrialApiary(ResourceLocation metaTileEntityId, ICubeRenderer renderer, int tier) {
        super(metaTileEntityId, null, renderer, tier, true);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityIndustrialApiary(metaTileEntityId, renderer, getTier());
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(this, 6, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(this, 9, this, true);
    }

    @Override
    protected FluidTankList createImportFluidHandler() {
        return new FluidTankList(false);
    }

    @Override
    protected FluidTankList createExportFluidHandler() {
        return new FluidTankList(false);
    }

    @Override
    public boolean hasGhostCircuitInventory() {
        return false;
    }

    @Override
    public SoundEvent getSound() {
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        // prevent NPE with `WorkableTieredMetaTileEntity#addInformation`
        tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_in", energyContainer.getInputVoltage(),
                GTValues.VNF[getTier()]));
        tooltip.add(
                I18n.format("gregtech.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
        String key = this.metaTileEntityId.getPath().split("\\.")[0];
        String mainKey = String.format("gregtech.machine.%s.tooltip", key);
        if (I18n.hasKey(mainKey)) {
            tooltip.add(1, mainKey);
        }
    }

    private IndustrialApiaryLogic getLogic() {
        return (IndustrialApiaryLogic) workable;
    }
}
