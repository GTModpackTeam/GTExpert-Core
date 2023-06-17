package gtexpert.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.jetbrains.annotations.NotNull;

public class GTEUtils {

    public static @NotNull ItemStack getModItem(String modID, String itemName) {
        return getModItem(modID, itemName, 1);
    }

    public static @NotNull ItemStack getModItem(String modID, String itemName, int amount) {
        return getModItem(modID, itemName, amount, 0);
    }

    public static @NotNull ItemStack getModItem(String modID, String itemName, int amount, int meta) {
        return getModItem(modID, itemName, amount, meta, null);
    }

    public static @NotNull ItemStack getModItem(String modID, String itemName, int amount, int meta, NBTTagCompound nbt) {
        return GameRegistry.makeItemStack(String.format("%s:%s", modID, itemName), meta, amount, nbt != null ? nbt.toString() : null);
    }

    public static @NotNull FluidStack getModFluid(String fluidName) {
        return FluidRegistry.getFluidStack(fluidName, 1000);
    }

    public static @NotNull FluidStack getModFluid(String fluidName, int amount) {
        return FluidRegistry.getFluidStack(fluidName, amount);
    }
}
