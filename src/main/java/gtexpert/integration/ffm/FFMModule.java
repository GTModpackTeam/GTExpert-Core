package gtexpert.integration.ffm;

import gtexpert.integration.ffm.loaders.FFMWoodRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.ffm.loaders.FFMOreDictionaryLoader;
import gtexpert.modules.GTEModules;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@GTEModule(
           moduleID = GTEModules.MODULE_FFM,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.FORESTRY,
           name = "GTExpert Forestry For Minecraft Integration",
           description = "Forestry For Minecraft Integration Module")
public class FFMModule extends GTEIntegrationSubmodule {

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        FFMWoodRecipe.remove();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        FFMOreDictionaryLoader.init();
        FFMWoodRecipe.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event) {
    }
}
