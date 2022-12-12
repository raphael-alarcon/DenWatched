package fr.den.watchedmod.proxy;

import fr.den.watchedmod.WatchedModMain;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

    public void preInit() {
        NetworkRegistry.INSTANCE.registerGuiHandler(WatchedModMain.instance, new GuiHandlerWatched());
    }

    public void init() {

    }

    public void postInit() {

    }

    public void registerItemRenderer(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public void registerVariantRenderer(Item item, int meta, String filename, String id)
    {

    }

    public void registerEntityRenderers()
    {

    }

}
