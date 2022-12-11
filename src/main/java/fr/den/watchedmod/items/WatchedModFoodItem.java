package fr.den.watchedmod.items;

import fr.den.watchedmod.WatchedModMain;
import fr.den.watchedmod.events.IHasModel;
import fr.den.watchedmod.init.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class WatchedModFoodItem extends ItemFood implements IHasModel {
    public WatchedModFoodItem(String name, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setRegistryName(name).setUnlocalizedName(name);
        ModItems.items.add(this);
        setMaxStackSize(3);
        setCreativeTab(WatchedModMain.modtab);
    }

    @Override
    public void registerModels() {
        WatchedModMain.proxy.registerItemRenderer(this, 0);
    }
}
