package gtexpert.integration.chisel;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.VH;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.GTEUtility;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.chisel.metatileentities.ChiselMetaTileEntities;
import gtexpert.integration.chisel.recipes.ChiselBlocksRecipe;
import gtexpert.integration.chisel.recipes.ChiselToolsRecipe;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_CHISEL,
           containerID = GTEValues.MODID,
           modDependencies = GTEValues.MODID_CHISEL,
           name = "GTExpert Chisel Module")
public class ChiselModule extends GTEIntegrationSubmodule {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ChiselMetaTileEntities.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        // craftChisel
        OreDictionary.registerOre("craftChisel", GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_iron"));
        OreDictionary.registerOre("craftChisel", GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_diamond"));
        OreDictionary.registerOre("craftChisel", GTEUtility.getModItem(GTEValues.MODID_CHISEL, "chisel_hitech"));

        ChiselBlocksRecipe.init();
        ChiselToolsRecipe.init();
    }

    private static void registerAutoChiselRecipe(String oreDictName) {
        List<ItemStack> targets = OreDictionary.getOres(oreDictName);
        targets.forEach(target -> ChiselRecipeMaps.AUTO_CHISEL_RECIPES.recipeBuilder()
                .input(oreDictName)
                .notConsumable(target)
                .outputs(target)
                .duration(10).EUt(VH[ULV])
                .hidden().buildAndRegister());
    }
}
