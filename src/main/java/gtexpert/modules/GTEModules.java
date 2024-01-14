package gtexpert.modules;

import gtexpert.api.modules.IModuleContainer;

public class GTEModules implements IModuleContainer {

    public static final String MODULE_CORE = "core";
    public static final String MODULE_TOOLS = "tools";
    public static final String MODULE_ITEMS = "items";
    public static final String MODULE_BLOCKS = "blocks";
    public static final String MODULE_FLUIDS = "fluids";
    public static final String MODULE_INTEGRATION = "integration",
            TOP_INTEGRATION = "top_integration",
            JEI_DEDA_INTEGRATION = "jei_integration";

    @Override
    public String getID() {
        return "gtexpert";
    }
}
