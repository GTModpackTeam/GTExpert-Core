package gtexpert.common.metatileentities;

import java.util.function.Function;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;

import gtexpert.api.gui.GTEGuiTextures;

public class GTESimpleMachineMetaTileEntity extends SimpleMachineMetaTileEntity {

    private static final int FONT_HEIGHT = 9; // Minecraft's FontRenderer FONT_HEIGHT value

    public GTESimpleMachineMetaTileEntity(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap,
                                          ICubeRenderer renderer, int tier, boolean hasFrontFacing) {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing);
    }

    public GTESimpleMachineMetaTileEntity(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap,
                                          ICubeRenderer renderer, int tier, boolean hasFrontFacing,
                                          Function<Integer, Integer> tankScalingFunction) {
        super(metaTileEntityId, recipeMap, renderer, tier, hasFrontFacing, tankScalingFunction);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new GTESimpleMachineMetaTileEntity(metaTileEntityId, workable.getRecipeMap(), renderer, getTier(),
                hasFrontFacing(), getTankScalingFunction());
    }

    protected ModularUI.Builder createGuiTemplate(EntityPlayer player) {
        RecipeMap<?> workableRecipeMap = workable.getRecipeMap();
        int yOffset = 0;
        if (workableRecipeMap.getMaxInputs() >= 6 || workableRecipeMap.getMaxFluidInputs() >= 6 ||
                workableRecipeMap.getMaxOutputs() >= 6 || workableRecipeMap.getMaxFluidOutputs() >= 6) {
            yOffset = FONT_HEIGHT;
        }

        ModularUI.Builder builder = workableRecipeMap
                .createUITemplate(workable::getProgressPercent, importItems, exportItems, importFluids, exportFluids,
                        yOffset)
                .widget(new LabelWidget(5, 5, getMetaFullName()))
                .widget(new SlotWidget(chargerInventory, 0, 79, 62 + yOffset, true, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.CHARGER_OVERLAY)
                        .setTooltipText("gregtech.gui.charger_slot.tooltip", GTValues.VNF[getTier()],
                                GTValues.VNF[getTier()]))
                .widget(new ImageWidget(79, 42 + yOffset, 18, 18, GuiTextures.INDICATOR_NO_ENERGY).setIgnoreColor(true)
                        .setPredicate(workable::isHasNotEnoughEnergy))
                .bindPlayerInventory(player.inventory, GuiTextures.SLOT, yOffset);

        int leftButtonStartX = 7;

        if (exportItems.getSlots() > 0) {
            builder.widget(new ToggleButtonWidget(leftButtonStartX, 62 + yOffset, 18, 18,
                    GuiTextures.BUTTON_ITEM_OUTPUT, this::isAutoOutputItems, this::setAutoOutputItems)
                            .setTooltipText("gregtech.gui.item_auto_output.tooltip")
                            .shouldUseBaseBackground());
            leftButtonStartX += 18;
        }
        if (exportFluids.getTanks() > 0) {
            builder.widget(new ToggleButtonWidget(leftButtonStartX, 62 + yOffset, 18, 18,
                    GuiTextures.BUTTON_FLUID_OUTPUT, this::isAutoOutputFluids, this::setAutoOutputFluids)
                            .setTooltipText("gregtech.gui.fluid_auto_output.tooltip")
                            .shouldUseBaseBackground());
            leftButtonStartX += 18;
        }

        builder.widget(new CycleButtonWidget(leftButtonStartX, 62 + yOffset, 18, 18,
                workable.getAvailableOverclockingTiers(), workable::getOverclockTier, workable::setOverclockTier)
                        .setTooltipHoverString("gregtech.gui.overclock.description")
                        .setButtonTexture(GuiTextures.BUTTON_OVERCLOCK));

        if (exportItems.getSlots() + exportFluids.getTanks() <= 9) {
            ImageWidget logo = new ImageWidget(152, 63 + yOffset, 17, 17,
                    GTValues.XMAS.get() ? GTEGuiTextures.GTE_LOGO_XMAS : GTEGuiTextures.GTE_LOGO).setIgnoreColor(true);

            if (this.circuitInventory != null) {
                SlotWidget circuitSlot = new GhostCircuitSlotWidget(this.circuitInventory, 0, 124, 62 + yOffset)
                        .setBackgroundTexture(GuiTextures.SLOT, this.getCircuitSlotOverlay());
                builder.widget(circuitSlot.setConsumer(this::getCircuitSlotTooltip)).widget(logo);
            }
        }
        return builder;
    }
}
