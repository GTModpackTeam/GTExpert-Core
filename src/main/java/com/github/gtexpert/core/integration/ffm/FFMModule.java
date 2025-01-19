package com.github.gtexpert.core.integration.ffm;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.ffm.loaders.FFMOreDictionaryLoader;
import com.github.gtexpert.core.integration.ffm.recipes.*;
import com.github.gtexpert.core.integration.ffm.recipes.machines.*;
import com.github.gtexpert.core.modules.GTEModules;

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

        CarpenterLoader.initBase();
        CarpenterLoader.initMode();
        CentrifugeLoader.init();
        FabricatorLoader.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        FFMOreDictionaryLoader.init();
    }
}
