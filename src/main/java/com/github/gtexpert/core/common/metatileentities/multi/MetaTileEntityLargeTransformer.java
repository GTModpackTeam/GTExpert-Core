package com.github.gtexpert.core.common.metatileentities.multi;

import java.util.ArrayList;
import java.util.List;

import gregtech.common.blocks.BlockMetalCasing;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockComputerCasing;
import gregtech.common.blocks.MetaBlocks;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import org.jetbrains.annotations.Nullable;

public class MetaTileEntityLargeTransformer extends MultiblockWithDisplayBase implements IControllable {

    private EnergyContainerList input;
    private ExtendedEnergyContainerList output;
    private boolean isWorkingEnabled = true;
    private boolean isActive = true;

    public MetaTileEntityLargeTransformer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.input = new EnergyContainerList(new ArrayList<>());
        this.output = new ExtendedEnergyContainerList(new ArrayList<>());
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityLargeTransformer(metaTileEntityId);
    }

    @Override
    protected void updateFormedValid() {
        if (isWorkingEnabled()) {
            long canDrain = input.getEnergyStored();
            long totalDrained = output.changeEnergy(canDrain);
            input.removeEnergy(totalDrained);
        }
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XSX", "XXX")
                .where('X', abilities(MultiblockAbility.MAINTENANCE_HATCH).setExactLimit(1)
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setExactLimit(1))
                        .or(abilities(MultiblockAbility.OUTPUT_ENERGY).setExactLimit(1))
                        .or(states(
                                MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PALLADIUM_SUBSTATION))
                                        .setExactLimit(2)))
                .where('S', selfPredicate())
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        List<IEnergyContainer> powerInput = new ArrayList<>(getAbilities(MultiblockAbility.INPUT_ENERGY));

        List<IEnergyContainer> powerOutput = new ArrayList<>(getAbilities(MultiblockAbility.OUTPUT_ENERGY));

        // Invalidate the structure if there is not at least one output and one input
        if (powerInput.isEmpty() || powerOutput.isEmpty()) {
            this.invalidateStructure();
        }

        this.input = new EnergyContainerList(powerInput);
        this.output = new ExtendedEnergyContainerList(powerOutput);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.input = new EnergyContainerList(new ArrayList<>());
        this.output = new ExtendedEnergyContainerList(new ArrayList<>());
        setActive(false);
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    @Override
    public boolean isWorkingEnabled() {
        return isWorkingEnabled;
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        this.isWorkingEnabled = isWorkingAllowed;
        markDirty();
        World world = getWorld();
        if (world != null && !world.isRemote) {
            writeCustomData(GregtechDataCodes.WORKING_ENABLED, buf -> buf.writeBoolean(isWorkingEnabled));
        }
    }

    @Override
    public boolean isActive() {
        return super.isActive() && this.isWorkingEnabled;
    }

    public void setActive(boolean active) {
        if (this.isActive != active) {
            this.isActive = active;
            markDirty();
            World world = getWorld();
            if (world != null && !world.isRemote) {
                writeCustomData(GregtechDataCodes.WORKABLE_ACTIVE, buf -> buf.writeBoolean(active));
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("isActive", this.isActive);
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.isActive = data.getBoolean("isActive");
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(this.isActive);
        buf.writeBoolean(this.isWorkingEnabled);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
        this.isWorkingEnabled = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, @NotNull PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            this.isActive = buf.readBoolean();
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.isWorkingEnabled = buf.readBoolean();
            scheduleRenderUpdate();
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return super.getCapability(capability, side);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.PALLADIUM_SUBSTATION_CASING;
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.DATA_BANK_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.isActive(),
                this.isWorkingEnabled());
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(true, isActive() && isWorkingEnabled())
                .addCustom(tl -> {
                    if (isStructureFormed()) {
                        // input
                        String inputEnergyFormatted = TextFormattingUtil.formatNumbers(getInputVolt());
                        ITextComponent inputVoltageName = new TextComponentString(
                                GTValues.VNF[GTUtility.getFloorTierByVoltage(getInputVolt())]);
                        ITextComponent inputAmp = TextComponentUtil.stringWithColor(
                                TextFormatting.YELLOW,
                                TextFormattingUtil.formatNumbers(getInputAmp()));
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gtexpert.multiblock.large_transformer.max_input_energy_per_tick_amps",
                                inputEnergyFormatted, inputVoltageName, inputAmp));
                        // output
                        String outputEnergyFormatted = TextFormattingUtil.formatNumbers(getOutputVolt());
                        ITextComponent outputVoltageName = new TextComponentString(
                                GTValues.VNF[GTUtility.getFloorTierByVoltage(getOutputVolt())]);
                        ITextComponent outputAmp = TextComponentUtil.stringWithColor(
                                TextFormatting.YELLOW,
                                TextFormattingUtil.formatNumbers(getOutputAmp()));
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gtexpert.multiblock.large_transformer.max_output_energy_per_tick_amps",
                                outputEnergyFormatted, outputVoltageName, outputAmp));
                    }
                })
                .addWorkingStatusLine();
    }

    @Override
    protected void addWarningText(List<ITextComponent> textList) {
        super.addWarningText(textList);
        if (isStructureFormed() && this.getOutput() > this.getInput()) {
            textList.add(TextComponentUtil.translationWithColor(
                    TextFormatting.YELLOW,
                    "gtexpert.multiblock.large_transformer.not_enough_input"));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip,
                               boolean advanced) {
        tooltip.add(I18n.format("gtexpert.machine.large_transformer.tooltip.1"));
        tooltip.add(I18n.format("gtexpert.machine.large_transformer.tooltip.2"));
    }

    private long getInput() {
        return input.getInputVoltage() * input.getInputAmperage();
    }

    private long getOutput() {
        return output.getOutputVoltage() * output.getOutputAmperage();
    }

    private long getInputAmp() {
        return getInput() / input.getHighestInputVoltage();
    }

    private long getInputVolt() {
        return input.getHighestInputVoltage();
    }

    private long getOutputAmp() {
        return getOutput() / output.getHighestOutputVoltage();
    }

    private long getOutputVolt() {
        return output.getHighestOutputVoltage();
    }

    private static class ExtendedEnergyContainerList extends EnergyContainerList {

        private final List<IEnergyContainer> energyContainerList;
        /** The highest single energy container's input voltage in the list. */
        private final long highestOutputVoltage;
        /** The number of energy containers at the highest input voltage in the list. */
        private final int numHighestOutputContainers;

        public ExtendedEnergyContainerList(@NotNull List<IEnergyContainer> energyContainerList) {
            super(energyContainerList);
            this.energyContainerList = energyContainerList;
            long highestOutputVoltage = 0;
            int numHighestOutputContainers = 0;
            for (IEnergyContainer container : energyContainerList) {
                if (container.getOutputVoltage() > highestOutputVoltage) {
                    highestOutputVoltage = container.getOutputVoltage();
                }
            }
            for (IEnergyContainer container : energyContainerList) {
                if (container.getOutputVoltage() == highestOutputVoltage) {
                    numHighestOutputContainers++;
                }
            }
            this.highestOutputVoltage = highestOutputVoltage;
            this.numHighestOutputContainers = numHighestOutputContainers;
        }

        /** The highest single voltage of an energy container in this list. */
        public long getHighestOutputVoltage() {
            return highestOutputVoltage;
        }

        /**
         * The number of parts with voltage specified in {@link ExtendedEnergyContainerList#getHighestOutputVoltage()}
         * in this list.
         */
        public int getNumHighestOutputContainers() {
            return numHighestOutputContainers;
        }
    }
}
