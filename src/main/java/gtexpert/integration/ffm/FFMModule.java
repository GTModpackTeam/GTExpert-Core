package gtexpert.integration.ffm;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.ffm.loaders.FFMOreDictionaryLoader;
import gtexpert.integration.ffm.recipes.*;
import gtexpert.integration.ffm.recipes.machines.FFMMachineRecipeManager;
import gtexpert.modules.GTEModules;

import forestry.api.recipes.RecipeManagers;
import forestry.factory.recipes.CarpenterRecipeManager;

@GTEModule(
           moduleID = GTEModules.MODULE_FFM,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.FORESTRY,
           name = "GTExpert Forestry For Minecraft Integration",
           description = "Forestry For Minecraft Integration Module")
public class FFMModule extends GTEIntegrationSubmodule {

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        FFMBlockRecipe.init();
        FFMItemRecipe.init();
        FFMMaterialsRecipe.init();
        FFMToolRecipe.init();
        FFMWoodRecipe.init();
        FFMMachineRecipeManager.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        FFMOreDictionaryLoader.init();
    }

    public static void removeCarpenterRecipe(ItemStack output) {
        CarpenterRecipeManager.getRecipes(output).forEach(r -> RecipeManagers.carpenterManager.removeRecipe(r));
    }
}
