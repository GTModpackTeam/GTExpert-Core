package gtexpert.integration.nae2;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;

import gtexpert.api.GTEValues;
import gtexpert.modules.GTEModules;

@LangKey(GTEValues.MODID + ".config.integration.nae2")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/integration/" + GTEModules.MODULE_NAE2,
        category = "Neeve's AE2")
public class NAE2ConfigHolder {

    @Config.Comment({ "Do you want to change the recipe to one that utilizes items from AEAdditions.",
            "If not changed, the recipe will utilize NAE2 items.", "Default: false" })
    public static boolean enableAEAdditions = false;
}
