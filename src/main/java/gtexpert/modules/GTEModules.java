package gtexpert.modules;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.IModuleContainer;

public class GTEModules implements IModuleContainer {

    public static final String MODULE_CORE = "core";
    public static final String MODULE_INTEGRATION = "integration";
    public static final String MODULE_TOP = "top_integration";
    public static final String MODULE_JEI = "jei_integration";
    public static final String MODULE_AE = "ae_integration";
    public static final String MODULE_TC = "tc_integration";
    public static final String MODULE_AVARITIA = "avaritia_integration";
    public static final String MODULE_GTFO = "gtfo_integration";
    public static final String MODULE_EIO = "eio_integration";
    public static final String MODULE_DEDA = "deda_integration";
    public static final String MODULE_CHISEL = "chisel_integration";
    public static final String MODULE_CT = "ct_integration";

    @Override
    public String getID() {
        return GTEValues.MODID;
    }
}
