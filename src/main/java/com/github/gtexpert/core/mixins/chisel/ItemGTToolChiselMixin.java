package com.github.gtexpert.core.mixins.chisel;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;

import gregtech.api.items.toolitem.ItemGTTool;
import gregtech.api.items.toolitem.ToolHelper;

import team.chisel.api.IChiselGuiType;
import team.chisel.api.IChiselGuiType.ChiselGuiType;
import team.chisel.api.IChiselItem;
import team.chisel.api.carving.ICarvingVariation;
import team.chisel.api.carving.IChiselMode;
import team.chisel.common.item.ChiselMode;

/**
 * Mixin to make GregTech tools with "chisel" tool class implement Chisel mod's IChiselItem interface.
 * This allows GT chisel tools to work with the Chisel mod's chiseling functionality.
 */
@Mixin(value = ItemGTTool.class, remap = false)
public abstract class ItemGTToolChiselMixin implements IChiselItem {

    /**
     * Check if this tool has the "chisel" tool class
     */
    private boolean gtexpert$isChiselTool() {
        ItemGTTool tool = (ItemGTTool) (Object) this;
        return tool.getToolClasses(ItemStack.EMPTY).contains("chisel");
    }

    @Override
    public boolean canOpenGui(World world, EntityPlayer player, EnumHand hand) {
        return gtexpert$isChiselTool();
    }

    @Override
    public IChiselGuiType getGuiType(World world, EntityPlayer player, EnumHand hand) {
        return ChiselGuiType.NORMAL;
    }

    @Override
    public boolean onChisel(World world, EntityPlayer player, ItemStack chisel, ICarvingVariation target) {
        return gtexpert$isChiselTool();
    }

    @Override
    public boolean canChisel(World world, EntityPlayer player, ItemStack chisel, ICarvingVariation target) {
        return gtexpert$isChiselTool() && !chisel.isEmpty();
    }

    @Override
    public boolean canChiselBlock(World world, EntityPlayer player, EnumHand hand, BlockPos pos, IBlockState state) {
        return gtexpert$isChiselTool();
    }

    @Override
    public boolean supportsMode(EntityPlayer player, ItemStack chisel, IChiselMode mode) {
        if (!gtexpert$isChiselTool()) return false;
        // Support basic modes, exclude advanced contiguous modes
        return mode != ChiselMode.CONTIGUOUS && mode != ChiselMode.CONTIGUOUS_2D;
    }

    @Override
    public ItemStack craftItem(ItemStack chisel, ItemStack source, ItemStack target, EntityPlayer player) {
        if (!gtexpert$isChiselTool() || chisel.isEmpty()) return ItemStack.EMPTY;

        int toCraft = Math.min(source.getCount(), target.getMaxStackSize());

        // Check remaining durability
        int durabilityLeft = chisel.getMaxDamage() - chisel.getItemDamage() + 1;
        toCraft = Math.min(toCraft, durabilityLeft);

        // Damage the tool using GT's damage system
        ToolHelper.damageItem(chisel, player, toCraft);

        ItemStack result = target.copy();
        source.shrink(toCraft);
        result.setCount(toCraft);
        return result;
    }
}
