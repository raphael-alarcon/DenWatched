package fr.den.watchedmod.events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.den.watchedmod.items.healItems.WatchedModHealItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

public class PlayerTickHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void refreshProgress(TickEvent.PlayerTickEvent event) {
        if (WatchedModHealItem.progress >= 0 && WatchedModHealItem.progress < ProgressBarRender.BAR_RATIO*100 && event.side.isServer()) {
            if (WatchedModHealItem.isUsing) {
                WatchedModHealItem.progress -= 0.5;
            } else {
                WatchedModHealItem.progress += 1;
            }
        }

        if (PlayerClickHandler.isUsing && (!Mouse.isButtonDown(1) || event.player.world.getBlockState(Minecraft.getMinecraft().objectMouseOver.getBlockPos()).getBlock() != Blocks.MELON_BLOCK)) {
            PlayerClickHandler.isUsing = false;
            System.out.println("####");
        }
    }
}
