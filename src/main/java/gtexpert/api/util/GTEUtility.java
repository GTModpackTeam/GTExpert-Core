package gtexpert.api.util;

import gtexpert.api.GTEValues;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

public class GTEUtility {

    public static @Nonnull ItemStack getModItem(String modID, String itemName) {
        return getModItem(modID, itemName, 1);
    }

    public static @Nonnull ItemStack getModItem(String modID, String itemName, int amount) {
        return getModItem(modID, itemName, amount, 0);
    }

    public static @Nonnull ItemStack getModItem(String modID, String itemName, int amount, int meta) {
        return getModItem(modID, itemName, amount, meta, null);
    }

    public static @Nonnull ItemStack getModItem(String modID, String itemName, int amount, int meta,
                                                NBTTagCompound nbt) {
        return GameRegistry.makeItemStack(String.format("%s:%s", modID, itemName), meta, amount,
                nbt != null ? nbt.toString() : null);
    }

    public static @Nonnull FluidStack getModFluid(String fluidName) {
        return FluidRegistry.getFluidStack(fluidName, 1000);
    }

    public static @Nonnull FluidStack getModFluid(String fluidName, int amount) {
        return FluidRegistry.getFluidStack(fluidName, amount);
    }

    @Nonnull
    public static ResourceLocation gteId(@Nonnull String path) {
        return new ResourceLocation(GTEValues.MODID, path);
    }
}
