package com.github.gtexpert.core.mixins.draconicevolution;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.brandon3055.draconicevolution.OreHandler;

/**
 * Mixin to prevent Draconic Evolution from registering its ore dictionary entries.
 * <p>
 * Specifically blocks the "oreDraconium" registration to allow GregTech
 * to handle draconium ore processing instead.
 */
@Mixin(value = OreHandler.class, remap = false)
public class OreHandlerMixin {

    /**
     * Redirects OreDictionary.registerOre calls to skip oreDraconium registration.
     * All other ore registrations are passed through unchanged.
     */
    @Redirect(method = "registerOres",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraftforge/oredict/OreDictionary;registerOre(Ljava/lang/String;Lnet/minecraft/item/ItemStack;)V"))
    private static void gtexpert$redirectRegisterOre(String name, ItemStack ore) {
        if (name.equals("oreDraconium")) {
            return;
        }
        OreDictionary.registerOre(name, ore);
    }
}
