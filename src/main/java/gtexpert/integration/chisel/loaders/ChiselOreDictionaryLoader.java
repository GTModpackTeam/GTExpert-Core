package gtexpert.integration.chisel.loaders;

import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;

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
