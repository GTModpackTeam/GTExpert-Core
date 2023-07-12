package gtexpert;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

import javax.annotation.Nullable;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class GTEMixinPlugin implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> map) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
