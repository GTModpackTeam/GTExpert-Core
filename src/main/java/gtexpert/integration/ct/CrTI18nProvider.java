package gtexpert.integration.ct;

import net.minecraft.client.resources.I18n;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtexpert.I18n")
@ZenRegister
public class CrTI18nProvider {

    @ZenMethod
    public static boolean hasKey(String key) {
        return I18n.hasKey(key);
    }

    @ZenMethod
    public static String format(String format, Object... args) {
        return I18n.format(format, args);
    }

    @ZenMethod
    public static String format(String format) {
        return I18n.format(format);
    }
}
