package gtexpert.loaders.recipe;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.MetaItems;
import gtexpert.api.recipes.GTERecipeMaps;
import gtexpert.common.GTEBlockMetalCasing;
import gtexpert.common.GTEMetaBlocks;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.endergy.init.EndergyObject;
import com.brandon3055.draconicevolution.DEFeatures;
import net.foxmcloud.draconicadditions.DAFeatures;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.common.items.MetaItems.*;
import static gtexpert.api.unification.material.GTEMaterials.*;
import static gtexpert.common.metatileentities.GTEMetaTileEntities.*;

public class DARecipeLoader {
    public static void init() {
        items();
        blocks();
        tools();
    }

    private static void items() {
        // Chaotic Energy Core
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, AWAKENED_DRACONIUM, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polyethylene.getFluid(288))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .duration(400).EUt(614400)
                .buildAndRegister();
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(RANDOM_ACCESS_MEMORY, 48)
                .input(wireFine, AWAKENED_DRACONIUM, 32)
                .fluidInputs(Redstone.getFluid(5184))
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .fluidInputs(Polytetrafluoroethylene.getFluid(144))
                .output(DAFeatures.chaoticEnergyCore, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .duration(400).EUt(614400)
                .buildAndRegister();
    }

    private static void blocks() {
        // Chaos Liquefier
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .inputNBT(DAFeatures.chaosContainer, 1, NBTMatcher.EQUAL_TO, NBTCondition.ANY)
                .input(DEFeatures.infusedObsidian, 4)
                .input(DEFeatures.draconicCore, 4)
                .fluidInputs(CRYOTHEUM.getFluid(18432))
                .output(DAFeatures.chaosLiquefier, 1)
                .fluidOutputs(PYROTHEUM.getFluid(4608))
                .duration(200).EUt(VA[ZPM])
                .buildAndRegister();

        // Chaotic Stability Core
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.reactorCore, 4)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(DEFeatures.infusedObsidian, 4)
                .fluidInputs(PYROTHEUM.getFluid(18432))
                .output(DAFeatures.chaosStabilizerCore, 1)
                .duration(1200).EUt(VA[UHV])
                .buildAndRegister();

        // Capacitor Supplier
        GTERecipeMaps.DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(Blocks.END_ROD, 1)
                .input(plate, STELLAR_ALLOY, 4)
                .input(stick, STELLAR_ALLOY, 4)
                .input(ring, Titanium, 4)
                .fluidInputs(CRYOTHEUM.getFluid(9216))
                .output(DAFeatures.capacitorSupplier, 1)
                .fluidOutputs(PYROTHEUM.getFluid(2304))
                .duration(100).EUt(VA[LuV])
                .buildAndRegister();
    }

    private static void tools() {
        // Chaotic Staff of Power
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicStaffOfPower, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticStaffOfPower, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Bow
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicBow, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticBow, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Helm
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicHelm, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticHelm, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Chestplate
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicChest, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticChest, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Leggings
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicLegs, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticLegs, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaotic Boots
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .input(DEFeatures.draconicBoots, 1)
                .input(DEFeatures.chaosShard, 4, 0)
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaoticBoots, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(600).EUt(VA[UHV])
                .buildAndRegister();

        // Chaos Container
        GTERecipeMaps.AWAKENED_DRACONIUM_FUSION_RECIPES.recipeBuilder()
                .inputNBT(DEFeatures.dislocator, NBTMatcher.EQUAL_TO, NBTCondition.ANY)
                .input(DEFeatures.infusedObsidian, 2)
                .input(DEFeatures.chaoticCore, 1)
                .input(DAFeatures.chaoticEnergyCore, 1)
                .fluidInputs(CRYOTHEUM.getFluid(27648))
                .outputs(new ItemStack(DAFeatures.chaosContainer, 1))
                .fluidOutputs(PYROTHEUM.getFluid(6912))
                .duration(100).EUt(VA[UHV])
                .buildAndRegister();

        // Portable Wired Wyvern Charger
        ModHandler.addShapedRecipe("portable_wired_charger_1", new ItemStack(DAFeatures.pwc, 1, 1),
                "DDD", "ICI", "DDD",
                'D', OreDictUnifier.get(plate, DARK_STEEL),
                'I', OreDictUnifier.get(plate, Iron),
                'C', new ItemStack(DEFeatures.wyvernCore));

        // Portable Wired Wyvern Discharger
        ModHandler.addShapedRecipe("portable_wired_discharger_1", new ItemStack(DAFeatures.pwd, 1, 1),
                "DDD", "GCG", "DDD",
                'D', OreDictUnifier.get(plate, DARK_STEEL),
                'G', OreDictUnifier.get(plate, Gold),
                'C', new ItemStack(DEFeatures.wyvernCore));

        // Wyvern Necklace of Shielding
        ModHandler.addShapedRecipe("wyvern_shield_necklace", new ItemStack(DAFeatures.wyvernShieldNecklace, 1),
                "SSS", "SES", " C ",
                'S', OreDictUnifier.get(stick, RoseGold),
                'E', new ItemStack(DEFeatures.wyvernEnergyCore),
                'C', new ItemStack(DEFeatures.wyvernCore));

        // Draconic Belt of Overloading
        ModHandler.addShapedRecipe("overload_belt", new ItemStack(DAFeatures.overloadBelt, 1),
                "SSS", "SLS", " C ",
                'S', OreDictUnifier.get(stick, DRACONIUM),
                'C', new ItemStack(DEFeatures.awakenedCore),
                'L', Items.LEATHER);

        // Ring of Inertia Cancellation
        ModHandler.addShapedRecipe("inertia_cancel_ring", new ItemStack(DAFeatures.inertiaCancelRing, 1),
                "SES", "E E", "SES",
                'S', OreDictUnifier.get(stick, RoseGold),
                'E', new ItemStack(DEFeatures.wyvernEnergyCore));
    }
}
