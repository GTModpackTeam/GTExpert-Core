package com.github.gtexpert.core.integration.chisel.tools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import gregtech.api.GTValues;
import gregtech.api.items.toolitem.IGTTool;
import gregtech.api.items.toolitem.ItemGTTool;
import gregtech.api.unification.OreDictUnifier;

import com.github.gtexpert.core.api.GTEValues;

public final class ChiselToolItems {

    private static final List<IGTTool> TOOLS = new ArrayList<>();

    public static IGTTool CHISEL;

    private ChiselToolItems() {}

    public static List<IGTTool> getAllTools() {
        return TOOLS;
    }

    public static void init() {
        CHISEL = register(ItemGTTool.Builder.of(GTEValues.MODID, "chisel")
                .toolStats(b -> b.cannotAttack().attackSpeed(-2.4F).durabilityMultiplier(2.0F))
                .oreDict("toolChisel")
                .secondaryOreDicts("craftingToolChisel")
                .toolClasses("chisel")
                .build());
    }

    public static IGTTool register(IGTTool tool) {
        TOOLS.add(tool);
        return tool;
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        TOOLS.forEach(tool -> ModelLoader.setCustomModelResourceLocation(tool.get(), 0, tool.getModelLocation()));
    }

    @SideOnly(Side.CLIENT)
    public static void registerColors() {
        TOOLS.forEach(
                tool -> Minecraft.getMinecraft().getItemColors().registerItemColorHandler(tool::getColor, tool.get()));
    }

    public static void registerOreDict() {
        TOOLS.forEach(tool -> {
            final ItemStack stack = new ItemStack(tool.get(), 1, GTValues.W);
            if (tool.getOreDictName() != null) {
                OreDictUnifier.registerOre(stack, tool.getOreDictName());
            }
            tool.getSecondaryOreDicts().forEach(oreDict -> OreDictUnifier.registerOre(stack, oreDict));
        });
    }
}
