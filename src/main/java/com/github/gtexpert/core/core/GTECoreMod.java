package com.github.gtexpert.core.core;

import java.io.*;
import java.util.Map;
import java.util.Properties;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import org.jetbrains.annotations.Nullable;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.util.GTELog;

public class GTECoreMod implements IFMLLoadingPlugin {

    static Properties coremodConfig = new Properties();
    public static boolean downloadOnlyOnce;

    @Override
    public String[] getASMTransformerClass() {
        GTELog.logger.info("GTECoreMod: getASMTransformerClass() called");
        return new String[] {
                "com.github.gtexpert.core.core.NAE2PatchTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        GTELog.logger.info("GTECoreMod: getSetupClass() called, returning DepLoader");
        return "com.github.gtexpert.core.core.deploader.DepLoader";
    }

    @Override
    public void injectData(Map<String, Object> data) {
        GTELog.logger.info("GTECoreMod: injectData() called");
        coremodConfig.setProperty("downloadOnlyOnce", "true");
        File mcLocation = (File) data.get("mcLocation");
        File configDir = new File(mcLocation, "config");
        // noinspection ResultOfMethodCallIgnored
        configDir.mkdir();
        File config = new File(configDir, GTEValues.MODID + "/GTECoreMod.properties");
        try (Reader r = new FileReader(config)) {
            coremodConfig.load(r);
        } catch (FileNotFoundException ignored) {
            // not a problem
        } catch (IOException e) {
            GTELog.logger.warn("Can't read coremod config. Proceeding with defaults!", e);
        }
        try (Writer r = new FileWriter(config)) {
            coremodConfig.store(r, "Config file for GTExpert CoreMod");
        } catch (IOException e) {
            GTELog.logger.warn("Can't write coremod config. Changes may not have been saved!", e);
        }
        downloadOnlyOnce = "true".equalsIgnoreCase(coremodConfig.getProperty("downloadOnlyOnce"));
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
