package fr.den.watchedmod.ct;

import fr.den.watchedmod.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class WatchedModTab extends CreativeTabs {

    public WatchedModTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModBlocks.computer_block);
    }

}
