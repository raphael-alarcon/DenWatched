package fr.den.watchedmod.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CustomButtonClose extends GuiButton {

    protected final ResourceLocation texture;

    public CustomButtonClose(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ResourceLocation texture) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.texture = texture;
    }

    public void drawButton(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        FontRenderer fr = mc.fontRenderer;
        GlStateManager.color(1F, 1F, 1F, 1F);
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width/2 && mouseY < this.y + this.height/2;
        this.mouseDragged(mc, mouseX, mouseY);
        int i = this.getHoverState(this.hovered);
        mc.getTextureManager().bindTexture(texture);
        drawModalRectWithCustomSizedTexture(this.x, this.y, 0, i * this.height / 2 - this.height / 2, this.width, this.height / 2, this.width, this.height);
    }

}
