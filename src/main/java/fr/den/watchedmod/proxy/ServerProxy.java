package fr.den.watchedmod.proxy;

import net.minecraft.item.Item;

public class ServerProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();
    }

    @Override
    public void registerItemRenderer(Item item, int meta) {
        super.registerItemRenderer(item, meta);
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id) {
        super.registerVariantRenderer(item, meta, filename, id);
    }

    @Override
    public void registerEntityRenderers() {
        super.registerEntityRenderers();
    }
}
