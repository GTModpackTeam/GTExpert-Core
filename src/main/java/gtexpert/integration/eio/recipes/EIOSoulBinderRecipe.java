package gtexpert.integration.eio.recipes;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.ore.OrePrefix.gem;

import java.util.Arrays;

import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.ingredients.nbtmatch.NBTTagType;
import gregtech.api.unification.material.Materials;

import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.api.util.GTEUtility;
import gtexpert.integration.eio.EnderIOModule;

import crazypants.enderio.base.init.ModObject;

public class EIOSoulBinderRecipe {

    public static void init() {
        soulBinderRecipes();
        recipeVillager();
        recipeWitch();
        recipeZombie();
    }

    private static void soulBinderRecipes() {
        for (ResourceLocation name : EntityList.getEntityNameList()) {
            if (EnderIOModule.isEntityInvalid(name)) continue;

            ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("entityId", name.toString());
            stack.setTagCompound(tag);

            // SoulBinder - Soul Filter
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(new GTRecipeItemInput(stack).setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                            NBTCondition.create(NBTTagType.STRING, "entityId", name.toString())))
                    .input(ModObject.itemBasicItemFilter.getItem())
                    .fluidInputs(GTEUtility.getModFluid("xpjuice", 4320))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemSoulFilterNormal.getItemNN())
                    .duration(1000).EUt(VA[LV]).buildAndRegister();

            // SoulBinder - Big Soul Filter
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(new GTRecipeItemInput(stack).setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                            NBTCondition.create(NBTTagType.STRING, "entityId", name.toString())))
                    .input(ModObject.itemBigItemFilter.getItem())
                    .fluidInputs(GTEUtility.getModFluid("xpjuice", 4320))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemSoulFilterBig.getItemNN())
                    .duration(1000).EUt(VA[LV]).buildAndRegister();
        }
    }

    private static void recipeVillager() {
        ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("entityId", "minecraft:villager");
        stack.setTagCompound(tag);

        // Soul Binder - Enticing Crystal
        GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                .input(new GTRecipeItemInput(stack).setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                        NBTCondition.create(NBTTagType.STRING, "entityId", "minecraft:villager")))
                .input(gem, Materials.Emerald)
                .fluidInputs(GTEUtility.getModFluid("xpjuice", 576))
                .output(ModObject.itemSoulVial.getItemNN())
                .output(ModObject.itemMaterial.getItemNN(), 1, 17)
                .duration(1000).EUt(VA[LV]).buildAndRegister();
    }

    private static void recipeWitch() {
        for (String witch : Arrays.asList("minecraft:witch", "enderzoo:witherwitch", "enderiozoo:witherwitch")) {
            if (EnderIOModule.isEntityInvalid(witch)) continue;
            ItemStack stack = new ItemStack(ModObject.itemSoulVial.getItemNN(), 1, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("entityId", witch);
            stack.setTagCompound(tag);

            // Soul Binder - Sentient Ender
            GTERecipeMaps.SOUL_BINDER_RECIPES.recipeBuilder()
                    .input(new GTRecipeItemInput(stack).setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                            NBTCondition.create(NBTTagType.STRING, "entityId", witch)))
                    .input(ModObject.itemMaterial.getItemNN(), 1, 43)
                    .fluidInputs(GTEUtility.getModFluid("xpjuice", 8640))
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
                    .input(new GTRecipeItemInput(stack).setNBTMatchingCondition(NBTMatcher.RECURSIVE_EQUAL_TO,
                            NBTCondition.create(NBTTagType.STRING, "entityId", zombie)))
                    .input(ModObject.itemMaterial.getItemNN(), 1, 41)
                    .fluidInputs(GTEUtility.getModFluid("xpjuice", 8640))
                    .output(ModObject.itemSoulVial.getItemNN())
                    .output(ModObject.itemMaterial.getItemNN(), 1, 42)
                    .duration(1000).EUt(VA[LV]).buildAndRegister();
        }
    }
}
