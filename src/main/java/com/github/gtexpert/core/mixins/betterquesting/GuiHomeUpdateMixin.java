package com.github.gtexpert.core.mixins.betterquesting;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import betterquesting.api.storage.BQ_Settings;
import betterquesting.api2.client.gui.controls.IPanelButton;
import betterquesting.api2.client.gui.events.PanelEvent;
import betterquesting.api2.client.gui.events.types.PEventButton;
import betterquesting.client.gui2.GuiHome;
import betterquesting.commands.admin.QuestCommandDefaults;
import betterquesting.handlers.SaveLoadHandler;

/**
 * Fixes the "Update Available!" button on the BetterQuesting home screen.
 * The original handler only checks for the legacy single-file format (DefaultQuests.json),
 * silently ignoring the directory format (DefaultQuests/) used by newer modpacks.
 * This mixin adds support for the directory format by delegating to
 * {@link QuestCommandDefaults#load}, matching the behavior of {@code /bq_admin default load}.
 */
@Mixin(value = GuiHome.class, remap = false)
public class GuiHomeUpdateMixin {

    @Inject(method = "onButtonPress", at = @At("HEAD"), cancellable = true)
    private void gtexpert$fixUpdateButton(PanelEvent event, CallbackInfo ci) {
        if (!(event instanceof PEventButton)) return;

        IPanelButton btn = ((PEventButton) event).getButton();
        if (btn.getButtonID() != 5) return;

        File dataDir = new File(BQ_Settings.defaultDir, QuestCommandDefaults.DEFAULT_FILE);
        File legacyFile = new File(BQ_Settings.defaultDir, QuestCommandDefaults.DEFAULT_FILE + ".json");

        if (dataDir.exists()) {
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                QuestCommandDefaults.load(null, null, dataDir, false);
                SaveLoadHandler.INSTANCE.resetUpdate();
            });
            Minecraft.getMinecraft().displayGuiScreen(null);
        } else if (legacyFile.exists()) {
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                QuestCommandDefaults.loadLegacy(null, null, legacyFile, false);
                SaveLoadHandler.INSTANCE.resetUpdate();
            });
            Minecraft.getMinecraft().displayGuiScreen(null);
        }

        ci.cancel();
    }
}
