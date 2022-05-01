package gtexpert.common.metatileentities;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.MetaBlocks;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEMetalCasing;
import gtexpert.common.ModBlocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityVoidOreMiner extends RecipeMapMultiblockController {

    public MetaTileEntityVoidOreMiner(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTERecipeMaps.VOID_ORE_MINER_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityVoidOreMiner(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", " FFF ", " FFF ", " FFF ", "     ", "     ", "     ", "     ", "     ", "     ")
                .aisle("CCCCC", "FCCCF", "FCCCF", "FCCCF", " FFF ", "  F  ", "  F  ", "     ", "     ", "     ")
                .aisle("CCCCC", "FCCCF", "FCCCF", "FCCCF", " FCF ", " FCF ", " FCF ", "  F  ", "  F  ", "  F  ")
                .aisle("CCCCC", "FCCCF", "FCCCF", "FCCCF", " FFF ", "  F  ", "  F  ", "     ", "     ", "     ")
                .aisle("CCSCC", " FFF ", " FFF ", " FFF ", "     ", "     ", "     ", "     ", "     ", "     ")
                .where('S', selfPredicate())
                .where('C', states(ModBlocks.gteMetalCasing.getState(GTEMetalCasing.MetalCasingType.VOID_ORE_MINER)).setMinGlobalLimited(45)
                        .or(autoAbilities(true, true, true, true, true, false, false)))
                .where('F', states(MetaBlocks.FRAMES.get(Materials.HSSE).getBlock(Materials.HSSE)))
                .where(' ', any())
                .build();
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ITEM_VOIDING_ADVANCED;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTETextures.VOID_ORE_MINER_CASING;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.1"));
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.2"));
        tooltip.add(I18n.format("gtexpert.machine.void_ore_miner.tooltip.3"));
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }
}
