package gtexpert.common.metatileentities;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.Recipe;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityGreenhouse extends RecipeMapMultiblockController {

    public MetaTileEntityGreenhouse(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTERecipeMaps.GREENHOUSE_RECIPES);
        this.recipeMapWorkable = new GreenhouseRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityGreenhouse(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle(" CCC ", " CCC ", " CCC ", " CCC ")
                .aisle("CCCCC", "CDDDC", "C###C", "CGGGC")
                .aisle("CCCCC", "CDDDC", "C###C", "CGGGC")
                .aisle("CCCCC", "CDDDC", "C###C", "CGGGC")
                .aisle(" CCC ", " CCC ", " CCC ", " CSC ")
                .where('S', selfPredicate())
                .where('C', states(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getState(GTEBlockMetalCasing.MetalCasingType.GREENHOUSE)).setMinGlobalLimited(50)
                        .or(autoAbilities(true, true, true, true, true, false, false)))
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS)))
                .where('D', blocks(Blocks.DIRT))
                .where('#', air())
                .where(' ', any())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTETextures.GREENHOUSE_CASING;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
    }

    protected boolean checkCanSeeSun() {
        BlockPos blockPos = getPos().up();
        if (!getWorld().canBlockSeeSky(blockPos))
            return false;
        return true;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    private class GreenhouseRecipeLogic extends MultiblockRecipeLogic {

        public GreenhouseRecipeLogic(MetaTileEntityGreenhouse tileEntity) {
            super(tileEntity);
        }

        @Override
        protected boolean checkRecipe(@Nonnull Recipe recipe) {

            if (!checkCanSeeSun())
                return false;

            return true;
        }
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (!checkCanSeeSun()) {
            textList.add(new TextComponentTranslation("gtexpert.multiblock.needsun")
                    .setStyle(new Style().setColor(TextFormatting.RED)));
        }
    }
}
