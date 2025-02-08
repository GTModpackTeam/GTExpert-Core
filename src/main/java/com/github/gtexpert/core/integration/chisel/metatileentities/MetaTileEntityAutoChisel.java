package com.github.gtexpert.core.integration.chisel.metatileentities;

import java.util.function.Function;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;

import com.github.gtexpert.core.api.capability.SingleblockRecipeLogicNoCache;
import com.github.gtexpert.core.api.gui.GTEGuiTextures;

public class MetaTileEntityAutoChisel extends SimpleMachineMetaTileEntity {

    public MetaTileEntityAutoChisel(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer,
                                    int tier, boolean hasFrontFacing, Function<Integer, Integer> tankScalingFunction) {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction);
        new SingleblockRecipeLogicNoCache(this, recipeMap, () -> this.energyContainer);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityAutoChisel(metaTileEntityId, workable.getRecipeMap(), renderer, getTier(),
                hasFrontFacing(), getTankScalingFunction());
    }

    protected @NotNull TextureArea getLogo() {
        return GTEGuiTextures.GTE_LOGO;
    }

    protected @NotNull TextureArea getXmasLogo() {
        return GTEGuiTextures.GTE_LOGO_XMAS;
    }
}
