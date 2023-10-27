package gtexpert.integration.ct;

import net.minecraft.util.text.translation.I18n;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.gtexpert.I18n")
public class CrTI18n {

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
