package gtexpert.common.metatileentities.multi;

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
import gregtech.client.renderer.ICubeRenderer;
import gregtech.core.sound.GTSoundEvents;

import gtexpert.api.gui.GTEGuiTextures;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.blocks.GTEBlockMetalCasing;
import gtexpert.common.blocks.GTEMetaBlocks;

public class MetaTileEntitySawmill extends RecipeMapMultiblockController {

    public MetaTileEntitySawmill(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTERecipeMaps.SAWMILL_RECIPES);
    }

    @Override
    public @NotNull MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySawmill(metaTileEntityId);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        TraceabilityPredicate casing = states(getCasingState()).setMinGlobalLimited(14);
        TraceabilityPredicate abilities = autoAbilities(true, false, true, true, true, false, false);
        return FactoryBlockPattern.start()
                .aisle("XCX", "X#X", "X X")
                .aisle(" C ", " # ", "XXX")
                .aisle(" C ", " # ", "X X")
                .aisle(" C ", " # ", "XXX")
                .aisle("XCX", "S#X", "X X")
                .where('S', selfPredicate())
                .where('X', casing.or(abilities))
                .where('C', blocks(GTEMetaBlocks.BLOCK_SAWMILL_CONVEYOR))
                .where('#', air())
                .where(' ', any())
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
    public boolean hasMaintenanceMechanics() {
        return false;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTETextures.SAWMILL_CASING;
    }

    protected IBlockState getCasingState() {
        return GTEMetaBlocks.GTE_METAL_CASING.getState(GTEBlockMetalCasing.MetalCasingType.SAWMill);
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.sawmill.tooltip.1"));
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
        return GTETextures.SAWMILL_OVERLAY;
    }
}
