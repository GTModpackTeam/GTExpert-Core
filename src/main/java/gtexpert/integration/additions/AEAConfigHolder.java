package gtexpert.integration.additions;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;

import gtexpert.api.GTEValues;
import gtexpert.modules.GTEModules;

@LangKey(GTEValues.MODID + ".config.aeadditions")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/" + GTEModules.MODULE_AEA,
        category = "AE Additions")
public class AEAConfigHolder {}
