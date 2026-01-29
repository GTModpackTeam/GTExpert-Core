package com.github.gtexpert.core.integration.chisel;

import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import org.jetbrains.annotations.NotNull;

import gregtech.api.items.toolitem.IGTTool;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.chisel.loaders.ChiselOreDictionaryLoader;
import com.github.gtexpert.core.integration.chisel.metatileentities.ChiselMetaTileEntities;
import com.github.gtexpert.core.integration.chisel.recipes.ChiselBlocksRecipe;
import com.github.gtexpert.core.integration.chisel.recipes.ChiselToolsRecipe;
import com.github.gtexpert.core.integration.chisel.tools.ChiselToolItems;
import com.github.gtexpert.core.integration.chisel.tools.ChiselToolRecipeHandler;
import com.github.gtexpert.core.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_CHISEL,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.CHISEL,
           name = "GTExpert Chisel Integration",
           description = "Chisel Integration Module")
public class ChiselModule extends GTEIntegrationSubmodule {

    @NotNull
    @Override
    public List<Class<?>> getEventBusSubscribers() {
        return Collections.singletonList(ChiselModule.class);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ChiselToolItems.init();
        ChiselToolRecipeHandler.registerRecipes();
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        for (IGTTool tool : ChiselToolItems.getAllTools()) {
            registry.register(tool.get());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModels(ModelRegistryEvent event) {
        ChiselToolItems.registerModels();
    }

    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        ChiselMetaTileEntities.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        ChiselOreDictionaryLoader.init();
        ChiselToolItems.registerOreDict();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        ChiselBlocksRecipe.init();
        ChiselToolsRecipe.init();
    }

    @SideOnly(Side.CLIENT)
    public static void registerColors() {
        ChiselToolItems.registerColors();
    }
}
