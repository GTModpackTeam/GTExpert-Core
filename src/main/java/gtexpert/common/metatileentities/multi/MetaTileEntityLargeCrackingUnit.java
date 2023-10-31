package gtexpert.common.metatileentities.multi;

import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import gtexpert.api.gui.GTEGuiTextures;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MetaTileEntityLargeCrackingUnit extends GCYMRecipeMapMultiblockController {

    private int coilTier;

    public MetaTileEntityLargeCrackingUnit(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.CRACKING_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityLargeCrackingUnit(this.metaTileEntityId);
    }

    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XCCXCCX", "XCCXCCX", "XCCXCCX")
                .aisle("XCCXCCX", "X##T##X", "XCCXCCX")
                .aisle("XCCXCCX", "XCCSCCX", "XCCXCCX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(10)
                        .or(autoAbilities(true, true, true, true, true, true, false)))
                .where('T', tieredCasing().or(states(getCasingState())))
                .where('#', air())
                .where('C', heatingCoils())
                .build();
        // return FactoryBlockPattern.start()
        // .aisle(" XXX ", " XXX ", " X ", " X ", " X ", " XXX ", " ")
        // .aisle("XXXXX", "XXXXX", " CCC ", " CCC ", " CCC ", "XXXXX", " XXX ")
        // .aisle("XXTXX", "XXXXX", "XC#CX", "XC#CX", "XC#CX", "XXXXX", " XHX ")
        // .aisle("XXXXX", "XXXXX", " CCC ", " CCC ", " CCC ", "XXXXX", " XXX ")
        // .aisle(" XSX ", " XXX ", " X ", " X ", " X ", " XXX ", " ")
        // .where('S', selfPredicate())
        // .where('X', states(getCasingState()).setMinGlobalLimited(10)
        // .or(autoAbilities(true, true, true, true, true, true, false)))
        // .where('T', tieredCasing().or(states(getCasingState())))
        // .where('H', abilities(MultiblockAbility.MUFFLER_HATCH))
        // .where('C', heatingCoils())
        // .where('#', air())
        // .build();
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.cracking_unit.energy",
                    100 - 10 * this.coilTier));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.machine.cracker.tooltip.1"));
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.CRACKING_UNIT_OVERLAY;
    }

    @Override
    protected @Nonnull TextureArea getLogo() {
        return GTEGuiTextures.GTE_LOGO_DARK;
    }

    @Override
    protected @Nonnull TextureArea getWarningLogo() {
        return GTEGuiTextures.GTE_LOGO_BLINKING_YELLOW;
    }

    @Override
    protected @Nonnull TextureArea getErrorLogo() {
        return GTEGuiTextures.GTE_LOGO_BLINKING_RED;
    }

    @Override
    public boolean isTiered() {
        return true;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("CoilType");
        if (type instanceof IHeatingCoilBlockStats) {
            this.coilTier = ((IHeatingCoilBlockStats) type).getTier();
        } else {
            this.coilTier = 0;
        }
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.coilTier = -1;
    }

    protected int getCoilTier() {
        return this.coilTier;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class LargeCrackingUnitWorkableHandler extends GCYMMultiblockRecipeLogic {

        public LargeCrackingUnitWorkableHandler(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
        }

        protected void performNonOverclockBonuses(int[] resultOverclock) {
            int coilTier = ((MetaTileEntityLargeCrackingUnit) metaTileEntity).getCoilTier();
            if (coilTier > 0) {
                resultOverclock[0] = (int) ((double) resultOverclock[0] * (1.0 - (double) coilTier * 0.1));
                resultOverclock[0] = Math.max(1, resultOverclock[0]);
            }
        }
    }
}
