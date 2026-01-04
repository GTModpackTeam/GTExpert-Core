package com.github.gtexpert.core.common.metatileentities.multi;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.IProgressBarMultiblock;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;

import com.github.gtexpert.core.api.gui.GTEGuiTextures;
import com.github.gtexpert.core.api.recipes.GTERecipeMaps;
import com.github.gtexpert.core.api.unification.material.GTEMaterials;
import com.github.gtexpert.core.client.GTETextures;
import com.github.gtexpert.core.common.blocks.GTEBlockMetalCasing;
import com.github.gtexpert.core.common.blocks.GTEMetaBlocks;

public class MetaTileEntityVoidOreMiner extends RecipeMapMultiblockController
                                        implements IProgressBarMultiblock {

    public MetaTileEntityVoidOreMiner(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTERecipeMaps.VOID_ORE_MINER_RECIPES);
    }

    @Override
    public @NotNull MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityVoidOreMiner(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        TraceabilityPredicate frame = states(getFrameState());
        TraceabilityPredicate casing = states(getCasingState());
        TraceabilityPredicate abilities = autoAbilities(true, true, true, true, true, false, false);
        return FactoryBlockPattern.start()
                .aisle("XXXXX", " FFF ", " FFF ", " FFF ", "     ", "     ", "     ", "     ", "     ", "     ")
                .aisle("XXXXX", "FCCCF", "FCCCF", "FCCCF", " FFF ", "  F  ", "  F  ", "     ", "     ", "     ")
                .aisle("XXXXX", "FCCCF", "FCCCF", "FCCCF", " FCF ", " FCF ", " FCF ", "  F  ", "  F  ", "  F  ")
                .aisle("XXXXX", "FCCCF", "FCCCF", "FCCCF", " FFF ", "  F  ", "  F  ", "     ", "     ", "     ")
                .aisle("XXSXX", " FFF ", " FFF ", " FFF ", "     ", "     ", "     ", "     ", "     ", "     ")
                .where('S', selfPredicate())
                .where('X', casing.setMinGlobalLimited(17).or(abilities))
                .where('C', casing.setMinGlobalLimited(30))
                .where('F', frame.setMinGlobalLimited(55))
                .where(' ', any())
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

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(GTEMaterials.NM_HEA_NPs).getBlock(GTEMaterials.NM_HEA_NPs);
    }

    protected IBlockState getCasingState() {
        return GTEMetaBlocks.GTE_METAL_CASING.getState(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER);
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.1"));
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.2"));
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.3"));
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.4"));
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
        return Textures.ITEM_VOIDING_ADVANCED;
    }

    // IProgressBarMultiblock implementation
    @Override
    public boolean showProgressBar() {
        return true;
    }

    @Override
    public int getNumProgressBars() {
        return 2;
    }

    @Override
    public double getFillPercentage(int index) {
        if (!isStructureFormed() || getInputFluidInventory() == null) {
            return 0;
        }

        if (index == 0) {
            // EnderPearl fluid
            int[] amount = getTotalFluidAmount(Materials.EnderPearl.getFluid(Integer.MAX_VALUE),
                    getInputFluidInventory());
            return amount[1] != 0 ? 1.0 * amount[0] / amount[1] : 0;
        } else {
            // Drilling Fluid
            int[] amount = getTotalFluidAmount(Materials.DrillingFluid.getFluid(Integer.MAX_VALUE),
                    getInputFluidInventory());
            return amount[1] != 0 ? 1.0 * amount[0] / amount[1] : 0;
        }
    }

    @Override
    public TextureArea getProgressBarTexture(int index) {
        if (index == 0) {
            return GTEGuiTextures.PROGRESS_BAR_VOID_ENDER;
        } else {
            return GTEGuiTextures.PROGRESS_BAR_VOID_FUEL;
        }
    }

    @Override
    public void addBarHoverText(List<ITextComponent> hoverList, int index) {
        if (!isStructureFormed() || getInputFluidInventory() == null) {
            return;
        }

        if (index == 0) {
            // EnderPearl fluid
            int[] amount = getTotalFluidAmount(Materials.EnderPearl.getFluid(Integer.MAX_VALUE),
                    getInputFluidInventory());
            ITextComponent fluidInfo = TextComponentUtil.stringWithColor(
                    TextFormatting.GREEN,
                    TextFormattingUtil.formatNumbers(amount[0]) + " / " +
                            TextFormattingUtil.formatNumbers(amount[1]) + " L");
            hoverList.add(TextComponentUtil.translationWithColor(
                    TextFormatting.GRAY,
                    "gtexpert.multiblock.void_ore_miner.ender_pearl_amount",
                    fluidInfo));
        } else {
            // Drilling Fluid
            int[] amount = getTotalFluidAmount(Materials.DrillingFluid.getFluid(Integer.MAX_VALUE),
                    getInputFluidInventory());
            ITextComponent fluidInfo = TextComponentUtil.stringWithColor(
                    TextFormatting.GOLD,
                    TextFormattingUtil.formatNumbers(amount[0]) + " / " +
                            TextFormattingUtil.formatNumbers(amount[1]) + " L");
            hoverList.add(TextComponentUtil.translationWithColor(
                    TextFormatting.GRAY,
                    "gtexpert.multiblock.void_ore_miner.drilling_fluid_amount",
                    fluidInfo));
        }
    }

    private int[] getTotalFluidAmount(FluidStack testStack, IMultipleTankHandler tanks) {
        int stored = 0;
        int capacity = 0;
        for (var tank : tanks.getFluidTanks()) {
            FluidStack contained = tank.getFluid();
            if (contained != null && contained.isFluidEqual(testStack)) {
                stored += contained.amount;
                capacity += tank.getCapacity();
            } else if (contained == null) {
                // Empty tank that could hold this fluid
                capacity += tank.getCapacity();
            }
        }
        return new int[] { stored, capacity };
    }
}
