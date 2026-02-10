package com.github.gtexpert.core.mixins.gregtech;

import java.util.List;

import net.minecraft.client.resources.I18n;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import gregtech.common.items.armor.QuarkTechSuite;

import com.github.gtexpert.core.common.GTEConfigHolder;

/**
 * Client-only mixin to hide the auto-eat tooltip when the feature is disabled via config.
 */
@Mixin(value = QuarkTechSuite.class, remap = false)
public class QuarkTechSuiteClientMixin {

    /**
     * Hides the auto-eat tooltip when the feature is disabled via config.
     */
    @Redirect(method = "addInfo",
              at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private boolean gtexpert$hideAutoEatTooltip(List<String> list, Object element) {
        if (GTEConfigHolder.ceuOverride.disableHelmetAutoEat &&
                I18n.format("metaarmor.tooltip.autoeat").equals(element)) {
            return false;
        }
        return list.add((String) element);
    }
}
