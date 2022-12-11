package fr.den.watchedmod.init;

import fr.den.watchedmod.items.WatchedModBatteryItem;
import fr.den.watchedmod.items.WatchedModFlashLightItem;
import fr.den.watchedmod.items.WatchedModFoodItem;
import fr.den.watchedmod.items.healItems.WatchedModHealItem;
import fr.den.watchedmod.items.WatchedModGenericItem;
import fr.den.watchedmod.utils.References;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = References.MODID)
public class ModItems {

    public static final List<Item> items = new ArrayList<>();

    public static Item bandage = new WatchedModHealItem("bandage", 100, 6);
    public static Item medkit = new WatchedModHealItem("medkit", 300, 10);

    public static Item alimentation = new WatchedModGenericItem("alimentation");
    public static Item ram = new WatchedModGenericItem("ram");
    public static Item usbkey = new WatchedModGenericItem("usbkey");
    public static Item wrench = new WatchedModGenericItem("wrench");
    public static Item handcuffs = new WatchedModGenericItem("handcuffs");
    public static Item battery = new WatchedModBatteryItem("battery");
    public static Item flashlight = new WatchedModFlashLightItem("flashlight");
    public static Item key = new WatchedModGenericItem("key");
    public static Item ration = new WatchedModFoodItem("ration", 8, 8, false);
    public static Item screws = new WatchedModGenericItem("screws");
    public static Item gear = new WatchedModGenericItem("gear");
}
