package fr.den.watchedmod.hud;

import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WatchedHUD {

    @SubscribeEvent
    public static void renderGameOverlayPre(RenderGameOverlayEvent.Pre event) {
        if (event.getType().equals(RenderGameOverlayEvent.ElementType.EXPERIENCE)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void renderGameOverlayPost(RenderGameOverlayEvent.Post event)
    {
    }

}
