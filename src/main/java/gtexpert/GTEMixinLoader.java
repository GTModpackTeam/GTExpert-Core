package gtexpert;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Arrays;
import java.util.List;

public class GTEMixinLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        String[] configs = { "mixins.gtexpert.json" };
        return Arrays.asList(configs);
    }
}
