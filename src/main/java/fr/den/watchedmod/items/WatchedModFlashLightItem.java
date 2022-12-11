package fr.den.watchedmod.items;

import fr.den.watchedmod.WatchedModMain;
import fr.den.watchedmod.events.IHasModel;
import fr.den.watchedmod.init.ModItems;
import fr.den.watchedmod.utils.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class WatchedModFlashLightItem extends Item implements IHasModel {

    private String name;
    public static int cooldownDamage;

    public WatchedModFlashLightItem(String name) {
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);
        ModItems.items.add(this);
        setMaxStackSize(1);
        setCreativeTab(WatchedModMain.modtab);
        setMaxDamage(100);
        cooldownDamage = 300;
        this.addPropertyOverride(new ResourceLocation("damaged"), new IItemPropertyGetter()
        {

            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return (entityIn != null && stack.getItemDamage() == stack.getMaxDamage()) ? 1.0F : 0.0F ;
            }
        });
    }

    @Override
    public void registerModels() {
        WatchedModMain.proxy.registerItemRenderer(this, 0);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(" ");
        tooltip.add("§7Cet objet peut être");
        tooltip.add("§7rechargé à l'aide d'une");
        tooltip.add("§fbatterie§7.");
        tooltip.add(" ");
        tooltip.add("§7Énergie §8» ");
    }


}
