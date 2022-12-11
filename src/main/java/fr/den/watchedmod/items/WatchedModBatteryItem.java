package fr.den.watchedmod.items;

import fr.den.watchedmod.WatchedModMain;
import fr.den.watchedmod.events.IHasModel;
import fr.den.watchedmod.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderTooltipEvent;
import org.jetbrains.annotations.NotNull;

public class WatchedModBatteryItem extends Item implements IHasModel {
    public WatchedModBatteryItem(String name) {
        setRegistryName(name).setUnlocalizedName(name);
        ModItems.items.add(this);
        setMaxStackSize(1);
        setMaxDamage(1);
        setCreativeTab(WatchedModMain.modtab);
    }

    @Override
    public void registerModels() {
        WatchedModMain.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public @NotNull ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack item = new ItemStack(this, 1, itemStack.getItemDamage() + 1);
        return item;
    }

    @Override
    public Item setContainerItem(Item containerItem) {
        return this;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
