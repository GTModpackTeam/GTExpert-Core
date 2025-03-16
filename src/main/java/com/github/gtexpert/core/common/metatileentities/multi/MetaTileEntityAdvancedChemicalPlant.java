package com.github.gtexpert.core.common.metatileentities.multi;

import static gregtech.api.recipes.logic.OverclockingLogic.*;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;

import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;

import com.github.gtexpert.core.api.gui.GTEGuiTextures;

public class MetaTileEntityAdvancedChemicalPlant extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityAdvancedChemicalPlant(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.LARGE_CHEMICAL_RECIPES);
        this.recipeMapWorkable = new AdvancedChemicalPlantWorkableHandler(this);
    }

    @Override
    public @NotNull MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityAdvancedChemicalPlant(metaTileEntityId);
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern() {
        TraceabilityPredicate casing = states(getCasingState()).setMinGlobalLimited(22);
        TraceabilityPredicate abilities = autoAbilities(true, true, true, true, true, true, false);
        return FactoryBlockPattern.start()
                .aisle("X   X", "XXXXX", "X   X", "XXXXX", "X   X")
                .aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
                .aisle("X   X", "XPPPX", "XPTPX", "XPPPX", "X   X")
                .aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
                .aisle("X   X", "SXXXX", "X   X", "XXXXX", "X   X")
                .where('S', selfPredicate())
                .where('X', casing.or(abilities))
                .where('T', tieredCasing().or(casing))
                .where('P', states(getPipeCasingState()))
                .where('C', heatingCoils().setMinGlobalLimited(12).setMaxGlobalLimited(12))
                .where(' ', any())
                .build();
    }

    @Override
    public boolean isTiered() {
        return true;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public boolean isParallel() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.INERT_PTFE_CASING;
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING);
    }

    protected IBlockState getPipeCasingState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE);
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.advanced_chemical_plant.tooltip.1"));
        tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
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
        return Textures.CHEMICAL_REACTOR_OVERLAY;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class AdvancedChemicalPlantWorkableHandler extends GCYMMultiblockRecipeLogic {

        public AdvancedChemicalPlantWorkableHandler(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        protected int[] runOverclockingLogic(@NotNull IRecipePropertyStorage propertyStorage, int recipeEUt,
                                             long maxVoltage, int duration, int amountOC) {
            return standardOverclockingLogic(
                    Math.abs(recipeEUt),
                    maxVoltage,
                    duration,
                    amountOC,
                    getOverclockingDurationDivisor(),
                    getOverclockingVoltageMultiplier());
        }

        protected double getOverclockingDurationDivisor() {
            return PERFECT_OVERCLOCK_DURATION_DIVISOR;
        }

        protected double getOverclockingVoltageMultiplier() {
            return STANDARD_OVERCLOCK_VOLTAGE_MULTIPLIER;
        }
    }
}
