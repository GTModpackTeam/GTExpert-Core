package gtexpert.integration.gendustry;

import net.minecraftforge.common.config.Config;

import gtexpert.api.GTEValues;
import gtexpert.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.gendustry")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_GENDUSTRY,
        category = "Gendustry")
public class GendustryConfigHolder {}
