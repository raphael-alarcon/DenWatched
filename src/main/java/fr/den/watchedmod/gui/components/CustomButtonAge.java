package fr.den.watchedmod.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CustomButtonAge extends GuiButton {

    protected final ResourceLocation texture;

    public CustomButtonAge(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ResourceLocation texture) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.texture = texture;
    }

    public void drawButton(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = mc.fontRenderer;
        if (hovered) {
            GlStateManager.color(154/255F, 0F, 243/255F, 1F);
        } else {
            GlStateManager.color(40/255F, 44/255F, 52/255F, 1.0F);
        }
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        this.mouseDragged(mc, mouseX, mouseY);
        int i = this.getHoverState(this.hovered);
        mc.getTextureManager().bindTexture(texture);
        drawModalRectWithCustomSizedTexture(this.x, this.y, 0, this.height, this.width, this.height, this.width, this.height);
        int j = 14737632;

        if (packedFGColour != 0)
        {
            j = packedFGColour;
        }
        else
        if (!this.enabled)
        {
            j = 10526880;
        }
        else if (this.hovered)
        {
            j = 16777120;
        }
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.9F, 0.9F, 0.9F);
        this.drawCenteredString(fr, this.displayString, (this.x + this.width / 2)/0.9, (this.y + (this.height - 8) / 2)/0.9+1, j);
        GlStateManager.popMatrix();
    }

    public void drawCenteredString(FontRenderer fontRendererIn, String text, double x, double y, int color) {
        fontRendererIn.drawStringWithShadow(text, (float)(x - fontRendererIn.getStringWidth(text) / 2), (float)y, color);
    }
}
