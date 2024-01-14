package gtexpert.recipe;

import java.util.Collections;
import java.util.Set;

import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public interface IGTERecipe {

    @NotNull
    default Set<ResourceLocation> getDependencyUids() {
        return Collections.emptySet();
    }

    default void init() {}

    @NotNull
    default Logger getLogger() {
        return LogManager.getLogger("GTExpert Recipe");
    }
}
