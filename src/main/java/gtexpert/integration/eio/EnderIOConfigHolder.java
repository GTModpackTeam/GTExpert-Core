package gtexpert.integration.eio;

import net.minecraftforge.common.config.Config;

import gtexpert.api.GTEValues;
import gtexpert.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.eio")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_EIO,
        category = "Ender IO")
public class EnderIOConfigHolder {

    @Config.Comment({ "The voltage at which EIO can be started.",
            "The material is also adjusted to each voltage.", "Default: 3 (HV)" })
    @Config.RangeInt(min = 1, max = 8)
    public static int voltageTier = 3;

    @Config.Comment({ "Change EIO swords, axes, armor, etc. to GT recipe standards.",
            "CEu's hardToolArmorRecipes to true to reflect.", "Default: false" })
    public static boolean hardToolArmorRecipes = false;

    @Config.Comment({ "Add Shapeless Recipe in CoreMod Machines and EIO Machines.",
            "This change adds a recipe for equivalent exchange of HV machines and EIO machines", "Default: false" })
    public static boolean addShapelessRecipeMachines = false;
}
