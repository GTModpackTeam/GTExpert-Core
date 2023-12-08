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
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.core.sound.GTSoundEvents;

import gtexpert.api.capability.MultiblockRecipeLogicNoCache;
import gtexpert.api.gui.GTEGuiTextures;
import gtexpert.api.recipes.draconic.GTEDraconicRecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;

public abstract class MetaTileEntityDraconiumFusion extends RecipeMapMultiblockController {

    public MetaTileEntityDraconiumFusion(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap);
        this.recipeMapWorkable = new MultiblockRecipeLogicNoCache(this);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        TraceabilityPredicate casing = states(getCasingState()).setMinGlobalLimited(15);
        TraceabilityPredicate abilities = autoAbilities(true, true, true, true, true, true, true);
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "X#X", "XXX")
                .aisle("XXX", "XSX", "XXX")
                .where('S', selfPredicate())
                .where('X', casing.or(abilities))
                .where('#', air())
                .build();
    }

    protected abstract GTEBlockMetalCasing.MetalCasingType getCasingType();

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    protected IBlockState getCasingState() {
        return GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getState(getCasingType());
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.draconium_fusion.tooltip.1"));
        tooltip.add(I18n.format("gtexpert.machine.draconium_fusion.tooltip.2"));
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

    public static class TierDraconic extends MetaTileEntityDraconiumFusion {

        public TierDraconic(ResourceLocation metaTileEntityId) {
            super(metaTileEntityId, GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES);
        }

        @Override
        protected GTEBlockMetalCasing.MetalCasingType getCasingType() {
            return GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING;
        }

        @SideOnly(Side.CLIENT)
        @Override
        public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
            return GTETextures.DRACONIUM_CASING;
        }

        @Override
        public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
            return new TierDraconic(metaTileEntityId);
        }
    }

    public static class TierAwakened extends MetaTileEntityDraconiumFusion {

        public TierAwakened(ResourceLocation metaTileEntityId) {
            super(metaTileEntityId, GTEDraconicRecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES);
        }

        @Override
        protected GTEBlockMetalCasing.MetalCasingType getCasingType() {
            return GTEBlockMetalCasing.MetalCasingType.AWAKENED_DRACONIUM_CASING;
        }

        @SideOnly(Side.CLIENT)
        @Override
        public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
            return GTETextures.AWAKENED_DRACONIUM_CASING;
        }

        @Override
        public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
            return new TierAwakened(metaTileEntityId);
        }
    }
}
