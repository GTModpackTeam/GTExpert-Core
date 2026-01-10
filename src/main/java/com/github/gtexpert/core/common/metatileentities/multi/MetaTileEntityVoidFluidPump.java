package com.github.gtexpert.core.common.metatileentities.multi;

import java.util.Collections;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Lists;

import gregtech.api.GTValues;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidDrillLogic;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.IProgressBarMultiblock;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.api.worldgen.bedrockFluids.BedrockFluidVeinHandler;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;

import com.github.gtexpert.core.api.capability.impl.VoidFluidPumpLogic;
import com.github.gtexpert.core.api.gui.GTEGuiTextures;
import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.client.GTETextures;
import com.github.gtexpert.core.common.GTEConfigHolder;
import com.github.gtexpert.core.common.blocks.GTEBlockMetalCasing;
import com.github.gtexpert.core.common.blocks.GTEMetaBlocks;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;

public class MetaTileEntityVoidFluidPump extends MultiblockWithDisplayBase
                                         implements IWorkable, IProgressBarMultiblock {

    private final VoidFluidPumpLogic minerLogic;

    protected IMultipleTankHandler inputFluidInventory;
    protected IMultipleTankHandler outputFluidInventory;
    protected IEnergyContainer energyContainer;

    public MetaTileEntityVoidFluidPump(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.minerLogic = new VoidFluidPumpLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityVoidFluidPump(metaTileEntityId);
    }

    protected void initializeAbilities() {
        this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.inputFluidInventory = new FluidTankList(true);
        this.outputFluidInventory = new FluidTankList(true);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
    }

    @Override
    protected void updateFormedValid() {
        this.minerLogic.performDrilling();
        if (!getWorld().isRemote && this.minerLogic.wasActiveAndNeedsUpdate()) {
            this.minerLogic.setWasActiveAndNeedsUpdate(false);
            this.minerLogic.setActive(false);
        }
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXXXX", "****F**", "****F**", "****F**")
                .aisle("XXXXHXX", "F*****F", "F*****F", "FFFFFFF")
                .aisle("XXXXHXX", "*******", "*******", "****F**")
                .aisle("XXXXHXX", "F*****F", "F*****F", "FFFFFFF")
                .aisle("SXXXXXX", "****F**", "****F**", "****F**")
                .where('S', selfPredicate())
                .where('X',
                        states(GTEMetaBlocks.GTE_METAL_CASING
                                .getState(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER))
                                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setExactLimit(1))
                                        .or(autoAbilities(true, false)))
                .where('F', frames(GTEMaterials.NM_HEA_NPs))
                .where('H', abilities(MultiblockAbility.EXPORT_FLUIDS).setExactLimit(1)
                        .or(states(GTEMetaBlocks.GTE_METAL_CASING
                                .getState(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER))))
                .where('*', any())
                .build();
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    @Override
    public boolean allowsExtendedFacing() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTETextures.VOID_ORE_MINER_CASING;
    }

    @Override
    protected @NotNull TextureArea getLogo() {
        return GTEGuiTextures.GTE_LOGO_DARK;
    }

    @Override
    protected @NotNull TextureArea getWarningLogo() {
        return GTEGuiTextures.GTE_LOGO_BLINKING_YELLOW;
    }

    @Override
    protected @NotNull TextureArea getErrorLogo() {
        return GTEGuiTextures.GTE_LOGO_BLINKING_RED;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.FLUID_VOIDING_ADVANCED;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(minerLogic.isWorkingEnabled(), minerLogic.isActive())
                .setWorkingStatusKeys(
                        "gregtech.multiblock.idling",
                        "gregtech.multiblock.work_paused",
                        "gregtech.multiblock.miner.drilling")
                .addEnergyUsageLine(energyContainer)
                .addCustom(tl -> {
                    if (isStructureFormed()) {
                        // Production multiplier info
                        ITextComponent multiplierInfo = TextComponentUtil.stringWithColor(
                                TextFormatting.AQUA,
                                getOverclockMultiplier() + "x");
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gtexpert.multiblock.void_fluid_pump.multiplier",
                                multiplierInfo));

                        if (minerLogic.getDrilledFluid() != null) {
                            // Fluid name
                            Fluid drilledFluid = minerLogic.getDrilledFluid();
                            ITextComponent fluidInfo = TextComponentUtil
                                    .setColor(GTUtility.getFluidTranslation(drilledFluid), TextFormatting.GREEN);
                            tl.add(TextComponentUtil.translationWithColor(
                                    TextFormatting.GRAY,
                                    "gregtech.multiblock.fluid_rig.drilled_fluid",
                                    fluidInfo));

                            // Fluid amount
                            ITextComponent amountInfo = TextComponentUtil.stringWithColor(
                                    TextFormatting.BLUE,
                                    TextFormattingUtil.formatNumbers(
                                            minerLogic.getFluidToProduce() * 20L / FluidDrillLogic.MAX_PROGRESS) +
                                            " L/s");
                            tl.add(TextComponentUtil.translationWithColor(
                                    TextFormatting.GRAY,
                                    "gregtech.multiblock.fluid_rig.fluid_amount",
                                    amountInfo));
                        } else {
                            ITextComponent noFluid = TextComponentUtil.translationWithColor(TextFormatting.RED,
                                    "gregtech.multiblock.fluid_rig.no_fluid_in_area");
                            tl.add(TextComponentUtil.translationWithColor(
                                    TextFormatting.GRAY,
                                    "gregtech.multiblock.fluid_rig.drilled_fluid",
                                    noFluid));
                        }
                    }
                })
                .addWorkingStatusLine()
                .addProgressLine(minerLogic.getProgressPercent());
    }

    @Override
    protected void addWarningText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed(), false)
                .addLowPowerLine(isStructureFormed() && !drainEnergy(true))
                .addCustom(tl -> {
                    if (isStructureFormed() && minerLogic.isInventoryFull()) {
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.YELLOW,
                                "gregtech.machine.miner.invfull"));
                    }
                });
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.void_fluid_pump.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.fluid_drilling_rig.description"));
        tooltip.add(I18n.format("gregtech.machine.fluid_drilling_rig.depletion",
                TextFormattingUtil.formatNumbers(0)));
        tooltip.add(I18n.format("gtexpert.machine.void_fluid_pump.tooltip.2", GTValues.VNF[getBaseTier()],
                getBaseMultiplier()));
        tooltip.add(I18n.format("gtexpert.machine.void_fluid_pump.tooltip.3"));
        tooltip.add(I18n.format("gtexpert.machine.void_fluid_pump.tooltip.overclock"));
    }

    @Override
    public void addToolUsages(ItemStack stack, @Nullable World world, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.tool_action.screwdriver.access_covers"));
        tooltip.add(I18n.format("gregtech.tool_action.wrench.set_facing"));
        super.addToolUsages(stack, world, tooltip, advanced);
    }

    public int getBaseTier() {
        return GTEConfigHolder.gteFlag.vfpBaseVoltage;
    }

    public int getBaseMultiplier() {
        return GTEConfigHolder.gteFlag.vfpBaseProductionRate;
    }

    public int getOverclockMultiplier() {
        int tierDiff = Math.max(0, getEnergyTier() - getBaseTier());
        return (tierDiff + 1) * (tierDiff + 1);
    }

    public int getRigMultiplier() {
        return Math.max(1, getBaseMultiplier() * getOverclockMultiplier());
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(),
                this.minerLogic.isActive(), this.minerLogic.isWorkingEnabled());
    }

    @Override
    public boolean isWorkingEnabled() {
        return this.minerLogic.isWorkingEnabled();
    }

    @Override
    public void setWorkingEnabled(boolean isActivationAllowed) {
        this.minerLogic.setWorkingEnabled(isActivationAllowed);
    }

    public boolean fillTanks(FluidStack stack, boolean simulate) {
        return GTTransferUtils.addFluidsToFluidHandler(outputFluidInventory, simulate,
                Collections.singletonList(stack));
    }

    public int getEnergyTier() {
        if (energyContainer == null) return getBaseTier();
        return Math.max(getBaseTier(), GTUtility.getFloorTierByVoltage(energyContainer.getInputVoltage()));
    }

    public long getEnergyInputPerSecond() {
        return energyContainer.getInputPerSec();
    }

    public boolean drainEnergy(boolean simulate) {
        long energyToDrain = GTValues.VA[getEnergyTier()];
        long resultEnergy = energyContainer.getEnergyStored() - energyToDrain;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate)
                energyContainer.changeEnergy(-energyToDrain);
            return true;
        }
        return false;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        return this.minerLogic.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.minerLogic.readFromNBT(data);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        this.minerLogic.writeInitialSyncData(buf);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.minerLogic.receiveInitialSyncData(buf);
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        this.minerLogic.receiveCustomData(dataId, buf);
    }

    @Override
    public int getProgress() {
        return minerLogic.getProgressTime();
    }

    @Override
    public int getMaxProgress() {
        return FluidDrillLogic.MAX_PROGRESS;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_WORKABLE)
            return GregtechTileCapabilities.CAPABILITY_WORKABLE.cast(this);
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE)
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        return super.getCapability(capability, side);
    }

    @Override
    public boolean showProgressBar() {
        return true;
    }

    @Override
    public double getFillPercentage(int index) {
        int numOperationsLeft = BedrockFluidVeinHandler.getOperationsRemaining(getWorld(), minerLogic.getChunkX(),
                minerLogic.getChunkZ());
        int maxOperations = BedrockFluidVeinHandler.MAXIMUM_VEIN_OPERATIONS;
        return 1.0 * numOperationsLeft / maxOperations;
    }

    @Override
    public TextureArea getProgressBarTexture(int index) {
        return GuiTextures.PROGRESS_BAR_FLUID_RIG_DEPLETION;
    }

    @Override
    public void addBarHoverText(List<ITextComponent> hoverList, int index) {
        int numOperationsLeft = BedrockFluidVeinHandler.getOperationsRemaining(getWorld(), minerLogic.getChunkX(),
                minerLogic.getChunkZ());
        int maxOperations = BedrockFluidVeinHandler.MAXIMUM_VEIN_OPERATIONS;
        int percentage = (int) Math.round(1.0 * numOperationsLeft / maxOperations * 100);
        TextFormatting color = percentage > 40 ? TextFormatting.GREEN :
                percentage > 10 ? TextFormatting.YELLOW : TextFormatting.RED;

        if (numOperationsLeft == 0) {
            hoverList.add(TextComponentUtil.translationWithColor(TextFormatting.RED,
                    "gregtech.multiblock.fluid_rig.vein_depleted"));
        } else {
            ITextComponent veinInfo = TextComponentUtil.stringWithColor(color, percentage + "%");
            hoverList.add(TextComponentUtil.translationWithColor(
                    TextFormatting.GRAY,
                    "gregtech.multiblock.fluid_rig.vein_depletion",
                    veinInfo));
        }
    }
}
