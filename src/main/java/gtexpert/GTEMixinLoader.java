package gtexpert;

import java.util.ArrayList;
import java.util.List;

import gtexpert.api.util.Mods;

import zone.rong.mixinbooter.ILateMixinLoader;

public class GTEMixinLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        List<String> configs = new ArrayList<>();

        // configs.add("mixins.gtexpert.ceu.json");

        return configs;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return switch (mixinConfig) {
            case "mixins.gtexpert.draconicadditions.json" -> Mods.DraconicAdditions.isModLoaded();
            default -> true;
        };
    }
}
