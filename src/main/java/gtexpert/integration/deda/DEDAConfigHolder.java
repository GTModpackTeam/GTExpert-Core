package gtexpert.integration.deda;

import net.minecraftforge.common.config.Config;

import gtexpert.api.GTEValues;
import gtexpert.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.deda")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/" + GTEModules.MODULE_DEDA,
        category = "Draconic Evolution & Draconic Additions")
public class DEDAConfigHolder {

    @Config.Comment({ "The voltage at which DE/DA can be started.",
            "The material is also adjusted to each voltage.", "Default: 6 (LuV)" })
    @Config.RangeInt(min = 3, max = 6)
    public static int voltageTier = 6;
}
