package gtexpert.recipe;

import gtexpert.api.util.GTEUtility;
import net.minecraft.util.ResourceLocation;

public interface IGTERecipeManager {

    default boolean isModuleEnabled(String containerID, String moduleID) {
        return isModuleEnabled(new ResourceLocation(containerID, moduleID));
    }

    default boolean isModuleEnabled(String moduleID) {
        return isModuleEnabled(GTEUtility.gteId(moduleID));
    }

    boolean isModuleEnabled(ResourceLocation id);

    void registerContainer(IGTERecipeContainer container);

    IGTERecipeContainer getLoadedContainer();
}
