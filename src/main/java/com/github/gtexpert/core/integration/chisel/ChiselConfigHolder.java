package com.github.gtexpert.core.integration.chisel;

import net.minecraftforge.common.config.Config;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.chisel")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_CHISEL,
        category = "Chisel")
public class ChiselConfigHolder {

    @Config.Comment({ "Change Chisel recipes to GT recipe standards.",
            "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
    public static boolean hardToolRecipes = false;

    @Config.Comment({ "Change LED for Project:RED recipes to GT recipe standards.", "Default: false" })
    public static boolean hardLedRecipes = false;
}
