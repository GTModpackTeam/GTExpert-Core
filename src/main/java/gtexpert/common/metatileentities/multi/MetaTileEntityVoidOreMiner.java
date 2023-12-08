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
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;

import gtexpert.api.gui.GTEGuiTextures;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;

public class MetaTileEntityVoidOreMiner extends RecipeMapMultiblockController {

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
        return GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getState(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER);
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
}
