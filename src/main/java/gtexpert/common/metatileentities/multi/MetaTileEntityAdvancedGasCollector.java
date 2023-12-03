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
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.core.sound.GTSoundEvents;

import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;

import gtexpert.api.gui.GTEGuiTextures;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;

public class MetaTileEntityAdvancedGasCollector extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityAdvancedGasCollector(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTERecipeMaps.ADVANCED_GAS_COLLECTOR_RECIPES);
    }

    @Override
    public @NotNull MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityAdvancedGasCollector(metaTileEntityId);
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
                .where('T', tieredCasing().or(states(getCasingState())))
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

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTETextures.VOID_ORE_MINER_CASING;
    }

    protected IBlockState getCasingState() {
        return GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getState(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER);
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.advanced_chemical_plant.tooltip.1"));
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
