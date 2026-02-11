package com.github.gtexpert.core.integration.ae;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.jetbrains.annotations.NotNull;

import gregtech.api.event.MaterialInfoEvent;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.ae.loaders.AEMaterialInfoLoader;
import com.github.gtexpert.core.integration.ae.loaders.AEOreDictionaryLoader;
import com.github.gtexpert.core.integration.ae.recipes.AEBlocksRecipe;
import com.github.gtexpert.core.integration.ae.recipes.AEItemsRecipe;
import com.github.gtexpert.core.integration.ae.recipes.AEMaterialsRecipe;
import com.github.gtexpert.core.integration.ae.recipes.AEToolsRecipe;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_AE,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.APPLIED_ENERGISTICS2,
           name = "GTExpert Applied Energistics 2 Integration",
           description = "Applied Energistics 2 Integration Module")
public class AEModule extends GTEIntegrationSubmodule {

    @NotNull
    @Override
    public List<Class<?>> getEventBusSubscribers() {
        return Collections.singletonList(AEModule.class);
    }

    @SubscribeEvent
    public static void onMaterialInfo(MaterialInfoEvent event) {
        AEMaterialInfoLoader.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        AEOreDictionaryLoader.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        AEMaterialsRecipe.remove();

        AEMaterialsRecipe.init();
        AEItemsRecipe.init();
        AEBlocksRecipe.init();
        AEToolsRecipe.init();
    }
}
