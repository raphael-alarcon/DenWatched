package fr.den.watchedmod.items.tooltip;

import fr.den.watchedmod.init.ModItems;
import fr.den.watchedmod.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class TooltipHandler extends Gui {

    private static ResourceLocation itemTexture = new ResourceLocation("watchedmod:textures/items/battery.png");

    private static int iconSize = 10;

    @SubscribeEvent
    public void onRenderTooltip(RenderTooltipEvent.PostText event) {
        if (event.getStack().getItem() == ModItems.flashlight) {

            int iconX = event.getX();
            int iconY = event.getY();

            for (int i = 0 ; i < event.getLines().size() ; i++) {
                if(event.getLines().get(i).contains("§7Énergie §8» ")) {
                    iconX += event.getFontRenderer().getStringWidth(event.getLines().get(i)) - 3;
                    iconY += i * (event.getFontRenderer().FONT_HEIGHT + 1);
                    break;
                }
            }

            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(itemTexture);
            drawModalRectWithCustomSizedTexture(iconX, iconY, 0, 0, iconSize, iconSize, iconSize, iconSize);
            GlStateManager.popMatrix();
        }
    }
}
