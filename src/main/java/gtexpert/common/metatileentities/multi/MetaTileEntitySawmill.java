package gtexpert.common.metatileentities.multi;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.client.GTETextures;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
        return FactoryBlockPattern.start()
                .aisle("CFC", "C#C", "C C")
                .aisle(" F ", " # ", "CCC")
                .aisle(" F ", " # ", "C C")
                .aisle(" F ", " # ", "CCC")
                .aisle("CFC", "S#C", "C C")
                .where('S', selfPredicate())
                .where('C', states(GTEMetaBlocks.GTE_BLOCK_METAL_CASING.getState(GTEBlockMetalCasing.MetalCasingType.SAWMill)).setMinGlobalLimited(15)
                        .or(autoAbilities(true, false, true, true, true, false, false)))
                .where('F', blocks(GTEMetaBlocks.BLOCK_SAWMILL_CONVEYOR))
                .where('#', air())
                .where(' ', any())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTETextures.SAWMILL_CASING;
    }

    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() { return GTETextures.SAWMILL_OVERLAY; }

    @Override
    public boolean hasMaintenanceMechanics() {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }
}
