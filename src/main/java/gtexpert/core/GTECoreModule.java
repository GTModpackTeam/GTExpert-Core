package gtexpert.core;

import static gregtech.api.GregTechAPI.HEATING_COILS;
import static gtexpert.common.blocks.GTEMetaBlocks.GTE_WIRE_COIL;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import gregtech.loaders.recipe.RecyclingRecipes;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.modules.IGTEModule;
import gtexpert.common.CommonProxy;
import gtexpert.common.blocks.GTEBlockWireCoil;
import gtexpert.common.blocks.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.core.loaders.GTEMaterialInfoLoader;
import gtexpert.core.loaders.GTEOreDictionaryLoader;
import gtexpert.core.metatileentities.GTEMetaTileEntities;
import gtexpert.core.recipes.*;
import gtexpert.modules.GTEModules;

@GTEModule(
           moduleID = GTEModules.MODULE_CORE,
           containerID = GTEValues.MODID,
           name = "GTExpert Core",
           description = "Core GTExpert content.",
           coreModule = true)
public class GTECoreModule implements IGTEModule {

    public static final Logger logger = LogManager.getLogger("GTExpert Core");

    @SidedProxy(modId = GTEValues.MODID,
                clientSide = "gtexpert.client.ClientProxy",
                serverSide = "gtexpert.common.CommonProxy")
    public static CommonProxy proxy;

    @Override
    public @NotNull Logger getLogger() {
        return logger;
    }

    @Override
    public void construction(FMLConstructionEvent event) {}

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

        GTEMetaBlocks.init();
        GTEMetaItems.init();

        /* Start API Block Registration */
        for (GTEBlockWireCoil.GTECoilType type : GTEBlockWireCoil.GTECoilType.values()) {
            HEATING_COILS.put(GTE_WIRE_COIL.getState(type), type);
        }
        /* End API Block Registration */

        GTEMetaTileEntities.init();
    }

    @Override
    public void init(FMLInitializationEvent event) {}

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        RecyclingRecipes.init();
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        GTEMaterialInfoLoader.init();
        GTEOreDictionaryLoader.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        GTERecipe.init();
        GTEWoodRecipe.init();
        CEUOverrideRecipe.init();
        VanillaOverrideRecipes.init();
    }
}
