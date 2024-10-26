package gtexpert.mixins.gregtech;

import gregtech.common.items.armor.QuarkTechSuite;

import net.minecraft.util.FoodStats;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = QuarkTechSuite.class, remap = false)
public class QuarkTechSuiteMixin {

    //disable autoeat in modpack
    @Redirect(method = "onArmorTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/FoodStats;needFood()Z"))
    private boolean gteCore$onArmorTick(FoodStats instance) {
        return false;
    }
}
