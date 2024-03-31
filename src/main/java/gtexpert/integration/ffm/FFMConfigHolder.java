package gtexpert.integration.ffm;

import net.minecraftforge.common.config.Config;

import gtexpert.api.GTEValues;
import gtexpert.modules.GTEModules;

@Config.LangKey(GTEValues.MODID + ".config.ae")
@Config(modid = GTEValues.MODID,
        name = GTEValues.MODID + "/" + GTEModules.MODULE_FFM,
        category = "Forestry")
public class FFMConfigHolder {

    @Config.Comment({ "Recipes for various items in Foresty will be more difficult",
            "default: false" })
    public static boolean hardForestryRecipe = false;
}
