package fr.den.watchedmod.events;

import fr.den.watchedmod.init.ModBlocks;
import fr.den.watchedmod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisteringEvent {

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(ModItems.items.toArray(new Item[0]));
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().registerAll(ModBlocks.blocks.toArray(new Block[0]));
    }

    @SubscribeEvent
    public void OnModelRegister(ModelRegistryEvent event) {
        for (Item item : ModItems.items) {
            if(item instanceof IHasModel) {
                ((IHasModel)item).registerModels();

            }
        }
        for (Block block : ModBlocks.blocks) {
            if(block instanceof IHasModel) {
                ((IHasModel)block).registerModels();
            }
        }
    }
}
