package fr.den.watchedmod.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CustomButtonConfirm extends GuiButton {

    protected final ResourceLocation texture;

    public CustomButtonConfirm(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ResourceLocation texture) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.texture = texture;
    }

    public void drawButton(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = mc.fontRenderer;
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        this.mouseDragged(mc, mouseX, mouseY);
        mc.getTextureManager().bindTexture(texture);
        if (hovered) {
            GlStateManager.color(154/255F, 0F, 243/255F, 1F);
        } else {
            GlStateManager.color(0.6F, 0.6F, 0.6F, 1F);
        }
        drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 0, this.width, this.height, this.width, this.height);
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
        this.drawCenteredString(fr, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2,  j);
    }

}
