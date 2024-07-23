package gtexpert.integration.gendustry.api.recipes.ui;

import net.minecraftforge.items.IItemHandlerModifiable;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.ModularUI;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ui.RecipeMapUI;

@ApiStatus.Internal
public class IndustrialApiaryUI<R extends RecipeMap<?>> extends RecipeMapUI<R> {

    public IndustrialApiaryUI(@NotNull R recipeMap) {
        super(recipeMap, true, true, true, true);
    }

    @Override
    protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler,
                           FluidTankList fluidHandler, boolean isFluid, boolean isOutputs) {}
}
