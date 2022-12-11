package fr.den.watchedmod.items.healItems;

import fr.den.watchedmod.WatchedModMain;
import fr.den.watchedmod.events.ProgressBarRender;
import fr.den.watchedmod.events.IHasModel;
import fr.den.watchedmod.init.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class WatchedModHealItem extends ItemFood implements IHasModel {

    public static float progress = 100F;
    private boolean alwaysEdible = true;

    private int itemUseDuration;
    public static boolean isUsing = false;

    public WatchedModHealItem(String name, int durationUseTicks, int healAmount) {
        super(healAmount, 0, false);
        this.itemUseDuration = durationUseTicks;
        setRegistryName(name).setUnlocalizedName(name);
        ModItems.items.add(this);
        setMaxStackSize(1);
        setCreativeTab(WatchedModMain.modtab);

    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return itemUseDuration;
    }

    @Override
    public void registerModels() {
        WatchedModMain.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        progress = itemUseDuration;

        if (entityLiving instanceof EntityPlayer)
        {
            isUsing = false;
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            entityLiving.setHealth(entityLiving.getHealth() + getHealAmount(new ItemStack(this)));
        }

        stack.shrink(1);
        return stack;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (playerIn.canEat(this.alwaysEdible))
        {
            isUsing = true;
            if (playerIn.world.isRemote) ProgressBarRender.setRatio(itemUseDuration/100);
            playerIn.setActiveHand(handIn);
            progress -= 1;
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }
        else
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        isUsing = false;
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        progress = itemUseDuration;
        return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(" ");
        tooltip.add("§7Soigne §f" + (getHealAmount(new ItemStack(this))/2*10) + "% §7de la santé (" + (itemUseDuration / 20) + "s).");
    }
}
