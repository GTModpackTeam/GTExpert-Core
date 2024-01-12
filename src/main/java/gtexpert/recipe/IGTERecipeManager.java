package gtexpert.recipe;

import net.minecraft.util.ResourceLocation;

import gtexpert.api.modules.IModuleContainer;
import gtexpert.api.util.GTEUtility;

public interface IGTERecipeManager {

    default boolean isModuleEnabled(String containerID, String moduleID) {
        return isModuleEnabled(new ResourceLocation(containerID, moduleID));
    }

    default boolean isModuleEnabled(String moduleID) {
        return isModuleEnabled(GTEUtility.gteId(moduleID));
    }

    boolean isModuleEnabled(ResourceLocation id);

    void registerContainer(IModuleContainer container);

    IModuleContainer getLoadedContainer();
}
