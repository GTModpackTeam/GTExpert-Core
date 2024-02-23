package gtexpert.integration.deda.recipes;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.ore.OrePrefix.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;

import com.brandon3055.draconicevolution.DEFeatures;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.BlastRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.MetaItems;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregicality.multiblocks.api.recipes.GCYMRecipeMaps;
import gregicality.multiblocks.api.unification.GCYMMaterialFlags;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;

import gtexpert.api.GTEValues;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.api.util.Mods;

public class DraconicMaterialsRecipe {

    private static final int ABFPyrotheumAmount = 200;
    private static final double ABFDurationMultiplier = 0.5;

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
        if (Mods.EnderIO.isModLoaded()) {
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
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconium_block"));
        ModHandler.addMirroredShapedRecipe("de_draconium_block", new ItemStack(DEFeatures.draconiumBlock), "B", 'B',
                OreDictUnifier.get(block, GTEMaterials.Draconium));
        ModHandler.addMirroredShapedRecipe("ceu_draconium_block", OreDictUnifier.get(block, GTEMaterials.Draconium),
                "B", 'B',
                new ItemStack(DEFeatures.draconiumBlock));

        // Awakened Draconium Block
        ModHandler.removeRecipeByName(Mods.DraconicEvolution.getResource("draconic_block"));
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

        // Extended recipes
        List<Material> materials = new ArrayList<>(GregTechAPI.materialManager.getRegisteredMaterials());
        materials.forEach(DraconicMaterialsRecipe::vacuumFreezerExtended);
        materials.forEach(DraconicMaterialsRecipe::alloyBlastFurnaceExtended);
    }

