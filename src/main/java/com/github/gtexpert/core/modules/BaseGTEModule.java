package com.github.gtexpert.core.modules;

import java.util.Collections;
import java.util.Set;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.github.gtexpert.core.api.modules.IGTEModule;
import com.github.gtexpert.core.api.util.GTEUtility;

public abstract class BaseGTEModule implements IGTEModule {

    @NotNull
    @Override
    public Set<ResourceLocation> getDependencyUids() {
        return Collections.singleton(GTEUtility.gteId(GTEModules.MODULE_CORE));
    }
}
