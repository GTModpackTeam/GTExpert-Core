package com.github.gtexpert.core.integration.eio;

import static gregtech.api.GTValues.*;

import java.util.function.Consumer;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import com.enderio.core.common.util.EntityUtil;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;

import com.github.gtexpert.core.api.GTEValues;
import com.github.gtexpert.core.api.modules.GTEModule;
import com.github.gtexpert.core.api.util.GTEUtility;
import com.github.gtexpert.core.api.util.Mods;
import com.github.gtexpert.core.integration.GTEIntegrationSubmodule;
import com.github.gtexpert.core.integration.eio.loaders.EIOMaterialInfoLoader;
import com.github.gtexpert.core.integration.eio.loaders.EIOOreDictionaryLoader;
import com.github.gtexpert.core.integration.eio.metatileentities.EIOMetaTileEntities;
import com.github.gtexpert.core.integration.eio.recipes.*;
import com.github.gtexpert.core.modules.GTEModules;

import crazypants.enderio.base.init.ModObject;

@GTEModule(
           moduleID = GTEModules.MODULE_EIO,
           containerID = GTEValues.MODID,
           modDependencies = Mods.Names.ENDER_IO,
           name = "GTExpert Ender IO Integration",
           description = "Ender IO Integration Module")
public class EnderIOModule extends GTEIntegrationSubmodule {

    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        EIOMetaTileEntities.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        EIOMaterialsRecipe.remove();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        EIOMaterialInfoLoader.init();
        EIOOreDictionaryLoader.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        // Ender IO Recipe
        EIOFluidRecipe.init();
        EIOMaterialsRecipe.init();
        EIOItemsRecipe.init();
        EIOBlocksRecipe.init();
        EIOToolsRecipe.init();
        EIOSliceNSpliceRecipe.init();

        // Ender IO Soul Recipe
        EIOSoulBinderRecipe.init();
        EIOVialExtractorRecipe.init();
    }

    /**
     * @param entityName            Name of entity, `modID:entityName`
     * @param xpAmount              Amount of Liquid XP to output
     * @param applyForRecipeBuilder Methods to call for recipe builder
     */
    public static void registerVialExtractorRecipe(String entityName, int xpAmount,
                                                   Consumer<RecipeBuilder<SimpleRecipeBuilder>> applyForRecipeBuilder) {
        if (isEntityInvalid(entityName)) return;

        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", entityName);
        stack.setTagCompound(tag);

        RecipeBuilder<SimpleRecipeBuilder> recipeBuilder = EnderIORecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder();

        recipeBuilder.inputNBT(new ItemStack(stack.getItem()), NBTMatcher.RECURSIVE_EQUAL_TO,
                NBTCondition.create(NBTTagType.STRING, "entityId", entityName))
                .output(ModObject.itemSoulVial.getItemNN())
                .fluidOutputs(GTEUtility.getModFluid("xpjuice", xpAmount));

        applyForRecipeBuilder.accept(recipeBuilder);

        recipeBuilder.duration(600).EUt(VA[LV]).buildAndRegister();
    }

    /**
     * @param entityName Name of entity, `modID:entityName`
     * @param xpAmount   Amount of Liquid XP to output
     * @param outputs    Item outputs, except for empty vial
     */
    public static void registerVialExtractorRecipe(String entityName, int xpAmount, ItemStack... outputs) {
        registerVialExtractorRecipe(entityName, xpAmount, builder -> builder.outputs(outputs));
    }

    /**
     * @param entityName Name of entity, `modID:entityName`
     * @param outputs    Item outputs, except for empty vial
     */
    public static void registerVialExtractorRecipe(String entityName, ItemStack... outputs) {
        registerVialExtractorRecipe(entityName, 1000, outputs);
    }

    public static boolean isEntityInvalid(ResourceLocation name) {
        return !EntityList.isRegistered(name) || !EntityUtil.isRegisteredMob(name);
    }

    public static boolean isEntityInvalid(String name) {
        return isEntityInvalid(new ResourceLocation(name));
    }
}