    /**
     * Vacuum Freezer to extended recipes
     *
     * @param material The material to add recipes for
     */
    private static void vacuumFreezerExtended(@NotNull Material material) {
        // Do not generate for disabled materials
        if (material.hasFlag(GCYMMaterialFlags.NO_ALLOY_BLAST_RECIPES)) return;

        // Check if the material has a blast recipe
        if (!material.hasProperty(GCYMPropertyKey.ALLOY_BLAST)) return;

        // Check if the material has a molten fluid
        Fluid molten = material.getFluid(GCYMFluidStorageKeys.MOLTEN);
        if (molten == null) return;

        // Get the vacuum freezer EUt and duration
        BlastProperty property = material.getProperty(PropertyKey.BLAST);
        int vacuumEUt = property.getVacuumEUtOverride() != -1 ? property.getVacuumEUtOverride() : VA[MV];
        int vacuumDuration = property.getVacuumDurationOverride() != -1 ? property.getVacuumDurationOverride() :
                (int) (material.getMass() * 0.5);

        // Check if the material has a blast temperature above 5000K
        if (property.getBlastTemperature() > 5000) {
            if (material.hasFlag(GENERATE_PLATE)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_PLATE)
                        .fluidInputs(new FluidStack(molten, 144))
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                        .output(plate, material, 1)
                        .duration(vacuumDuration / 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            if (material.hasFlag(GENERATE_SMALL_GEAR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_GEAR_SMALL)
                        .fluidInputs(new FluidStack(molten, 144))
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                        .output(gearSmall, material, 1)
                        .duration(vacuumDuration / 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            if (material.hasFlag(GENERATE_GEAR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_GEAR)
                        .fluidInputs(new FluidStack(molten, 576))
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(1000))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 200))
                        .output(gear, material, 1)
                        .duration(vacuumDuration * 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            if (material.hasFlag(GENERATE_ROTOR)) {
                RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                        .notConsumable(MetaItems.SHAPE_MOLD_ROTOR)
                        .fluidInputs(new FluidStack(molten, 576))
                        .fluidInputs(GTEMaterials.Cryotheum.getFluid(1000))
                        .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 200))
                        .output(rotor, material, 1)
                        .duration(vacuumDuration / 2)
                        .EUt(vacuumEUt)
                        .buildAndRegister();
            }
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .input(ingotHot, material, 1)
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                    .output(ingot, material, 1)
                    .duration(vacuumDuration / 2)
                    .EUt(vacuumEUt)
                    .buildAndRegister();
            RecipeMaps.VACUUM_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .fluidInputs(new FluidStack(molten, 144))
                    .fluidInputs(GTEMaterials.Cryotheum.getFluid(250))
                    .fluidOutputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, 50))
                    .fluidOutputs(material.getFluid(144))
                    .duration(vacuumDuration / 2)
                    .EUt(vacuumEUt)
                    .buildAndRegister();
        }
    }

    private static void alloyBlastFurnaceExtended(Material material) {
        // Do not generate for disabled materials
        if (material.hasFlag(GCYMMaterialFlags.NO_ALLOY_BLAST_RECIPES)) return;

        // Check if the material has a blast recipe
        if (!material.hasProperty(GCYMPropertyKey.ALLOY_BLAST)) return;

        // Check if the material has a molten fluid
        Fluid molten = material.getFluid(GCYMFluidStorageKeys.MOLTEN);
        if (molten == null) return;

        // Get the vacuum freezer EUt and duration
        BlastProperty property = material.getProperty(PropertyKey.BLAST);

        produce(material, property);
    }

    /**
     * Generates alloy blast recipes for a material
     *
     * @param material      the material to generate for
     * @param blastProperty the blast property of the material
     */
    private static void produce(@NotNull Material material, @NotNull BlastProperty blastProperty) {
        final int componentAmount = material.getMaterialComponents().size();

        // ignore non-alloys
        if (componentAmount < 2) return;

        // get the output fluid
        Fluid molten = material.getFluid(GCYMFluidStorageKeys.MOLTEN);
        if (molten == null) return;

        RecipeBuilder<BlastRecipeBuilder> builder = createBuilder(blastProperty, material);

        int outputAmount = addInputs(material, builder);
        if (outputAmount <= 0) return;

        buildRecipes(blastProperty, molten, outputAmount, componentAmount, builder);
    }

    /**
     * Creates the recipeBuilder with duration and EUt
     *
     * @param property the blast property of the material
     * @param material the material
     * @return the builder
     */
    @SuppressWarnings("MethodMayBeStatic")
    private static @NotNull BlastRecipeBuilder createBuilder(@NotNull BlastProperty property,
                                                             @NotNull Material material) {
        BlastRecipeBuilder builder = GCYMRecipeMaps.ALLOY_BLAST_RECIPES.recipeBuilder();
        // apply the duration override
        int duration = property.getDurationOverride();
        if (duration < 0) duration = Math.max(1, (int) (material.getMass() * property.getBlastTemperature() / 100L));
        builder.duration(duration);

        // apply the EUt override
        int EUt = property.getEUtOverride();
        if (EUt < 0) EUt = GTValues.VA[GTValues.MV];
        builder.EUt(EUt);

        return builder.blastFurnaceTemp(property.getBlastTemperature());
    }

    /**
     * @param material the material to start recipes for
     * @param builder  the recipe builder to append to
     * @return the outputAmount if the recipe is valid, otherwise -1
     */
    private static int addInputs(@NotNull Material material, @NotNull RecipeBuilder<BlastRecipeBuilder> builder) {
        // calculate the output amount and add inputs
        int outputAmount = 0;
        int fluidAmount = 0;
        for (MaterialStack materialStack : material.getMaterialComponents()) {
            final Material msMat = materialStack.material;
            final int msAmount = (int) materialStack.amount;

            if (msMat.hasProperty(PropertyKey.DUST)) {
                builder.input(OrePrefix.dust, msMat, msAmount);
            } else if (msMat.hasProperty(PropertyKey.FLUID)) {
                if (fluidAmount >= 2) return -1; // more than 2 fluids won't fit in the machine
                fluidAmount++;
                // assume all fluids have 1000mB/mol, since other quantities should be as an item input
                builder.fluidInputs(msMat.getFluid(1000 * msAmount));
            } else return -1; // no fluid or item prop means no valid recipe
            outputAmount += msAmount;
        }
        return outputAmount;
    }

    /**
     * Builds the alloy blast recipes
     *
     * @param property        the blast property to utilize
     * @param molten          the molten fluid
     * @param outputAmount    the amount of material to output
     * @param componentAmount the amount of different components in the material
     * @param builder         the builder to continue
     */
    private static void buildRecipes(@NotNull BlastProperty property, @NotNull Fluid molten, int outputAmount,
                                     int componentAmount,
                                     @NotNull RecipeBuilder<BlastRecipeBuilder> builder) {
        // add the fluid output with the correct amount
        builder.fluidOutputs(new FluidStack(molten, GTValues.L * outputAmount));

        // apply alloy blast duration reduction: 3/4
        int duration = builder.getDuration() * outputAmount * 3 / 4;

        // build the gas recipe if it exists
        if (property.getGasTier() != null) {
            RecipeBuilder<BlastRecipeBuilder> builderGas = builder.copy();
            builderGas.notConsumable(new IntCircuitIngredient(getGasCircuitNum(componentAmount)))
                    .fluidInputs(GTEMaterials.Pyrotheum.getFluid(GCYMFluidStorageKeys.MOLTEN, ABFPyrotheumAmount))
                    .duration((int) (duration * 0.67 * ABFDurationMultiplier))
                    .buildAndRegister();
        }

        // build the non-gas recipe
        builder.notConsumable(new IntCircuitIngredient(getCircuitNum(componentAmount)))
                .duration(duration)
                .buildAndRegister();
    }

    /**
     * @param componentAmount the amount of different components in the material
     * @return the circuit number for the regular recipe
     */
    private static int getCircuitNum(int componentAmount) {
        return componentAmount;
    }

    /**
     * @param componentAmount the amount of different components in the material
     * @return the circuit number for the gas-boosted recipe
     */
    private static int getGasCircuitNum(int componentAmount) {
        return componentAmount + 11;
    }
}
