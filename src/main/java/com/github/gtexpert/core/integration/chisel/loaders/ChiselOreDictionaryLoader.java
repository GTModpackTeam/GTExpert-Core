package com.github.gtexpert.core.integration.chisel.loaders;

import com.github.gtexpert.core.api.util.GTEUtility;
import com.github.gtexpert.core.api.util.Mods;

public class ChiselOreDictionaryLoader {

    public static void init() {
        // craftChisel
        GTEUtility.registerOre(
                "craftChisel",
                Mods.Chisel.getItem("chisel_iron"),
                Mods.Chisel.getItem("chisel_diamond"),
                Mods.Chisel.getItem("chisel_hitech"));
    }
}
