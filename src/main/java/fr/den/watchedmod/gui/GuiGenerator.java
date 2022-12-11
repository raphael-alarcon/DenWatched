package fr.den.watchedmod.gui;

import fr.den.watchedmod.gui.components.CustomButtonClose;
import fr.den.watchedmod.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class GuiGenerator extends GuiScreen {

    private final ResourceLocation background = new ResourceLocation("watchedmod:textures/gui/gui_background.png");

    private final ResourceLocation icon = new ResourceLocation("watchedmod:textures/gui/icon_generator.png");

    private final int xSize = 256;
    private final int ySize = 256;

    private int guiLeft;
    private int guiTop;

    private GuiButton closeButton;

    private final Minecraft minecraft = Minecraft.getMinecraft();

    public void initGui() {
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        buttonList.clear();
        buttonList.add(closeButton = new CustomButtonClose(0, guiLeft - 32 + xSize - 24, guiTop + 32, 32, 32, "", new ResourceLocation(References.MODID, "textures/gui/button_close.png")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.player.closeScreen();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackgroundImage();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawBackgroundImage() {
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        minecraft.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        mc.getTextureManager().bindTexture(icon);
        drawModalRectWithCustomSizedTexture(guiLeft + 32, guiTop + 32, 0, 0, 16, 16, 16, 16);


        drawString(fontRenderer, "Génerateur", guiLeft + 53, guiTop + 36, Color.LIGHT_GRAY.getRGB());
        GlStateManager.popMatrix();
    }
}