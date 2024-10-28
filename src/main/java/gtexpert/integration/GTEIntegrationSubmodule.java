package gtexpert.integration;

import java.util.Collections;
import java.util.Set;

import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import gtexpert.api.util.GTEUtility;
import gtexpert.modules.BaseGTEModule;
import gtexpert.modules.GTEModules;

/**
 * Abstract class meant to be used by mod-specific compatibility modules.
 * Implements some shared skeleton code that should be shared by other modules.
 */
public abstract class GTEIntegrationSubmodule extends BaseGTEModule {

    private static final Set<ResourceLocation> DEPENDENCY_UID = Collections.singleton(
            GTEUtility.gteId(GTEModules.MODULE_INTEGRATION));

    @NotNull
    @Override
    public Logger getLogger() {
        return GTEIntegrationModule.logger;
    }

    @NotNull
    @Override
    public Set<ResourceLocation> getDependencyUids() {
        return DEPENDENCY_UID;
    }
}
