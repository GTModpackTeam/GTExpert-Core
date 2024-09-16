package gtexpert.mixins;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraftforge.fml.common.Loader;

import gtexpert.api.GTEValues;
import gtexpert.api.util.GTELog;
import gtexpert.api.util.Mods;

import zone.rong.mixinbooter.ILateMixinLoader;

public class GTEMixinLoader implements ILateMixinLoader {

    public static final Map<String, Boolean> modMixinsConfig = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>(Mods.Names.DRACONIC_ADDITIONS,
                    Mods.DraconicAdditions.isModLoaded()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @Override
    public List<String> getMixinConfigs() {
        return modMixinsConfig.keySet().stream().map(mod -> "mixins." + GTEValues.MODID + "." + mod + ".json")
                .collect(Collectors.toList());
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        String[] parts = mixinConfig.split("\\.");

        if (parts.length != 4) {
            GTELog.logger.fatal("Mixin Config Check Failed! Invalid Length.");
            GTELog.logger.fatal("Mixin Config: " + mixinConfig);
            return true;
        }

        if (!Objects.equals(parts[1], GTEValues.MODID)) {
            GTELog.logger.error("Non GTExpertCore Mixin Found in Mixin Queue. This is probably an error. Skipping...");
            GTELog.logger.error("Mixin Config: " + mixinConfig);
            return true;
        }

        if (!Loader.isModLoaded(parts[2])) {
            GTELog.logger.error("Mod '" + parts[2] +
                    "' is not loaded. If this is a normal GTExpertCore instance, this is probably an error.");
            GTELog.logger.error("Not Loading Mixin Config " + mixinConfig);
            return false;
        }

        if (!modMixinsConfig.containsKey(parts[2]) || !modMixinsConfig.get(parts[2])) {
            GTELog.logger.info("Integration for Mod '" + parts[2] + "' is not enabled, or does not exist.");
            GTELog.logger.info("Not Loading Mixin Config " + mixinConfig);
            return false;
        }

        return true;
    }
}
