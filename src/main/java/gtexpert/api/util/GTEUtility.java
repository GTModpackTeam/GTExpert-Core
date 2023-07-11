package gtexpert.api.util;

import gtexpert.api.GTEValues;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Objects;

import javax.annotation.Nonnull;

public class GTEUtility {

    public static @Nonnull ItemStack getModItem(String modID, String itemName) {
        return GameRegistry.makeItemStack(modID + ":" + itemName, 0, 1, null);
    }

    public static @Nonnull ItemStack getModItem(String modID, String itemName, int amount) {
        return GameRegistry.makeItemStack(modID + ":" + itemName, 0, amount, null);
    }

    public static @Nonnull ItemStack getModItem(String modID, String itemName, int amount, int meta) {
        return GameRegistry.makeItemStack(modID + ":" + itemName, meta, amount, null);
    }

    public static @Nonnull ItemStack getModItem(String modID, String itemName, int amount, int meta,
                                                NBTTagCompound nbt) {
        return GameRegistry.makeItemStack(modID + ":" + itemName, meta, amount, nbt != null ? nbt.toString() : null);
    }

    public static @Nonnull FluidStack getModFluid(String fluidName) {
        return Objects.requireNonNull(FluidRegistry.getFluidStack(fluidName, 1000));
    }

    public static @Nonnull FluidStack getModFluid(String fluidName, int amount) {
        return Objects.requireNonNull(FluidRegistry.getFluidStack(fluidName, amount));
    }

    public static @Nonnull ResourceLocation gteId(String path) {
        return new ResourceLocation(GTEValues.MODID, path);
    }
}
