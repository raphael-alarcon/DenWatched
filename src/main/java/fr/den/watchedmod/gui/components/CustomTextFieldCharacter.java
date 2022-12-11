package fr.den.watchedmod.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class CustomTextFieldCharacter extends GuiTextField {

    protected final ResourceLocation texture;
    private FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    private boolean isEnabled = true;
    private int enabledColor = 14737632;
    private int disabledColor = 7368816;
    private int lineScrollOffset = 0;
    private int cursorCounter = 0;

    public CustomTextFieldCharacter(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height, ResourceLocation texture, String placeholder) {
        super(componentId, fontrendererObj, x, y, par5Width, par6Height);
        this.texture = texture;
        setMaxStringLength(15);
        setFocused(false);
        if (componentId == 3) {
            setMaxStringLength(2);
        }
        setText(placeholder);
    }

    @Override
    public void drawTextBox() {
        if (this.getVisible())
        {
            GlStateManager.pushMatrix();
            GlStateManager.color(111/255F, 119/255F, 136/255F, 1.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
            drawModalRectWithCustomSizedTexture(this.x, this.y, 0, this.height, this.width, this.height, this.width, this.height);
            GlStateManager.popMatrix();
            int i = this.isEnabled ? this.enabledColor : this.disabledColor;
            int j = this.getCursorPosition() - this.lineScrollOffset;
            int k = this.getSelectionEnd() - this.lineScrollOffset;
            String s = this.fontRenderer.trimStringToWidth(this.getText().substring(this.lineScrollOffset), this.getWidth());
            boolean flag = j >= 0 && j <= s.length();
            boolean flag1 = this.isFocused() && this.cursorCounter / 6 % 2 == 0 && flag;
            int l = this.x + this.width / 2 - fontRenderer.getStringWidth(s) / 2;
            int i1 = this.getEnableBackgroundDrawing() ? this.y + (this.height - 8) / 2 : this.y;
            int j1 = l;

            if (k > s.length())
            {
                k = s.length();
            }

            if (!s.isEmpty())
            {
                String s1 = flag ? s.substring(0, j) : s;
                j1 = this.fontRenderer.drawStringWithShadow(s1, (float)l, (float)i1, i);
            }

            boolean flag2 = this.getCursorPosition() < this.getText().length() || this.getText().length() >= this.getMaxStringLength();
            int k1 = j1;

            if (!flag)
            {
                k1 = j > 0 ? l + this.width : l;
            }
            else if (flag2)
            {
                k1 = j1 - 1;
                --j1;
            }

            if (!s.isEmpty() && flag && j < s.length())
            {
                j1 = this.fontRenderer.drawStringWithShadow(s.substring(j), (float)j1, (float)i1, i);
            }

            if (flag1)
            {
                if (flag2)
                {
                    Gui.drawRect(k1, i1 - 1, k1 + 1, i1 + 1 + this.fontRenderer.FONT_HEIGHT, -3092272);
                }
                else
                {
                    this.fontRenderer.drawStringWithShadow("_", (float)k1, (float)i1, i);
                }
            }

            if (k != j)
            {
                int l1 = l + this.fontRenderer.getStringWidth(s.substring(0, k));
                drawSelectionBox(k1, i1 - 1, l1 - 1, i1 + 1 + this.fontRenderer.FONT_HEIGHT);
            }
        }
    }

    private void drawSelectionBox(int startX, int startY, int endX, int endY)
    {
        if (startX < endX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        if (startY < endY)
        {
            int j = startY;
            startY = endY;
            endY = j;
        }

        if (endX > this.x + this.width)
        {
            endX = this.x + this.width;
        }

        if (startX > this.x + this.width)
        {
            startX = this.x + this.width;
        }

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.color(0.0F, 0.0F, 255.0F, 255.0F);
        GlStateManager.disableTexture2D();
        GlStateManager.enableColorLogic();
        GlStateManager.colorLogicOp(GlStateManager.LogicOp.OR_REVERSE);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)startX, (double)endY, 0.0D).endVertex();
        bufferbuilder.pos((double)endX, (double)endY, 0.0D).endVertex();
        bufferbuilder.pos((double)endX, (double)startY, 0.0D).endVertex();
        bufferbuilder.pos((double)startX, (double)startY, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.disableColorLogic();
        GlStateManager.enableTexture2D();
    }
}
