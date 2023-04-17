package gtexpert.api.recipes.draconicupgrade;

import com.brandon3055.draconicevolution.api.fusioncrafting.IFusionRecipe;
import com.brandon3055.draconicevolution.api.itemupgrade.FusionUpgradeRecipe;
import com.brandon3055.draconicevolution.lib.ToolUpgradeRecipe;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gtexpert.GTExpertMod;
import gtexpert.api.recipes.draconicupgrade.tierup.TierUpRecipeBuilder;
import gtexpert.api.recipes.draconicupgrade.tierup.TierUpRecipeProperty;
import gtexpert.api.recipes.draconicupgrade.upgrade.UpgradeRecipeBuilder;
import gtexpert.api.recipes.draconicupgrade.upgrade.UpgradeRecipeProperty;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RecipeMapDraconicFusion extends RecipeMap<SimpleRecipeBuilder> {

    private final RecipeMap<TierUpRecipeBuilder> tierUpRecipeMap;
    private final RecipeMap<UpgradeRecipeBuilder> upgradeRecipeMap;

    public RecipeMapDraconicFusion(@NotNull String unlocalizedName, int maxInputs, int maxOutputs, int maxFluidInputs, int maxFluidOutputs, @NotNull SimpleRecipeBuilder defaultRecipeBuilder, boolean isHidden, RecipeMap<TierUpRecipeBuilder> tierUpRecipeMap, RecipeMap<UpgradeRecipeBuilder> upgradeRecipeMap) {
        super(unlocalizedName, maxInputs, maxOutputs, maxFluidInputs, maxFluidOutputs, defaultRecipeBuilder, isHidden);
        this.tierUpRecipeMap = tierUpRecipeMap;
        this.upgradeRecipeMap = upgradeRecipeMap;
    }

    @Nullable
    @Override
    public Recipe findRecipe(long voltage, List<ItemStack> inputs, List<FluidStack> fluidInputs, boolean exactVoltage) {
        Recipe superRecipe = super.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (superRecipe != null) {
            return superRecipe;
        }

        Recipe tierUpRecipe = tierUpRecipeMap.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (tierUpRecipe != null) {
            return setupTierUpRecipe(tierUpRecipe, inputs);
        }
        Recipe upgradeRecipe = upgradeRecipeMap.findRecipe(voltage, inputs, fluidInputs, exactVoltage);
        if (upgradeRecipe != null) {
            return setupUpgradeRecipe(upgradeRecipe, inputs);
        }

        return null;
    }

    private Recipe setupTierUpRecipe(Recipe gtRecipe, List<ItemStack> inputs) {
        ToolUpgradeRecipe tierUpRecipe = gtRecipe.getProperty(TierUpRecipeProperty.getInstance(), null);
        if (tierUpRecipe == null) {
            GTExpertMod.logger.warn("Recipe found, but property ToolUpgradeRecipe not found");
            GTExpertMod.logger.warn("Recipe: " + gtRecipe);
            return null;
        }
        ItemStack catalyst = findCatalyst(inputs, tierUpRecipe);
        if (catalyst == null) {
            GTExpertMod.logger.warn("Recipe found, but actual catalyst not found in the GT recipe");
            GTExpertMod.logger.warn("Recipe: " + gtRecipe);
            GTExpertMod.logger.warn("Expected catalyst: " + tierUpRecipe.getRecipeCatalyst());
            return null;
        }

        Recipe retRecipe = gtRecipe.copy();
        retRecipe.getOutputs().clear(); // This assumes output has only 1 slot
        retRecipe.getOutputs().add(tierUpRecipe.getRecipeOutput(catalyst));
        return retRecipe;
    }

    private Recipe setupUpgradeRecipe(Recipe gtRecipe, List<ItemStack> inputs) {
        FusionUpgradeRecipe upgradeRecipe = gtRecipe.getProperty(UpgradeRecipeProperty.getInstance(), null);
        if (upgradeRecipe == null) {
            GTExpertMod.logger.warn("Recipe found, but property UpgradeRecipeProperty not found");
            GTExpertMod.logger.warn("Recipe: " + gtRecipe);
            return null;
        }
        ItemStack catalyst = findCatalyst(inputs, upgradeRecipe);
        if (catalyst == null) {
            GTExpertMod.logger.warn("Recipe found, but actual catalyst not found in the GT recipe");
            GTExpertMod.logger.warn("Recipe: " + gtRecipe);
            GTExpertMod.logger.warn("Expected catalyst: " + upgradeRecipe.getRecipeCatalyst());
            return null;
        }

        Recipe retRecipe = gtRecipe.copy();
        retRecipe.getOutputs().clear(); // This assumes output has only 1 slot
        retRecipe.getOutputs().add(upgradeRecipe.getRecipeOutput(catalyst));
        return retRecipe;
    }

    private ItemStack findCatalyst(List<ItemStack> inputs, IFusionRecipe fusionRecipe) {
        for (ItemStack input : inputs) {
            if (fusionRecipe.isRecipeCatalyst(input)) {
                return input;
            }
        }
        return null;
    }
}
