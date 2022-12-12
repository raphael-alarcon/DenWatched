package fr.den.watchedmod.items;

import fr.den.watchedmod.WatchedModMain;
import fr.den.watchedmod.events.IHasModel;
import fr.den.watchedmod.init.ModItems;
import net.minecraft.item.ItemFood;

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
