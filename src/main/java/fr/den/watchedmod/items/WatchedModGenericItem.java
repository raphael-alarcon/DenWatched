package fr.den.watchedmod.items;

import fr.den.watchedmod.WatchedModMain;
import fr.den.watchedmod.events.IHasModel;
import fr.den.watchedmod.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class WatchedModGenericItem extends Item implements IHasModel {

    public WatchedModGenericItem(String name) {
        setRegistryName(name).setUnlocalizedName(name);
        ModItems.items.add(this);
        setMaxStackSize(1);
        setCreativeTab(WatchedModMain.modtab);
    }

    @Override
    public void registerModels() {
        WatchedModMain.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        player.openGui(WatchedModMain.instance, 1, null, 0, 0, 0);
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
