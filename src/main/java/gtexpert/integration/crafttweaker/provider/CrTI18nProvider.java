package gtexpert.integration.crafttweaker.provider;

import net.minecraft.util.text.translation.I18n;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtexpert.I18n")
@ZenRegister
public class CrTI18nProvider {

    @ZenMethod
    public static boolean hasKey(String key) {
        return I18n.canTranslate(key);
    }

    @ZenMethod
    public static String format(String format, Object... args) {
        return I18n.translateToLocalFormatted(format, args);
    }

    @ZenMethod
    public static String format(String format) {
        return I18n.translateToLocal(format);
    }
}
