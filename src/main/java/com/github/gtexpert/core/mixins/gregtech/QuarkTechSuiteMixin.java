package com.github.gtexpert.core.mixins.gregtech;

import net.minecraft.util.FoodStats;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import gregtech.common.items.armor.QuarkTechSuite;

import com.github.gtexpert.core.common.GTEConfigHolder;

@Mixin(value = QuarkTechSuite.class, remap = false)
public class QuarkTechSuiteMixin {

    @Redirect(method = "onArmorTick",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/util/FoodStats;func_75121_c()Z"))
    private boolean gteCore$onArmorTick(FoodStats foodStats) {
        if (GTEConfigHolder.ceuOverride.disableHelmetAutoEat) {
            return false;
        } else {
            return foodStats.needFood();
        }
    }
}
