package fr.den.watchedmod.events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.den.watchedmod.init.ModItems;
import fr.den.watchedmod.items.healItems.WatchedModHealItem;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

public class PlayerClickHandler {

    public static float progress = 0F;
    public static boolean isUsing = false;

    public static int blockUseDuration = 100;

    @SubscribeEvent
    public void onRightClickOnBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntityPlayer().world.getBlockState(event.getPos()).getBlock() == Blocks.MELON_BLOCK
                && event.getEntityPlayer().getActiveItemStack().getItem() != ModItems.bandage) {
            if (!isUsing) {
                if (event.getEntityPlayer().world.isRemote) ProgressBarRender.setRatio(blockUseDuration / 100);
                isUsing = true;
                progress = 0F;
            }
            progress += 1F;
        }
    }

    @SubscribeEvent
    public void refreshProgress(TickEvent.PlayerTickEvent event) {
        if (!PlayerClickHandler.isUsing) {
            PlayerClickHandler.progress -= 1.0F;
        }
    }
}