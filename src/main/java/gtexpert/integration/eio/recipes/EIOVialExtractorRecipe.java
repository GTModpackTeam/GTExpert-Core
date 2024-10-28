package gtexpert.integration.eio.recipes;

import static gregtech.api.unification.ore.OrePrefix.ingot;

import java.util.Arrays;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.unification.material.Materials;

import gtexpert.api.util.Mods;
import gtexpert.integration.eio.EnderIOModule;

import crazypants.enderio.base.init.ModObject;

public class EIOVialExtractorRecipe {

    public static void init() {
        EnderIOModule.registerVialExtractorRecipe("minecraft:blaze", new ItemStack(Items.BLAZE_ROD, 10));
        EnderIOModule.registerVialExtractorRecipe("minecraft:creeper", new ItemStack(Items.SKULL, 10, 4),
                new ItemStack(Items.GUNPOWDER, 10));
        EnderIOModule.registerVialExtractorRecipe("minecraft:enderman",
                new ItemStack(ModObject.blockEndermanSkull.getItemNN(), 10, 0), new ItemStack(Items.ENDER_PEARL, 10));
        EnderIOModule.registerVialExtractorRecipe("minecraft:skeleton", new ItemStack(Items.SKULL, 10, 0),
                new ItemStack(Items.BONE, 10));
        EnderIOModule.registerVialExtractorRecipe("minecraft:ghast", new ItemStack(Items.GHAST_TEAR, 5));
        EnderIOModule.registerVialExtractorRecipe("minecraft:shulker", new ItemStack(Items.SHULKER_SHELL, 5));
        EnderIOModule.registerVialExtractorRecipe("minecraft:wither", 4000, new ItemStack(Items.NETHER_STAR, 2));
        for (String slime : Arrays.asList("minecraft:slime", "minecraft:magma_cube")) {
            EnderIOModule.registerVialExtractorRecipe(slime, 2000, new ItemStack(Items.SLIME_BALL, 10));
        }
        EnderIOModule.registerVialExtractorRecipe("minecraft:spider", new ItemStack(Items.SPIDER_EYE, 10),
                new ItemStack(Items.STRING, 10));
        EnderIOModule.registerVialExtractorRecipe("minecraft:zombie_pigman", 1000,
                builder -> builder.output(Items.ROTTEN_FLESH, 10).chancedOutput(ingot, Materials.Gold, 250, 250));
        EnderIOModule.registerVialExtractorRecipe("minecraft:wither_skeleton", new ItemStack(Items.SKULL, 5, 1),
                new ItemStack(Items.BONE, 5));
        for (String zombie : Arrays.asList("minecraft:zombie", "minecraft:husk", "minecraft:zombie_villager")) {
            EnderIOModule.registerVialExtractorRecipe(zombie, 1000, builder -> builder.output(Items.SKULL, 10, 2)
                    .output(Items.ROTTEN_FLESH, 10).chancedOutput(ingot, Materials.Iron, 100, 0));
        }
        for (String guardian : Arrays.asList("minecraft:guardian", "minecraft:elder_guardian")) {
            EnderIOModule.registerVialExtractorRecipe(guardian, new ItemStack(Items.PRISMARINE_SHARD, 5),
                    new ItemStack(Blocks.SPONGE, 1, 1));
        }
        EnderIOModule.registerVialExtractorRecipe("minecraft:squid", new ItemStack(Items.DYE, 10, 0));
        EnderIOModule.registerVialExtractorRecipe("minecraft:pig", new ItemStack(Items.PORKCHOP, 10));
        EnderIOModule.registerVialExtractorRecipe("minecraft:sheep", new ItemStack(Items.MUTTON, 10),
                new ItemStack(Blocks.WOOL, 5));
        EnderIOModule.registerVialExtractorRecipe("minecraft:chicken", 200, new ItemStack(Items.CHICKEN, 5),
                new ItemStack(Items.FEATHER, 2));
        EnderIOModule.registerVialExtractorRecipe("minecraft:rabbit", 200, builder -> builder.output(Items.RABBIT, 5)
                .output(Items.RABBIT_HIDE, 2)
                .chancedOutput(new ItemStack(Items.RABBIT_FOOT, 1), 2000, 0));
        for (String cow : Arrays.asList("minecraft:cow", Mods.GregTechFoodOption.name() + ":italian_buffalo")) {
            EnderIOModule.registerVialExtractorRecipe(cow, new ItemStack(Items.BEEF, 10),
                    new ItemStack(Items.LEATHER, 5));
        }
    }
}
