package fr.den.watchedmod.events;

import fr.den.watchedmod.items.healItems.WatchedModHealItem;
import fr.den.watchedmod.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ProgressBarRender extends Gui {

    private static int BAR_POSX;
    private static int BAR_POSY;

    private static int BAR_WIDTH;
    private static int BAR_HEIGHT;
    public static int BAR_RATIO;

    private static ResourceLocation overlayBar;

    public ProgressBarRender() {
        BAR_WIDTH = 100;
        BAR_HEIGHT = 8;
        BAR_RATIO = 1;
        overlayBar = new ResourceLocation(References.MODID,"textures/gui/rect.png");

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void displayProgressBar(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (WatchedModHealItem.progress >= 0 && WatchedModHealItem.progress < BAR_RATIO*BAR_WIDTH) {
                BAR_POSX = (event.getResolution().getScaledWidth() - BAR_WIDTH) / 2;
                BAR_POSY = (event.getResolution().getScaledHeight() - 70);

                drawCenteredString(Minecraft.getMinecraft().fontRenderer, "§fSoin en cours...", event.getResolution().getScaledWidth()/2, BAR_POSY-10, 0xFFFFF);

                GlStateManager.pushMatrix();

                GlStateManager.enableBlend();
                GlStateManager.color(0.5F, 0.5F, 0.5F, 0.5F);

                Minecraft.getMinecraft().getTextureManager().bindTexture(overlayBar);
                drawTexturedModalRect(BAR_POSX, BAR_POSY, 0, 0, BAR_WIDTH, BAR_HEIGHT);

                GlStateManager.color(255/255F, 102/255F, 153/255F, 255.0F);
                drawTexturedModalRect(BAR_POSX, BAR_POSY, 0, 0, (int) WatchedModHealItem.progress / BAR_RATIO, BAR_HEIGHT);

                GlStateManager.scale(0.7F, 0.7F, 1F);
                drawCenteredString(Minecraft.getMinecraft().fontRenderer, "§f" + String.format("%.1f", WatchedModHealItem.progress / 20), (int)(BAR_POSX/0.7) + 15, (int)(BAR_POSY/0.7) + (BAR_HEIGHT - 4) / 2, 0xFFFFF);
                GlStateManager.scale(1F, 1F, 1F);
                GlStateManager.popMatrix();
            } else if (PlayerClickHandler.progress > 0 && PlayerClickHandler.progress < BAR_RATIO*BAR_WIDTH) {
                BAR_POSX = (event.getResolution().getScaledWidth() - BAR_WIDTH) / 2;
                BAR_POSY = (event.getResolution().getScaledHeight() - 70);

                drawCenteredString(Minecraft.getMinecraft().fontRenderer, "§fAction en cours...", event.getResolution().getScaledWidth()/2, BAR_POSY-10, 0xFFFFF);

                GlStateManager.pushMatrix();

                GlStateManager.enableBlend();
                GlStateManager.color(0.5F, 0.5F, 0.5F, 0.5F);

                Minecraft.getMinecraft().getTextureManager().bindTexture(overlayBar);
                drawTexturedModalRect(BAR_POSX, BAR_POSY, 0, 0, BAR_WIDTH, BAR_HEIGHT);

                GlStateManager.color(162/255F, 62/255F, 72/255F, 255.0F);
                drawTexturedModalRect(BAR_POSX, BAR_POSY, 0, 0, (int) PlayerClickHandler.progress / BAR_RATIO, BAR_HEIGHT);

                GlStateManager.scale(0.7F, 0.7F, 1F);
                drawCenteredString(Minecraft.getMinecraft().fontRenderer, "§f" + String.format("%.1f", (PlayerClickHandler.blockUseDuration - PlayerClickHandler.progress) / 10), (int)(BAR_POSX/0.7) + 15, (int)(BAR_POSY/0.7) + (BAR_HEIGHT - 4) / 2, 0xFFFFF);
                GlStateManager.scale(1F, 1F, 1F);
                GlStateManager.popMatrix();
            }
        }
    }

    public static void setRatio(int n) {
        BAR_RATIO = n;
    }

}
