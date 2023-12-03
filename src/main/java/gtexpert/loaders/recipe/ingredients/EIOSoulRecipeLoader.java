package gtexpert.loaders.recipe.ingredients;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import java.util.Arrays;
import java.util.function.Consumer;

import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import com.enderio.core.common.util.EntityUtil;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gregtech.api.unification.material.Materials;

import gtexpert.api.GTEValues;
import gtexpert.api.recipes.GTERecipeMaps;

import crazypants.enderio.base.fluid.Fluids;
import crazypants.enderio.base.init.ModObject;

public class EIOSoulRecipeLoader {

    public static void init() {
        soulBinderRecipes();
        vialExtractorRecipes();
        recipeVillager();
        recipeWitch();
        recipeZombie();
    }

    private static void soulBinderRecipes() {
        for (ResourceLocation name : EntityList.getEntityNameList()) {
            if (isEntityInvalid(name)) continue;

            ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("entityId", name.toString());
            stack.setTagCompound(tag);

            // SoulBinder - Soul Filter
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                    NBTCondition.create(NBTTagType.STRING, "entityId", name.toString())))
                    .input(ModObject.itemBasicItemFilter.getItem())
                    .fluidInputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 4320))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemSoulFilterNormal.getItemNN())
                    .duration(1000).EUt(VA[LV]).buildAndRegister();

            // SoulBinder - Big Soul Filter
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                    NBTCondition.create(NBTTagType.STRING, "entityId", name.toString())))
                    .input(ModObject.itemBigItemFilter.getItem())
                    .fluidInputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 4320))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemSoulFilterBig.getItemNN())
                    .duration(1000).EUt(VA[LV]).buildAndRegister();
        }
    }

    private static void vialExtractorRecipes() {
        registerVialExtractorRecipe("minecraft:blaze", new ItemStack(Items.BLAZE_ROD, 10));
        registerVialExtractorRecipe("minecraft:creeper", new ItemStack(Items.SKULL, 10, 4),
                new ItemStack(Items.GUNPOWDER, 10));
        registerVialExtractorRecipe("minecraft:enderman",
                new ItemStack(ModObject.blockEndermanSkull.getItemNN(), 10, 0), new ItemStack(Items.ENDER_PEARL, 10));
        registerVialExtractorRecipe("minecraft:skeleton", new ItemStack(Items.SKULL, 10, 0),
                new ItemStack(Items.BONE, 10));
        registerVialExtractorRecipe("minecraft:ghast", new ItemStack(Items.GHAST_TEAR, 5));
        registerVialExtractorRecipe("minecraft:shulker", new ItemStack(Items.SHULKER_SHELL, 5));
        registerVialExtractorRecipe("minecraft:wither", 4000, new ItemStack(Items.NETHER_STAR, 2));
        for (String slime : Arrays.asList("minecraft:slime", "minecraft:magma_cube")) {
            registerVialExtractorRecipe(slime, 2000, new ItemStack(Items.SLIME_BALL, 10));
        }
        registerVialExtractorRecipe("minecraft:spider", new ItemStack(Items.SPIDER_EYE, 10),
                new ItemStack(Items.STRING, 10));
        registerVialExtractorRecipe("minecraft:zombie_pigman", 1000,
                builder -> builder.output(Items.ROTTEN_FLESH, 10).chancedOutput(ingot, Materials.Gold, 250, 250));
        registerVialExtractorRecipe("minecraft:wither_skeleton", new ItemStack(Items.SKULL, 5, 1),
                new ItemStack(Items.BONE, 5));
        for (String zombie : Arrays.asList("minecraft:zombie", "minecraft:husk", "minecraft:zombie_villager")) {
            registerVialExtractorRecipe(zombie, 1000, builder -> builder.output(Items.SKULL, 10, 2)
                    .output(Items.ROTTEN_FLESH, 10).chancedOutput(ingot, Materials.Iron, 100, 0));
        }
        for (String guardian : Arrays.asList("minecraft:guardian", "minecraft:elder_guardian")) {
            registerVialExtractorRecipe(guardian, new ItemStack(Items.PRISMARINE_SHARD, 5),
                    new ItemStack(Blocks.SPONGE, 1, 1));
        }
        registerVialExtractorRecipe("minecraft:squid", new ItemStack(Items.DYE, 10, 0));
        registerVialExtractorRecipe("minecraft:pig", new ItemStack(Items.PORKCHOP, 10));
        registerVialExtractorRecipe("minecraft:sheep", new ItemStack(Items.MUTTON, 10), new ItemStack(Blocks.WOOL, 5));
        registerVialExtractorRecipe("minecraft:chicken", 200, new ItemStack(Items.CHICKEN, 5),
                new ItemStack(Items.FEATHER, 2));
        registerVialExtractorRecipe("minecraft:rabbit", 200, builder -> builder.output(Items.RABBIT, 5)
                .output(Items.RABBIT_HIDE, 2)
                .chancedOutput(new ItemStack(Items.RABBIT_FOOT, 1), 2000, 0));
        for (String cow : Arrays.asList("minecraft:cow", GTEValues.MODID_GTFO + ":italian_buffalo")) {
            registerVialExtractorRecipe(cow, new ItemStack(Items.BEEF, 10), new ItemStack(Items.LEATHER, 5));
        }
    }

    private static void recipeVillager() {
        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", "minecraft:villager");
        stack.setTagCompound(tag);

        // Soul Binder - Enticing Crystal
        GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                .input(GTRecipeItemInput.getOrCreate(stack)
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.STRING, "entityId", "minecraft:villager")))
                .input(gem, Materials.Emerald)
                .fluidInputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 576))
                .output(ModObject.itemSoulVial.getItemNN())
                .output(ModObject.itemMaterial.getItemNN(), 1, 17)
                .duration(1000).EUt(VA[LV]).buildAndRegister();
    }

    private static void recipeWitch() {
        for (String witch : Arrays.asList("minecraft:witch", "enderzoo:witherwitch", "enderiozoo:witherwitch")) {
            if (isEntityInvalid(witch)) continue;
            ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("entityId", witch);
            stack.setTagCompound(tag);

            // Soul Binder - Sentient Ender
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                    NBTCondition.create(NBTTagType.STRING, "entityId", witch)))
                    .input(ModObject.itemMaterial.getItemNN(), 1, 43)
                    .fluidInputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 8640))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemMaterial.getItemNN(), 1, 44)
                    .duration(1000).EUt(VA[LV]).buildAndRegister();
        }
    }

    private static void recipeZombie() {
        for (String zombie : Arrays.asList("minecraft:zombie", "minecraft:husk", "minecraft:zombie_villager")) {
            ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("entityId", zombie);
            stack.setTagCompound(tag);

            // Soul Binder - Frank'N'Zombie
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(GTRecipeItemInput.getOrCreate(stack)
                            .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                    NBTCondition.create(NBTTagType.STRING, "entityId", zombie)))
                    .input(ModObject.itemMaterial.getItemNN(), 1, 41)
                    .fluidInputs(new FluidStack(Fluids.XP_JUICE.getFluid(), 8640))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemMaterial.getItemNN(), 1, 42)
                    .duration(1000).EUt(VA[LV]).buildAndRegister();
        }
    }

    /**
     * @param entityName            Name of entity, `modID:entityName`
     * @param xpAmount              Amount of Liquid XP to output
     * @param applyForRecipeBuilder Methods to call for recipe builder
     */
    private static void registerVialExtractorRecipe(String entityName, int xpAmount,
                                                    Consumer<RecipeBuilder<SimpleRecipeBuilder>> applyForRecipeBuilder) {
        if (isEntityInvalid(entityName)) return;

        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", entityName);
        stack.setTagCompound(tag);

        RecipeBuilder<SimpleRecipeBuilder> recipeBuilder = GTERecipeMaps.VIAL_EXTRACTOR_RECIPES.recipeBuilder();

        recipeBuilder
                .input(GTRecipeItemInput.getOrCreate(stack)
                        .setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                                NBTCondition.create(NBTTagType.STRING, "entityId", entityName)))
                .output(ModObject.itemSoulVial.getItemNN())
                .fluidOutputs(new FluidStack(Fluids.XP_JUICE.getFluid(), xpAmount));

        applyForRecipeBuilder.accept(recipeBuilder);

        recipeBuilder.duration(600).EUt(VA[LV]).buildAndRegister();
    }

    /**
     * @param entityName Name of entity, `modID:entityName`
     * @param xpAmount   Amount of Liquid XP to output
     * @param outputs    Item outputs, except for empty vial
     */
    private static void registerVialExtractorRecipe(String entityName, int xpAmount, ItemStack... outputs) {
        registerVialExtractorRecipe(entityName, xpAmount, builder -> builder.outputs(outputs));
    }

    /**
     * @param entityName Name of entity, `modID:entityName`
     * @param outputs    Item outputs, except for empty vial
     */
    private static void registerVialExtractorRecipe(String entityName, ItemStack... outputs) {
        registerVialExtractorRecipe(entityName, 1000, outputs);
    }

    private static boolean isEntityInvalid(ResourceLocation name) {
        return !EntityList.isRegistered(name) || !EntityUtil.isRegisteredMob(name);
    }

    private static boolean isEntityInvalid(String name) {
        return isEntityInvalid(new ResourceLocation(name));
    }
}
