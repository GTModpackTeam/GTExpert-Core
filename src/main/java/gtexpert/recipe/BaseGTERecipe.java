package gtexpert.recipe;

import java.util.Collections;
import java.util.Set;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import gtexpert.api.util.GTEUtility;

public abstract class BaseGTERecipe implements IGTERecipe {

    @NotNull
    @Override
    public Set<ResourceLocation> getDependencyUids() {
        return Collections.singleton(GTEUtility.gteId(GTERecipeModules.MODULE_CORE));
    }
}
