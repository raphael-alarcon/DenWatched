package fr.den.watchedmod.tileentities;

import fr.den.watchedmod.blocks.BlockMovingLightSource;
import fr.den.watchedmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

/**
 * @author jabelar
 *
 */
public class TileEntityMovingLightSource extends TileEntity implements ITickable
{
    public EntityPlayer theEntity; // the entity holding the light-emitting item
    private boolean shouldDie;
    private static final int MAX_DEATH_TIMER = 4; // number of ticks a light source persists
    private int deathTimer; 
    public boolean typeFlashlight;

    public TileEntityMovingLightSource(EntityPlayer p)
    {
        // after constructing the tile entity instance, remember to call the setEntityLiving() method.
        shouldDie = false;
        typeFlashlight = false;
        deathTimer = MAX_DEATH_TIMER;
        theEntity = p;
        
//        DEBUG
//        System.out.println("Constructing");
    }
    
    @Override
    public void update()
    {
//         // DEBUG
//        if (theEntity instanceof EntityArrow)
//        {
//            System.out.println(this+" at pos = "+getPos()+" for block "+world.getBlockState(getPos()).getBlock()+" placed by entity = "+theEntity+" still ticking with shouldDie = "+shouldDie);
//        }
        
        // check if already dying
        if (!typeFlashlight && shouldDie)
        {
//             DEBUG
//             System.out.println("Should die = "+shouldDie+" with deathTimer = "+deathTimer);
            if (deathTimer > 0)
            {
                deathTimer--;
            }
            else
            {
                world.setBlockToAir(getPos());
                world.removeTileEntity(getPos());
            }
            return;
        }
        
        if (theEntity == null || theEntity.isDead)
        {
/*          DEBUG
            System.out.println("The associated entity is null or dead"); */
            shouldDie = true;
            world.setBlockToAir(getPos());
            world.removeTileEntity(getPos());
            return;
        }
       
        if (!this.typeFlashlight)
        {
            Block theLightBlock = ModBlocks.movinglightsource;
            if (theLightBlock == null)
            {
                shouldDie = true;
                world.setBlockToAir(getPos());
                world.removeTileEntity(getPos());
            }

            /*
             * check if entityLiving has moved away from the tile entity or no longer holding light emitting item set block to air
             */
            double distanceSquared = getDistanceSq(theEntity.posX, theEntity.posY, theEntity.posZ);
            if (distanceSquared > 4.0D)
            {
    //            DEBUG
    //            if (theEntity instanceof EntityArrow)
    //            {
    //                System.out.println(this+" is setting block to shouldDie because entity moved away, with distance squared = "+distanceSquared+" from entity at position ="+theEntity.getPosition());
    //            }
    //            
    //            if (!(blockAtLocation instanceof BlockMovingLightSource))
    //            {
    //                DEBUG
    //                System.out.println(this+" is unexpectedly not in position with a light block");
    //            }
                shouldDie = true;

            } else if (!BlockMovingLightSource.isLightEmittingItem(
                    theEntity.getHeldItemMainhand().getItem()) || (BlockMovingLightSource.isLightEmittingItem(theEntity.getHeldItemMainhand().getItem()) && theEntity.getHeldItemMainhand().getItemDamage() == theEntity.getHeldItemMainhand().getMaxDamage())) {
                shouldDie = true;
            }
        }
    }

    @Override
    public void setPos(BlockPos posIn)
    {
        pos = posIn.toImmutable();
        theEntity = world.getClosestPlayer(pos.getX(),pos.getY(),pos.getZ(), 2.0D, false);
    }
}
