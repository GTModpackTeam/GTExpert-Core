package com.github.gtexpert.core.integration.eio.metatileentities;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.util.ItemStackHashStrategy;
import gregtech.client.renderer.ICubeRenderer;

import com.github.gtexpert.core.client.GTETextures;
import com.github.gtexpert.core.common.metatileentities.GTESimpleMachineMetaTileEntity;
import com.github.gtexpert.core.integration.eio.EnderIORecipeMaps;

public class MetaTileEntitySliceNSplice extends GTESimpleMachineMetaTileEntity {

    public MetaTileEntitySliceNSplice(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, ICubeRenderer renderer,
                                      int tier, boolean hasFrontFacing,
                                      Function<Integer, Integer> tankScalingFunction) {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySliceNSplice(this.metaTileEntityId, EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES,
                GTETextures.SLICE_N_SPLICE_OVERLAY, this.getTier(), this.hasFrontFacing(),
                this.getTankScalingFunction());
    }

    @Override
    protected RecipeLogicEnergy createWorkable(RecipeMap<?> recipeMap) {
        return new SliceNSpliceRecipeLogic(this, recipeMap, () -> energyContainer);
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();

        this.importItems = new AmountLimitedItemStackHandler(this, 6, this);
        this.itemInventory = new ItemHandlerProxy(this.importItems, this.exportItems);
    }

    protected boolean checkRecipe(@NotNull Recipe recipe) {
        List<GTRecipeInput> inputs = recipe.getInputs();
        for (int i = 0; i < inputs.size(); i++) {
            if (!inputs.get(i).acceptsStack(this.importItems.getStackInSlot(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtexpert.machine.slice_n_splice.slot.1"));
        tooltip.add(I18n.format("gtexpert.machine.slice_n_splice.slot.2"));
        tooltip.add(I18n.format("gtexpert.machine.slice_n_splice.ordered"));
        super.addInformation(stack, player, tooltip, advanced);
    }

    private static class AmountLimitedItemStackHandler extends NotifiableItemStackHandler {

        private final Collection<Recipe> recipes;

        public AmountLimitedItemStackHandler(MetaTileEntity metaTileEntity, int slots, MetaTileEntity entityToNotify) {
            super(metaTileEntity, slots, entityToNotify, false);
            this.recipes = EnderIORecipeMaps.SLICE_N_SPLICE_RECIPES.getRecipeList();
        }

        // === Copied from MetaTileEntityMachineHatch.LimitedImportHandler#insertItem ===
        @NotNull
        @Override
        // Insert item returns the remainder stack that was not inserted
        public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            // If the item was not valid, nothing from the stack can be inserted
            if (!isItemValid(slot, stack)) {
                return stack;
            }

            // Return Empty if passed Empty
            if (stack.isEmpty()) {
                return ItemStack.EMPTY;
            }

            // If the stacks do not match, nothing can be inserted
            if (!ItemStackHashStrategy.comparingAllButCount().equals(stack, this.getStackInSlot(slot)) &&
                    !this.getStackInSlot(slot).isEmpty()) {
                return stack;
            }

            int amountInSlot = this.getStackInSlot(slot).getCount();
            int slotLimit = getSlotLimit(slot);

            // If the current stack size in the slot is greater than the limit of the Multiblock, nothing can be
            // inserted
            if (amountInSlot >= slotLimit) {
                return stack;
            }

            // This will always be positive and greater than zero if reached
            int spaceAvailable = slotLimit - amountInSlot;

            // Insert the minimum amount between the amount of space available and the amount being inserted
            int amountToInsert = Math.min(spaceAvailable, stack.getCount());

            // The remainder that was not inserted
            int remainderAmount = stack.getCount() - amountToInsert;

            // Handle any remainder
            ItemStack remainder = ItemStack.EMPTY;

            if (remainderAmount > 0) {
                remainder = stack.copy();
                remainder.setCount(remainderAmount);
            }

            if (!simulate) {
                // Perform the actual insertion
                ItemStack temp = stack.copy();
                temp.setCount(amountInSlot + amountToInsert);
                this.setStackInSlot(slot, temp);
            }

            return remainder;
        }

        @Override
        // Determine whether the item is used in the recipe
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            for (Recipe recipe : this.recipes) {
                List<GTRecipeInput> inputs = recipe.getInputs();

                if (slot < 0 || slot >= inputs.size()) continue;

                if (inputs.get(slot).acceptsStack(stack)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    }

    private static class SliceNSpliceRecipeLogic extends RecipeLogicEnergy {

        public SliceNSpliceRecipeLogic(MetaTileEntity tileEntity, RecipeMap<?> recipeMap,
                                       Supplier<IEnergyContainer> energyContainer) {
            super(tileEntity, recipeMap, energyContainer);
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe) {
            return ((MetaTileEntitySliceNSplice) metaTileEntity).checkRecipe(recipe) && super.checkRecipe(recipe);
        }
    }
}
