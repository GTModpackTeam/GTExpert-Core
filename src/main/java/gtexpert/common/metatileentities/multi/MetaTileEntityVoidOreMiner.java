package gtexpert.common.metatileentities.multi;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.MetaBlocks;

import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MetaTileEntityVoidOreMiner extends RecipeMapMultiblockController {

    public MetaTileEntityVoidOreMiner(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTERecipeMaps.VOID_ORE_MINER_RECIPES);
    }

    @Override
    public @Nonnull MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityVoidOreMiner(metaTileEntityId);
    }

    @Override
    protected @Nonnull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", " FFF ", " FFF ", " FFF ", "     ", "     ", "     ", "     ", "     ", "     ")
                .aisle("XXXXX", "FCCCF", "FCCCF", "FCCCF", " FFF ", "  F  ", "  F  ", "     ", "     ", "     ")
                .aisle("XXXXX", "FCCCF", "FCCCF", "FCCCF", " FCF ", " FCF ", " FCF ", "  F  ", "  F  ", "  F  ")
                .aisle("XXXXX", "FCCCF", "FCCCF", "FCCCF", " FFF ", "  F  ", "  F  ", "     ", "     ", "     ")
                .aisle("XXSXX", " FFF ", " FFF ", " FFF ", "     ", "     ", "     ", "     ", "     ", "     ")
                .where('S', selfPredicate())
                .where('X',
                        states(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                                .getState(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER)).setMinGlobalLimited(17)
                                        .or(autoAbilities(true, true, true, true, true, false, false)))
                .where('C',
                        states(GTEMetaBlocks.GTE_BLOCK_METAL_CASING
                                .getState(GTEBlockMetalCasing.MetalCasingType.VOID_ORE_MINER)).setMinGlobalLimited(30))
                .where('F',
                        states(MetaBlocks.FRAMES.get(GTEMaterials.NM_HEA_NPs).getBlock(GTEMaterials.NM_HEA_NPs))
                                .setMinGlobalLimited(55))
                .where(' ', any())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTETextures.VOID_ORE_MINER_CASING;
    }

    @Override
    protected @Nonnull ICubeRenderer getFrontOverlay() {
        return Textures.ITEM_VOIDING_ADVANCED;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @Nonnull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.1"));
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.2"));
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.3"));
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.4"));
    }

    @Override
    protected @Nonnull TextureArea getLogo() {
        // return GTEGuiTextures.GTE_LOGO_DARK;
        return GuiTextures.GREGTECH_LOGO_DARK;
    }

    @Override
    protected @Nonnull TextureArea getWarningLogo() {
        // return GTEGuiTextures.GTE_LOGO_BLINKING_YELLOW;
        return GuiTextures.GREGTECH_LOGO_BLINKING_YELLOW;
    }

    @Override
    protected @Nonnull TextureArea getErrorLogo() {
        // return GTEGuiTextures.GTE_LOGO_BLINKING_RED;
        return GuiTextures.GREGTECH_LOGO_BLINKING_RED;
    }
}
