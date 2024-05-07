package gtexpert.core.loaders;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.jetbrains.annotations.NotNull;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.loaders.WoodTypeEntry;

import gtexpert.api.util.GTEUtility;
import gtexpert.core.GTEConfigHolder;
import gtexpert.core.GTERecipeMaps;

public class GTEWoodRecipeLoader {

    public static void registerWoodTypeRecipe(boolean randomRecipeID, @NotNull WoodTypeEntry entry) {
        final String name = entry.woodName;
        final String prefix = randomRecipeID ? entry.modid + "_pf" + GTEUtility.generateRandomString(6) : entry.modid;

        if (entry.planks.isEmpty()) {
            throw new IllegalStateException("Could not find planks form of WoodTypeEntry '" + name + "'.");
        }

        // log-associated recipes
        if (!entry.log.isEmpty()) {
            // nerf regular log -> plank crafting, if enabled
            boolean hasPlanksRecipe = entry.planksRecipeName != null;

            int plank_normal = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 1 : 2;
            plank_normal = ConfigHolder.recipes.nerfWoodCrafting ? plank_normal : 4;
            int plank_saw = GTEConfigHolder.ceuOverride.moreNerfPlankCrafting ? 2 : 4;
            plank_saw = ConfigHolder.recipes.nerfWoodCrafting ? plank_saw : 6;

            if (hasPlanksRecipe) {
                ModHandler.addShapelessRecipe(prefix + "_" + entry.planksRecipeName,
                        GTUtility.copy(plank_normal, entry.planks), entry.log.copy());
                ModHandler.addMirroredShapedRecipe(prefix + "_" + entry.planksRecipeName + "_saw",
                        GTUtility.copy(plank_saw, entry.planks), "s", "L",
                        'L', entry.log.copy());
            } else {
                ModHandler.addShapelessRecipe(prefix + "_" + name + "_plank",
                        GTUtility.copy(plank_normal, entry.planks), entry.log.copy());
                ModHandler.addMirroredShapedRecipe(prefix + "_" + name + "_plank_saw",
                        GTUtility.copy(plank_saw, entry.planks), "s", "L",
                        'L', entry.log.copy());
            }

            // log -> charcoal furnace recipe removal, if enabled
            if (ConfigHolder.recipes.harderCharcoalRecipe) {
                if (entry.removeCharcoalRecipe) {
                    final ItemStack outputStack = FurnaceRecipes.instance().getSmeltingResult(entry.log);
                    if (outputStack.getItem() == Items.COAL && outputStack.getItemDamage() == 1) {
                        ModHandler.removeFurnaceSmelting(entry.log);
                    }
                }
            } else {
                if (entry.addCharcoalRecipe) {
                    GameRegistry.addSmelting(MetaBlocks.RUBBER_LOG, new ItemStack(Items.COAL, 1, 1), 0.15F);
                }
            }
        }

        // door
        if (!entry.door.isEmpty()) {
            final boolean hasDoorRecipe = entry.doorRecipeName != null;
            if (ConfigHolder.recipes.hardWoodRecipes) {
                // hard plank -> door crafting
                if (hasDoorRecipe) {
                    ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, entry.doorRecipeName));
                }
                ModHandler.addShapedRecipe(hasDoorRecipe ? prefix + entry.doorRecipeName : prefix + name + "_door",
                        entry.door.copy(),
                        "PTd", "PRS", "PPs",
                        'P', entry.planks.copy(),
                        'T', new ItemStack(Blocks.TRAPDOOR),
                        'R', new UnificationEntry(ring, Materials.Iron),
                        'S', new UnificationEntry(screw, Materials.Iron));

                // plank -> door assembling
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(Blocks.TRAPDOOR))
                        .inputs(GTUtility.copy(4, entry.planks))
                        .fluidInputs(Materials.Iron.getFluid(GTValues.L / 9))
                        .outputs(entry.door.copy())
                        .duration(400).EUt(4).buildAndRegister();
            } else {
                if (!hasDoorRecipe) {
                    ModHandler.addShapedRecipe(prefix + name + "_door", GTUtility.copy(3, entry.door),
                            "PP", "PP", "PP",
                            'P', entry.planks.copy());
                }

                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                        .inputs(GTUtility.copy(6, entry.planks))
                        .outputs(GTUtility.copy(3, entry.door))
                        .circuitMeta(6)
                        .duration(600).EUt(4)
                        .buildAndRegister();
            }
        }

        // stairs
        if (!entry.stairs.isEmpty()) {
            final boolean hasStairRecipe = entry.stairsRecipeName != null;
            if (entry.addStairsCraftingRecipe) {
                ModHandler.addShapedRecipe(hasStairRecipe ? prefix + entry.stairsRecipeName : prefix + name + "_stairs",
                        GTUtility.copy(4, entry.stairs),
                        "P  ", "PP ", "PPP",
                        'P', entry.planks.copy());
            }

            // plank -> stairs assembling
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(GTUtility.copy(6, entry.planks))
                    .outputs(GTUtility.copy(4, entry.stairs))
                    .circuitMeta(7)
                    .EUt(1).duration(100).buildAndRegister();
        }

        // slab
        if (!entry.slab.isEmpty()) {
            if (entry.addSlabCraftingRecipe && !ConfigHolder.recipes.hardWoodRecipes) {
                ModHandler.addShapedRecipe(prefix + name + "_slab", GTUtility.copy(6, entry.slab),
                        "PPP", 'P', entry.planks.copy());
            }

            // plank -> slab crafting
            ModHandler.addShapedRecipe(prefix + name + "_slab_saw", GTUtility.copy(2, entry.slab),
                    "sS", 'S', entry.planks.copy());

            if (ConfigHolder.recipes.hardWoodRecipes && entry.slabRecipeName != null) {
                ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, entry.slabRecipeName));
            }

            // plank -> slab cutting
            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                    .inputs(entry.planks.copy())
                    .outputs(GTUtility.copy(2, entry.slab))
                    .duration(200).EUt(VA[ULV])
                    .buildAndRegister();
        }

        // fence
        if (!entry.fence.isEmpty()) {
            final boolean hasFenceRecipe = entry.fenceRecipeName != null;
            if (ConfigHolder.recipes.hardWoodRecipes) {
                // hard plank -> fence crafting
                if (hasFenceRecipe) {
                    ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, entry.fenceRecipeName));
                }

                ModHandler.addShapedRecipe(hasFenceRecipe ? prefix + entry.fenceRecipeName : prefix + name + "_fence",
                        entry.fence.copy(),
                        "PSP", "PSP", "PSP",
                        'P', entry.planks.copy(),
                        'S', entry.getStick());
            } else {
                if (!hasFenceRecipe) {
                    ModHandler.addShapedRecipe(prefix + name + "_fence", GTUtility.copy(3, entry.fence),
                            "PSP", "PSP",
                            'P', entry.planks.copy(),
                            'S', entry.getStick());
                }
            }

            // plank -> fence assembling
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(entry.planks.copy())
                    .outputs(entry.fence.copy())
                    .circuitMeta(1)
                    .duration(100).EUt(4)
                    .buildAndRegister();
        }

        // fence gate
        if (!entry.fenceGate.isEmpty()) {
            final boolean hasFenceGateRecipe = entry.fenceGateRecipeName != null;
            if (ConfigHolder.recipes.hardWoodRecipes) {
                // hard plank -> fence gate crafting
                if (hasFenceGateRecipe) {
                    ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, entry.fenceGateRecipeName));
                }

                ModHandler.addShapedRecipe(
                        hasFenceGateRecipe ? prefix + entry.fenceGateRecipeName : prefix + name + "_fence_gate",
                        entry.fenceGate.copy(),
                        "F F", "SPS", "SPS",
                        'P', entry.planks.copy(),
                        'S', entry.getStick(),
                        'F', new ItemStack(Items.FLINT));

                ModHandler.addShapedRecipe(prefix + name + "_fence_gate_screws", GTUtility.copy(2, entry.fenceGate),
                        "IdI", "SPS", "SPS",
                        'P', entry.planks,
                        'S', entry.getStick(),
                        'I', new UnificationEntry(screw, Materials.Iron));
            } else {
                if (!hasFenceGateRecipe) {
                    ModHandler.addShapedRecipe(prefix + name + "_fence_gate", entry.fenceGate.copy(),
                            "SPS", "SPS",
                            'P', entry.planks.copy(),
                            'S', entry.getStick());
                }
            }

            // plank -> fence gate assembling
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(GTUtility.copy(2, entry.planks))
                    .input(entry.getStick().toString(), 2)
                    .outputs(entry.fenceGate.copy())
                    .circuitMeta(2)
                    .duration(100).EUt(4).buildAndRegister();
        }

        // boat
        if (!entry.boat.isEmpty()) {
            final boolean hasBoatRecipe = entry.boatRecipeName != null;
            if (ConfigHolder.recipes.hardWoodRecipes) {
                if (!entry.slab.isEmpty()) {
                    // hard plank -> boat crafting
                    if (hasBoatRecipe) {
                        ModHandler.removeRecipeByName(new ResourceLocation(entry.modid, entry.boatRecipeName));
                    }

                    ModHandler.addShapedRecipe(hasBoatRecipe ? prefix + entry.boatRecipeName : prefix + name + "_boat",
                            entry.boat.copy(),
                            "PHP", "PkP", "SSS",
                            'P', entry.planks.copy(),
                            'S', entry.slab.copy(),
                            'H', new ItemStack(Items.WOODEN_SHOVEL));
                }
            } else {
                if (!hasBoatRecipe) {
                    ModHandler.addShapedRecipe(name + "_boat", entry.boat.copy(),
                            "P P", "PPP",
                            'P', entry.planks.copy());
                }
            }

            // plank -> boat assembling
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                    .inputs(GTUtility.copy(5, entry.planks))
                    .outputs(entry.boat.copy())
                    .circuitMeta(15)
                    .duration(100).EUt(4).buildAndRegister();
        }
    }

    public static void addCuttingRecipe(@NotNull WoodTypeEntry entry) {
        if (!entry.log.isEmpty()) {
            RecipeMaps.CUTTER_RECIPES.recipeBuilder()
                    .inputs(entry.log.copy())
                    .outputs(GTUtility.copy(6, entry.planks))
                    .output(dust, Materials.Wood, 2)
                    .duration(200)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }
    }

    public static void addSawmillRecipe(@NotNull WoodTypeEntry entry) {
        if (!entry.log.isEmpty()) {
            GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .inputs(GTUtility.copy(6, entry.log))
                    .fluidInputs(Materials.Water.getFluid(1000))
                    .outputs(GTUtility.copy(48, entry.planks))
                    .output(dust, Materials.Wood, 12)
                    .duration(600).EUt(VA[LV])
                    .buildAndRegister();
            GTERecipeMaps.SAWMILL_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .inputs(GTUtility.copy(6, entry.log))
                    .fluidInputs(Materials.Water.getFluid(2500))
                    .outputs(GTUtility.copy(60, entry.planks))
                    .duration(800).EUt(VA[LV])
                    .buildAndRegister();
        }
    }

    public static void removePlankRecipe(boolean removeSawRecipes, @NotNull WoodTypeEntry entry,
                                         @NotNull String otherModId) {
        final String name = entry.woodName;
        final String prefix = otherModId.isEmpty() ? entry.modid : otherModId;
        final boolean hasPlanksRecipe = entry.planksRecipeName != null;

        ModHandler.removeRecipeByName(
                new ResourceLocation(prefix, hasPlanksRecipe ? entry.planksRecipeName : name + "_planks"));

        if (!removeSawRecipes) return;
        ModHandler.removeRecipeByName(
                new ResourceLocation(prefix, hasPlanksRecipe ? entry.planksRecipeName + "_saw" : name + "_planks_saw"));
    }
}
