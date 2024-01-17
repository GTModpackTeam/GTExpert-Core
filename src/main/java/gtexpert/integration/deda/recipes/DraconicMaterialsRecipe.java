package gtexpert.integration.deda.recipes;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.ore.OrePrefix.*;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import com.brandon3055.draconicevolution.DEFeatures;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;

public class DraconicMaterialsRecipe {

    public static void init() {
        // ########################################
        // Draconic Evolution
        // ########################################
        // Dragon Dust
        RecipeBuilder<?> builderDD = RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Materials.Iridium, 1)
                .fluidInputs(Materials.SaltWater.getFluid(1000))
                .fluidInputs(Materials.EnderEye.getFluid(144))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, GTEMaterials.Dragon, 2)
                .duration(600).EUt(VA[GTEValues.dedaVoltageTier]);
        if (Loader.isModLoaded(GTEValues.MODID_EIO)) {
            builderDD.input(dust, GTEMaterials.EndSteel, 1);
        } else {
            builderDD.input(dust, Materials.Endstone, 1);
        }
        builderDD.buildAndRegister();

        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .input(Blocks.DRAGON_EGG)
                .output(dust, GTEMaterials.Dragon, 8)
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();

        // Chaos Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Dragon, 8)
                .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 8000))
                .fluidInputs(GTEMaterials.AwakenedDraconium.getFluid(1152))
                .cleanroom(CleanroomType.CLEANROOM)
                .output(dust, GTEMaterials.Chaos, 2)
                .duration(1200).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(DEFeatures.chaosShard, 1, 1))
                .output(dust, GTEMaterials.Chaos, 1)
                .duration(200).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Chaos, 1)
                .fluidInputs(Materials.DistilledWater.getFluid(50))
                .output(DEFeatures.chaosShard, 1, 1)
                .duration(1200).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Chaos, 1)
                .fluidInputs(Materials.Water.getFluid(250))
                .chancedOutput(new ItemStack(DEFeatures.chaosShard, 1, 1), 7000, 1000)
                .duration(2400).EUt(VA[GTEValues.dedaVoltageTier + 1])
                .buildAndRegister();

        // Draconium Dust
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.Dragon, 1)
                .input(dust, Materials.Obsidian, 1)
                .fluidInputs(Materials.LiquidEnderAir.getFluid(8000))
                .fluidInputs(Materials.EnderPearl.getFluid(576))
                .output(dust, GTEMaterials.Draconium, 2)
                .duration(100).EUt(VA[GTEValues.dedaVoltageTier])
                .buildAndRegister();

        // Draconium Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconium_block"));
        ModHandler.addMirroredShapedRecipe("de_draconium_block", new ItemStack(DEFeatures.draconiumBlock), "B", 'B',
                OreDictUnifier.get(block, GTEMaterials.Draconium));
        ModHandler.addMirroredShapedRecipe("ceu_draconium_block", OreDictUnifier.get(block, GTEMaterials.Draconium),
                "B", 'B',
                new ItemStack(DEFeatures.draconiumBlock));

        // Awakened Draconium Block
        ModHandler.removeRecipeByName(new ResourceLocation(GTEValues.MODID_DE, "draconic_block"));
        ModHandler.addMirroredShapedRecipe("de_draconic_block", new ItemStack(DEFeatures.draconicBlock), "B", 'B',
                OreDictUnifier.get(block, GTEMaterials.AwakenedDraconium));
        ModHandler.addMirroredShapedRecipe("ceu_draconic_block",
                OreDictUnifier.get(block, GTEMaterials.AwakenedDraconium), "B",
                'B', new ItemStack(DEFeatures.draconicBlock));
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, GTEMaterials.Draconium, 4)
                .output(block, GTEMaterials.AwakenedDraconium, 3)
                .output(dustSmall, Materials.DarkAsh, 1)
                .explosivesAmount(2)
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder()
                .input(DEFeatures.dragonHeart, 1)
                .input(block, GTEMaterials.Draconium, 4)
                .output(block, GTEMaterials.AwakenedDraconium, 3)
                .output(dustSmall, Materials.DarkAsh, 1)
                .explosivesType(MetaItems.DYNAMITE.getStackForm())
                .duration(20).EUt(VA[LV])
                .buildAndRegister();
    }
}
