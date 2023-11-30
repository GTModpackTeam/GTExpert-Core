package gtexpert;

import java.util.Arrays;
import java.util.List;

import zone.rong.mixinbooter.ILateMixinLoader;

public class GTEMixinLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        String[] configs = { "mixins.gtexpert.json" };
        return Arrays.asList(configs);
    }
}
