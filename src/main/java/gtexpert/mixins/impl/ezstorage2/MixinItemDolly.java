package gtexpert.mixins.impl.ezstorage2;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import com.zerofall.ezstorage.item.EZItem;
import com.zerofall.ezstorage.item.ItemDolly;
import com.zerofall.ezstorage.tileentity.TileEntityStorageCore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

@Mixin(ItemDolly.class)
public abstract class MixinItemDolly extends EZItem {

    public MixinItemDolly(String name) {
        super(name);
    }

    @Inject(method = "onItemUse", at = @At("HEAD"), remap = false, cancellable = true)
    public void injectOnItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
                                EnumFacing facing, float hitX, float hitY, float hitZ,
                                CallbackInfoReturnable<EnumActionResult> cir) {
        cir.setReturnValue(EnumActionResult.PASS);
        cir.cancel();
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World world, @NotNull EntityPlayer player,
                                                             @NotNull EnumHand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (world.isRemote) {
            return new ActionResult<>(EnumActionResult.PASS, heldItem);
        }

        NBTTagCompound nbt = heldItem.getTagCompound();
        RayTraceResult raytraceresult = this.rayTrace(world, player, false);
        BlockPos pos = raytraceresult.getBlockPos();
        if (nbt != null && nbt.getBoolean("isFull")) {
            BlockPos placePos = pos.offset(raytraceresult.sideHit);
            Block block = Block.getBlockFromName(nbt.getString("blockType"));
            if (block == null || !world.getBlockState(placePos).getBlock().isReplaceable(world, placePos) ||
                    !block.canPlaceBlockAt(world, placePos)) {
                return new ActionResult<>(EnumActionResult.FAIL, heldItem);
            }

            IBlockState state = block.getDefaultState();
            if (block.equals(Blocks.CHEST)) {
                state = state.withProperty(BlockChest.FACING, player.getHorizontalFacing().getOpposite());
            }

            world.setBlockState(placePos, state);
            TileEntity targetTileEntity = world.getTileEntity(placePos);
            if (targetTileEntity != null) {
                NBTTagCompound stored = nbt.getCompoundTag("stored");
                stored.setInteger("x", placePos.getX());
                stored.setInteger("y", placePos.getY());
                stored.setInteger("z", placePos.getZ());
                targetTileEntity.readFromNBT(stored);
            } else {
                world.setBlockToAir(placePos);
                return new ActionResult<>(EnumActionResult.FAIL, heldItem);
            }

            if (player.isCreative()) {
                nbt.setBoolean("isFull", false);
                nbt.removeTag("blockType");
                nbt.removeTag("isChest");
                nbt.removeTag("isStorageCore");
                nbt.removeTag("stored");
            } else {
                ItemStack emptyDolly = heldItem.splitStack(1);
                NBTTagCompound emptyDollyNBT = emptyDolly.getTagCompound();
                if (emptyDollyNBT != null) {
                    emptyDollyNBT.setBoolean("isFull", false);
                    emptyDollyNBT.removeTag("blockType");
                    emptyDollyNBT.removeTag("isChest");
                    emptyDollyNBT.removeTag("isStorageCore");
                    emptyDollyNBT.removeTag("stored");
                }
                emptyDolly.damageItem(1, player);

                if (heldItem.isEmpty()) {
                    return new ActionResult<>(EnumActionResult.SUCCESS, emptyDolly);
                }

                if (!player.addItemStackToInventory(emptyDolly)) {
                    player.dropItem(emptyDolly, false);
                }
            }
        } else {
            if (!world.isBlockModifiable(player, pos)) {
                return new ActionResult<>(EnumActionResult.FAIL, heldItem);
            }

            if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
                return new ActionResult<>(EnumActionResult.PASS, heldItem);
            }

            IBlockState state = world.getBlockState(pos);
            TileEntity tileEntity = world.getTileEntity(pos);
            boolean isChest = tileEntity instanceof TileEntityChest;
            boolean isStorageCore = tileEntity instanceof TileEntityStorageCore;
            if (!(isChest || isStorageCore)) {
                return new ActionResult<>(EnumActionResult.PASS, heldItem);
            }

            NBTTagCompound distTag = new NBTTagCompound();
            NBTTagCompound storageData = tileEntity.writeToNBT(new NBTTagCompound());
            distTag.setBoolean("isFull", true);
            distTag.setString("blockType", Objects.requireNonNull(state.getBlock().getRegistryName()).toString());
            distTag.setBoolean("isChest", isChest);
            distTag.setBoolean("isStorageCore", isStorageCore);
            distTag.setTag("stored", storageData);

            if (isStorageCore) {
                world.setBlockToAir(pos);
                world.removeTileEntity(pos);
            } else {
                world.removeTileEntity(pos);
                world.setBlockToAir(pos);
            }

            if (player.isCreative()) {
                heldItem.setTagCompound(distTag);
            } else {
                ItemStack distItem = heldItem.splitStack(1);
                distItem.setTagCompound(distTag);

                if (heldItem.isEmpty()) {
                    return new ActionResult<>(EnumActionResult.SUCCESS, distItem);
                }

                if (!player.addItemStackToInventory(distItem)) {
                    player.dropItem(distItem, false);
                }
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null && nbt.getBoolean("isFull"))
            return 1;
        return super.getItemStackLimit(stack);
    }
}
