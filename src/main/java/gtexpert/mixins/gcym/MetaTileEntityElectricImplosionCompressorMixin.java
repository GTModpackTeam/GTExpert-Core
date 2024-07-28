package gtexpert.mixins.gcym;

import gregicality.multiblocks.common.metatileentities.multiblock.standard.MetaTileEntityElectricImplosionCompressor;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;

import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;

import gregtech.api.recipes.builders.ImplosionRecipeBuilder;

import gregtech.api.recipes.builders.SimpleRecipeBuilder;

import gtexpert.api.recipes.GTERecipeMaps;

import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MetaTileEntityElectricImplosionCompressor.class, remap = false)
public class MetaTileEntityElectricImplosionCompressorMixin {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE",
                                           target = "Lgregtech/api/metatileentity/multiblock/RecipeMapMultiblockController;<init>(Lnet/minecraft/util/ResourceLocation;Lgregtech/api/recipes/RecipeMap;)V"), index = 1)
    private static RecipeMap<SimpleRecipeBuilder> injectArg(RecipeMap<SimpleRecipeBuilder> a) {
        return GTERecipeMaps.ELECTRIC_IMPLOSION_COMPRESSOR_RECIPES;
    }
}
