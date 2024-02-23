package gtexpert.integration.eio;

import static gregtech.api.GTValues.*;

import java.util.function.Consumer;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;

import com.enderio.core.common.util.EntityUtil;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.eio.loaders.EnderIOMaterialInfoLoader;
import gtexpert.integration.eio.metatileentities.EIOMetaTileEntities;
import gtexpert.integration.eio.recipes.*;
import gtexpert.modules.GTEModules;

import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;

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
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        EnderIOMaterialInfoLoader.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        // craftNutrientDistillation
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.PORKCHOP));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.BEEF));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.CHICKEN));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.RABBIT));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.MUTTON));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.FISH));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.FISH, 1, 1));
        OreDictionary.registerOre("craftNutrientDistillation", new ItemStack(Items.FISH, 1, 2));

        // craftHootch
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.WHEAT_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.DYE, 1, 3));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.BEETROOT_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.PUMPKIN_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.MELON_SEEDS));
        OreDictionary.registerOre("craftHootch", new ItemStack(Items.POISONOUS_POTATO));

        // eio.capacitor
        OreDictionary.registerOre("craftCapacitorEIO", new ItemStack(ModObject.itemBasicCapacitor.getItemNN()));
        OreDictionary.registerOre("craftCapacitorEIO", new ItemStack(EndergyObject.itemCapacitorSilver.getItemNN(), 1));

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

        recipeBuilder
                .input(new GTRecipeItemInput(stack).setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                        NBTCondition.create(NBTTagType.STRING, "entityId", entityName)))
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
