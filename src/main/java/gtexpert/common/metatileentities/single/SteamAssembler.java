package gtexpert.common.metatileentities.single;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.client.renderer.texture.Textures;

import gtexpert.api.recipes.GTERecipeMaps;

public class SteamAssembler extends SteamMetaTileEntity {

    public SteamAssembler(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, GTERecipeMaps.STEAM_ASSEMBLER_RECIPES, Textures.ASSEMBLER_OVERLAY, isHighPressure);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new SteamAssembler(metaTileEntityId, isHighPressure);
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager) {
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtexpert.machine.steam_assembler.tooltip"));
        tooltip.add(I18n.format("gtexpert.machine.steam_assembler.tooltip.1"));
    }
}
