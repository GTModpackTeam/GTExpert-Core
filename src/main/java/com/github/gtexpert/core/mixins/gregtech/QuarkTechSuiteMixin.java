package com.github.gtexpert.core.mixins.gregtech;

import net.minecraft.util.FoodStats;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import gregtech.common.items.armor.QuarkTechSuite;

import com.github.gtexpert.core.common.GTEConfigHolder;

/**
 * Mixin to add configurable auto-eat behavior to the Quark Tech Suite helmet.
 * <p>
 * Allows disabling the automatic food consumption feature via config option,
 * giving players more control over their food management.
 */
@Mixin(value = QuarkTechSuite.class, remap = false)
public class QuarkTechSuiteMixin {

    /**
     * Redirects the hunger check to optionally disable auto-eat functionality.
     * When disabled, the helmet will never attempt to automatically feed the player.
     */
    @Redirect(method = "onArmorTick",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/util/FoodStats;needFood()Z", remap = true))
    private boolean gtexpert$onArmorTick(FoodStats foodStats) {
        if (GTEConfigHolder.ceuOverride.disableHelmetAutoEat) {
            return false;
        } else {
            return foodStats.needFood();
        }
    }
}
