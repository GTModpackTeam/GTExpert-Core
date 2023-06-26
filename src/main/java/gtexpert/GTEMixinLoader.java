package gtexpert;

import net.minecraftforge.fml.common.Loader;

import com.google.common.collect.Lists;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Collections;
import java.util.List;

public class GTEMixinLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        if (Loader.isModLoaded("ezstorage"))
            return Lists.newArrayList("mixins.gtexpert.json");
        return Collections.emptyList();
    }
}
