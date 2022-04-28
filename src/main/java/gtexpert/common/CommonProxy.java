package gtexpert.common;

import gregtech.api.GregTechAPI;
import gregtech.common.blocks.VariantItemBlock;
import gtexpert.api.unification.material.GTEMaterials;
import gtexpert.loaders.recipe.GTERecipeLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.Function;
import static gtexpert.common.ModBlocks.*;

@Mod.EventBusSubscriber(modid = "gtexpert")
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(gteMetalCasing);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(createItemBlock(gteMetalCasing, VariantItemBlock::new));
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return itemBlock;
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(GregTechAPI.MaterialEvent event) {
        GTEMaterials.init();
    }

    @SubscribeEvent()
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        // Main recipe registration
        // This is called AFTER GregTech registers recipes, so
        // anything here is safe to call removals in
        GTERecipeLoader.init();
    }
}
