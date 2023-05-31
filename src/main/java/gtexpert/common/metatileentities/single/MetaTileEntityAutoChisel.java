package gtexpert.common.metatileentities.single;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class MetaTileEntityAutoChisel extends SimpleMachineMetaTileEntity {
    public MetaTileEntityAutoChisel(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer,
                                    int tier, boolean hasFrontFacing) {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityAutoChisel(this.metaTileEntityId, this.workable.getRecipeMap(), this.renderer,
                this.getTier(), this.hasFrontFacing());
    }

    @Override
    public SoundEvent getSound() { return GTSoundEvents.FIRE; }
}
