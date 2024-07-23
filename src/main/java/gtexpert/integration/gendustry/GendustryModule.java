package gtexpert.integration.gendustry;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.VA;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.util.Mods;
import gtexpert.integration.GTEIntegrationSubmodule;
import gtexpert.integration.gendustry.api.recipes.GendustryRecipeMaps;
import gtexpert.integration.gendustry.metatileentities.GendustryMetaTileEntities;
import gtexpert.integration.gendustry.recipes.GendustryBlocksRecipe;
import gtexpert.integration.gendustry.recipes.GendustryItemsRecipe;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_GENDUSTRY,
           containerID = GTEValues.MODID,
           modDependencies = { Mods.Names.FORESTRY, Mods.Names.GENDUSTRY },
           name = "GTExpert Gendustry For Minecraft Integration",
           description = "Gendustry Integration Module")
public class GendustryModule extends GTEIntegrationSubmodule {

    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        GendustryMetaTileEntities.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        GendustryItemsRecipe.init();
        GendustryBlocksRecipe.init();

        // Dummy registration
        GendustryRecipeMaps.INDUSTRIAL_APIARY_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.RedAlloy)
                .output(OrePrefix.dust, Materials.RedAlloy)
                .duration(10).EUt(VA[ULV])
                .buildAndRegister();
    }
}
