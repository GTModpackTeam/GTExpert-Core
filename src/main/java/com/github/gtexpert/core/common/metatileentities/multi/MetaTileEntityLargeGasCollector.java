package com.github.gtexpert.core.common.metatileentities.multi;

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
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.core.sound.GTSoundEvents;

import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;

import com.github.gtexpert.core.api.gui.GTEGuiTextures;
import com.github.gtexpert.core.api.recipes.GTERecipeMaps;
import com.github.gtexpert.core.client.GTETextures;
import com.github.gtexpert.core.common.GTEConfigHolder;
import com.github.gtexpert.core.common.blocks.GTEBlockMetalCasing;
import com.github.gtexpert.core.common.blocks.GTEMetaBlocks;

public class MetaTileEntityLargeGasCollector extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityLargeGasCollector(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, new RecipeMap[] {
                RecipeMaps.GAS_COLLECTOR_RECIPES,
                GTERecipeMaps.LARGE_GAS_COLLECTOR_RECIPES
        });
    }

    @Override
    public @NotNull MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityLargeGasCollector(metaTileEntityId);
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern() {
        TraceabilityPredicate casing = states(getCasingState()).setMinGlobalLimited(8);
        TraceabilityPredicate abilities = autoAbilities(true, true, true, true, true, true, false);
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XTX", "X#X", "XHX")
                .aisle("XXX", "XSX", "XXX")
                .where('S', selfPredicate())
                .where('X', casing.or(abilities))
                .where('T', tieredCasing().or(casing))
                .where('H', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('#', air())
                .build();
    }

    @Override
    public boolean allowsExtendedFacing() {
        return false;
    }

    @Override
    public boolean allowsFlip() {
        return false;
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
        return GTETextures.VOID_ORE_MINER_CASING;
    }

    protected IBlockState getCasingState() {
        return GTEMetaBlocks.GTE_METAL_CASING.getState(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER);
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format(GTEConfigHolder.gteFlag.featureFlag ?
                "gtexpert.machine.large_gas_collector.tooltip.1" :
                "gtexpert.machine.advanced_gas_collector.tooltip.1"));
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
        return Textures.GAS_COLLECTOR_OVERLAY;
    }
}
