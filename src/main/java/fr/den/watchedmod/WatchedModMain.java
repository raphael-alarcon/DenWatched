package fr.den.watchedmod;

import com.google.common.collect.Lists;
import fr.den.watchedmod.ct.WatchedModTab;
import fr.den.watchedmod.events.*;
import fr.den.watchedmod.init.ModItems;
import fr.den.watchedmod.items.tooltip.TooltipHandler;
import fr.den.watchedmod.proxy.CommonProxy;
import fr.den.watchedmod.tileentities.TileEntityMovingLightSource;
import fr.den.watchedmod.utils.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.ArrayList;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION)
public class WatchedModMain {

    @Mod.Instance(References.MODID)
    public static WatchedModMain instance;

    //region Proxy
    @SidedProxy(clientSide = References.CP ,serverSide = References.SP)
    public static CommonProxy proxy;

    public static final String prefix = "§3Watched §8» §f";

    public static final CreativeTabs modtab = new WatchedModTab("WatchedModtab");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit();
        if (e.getSide().isClient()) {
            MinecraftForge.EVENT_BUS.register(new ProgressBarRender());
            MinecraftForge.EVENT_BUS.register(new TooltipHandler());
            ClientCommandHandler.instance.registerCommand(new CharacterMenuCommand());
        }
        MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerClickHandler());
        MinecraftForge.EVENT_BUS.register(new MovingLightEvent());
        MinecraftForge.EVENT_BUS.register(new RegisteringEvent());
        GameRegistry.registerTileEntity(TileEntityMovingLightSource.class, new ResourceLocation(References.MODID, "TileEntityMovingLightSource"));
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        removeRecipes();
        GameRegistry.addShapedRecipe(new ResourceLocation(References.MODID, "recipes/flashlight.json"), null, new ItemStack(ModItems.flashlight), "#O", '#', new ItemStack(ModItems.battery,1,0), 'O', ModItems.flashlight);
        proxy.postInit();
    }
    //endregion


    public static void removeRecipes() {
        ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES;
        ArrayList<IRecipe> recipes = Lists.newArrayList(recipeRegistry.getValuesCollection());

        for (IRecipe r : recipes) {
            recipeRegistry.remove(r.getRegistryName());
        }
    }
}
