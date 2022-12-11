package fr.den.watchedmod.events;

import fr.den.watchedmod.blocks.BlockMovingLightSource;
import fr.den.watchedmod.init.ModBlocks;
import fr.den.watchedmod.items.WatchedModFlashLightItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class MovingLightEvent {

    @SubscribeEvent
    public void onEvent(TickEvent.PlayerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START && !event.side.isClient())
        {
            if (event.player.getHeldItemMainhand() != ItemStack.EMPTY)
            {
                if (BlockMovingLightSource.isLightEmittingItem(
                        event.player.getHeldItemMainhand().getItem()) && event.player.getHeldItemMainhand().getItemDamage() != event.player.getHeldItemMainhand().getMaxDamage())
                {
                    BlockMovingLightSource.player = event.player;
                    if (WatchedModFlashLightItem.cooldownDamage == 0) {
                        event.player.getHeldItemMainhand().damageItem(1, event.player);
                        WatchedModFlashLightItem.cooldownDamage = 300;
                    } else {
                        WatchedModFlashLightItem.cooldownDamage--;
                    }
                    int blockX = MathHelper.floor(event.player.posX);
                    int blockY = MathHelper.floor(event.player.posY-0.2D -
                            event.player.getYOffset());
                    int blockZ = MathHelper.floor(event.player.posZ);
                    // place light at head level
                    BlockPos blockLocation = new BlockPos(blockX, blockY, blockZ).up();
                    if (event.player.world.getBlockState(blockLocation).getBlock() ==
                            Blocks.AIR)
                    {
                        event.player.world.setBlockState(
                                blockLocation,
                                ModBlocks.movinglightsource.getDefaultState());
                    }
                    else if (event.player.world.getBlockState(
                            blockLocation.add(
                                    event.player.getLookVec().x,
                                    event.player.getLookVec().y,
                                    event.player.getLookVec().z)).getBlock() == Blocks.AIR)
                    {
                        event.player.world.setBlockState(
                                blockLocation.add(
                                        event.player.getLookVec().x,
                                        event.player.getLookVec().y,
                                        event.player.getLookVec().z),
                                ModBlocks.movinglightsource.getDefaultState());
                    }
                }
            }
        }
    }

}
