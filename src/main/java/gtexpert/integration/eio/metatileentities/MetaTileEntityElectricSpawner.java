package gtexpert.integration.eio.metatileentities;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.DoubleSupplier;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;

import org.jetbrains.annotations.Nullable;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.capability.impl.RecipeLogicEnergy;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.IGuiTexture;
import gregtech.api.gui.resources.ItemStackTexture;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.LocalizationUtils;
import gregtech.api.util.Position;
import gregtech.api.util.Size;
import gregtech.client.renderer.ICubeRenderer;

import gtexpert.api.gui.GTEGuiTextures;
import gtexpert.api.util.GTEUtility;
import gtexpert.api.util.Mods;
import gtexpert.core.metatileentities.GTESimpleMachineMetaTileEntity;

public class MetaTileEntityElectricSpawner extends GTESimpleMachineMetaTileEntity {

    private static IGuiTexture spawnEggTexture;
    private static IGuiTexture soulVialTexture;
    private static IGuiTexture redstoneTorchTexture;

    public MetaTileEntityElectricSpawner(ResourceLocation metaTileEntityId, ICubeRenderer renderer, int tier) {
        super(metaTileEntityId, null, renderer, tier, true);
    }

    @Override
    protected RecipeLogicEnergy createWorkable(RecipeMap<?> recipeMap) {
        return new ElectricSpawnerLogic(this, () -> energyContainer);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityElectricSpawner(metaTileEntityId, renderer, getTier());
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new NotifiableItemStackHandler(this, 2, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(this, 1, this, true);
    }

    @Override
    protected FluidTankList createImportFluidHandler() {
        return new FluidTankList(false);
    }

    @Override
    protected FluidTankList createExportFluidHandler() {
        return new FluidTankList(false);
    }

    @Override
    public boolean hasGhostCircuitInventory() {
        return false;
    }

    @Override
    public SoundEvent getSound() {
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        // prevent NPE with `WorkableTieredMetaTileEntity#addInformation`
        tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_in", energyContainer.getInputVoltage(),
                GTValues.VNF[getTier()]));
        tooltip.add(
                I18n.format("gregtech.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
        String key = this.metaTileEntityId.getPath().split("\\.")[0];
        String mainKey = String.format("gregtech.machine.%s.tooltip", key);
        if (I18n.hasKey(mainKey)) {
            tooltip.add(1, mainKey);
        }
    }

    private ElectricSpawnerLogic getLogic() {
        return (ElectricSpawnerLogic) workable;
    }

    public String getSpawnModeTranslationKey() {
        return getSpawnModeTranslationKey(getLogic().getSpawnMode());
    }

    private String getSpawnModeTranslationKey(boolean spawnMode) {
        String mode = spawnMode ? "spawn" : "capture";
        return "gtexpert.gui.electric_spawner_mode." + mode;
    }

    public boolean needsRedstone() {
        return getLogic().needsRedstone();
    }

    @Override
    protected ModularUI.Builder createGuiTemplate(EntityPlayer player) {
        if (spawnEggTexture == null) {
            spawnEggTexture = new ItemStackTexture(Items.SPAWN_EGG);
        }
        if (soulVialTexture == null) {
            soulVialTexture = new ItemStackTexture(GTEUtility.getModItem(Mods.Names.ENDER_IO, "item_soul_vial"));
        }
        if (redstoneTorchTexture == null) {
            redstoneTorchTexture = new ItemStackTexture(new ItemStack(Objects.requireNonNull(Blocks.REDSTONE_TORCH)));
        }

        int yOffset = 0;

        ModularUI.Builder builder = createUITemplate(workable::getProgressPercent, importItems, exportItems, yOffset)
                .widget(new LabelWidget(5, 5, getMetaFullName()))
                .widget(new SlotWidget(chargerInventory, 0, 79, 62 + yOffset, true, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.CHARGER_OVERLAY)
                        .setTooltipText("gregtech.gui.charger_slot.tooltip", GTValues.VNF[getTier()],
                                GTValues.VNF[getTier()]))
                .widget(new ImageWidget(79, 42 + yOffset, 18, 18, GuiTextures.INDICATOR_NO_ENERGY).setIgnoreColor(true)
                        .setPredicate(workable::isHasNotEnoughEnergy))
                .bindPlayerInventory(player.inventory, GuiTextures.SLOT, yOffset);

        int leftButtonStartX = 7;

        builder.widget(new ToggleButtonWidget(leftButtonStartX, 62 + yOffset, 18, 18,
                GuiTextures.BUTTON_ITEM_OUTPUT, this::isAutoOutputItems, this::setAutoOutputItems)
                        .setTooltipText("gregtech.gui.item_auto_output.tooltip")
                        .shouldUseBaseBackground());
        leftButtonStartX += 18;

        builder.widget(new CycleButtonWidget(leftButtonStartX, 62 + yOffset, 18, 18,
                workable.getAvailableOverclockingTiers(), workable::getOverclockTier, workable::setOverclockTier)
                        .setTooltipHoverString("gregtech.gui.overclock.description")
                        .setButtonTexture(GuiTextures.BUTTON_OVERCLOCK));
        leftButtonStartX += 18;

        ImageWidget logo = new ImageWidget(152, 63 + yOffset, 17, 17,
                GTValues.XMAS.get() ? GTEGuiTextures.GTE_LOGO_XMAS : GTEGuiTextures.GTE_LOGO).setIgnoreColor(true);
        builder.widget(logo);

        builder.widget(new ToggleButtonWidget(leftButtonStartX, 62 + yOffset, 18, 18,
                () -> getLogic().getSpawnMode(),
                mode -> getLogic().setSpawnMode(mode)) {

            @Override
            public void drawInBackground(int mouseX, int mouseY, float partialTicks, IRenderContext context) {
                // Don't call super not to crop texture
                Position pos = getPosition();
                Size size = getSize();
                // button
                // TODO: weird rendering while capture mode
                GuiTextures.BUTTON_OVERCLOCK.draw(pos.x, pos.y, size.width, size.height);
                // icon overlay
                IGuiTexture overlay = isPressed ? spawnEggTexture : soulVialTexture;
                overlay.draw(pos.x + 0.5, pos.y + 0.5, size.width - 1, size.height - 1);
            }

            // dynamic tooltip
            @Override
            public void drawInForeground(int mouseX, int mouseY) {
                if (!isMouseOverElement(mouseX, mouseY)) return;
                List<String> hoverList = Arrays.asList(LocalizationUtils.formatLines(getSpawnModeTranslationKey()));
                drawHoveringText(ItemStack.EMPTY, hoverList, 300, mouseX, mouseY);
            }
        });
        leftButtonStartX += 18;

        builder.widget(new ImageWidget(leftButtonStartX, 62 + yOffset, 16, 16) {

            @Override
            public void drawInBackground(int mouseX, int mouseY, float partialTicks, IRenderContext context) {
                if (!needsRedstone()) return;
                Position position = getPosition();
                Size size = getSize();
                redstoneTorchTexture.draw(position.x, position.y, size.width, size.height);
            }

            @Override
            public void drawInForeground(int mouseX, int mouseY) {
                if (!isMouseOverElement(mouseX, mouseY) || !needsRedstone()) return;
                List<String> hoverList = Arrays.asList(LocalizationUtils.formatLines("gtexpert.gui.needs_redstone"));
                drawHoveringText(ItemStack.EMPTY, hoverList, 300, mouseX, mouseY);
            }
        });

        return builder;
    }

    // === Copied from RecipeMap with some adjustments ===

    private ModularUI.Builder createUITemplate(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems,
                                               IItemHandlerModifiable exportItems, int yOffset) {
        ModularUI.Builder builder = ModularUI.defaultBuilder(yOffset);
        builder.widget(new RecipeProgressWidget(progressSupplier, 78, 23 + yOffset, 20, 20,
                GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL, null) {

            // Disable "Show Recipes" tooltip
            @Override
            public void drawInForeground(int mouseX, int mouseY) {}
        });
        addInventorySlotGroup(builder, importItems, false, yOffset);
        addInventorySlotGroup(builder, exportItems, true, yOffset);
        return builder;
    }

    private void addInventorySlotGroup(ModularUI.Builder builder, IItemHandlerModifiable itemHandler, boolean isOutputs,
                                       int yOffset) {
        int itemInputsCount = itemHandler.getSlots();
        int[] inputSlotGrid = determineSlotsGrid(itemInputsCount);
        int itemSlotsToLeft = inputSlotGrid[0];
        int itemSlotsToDown = inputSlotGrid[1];
        int startInputsX = isOutputs ? 106 : 70 - itemSlotsToLeft * 18;
        int startInputsY = 33 - (int) (itemSlotsToDown / 2.0 * 18) + yOffset;
        boolean wasGroup = itemHandler.getSlots() == 12;
        if (wasGroup) startInputsY -= 9;
        for (int i = 0; i < itemSlotsToDown; i++) {
            for (int j = 0; j < itemSlotsToLeft; j++) {
                int slotIndex = i * itemSlotsToLeft + j;
                if (slotIndex >= itemInputsCount) break;
                int x = startInputsX + 18 * j;
                int y = startInputsY + 18 * i;
                addSlot(builder, x, y, slotIndex, itemHandler, isOutputs);
            }
        }
    }

    private void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex, IItemHandlerModifiable itemHandler,
                         boolean isOutputs) {
        builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
                .setBackgroundTexture(getOverlaysForSlot(isOutputs, slotIndex == itemHandler.getSlots() - 1)));
    }

    private TextureArea[] getOverlaysForSlot(boolean isOutput, boolean isLast) {
        TextureArea base = GuiTextures.SLOT;
        TextureArea overlay = (!isOutput && isLast) ? GTEGuiTextures.SOULVIAL_EMPTY_OVRELAY :
                GTEGuiTextures.SOULVIAL_FULL_OVERLAY;
        return new TextureArea[] { base, overlay };
    }

    private static int[] determineSlotsGrid(int itemInputsCount) {
        int itemSlotsToLeft;
        int itemSlotsToDown;
        double sqrt = Math.sqrt(itemInputsCount);
        // if the number of input has an integer root
        // return it.
        if (sqrt % 1 == 0) {
            itemSlotsToLeft = itemSlotsToDown = (int) sqrt;
        } else if (itemInputsCount == 3) {
            itemSlotsToLeft = 3;
            itemSlotsToDown = 1;
        } else {
            // if we couldn't fit all into a perfect square,
            // increase the amount of slots to the left
            itemSlotsToLeft = (int) Math.ceil(sqrt);
            itemSlotsToDown = itemSlotsToLeft - 1;
            // if we still can't fit all the slots in a grid,
            // increase the amount of slots on the bottom
            if (itemInputsCount > itemSlotsToLeft * itemSlotsToDown) {
                itemSlotsToDown = itemSlotsToLeft;
            }
        }
        return new int[] { itemSlotsToLeft, itemSlotsToDown };
    }
}
