package gtexpert.integration.te;

import net.minecraftforge.common.config.Config;

import gtexpert.api.GTEValues;
import gtexpert.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.integration.te")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_TE,
        category = "ThaumicEnergistics")
public class TEConfigHolder {}
