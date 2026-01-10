package com.github.gtexpert.core.mixins.chisel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import team.chisel.api.block.VariationData;
import team.chisel.common.block.BlockCarvable;

/**
 * Mixin to add harvest level information to Chisel's carvable blocks.
 * <p>
 * Sets appropriate harvest tools (pickaxe, axe, shovel) based on block material type,
 * enabling proper tool requirements for breaking these blocks.
 */
@Mixin(value = BlockCarvable.class, remap = false)
public abstract class BlockCarvableMixin extends Block {

    protected BlockCarvableMixin(Material materialIn) {
        super(materialIn);
    }

    /**
     * Sets the harvest level for carvable blocks based on their material type.
     * <ul>
     * <li>Pickaxe: Rock, Iron, Anvil, Ice</li>
     * <li>Axe: Wood</li>
     * <li>Shovel: Ground, Sand, Clay</li>
     * </ul>
     */
    @Inject(method = "<init>(Lnet/minecraft/block/material/Material;Lnet/minecraft/util/BlockRenderLayer;II[Lteam/chisel/api/block/VariationData;)V",
            at = @At("RETURN"))
    private void gtexpert$setHarvestLevel(Material material, BlockRenderLayer layer, int index, int max,
                                          VariationData[] variations, CallbackInfo ci) {
        if (material == Material.ROCK || material == Material.IRON || material == Material.ANVIL ||
                material == Material.ICE || material == Material.PACKED_ICE) {
            this.setHarvestLevel("pickaxe", 0);
        } else if (material == Material.WOOD) {
            this.setHarvestLevel("axe", 0);
        } else if (material == Material.GROUND || material == Material.SAND || material == Material.CLAY) {
            this.setHarvestLevel("shovel", 0);
        }
    }
}
