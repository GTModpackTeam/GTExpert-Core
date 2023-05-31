package gtexpert.common.metatileentities.multi;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gtexpert.api.capability.MultiblockRecipeLogicNoCache;
import gtexpert.api.recipes.draconic.GTEDraconicRecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public abstract class MetaTileEntityDraconiumFusion extends RecipeMapMultiblockController {

    public MetaTileEntityDraconiumFusion(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap);
        this.recipeMapWorkable = new MultiblockRecipeLogicNoCache(this);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCC", "CCC", "CCC")
                .aisle("CCC", "C#C", "CCC")
                .aisle("CCC", "CSC", "CCC")
                .where('S', selfPredicate())
                .where('C', states(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getState(getCasingType())).setMinGlobalLimited(15).or(autoAbilities()))
                .where('#', air())
                .build();
    }

    protected abstract GTEBlockMetalCasing.MetalCasingType getCasingType();

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    public static class TierDraconic extends MetaTileEntityDraconiumFusion {

        public TierDraconic(ResourceLocation metaTileEntityId) {
            super(metaTileEntityId, GTEDraconicRecipeMaps.DRACONIUM_FUSION_RECIPES);
        }

        @Override
        protected GTEBlockMetalCasing.MetalCasingType getCasingType() {
            return GTEBlockMetalCasing.MetalCasingType.DRACONIUM_CASING;
        }

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
