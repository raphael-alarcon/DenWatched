package fr.den.watchedmod.gui;

import fr.den.watchedmod.gui.components.*;
import fr.den.watchedmod.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.Int;

import java.awt.*;
import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiCharacterCreation extends GuiScreen {

    private final ResourceLocation background = new ResourceLocation("watchedmod:textures/gui/gui_background.png");

    private final ResourceLocation icon = new ResourceLocation("watchedmod:textures/gui/icon_character.png");

    private final int xSize = 256;
    private final int ySize = 256;

    private int guiLeft;
    private int guiTop;

    private String errorMessage = "";

    private int errorMessageTimer;

    private GuiButton closeButton;
    private GuiButton confirmButton;

    private CustomTextFieldCharacter characterName;
    private CustomTextFieldCharacter characterSurname;

    private CustomTextFieldCharacter characterAge;
    private GuiButton characterAgeRemove;
    private GuiButton characterAgeAdd;


    private CustomButtonGender characterGenderMale;
    private CustomButtonGender characterGenderFemale;

    private final Minecraft minecraft = Minecraft.getMinecraft();

    public void initGui() {
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        buttonList.clear();
        buttonList.add(closeButton = new CustomButtonClose(0, guiLeft - 32 + xSize - 16, guiTop + 32, 32, 32, "", new ResourceLocation(References.MODID, "textures/gui/button_close.png")));
        characterName = new CustomTextFieldCharacter(1, mc.fontRenderer, guiLeft+40, guiTop + 60, 100, 16, new ResourceLocation(References.MODID, "textures/gui/text_field.png"), "Nom");
        characterSurname = new CustomTextFieldCharacter(2, mc.fontRenderer, guiLeft+40, guiTop + 100, 100, 16, new ResourceLocation(References.MODID, "textures/gui/text_field.png"), "Prénom");
        characterAge = new CustomTextFieldCharacter(8, mc.fontRenderer, guiLeft + 40, guiTop + 140, 100, 16, new ResourceLocation(References.MODID, "textures/gui/text_field.png"), "20");
        buttonList.add(characterAgeRemove = new CustomButtonAge(3, guiLeft + 45, guiTop + 143, 15, 9, "<", new ResourceLocation(References.MODID, "textures/gui/button_age.png")));
        buttonList.add(characterAgeAdd = new CustomButtonAge(4, guiLeft + 40 + 100 - 20, guiTop + 143, 15, 9, ">", new ResourceLocation(References.MODID, "textures/gui/button_age.png")));
        characterGenderMale = new CustomButtonGender(5, guiLeft +50, guiTop+165, 40, 40, "", new ResourceLocation(References.MODID, "textures/gui/button_male.png"), true);
        characterGenderFemale = new CustomButtonGender(6, guiLeft + 110, guiTop+165, 40, 40, "", new ResourceLocation(References.MODID, "textures/gui/button_female.png"), false);
        buttonList.add(characterGenderMale);
        buttonList.add(characterGenderFemale);
        buttonList.add(confirmButton = new CustomButtonConfirm(7, guiLeft + 40, guiTop + 200, 100, 16, "Confirmer", new ResourceLocation(References.MODID, "textures/gui/text_field.png")));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        characterName.textboxKeyTyped(typedChar, keyCode);
        characterSurname.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.player.closeScreen();
        } else if (button.id == 3) {
            if (Integer.parseInt(characterAge.getText()) > 18) {
                int n = Integer.parseInt(characterAge.getText());
                n--;
                characterAge.setText("" + (n));
            }
        } else if (button.id == 4) {
            if (Integer.parseInt(characterAge.getText()) < 90) {
                int n = Integer.parseInt(characterAge.getText());
                n++;
                characterAge.setText("" + (n));
            }
        } else if (button.id == 5) {
            characterGenderMale.isChosen = true;
            characterGenderFemale.isChosen = false;
        } else if (button.id == 6) {
            characterGenderMale.isChosen = false;
            characterGenderFemale.isChosen = true;
        } else if (button.id == 7) {
            if (!characterName.getText().startsWith("Nom") && !characterName.getText().startsWith("§cNom") && !characterSurname.getText().startsWith("Prénom") && !characterName.getText().startsWith("§cPrénom")) {
                characterName.setText(characterName.getText().replace("§c", ""));
                characterSurname.setText(characterSurname.getText().replace("§c", ""));
                this.mc.player.sendChatMessage(characterName.getText() + " " + characterSurname.getText() + " " + characterAge.getText() + " " + getSelectedStateGender());
                this.mc.player.closeScreen();
            } else {
                errorMessageTimer = 1000;
                errorMessage = "Certains champs sont incorrects.";
                characterName.setText(characterName.getText().replace("§c", ""));
                characterSurname.setText(characterSurname.getText().replace("§c", ""));
                if (characterName.getText().startsWith("Nom")) {
                    characterName.setText("§c" + characterName.getText());
                } else {
                    if (characterName.getText().startsWith("§c"))characterName.setText(characterName.getText().replace("§c", ""));
                }
                if (characterSurname.getText().startsWith("Prénom")) {
                    characterSurname.setText("§c" + characterSurname.getText());
                } else {
                    if (characterSurname.getText().startsWith("§c"))characterSurname.setText(characterSurname.getText().replace("§c", ""));
                }
            }
        }
    }

    private int getSelectedStateGender() {
        if (characterGenderMale.isChosen) {
            return 0;
        }
        return 1;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        characterName.updateCursorCounter();
        characterSurname.updateCursorCounter();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackgroundImage();
        GlStateManager.color(1F, 1F, 1F, 1F);
        drawEntityOnScreen(guiLeft+190, guiTop+170, 40, guiLeft+190-mouseX, guiTop+110-mouseY, mc.player);
        if (errorMessageTimer>0)errorMessageTimer--;
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (characterName.getText().startsWith("§c") && !characterName.getText().startsWith("§cNom")) {
            characterName.setText(characterName.getText().replace("§c", ""));
        }
        if (characterSurname.getText().startsWith("§c") && !characterSurname.getText().startsWith("§cPrénom")) {
            characterSurname.setText(characterSurname.getText().replace("§c", ""));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        characterName.mouseClicked(mouseX, mouseY, mouseButton);
        characterSurname.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private void drawBackgroundImage() {
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        minecraft.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        mc.getTextureManager().bindTexture(icon);
        drawModalRectWithCustomSizedTexture(guiLeft + 32, guiTop + 32, 0, 0, 16, 16, 16, 16);

        characterName.drawTextBox();
        characterSurname.drawTextBox();
        characterAge.drawTextBox();

        drawString(fontRenderer, "Création de personnage", guiLeft + 53, guiTop + 36, Color.LIGHT_GRAY.getRGB());

        if (errorMessageTimer > 0) {
            GlStateManager.scale(0.6F, 0.6F, 0.6F);
            drawString(fontRenderer, "§c" + errorMessage, (int) (confirmButton.x / 0.6), (int) ((confirmButton.y + 20) / 0.6), 0xFFFFF);
            if (errorMessageTimer == 1) {
                characterName.setText(characterName.getText().replace("§c", "§r"));
                characterSurname.setText(characterSurname.getText().replace("§c", "§r"));
            }
        } else {
            characterName.setText(characterName.getText().replace("§c", ""));
            characterSurname.setText(characterSurname.getText().replace("§c", ""));
        }
        GlStateManager.popMatrix();
    }

    //region Misc
    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
    //endregion
}