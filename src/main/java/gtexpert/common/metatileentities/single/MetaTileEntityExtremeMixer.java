package gtexpert.common.metatileentities.single;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;

import gtexpert.common.metatileentities.GTESimpleMachineMetaTileEntity;

import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class MetaTileEntityExtremeMixer extends GTESimpleMachineMetaTileEntity {

    public MetaTileEntityExtremeMixer(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer,
                                      int tier, boolean hasFrontFacing,
                                      Function<Integer, Integer> tankScalingFunction) {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityExtremeMixer(this.metaTileEntityId, this.workable.getRecipeMap(), this.renderer,
                this.getTier(), this.hasFrontFacing(), this.getTankScalingFunction());
    }
}
