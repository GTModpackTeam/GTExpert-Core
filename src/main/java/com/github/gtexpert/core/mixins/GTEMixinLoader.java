package com.github.gtexpert.core.mixins;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import net.minecraftforge.fml.common.Loader;

import com.google.common.collect.ImmutableMap;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.util.GTELog;
import com.github.gtexpert.core.api.util.Mods;

import zone.rong.mixinbooter.ILateMixinLoader;

@SuppressWarnings("unused")
public class GTEMixinLoader implements ILateMixinLoader {

    public static final Map<String, Boolean> modMixinsConfig = new ImmutableMap.Builder<String, Boolean>()
            .put(Mods.Names.BETTER_QUESTING, true)
            .put(Mods.Names.DRACONIC_EVOLUTION, true)
            .put(Mods.Names.DRACONIC_ADDITIONS, true)
            .put(Mods.Names.GREGTECH, true)
            .put(Mods.Names.GREGICALITY_MULTIBLOCKS, true)
            .build();

    @SuppressWarnings("SimplifyStreamApiCallChains")
    @Override
    public List<String> getMixinConfigs() {
        return modMixinsConfig.keySet().stream().map(mod -> "mixins." + GTEValues.MODID + "." + mod + ".json")
                .collect(Collectors.toList());
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        String[] parts = mixinConfig.split("\\.");

        if (parts.length != 4 && parts.length != 5) {
            GTELog.logger.fatal("Mixin Config Check Failed! Invalid Length.");
            GTELog.logger.fatal("Mixin Config: {}", mixinConfig);
            return true;
        }

        if (!Objects.equals(parts[1], GTEValues.MODID)) {
            GTELog.logger.error("Non GTExpertCore Mixin Found in Mixin Queue. This is probably an error. Skipping...");
            GTELog.logger.error("Mixin Config: {}", mixinConfig);
            return true;
        }

        if (!Loader.isModLoaded(parts[2])) {
            GTELog.logger.error(
                    "Mod '{}' is not loaded. If this is a normal GTExpertCore instance, this is probably an error.",
                    parts[2]);
            GTELog.logger.error("Not Loading Mixin Config {}", mixinConfig);
            return false;
        }

        if (!modMixinsConfig.containsKey(parts[2]) || !modMixinsConfig.get(parts[2])) {
            GTELog.logger.info("Integration for Mod '{}' is not enabled, or does not exist.", parts[2]);
            GTELog.logger.info("Not Loading Mixin Config {}", mixinConfig);
            return false;
        }

        return true;
    }
}
