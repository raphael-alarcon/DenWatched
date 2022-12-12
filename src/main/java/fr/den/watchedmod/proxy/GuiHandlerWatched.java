package fr.den.watchedmod.proxy;

import fr.den.watchedmod.gui.GuiCharacterCreation;
import fr.den.watchedmod.gui.GuiGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

public class GuiHandlerWatched implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) {
            return new GuiGenerator();
        } else if (ID == 1) {
            return new GuiCharacterCreation();
        }
        return null;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) {
            return new GuiGenerator();
        } else if (ID == 1) {
            return new GuiCharacterCreation();
        }
        return null;
    }
}
