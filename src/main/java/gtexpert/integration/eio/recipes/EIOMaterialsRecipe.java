package gtexpert.integration.eio.recipes;

import static gregtech.api.GTValues.VA;
import static gregtech.api.unification.ore.OrePrefix.dust;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.common.ConfigHolder;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;

public class EIOMaterialsRecipe {

    public static void init() {
        // Soul Sand Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.SOUL_SAND))
                .outputs(OreDictUnifier.get(dust, GTEMaterials.SoulSand))
                .duration(25)
                .EUt(2)
                .buildAndRegister();

        // Chorus fruit Dust
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.CHORUS_FRUIT))
                .outputs(OreDictUnifier.get(dust, GTEMaterials.ChorusFruit))
                .duration(25)
                .EUt(2)
                .buildAndRegister();

        // Electrical Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Steel, 1)
                .input(dust, Materials.Coal, 1)
                .input(dust, Materials.Silicon, 1)
                .output(dust, GTEMaterials.ElectricalSteel, 3)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Energetic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.Gold, 1)
                .input(dust, Materials.Glowstone, 1)
                .output(dust, GTEMaterials.EnergeticAlloy, 3)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Vibrant Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.EnergeticAlloy, 1)
                .input(dust, Materials.EnderPearl, 1)
                .output(dust, GTEMaterials.VibrantAlloy, 2)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Redstone Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.RedAlloy, 1)
                .input(dust, Materials.Silicon, 1)
                .output(dust, GTEMaterials.RedstoneAlloy, 2)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Conductive Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Iron, 1)
                .input(dust, GTEMaterials.RedstoneAlloy, 1)
                .output(dust, GTEMaterials.ConductiveIron, 2)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Pulsating Iron
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Iron, 1)
                .input(dust, Materials.EnderPearl, 1)
                .output(dust, GTEMaterials.PulsatingIron, 2)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Dark Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Iron, 1)
                .input(dust, Materials.Coal, 1)
                .input(dust, Materials.Obsidian, 1)
                .output(dust, GTEMaterials.DarkSteel, 3)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // Soularium
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Gold, 1)
                .input(dust, Materials.Ash, 1)
                .input(dust, GTEMaterials.SoulSand, 1)
                .output(dust, GTEMaterials.Soularium, 3)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier])
                .buildAndRegister();

        // End Steel
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Endstone, 1)
                .input(dust, GTEMaterials.DarkSteel, 1)
                .input(dust, Materials.Obsidian, 1)
                .output(dust, GTEMaterials.EndSteel, 3)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier + 1])
                .buildAndRegister();

        // Iron Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Platinum, 1)
                .input(dust, Materials.Iron, 1)
                .input(dust, Materials.Aluminium, 1)
                .output(dust, GTEMaterials.ConstructionAlloy, 3)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier + 1])
                .buildAndRegister();

        // Crystalline Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                // .input(ModObject.itemMaterial.getItemNN(), 1, 34)
                .input(dust, Materials.Gold, 1)
                .input(dust, Materials.Platinum, 1)
                .input(dust, Materials.Emerald, 1)
                .input(dust, GTEMaterials.VibrantAlloy, 1)
                .output(dust, GTEMaterials.CrystallineAlloy, 4)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier + 1])
                .buildAndRegister();

        // Melodic Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.EndSteel, 1)
                .input(dust, GTEMaterials.ChorusFruit, 1)
                .output(dust, GTEMaterials.MelodicAlloy, 2)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier + 1])
                .buildAndRegister();

        // Stellar Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.NetherStar, 1)
                .input(dust, GTEMaterials.MelodicAlloy, 1)
                .input(dust, Materials.Clay, 1)
                .output(dust, GTEMaterials.StellarAlloy, 3)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier + 3])
                .buildAndRegister();

        // Crystalline Pink Slime
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.MelodicAlloy, 1)
                .input(dust, Materials.RawRubber, 2)
                .output(dust, GTEMaterials.CrystallinePinkSlime, 2)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier + 1])
                .buildAndRegister();

        // Energetic Silver
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, Materials.Silver, 1)
                .input(dust, Materials.Redstone, 1)
                .input(dust, Materials.Glowstone, 1)
                .output(dust, GTEMaterials.EnergeticSilver, 3)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier + 1])
                .buildAndRegister();

        // Vivid Alloy
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .input(dust, GTEMaterials.EnergeticSilver, 1)
                .input(dust, Materials.EnderPearl, 1)
                .output(dust, GTEMaterials.VividAlloy, 2)
                .duration(40).EUt(VA[GTEValues.eioVoltageTier + 1])
                .buildAndRegister();
    }

    public static void remove() {
        if (ConfigHolder.recipes.disableManualCompression) {}
    }
}
