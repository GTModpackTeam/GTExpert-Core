package gtexpert.core;

import static gregtech.api.GregTechAPI.HEATING_COILS;
import static gtexpert.GTExpertMod.createItemBlock;
import static gtexpert.common.blocks.GTEMetaBlocks.*;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import gregtech.api.block.VariantItemBlock;
import gregtech.api.recipes.RecipeMaps;
import gregtech.loaders.recipe.RecyclingRecipes;

import gtexpert.api.GTEValues;
import gtexpert.api.modules.GTEModule;
import gtexpert.api.modules.IGTEModule;
import gtexpert.common.CommonProxy;
import gtexpert.common.blocks.GTEBlockWireCoil;
import gtexpert.common.blocks.GTEMetaBlocks;
import gtexpert.common.items.GTEMetaItems;
import gtexpert.common.items.GTEToolItems;
import gtexpert.common.metatileentities.GTEMetaTileEntities;
import gtexpert.loaders.GTEMaterialInfoLoader;
import gtexpert.loaders.GTEOreDictionaryLoader;
import gtexpert.loaders.recipe.CEUOverrideRecipe;
import gtexpert.loaders.recipe.GTERecipe;
import gtexpert.loaders.recipe.GTEVanillaOverrideRecipes;
import gtexpert.loaders.recipe.GTEWoodRecipe;
import gtexpert.loaders.recipe.handlers.GTEToolRecipeHandler;
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
        GTEToolItems.init();

        /* Start API Block Registration */
        for (GTEBlockWireCoil.GTECoilType type : GTEBlockWireCoil.GTECoilType.values()) {
            HEATING_COILS.put(GTE_WIRE_COIL.getState(type), type);
        }
        /* End API Block Registration */
    }

    @Override
    public void init(FMLInitializationEvent event) {}

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        RecyclingRecipes.init();
    }

    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(GTE_WIRE_COIL);
        registry.register(GTE_METAL_CASING);
        registry.register(BLOCK_SAWMILL_CONVEYOR);
    }

    @Override
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        // TODO Add preLoad to RecipeManager
        RecipeMaps.VACUUM_RECIPES.setMaxFluidOutputs(2);

        registry.register(createItemBlock(GTE_WIRE_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(GTE_METAL_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(BLOCK_SAWMILL_CONVEYOR, ItemBlock::new));
    }

    @Override
    public void registerRecipesNormal(RegistryEvent.Register<IRecipe> event) {
        GTEToolRecipeHandler.register();
        GTEMaterialInfoLoader.init();
        GTEOreDictionaryLoader.init();
        GTEMetaTileEntities.init();
    }

    @Override
    public void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        GTERecipe.init();
        GTEWoodRecipe.init();
        CEUOverrideRecipe.init();
        GTEVanillaOverrideRecipes.init();
    }
}
