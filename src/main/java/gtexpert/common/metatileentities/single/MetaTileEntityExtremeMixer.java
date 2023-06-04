package gtexpert.common.metatileentities.single;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import java.util.function.Function;

public class MetaTileEntityExtremeMixer extends SimpleMachineMetaTileEntity {
    public MetaTileEntityExtremeMixer(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer,
                                      int tier, boolean hasFrontFacing, Function<Integer, Integer> tankScalingFunction) {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityExtremeMixer(this.metaTileEntityId, this.workable.getRecipeMap(), this.renderer,
                this.getTier(), this.hasFrontFacing(), this.getTankScalingFunction());
    }
}
